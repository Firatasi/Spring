# app/api/v1/admin.py
from fastapi import APIRouter, Depends, Query
from sqlalchemy.orm import Session
from typing import List

from app.database import get_db
from app import crud, schemas
from app.api.dependencies import get_current_admin_user

router = APIRouter()

@router.get("/dashboard")
async def admin_dashboard(
    db: Session = Depends(get_db),
    current_admin = Depends(get_current_admin_user)
):
    """Admin dashboard statistics"""
    
    total_users = db.query(User).count()
    total_products = db.query(Product).count()
    total_orders = db.query(Order).count()
    pending_orders = db.query(Order).filter(Order.status == "pending").count()
    
    return {
        "total_users": total_users,
        "total_products": total_products,
        "total_orders": total_orders,
        "pending_orders": pending_orders
    }

@router.get("/orders", response_model=List[schemas.OrderResponse])
async def admin_list_orders(
    skip: int = 0,
    limit: int = Query(default=50, le=100),
    status: str = None,
    db: Session = Depends(get_db),
    current_admin = Depends(get_current_admin_user)
):
    """List all orders (Admin)"""
    query = db.query(Order)
    
    if status:
        query = query.filter(Order.status == status)
    
    orders = query.offset(skip).limit(limit).all()
    return orders

@router.get("/users", response_model=List[schemas.UserResponse])
async def admin_list_users(
    skip: int = 0,
    limit: int = Query(default=50, le=100),
    db: Session = Depends(get_db),
    current_admin = Depends(get_current_admin_user)
):
    """List all users (Admin)"""
    users = crud.user.get_users(db, skip=skip, limit=limit)
    return users