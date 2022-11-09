import { Invoice } from "./classes/Invoice.js";

//Instantiate the class


let values : [string,string,number]
values = ["mario", "website dev", 250]

const invOne = new Invoice(...values);
const invTwo = new Invoice("luigi", "backend dev", 300);

console.log(invOne, invTwo);

let invoices: Invoice[] = []; // Only Objects created with Invoice class can be added to the array
invoices.push(invOne);
invoices.push(invTwo);
