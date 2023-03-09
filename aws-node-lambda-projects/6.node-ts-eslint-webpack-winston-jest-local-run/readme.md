### EsLint

1. https://typescript-eslint.io/getting-started
2. https://eslint.org/docs/latest/rules/

## NPM INSTALLs

1. npm i eslint
2. npm i typescript
3. npm i -D @typescript-eslint/parser @typescript-eslint/eslint-plugin eslint typescript
4. npm i -D webpack
5. npm i -D webpack-cli
6. npm i -D ts-loader
7. npm i mongodb
8. npm i dotenv
9. npm i -D @types/aws-lambda
10. npm install winston
11. npm install --save-dev jest
12. npm install --save-dev babel-jest @babel/core @babel/preset-env
13. npm i --save-dev @types/jest
14. npm install --save-dev @babel/preset-typescript

## Jest Command After step 11

1. jest --init for initial File
2. jest --silent for disabling logs during jest test runner


## Extra notes

1. The app can be run locally using the jest test - `describe('This test actually runs the whole service locally', function () {`
2. Now the above test should be used as it leaks a bit as we are not explicitly closing the mongo db connection. In lambda environment the lambda service is doing this for us.



