from pydantic import BaseModel, Field
from typing import List
from decimal import Decimal

from app.schemas.product import ProductResponse

class CartItemBase(BaseModel):
    product_id: int
    quantity: int = Field(..., ge=1)

class CartItemCreate(CartItemBase):
    pass

class CartItemUpdate(BaseModel):
    quantity: int = Field(..., ge=1)

class CartItemResponse(BaseModel):
    id: int
    product_id: int
    quantity: int
    product: ProductResponse
    subtotal: Decimal
    
    class Config:
        orm_mode = True

class CartResponse(BaseModel):
    items: List[CartItemResponse]
    total: Decimal