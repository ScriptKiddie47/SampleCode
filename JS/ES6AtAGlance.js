//Training URL : https://app.pluralsight.com/library/courses/rapid-es6-training/table-of-contents
// ES6 Compatibility http://kangax.github.io/compat-table/es6/
// ES6 is moving away from global function
console.log(Number.parseInt === parseFloat); // true

// this & arrow key function , the context of this here in line 11 was the object 'data'
const data = {
    number: 123,
    process: function () { console.log(this.number) },
    process1: () => { console.log(this.number) }, 
    process2: function () { return () => console.log(this.number) } 
}
data.process(); // 123
data.process1(); // undefined
data.process2()(); // 123

//We cannot bind a new object to the arrow function. We cannot change the value of this with bind
// When we use arrow functions , call to bind,apply.. are useless
let invoice = {
    number: 123,
    getNumber: function () { return this.number; },
    process: function () { return () => this.number }
};
let newInvoice = { number: 456 };
console.log(invoice.getNumber.bind(newInvoice)()); // 456
console.log(invoice.process().bind(newInvoice)()); // 123

// Default Function parameters  : 
var getTotal = new Function("price = 20.00", "return price;");
console.log(getTotal()); // 20

// Dynamic Function : 

// The dynamic nature of JavaScript means that a function is able to not only call itself, but define itself, and even redefine itself. This is done by assigning an anonymous function to a variable that has the same name as the function
//  https://www.sitepoint.com/javascript-functions-that-define-and-rewrite-themselves/

// The "new Function" syntax
// There’s one more way to create a function. It’s rarely used, but sometimes there’s no alternative.
// Syntax
// The syntax for creating a function:
// let func = new Function ([arg1, arg2, ...argN], functionBody);
// The function is created with the arguments arg1...argN and the given functionBody.
// It’s easier to understand by looking at an example. Here’s a function with two arguments:
let sum = new Function('a', 'b', 'return a + b');
console.log(sum(5, 6)); // 11

// Object Literal Extension
const filed = 'dynamic filed'
const price = 11;
const productView = {
    [filed] : price
}
console.log(productView); // {dynamic filed: 11}


// forof loop 
const catergories = ['hardware', 'software', 'vaporware'];
const codes = "ABCDF";
for (const iterator of catergories) { console.log(iterator) }
for (const iterator of codes) { console.log(iterator) }

// Interpolation and Template Literals
let invoiceNum = '1350';
console.log(`Invoice Number : ${invoiceNum}`); // Invoice Number : 1350
console.log(`Invoice Number : \${invoiceNum}`); // Invoice Number : ${invoiceNum} Escaping the Dollar Sign ,no interpolation takes place
function processInvoice(segements) { console.log(segements) }
processInvoice`template`; // ['template', raw: Array(1)]

// Heavy Implementation of Template ( Advise Caution )
function processInvoice(segements, ...values) {
    console.log(segements);   // ['Invoice : ', ' for ', '', raw: Array(3)]
    console.log(values); // [1350, 2000]
}
const invoiceNumber = 1350;
const amount = 2000;
processInvoice`Invoice : ${invoiceNumber} for ${amount}`;

// Destructuring 
let salary = ['100', '200', '300'];
let [s1, , s3, s4, s5 = 900] = salary;
console.log(`s3 : ${s3} , s4 : ${s4} , s5 : ${s5}`); // s3 : 300 , s4 : undefined , s5 : 900

