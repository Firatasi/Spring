# app/api/v1/orders.py
from fastapi import APIRouter, Depends, HTTPException, status, BackgroundTasks
from sqlalchemy.orm import Session
from typing import List

from app.database import get_db
from app import crud, schemas
from app.api.dependencies import get_current_active_user, get_current_admin_user

router = APIRouter()

@router.post("/", response_model=schemas.OrderResponse, status_code=status.HTTP_201_CREATED)
async def create_order(
    order: schemas.OrderCreate,
    background_tasks: BackgroundTasks,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Create order from cart"""
    
    try:
        new_order = crud.order.create_order_from_cart(
            db,
            user_id=current_user.id,
            shipping_address=order.shipping_address
        )
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    
    # Send order confirmation email (background)
    # background_tasks.add_task(send_order_confirmation_email, current_user.email, new_order.id)
    
    return new_order

@router.get("/", response_model=List[schemas.OrderResponse])
async def list_orders(
    skip: int = 0,
    limit: int = 100,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """List user's orders"""
    orders = crud.order.get_user_orders(db, current_user.id, skip, limit)
    return orders

@router.get("/{order_id}", response_model=schemas.OrderResponse)
async def get_order(
    order_id: int,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Get order details"""
    order = crud.order.get_order(db, order_id)
    
    if not order:
        raise HTTPException(status_code=404, detail="Order not found")
    
    # Verify ownership
    if order.user_id != current_user.id and not current_user.is_admin:
        raise HTTPException(status_code=403, detail="Not authorized")
    
    return order

@router.patch("/{order_id}/status")
async def update_order_status(
    order_id: int,
    status: str,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_admin_user)
):
    """Update order status (Admin only)"""
    
    valid_statuses = ["pending", "confirmed", "shipped", "delivered", "cancelled"]
    if status not in valid_statuses:
        raise HTTPException(status_code=400, detail="Invalid status")
    
    updated_order = crud.order.update_order_status(db, order_id, status)
    if not updated_order:
        raise HTTPException(status_code=404, detail="Order not found")
    
    return {"message": "Order status updated", "status": status}

@router.post("/{order_id}/payment")
async def process_payment(
    order_id: int,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Process payment for order"""
    
    order = crud.order.get_order(db, order_id)
    
    if not order:
        raise HTTPException(status_code=404, detail="Order not found")
    
    if order.user_id != current_user.id:
        raise HTTPException(status_code=403, detail="Not authorized")
    
    if order.payment_status == "paid":
        raise HTTPException(status_code=400, detail="Order already paid")
    
    # Process payment (Stripe simulation)
    # payment_successful = process_stripe_payment(order.total_amount)
    payment_successful = True  # Simulated
    
    if payment_successful:
        crud.order.update_payment_status(db, order_id, "paid")
        crud.order.update_order_status(db, order_id, "confirmed")
        return {"message": "Payment successful", "order_id": order_id}
    else:
        crud.order.update_payment_status(db, order_id, "failed")
        raise HTTPException(status_code=400, detail="Payment failed")