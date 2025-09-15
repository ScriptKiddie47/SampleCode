# Docker Networks

1. Connecting to outside network through HTTPS
1. Connecting to outside docker containers. We will explore things
1. Out of the box containers can send HTTPS request to WWW also set TCP connections to Atlas

## Setup 

1. App which calls another API & Mongo Atlas DB.

    ```js
    const express = require('express')
    const app = express()
    const port = 3000
    const axios = require('axios').default;
    const { MongoClient } = require("mongodb");
    const uri = "mongodb+srv://<username>:<password>@scriptkiddie-cluster.bc109kt.mongodb.net/?retryWrites=true&w=majority&appName=scriptkiddie-cluster";
    const client = new MongoClient(uri);


    app.get('/user', async (req, res) => {
    const userlist = await getUser();
    res.send(userlist);
    })

    app.get('/product', async (req, res) => {
    const product = await getProduct();
    res.send(product);
    })

    app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
    })

    async function getUser() {
    try {
        const response = await axios.get('https://jsonplaceholder.typicode.com/users');
        return response.data;
    } catch (error) {
        console.error(error);
    }
    }


    async function getProduct() {
    try {
        const database = client.db('product_list');
        const product_collection = database.collection('product');
        const query = { name: 'Iphone 13' };
        const product = await product_collection.findOne(query);
        return product;
    } finally {
        await client.close();
    }
    }
    ```

1. Simple Curls

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/external-caller
    $ curl localhost:3000/user
    {"page":2,"per_page":6,"total":12,"total_pages":2,"data":[{"id":7,"email":"michael.lawson@reqres.in","first_name"....
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/external-caller
    $ curl localhost:3000/product
    {"_id":"639dc186741cff3765fdeea1","name":"Iphone 13","description":"Latest & Greatest","price":"12000"}
    ```

1. Lets create a DockerFile

    ```dockerfile
    FROM node:current-alpine3.22
    WORKDIR /app
    COPY package.json .
    RUN npm install
    COPY . .
    EXPOSE 3000
    CMD ["npm", "start"]
    ```

1. Build , Deploy & test

    ```ps
    $ docker build -t syndicate47/synimage:external-caller-1.0.0 .
    $ docker run -p 3000:3000 --rm -d syndicate47/synimage:external-caller-1.0.0
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/external-caller
    $ curl localhost:3000/product
    {"_id":"639dc186741cff3765fdeea1"...
    $ curl localhost:3000/user
    [{"id":1,"name":"Le...
    ```

## Connection to `localhost`

1. If the docker server was running on our local machine instead of atlas we need to modify the connecting string. It would be something like 

    ```js
    const uri  = "mongodb://host.docker.internal:27017/<clustername>"
    ```

1. So `localhost` is `host.docker.internal`. That how docker understands the host machine.


## Container to Container Connection

1. Lets first setup a mongoDB

    ```ps
    $ docker run -d --name mongodb -p 27017:27107 --rm mongo
    ```

1. Run Docker Inspect on the container app & get the IPAddress

    ```ps
    $ docker inspect mongodb
    "IPAddress": "172.17.0.3",
    ```

1. Docker URL Update

    ```ps
    const uri  = "mongodb://172.17.0.3:27017/<clustername>"
    ```
1. Now we are able to talk to other container
1. But this is very bad. We had to look up IP Address of other container.
1. So lets look at the easier way.

## Container Networks

1. Docker Internal network that we can use for Docker Containers to talk to each other 
1. Lets create one

    ```ps
    $ docker network create external-connect-app-net
    ```

1. Inspect Exisitng Network + Default Networks

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/external-caller
    $  docker network ls
    NETWORK ID     NAME                       DRIVER    SCOPE
    31c45cc3ad33   bridge                     bridge    local
    2d6ec483fc8e   external-connect-app-net   bridge    local
    35539ae4655a   host                       host      local
    d65cce13655f   none                       null      local
    ```

1. Lets now run the mongoDB on Docker Network.

    ```ps
    syndicate@pop-os:~
    $ docker run -d --name mongodb -p 27017:27107 --network external-connect-app-net --rm mongo
    09da92e8b0a2bea013b4bca8e1684206cb560504e632c0a0e07513a9f06c01f7
    ```

1. Any other container can also be part of the network & can talk to each other. Question is How?
1. We can simple put the container name in the connection URL.

    ```js
    const uri  = "mongodb://mongodb:27017/<clustername>"
    ```

1. `mongodb` -> name here will be translated to container IP by docker. Lets update the code & rebuild & deploy.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/external-caller
    $ docker run -p 3000:3000 --rm --network external-connect-app-net -d syndicate47/synimage:external-caller-1.0.0
    30804d434b3092966d0659a0d1e16472fa04f04faa36ad7526a1c1eb564c44bc
    ```

1. Run some tests & we will see call hapenning to MongoDB.
