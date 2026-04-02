from fastapi import FastAPI, Depends

app = FastAPI()



@app.get("/")
def read_root():
    return {"message": "API calisiyor"}

