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
