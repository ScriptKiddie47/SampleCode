# Data & Volumes

### Types of Data
1. App Code + Dependencies
1. Temp Data
1. Permanent App Data

## Volumes

1. Persistent Data if even the container shutdown or gets removed.
1. Folders on Host Machine which are mounted ( made available or mapped )

### Add Volume to Container 

1. Docker File.
1. So if my app writes to a folder called `feedback` inside of my container & in the docker file has working dir `/app`
1. So path inside of my container -> `/app/feedback`.
1. So if we just add a line in our DockerFile.

    ```dockerfile
    WORKDIR /app
    VOLUME [ "/app/feedback" ]
    ```
1. Build Command

    ```ps
    $ docker build -t syndicate47/synimage:dv-01 .
    ```

1. So the above creates an anonymous volume managed by docker. So we don't know where

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker volume ls
    DRIVER    VOLUME NAME
    local     f3319865671d1231edc18eb23631ff5dc359733ebd58714f12c6943c62aa5b61
    ```

1. The name looks odd because it is a automatically assinged volume.
1. If we shut down the container. The volume disappears as well. So it exists as long our container exists.
### Named Volumes

1. We cannot create named volumes inside of a docker file. So remove the VOLUME line from Dockerfile.
1. Named volumes aren't deleted when container removed.
1. Named volumes aren't attached to a container

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker run -p 3000:80 -d --rm -v feedback:/app/feedback syndicate47/synimage:dv-01
    ```
1. Lets Explain `-v feedback:/app/feedback`
    - `-v` flag for volumes
    - `feedback` volume name managed by docker
    - `:` separator
    - `/app/feedback` data source inside container

    ```ps
    $ docker volume ls
    DRIVER    VOLUME NAME
    local     feedback
    ```

1. Even if we stop , restart or create new container with `-v feedback:/app/feedback` the app will have access to data. So we persisted the data.

## Bind Mounts

1. Many similarities with volume but we as developers set the path.
1. Usefull for Development. Container will have access to the latest code not just whats in the image.
1. Suppose your working Dir is -> `$ pwd
/home/syndicate/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup`

1. Also makes sure docker has access to the file system where our code resides using Docker Desktop File Sharing

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker run -p 3000:80 -d --rm -v "feedback:/app/feedback" -v "/home/syndicate/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup:/app" -v "/app/node_modules" syndicate47/synimage:dv-01
    0a87b381def3fffdd52bcb0cb052ce597697d8c648d2bf7702048c1a7d0cb1c3
    ```
1. Note -> `-v "/home/syndicate/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup:/app"`

1. The usage of Absolute path here defines the Bind mount connected to app folder of container.
1. One risk -> We overwrite everything in our app folder so if we ran `RUN npm install` the node modules folder will be overriden once we add the mount. We are good if we have a node_modules in our local.
    - Fix : We can create an anonymous volume and do something like `-v /app/node_modules`
    - For some wierd reason this is not touched by the bind mount. Excluded 

## Create volumes outside of Container Initialization

```ps
syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
$ docker volume create syn-volume
syn-volume
syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
$ docker volume ls
DRIVER    VOLUME NAME
local     feedback
local     syn-volume
```

## Docker Ignore

1. Pretty straightforward. Which files should not be copied

## Arguments and Environment Varibales

1. Docker Support Built time Arguments & Runtime environment variables
1. Node Js code : `app.listen(process.env.PORT); & console.log(app started at : ${process.env.PORT})`
1. DockerFile update

    ```dockerfile
    ENV PORT=80
    EXPOSE $PORT
    ```
1. This defines a env variables which has a default value of 80.

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker run -p 3000:8000 -d --rm --env PORT=8000 syndicate47/synimage:dv-01
    251c99b1c4d62e6021239a80b088ad81ddeb46ab3bf8e69054109698dcf78b5b
    $ docker ps -a
    CONTAINER ID   IMAGE                        COMMAND                  CREATED          STATUS          PORTS                                         NAMES
    251c99b1c4d6   syndicate47/synimage:dv-01   "docker-entrypoint.s…"   22 seconds ago   Up 21 seconds   0.0.0.0:3000->8000/tcp, [::]:3000->8000/tcp   brave_joliot
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker logs brave_joliot
    app started at : 8000
    ```
1. `--env PORT=8000` 
    - We can also do `-e`
    - If we have multiple we can add multiple `-e PORT=8000 -e SERVER_IP=htt...`
1. We can also add it through a file
1. Create a file called `.env` & add the below content

    ```env
    PORT=8000
    ```
1. Reference during container creation

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker run -p 3000:8000 -d --rm --env-file ./.env syndicate47/synimage:dv-01
    1f8713274c40252f7a47bcedf2c22c3731b4450c22b2c363de32147e1667f0af
    ```
1. Lets now use Arguments
1. Point 3 hardcodes the default values. Lets make it dynamic.
    - We cannont use it on CMD statements

    ```dockerfile
    ARG DEFAULT_PORT=80
    ENV PORT=$DEFAULT_PORT
    EXPOSE $PORT
    ```
1. Image Build

    ```ps
    syndicate@pop-os:~/Documents/CodeSource/node-js-projects/data-volumes-01-starting-setup
    $ docker build -t syndicate47/synimage:dv-01 --build-arg DEFAULT_PORT=8000 .
    ```
