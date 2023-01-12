test("returns undefined by default", () => {
    const mock = jest.fn(() => "LOL"); // This is a Function that returns "LOL" when called

    let result = mock("foo"); // Mock is invoked with a argument here

    expect(mock).toHaveBeenCalled();
    expect(mock).toHaveBeenCalledTimes(1);
    expect(mock).toHaveBeenCalledWith("foo");
});

test("mock implementation", () => {
    const mock = jest.fn(() => "bar");
    expect(mock()).toBe("bar"); // 'bar' returned as a response is being checked here
});

test("also mock implementation", () => {


    /* Accepts a function that should be used as the implementation of the mock. 
    The mock itself will still record all calls that go into and instances 
    that come from itself – the only difference is that the 
    implementation will also be executed when the mock is called.*/

    const mock = jest.fn().mockImplementation(() => "bar");
    expect(mock("foo")).toBe("bar");
    expect(mock).toHaveBeenCalledWith("foo");
    expect(mock("foo")).toBe("bar");
    expect(mock).toHaveBeenCalledWith("foo");
});

test("mock implementation one time", () => {


    /* Accepts a function that will be used as an implementation of the mock 
    for one call to the mocked function. 
    Can be chained so that multiple function calls produce different results. */

    const mock = jest.fn().mockImplementationOnce(() => "bar");

    expect(mock("foo")).toBe("bar");
    expect(mock).toHaveBeenCalledWith("foo");

    expect(mock("baz")).toBe(undefined); // To Understand 'undefined' read the above message
    expect(mock).toHaveBeenCalledWith("baz");
});

test("mock return value", () => {
    const mock = jest.fn();
    mock.mockReturnValue("bar");

    expect(mock("foo")).toBe("bar");
    expect(mock).toHaveBeenCalledWith("foo");
});

test("mock promise resolution", () => {

    // Useful to mock async functions in async tests:

    const mock = jest.fn();
    mock.mockResolvedValue("bar");

    expect(mock("foo")).resolves.toBe("bar");
    expect(mock).toHaveBeenCalledWith("foo");
});