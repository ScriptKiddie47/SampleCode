from fastapi import FastAPI,HTTPException
from pydantic import BaseModel

app = FastAPI()

class UserInput(BaseModel):
    query: str

@app.get("/health")
def health():
    return "Up"

1
@app.post("/rulehelp")
def user_request(uInput:UserInput):
    return uInput




