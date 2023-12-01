## Function Calling another Function
### Example 1:
```
printOneToFive(1);
private static void printOneToFive(int i) {
  if(i>5) return;
    System.out.print(i++); //12345
    printOneToFive(i);
}
```
Things to Note : there is no return value in this method & return statement directly exists the method.\
` if(i>5) return;` is the Base Condition

### Example 2 : This is the most Inmportant:

Find nth Fibonacci number
Ex : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 
```
private static int fib(int n) {
  if(n < 2) return n;
    return fib(n-1) + fib(n-2);
}
```
In ordeer to understand this example , its very important to understand the tree. ( Image from https://visualgo.net/en/recursion )

![image](https://github.com/ScriptKiddie47/SampleCode/assets/59485946/72521a26-a3cf-49f4-a624-90b75d3b894c)
