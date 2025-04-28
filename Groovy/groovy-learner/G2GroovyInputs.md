# Inputs

```groovy
println("Whats your name");
def fName = System.console().readLine();
println("Hello " + fName); // Hello Ritam
```

### Numbers as Inputs

```groovy
println("Enter a number: ");
def num1 = System.console().readLine().toDouble();
println("Enter another number: ");
def num2 = System.console().readLine().toDouble();
printf("%.2f + %.2f = %.2f\n",[num1,num2,(num1+num2)]);
```

Output

```ps
Enter a number: 
4.5
Enter another number: 
4.5
4.50 + 4.50 = 9.00
```