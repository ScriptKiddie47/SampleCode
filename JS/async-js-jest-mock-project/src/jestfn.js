export const runCallBack = (num, callback) => {
    const xNumber = num * 5;
    if (xNumber < 100) {
        callback(xNumber);
    }
}