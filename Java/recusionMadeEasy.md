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

### Example 2 : This is the most Important:

Find nth Fibonacci number
Ex : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 
```
private static int fib(int n) {
  if(n < 2) return n;
    return fib(n-1) + fib(n-2);
}
```
In order to understand this example , its very important to understand the tree. ( Image from https://visualgo.net/en/recursion )

![image](https://github.com/ScriptKiddie47/SampleCode/assets/59485946/72521a26-a3cf-49f4-a624-90b75d3b894c)

### Example 3 : Binary Search
```
public static void main(String[] args) {
	int ar1[] = new int[] { 2, 5, 8, 12, 16, 23, 38, 56, 72, 91 };
	int index = binarySearch(ar1, 0, ar1.length - 1, 72);
	System.out.println(index);
}

private static int binarySearch(int[] ar1, int s, int e, int t) {
	if (s > e)
		return -1;

	int m = s + (e - s) / 2;
	if (ar1[m] == t)
		return m;

	if (t > ar1[m]) {
		return binarySearch(ar1, m + 1, e, t);
	} else {
		return binarySearch(ar1, s, m - 1, t);
	}

}
```
