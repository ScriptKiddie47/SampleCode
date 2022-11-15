# NODE-JS TS ESLINT PRETTIER JEST WEBPACK

### .nvmrc file -> Allow us to have consistent node version

### npm commands
npm i typescript -D   -> Mandatory for TS
npm i ts-node -D      -> Runtime that , lets us run ts files without transpile
npm i @types/node -D  -> Type definations for node js
npm i eslint -D       -> Setup ESLint
npm i prettier -D     -> Setup prettier
npm i eslint-plugin-prettier -D -> Help us setup diff eslint rules w.r.t prettier
npm i eslint-config-prettier -D -> Help us setup diff eslint rules w.r.t prettier

### TS-Config File
tsc --init  --> Initializes tsconfig.json

### ESLint
Run ESLint in Local -> npx eslint src
Download plugin from VSCode Extensions
Global installation is not required
npx eslint --init -> Setup the .eslintrc file ( use JS for the config file format )

Dependencies that will be installed
eslint-config-standard-with-typescript@latest @typescript-eslint/eslint-plugin@^5.0.0 eslint@^8.0.1 eslint-plugin-import@^2.25.2 eslint-plugin-n@^15.0.0 
eslint-plugin-promise@^6.0.0 typescript@*

.eslintignore --> Ignore node modules , dist , coverages etc
extends in file .eslintrc.js --> Inherits traits

### Prettier

Download Plugin from VSCode Extension
create file .prettierrc -> Review Options from  https://prettier.io/docs/en/options.html

### Webpack

Installs : npm i webpack --save-dev webpack-cli --save-dev ts-loader --save-dev typescript --save-dev
webpack.config.js --> Export an object using node js module system, we use it because webpack is ultimately read by node js

### Jest

Installs -> npm install --save-dev jest --save-dev ts-jest --save-dev @types/jest
Create config file -> npx ts-jest config:init ( jest.config.js )
Modify package.json and add under scripts "test": "jest",test:coverage": "jest --coverage"