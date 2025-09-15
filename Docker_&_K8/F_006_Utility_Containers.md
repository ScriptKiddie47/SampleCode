# Utility Containers


### Exec Command 

1. Run commands inside a running container : `docker exec brave_pasteur npm init -y`

    ```ls
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker run -it -d --rm node:current-alpine3.22 
    2f02e1a3c16413c233cdc4c6fc21b470f44b212503806315e4033e2633d0116e
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker ps -a
    CONTAINER ID   IMAGE                     COMMAND                  CREATED          STATUS          PORTS     NAMES
    2f02e1a3c164   node:current-alpine3.22   "docker-entrypoint.s…"   16 seconds ago   Up 15 seconds             brave_pasteur
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/compose-01-starting-setup
    $ docker exec brave_pasteur npm init -y
    Wrote to /package.json:

    {
      "name": "",
      "version": "1.0.0",
      "description": "",
      "main": "index.js",
      "directories": {
        "lib": "lib"
      },
      "scripts": {
        "test": "echo \"Error: no test specified\" && exit 1"
      },
      "keywords": [],
      "author": "",
      "license": "ISC",
      "type": "commonjs"
    }
    ```

1. If you want to attach yourself to the container while running the command. We can use `-it` with docker run : `docker exec -it brave_pasteur npm init`

### Entry Point

1. Append commands to the dockerfile - `ENTRYPOINT ["npm"]`

```dockerfile
FROM node:current-alpine3.22
WORKDIR /app
ENTRYPOINT ["npm"]
```

1. If entry point is not mentioned the default entrypoint will kick in.
1. In the above example we can run `docker run ... <docker-image-name> init` & it will begin the Dockerfile.
