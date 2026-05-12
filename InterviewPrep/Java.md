# OOPS Concept

1. Encapsulation,Inheritance, Polymorphism, and Abstraction

# Java Feautes

8 -> Lambda Expression,Streams API, Optional, Default Methods ( Interface method with Body ) , method References, New Date/Time API, Funcitonal Interface ( Predicate,Function, Consumer, Supplier)
11 -> var, modern HTTP Client
17 -> Records,Text Blocks,Sealed Classes.
21 -> Virtual Threads,Sequenced Collections

# Solid Principles

The SOLID principles are five essential guidelines that enhance software design, making code more maintainable and scalable.

Single Responsibiliy ->  Every class should have a single responsibility
Open / Closed -> extend a class behavior, without modifying it
Liskov Substitution -> 
Interface segreation -> prevent fat interfaces by using multiple small, client-specific interfaces
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

ArrayList vs LinkedList - add(),remove(),get()
ArrayList is a dynamic array-based implementation of the List interface. It internally uses a resizable array to store elements.
    Stores elements in contiguous memory locations
    Fast random access using index (O(1))
    Slower data inserts in middle or beginning (O(n)), faster at end
    Best for frequent access and rare modifications
LinkedList is a doubly linked list-based implementation of the List and Deque interfaces. Each element is stored as a separate node.
    Stores elements as nodes linked using pointers
    Faster for insertion/deletion anywhere (O(1) if position known)
    Slower random access (O(n))
    Best for frequent insertions and deletions

HashMap:
A HashMap stores items in key/value pairs, where each key maps to a specific value.
It is part of the java.util package and implements the Map interface.
put(),get(),remove(),contains()

SQL
    Tables with rows and columns
    Fixed schema (predefined structure)
    ACID-compliant (strong consistency)
    SQL (Structured Query Language)
    Efficient for complex queries and transactions
    Best for transactional systems (banking, ERP, etc.)
NOSQL
    Document-based, key-value, column-family, or graph-based
    Flexible schema (dynamic and adaptable)
    BASE-compliant (more available, less consistent)
    Varies (e.g., MongoDB uses its own query language)
    Better for large-scale data and fast read/write operations
    Ideal for big data, real-time web apps, and data lakes


What are the different ways to `create an object` in Java? - new,reflection,clone(),factory metthod/builder pattern
`class` `loading` process in Java ? - Loading ( load bytecode,ext),Linking ( allocate memory ) & initialization ( static blocks and initlization )
Can a `class be loaded` twice ? - Not by the same classloader
What happens when you start a `thread twice`? - IllegationStateException - A thread can only be run twice.
How does Java handle memory leaks even though it has garbage collection? - Garbage collection works on Objects with no references but there can be object still references but not doing anything.
How does a HashSet ensure uniqueness internally? - Internally, HashSet IS a HashMap -  compute hashCode() on element + equals() -> duplicate, don't insert
What happens if you don't override hashCode properly? -> hashCode() gets you to the right bucket, equals() confirms you're the right tenant.
What's the difference between `ArrayList` and `CopyOnWriteArrayList`? - CopyOnWriteArrayList is Thread Safe.
What's the difference between `fail-fast` and `fail-safe` iterators?  - Fail-Fast throws ConcurrentModificationException
Fail Said Collections -> `ConcurrentHashMaps`,`CopyOnWriteArrayList`
How would you return a value from a thread?
How many types of `memory areas` are there in Java? ( Heap [Objects,Instance Variable],Stack[Each Thread gets its own stack,method calls,references]),Method Area,PC Register(Thread),Native Method Stack
What is `volatile` `keyword` and how is it different from `synchronized`? volatile solves visibility, synchronized solves both visibility and atomicity



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

