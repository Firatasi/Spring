from pydantic import BaseModel, ConfigDict, Field
from typing import List, Optional


class EmbedRequest(BaseModel):
    texts: List[str]


class EmbedResponse(BaseModel):
    embeddings: List[List[float]]
    dimensions: int


class AnalyzeAndStoreRequest(BaseModel):
    analysis_record_id: int = Field(alias="analysisRecordId")
    text: str

    model_config = ConfigDict(populate_by_name=True)


class SimilaritySearchRequest(BaseModel):
    query_text: str = Field(alias="queryText")
    limit: int = 5

    model_config = ConfigDict(populate_by_name=True)


class SimilarChunk(BaseModel):
    id: int
    analysis_record_id: Optional[int] = None
    chunk_text: str
    similarity: float


class SimilaritySearchResponse(BaseModel):
    results: List[SimilarChunk]