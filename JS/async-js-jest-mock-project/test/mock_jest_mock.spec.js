import * as app from "../src/app.js";
import * as math from "../src/math.js";

// Set all module functions to jest.fn()

jest.mock('../src/math.js');

test('calls math.add', () => {
    app.doAdd(1, 2);
    expect(math.add).toHaveBeenCalledWith(1, 2)
})