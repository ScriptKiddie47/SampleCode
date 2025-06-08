# Fast API

### Instalation 

1. Install Below Dependencies

```ps
conda install conda-forge::fastapi
conda install conda-forge::uvicorn
```

### Define Paths

1. Get

    ```py
    from fastapi import FastAPI
    app = FastAPI()
    @app.get("/")
    def path():
        return {"Hello":"World"}
    ```

1. Run Python App in CLI

    ```ps
    $ fast-api:app --reload
    INFO:     Will watch for changes in these directories: ['/home/syndicate/Documents/CodeSource/GitHub/SampleCode/Python/fast-api-learner']
    INFO:     Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
    INFO:     Started reloader process [71008] using WatchFiles
    INFO:     Started server process [71018]
    INFO:     Waiting for application startup.
    INFO:     Application startup complete.
    ```
1. --reload tag makes the server auto refresh anything we make changes to the file.
1. Curl it 

    ```ps
    curl http://127.0.0.1:8000 
    {"Hello":"World"}
    ```

### Post with Query Params

1. Notice the Params

    ```py
    items = []
    @app.post("/items")
    def create_item(item:str):
        items.append(item)
        return items
    ```

    ```ps
    curl --request POST \
    --url 'http://127.0.0.1:8000/items?item=DogFood'
    ["DogFood"]
    curl --request POST \
    --url 'http://127.0.0.1:8000/items?item=Orange' 
    ["DogFood","Orange"]
    ```

### Get with Path Params

1. Path Params

    ```py
    @app.get("/items/{item_id}")
    def get_item(item_id:int):
        return items[item_id]

    ```

    ```ps
    curl --request GET \
    --url http://127.0.0.1:8000/items/0
    "DogFood"
    ```
1. With Exception 


    ```py
    from fastapi import FastAPI,HTTPException
    app = FastAPI()
    items = []
    @app.post("/items")
    def create_item(item:str):
        items.append(item)
        return items
    @app.get("/items/{item_id}")
    def get_item(item_id:int):
        if(item_id < len(items)):
            return items[item_id]
        else:
            raise HTTPException(status_code=404,detail=f"Item {item_id} not found")
    ```


    ```ps
    curl --request GET \
    --url http://127.0.0.1:8000/items/1
    {"detail":"Item 1 not found"}
    ```

### Notes

1. Future : https://fastapi.tiangolo.com/advanced/response-directly/#returning-a-custom-response
