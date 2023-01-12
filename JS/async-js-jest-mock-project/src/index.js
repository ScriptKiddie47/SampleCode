import { generateTxt, generateTxtAsync } from "./util.js";
import { getUser } from './getUser.js'
import { runCallBack } from './jestfn.js'
import { returnFinalString } from './voidFunction.js';


const bio = generateTxt('Ritam', 27);
console.log(bio);

async function networkCalls() {
    const result = await getUser();
    // console.log(result)

    const textAsync = await generateTxtAsync('Bala', 45);
    console.log(textAsync);


    const asyncString = await returnFinalString('Syndicate');
    console.log(asyncString);
}

networkCalls();
runCallBack(5, (x) => console.log(x));


