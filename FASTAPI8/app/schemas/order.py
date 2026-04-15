from pydantic import BaseModel
from typing import List
from decimal import Decimal
from datetime import datetime

from app.schemas.product import ProductResponse

class OrderItemBase(BaseModel):
    product_id: int
    quantity: int
    price: Decimal

class OrderItemResponse(OrderItemBase):
    id: int
    product: ProductResponse
    
    class Config:
        orm_mode = True

class OrderCreate(BaseModel):
    shipping_address: str

class OrderResponse(BaseModel):
    id: int
    total_amount: Decimal
    status: str
    payment_status: str
    shipping_address: str
    created_at: datetime
    items: List[OrderItemResponse]
    
    class Config:
        orm_mode = True


