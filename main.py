from fastapi import FastAPI # FastAPI sınıfını import ediyoruz


app = FastAPI()# Uygulama instance'ı oluşturuyoruz


#root endpoint
@app.get("/")# HTTP GET metoduyla "/" yoluna istek geldiğinde
def read_root():
    """"Ana sayfa - Hosgeldiniz"""
    return {"message": "haho"}

@app.get("/info")
def get_info():
    """API bilgileri"""
    return {
        "app_name": "My First FastAPI",
        "version": "1.0.0",
        "description": "Adım adım FastAPI öğreniyorum"
    }

#şu anki zaman
@app.get("/time")
def get_time():
    """Şu anki server zamanı"""
    from datetime import datetime
    return {
        "current_time": datetime.now().isoformat(), #isoformat tarihi düzgün hale getirmek için kullanılır
        "timezone": "UTC"
    }

#parametreli endpoint
@app.get("/greet/{name}")
def greet(name: str):
    """Kişiselleştirilmiş selamlama"""
    return{"message": f"Merhaba, {name}!"}


@app.get("/users/{user_id}")
def get_user(user_id: int):
    """Kullanıcı bilgisi"""
    return {
        "user_id": user_id,
        "name": f"User {user_id}",
        "email": f"user{user_id}@example.com" #f"" String içine değişken gömmek"
    }


@app.get("items/{item_id}/details")
def get_item_details(item_id:int):
    """Ürün Detayları"""
    return {
        "item_id": item_id,
        "name": f"Item {item_id}",
        "price": item_id * 10.99,
        "in_stock": True
    }

#Query Parameters (Sorgu Parametreleri)
@app.get("/search")
def search_items(q:str = "", limit: int = 10):
    """Ürün Arama"""
    return {
        "query": q,
        "limit": limit,
        "results": [f"Item matching '{q}'" for i in range(limit)]
    }

@app.get("/filter")
def filter_items(
    min_price: float = 0.0,
    max_price: float = 1000.0,
    in_stock: bool = True
):
    """Urun Filtreleme"""
    return {
        "filters": {
            "min_price": min_price,
            "max_price": max_price,
            "in_stock": in_stock
        }
    }

# Senkron endpoint
@app.get("/sync")
def sync_endpoint():
    time.sleep(1)  # Blocking - diğer istekler bekler
    return {"message": "Sync response"}

# Asenkron endpoint (Önerilen!)
@app.get("/async")
async def async_endpoint():
    await asyncio.sleep(1)  # Non-blocking - diğer istekler devam eder
    return {"message": "Async response"}

# Harici API çağrısı
@app.get("/weather")
async def get_weather():
    async with httpx.AsyncClient() as client:
        response = await client.get("https://api.weather.com/...")
        return response.json()
    
#    Type Hints, Python'da değişkenlerin tipini belirtme yolu

#Type hints OLMADAN
def greet(name):
    return f"Merhaba, {name}"
    
#Type hints İLE
def greet(name:str) -> str:
    #      ↑ parametre tipi   ↑ dönüş tipi
    return f"Merhaba, {name}"

def get_user(user_id: int) -> dict:
    return {"id": user_id, "name": "Ahmet"}

user = get_user(123)
#user.  # ← Editor burada "name", "id" önerir


# Basit tipler
name: str = "Ahmet"
age: int = 25
price: float = 19.99
is_active: bool = True

# Liste, Dict, Tuple
names: list[str] = ["Ahmet", "Mehmet"]
user: dict[str, int] = {"age": 25, "score": 100}
coordinates: tuple[float, float] = (41.0, 29.0)

# Optional (None olabilir)
from typing import Optional
middle_name: Optional[str] = None  # str veya None

# Union (birden fazla tip)
from typing import Union
id: Union[int, str] = 123  # int veya str olabilir

# List of dicts
users: list[dict[str, any]] = [
    {"name": "Ahmet", "age": 25},
    {"name": "Mehmet", "age": 30}
]



from typing import Optional
from fastapi import FastAPI

app = FastAPI()
@app.get("/items/{item_id}")
def get_item(
    item_id: int,                     # Path parameter (zorunlu)
    q: Optional[str] = None,          # Query parameter (opsiyonel)
    limit: int = 10                   # Query parameter (default değer)
) -> dict:                            # Dönüş tipi
    return{
        "item_id": item_id,
        "query": q,
        "limit": limit
    }


# 3.4 Pydantic'e Giriş
# Pydantic Nedir?
# Pydantic = Data validation library (FastAPI'nin kalbi!)

from pydantic import BaseModel

# Pydantic Model
class User(BaseModel):
    id: str
    age: int
    email: str

