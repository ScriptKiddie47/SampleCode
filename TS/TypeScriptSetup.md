#  Typescript

## Setup 

1. Best to install globally via NPM for a new PC -> `npm install -g typescript`

## Typescript Config File
 
1. `tsc --init` -> Creates a config file
1. Don't be alarmed by the huge array of options , lets discuss few imp ones
1. `"target": "es2016"` -> Set the JavaScript language version
1. `"rootDir": "./"` ->  Specify the root folder within your source files ( TS files ) . Generally -> `"rootDir": "./src"`
1. `"outDir": "./"` -> Specify an output folder for all emitted files ( JS files ). Generally -> `""outDir": "./dist"`
1. `"noEmitOnError": true` -> Disable emitting files if any type checking errors are reported
1. Once we have config file setup, we can just do `tsc` and the tsc will get compiled

## Debug Typescript

1. Navigate to tsconfig file and enable -> `"sourceMap": true`
1. Create the launch.json ( we can auto-genarate )
1. We need to add one attribute to the launch.json

```js
{
    "version": "0.2.0",
    "configurations": [
        {
            .
            "program": "${workspaceFolder}/src/index.ts",
            "preLaunchTask": "tsc: build - tsconfig.json", // Telling VSCODE to use ts compiler config file
            .
        }
    ]
}
```
## Import and Exporting local files

1. ES6 Syntax
1. File 1 -

```js
import { sum } from "./sum"
console.log(sum(5,5));
```

1. File 2 - 

```js
export function sum(a: number, b: number): number {
  return a + b;
}
```


## NODE JS SETUP

1. In package.json , add `"type": "module"` so that we can use ES Modules & add a build script.
1. Note -> "type": "module" has a lot of issues with bundler , better to stick with commonJS which is by default.

```
.
"type": "module",
"scripts": {
   "build": "tsc",
   "start": "npm run build && node dist/index.js ",
.
.
```


1. Dependencies

```bash
npm i -D typescript
npm i -D @types/node
```

1. TS Config

```json
{
  "compilerOptions": {
    "module": "NodeNext", #This is kinda unique , it works well with types modules
    "moduleResolution": "NodeNext", 
    "target": "ES2020", #JS Flavor
    "sourceMap": true,
    "outDir": "dist"
  },
  "include": ["src/**/*"]
}
```

1. Run the command `npm run build`. Voila
1. Note : When importing local files utilize the `.js` extention , this is because of `nodenext`. Or Wait use `esModuleInterop` I guess

## JSLINT

1. https://typescript-eslint.io/getting-started/legacy-eslint-setup
1. File name : `.eslintrc`

```js
{
  "root": true,
  "parser": "@typescript-eslint/parser",
  "plugins": [
    "@typescript-eslint"
  ],
  "extends": [
    "eslint:recommended",
    "plugin:@typescript-eslint/eslint-recommended",
    "plugin:@typescript-eslint/recommended"
  ]
}
```
1. Package.json -> `"lint": "eslint . --ext .ts",`


## WEBPACK 

1. `npm install -D webpack-cli`
1. https://webpack.js.org/guides/typescript/
1. webpack.config.js file for commonJS 

```js
const path = require("path");

module.exports = {
  target: "node", #This really solves a lot of issues
  mode: "development",
  entry: "./src/index.ts",
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"]
  },
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, "dist"),
  },
};

```
## JEST TESTING LIB

1. https://jestjs.io/docs/getting-started
1. npm install --save-dev jest
1. npm install --save-dev ts-jest
1. npm install --save-dev @types/jest
1. Package JSON - `"test": "jest"`
1. Create a config file with the command -> `npx ts-jest config:init`

```
/** @type {import('ts-jest').JestConfigWithTsJest} */
module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
};
```



