import { generateTxt, generateTxtAsync } from '../src/util'

test('generate bio', () => {
    const expectedValue = 'Ritam is 27 years old';
    const text = generateTxt('Ritam', 27);
    expect(text).toBe(expectedValue);
});


test('generate bio', async () => {
    const expectedValue = 'Ritam is 27 years old';
    const text = await generateTxtAsync('Ritam', 27);
    expect(text).toBe(expectedValue);
});