const path = require('path')
const { library } = require('webpack')
module.exports = {
    entry : "./src/index.ts",
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.ts$/, // Work with anything that ends in .ts
                exclude: /node_modules/,
                use: {
                    loader: 'ts-loader'
                }
            }
        ]
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
    output: {
        clean : true, // Clean the output directory before emit.
        filename: 'index.js',
        path: path.resolve(__dirname, 'public'),  // Its an absolute path
        library : {
            type : 'commonjs'
        }
    },
    devtool: false,
    target : "node"
}