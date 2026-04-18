# app/api/v1/products.py
from fastapi import APIRouter, Depends, HTTPException, status, Query
from sqlalchemy.orm import Session
from typing import List, Optional

from app.database import get_db
from app import crud, schemas
from app.api.dependencies import get_current_admin_user

router = APIRouter()

@router.get("/", response_model=List[schemas.ProductResponse])
async def list_products(
    skip: int = 0,
    limit: int = Query(default=20, le=100),
    category_id: Optional[int] = None,
    search: Optional[str] = None,
    min_price: Optional[float] = None,
    max_price: Optional[float] = None,
    in_stock_only: bool = False,
    db: Session = Depends(get_db)
):
    """List products with filters"""
    products = crud.product.get_products(
        db,
        skip=skip,
        limit=limit,
        category_id=category_id,
        search=search,
        min_price=min_price,
        max_price=max_price,
        in_stock_only=in_stock_only
    )
    return products

@router.get("/{product_id}", response_model=schemas.ProductResponse)
async def get_product(
    product_id: int,
    db: Session = Depends(get_db)
):
    """Get product by ID"""
    product = crud.product.get_product(db, product_id=product_id)
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return product

@router.post("/", response_model=schemas.ProductResponse, status_code=status.HTTP_201_CREATED)
async def create_product(
    product: schemas.ProductCreate,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_admin_user)
):
    """Create product (Admin only)"""
    
    # Check if slug exists
    existing = crud.product.get_product_by_slug(db, slug=product.slug)
    if existing:
        raise HTTPException(status_code=400, detail="Product slug already exists")
    
    new_product = crud.product.create_product(db, product=product)
    return new_product

@router.patch("/{product_id}", response_model=schemas.ProductResponse)
async def update_product(
    product_id: int,
    product_update: schemas.ProductUpdate,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_admin_user)
):
    """Update product (Admin only)"""
    updated_product = crud.product.update_product(db, product_id, product_update)
    if not updated_product:
        raise HTTPException(status_code=404, detail="Product not found")
    return updated_product

@router.delete("/{product_id}", status_code=status.HTTP_204_NO_CONTENT)
async def delete_product(
    product_id: int,
    db: Session = Depends(get_db),
    current_user = Depends(get_current_admin_user)
):
    """Delete product (Admin only)"""
    success = crud.product.delete_product(db, product_id)
    if not success:
        raise HTTPException(status_code=404, detail="Product not found")
    return None