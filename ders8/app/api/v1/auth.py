# app/api/v1/auth.py
from fastapi import APIRouter, Depends, HTTPException, status
from fastapi.security import OAuth2PasswordRequestForm
from sqlalchemy.orm import Session
from datetime import timedelta

from app.api.dependencies import get_current_active_user
from app.database import get_db
from app import crud, schemas
from app.core.security import verify_password, create_access_token, get_password_hash
from app.core.config import settings

#Register → DB’ye kaydet, Login → token üret,Request → token ile kimlik doğrula
router = APIRouter()

@router.post("/register", response_model=schemas.UserResponse, status_code=status.HTTP_201_CREATED)
async def register(
    user: schemas.UserCreate,
    db: Session = Depends(get_db)
):
    """User registration"""
    
    # Check if user exists
    db_user = crud.user.get_by_email(db, email=user.email)
    if db_user:
        raise HTTPException(
            status_code=400,
            detail="Email already registered"
        )
    
    db_user = crud.user.get_by_username(db, username=user.username)
    if db_user:
        raise HTTPException(
            status_code=400,
            detail="Username already taken"
        )
    
    # Create user
    hashed_password = get_password_hash(user.password)
    new_user = crud.user.create(db, user=user, hashed_password=hashed_password)
    
    # Send welcome email (background task)
    # background_tasks.add_task(send_welcome_email, user.email)
    
    return new_user

@router.post("/login")
async def login(
    form_data: OAuth2PasswordRequestForm = Depends(),
    db: Session = Depends(get_db)
):
    """Login and get access token"""
    
    # Authenticate user
    user = crud.user.get_by_email(db, email=form_data.username)
    if not user or not verify_password(form_data.password, user.hashed_password):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Incorrect email or password",
            headers={"WWW-Authenticate": "Bearer"},
        )
    
    if not user.is_active:
        raise HTTPException(status_code=400, detail="Inactive user")
    
    # Create access token
    access_token_expires = timedelta(minutes=settings.ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = create_access_token(
        data={"sub": user.id},
        expires_delta=access_token_expires
    )
    
    return {
        "access_token": access_token,
        "token_type": "bearer"
    }

@router.get("/me", response_model=schemas.UserResponse)
async def read_users_me(
    current_user = Depends(get_current_active_user)
):
    """Get current user info"""
    return current_user

