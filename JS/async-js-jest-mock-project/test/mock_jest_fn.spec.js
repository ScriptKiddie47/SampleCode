/*The most basic strategy for mocking is to reassign a  function to the Mock Function. 
Then, anywhere the reassigned functions are used, 
the mock will be called instead of the original function:*/

import * as app from "../src/app.js";
import * as math from "../src/math.js";

math.add = jest.fn(() => 100);
math.subtract = jest.fn(() => -50);

test("calls math.add", () => {
  const result = app.doAdd(1, 2);
  expect(result).toBe(100);
  expect(math.add).toHaveBeenCalledWith(1, 2);
});

test("calls math.subtract", () => {
  const result = app.doSubtract(1, 2);
  expect(result).toBe(-50);
  expect(math.subtract).toHaveBeenCalledWith(1, 2);
});

// This type of mocking is less common for a couple reasons:

// jest.mock does this automatically for all functions in a module
// jest.spyOn does the same thing but allows restoring the original function
