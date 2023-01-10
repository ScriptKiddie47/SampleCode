const { simpleObject } = require("./simple");
exports.handler = async (event) => {
	console.log(simpleObject);
	return "success";
};