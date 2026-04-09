from fastapi import FastAPI
from typing import Dict
from typing import Optional



app = FastAPI()

@app.get("/items")
def get_items():
    """Tüm ürünleri listeler"""
    return [
        {"id": 1, "name": "Laptop", "price": 999.99},
        {"id": 2, "name": "Mouse", "price": 29.99},
        {"id": 3, "name": "Keyboard", "price": 79.99}
    ]

@app.get("/items/{item_id}")
def get_item(item_id: int):
    """Belirli bir ürünü getirir"""
    return {
        "id": item_id,
        "name": f"Item {item_id}",
        "price": 99.99
    }
    

@app.post("/items")
def create_item(name: str, price: float) -> Dict:
    """Yeni ürün oluşturur"""
    new_item = {
        "id": 4,  # Gerçek uygulamada database'den gelir
        "name": name,
        "price": price,
        "created_at": "2025-11-28T10:00:00"
    }
    return new_item

@app.put("/items/{item_id}")
def update_item(item_id: int, name: str, price: float, description: str):
    """Ürünü tamamen günceller"""
    updated_item = {
        "id": item_id,
        "name": name,
        "price": price,
        "description": description,
        "updated_at": "2025-11-28T10:30:00"
    }
    return updated_item



@app.patch("/items/{item_id}")
def partial_update_item(
    item_id: int,
    name: Optional[str] = None,
    price: Optional[float] = None
):
    """Ürünün belirli alanlarını günceller"""
    
    # Mevcut veri (gerçek uygulamada database'den gelir)
    current_item = {
        "id": item_id,
        "name": "Old Name",
        "price": 99.99,
        "description": "Old Description"
    }
    
    # Sadece gönderilen alanları güncelle
    if name is not None:
        current_item["name"] = name
    if price is not None:
        current_item["price"] = price
    
    current_item["updated_at"] = "2025-11-28T10:45:00"
    
    return current_item


@app.delete("/items/{item_id}")
def delete_item(item_id: int):
    """Ürünü siler"""
    return {
        "message": f"Item {item_id} deleted successfully",
        "deleted_at": "2025-11-28T11:00:00"
    }

@app.head("/items/{item_id}")
def check_item_exists(item_id: int):
    """Ürünün varlığını kontrol eder (sadece headers döner)"""
    # Response body döndürülmez, sadece headers
    return None


@app.options("/items")
def get_item_options():
    """Endpoint'in desteklediği metodları döndürür"""
    return {
        "allowed_methods": ["GET", "POST", "PUT", "PATCH", "DELETE"]
    }