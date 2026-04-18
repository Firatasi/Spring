# app/api/v1/cart.py
from fastapi import APIRouter, Depends, HTTPException, status
from sqlalchemy.orm import Session

from app.database import get_db
from app import crud, schemas
from app.api.dependencies import get_current_active_user

router = APIRouter()

@router.get("/", response_model=schemas.CartResponse)
async def get_cart(
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Get user's shopping cart"""
    cart_items = crud.cart.get_cart(db, user_id=current_user.id)
    
    # Calculate total
    total = crud.cart.calculate_cart_total(db, user_id=current_user.id)
    
    # Format response
    items_response = []
    for item in cart_items:
        items_response.append({
            "id": item.id,
            "product_id": item.product_id,
            "quantity": item.quantity,
            "product": item.product,
            "subtotal": float(item.product.price * item.quantity)
        })
    
    return {
        "items": items_response,
        "total": total
    }

@router.post("/items", status_code=status.HTTP_201_CREATED)
async def add_to_cart(
    cart_item: schemas.CartItemCreate,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Add product to cart"""
    
    # Verify product exists and has stock
    product = crud.product.get_product(db, cart_item.product_id)
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    
    if product.stock < cart_item.quantity:
        raise HTTPException(status_code=400, detail="Insufficient stock")
    
    # Add to cart
    cart_item_db = crud.cart.add_to_cart(
        db,
        user_id=current_user.id,
        product_id=cart_item.product_id,
        quantity=cart_item.quantity
    )
    
    return {"message": "Item added to cart", "cart_item_id": cart_item_db.id}

@router.patch("/items/{product_id}")
async def update_cart_item(
    product_id: int,
    cart_item_update: schemas.CartItemUpdate,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Update cart item quantity"""
    
    # Verify product stock
    product = crud.product.get_product(db, product_id)
    if product.stock < cart_item_update.quantity:
        raise HTTPException(status_code=400, detail="Insufficient stock")
    
    updated_item = crud.cart.update_cart_item(
        db,
        user_id=current_user.id,
        product_id=product_id,
        quantity=cart_item_update.quantity
    )
    
    if not updated_item:
        raise HTTPException(status_code=404, detail="Cart item not found")
    
    return {"message": "Cart item updated"}

@router.delete("/items/{product_id}", status_code=status.HTTP_204_NO_CONTENT)
async def remove_from_cart(
    product_id: int,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Remove item from cart"""
    success = crud.cart.remove_from_cart(db, current_user.id, product_id)
    if not success:
        raise HTTPException(status_code=404, detail="Cart item not found")
    return None

@router.delete("/", status_code=status.HTTP_204_NO_CONTENT)
async def clear_cart(
    db: Session = Depends(get_db),
    current_user = Depends(get_current_active_user)
):
    """Clear cart"""
    crud.cart.clear_cart(db, current_user.id)
    return None