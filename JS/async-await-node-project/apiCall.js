export const networkCall1 = async () => {
    try {
        const response = await fetch('https://eqres.in/api/users/2');
        const myJson = await response.json();
        console.log(myJson);
        return myJson;
    } catch (e) {
        console.log("Network Call Failed");
    }
}