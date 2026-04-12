from pydantic import BaseModel, Field
from typing import Optional
from datetime import datetime

class PostBase(BaseModel):
    """TEMEL POST ALANLARI"""
    title: str = Field(..., min_length=1, max_length=200)
    content: str = Field(..., min_length = 1)
    published: bool = True

    class Config:
        schema_extra = {
            "example": {
                "title": "FastAPI ile Blog API Geliştirme",
                "content": "Bu yazıda FastAPI kullanarak nasıl blog API geliştirebileceğinizi öğreneceksiniz...",
                "published": True
            }
        }

class PostCreate(PostBase):
    """ POST OLUŞTURMAK İÇİN """
    pass

class PostUpdate(BaseModel):
    """POST GUNCELLEMEK ICIN"""
    """Post güncelleme için (tüm alanlar opsiyonel)"""
    title: Optional[str] = Field(None, min_length=1, max_length=200)
    content: Optional[str] = Field(None, min_length=1)
    published: Optional[bool] = None

class PostResponse(PostBase):
    """API response için"""
    id: int
    created_at: datetime
    updated_at: datetime
    
    class Config:
        orm_mode = True
