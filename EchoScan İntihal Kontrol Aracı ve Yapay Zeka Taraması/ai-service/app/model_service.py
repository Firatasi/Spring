import os
from sentence_transformers import SentenceTransformer


class EmbeddingModelService:
    def __init__(self):
        model_name = os.getenv(
            "EMBEDDING_MODEL",
            "sentence-transformers/all-MiniLM-L6-v2"
        )
        self.model = SentenceTransformer(model_name)

    def encode(self, texts: list[str]) -> list[list[float]]:
        embeddings = self.model.encode(
            texts,
            normalize_embeddings=True
        )
        return embeddings.tolist()

    def dimensions(self) -> int:
        vector = self.model.encode(["test"], normalize_embeddings=True)
        return len(vector[0])