# Provide base image
FROM node:20-alpine3.17
#Set a Working directory , else everything will be in the root folder
WORKDIR /app
# Provide file that will be copied to the image
# 1st dot - All folder/files will be copied to the image
# 2nd dot - Where the files will be copied 'app' folder set in WORKDIR
COPY . /app
#Run shell command
#Note : We should run the below command in the app folder, we make sure of this using WORKDIR
RUN npm install
#Expose PORT , this is a documentation , no practical application ( doubt )
EXPOSE 80
#Run a command when the container is started based on the image
CMD ["node","server.js"]
