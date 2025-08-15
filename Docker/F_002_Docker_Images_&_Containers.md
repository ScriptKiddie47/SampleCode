## Docker Run Interactive & Attached / Dettached Mode ( Different from SSH )

1. Start the container with a session ( REPL / SSH )

    ```ps
    syndicate@pop-os:~
    $ docker run -it node:current-alpine3.22
    Unable to find image 'node:current-alpine3.22' locally
    current-alpine3.22: Pulling from library/node
    Digest: sha256:e8e882c692a08878d55ec8ff6c5a4a71b3edca25eda0af4406e2a160d8a93cf2
    Status: Downloaded newer image for node:current-alpine3.22
    Welcome to Node.js v24.5.0.
    Type ".help" for more information.
    > 1+1
    2
    ```
1. `Ctrl+C` to exit the above terminal
1. Use `docker run --help` to get mode details.
1. As per the doc
    - `-i` -> interactive
    - `-t` -> tty
1. You are taken directly to the Node.js REPL (interactive prompt) inside the container because the default command set for this image is to launch the Node.js interpreter, not a shell (like sh or bash).
1. `docker run node:current-alpine3.22` starts in Attached mode & is default. Can be used to view logs.
1. Detcahed mode

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker run -p 3000:80 -d 1abf9c9be020
    0e7c422dbbe745114f242b055b9644df3c3447837b25532aafcd915a50392a66
    ```
1. `-d`.
1. Lets attach us to the running container

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $  docker attach stupefied_rubin
    ```
1. 

## Docker Create / Build Image

1. Docker Images are Layer Based.
1. All layers after changing layer is rebuilt.
1. `$ docker build .` -> `.` signifies the same path

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker build .
    [+] Building 6.2s (9/9) FINISHED                                                                                                                                                          docker:desktop-linux
    => [internal] load build definition from Dockerfile                                                                                                                                                      0.1s
    ...
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker images -a
    REPOSITORY   TAG                  IMAGE ID       CREATED              SIZE
    <none>       <none>               1abf9c9be020   About a minute ago   256MB
    ```

1. Custom names to Images


## Docker Container Ports

1. `$ docker run -p 3000:80 1abf9c9be020`
1. `3000` is our PC port & `80` refers to the container port exposed

## Docker Start & Stop Container

1. `docker stop reverent_banach`

    ```ps 
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker ps
    CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                     NAMES
    78cfaf9ab0d5   1abf9c9be020   "docker-entrypoint.s…"   6 minutes ago   Up 6 minutes   0.0.0.0:3000->80/tcp, [::]:3000->80/tcp   reverent_banach
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker stop reverent_banach
    reverent_banach
    ```

1. Docker Start Container

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker ps -a
    CONTAINER ID   IMAGE          COMMAND                  CREATED             STATUS                           PORTS     NAMES
    78cfaf9ab0d5   1abf9c9be020   "docker-entrypoint.s…"   About an hour ago   Exited (137) About an hour ago             reverent_banach
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker start reverent_banach
    reverent_banach
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker ps
    CONTAINER ID   IMAGE          COMMAND                  CREATED             STATUS              PORTS                                     NAMES
    78cfaf9ab0d5   1abf9c9be020   "docker-entrypoint.s…"   About an hour ago   Up About a minute   0.0.0.0:3000->80/tcp, [::]:3000->80/tcp   reverent_banach
     ```

1. Doesn't block command line
1. Docker Start container in Attached mode

    ```ps
    syndicate@pop-os:~
    $ docker start -a stupefied_rubin
    ```

1. Docker Start container in Attached and Interractive Mode

    ```ps
    syndicate@pop-os:~
    $ docker start -ai stupefied_rubin
    ```

## Fetch Logs from Container

1. `docker logs --help` is a great command. There is also follow mode just by adding `-f`

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker logs stupefied_rubin
    Cool
    ```
## Docker Delete / Stop Images & Containers

1. We cannot remove a running container so we need to stop it

    ```ps
    syndicate@pop-os:~
    $ docker ps
    CONTAINER ID   IMAGE          COMMAND                  CREATED      STATUS          PORTS                                     NAMES
    0e7c422dbbe7   1abf9c9be020   "docker-entrypoint.s…"   3 days ago   Up 12 seconds   0.0.0.0:3000->80/tcp, [::]:3000->80/tcp   stupefied_rubin
    syndicate@pop-os:~
    $ docker stop stupefied_rubin
    stupefied_rubin
    ```
1. Docker Remove Containers 

    ```ps
    $ docker rm stupefied_rubin
    stupefied_rubin
    ```

1. Docker Remove Images

    ```ps
    syndicate@pop-os:~
    $ docker images -a
    REPOSITORY   TAG                  IMAGE ID       CREATED       SIZE
    <none>       <none>               1abf9c9be020   3 days ago    256MB
    node         current-alpine3.22   e8e882c692a0   10 days ago   236MB
    syndicate@pop-os:~
    $ docker rmi 1abf9c9be020
    Deleted: sha256:1abf9c9be020b3450b5535bd547da8a226ad4d32b75c319b269f7e704ed336d3
    ```

1. Automatically Remove Stopped containers ( `--rm` flag)

    ```ps
    $ docker run -p 3000:80 -d --rm 9fb278bc6145
    64101a4453483fb81df40c4f7586e1ec2c0d0fae12bba82f3ef844f594ca5ace
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker ps
    CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                     NAMES
    64101a445348   9fb278bc6145   "docker-entrypoint.s…"   4 seconds ago   Up 4 seconds   0.0.0.0:3000->80/tcp, [::]:3000->80/tcp   confident_lamport
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker stop confident_lamport
    confident_lamport
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker ps -a
    CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
    ```

## Docker Images Inspect

1. We can get good number of details by running the `$docker inspect 9fb278bc6145` command.
1. Such as Full Images ID,Ports,Environment Variables,Entrypoint ( Default one),OS,etc

## Docker File Copy

1. Allows us to copy files/folder into & out of a running container.
1. Folder we want to copy - `/home/syndicate/Documents/CodeSource/node-js-projects/docker-file-cp`
1. Lets copy it inside docker file structure folder called `test`.


    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker cp /home/syndicate/Documents/CodeSource/node-js-projects/docker-file-cp/. compassionate_gagarin:/docker-cp-test
    Successfully copied 2.05kB to compassionate_gagarin:/docker-cp-test
    ```

