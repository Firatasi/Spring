from fastapi import FastAPI, Depends
from fastapi.middleware.cors import CORSMiddleware #frontend backend iletişim için
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm #token alma sistemi, login formu (username + password)
from auth import create_access_token, get_password_hash #yazdığın JWT ve hash fonksiyonları
from middleware import log_requests_middleware #custom middleware (log + süre ölçümü)

app = FastAPI(title="Blog API v3", version="3.0.0")

@app.get("/")
def root():
    return {"message": "API çalışıyor kanka"}

# CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"], #sadece bu frontend izinli
    allow_credentials=True, #cookie/token gönderilebilir
    allow_methods=["*"],
    allow_headers=["*"], #tüm HTTP method ve header’lara izin
)

# Custom middleware
app.middleware("http")(log_requests_middleware) #tüm requestler buradan geçer, request log, response log,süre ölçme kendi yazdıgımız logları tutmak için middleware sayfası

# OAuth2
oauth2_scheme = OAuth2PasswordBearer(tokenUrl="token") #frontend token gönderirken kullanılır, tokenUrl="token" → login endpoint

@app.post("/register")
async def register(username: str, password: str, email: str):
    """Kullanıcı kaydı"""
    hashed_password = get_password_hash(password)
    # Storage'a kaydet
    return {"message": "User registered successfully"}

@app.post("/login")
async def login(form_data: OAuth2PasswordRequestForm = Depends()):
    """Login endpoint"""
    # Kullanıcı kontrolü ve token oluşturma
    access_token = create_access_token({"sub": form_data.username})
    return {"access_token": access_token, "token_type": "bearer"}

@app.get("/posts")
async def get_posts(token: str = Depends(oauth2_scheme)):
    """Protected posts endpoint"""
    # Token decode ve posts getir
    return {"posts": []}