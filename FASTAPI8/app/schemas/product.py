from pydantic import BaseModel, Field
from typing import Optional
from decimal import Decimal
from datetime import datetime

class CategoryBase(BaseModel):
    name: str
    slug: str
    description: Optional[str] = None

class CategoryCreate(CategoryBase):
    parent_id: Optional[int] = None

class CategoryResponse(CategoryBase):
    id: int
    created_at: datetime

class Config:
    orm_mode = True

class ProductBase(BaseModel):
    name: str
    slug: str
    description: Optional[str] = None
    price: Decimal = Field(..., gt = 0)
    stock: int = Field(default = 0, ge = 0)
    category_id: int
    is_active: bool = True

class ProductCreate(ProductBase):
    pass    

class ProductUpdate(BaseModel):
    name: Optional[str] = None
    description: Optional[str] = None
    price: Optional[Decimal] = Field(None, gt=0)
    stock: Optional[int] = Field(None, ge=0)
    category_id: Optional[int] = None
    is_active: Optional[bool] = None

class ProductResponse(ProductBase):
    id: int
    image_url: Optional[str] = None
    created_at: datetime
    category: CategoryResponse
    
    class Config:
        orm_mode = True