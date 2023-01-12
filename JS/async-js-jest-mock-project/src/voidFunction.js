import { returnString } from './returnString.js'

export const returnFinalString = async (request) => {
    const resultString = await returnString();
    return resultString + request;
}

export const noReturnVoid = async (req) => {
    const resultString = await returnString();
}