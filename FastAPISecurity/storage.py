from typing import List, Optional, Dict
from datetime import datetime
from models import PostCreate, PostUpdate

class PostStorage:
    """Geçici memory storage"""
    
    def __init__(self):
        self.posts: Dict[int, dict] = {}
        self.next_id: int = 1
    
    def create(self, post: PostCreate) -> dict:
        """Yeni post oluştur"""
        post_dict = post.dict()
        post_dict["id"] = self.next_id
        post_dict["created_at"] = datetime.now()
        post_dict["updated_at"] = datetime.now()
        
        self.posts[self.next_id] = post_dict
        self.next_id += 1
        
        return post_dict
    
    def get(self, post_id: int) -> Optional[dict]:
        """ID ile post getir"""
        return self.posts.get(post_id)
    
    def get_all(self, skip: int = 0, limit: int = 10) -> List[dict]:
        """Tüm postları getir (pagination)"""
        all_posts = list(self.posts.values())
        return all_posts[skip : skip + limit]
    
    def update(self, post_id: int, post_update: PostUpdate) -> Optional[dict]:
        """Post güncelle"""
        if post_id not in self.posts:
            return None
        
        stored_post = self.posts[post_id]
        
        # Sadece gönderilen alanları güncelle
        update_data = post_update.dict(exclude_unset=True)
        for field, value in update_data.items():
            stored_post[field] = value
        
        stored_post["updated_at"] = datetime.now()
        
        return stored_post
    
    def delete(self, post_id: int) -> bool:
        """Post sil"""
        if post_id in self.posts:
            del self.posts[post_id]
            return True
        return False
    
    def count(self) -> int:
        """Toplam post sayısı"""
        return len(self.posts)

# Global storage instance
storage = PostStorage()