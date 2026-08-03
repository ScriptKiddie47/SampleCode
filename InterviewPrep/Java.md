# OOPS Concept

1. Encapsulation,Inheritance, Polymorphism, and Abstraction

# Java Features

8 -> Lambda Expression,Streams API, Optional, Default Methods ( Interface method with Body ) , method References, New Date/Time API, Functional Interface ( Predicate,Function, Consumer, Supplier)
10 -> var
11 -> var in lambda parameters, modern HTTP Client
17 -> Records,Text Blocks,Sealed Classes.
21 -> Virtual Threads,Sequenced Collections

# Solid Principles

The SOLID principles are five essential guidelines that enhance software design, making code more maintainable and scalable.

Single Responsibility ->  Every class should have a single responsibility
Open / Closed -> extend a class behavior, without modifying it
Liskov Substitution -> A subclass should be substitutable for its parent class without breaking the program
Interface Segregation -> prevent fat interfaces by using multiple small, client-specific interfaces
Dependency Inversion -> depend upon abstractions, not concretes

# Unique Scenario 

Range Search Problem -> TreeMap with `floorKey()`-> `TreeMap<Integer,String> rangeMap = new TreeMap<>() ->  v = rangeMap.floorKey(199) -> rangeMap.get(v)`
1 GB File Upload -> `File Input Stream -> byte buffer[]`
 
# Definitions 

Dependency Injection (DI) is a design pattern used in object-oriented programming where an object receives its required dependencies from an external source rather than creating them itself. It helps manage how objects are constructed and how they obtain the resources they need. By separating dependency creation from usage, DI improves code flexibility. This results in systems that are easier to modify, test, and maintain.
Reduces tight coupling between classes
Improves code reusability and flexibility
Makes unit testing easier by allowing mock dependencies
Enhances maintainability and scalability of the system

`ArrayList` vs `LinkedList` - add(),remove(),get()
ArrayList is a dynamic array-based implementation of the List interface. It internally uses a resizable array to store elements.
    Stores elements in `contiguous memory locations`
    Fast random access using index (O(1))
    Slower data inserts in middle or beginning (O(n)), faster at end
    Best for frequent access and rare modifications
LinkedList is a doubly linked list-based implementation of the List and Deque interfaces. Each element is stored as a separate node.
    Stores elements as `nodes linked using pointers`
    Faster for insertion/deletion anywhere (O(1) if you already hold the node reference, O(n) to find the position)
    Slower random access (O(n))
    Best for frequent insertions and deletions





What is `classLoader` in Java - Part of JVM used to load .class files. | Bootstrap ClassLoader | 
What are the different ways to `create an object` in Java? - new,reflection,clone(),factory method/builder pattern
`class` `loading` process in Java ? - Loading ( load bytecode,ext),Linking ( allocate memory ) & initialization ( static blocks and initialization )
Can a `class be loaded` twice ? - Not by the same classloader
What happens when you start a `thread twice`? - IllegalStateException - A thread can only be run once.
How does Java handle memory leaks even though it has garbage collection? - Garbage collection works on Objects with no references but there can be object still references but not doing anything.
How does a `HashSet` ensure uniqueness internally? - Internally, `HashSet` IS a HashMap -  compute hashCode() on element + equals() -> duplicate, don't insert
What happens if you don't override hashCode properly? -> `hashCode()` gets you to the right bucket, `equals()` confirms you're the right tenant.
What's the difference between `ArrayList` and `CopyOnWriteArrayList`? - CopyOnWriteArrayList is Thread Safe.
What's the difference between `fail-fast` and `fail-safe` iterators?  - Fail-Fast throws ConcurrentModificationException
`fail safe` Collections -> `ConcurrentHashMaps`,`CopyOnWriteArrayList`
How would you return a value from a thread?
How many types of `memory areas` are there in Java? ( Heap [Objects,Instance Variable],Stack[Each Thread gets its own stack,method calls,references]),Method Area,PC Register(Thread),Native Method Stack
What is `volatile` `keyword` and how is it different from `synchronized`?
 volatile variable solves visibility, synchronized solves both visibility and atomicity
How can we make a class Immutable in Java ? - Class as Final,All Fields private & final, return copies of mutable objects.
What happens when the main thread dies but other threads are still running ? - If the main thread dies but the other `non daemon` threads are still running the program still runs.
How to remove duplicates from 1 million records - Use HashSet
What happens when we modify a list while iterating over it ? - If we do it using `for-each` - Java throws `Concurrent Modification Exception`. Use `iterator.remove()` or `CopyOnWriteArrayList`
ReentrantLock vs Synchronized - synchronized for simplicity (Simple mutual exclusion,Readability matters), ReentrantLock when you need fine-grained control. ( Need try/timeout/fairness , Multiple wait conditions)
Why `abstract` class have `constructor`? - The constructor is there to initialize the state (fields) of the abstract class — because the subclass inherits that state and needs it to be set up properly.


# Code Errors

```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
Stream<Integer> stream = list.stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println);
1
2
3
4
5
Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        at java.base/java.util.stream.AbstractPipeline.sourceStageSpliterator(AbstractPipeline.java:279)
        at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762)
        at Code.main(Code.java:9)
```

# Java Collections

1. `HashMap`:A HashMap stores items in key/value pairs, where each key maps to a specific value. `Unordered`
It is part of the java.util package and implements the Map interface.
put(),get(),remove(),contains()
Regarding Collision - It maintains a LinkedList per bucket and uses equals() to find the right entry. When a bucket exceeds 8 entries, it converts to a balanced tree (red-black tree) — lookup stays O(log n) instead of O(n)
1. `LinkedHashMap` - Keeps insertion order
1. `TreeMaps` - Keeps data in sorted order by Keys
1. `Concurrent HashMap` - Allows multiple threads to read and write safely without blocking the whole map
1. `Synchronized Map` - Entire map is blocked for each op. Slow in multithread envs





# Oauth 

An open standard / protocol for authorization

# Shallow Copy Vs Deep Copy

Shallow copy can be done using clone method. `@Override protected Object clone(){return super.clone();}`
The problem is primitives are fine but if the Class uses mutable objects the references are copied which is bad.

```java
@Override
protected Object clone() throws CloneNotSupportedException { // DEEP COPY
    Car car = (Car)super.clone();
    car.country = (Country)country.clone();
    return car;
}
```