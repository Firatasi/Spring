# app/crud/order.py
from sqlalchemy.orm import Session
from typing import List, Optional
from app.models.order import Order, OrderItem
from app.models.cart import CartItem
from app import crud

def create_order_from_cart(
    db: Session,
    user_id: int,
    shipping_address: str
) -> Order:
    """Create order from user's cart"""
    
    # Get cart items
    cart_items = crud.cart.get_cart(db, user_id)
    
    if not cart_items:
        raise ValueError("Cart is empty")
    
    # Calculate total
    total_amount = sum(item.product.price * item.quantity for item in cart_items)
    
    # Create order
    order = Order(
        user_id=user_id,
        total_amount=total_amount,
        shipping_address=shipping_address,
        status="pending",
        payment_status="pending"
    )
    db.add(order)
    db.flush()
    
    # Create order items
    for cart_item in cart_items:
        # Check stock
        if cart_item.product.stock < cart_item.quantity:
            db.rollback()
            raise ValueError(f"Insufficient stock for {cart_item.product.name}")
        
        order_item = OrderItem(
            order_id=order.id,
            product_id=cart_item.product_id,
            quantity=cart_item.quantity,
            price=cart_item.product.price  # Snapshot price
        )
        db.add(order_item)
        
        # Update stock
        cart_item.product.stock -= cart_item.quantity
    
    # Clear cart
    crud.cart.clear_cart(db, user_id)
    
    db.commit()
    db.refresh(order)
    
    return order

def get_order(db: Session, order_id: int) -> Optional[Order]:
    return db.query(Order).filter(Order.id == order_id).first()

def get_user_orders(db: Session, user_id: int, skip: int = 0, limit: int = 100) -> List[Order]:
    return db.query(Order).filter(Order.user_id == user_id).offset(skip).limit(limit).all()

def update_order_status(db: Session, order_id: int, status: str) -> Optional[Order]:
    order = get_order(db, order_id)
    if not order:
        return None
    
    order.status = status
    db.commit()
    db.refresh(order)
    return order

def update_payment_status(db: Session, order_id: int, payment_status: str) -> Optional[Order]:
    order = get_order(db, order_id)
    if not order:
        return None
    
    order.payment_status = payment_status
    db.commit()
    db.refresh(order)
    return order