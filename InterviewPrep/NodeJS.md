NodeJS -> Open Source,Cross Platfrom Runtime Environment, Single Threaded,Event Driver & Non Blocking Architecture
Internal Working -> All requests are placed in Event Queue,Event Loop -> Picks up request 1 by 1 from Queue. For non blocking request it quickly processes them. If request is blocking,sends request to review thread pool
Why NodeJS is Single Threaded - Only 1 main thread. Reduces complexity & avoid thread conflicts or deadlocks.

Destructure

# Var,Let,Const

```js
function foo() {
  var x = 10;
  if (true) {
    var x = 20;
    var y = 30;
  }
  console.log(y); // 30
  console.log(x); // 20 - Insance Scope 
}
foo();
```

# Arrow Function

```js
const foo = function (a, b) {
  return a + b;
};
console.log(foo(10, 10)); // 20
const add = (a, b) => a + b;
console.log(add(10, 10)); // 20
```

# Function Declaration Scope 

```js
func2(); // Good
function func2(){}
func1(); // Bad
const func1 = function(){}
```

# UseState

```jsx
function MoviesGrid() {
  const [count, setCounter] = useState(0);
  return (
    <div>
      <button onClick={() => setCounter(count + 1)}>Click me {count}</button>
    </div>
  );
}
export default MoviesGrid;
```