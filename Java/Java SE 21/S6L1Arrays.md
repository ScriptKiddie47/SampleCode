# Arrays

1. Once an array is created, its length cannot be changed
1. An array of object references is fillied with `null` values.
1. An array of pimitives values is filled with 0 values( false if boolean)
1. Last valid index position is `array.length-1`


## Array Initialization 

1. Two ways

```java
int[] primes1 = new int[3]; // 0,0,0
int[] primes2 = new int[]{2,3,5}; // 2,3,5
int[] primes3 = {2,3,5}; // 2,3,5
```

## Multi Dimensional Array

1. It doesn't have to be square shape.
1. Fun

    ```java
    int[] primes23 = {2,3,5}; // 2,3,5
    int[][] matrix = new int[2][3]; // 2 Rows 3 Columns
    matrix[0][1] = 5;
    int[][] matrix2 = {{4,1},{2,0,5}};
    /**
    4 1
    2 0 5
    */
    ```

## Copy Array Content

1. `Arrays.copyof()`
1. There is also System.arraycopy but its little complex. Stick to array.copyof

```java
char[] a1 = {'a','c','m','e'};
char[] a2 = Arrays.copyOf(a1,a1.length);
```

# Arrays Class

1. Methods to handle array
1. Filling an array with values - `Arrays.fill()`
1. Searching through the array - `Array must be ordered also Arrays.binarySearch gives no guarantee about which index is returned when duplicates exist.`
1. Comparing contenet
1. Sorting ( Comparable / Comparator )


    ```java
    char[] a1 = new char[10];
    Arrays.fill(a1,'A');
    for (char c : a1) {
        System.out.print(c + ",");
    }
    int x = Arrays.binarySearch(a1, 'A');
    System.out.println(x); // 4 
    int[] a1 = new int[]{1,2,100,3};
    int[] a2 = new int[]{4,5,100,6};
    boolean isSame = Arrays.equals(a1, a2);
    System.out.println(isSame); // false
    }
    ```

## Complex For Loops

1. Syntactically we don't need a body

```java
int[] values = new int[]{1,2,3,4,5,6};
int sum = 0;
for(int i=0;i<values.length;sum+=i++);
System.out.println(sum);
```

1. 2D array - Say we want to print the diagonal elements

```java
int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
for (int i = 0, j = 2; !(i == 3 || j == -1); i++, j--) {
    System.out.println(matrix[i][j]); // 3 5 7
}
```

## Break and continue

1. `continue` operator skips the `current` loop cycle
1. `break` operator terminates the `current` loop