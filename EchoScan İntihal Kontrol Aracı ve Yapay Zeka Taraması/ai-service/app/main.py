from fastapi import FastAPI, HTTPException, Request
from app.db import get_connection
from app.model_service import EmbeddingModelService
import json
import traceback

app = FastAPI(title="EchoScan AI Service")

model_service = EmbeddingModelService()


def split_into_chunks(text: str) -> list[str]:
    raw_sentences = []
    current = ""

    for char in text:
        current += char
        if char in ".!?":
            sentence = current.strip()
            if sentence:
                raw_sentences.append(sentence)
            current = ""

    if current.strip():
        raw_sentences.append(current.strip())

    cleaned = [s.strip() for s in raw_sentences if len(s.split()) >= 4]

    if cleaned:
        return cleaned

    text = text.strip()
    return [text] if text else []


@app.get("/health")
def health():
    return {"status": "ok", "dimensions": 384}


@app.get("/debug/db-check")
def debug_db_check():
    conn = get_connection()
    cur = conn.cursor()

    try:
        cur.execute("SELECT current_database(), current_schema()")
        db_info = cur.fetchone()

        cur.execute("""
            SELECT table_schema, table_name
            FROM information_schema.tables
            WHERE table_name = 'text_chunks'
        """)
        tables = cur.fetchall()

        return {
            "current_database": db_info[0],
            "current_schema": db_info[1],
            "tables_found": tables
        }
    finally:
        cur.close()
        conn.close()


@app.post("/analyze-and-store")
async def analyze_and_store(request: Request):
    try:
        raw_body = await request.body()
        print("RAW ANALYZE BODY:", raw_body)

        if not raw_body:
            raise HTTPException(status_code=400, detail="Empty body")

        payload = json.loads(raw_body.decode("utf-8"))

        analysis_record_id = payload.get("analysisRecordId") or payload.get("analysis_record_id")
        text = payload.get("text", "")

        if not analysis_record_id:
            raise HTTPException(status_code=400, detail="analysisRecordId missing")

        if not text:
            raise HTTPException(status_code=400, detail="text missing")

        chunks = split_into_chunks(text)

        if not chunks:
            raise HTTPException(status_code=400, detail="No valid chunks")

        embeddings = model_service.encode(chunks)

        conn = get_connection()
        cur = conn.cursor()

        try:
            for chunk, embedding in zip(chunks, embeddings):
                cur.execute(
                    """
                    INSERT INTO echoscan.text_chunks (analysis_record_id, chunk_text, embedding)
                    VALUES (%s, %s, %s::vector)
                    """,
                    (analysis_record_id, chunk, str(embedding)),
                )

            conn.commit()

            return {
                "message": "stored",
                "chunk_count": len(chunks)
            }

        finally:
            cur.close()
            conn.close()

    except HTTPException:
        raise
    except Exception as e:
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/similarity/search")
async def similarity_search(request: Request):
    try:
        raw_body = await request.body()
        print("RAW SIMILARITY BODY:", raw_body)

        if not raw_body:
            raise HTTPException(status_code=400, detail="Empty body")

        payload = json.loads(raw_body.decode("utf-8"))

        query_text = payload.get("queryText") or payload.get("query_text")
        limit = payload.get("limit", 5)

        if not query_text:
            raise HTTPException(status_code=400, detail="queryText missing")

        query_embedding = model_service.encode([query_text])[0]

        conn = get_connection()
        cur = conn.cursor()

        try:
            cur.execute(
                """
                SELECT id,
                       analysis_record_id,
                       chunk_text,
                       1 - (embedding <=> %s::vector) AS similarity
                FROM echoscan.text_chunks
                ORDER BY embedding <=> %s::vector
                LIMIT %s
                """,
                (
                    str(query_embedding),
                    str(query_embedding),
                    limit,
                ),
            )

            rows = cur.fetchall()

            results = [
                {
                    "id": r[0],
                    "analysis_record_id": r[1],
                    "chunk_text": r[2],
                    "similarity": round(float(r[3]), 4)
                }
                for r in rows
            ]

            return {"results": results}

        finally:
            cur.close()
            conn.close()

    except HTTPException:
        raise
    except Exception as e:
        traceback.print_exc()
        raise HTTPException(status_code=500, detail=str(e))