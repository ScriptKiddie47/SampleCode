const path = require("path");
module.exports = {
  devtool : "eval-source-map",
  mode: "development",
  entry: "./src/index.ts", // Entry point for webpack
  module: {
    rules: [
      {
        test: /\.ts$/, // File should end in .ts
        use: "ts-loader", //If the above test , passes the ts-loader takes the ts file and compiles into JS for us.
        include: [path.resolve(__dirname, "src")], //Specify where the typescript files will be
      },
    ],
  },
  resolve : {
    extensions : ['.ts','.js']
  },
  output: {
    publicPath: "public",
    filename: "bundle.js",
    path: path.resolve(__dirname, "public"), // Its an absolute path
  },
};
