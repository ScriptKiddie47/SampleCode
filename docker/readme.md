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
```
FROM node:20-alpine3.17
WORKDIR /app
COPY package.json /app # This ensures the layer is only triggerd if package details have been modified
RUN npm install
COPY . /app
EXPOSE 80
CMD ["node","server.js"]
```
6. 








