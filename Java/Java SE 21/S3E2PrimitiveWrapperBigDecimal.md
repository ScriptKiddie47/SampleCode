# Wrapper Classes

1. Object Oriented Capabilities to primitives
1. Focus on `valueOf` & `parse`

```java
int a = 42;
Integer b = Integer.valueOf(a); // Construct Wrapper Object
int c = b.intValue(); // Extract Primitives value
String d = "12.22";
Float e = Float.valueOf(d); // Construct Wrapper Object
String g = "10";
int a = Integer.parseInt(g); // 10
Integer aInteger = Integer.valueOf(g); // 10
```
1. We can also do auto unboxing and boxing but avoid for performance.
1. Every wrapper class have `MIN` & `MAX` values. `System.out.println(Integer.MAX_VALUE);`

# Big Decimal

1. `java.math.BigDecimal` -> Great for handling decimal values that require exact precision. Immutable.

    ```java
    BigDecimal premium = BigDecimal.valueOf(100000);
    premium = premium.add(BigDecimal.valueOf(99999)); // 199999
    ```

1. Everytime we do a big decimal operation it produces a new Decimal Object hence the reassignment on `premium`.
1. 