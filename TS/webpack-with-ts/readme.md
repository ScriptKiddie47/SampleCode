Project: 
WEBPACK WITH TS - https://www.youtube.com/playlist?list=PL4cUxeGkcC9hOkGbwzgYFmaxB0WiduYJC

	Install : 
		
		npm i webpack --save-dev
		npm i webpack-cli --save-dev
		npm i ts-loader --save-dev --> Compiles TS to JS for Webpack
		npm i typescript --save-dev
		
	Webpack Config file :
		webpack.config.js --> Export an object using node js module system, we use it because webpack is ultimately read by node js
		
		const path = require('path')
		module.exports = {
			entry : "./src/index.ts", // Entry point for webpack
			module : {
				rules : [
					{
						test: /\.ts$/, // File should end in .ts
						use:'ts-loader', //If the above test , passes the ts-loader takes the ts file and compiles into JS for us.
						include : [path.resolve(__dirname,'src')] //Specify where the typescript files will be
					}
				]
			},
			output : {
				filename:'bundle.js',
				path : path.resolve(__dirname,'public')  // Its an absolute path
			}
		
		}
		
	Package-json :
	
	"build" : "webpack"
	
-------------------------------------------------------------------------	
	
	Webpack Dev Server :
		npm i webpack-dev-server -D
		
	Package.json :
		 "dev-server" : "webpack-dev-server",
	
	output : {
        publicPath: 'public', // Tell the dev server where to pate the JS code
	
	https://www.youtube.com/watch?v=8AtOBFTunWY&list=PL4cUxeGkcC9hOkGbwzgYFmaxB0WiduYJC&index=4
	
-------------------------------------------------------------------------	

ES6 Modules

	File 1: export const formData = (form: HTMLFormElement) => {....
	File 2: import { formData } from "./forms";
	
	When we run the build command we get the error :  Can't resolve './forms'
	We solve the above issue using :
	
	resolve : {
		extensions : ['.ts','.js']
	},
	output: {
	
-------------------------------------------------------------------------	

Source Maps -- Creates a link between source file and output files

	module.exports = {
		devtool : "eval-source-map",
		
	Note : Opening the bundle.js doesn't showcase the error
	
------------------------------------------------------------------------