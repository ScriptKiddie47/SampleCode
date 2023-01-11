import { readFile } from 'fs';
import { promisify } from 'util';
import { networkCall1 } from './apiCall.js';

import "dotenv/config.js";

const read = promisify(readFile)

var run = async () => {

    const data = await read('sample_text.txt');
    console.log("File Read is Completed");

    const userResponseJson = await networkCall1();
    console.log(userResponseJson);

}

run();
const port = process.env.PORT;
console.log(`Your port is ${port}`);