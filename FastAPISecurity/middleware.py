import time
import logging
from fastapi import Request

logger = logging.getLogger(__name__)

async def log_requests_middleware(request: Request, call_next):
    """Request logging middleware"""
    start_time = time.time()
    
    logger.info(f"Request: {request.method} {request.url.path}")
    
    response = await call_next(request)
    
    process_time = time.time() - start_time
    logger.info(f"Response: {response.status_code} - {process_time:.4f}s")
    
    response.headers["X-Process-Time"] = str(process_time)
    
    return response

