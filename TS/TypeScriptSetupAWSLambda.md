# TS AWS LAMBDA


### Tools

1. NODE,TS,AWS SDK,ESLINT,WEBPACK

### Setting up environment

1. https://docs.aws.amazon.com/lambda/latest/dg/lambda-typescript.html
1. Commands to Fetch the dependencies

```bash
$ npm init -y
$ npm i -D typescript @types/node
$ npm i -D @typescript-eslint/parser @typescript-eslint/eslint-plugin eslint
$ npm i -D webpack-cli ts-loader
$ npm i -D jest ts-jest @types/jest
$ npm i @types/aws-lambda
```

1. TS-Config Source : https://docs.aws.amazon.com/lambda/latest/dg/lambda-typescript.html
1. So by default `noEmit` is true so keep a note.
1. `tsconfig.json`

```json
{
  "compilerOptions": {
    "target": "es2020",
    "strict": true,
    "preserveConstEnums": true,
    "noEmit": false,
    "sourceMap": false,
    "module": "commonjs",
    "moduleResolution": "node",
    "esModuleInterop": true,
    "skipLibCheck": true,
    "forceConsistentCasingInFileNames": true,
    "isolatedModules": true,
    "outDir": "./dist",
    "allowJs": true
  },
  "exclude": ["node_modules"],
  "include": ["src/**/*", "**/*.test.ts"]
}

```

1. ESLINT - https://typescript-eslint.io/getting-started/legacy-eslint-setup
1. `.eslintrc.cjs`

```js
/* eslint-env node */
module.exports = {
  extends: ['eslint:recommended', 'plugin:@typescript-eslint/recommended'],
  parser: '@typescript-eslint/parser',
  plugins: ['@typescript-eslint'],
  root: true,
};
```

1. WEBPACK - https://webpack.js.org/guides/typescript/
1. `webpack.config.js`

```js
const path = require("path");
module.exports = {
  devtool: false,
  target: "node",
  mode: "development",
  entry: "./src/index.ts",
  module: {
    rules: [
      {
        test: /\.ts$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: [".ts", ".js"],
  },
  output: {
    clean: true,
    filename: "index.js",
    path: path.resolve(__dirname, "build"),
    library: {
      type: "commonjs", #This is a magical property
    },
  },
};

```

1. JEST - https://jestjs.io/docs/getting-started#via-ts-jest
1. Major note , since the above properties are designed for commonjs , we should modify the file extension in AWS lambda from `.mjs` to `.js`



## INVOKING A LAMBDA LOCALLY

1. `npm i -D ts-node` -> https://typestrong.org/ts-node/
1.  













