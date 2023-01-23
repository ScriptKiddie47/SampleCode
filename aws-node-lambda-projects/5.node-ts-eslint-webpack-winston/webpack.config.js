// eslint-disable-next-line @typescript-eslint/no-var-requires
const path = require('path')
module.exports = {
    entry : "./src/index.ts",
    mode: 'production',
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