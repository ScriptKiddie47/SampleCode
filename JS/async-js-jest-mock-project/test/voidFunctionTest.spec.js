import * as asyncCalls from '../src/voidFunction.js';
import * as returnStringFile from '../src/returnString';


describe("Test async Functions", () => {
    test("async return final String", async () => {
        returnStringFile.returnString = jest.fn().mockResolvedValue('Bye Bye Async ');
        const testDataTxt = await asyncCalls.returnFinalString("Syndicate")
        expect(testDataTxt).toBe('Bye Bye Async Syndicate');
    })

    test("Void Function", async () => {
        returnStringFile.returnString = jest.fn().mockResolvedValue();
        await asyncCalls.noReturnVoid("Syndicate")
        expect(returnStringFile.returnString).toHaveBeenCalled();
        expect(returnStringFile.returnString).toHaveBeenCalledTimes(1);
    })
})