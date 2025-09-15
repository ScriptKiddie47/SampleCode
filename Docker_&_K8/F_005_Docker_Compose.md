# Docker Compose

1. One config to rule them all. Lets talk about it.
1. When we use docker compose by default apps run in Attached mode & are removed when service is bought down.
1. Docker compose creates a default network for us. We can use a specific one if required.
1. `version` - Specify the Docker Compose version. This dictatest the docker compose features. This is deprecated.
1. Any named volumes used in services must be listed in volumes section separately.
1. Validate Docker Compose Plugin Installation

    ```ps
    syndicate@pop-os:~
    $ docker compose version
    Docker Compose version v2.38.2-desktop.1
    ```

1. Lets define a Simple Docker Compose with Mongo.

    ```yaml
    services:
    mognodb:
        image: 'mongo'
        volumes:
        - data:/data/db
        environment:
        MONGO_INITDB_ROOT_USERNAME: max
        MONGO_INITDB_ROOT_PASSWORD: secret
    volumes:
    data:
    ```

1. Lets run it

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker compose up
    [+] Running 1/1
    ✔ mognodb Pulled                                                                                                              5.9s 
    [+] Running 3/3
    ✔ Network compose-01-starting-setup_default      Created                                                                      0.0s 
    ✔ Volume "compose-01-starting-setup_data"        Created                                                                      0.0s 
    ✔ Container compose-01-starting-setup-mognodb-1  Created                                                                      0.2s 
    Attaching to mognodb-1
    mognodb-1  | about to fork child p.................
    ```

1. It creates a network -> `compose-01-starting-setup_default`.
1. Creates Volume -> `compose-01-starting-setup_data`. Its prefixed by project folder name + `data`.
1. Then starts listening to service. Mongo in our case.
1. Pressing `Ctrl+C` stops the container.
1. Start in detached mode.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker compose up -d
    [+] Running 1/1
    ✔ Container compose-01-starting-setup-mognodb-1  Started 
    ```

1. Delete all resources.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker compose down
    [+] Running 2/2
    ✔ Container compose-01-starting-setup-mognodb-1  Removed                                                                                                   0.3s 
    ✔ Network compose-01-starting-setup_default      Removed
    ```

1. It doesn't delete volume. If we want to do that we need `-v` flag. -> `$ docker compose down -v`

## Docker Compose Custom Image

1. Suppose `backend` is our node API. It has Dockerfile
1. Docker compose also replaced a build. Instead of specifying a built image we can use the compose to build the image.
1. `build` option.
1. Use `depends_on` to showcase service depenedency.

    ```yaml
    services:
      mongodb:
        image: 'mongo'
        volumes:
          - data:/data/db
        environment:
          MONGO_INITDB_ROOT_USERNAME: max
          MONGO_INITDB_ROOT_PASSWORD: secret
      backend: 
        build: ./backend
        ports:
          - '80:80'
        volumes:
          - logs:/app/logs
          - ./backend:/app # Relative path for Bind Mouth
          - /app/node_modules
        environment:
          MONGODB_USERNAME: max
          MONGODB_PASSWORD: secret
        depends_on: 
          - mongodb


    volumes:
      data:
      logs:
    ```

1. Once we run `$ docker compose up -d`. Below containers are created

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker ps -a
    CONTAINER ID   IMAGE                               COMMAND                  CREATED              STATUS              PORTS                                 NAMES
    08a73949f7f3   compose-01-starting-setup-backend   "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:80->80/tcp, [::]:80->80/tcp   compose-01-starting-setup-backend-1
    8c496d4c9a33   mongo                               "docker-entrypoint.s…"   About a minute ago   Up About a minute   27017/tcp                             compose-01-starting-setup-mongodb-1
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup

    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker logs compose-01-starting-setup-backend-1 

    > backend@1.0.0 start
    > nodemon app.js

    [nodemon] 2.0.22
    [nodemon] to restart at any time, enter `rs`
    [nodemon] watching path(s): *.*
    [nodemon] watching extensions: js,mjs,json
    [nodemon] starting `node app.js`
    CONNECTED TO MONGODB!!

    ```

1. We see the mongo container name `compose-01-starting-setup-mongodb-1`. The code is `mongodb://${process.env.MONGODB_USERNAME}:${process.env.MONGODB_PASSWORD}@mongodb:27017/course-goals?authSource=admin`.
1. So even though the container name is diff our backend container is able to connect to mongodb container with just `mongodb` because we used `mongodb` as service name in docker compose.

1. Now create add a react app to our compose. We can start it up in interractive mode using the below configs
1. `stdin_open: true` & `tty: true` 

```yaml
services:
  mongodb:
    image: 'mongo'
    volumes:
      - data:/data/db
    environment:
      MONGO_INITDB_ROOT_USERNAME: max
      MONGO_INITDB_ROOT_PASSWORD: secret
  backend: 
    build: ./backend
    ports:
      - '80:80'
    volumes:
      - logs:/app/logs
      - ./backend:/app # Relative path for Bind Mouth
      - /app/node_modules
    environment:
      MONGODB_USERNAME: max
      MONGODB_PASSWORD: secret
    depends_on: 
      - mongodb
  fontend:
    build : ./frontend
    ports:
      - '3000:3000'
    volumes:
      - ./frontend/src:/app/src
    stdin_open: true
    tty: true
    depends_on:
      - backend



volumes:
  data:
  logs:
```


## Docker Compose Important arguments

1. `docker compse up`
  - `--build ` -> Force docker Image is built before starting the container. Use for changes.
1. `docker compose build` -> Build missing images but not start container
1. Container name -> `container_name : mongodb`

```yaml
services:
  mongodb:
    image: 'mongo'
    container_name : mongodb
```

## Docker Run Individual Service in Docker Compose file

1. `docker compose run --rm mongodb`

or

```ps
syndicate@pop-os:~/Documents/CodeSource/php-project-docker
$ docker compose up -d server php mysql
```