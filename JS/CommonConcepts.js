// Rest Parameters
// Destructuring Arrays
// Destructuring Objects
// Spread Syntax

console.log(typeof(1)); // number
console.log(typeof(true)); // boolean
console.log(typeof('Hello')); // string
console.log(typeof(function(){})); // function
console.log(typeof(null)); // object [ Controversy ]
console.log(typeof({})); // object
console.log(typeof(undefined)); //undefined
console.log(NaN); // number [ Controversy ]
console.log(typeof []); // Object

// Common type conversions

const stringValue = '45';
console.log(typeof (stringValue.toString())); //String
console.log( Number.parseInt('55.5BAC')); //55 
console.log( Number.parseFloat('55.55BAC')); //55.55 

//Equalto Operators
// ==         -> Type conversion
// ===        -> No Type conversion ( String quality Operator )

// Valid Objects and Logical Operators
const userSettings = null;
const defaultSettings = { name : 'Default'};
console.log( userSettings || defaultSettings); // { name : 'Default'}
const car = null;
console.log(car); // null
console.log(!car); // true

// IIFE's pattern --> Immediately Invoked Function Expression
// Immediately Invoked Function Expression [ IIFE ]
(function () { console.log('in function'); })(); // Will be invoked right away.

const app1 = (function () { console.log('in function : '); return {}; })(); // in function :
console.log(app1); // {}


// Closure using iffe pattern
// Any variable declared in the IFFE will persist along with any function. We just need to make sure, we return a reference to the function.
let app = (function(){
    let carId = 123;
    let getId = function(){
        return carId;
    };
    return {
        getId : getId
    };
})();
console.log(typeof(app.getId())); // number
console.log(typeof(app.getId)); // function

let app2 = function () {
    let carId = 123;
    return {
        carId: carId
    };
};
console.log(app2.carId); // undefined


// this keyword
let fn = function () { console.log(this === window); };
fn(); // true

let o = {
    carId : 123,
    getId : function(){
        return this.carId;
    }
};
console.log(o.getId());

// call function | Unique WTF 
let o = {
    carId: 123,
    getID: function () { return this.carId; }
};
let newCar = { carId: 456 };
console.log(o.getID()); // 123
console.log(o.getID.call(newCar)); // 456

// apply function | Unique WTF  - apply accepts an array of arguments

let o = {
    carId: 123,
    getID: function (prefix) { return prefix + this.carId; }
};
let newCar = { carId: 456 };
console.log(o.getID.apply(newCar,['ID : ']));


// bind function | Unique WTF  - creates a copy of an existing function and pass value of 'this'.
let o = {
    carId: 123,
    getID: function () { return this.carId; }
};
let newCar = { carId: 456 };
let newFn = o.getID.bind(newCar);
console.log(newFn()); // 456

// Arrow function do not have their own 'this' value , 'this' refers to the enclosing context
const data = {
    n1 : 20,
    getN : () => this.n1,
    getNFn : function(){
        return this.n1;
    }
};
console.log(data.getN()); // undefined
console.log(data.getNFn()); // 20

// Default Parameters and Interpolation
let trackCar = function (carId, city = 'NY') { console.log(`Tracking ${carId} in ${city}`); };
trackCar(123); // Tracking 123 in NY
trackCar(123, 'Chicago'); // Tracking 123 in Chicago

// Constructor Functions --> Came before classes, was the primary way to create objects earlier.
function Car(id) { this.carId = id; }
let car = new Car(123);
console.log(car.carId); // 123

function Car(id) { 
    this.carId = id; 
    this.start = function(){
        console.log('start ' + this.carId);
    };
}
let car = new Car(123);
car.start();

// Prototype | Unique WTF | WTF Just happened

function Car(id) { this.carId = id; }
Car.prototype.start = function () { console.log('start ' + this.carId); };
let car = new Car(123);
car.start();

// Expand Object using Prototype

String.prototype.hello = function (){ return this.toString() + ' Hello';};
console.log('foo'.hello());
// Any String in JavaScript has now access to the Function hello()


// JSON : Object to Json, Array to JSON,JSON to objects ( Parse )
let car = {
    id : 123,
    style : 'convertible'
};
console.log(JSON.stringify(car)); // {"id":123,"style":"convertible"}

let car = [{carId : 123},{carId : 456},{carId : 789}];
console.log(JSON.stringify(car)); // [{"carId":123},{"carId":456},{"carId":789}]

