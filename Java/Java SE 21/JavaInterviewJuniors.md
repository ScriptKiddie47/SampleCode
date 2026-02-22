# Java Interview Juniors Question

# Code

### Snippet 1 – Static Context Error

```java
public class Employee {
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public static void main(String[] args) {
        setName("John");
        System.out.println(name);
    }
}
```

```txt
Exception in thread "main" java.lang.Error: Unresolved compilation problems: 
        Cannot make a static reference to the non-static method setName(String) from the type SingleScript
        Cannot make a static reference to the non-static field name
        at SingleScript.main(SingleScript.java:7)
```
### Snippet 2 – String Comparison Bug

```java
public class TestString {
    public static void main(String[] args) {
        String a = new String("hello");
        String b = new String("hello");
        if (a == b) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}
```
Output : Not Equal

### Snippet 3 – Incorrect Overriding

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}
class SingleScript extends Animal {
    void sound(String type) {
        System.out.println("Dog barking");
    }
    public static void main(String[] args) {
        Animal a = new SingleScript();
        a.sound();
    }
}
```

```txt
Animal sound
```

###  Snippet 4 – Unreachable Code

```java
class SingleScript {
    public static void main(String[] args) {
        System.out.println("Start");
        return;
        System.out.println("End");
    }
}
```


```txt
        Unreachable code
        at SingleScript.main(SingleScript.java:8)
```

# Quick Questions

### Core Java Basics

1. **What is the difference between JDK, JRE, and JVM?**
   JDK is for development (includes JRE + tools), JRE runs Java programs, and JVM executes bytecode.

2. **Is Java pass-by-value or pass-by-reference?**
   Java is strictly pass-by-value (object references are passed by value).

3. **What happens if the `main()` method signature is changed?**
   The JVM will not recognize it as the entry point and the program will not run.

4. **Can we overload the `main()` method?**
   Yes, but JVM only calls the standard `public static void main(String[] args)` method.

5. **What is the difference between `==` and `.equals()`?**
   `==` compares references, while `.equals()` compares object content (if overridden).

6. **Why are Strings immutable in Java?**
   For security, thread safety, caching (string pool), and performance optimization.

7. **Difference between `String`, `StringBuilder`, and `StringBuffer`?**
   `String` is immutable, `StringBuilder` is mutable and not thread-safe, `StringBuffer` is mutable and thread-safe.

8. **Can a class be both `abstract` and `final`?**
   No, because abstract requires inheritance and final prevents it.

9. **What is the default value of instance variables?**
   Default values depend on type (e.g., `0`, `false`, `null`).

10. **What is the purpose of the `static` keyword?**
    It makes members belong to the class rather than to instances.

---

### OOP Concepts

11. **Difference between abstraction and encapsulation?**
    Abstraction hides implementation details, encapsulation hides data using access modifiers.

12. **Method overloading vs method overriding?**
    Overloading changes parameters in the same class, overriding redefines a superclass method in a subclass.

13. **Can a constructor be overridden?**
    No, constructors are not inherited.

14. **What is runtime polymorphism?**
    Method overriding resolved at runtime via dynamic dispatch.

15. **Difference between interface and abstract class?**
    Interface supports multiple inheritance and defines contracts; abstract class can have state and constructors.

16. **What new features were added to interfaces in Java 8?**
    Default methods and static methods.

17. **What is the use of the `super` keyword?**
    To access parent class methods, variables, or constructors.

18. **What is object slicing in Java? Does it happen?**
    Object slicing does not occur in Java because objects are accessed via references.

19. **Composition vs inheritance?**
    Composition is "has-a" relationship; inheritance is "is-a" relationship.

20. **Can a class implement multiple interfaces?**
    Yes, Java supports multiple interface implementation.

---

### Exception Handling

21. **Difference between checked and unchecked exceptions?**
    Checked exceptions are verified at compile-time; unchecked exceptions occur at runtime.

22. **What happens if an exception is not handled?**
    It propagates up the call stack and may terminate the program.

23. **Can we have multiple catch blocks?**
    Yes, but they must be ordered from specific to general.

24. **Can we have a try block without catch?**
    Yes, if it is followed by a finally block.

25. **Difference between `throw` and `throws`?**
    `throw` is used to explicitly throw an exception; `throws` declares exceptions in a method signature.

26. **What is the purpose of the finally block?**
    To execute cleanup code regardless of exception occurrence.

27. **Does finally always execute?**
    It executes in most cases except when JVM shuts down abruptly (e.g., `System.exit()`).

---

### Collections & Generics

28. **Difference between `ArrayList` and `LinkedList`?**
    `ArrayList` uses dynamic arrays (fast random access); `LinkedList` uses nodes (fast insert/delete).

29. **Difference between `HashMap` and `ConcurrentHashMap`?**
    `HashMap` is not thread-safe; `ConcurrentHashMap` is thread-safe with better concurrency control.

30. **How does `HashMap` handle collisions?**
    By using buckets with linked lists or balanced trees (since Java 8).

31. **What is load factor in `HashMap`?**
    It determines resizing threshold based on capacity and performance trade-off.

32. **Difference between `Set` and `List`?**
    `Set` does not allow duplicates; `List` allows duplicates and maintains order.

33. **Why are generics used?**
    To provide type safety and eliminate runtime casting errors.

34. **What is type erasure?**
    Generic type information is removed at compile time and replaced with raw types.
