from sqlalchemy.orm import Session
from typing import List, Optional
import models, schemas

# ================ USER CRUD =================
def get_user(db: Session, user_id: int) -> Optional[models.User]:
    """Kullanıcı getir"""
    return db.query(models.User).filter(models.User.id == user_id).first()

def get_user_by_email(db: Session, email: str) -> Optional[models.User]:
    """Email ile kullanıcı getir"""
    return db.query(models.User).filter(models.User.email == email).first()

def get_users(db: Session, skip: int = 0, limit: int = 100) -> List[models.User]:
    """Kullanıcı listesi"""
    return db.query(models.User).offset(skip).limit(limit).all()

def create_user(db: Session, user: schemas.UserCreate, password: str) -> models.User:
    """Yeni kullanıcı oluştur"""
    db_user = models.User(
        username = user.username,
        email = user.email,
        password = password
    )
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

def update_user(db: Session, user_id: int, user_update: schemas.UserUpdate) -> Optional[models.User]:
    """Kullanıcı güncelle"""
    db_user = get_user(db, user_id)
    if not db_user:
        return None
    
    update_data = user_update.dict(exclude_unset=True)
    for field, value in update_data.items():
        setattr(db_user, field, value)
    
    db.commit()
    db.refresh(db_user)
    return db_user

def delete_user(db: Session, user_id: int) -> bool:
    """Kullanıcı sil"""
    db_user = get_user(db, user_id)
    if not db_user:
        return False
    
    db.delete(db_user)
    db.commit()
    return True


# ============ POST CRUD ============

def get_post(db: Session, post_id: int) -> Optional[models.Post]:
    """Post getir"""
    return db.query(models.Post).filter(models.Post.id == post_id).first()

def get_posts(
    db: Session,
    skip: int = 0,
    limit: int = 100,
    author_id: Optional[int] = None,
    published_only: bool = False
) -> List[models.Post]:
    """Post listesi (filtreleme ile)"""
    query = db.query(models.Post)
    
    if author_id:
        query = query.filter(models.Post.author_id == author_id)
    
    if published_only:
        query = query.filter(models.Post.published == True)
    
    return query.offset(skip).limit(limit).all()

def create_post(db: Session, post: schemas.PostCreate, author_id: int) -> models.Post:
    """Yeni post oluştur"""
    db_post = models.Post(**post.dict(), author_id=author_id)
    db.add(db_post)
    db.commit()
    db.refresh(db_post)
    return db_post

def update_post(db: Session, post_id: int, post_update: schemas.PostUpdate) -> Optional[models.Post]:
    """Post güncelle"""
    db_post = get_post(db, post_id)
    if not db_post:
        return None
    
    update_data = post_update.dict(exclude_unset=True)
    for field, value in update_data.items():
        setattr(db_post, field, value)
    
    db.commit()
    db.refresh(db_post)
    return db_post

def delete_post(db: Session, post_id: int) -> bool:
    """Post sil"""
    db_post = get_post(db, post_id)
    if not db_post:
        return False
    
    db.delete(db_post)
    db.commit()
    return True
