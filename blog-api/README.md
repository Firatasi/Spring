# Blog Yönetim Sistemi – REST API

## 📌 Proje Hakkında

Bu proje, JWT tabanlı kimlik doğrulama ve rol bazlı yetkilendirme içeren, katmanlı mimariye sahip bir blog yönetim sistemi REST API uygulamasıdır.

Amaç; modern backend geliştirme prensiplerini, güvenli API tasarımını ve temiz mimari yaklaşımını pratik olarak göstermektir.

---

## 🛠 Kullanılan Teknolojiler

- Java 21
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Springdoc OpenAPI (Swagger)
- Maven

---

## 🏗 Mimari Yapı

Proje katmanlı mimari prensibine uygun olarak tasarlanmıştır:

- articles
- auth
- comments
- exception
- security
- users

Her domain aşağıdaki katmanlara ayrılmıştır:

- controller
- service
- repository
- dto
- entity

Security katmanı ayrı bir yapı olarak konumlandırılmıştır.

---

## 🗄 Veritabanı Yapısı

Temel varlıklar:

- User
- Article
- Comment

İlişkiler:

- Bir kullanıcı birçok makale yazabilir.
- Bir kullanıcı birçok yorum yapabilir.
- Bir makaleye birden fazla yorum yapılabilir.

---

## 🔐 Kimlik Doğrulama ve Yetkilendirme

Sistem stateless JWT mimarisi ile çalışmaktadır.

### Herkese Açık Endpointler

- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/articles`
- GET `/api/articles/{id}`

### Kimlik Doğrulama Gerektiren Endpointler

- Makale oluşturma
- Makale güncelleme
- Makale silme
- Yorum ekleme
- Yorum silme
- Profil görüntüleme
- Profil güncelleme

### Yetkilendirme Kuralları

- Kullanıcı yalnızca kendi makalesini güncelleyebilir veya silebilir.
- Kullanıcı yalnızca kendi yorumunu silebilir.
- ADMIN rolü tüm içerikler üzerinde işlem yapabilir.

---

## 🚀 API Özellikleri

### Auth
- Kullanıcı kayıt
- Kullanıcı giriş (JWT üretimi)

### Kullanıcı
- Profil görüntüleme
- Profil güncelleme

### Makale
- Listeleme (pagination + sorting)
- Detay görüntüleme
- Oluşturma
- Güncelleme
- Silme

### Yorum
- Makaleye ait yorumları listeleme
- Yorum ekleme
- Yorum silme

---

## 📄 API Dokümantasyonu

Swagger UI:

http://localhost:8080/swagger

OpenAPI JSON:

http://localhost:8080/api-docs

---

## ⚙️ Projeyi Çalıştırma

### 1. PostgreSQL veritabanı oluşturun

```sql
CREATE DATABASE blogdb;
