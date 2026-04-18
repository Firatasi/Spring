# app/crud/cart.py
from sqlalchemy.orm import Session
from typing import List, Optional
from app.models.cart import CartItem
from app.models.product import Product

def get_cart(db: Session, user_id: int) -> List[CartItem]:
    return db.query(CartItem).filter(CartItem.user_id == user_id).all()

def add_to_cart(db: Session, user_id: int, product_id: int, quantity: int) -> CartItem:
    # Check if item already in cart
    cart_item = db.query(CartItem).filter(
        CartItem.user_id == user_id,
        CartItem.product_id == product_id
    ).first()
    
    if cart_item:
        # Update quantity
        cart_item.quantity += quantity
    else:
        # Create new cart item
        cart_item = CartItem(
            user_id=user_id,
            product_id=product_id,
            quantity=quantity
        )
        db.add(cart_item)
    
    db.commit()
    db.refresh(cart_item)
    return cart_item

def update_cart_item(db: Session, user_id: int, product_id: int, quantity: int) -> Optional[CartItem]:
    cart_item = db.query(CartItem).filter(
        CartItem.user_id == user_id,
        CartItem.product_id == product_id
    ).first()
    
    if not cart_item:
        return None
    
    cart_item.quantity = quantity
    db.commit()
    db.refresh(cart_item)
    return cart_item

def remove_from_cart(db: Session, user_id: int, product_id: int) -> bool:
    cart_item = db.query(CartItem).filter(
        CartItem.user_id == user_id,
        CartItem.product_id == product_id
    ).first()
    
    if not cart_item:
        return False
    
    db.delete(cart_item)
    db.commit()
    return True

def clear_cart(db: Session, user_id: int):
    db.query(CartItem).filter(CartItem.user_id == user_id).delete()
    db.commit()

def calculate_cart_total(db: Session, user_id: int) -> float:
    cart_items = get_cart(db, user_id)
    total = sum(item.product.price * item.quantity for item in cart_items)
    return float(total)