# Kullanım
user = User(name = "Ahmet", age = 25, email = "ahmet@example.com")
print(user.name) # Ahmet
print(user.age) # 25

#Otamatik Validasyon
try:
    invalid_user = User(name = "firat", age = "yirmibes", email = "invalid-email")
except ValueError as e:
    print("Validation error:", e)





from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

# Request model
class Item(BaseModel):
    name: str
    price: float
    is_offer: bool = False

@app.post("/items")
def create_item(item: Item):
    # item otomatik validate edildi!
    return {
        "message": "Item created",
        "item": item
    }










#Basit Örnek

# main.py - Presentation Layer
from fastapi import FastAPI, Depends
from pydantic import BaseModel

app = FastAPI()

# Validation Layer - Pydantic Model
class User(BaseModel):
    name: str
    email: str
    age: int

# Business Logic - Service
class UserService:
    def create_user(self, user: User) -> dict:
        # İş kuralları burada
        if user.age < 18:
            raise ValueError("Yaş 18'den küçük olamaz")
        return {"id": 123, **user.dict()}

# Dependency
def get_user_service():
    return UserService()

# Path Operation
@app.post("/users")
def create_user(
    user: User,  # Validation
    service: UserService = Depends(get_user_service)  # DI
):
    # Business logic
    new_user = service.create_user(user)
    return new_user




#! 4. Pratik Görev: Hello API Projesi

from fastapi import FastAPI
from datetime import datetime

#uygulama oluştur
app = FastAPI(
    title = "Hello API",
    description = "Adım adım FastAPI öğreniyorum",
    version = "1.0.0"
)

#✅ Ana sayfa (welcome mesajı)
@app.get("/", tags=["Ana"])
def read_root():
    """Ana sayfa - Hosgeldiniz"""
    return {
    "message": "Hello, welcome to my api!",
    "documentation": "/docs",
    "endpoints": ["/info", "/time", "/random", "/greet/{name}", "/calc"]
    }

# 2. API bilgileri
@app.get("/info", tags=["Bilgi"])
def get_info():
    """API bilgileri"""
    return {
        "app_name": "Hello API",
        "version": "1.0.0",
        "description": "FastAPI öğrenim projesi",
        "author": "Your Name",
        "endpoints_count": 6
    }


# 3. Şu anki zaman
@app.get("/time", tags=["Bilgi"])
def get_time():
    """
    Şu anki server zamanı
    """
    now = datetime.now()
    return {
        "current_time": now.isoformat(),
        "date": now.strftime("%Y-%m-%d"),
        "time": now.strftime("%H:%M:%S"),
        "day_of_week": now.strftime("%A")
    }

# 4. Rastgele sayı
@app.get("/random", tags=["Eğlence"])
def get_random(min: int = 1, max: int = 100):
    """
    Rastgele sayı üret
    
    Args:
        min: Minimum değer (default: 1)
        max: Maksimum değer (default: 100)
    """
    number = random.randint(min, max)
    return {
        "number": number,
        "min": min,
        "max": max,
        "message": f"Rastgele sayı: {number}"
    }

# 5. Kişiselleştirilmiş selamlama
@app.get("/greet/{name}", tags=["Eğlence"])
def greet(name: str, lang: str = "tr"):
    """
    Kişiselleştirilmiş selamlama
    
    Args:
        name: İsim
        lang: Dil (tr, en, de) - default: tr
    """
    greetings = {
        "tr": f"Merhaba, {name}!",
        "en": f"Hello, {name}!",
        "de": f"Hallo, {name}!"
    }
    
    greeting = greetings.get(lang, greetings["tr"])
    
    return {
        "message": greeting,
        "name": name,
        "language": lang
    }

# 6. Basit hesap makinesi
@app.get("/calc", tags=["Araçlar"])
def calculate(
    a: float,
    b: float,
    operation: str = "add"
):
    """
    Basit hesap makinesi
    
    Args:
        a: İlk sayı
        b: İkinci sayı
        operation: İşlem (add, subtract, multiply, divide)
    """
    operations = {
        "add": a + b,
        "subtract": a - b,
        "multiply": a * b,
        "divide": a / b if b != 0 else "Error: Division by zero"
    }
    
    result = operations.get(operation, "Error: Invalid operation")
    
    return {
        "a": a,
        "b": b,
        "operation": operation,
        "result": result
    }

# Bonus: Health check
@app.get("/health", tags=["Sistem"])
def health_check():
    """
    Sistem sağlık kontrolü
    """
    return {
        "status": "healthy",
        "timestamp": datetime.now().isoformat()
    }