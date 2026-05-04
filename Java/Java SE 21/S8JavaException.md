# Java Logging API

1. `java.util.logging.*`

```java
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleScript {
    private static Logger LOG = Logger.getLogger(SingleScript.class.getName());
    public static void main(String[] args) { 
        LOG.info("Hello"); // Apr 15, 2026 12:45:16 PM SingleScript main INFO: Hello
        LOG.log(Level.INFO, "There"); // Apr 15, 2026 12:45:16 PM SingleScript main INFO: There
    }
}
```
## Guarded Logging

1. Set Log Level at the config level

## Log Writing Handling

1. We can write log message to a log destination - `Console`,`File`,`Memory`,`Socket`,`Stream`.
1. Handlers use formatters to format the record - `SimpleFormatter`,`XMLFormatter`
1. The above can be achieved using a `logging.properties` file.

# Java Exception

1. Exception is an unexpected event that occurs in the program.
1. Exception interrupt normal execution flow.
1. All exceptions decend from the class `Throwable`
1. Types
    1. `Checked` - Must be caught, must be explicity propagated - `Exception,SQLException,IOExcetion`
    1. `Unchecked (Runtime)` - May be caught , do not have to be explicity propagated - `RuntimeException,NullPonterException,AirthmeticExcpetion`

## Create Custom Exception

1. Must extend class `Exception` or another more specific descendant of `Throwable`
1. May provide constructors that utilize superclass constructor abilities such as :
    1. Provide an error message
    1. Wrap another exception indicating a cause of this exception.

    ```java
    class ProductException extends Exception{
        public ProductException(){
            super();
        }
        public ProductException(String message){
            super(message);
        }
        public ProductException(String message,Throwable throwable){
            super(message, throwable);
        }
    }
    ```
## Throwing Exception 

1. `Instantiate Excpetion of the required type` using any availabe constructors
1. Use `throw` operator to interrupt the flow and trigger the exception propagation process.
1. When an exception is raised
    1. Normal program flow is terminated.
    1. Control is passed to the nearest available exception handler(covered next)
1. If exception handler is not available within this mothod.
    1. Unchecked exceptions are automatically propagrated to the invoker.
    1. Checked exceptions must be explicitly listed within the `throws` clause.


```java
class ProductException {
    public void doThings(int v) throws IOException {
        if (v == 10) {
            throw new IOException();
        }
        throw new NullPointerException();
    }
}
```

1. I don't need to add `NullPointerException` to the `throws` as NullPointerException extends RuntimeException, so it gets a free pass.
1. Only `checked` are needed to be mentioned in the `throws` clause. Unchecked exceptions are automatically propagated to the called method.

```java
class ProductException {
    public void doThings(int v) throws IOException{
        if (v == 10) {
            throw new IOException();
        }
        throw new FileNotFoundException();
    }
}
```
1. In the above example - I don't need to throw `FileNotFoundException` because of it being a subclass of `IOException`

## Catching Exception

1. Surround code that can produce exceptions with the `try` block.
1. Place one or more `catch` blocks or `finally` block or both after that `try` block.
1. Specific exception handler (catching exception subtypes) must be placed before generic handlers.
1. Unchecked(runtime excpetion) handlers are optional.
1. For `finally` actions will be executed regardless if exceptions occur or not.

1. Without try catch

```java
public class SingleScript {
    public static void main(String[] args) {
        int divide = divide(2,0);
        System.out.println(divide);
    }
    public static int divide(int x,int y){
        return x%y;
    }
}
```
1. Output:

```ps
$ java SingleScript.java
Exception in thread "main" java.lang.ArithmeticException: / by zero
        at SingleScript.divide(SingleScript.java:10)
        at SingleScript.main(SingleScript.java:5)
```

1. When we add details to the error

```java
public class SingleScript {
    public static void main(String[] args) {
        int divide = divide(2,0);
        System.out.println(divide);
    }

    public static int divide(int x,int y){
        if(y == 0){
            throw new ArithmeticException("Error Divide by 0");
        }
        return x%y;
    }
}

```

```txt
$ java SingleScript.java
Exception in thread "main" java.lang.ArithmeticException: Error Divide by 0
        at SingleScript.divide(SingleScript.java:11)
        at SingleScript.main(SingleScript.java:5)
```

## Handling Exception 

1. In the catch block we can write logs, throw other exceptions or return something.

```java
public class SingleScript {
    public static void main(String[] args) {
        int divide = divide(2, 0);
        System.out.println(divide); // 0
    }
    public static int divide(int x, int y) {
        try {
            return x / y;
        } catch (Exception e) {
            System.out.println("Error occuered. 0 is being returned");
            return 0;
        }
    }
}
```

1. In the above example the code keeps on executing because we handled the error and returned something.

## Resource Auto-Closure

1. Classes that implement the `AutoCoseable` interface can be instantiated using the `try-with-parameter` syntax.
1.  Automatic closure of such resources is provided by an implicitly formed `finally` block.

    ```java
    public static void readFile() {
        try (BufferedReader in = new BufferedReader(new FileReader("some.txt"))) {
            System.out.println(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ```
## Suppressed Exception

1. Auto Closure of a resource may produce suppressed exception.
1. Consider
    1. One exception is produced in the try block
    1. Another exception is produced in the implicitly formed finally block ( thrown by the close method).
    1. Method `getSuppressed` return a list of Suppressed Exception

```java
public class SingleScript {
    static class MyResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new Exception("Exception in close()");
        }
    }

    public static void main(String[] args) {
        try (MyResource res = new MyResource()) {
            throw new Exception("Exception in try block"); // 1
        } catch (Exception e) {
            System.out.println("Main Exception: " + e.getMessage());

            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed Exception: " + suppressed.getMessage());
            }
        }
    }
}
```

# Java Debugging

1. JDB Tool : https://mylearn.oracle.com/ou/course/java-se-21-programming-complete/138847/219975