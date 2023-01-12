import { default as axios } from 'axios'

export async function getUser() {
    let result;
    try {
        const response = await axios.get('https://reqres.in/api/users/2');
        result = response.data;
    } catch (error) {
        console.error(error);
    }
    return result;
}