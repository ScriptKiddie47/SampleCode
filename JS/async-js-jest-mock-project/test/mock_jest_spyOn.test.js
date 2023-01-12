import * as app from "../src/app.js";
import * as math from "../src/math.js";


/*Here we simply “spy” calls to the math function,
 but leave the original implementation in place*/

test("calls math.add", () => {
    const addMock = jest.spyOn(math, "add");

    // calls the original implementation
    expect(app.doAdd(1, 2)).toEqual(3);

    // and the spy stores the calls to add
    expect(addMock).toHaveBeenCalledWith(1, 2);
});

/*In other cases, you may want to mock a function, 
but then restore the original implementation */

test("calls math.add", () => {
    const addMock = jest.spyOn(math, "add");

    // override the implementation
    addMock.mockImplementation(() => "mock");
    expect(app.doAdd(1, 2)).toEqual("mock");

    // restore the original implementation
    addMock.mockRestore();
    expect(app.doAdd(1, 2)).toEqual(3);
});