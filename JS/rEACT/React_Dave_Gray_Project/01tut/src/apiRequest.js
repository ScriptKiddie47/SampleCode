const apiRequest = async (url = '', optionsObj = null, errMsg = null) => {
    try {
        console.log(`url : ${url}, optionsObj : ${JSON.stringify(optionsObj)}`);
        const response = await fetch(url, optionsObj);
        if (!response.ok) throw new Error('Please reload app');
    } catch (err) {
        errMsg = err.message;
    } finally {
        return errMsg;
    }
}
export default apiRequest;