1. `/.` -> We copied everything inside `docker-file-cp` to `compassionate_gagarin:/docker-cp-test`.

## Docker Container & Images Naming

1. Lets first start with a container. Letsd call it `goals-app`

    ```ps
    syndicate@pop-os:~
    $ docker images -a
    REPOSITORY   TAG                  IMAGE ID       CREATED       SIZE
    <none>       <none>               9fb278bc6145   4 days ago    256MB
    node         current-alpine3.22   e8e882c692a0   10 days ago   236MB
    syndicate@pop-os:~
    $ docker run -p 3000:80 -d --name goals-app 9fb278bc6145
    c48f662b38bd7e234baeb66a39560259f56494003c93f248e420acaca484255d
    syndicate@pop-os:~
    $ docker ps -a
    CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                     NAMES
    c48f662b38bd   9fb278bc6145   "docker-entrypoint.s…"   4 seconds ago   Up 3 seconds   0.0.0.0:3000->80/tcp, [::]:3000->80/tcp   goals-app
    ```

1. Lets do that with images. Names are called Tag.
1. Images tag consist of 2 things -> Repo + Tag
1. Just thing of Repo has high level & tag as more specific.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker build -t goals:latest .
    [+] Building 0.6s (9/9) 

    View build details: docker-desktop://dashboard/build/desktop-linux/desktop-linux/04xzmqcfoeixluwk7ru1o00n6
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker images -a
    REPOSITORY   TAG                  IMAGE ID       CREATED       SIZE
    goals        latest               0d9a61bcccaa   4 days ago    256MB
    ```
## Push Image to DockerHub

1. The authentication to DockerHub is fking nightmare so just follow the docs.
1. Create a repo in DockerHub called : `synimage`. My account name is `syndicate47`
1. So the command to push images to this new repo : `docker push syndicate47/synimage:tagname`
1. So the image name needs to be `syndicate47/synimage` & tag upto us. We can also retag images with docker `tag` command.
1. Lets do it : 

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/nodejs-app-starting-setup
    $ docker build -t syndicate47/synimage:goals-app-1.0.0 .
    $ docker images -a
    REPOSITORY             TAG                  IMAGE ID       CREATED       SIZE
    syndicate47/synimage   goals-app-1.0.0      450de440a313   6 days ago    256MB
    node                   current-alpine3.22   e8e882c692a0   12 days ago   236MB
    syndicate@pop-os:~
    $ docker push syndicate47/synimage:goals-app-1.0.0
    The push refers to repository [docker.io/syndicate47/synimage]
    e77b1be4ed4c: Layer already exists 
    a6fb8c31a650: Layer already exists 
    cb0e97c551ce: Layer already exists 
    cc6701eaa104: Pushed 
    3867e47dd573: Layer already exists 
    72a69f62283f: Already exists 
    c2b797974eb3: Layer already exists 
    9824c27679d3: Layer already exists 
    goals-app-1.0.0: digest: sha256:450de440a3132a79b1ec8651ee0714a197e974200bd7069820981f8e7a41e97b size: 856
    ```
1. Docker Pull download the image

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/GitHub/SampleCode/Docker
    $ docker pull syndicate47/synimage:goals-app-1.0.0
    goals-app-1.0.0: Pulling from syndicate47/synimage
    c2b797974eb3: Pull complete 
    a6fb8c31a650: Pull complete 
    3867e47dd573: Pull complete 
    Digest: sha256:450de440a3132a79b1ec8651ee0714a197e974200bd7069820981f8e7a41e97b
    Status: Downloaded newer image for syndicate47/synimage:goals-app-1.0.0
    docker.io/syndicate47/synimage:goals-app-1.0.0
    syndicate@pop-os:~/Documents/CodeSource/GitHub/SampleCode/Docker
    $ docker images -a
    REPOSITORY             TAG                  IMAGE ID       CREATED       SIZE
    syndicate47/synimage   goals-app-1.0.0      450de440a313   6 days ago    256MB
    node                   current-alpine3.22   e8e882c692a0   12 days ago   236MB
    $ docker run -p 3000:80 -d --rm syndicate47/synimage:goals-app-1.0.0
    ```