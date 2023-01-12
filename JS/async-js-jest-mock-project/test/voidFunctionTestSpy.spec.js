import * as asyncCalls from '../src/voidFunction.js';
import * as returnStringFile from '../src/returnString';

test("Test Actual", async () => {
    const returnStringMock = jest.spyOn(returnStringFile, "returnString");

    //Override the implementaion
    returnStringMock.mockResolvedValue('Bye Bye Async ');
    const testDataTxt = await asyncCalls.returnFinalString("Syndicate")
    expect(testDataTxt).toBe('Bye Bye Async Syndicate');


    // restore the original implementation
    returnStringMock.mockRestore();
    const testDataTxt3 = await asyncCalls.returnFinalString("Syndicate")
    expect(testDataTxt3).toBe('Hello Async Response Syndicate');

})