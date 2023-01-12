import { returnString, returnStringWithException } from './returnString.js'

export const returnFinalString = async (request) => {
    const resultString = await returnString();
    return resultString + request;
}

export const noReturnVoid = async (req) => {
    const resultString = await returnString();
}

export const returnFinalStringWithError = async (request) => {
    let resultString;
    try {
        resultString = await returnString();
    } catch (e) {
        console.error('Inside Error Block', e)
        throw new TypeError('Incorrect Data');
    }
    return resultString + request;
}