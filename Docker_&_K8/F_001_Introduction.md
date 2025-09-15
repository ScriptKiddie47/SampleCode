# Docker Made Easy

1. Docker is a container technology.

# Lets get started

1. Lets have try to a Node JS app hosted on port 3000.
1. Create a docker file
1. We need a base image. Get the alpine one from DockerHub -> `FROM node:current-alpine3.22`
1. Set a workdir or create one & navigate to it -> `WORKDIR /app`
1. So the if we do `$pwd` we will be inside the `.../app` dir.
1. Copy the `package.json` file to our work dir which is `app` -> `COPY package.json .`
1. Lets run a command inside the docker container to install the dependencies -> `RUN npm install`
1. Copy Rest of the code from local dir to container -> `COPY . .` or -> `COPY . /app`
    - `/app` -> absolute dir
1. Expose port to the outside world -> `EXPOSE 3000`
    - This is just for documentation purpose , doesn't do anything
1. Lets run the app inside the container -> `CMD ["node", "app.mjs"]`
    - The reason we do the above & not `RUN node app.mjs` because `RUN` is an instruction used to build / setting up images
    - `CMD` executes when a container starts
1. Quick look at the file DIR

    ```ls
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup$ tree
    .
    ├── app.mjs
    ├── Dockerfile
    ├── helpers.mjs
    └── package.json

    0 directories, 4 files
    ```
1. Docker File

    ```dockerfile
    FROM node:current-alpine3.22
    WORKDIR /app
    COPY package.json .
    RUN npm install
    COPY . .
    EXPOSE 3000
    CMD ["node", "app.mjs"]
    ```
1. Lets build the image.

    ```bash
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup$ docker build .
    [+] Building 16.4s (11/11) FINISHED                                                                                docker:desktop-linux
    .
    .
    => => transferring context: 1.11kB                                                                                                0.0s
    => [2/5] WORKDIR /app                                                                                                             0.2s
    => [3/5] COPY package.json .                                                                                                      0.1s
    => [4/5] RUN npm install                                                                                                          3.1s
    => [5/5] COPY . .                                                                                                                 0.1s
    => exporting to image                                                                                                             1.1s
    .
    => => unpacking to moby-dangling@sha256:c1908433d1e5c97e5306792f47e85e72f3712929c6c0bfd61a36e232b238f9b0                          0.3s

    View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/yc8yy3gdmob0yu2b4xndwi9pw

    ```

1. Lets check the image details

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup
    $ docker images -a
    REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
    <none>       <none>    c1908433d1e5   4 minutes ago   255MB
    ```

1. With the image ID. Lets create a container. We also need to mention the port of the our machine through which we can access the port of the container.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup
    $ docker run -p 3000:3000 c1908433d1e5
    ```

1. Test it 

    ```ps
    $ curl http://localhost:3000/
    <h2>Hi there!</h2>
    ```

1. Lets grab the container details

    ```ps
    $ docker ps -a
    CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                         NAMES
    4fa7c46f0075   c1908433d1e5   "docker-entrypoint.s…"   2 minutes ago   Up 2 minutes   0.0.0.0:3000->3000/tcp, [::]:3000->3000/tcp   hopeful_sutherland
    ```

1. Lets stop the container

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup
    $ docker stop hopeful_sutherland
    hopeful_sutherland
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/first-demo-starting-setup
    $ docker ps -a
    CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS                        PORTS     NAMES
    4fa7c46f0075   c1908433d1e5   "docker-entrypoint.s…"   4 minutes ago   Exited (137) 13 seconds ago             hopeful_sutherland
    ```

1. Observe the status as Exited.