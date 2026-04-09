from fastapi import FastAPI, HTTPException, status, Response
from typing import List
from models import PostCreate, PostUpdate, PostResponse
from storage import storage

app = FastAPI(
    title="Blog API",
    description="Simple blog API with CRUD operations",
    version="1.0.0"
)

@app.get("/", tags = ["Root"])
def read_root():
    """ANA SAYFA"""
    return {
        "message": "Welcome to Blog API",
        "version": "1.0.0",
        "endpoints": {
            "posts": "/posts",
            "docs": "/docs"
        }
    }


@app.post("/posts",response_mode = PostResponse, status_code=status.HTTP_201_CREATED,tags=["Posts"])
def create_post(post: PostCreate):
    """
    YENİ BLOG YAZISI OLUSTUR
    
    - **title**: Yazı başlığı (1-200 karakter)
    - **content**: Yazı içeriği (minimum 1 karakter)
    - **published**: Yayınlanma durumu (default: true)
    """
    new_post = storage.create(post)
    return new_post


@app.get(
    "/posts",
    response_model=List[PostResponse],
    tags=["Posts"]
)
def get_posts(
    skip: int = 0,
    limit: int = 10,
    response: Response = None
):
    """
    Blog yazılarını listele
    
    - **skip**: Atlanacak kayıt sayısı (pagination)
    - **limit**: Maksimum kayıt sayısı
    """
    posts = storage.get_all(skip=skip, limit=limit)
    
    # Custom header: toplam kayıt sayısı
    if response:
        response.headers["X-Total-Count"] = str(storage.count())
    
    return posts

@app.get(
    "/posts/{post_id}",
    response_model=PostResponse,
    tags=["Posts"]
)
def get_post(post_id: int):
    """
    Belirli bir blog yazısını getir
    
    - **post_id**: Yazı ID'si
    """
    post = storage.get(post_id)
    
    if not post:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Post with id {post_id} not found"
        )
    
    return post

@app.put(
    "/posts/{post_id}",
    response_model=PostResponse,
    tags=["Posts"]
)
def update_post(post_id: int, post_update: PostCreate):
    """
    Blog yazısını tamamen güncelle (PUT)
    
    Tüm alanlar gönderilmelidir.
    """
    # Önce varlığını kontrol et
    existing_post = storage.get(post_id)
    if not existing_post:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Post with id {post_id} not found"
        )
    
    # PostUpdate'e çevir ve güncelle
    post_update_model = PostUpdate(**post_update.dict())
    updated_post = storage.update(post_id, post_update_model)
    
    return updated_post


@app.patch(
    "/posts/{post_id}",
    response_model=PostResponse,
    tags=["Posts"]
)
def partial_update_post(post_id: int, post_update: PostUpdate):
    """
    Blog yazısını kısmen güncelle (PATCH)
    
    Sadece güncellenecek alanlar gönderilir.
    """
    updated_post = storage.update(post_id, post_update)
    
    if not updated_post:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Post with id {post_id} not found"
        )
    
    return updated_post

@app.delete(
    "/posts/{post_id}",
    status_code=status.HTTP_204_NO_CONTENT,
    tags=["Posts"]
)
def delete_post(post_id: int):
    """
    Blog yazısını sil
    
    - **post_id**: Silinecek yazı ID'si
    """
    deleted = storage.delete(post_id)
    
    if not deleted:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Post with id {post_id} not found"
        )
    
    return None

@app.get("/stats", tags=["Stats"])
def get_stats():
    """İstatistikler"""
    return {
        "total_posts": storage.count(),
        "published_posts": len([p for p in storage.posts.values() if p["published"]]),
        "draft_posts": len([p for p in storage.posts.values() if not p["published"]])
    }