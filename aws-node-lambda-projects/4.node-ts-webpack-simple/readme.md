## NPM Commands

npm i -D webpack\
npm install -D webpack-cli\
npm i -D ts-loader\
npm i -D typescript\
tsc --init\
npm install -D @types/aws-lambda\
npm install adm-zip


Adding the below code in output object in webpack config
```
library : {
            type : 'commonjs'
        }
```
Changes the whole game for aws lambda