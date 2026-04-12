from sqlalchemy import create_engine #👉 DB bağlantısını oluşturur
from sqlalchemy.ext.declarative import declarative_base #model (tablo) tanımlamak için base class
from sqlalchemy.orm import sessionmaker #model (tablo) tanımlamak için base class

# Database URL
# PostgreSQL
SQLALCHEMY_DATABASE_URL = "postgresql://user:password@localhost:5432/postgres"

# SQLite
# SQLALCHEMY_DATABASE_URL = "sqlite:///./blog.db"


# MySQL
# SQLALCHEMY_DATABASE_URL = "mysql://user:password@localhost/dbname"


# Engine oluştur
engine = create_engine(SQLALCHEMY_DATABASE_URL)


SessionLocal = sessionmaker(
    autocommit=False, #commit’i sen manuel yaparsın
    autoflush=False, #DB ile konuşan session üretir
    bind=engine #hangi DB’ye bağlanacağını söyler
)

# Base class for models
Base = declarative_base() #tüm modeller buradan türetilir


# Dependency: Database session
def get_db():
    """
    Database session dependency
    Yield ile her request için yeni session oluşturulur
    """
    db = SessionLocal() #DB bağlantısı açılır
    try:
        yield db
    finally:
        db.close() #bağlantıyı kapatır
