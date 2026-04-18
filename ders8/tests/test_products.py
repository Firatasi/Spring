# tests/test_products.py
def test_list_products(client):
    response = client.get("/api/v1/products/")
    assert response.status_code == 200
    assert isinstance(response.json(), list)

def test_create_product_admin(client, auth_headers):
    response = client.post(
        "/api/v1/products/",
        json={
            "name": "Test Product",
            "slug": "test-product",
            "price": 99.99,
            "stock": 10,
            "category_id": 1
        },
        headers=auth_headers
    )
    assert response.status_code == 201
    assert response.json()["name"] == "Test Product"

# tests/test_cart.py
def test_add_to_cart(client, auth_headers):
    response = client.post(
        "/api/v1/cart/items",
        json={"product_id": 1, "quantity": 2},
        headers=auth_headers
    )
    assert response.status_code == 201

def test_get_cart(client, auth_headers):
    response = client.get("/api/v1/cart/", headers=auth_headers)
    assert response.status_code == 200
    assert "items" in response.json()
    assert "total" in response.json()