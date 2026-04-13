# test_main.py
from fastapi.testclient import TestClient
from main import app

client = TestClient(app)

def test_read_root():
    """Ana sayfa testi"""
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"message": "Hello World"}

def test_create_item():
    """Item oluşturma testi"""
    response = client.post(
        "/items",
        json={"name": "Test Item", "price": 10.99}
    )
    assert response.status_code == 201
    assert response.json()["name"] == "Test Item"

def test_get_nonexistent_item():
    """Olmayan item testi"""
    response = client.get("/items/999")
    assert response.status_code == 404