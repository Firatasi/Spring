# main.py
from fastapi import FastAPI, Depends, HTTPException, status
from sqlalchemy.orm import Session
from typing import List, Optional

import crud, models, schemas
from database import engine, get_db


# Tabloları oluştur
models.Base.metadata.create_all(bind=engine)

app = FastAPI()

@app.middleware("http")
async def add_process_time_header(request: Request, call_next):
    """Request processing time middleware"""
    start_time = time.time()
    
    # Request işleniyor
    response = await call_next(request)
    
    # Processing time hesapla
    process_time = time.time() - start_time
    response.headers["X-Process-Time"] = str(process_time)
    
    return response

# ============ USER ENDPOINTS ============

@app.post("/users", response_model=schemas.UserResponse, status_code=status.HTTP_201_CREATED)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    """Yeni kullanıcı oluştur"""
    db_user = crud.get_user_by_email(db, email=user.email)
    if db_user:
        raise HTTPException(status_code=400, detail="Email already registered")
    
    password = "hashed_" + user.password  # Gerçek uygulamada hash kullan
    return crud.create_user(db=db, user=user, password=password)

@app.get("/users", response_model=List[schemas.UserResponse])
def read_users(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    """Kullanıcı listesi"""
    users = crud.get_users(db, skip=skip, limit=limit)
    return users

@app.get("/users/{user_id}", response_model=schemas.UserResponse)
def read_user(user_id: int, db: Session = Depends(get_db)):
    """Belirli bir kullanıcı"""
    db_user = crud.get_user(db, user_id=user_id)
    if db_user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return db_user

# ============ POST ENDPOINTS ============

@app.post("/posts", response_model=schemas.PostResponse, status_code=status.HTTP_201_CREATED)
def create_post(post: schemas.PostCreate, author_id: int, db: Session = Depends(get_db)):
    """Yeni post oluştur"""
    return crud.create_post(db=db, post=post, author_id=author_id)

@app.get("/posts", response_model=List[schemas.PostResponse])
def read_posts(
    skip: int = 0,
    limit: int = 100,
    author_id: Optional[int] = None,
    published_only: bool = False,
    db: Session = Depends(get_db)
):
    """Post listesi"""
    posts = crud.get_posts(
        db,
        skip=skip,
        limit=limit,
        author_id=author_id,
        published_only=published_only
    )
    return posts