const { fetchSecrets } = require("./simple");
exports.handler = async (event) => {
	let secrets = await fetchSecrets();
	console.log(secrets);
	return "success";
};