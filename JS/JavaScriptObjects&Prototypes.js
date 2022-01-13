// If new keyword is not used while using Constructor Functions to create objects, 'this' will actually alter the window object.
function Cat(){
    this.colorMe = 'White'
}
var cat = Cat();
console.log(window.colorMe); // White


// 4 Ways to Create class [1.Constructor Function][2.Object.create][3.Class][4.Oject Literal]  
const catLiteral = { name : 'Fluffy' , color : 'White'};
console.log(`catLiteral -> ${typeof(catLiteral)}`); // catLiteral -> object

function CatCons(name, color) {
    this.name = name
    this.colorMe = color
}
var catCons = new CatCons('Fluffy', 'Red');
console.log(`catCons -> ${typeof(catCons)}`); // catCons -> object
var catCreate = Object.create(Object.prototype,{
    name : {
        value : 'Fluffy',
        enumerable : true,
        writable : true,
        configurable : true
    },
    color : {
        value: 'White',
        enumerable : true,
        writable : true,
        configurable : true
    }
})
console.log(`catCreate -> ${typeof(catCreate)}`); // catCreate -> object
// The 3 values defined here : enumerable,writable,configurable are auto configured for others
class CatClass {
    constructor(name,color){
        this.name = name;
        this.color = color;
    }
}
var catClass = new CatClass('Fluffy', 'Red');
console.log(`catClass -> ${typeof(catClass)}`); // catClass -> object

// propertyName and for...in loop
const catLiteral = { name: { first: 'Fluffy', last: 'LaBeaif' }, color: 'White' };
for (let propertyName in catLiteral) {
    console.log(propertyName);
}

// Object.getOwnPropertyDescriptor(objectName,'propertyName') -> Lists down the Property
const catLiteral = { name : 'Fluffy' , color : 'White'};
console.log(Object.getOwnPropertyDescriptor(catLiteral,'name')); // {value: 'Fluffy', writable: true, enumerable: true, configurable: true}
// We can update the property Value of an object using : Object.defineProperty
Object.defineProperty(catLiteral,'name',{writable:false});
// writable --> Unable to modify values
// enumerable -- > loop over objects , if we set it as false we cannot loop over it. But we can still See/Read the property
// configurable --> Locks down the Object. Can't be deleted if set as false

// Getters , Setters
var catLiteral = { name: { first: 'Fluffy', last: 'LaBeaif' }, color: 'White' };

Object.defineProperty(catLiteral, 'fullName', {
    get: function () { return this.name.first + ` ` + this.name.last },
    set: function (value) {
        const nameSplit = value.split(' ')
        this.name.first = nameSplit[0]
        this.name.last = nameSplit[1]
    }
});
console.log(catLiteral.fullName); // Fluffy LaBeaif
catLiteral.fullName = "Muffin Top";
console.log(catLiteral.fullName); // Muffin Top

var catLiteral = {
    color: 'White',
    getter() { return this.color }, // Function
    get colors() { return this.color } // WTF is this get
};

// ----------------------------------------Prototypes------------------------------------------- 
// This it of as updating base source code & Everthing is a function. SO PROTOTYPE IS OBJECT THAT EXISTS IN EVERY FUNCTION IN JAVASCRIPT 
let foo = function(){};
console.log(` foo.prototype typeOf ${typeof(foo.prototype)}`); // object

console.log(`Array typeOf : ${typeof(Array)}`); // function
console.log(`Object typeOf : ${typeof(Array)}`); // function
console.log(`Array.prototype : ${typeof(Array.prototype)}`); // object

// Since Array.prototype is an Object we can update the base souce code and introduce an independent method
Object.defineProperty(Array.prototype, 'last', {
    get: function () { return this[this.length - 1] }
})
let arr = ['red', 'blue', 'green'];
console.log(arr.last); // green 