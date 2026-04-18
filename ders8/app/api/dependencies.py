# app/api/dependencies.py
from fastapi import Depends, HTTPException, status
from fastapi.security import OAuth2PasswordBearer
from sqlalchemy.orm import Session
from app.database import get_db
from app.core.security import decode_token
from app import crud
#KISA MANTIK: TOKEN AL, DECODE ET, USER BUL, AKTİF Mİ BAK, ADMİN Mİ BAK
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/api/v1/auth/login")

async def get_current_user( # kullanıcı var mı token doğru mu
    token: str = Depends(oauth2_scheme),
    db: Session = Depends(get_db)
):
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )
    
    payload = decode_token(token)
    if payload is None:
        raise credentials_exception
    
    user_id: int = payload.get("sub")
    if user_id is None:
        raise credentials_exception
    
    user = crud.user.get(db, id=user_id)
    if user is None:
        raise credentials_exception
    
    return user

async def get_current_active_user(
    current_user = Depends(get_current_user)
):
    if not current_user.is_active:
        raise HTTPException(status_code=400, detail="Inactive user")
    return current_user

async def get_current_admin_user(
    current_user = Depends(get_current_active_user)
):
    if not current_user.is_admin:
        raise HTTPException(
            status_code=403,
            detail="Not enough permissions"
        )
    return current_user