const jsonIn = `[{"carId":123},{"carId":456},{"carId":789}]`;
const carIds = JSON.parse(jsonIn);
console.log(carIds); 

// 0: {carId: 123}
// 1: {carId: 456}
// 2: {carId: 789}


// Basic Array Methods


let car = [{carId : 123 , style : 'convertible'},{carId : 456 , style : 'convertible'},{carId : 789 , style : 'sedan'}];

// Filter -> Returns an new Array
let converitble = car.filter(car => car.style === 'convertible');
console.log(converitble);

// 0: {carId: 123, style: 'convertible'}
// 1: {carId: 456, style: 'convertible'}


console.log(car.every(car => car.carId > 0)); // true ( Loops Through all the elements and returns a boolean if all the conditions match)
console.log(car.find(car => car.carId > 500)); // {carId: 789, style: 'sedan'} ( Finds and returns the first instance )


// JS Class 

class Car {
    constructor(id){
        this.id = id;
    }
    identify(suffix){
        return `Car Id : ${this.id} ${suffix}`;
    }
}
let car = new Car(123);
console.log(car.identify(`!!!`)); // Car Id : 123 !!!


// Inheritance 

class Vehicle {
    constructor(){
        this.type = 'car';
    }
    start(){
        return `Starting : ${this.type}`;
    }
}
class Car extends Vehicle{
    start(){
        return `in Car Start` + super.start();
    }
}
console.log(new Car().start()); // in Car StartStarting : car


// Modules

export class Car{
    constructor(id){
        this.id = id;
    }
}
import { Car } from '../models/car.js';
let car = new Car(123);
console.log(car.id); // 123

// window --> Global Object
// timers --> async

// Error Handling try/catch: 

try { let car = new Car(); }
catch (error) { console.log('error:' + error); }
console.log('Continue...');

// error:ReferenceError: Car is not defined
// index.js?15bb:10 Continue...

// Error Handling try/catch,finally:

try { let car = new Car(); }
catch (error) { console.log('error:' + error); }
finally{ console.log('this always executes');}
console.log('Continue...');


// error:ReferenceError: Car is not defined
// index.js?15bb:9 this always executes
// index.js?15bb:12 Continue...


// Developer Defined Errors

try { throw new Error(' my custom error'); }
catch (error) { console.log('error:' + error); }
finally{ console.log('this always executes');}
console.log('Continue...');


// The Promise object represents the eventual completion (or failure) of an asynchronous operation and its resulting value.
let promise = new Promise(
    (resolve,reject) => {
        setTimeout(resolve,100,'someValue');
    }
);
console.log(promise); // Promise{<pending>}[[Prototype]]: Promise[[PromiseState]]: "fulfilled"[[PromiseResult]]: "someValue"
// This function accepts two more parameters [ resolve & reject ]. This function will execute immediately once we create this promise.
// setTimeout sets up a timeout where the resolve function will be called after 100 ms with 1 argument 'someValue'.
// in order to get value from promise
promise.then(value => console.log('fulfilled : ' + value), error => console.log('rejected : ' + error)); //fulfilled : someValue


// Setup jQuery : 1. npm install jquery 2. Import
import $ from 'jquery';
$.get("https://reqres.in/api/users?page=2", data => console.log('data : ' + JSON.stringify(data)));
// data : {"page":2,"per_page":6,"total":12,"total_pages":2,"data":[{"id":7,"email":"michael.lawson@reqres.in","first_name":"Michael","last_name":"Lawson","avatar":.......
// In package.json we see : "jquery": "^3.6.0" now set as a dependency 

//We get a response , but this isn't the best way to use get as it returns a promise.
import $ from 'jquery';
let promise = $.get("https://reqres.in/api/users?page=2");
promise.then(data => console.log("Success :" + data), error => console.log("error :" + error));


// Forms 
// preventDefault() --> prevent the browser from submitting the form
// Access Form Fields() --> form.elements['user'];  form.elements['avatar-file'];
// Validate Form Data 

let form = document.getElementById('user-form');

form.addEventListener('submit', e => {
    let user = form.elements['user'];
    let userError = document.getElementById('user-error');
    userError.textContent = '';
    if (user.value.length < 4) {
        userError.textContent = 'Invald Entry';
        user.focus();
    }
    let avatarFile = form.elements['avatar-file'];
    console.log(user.value, avatarFile.value);
    e.preventDefault();
});


// If .length is used on a function , it will show the number of parameters