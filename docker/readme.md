# Docker Basic Terms
1. Docker - Tool for creating and managing containers
2. Container - Running unit of Software  ( Runs Images )
3. Docker Enginer - 
4. Docker Hub - 
5. Docker Compose - 
6. Kubernates - 
7. Images - code + dependencies
8. 

## Docker Images

1. Use predefined images -dockerhub
2. Images are readonly
3. When we build an image docker cache build , so rebuild happens very quick.
4. When one layer is modified , below layers are also fired
5. We can do some simple optimization to speed up the build
6. Remember to use .dockerignore

```
FROM node:20-alpine3.17
WORKDIR /app
COPY package.json /app # This ensures the layer is only triggerd if package details have been modified
RUN npm install
COPY . /app
EXPOSE 80
CMD ["node","server.js"]
```
6. We can only remove images for which containers are removed as well.
7. docker run first checks your local , if it doesn't find an image it will pull. But it won't check for latest image on the docker hub
8. 

## Two types of Data Externalize
### Volumes
1. Permament App Data
2. Volumes are folder in our host machine whihc are mounted into containers.
3. Volume persists if a container shuts down.
4. Survives container removal & shutdown
5. Two type - named & anonymous volumes
### Bind Mounts









