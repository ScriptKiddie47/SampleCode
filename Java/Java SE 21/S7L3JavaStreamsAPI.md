# Java Streams API

1. Immutable Flow of elements
1. Stream processing can be sequential or parallel. 
1. Uses Method Chaining - Intermediate Operations returns streams
1. Pipeline Traversal is lazy
    1. Intermediate actions are deferred unitl stream is traversed by the terminal operation
    1. The chain of activities could be fused into a single pass on data.
    1. Stream processing ends as soon as the result is determine; remaning stream data can be ignored.
1. Stream operations use functional interfaces and can be implemented as lambda expression.

## Create Streams using Stream API

1. `BaseStream` - Define Core Behaviours
1. `Stream,DoubleStream,IntStream,LongStream` - Interface extends `BaseStream`
1. Stream can be obtained from any collection and array or by using static methods of the stream class.


    ```java
    int sum = IntStream.of(1,3,4,5).sum(); //13
    int sum2 = IntStream.generate(() -> (int)(Math.random()*10)).limit(10).sum(); // 56
    ```

1. IntStream.generate() produces an infinite stream. Calling .sum() on it means Java tries to sum infinitely many elements — it never terminates.
generate() is an unbounded source by design. Terminal operations like sum(), collect(), toList() etc. need to consume the entire stream before returning, so they'll loop forever (or until you run out of memory/patience). 
1. .limit(n) makes it a finite stream, so sum() now has a clear stopping point.

## Stream Pipeline 

1. Steam handling operations categories
    1. `Intermediate` : Perform actions and produce another stream
    1. `Terminal` : Traverse stream pipeline and end the stream processing.
    1. `Short`-`Circuit` : Produce finite result,even if presented with infinite input.
1. Basic function purpose:
    1. `Predicate<T>` - Performs Tests
    1. `Function<T,R>` - Convert Types
    1. `UnaryOperator (A variant of funciton)<T>` - Converts Types
    1. `Consumer<T>` - Process elements
    1. `Supplier<T>` - Produces elements

    ```java
    Stream.generate(<Supplier>)
            .filter(<Predicate>)
            .peek(<Consumer>)
            .map(<Function>/<UnaryOperator>)
            .forEach(<Consumer>);
    ```

| Type | Examples | Behaviour |
|---|---|---|
| **Intermediate** | `peek()`, `filter()`, `map()` | Lazy — builds a pipeline, does nothing |
| **Terminal** | `forEach()`, `collect()`, `count()` | Eager — **triggers** the entire pipeline |

## Functional Interfaces

1. `Predicate<T>` - Defines `boolean test ( t t)` to apply conditions to filter elements
1. `Function<T,R>` - Defines method `R apply(T t)` to convert types of elements
1. `UnaryOperator<T>` - (Variant of Function) Defines method T apply ( T t) to convert values. Essentially returns the same type
1. `Consumer<T>` - Defines method `void accept(T t)` to process elements
1. `Supplier<T>` - Defines method `T get()` to produce elements


```java
public class SingleScript {
    public static void main(String[] args) { 
        List<Product> products = new ArrayList<>();
        products.stream() // Produces a Stream of Products from the List
            .filter(p -> p.discount() == 0) // Retain only products with no discount
            .peek(p -> p.applyDisocunt(0.1)) // Apply discount of 10%
            .map(p -> p.bestBeforeDays()) // Produce a stream of LocalDate Objects
            .forEach(d -> d.plusDays(1)); // Calculate the next day
    }
}

record Product(int discount){
    void applyDisocunt(double discount){}
    LocalDate bestBeforeDays(){ return LocalDate.now(); }
}
```
## Primitive Variations of Funcation Interfaces

1. Helps us avoid excessive auto-boxing-unboxing.

```java
Stream.of("ONE","TWO","THREE","FOUR")
    .mapToInt(value -> value.length())
    .peek(System.out::println)
    .filter(value -> value > 3)
    .sum();
```

1. `IntStream mapToInt(ToIntFunction<? super T> mapper);`

## BI-Arguments variants of Funcitonal Interface

1. Passing more than one value at a time.
    1. `BiPridicate<T,U>`
    2. `BiFunctiona<T,U,R>`
    3. `BinaryOperator<T>`
    4. `BiConsumer<T,U>`


```java
public class SingleScript {
    public static void main(String[] args) { 
        Map<Product,Integer> items = new HashMap<>();
        items.put(new Product("TV", 1000), 1);
        items.put(new Product("Fridge", 2000), 2);
        items.forEach((p, i) -> System.out.println(p.name() + ":" + i)); // TV:1   Fridge:2
    }
}

record Product(String name,int price){}
```

## Perform Actions with Stream Pipeline Elements

1. `Consumer<T>` Interface - Perform Actions
    1. Operations `peek`,`forEach`,`forEachOrdered` accept `Consumer<T>` Interface
    1. The default `andThen` method provided by the consumer interface combines consumers together.

```java
public class SingleScript {
    public static void main(String[] args) { 
        Product p1 = new Product(1000, "TV");
        Product p2 = new Product(500, "Cycle");
        List<Product> pList = Arrays.asList(p1,p2); // [1000:TV:0, 500:Cycle:0]
        pList.stream().peek(p -> {
            if(p.getPrice() > 500 ){
                p.setStars(5);
            }else{
                p.setStars(4);
            }
        });
        System.out.println(pList); // [1000:TV:0, 500:Cycle:0]
    }
}

class Product{
    private int price;
    private String name;
    private int stars;

    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public void setStars(int stars){
        this.stars = stars;
    }

    public int getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return price + ":" + name + ":" + stars;
    }
}
```

1. In the above example we see that the objects in pList didn't get modified. The entire .stream().peek(...) expression simply builds a Stream object and discards it — since you never assign it or terminate it.
1. However if we just do 

```java
List<Product> pList = Arrays.asList(p1,p2); // [1000:TV:0, 500:Cycle:0]
pList.stream().peek(p -> {
    if(p.getPrice() > 500 ){
        p.setStars(5);
    }else{
        p.setStars(4);
    }
}).forEach(System.out::println);
System.out.println(pList); // [1000:TV:5, 500:Cycle:4]
```
1. Terminal op fires the pipeline `peek()` executes on each element `setStars() `mutates the objects
1. However this is also not the correct way.This is the functional style — don't mutate, produce a new object. Works best if Product is immutable (like a record).

```java
List<Product> updated = pList.stream()
    .map(p -> new Product(p.getPrice(), p.getName(), p.getPrice() > 500 ? 5 : 4))
    .collect(Collectors.toList());
```

## Perform Filtering of Stream Pipeline

1. Method `filter`
1. Defualt methods provided by the Predicate
    1. `and` - Combines Predicate like &&
    1. `or` - Combines Predicate like ||
    1. `negate` - returns a preciate that represents the logical negation of this predicate.
1. Static Methods by the predicate interface
    1. `not`
    1. `isEqual` returns a predicate that compares the supplied object with the contents of the collection.

## Perform Mapping of Stream Pipeline Elements

1. The `map` accepts a `Function<T,R>`
1. `andThen` and `composte` combines function together.
1. Interface `UnaryOperator<T>` is a variant of a Funciton that maps values without changing the type.

```java
public class SingleScript {
    public static void main(String[] args) { 
        Product p1 = new Product(1000);
        Product p2 = new Product(500);
        List<Product> pList = Arrays.asList(p1,p2);
        pList.stream().map(t -> t.price()).forEach(System.out::print); // 1000,500
    }
}
record Product(int price){}
```


## Join Streams using flatMap Operation

1. Flatten a number of Streams into a single stream
1. Operation `Stream<R> flatMap(Function <T,Stream<R>> f)` merges stream

```java
public class SingleScript {
    public static void main(String[] args) { 
        Product tea = new Product("Tea",1000);
        Product cake = new Product("Cake",2000);
        Product coffee = new Product("Coffee",3000);
        Product cookie = new Product("Cookie",4000);
        Order o1 = new Order(Arrays.asList(tea,cake));
        Order o2 = new Order(Arrays.asList(cookie,coffee,cake));
        Order o3 = new Order(Arrays.asList(tea,cookie));
        List<Order> orders = Arrays.asList(o1,o2,o3);
        int x = orders.stream()
            .flatMap(o -> o.itemStream())
            .filter(p -> p.name().equals("Tea")) // Stream of all Products
            .mapToInt(p -> p.price())
            .sum();
        System.out.println(x); // 2000

    }
}
record Product(String name,int price){}
record Order(List<Product> items){
    public Stream<Product> itemStream(){
        return items.stream();
    }
}
```

1. The above example figures out the Total Sales of Tea for all orders. 

## Other Intermediate Stream operations

1. `distinct()`,`sorted()`,`sorted(Comparator<T> t)`,`skip(long 1)`
1. `takeWhile(Preidcate p)` - Takes elements from the stream while they match the predicate. `dropWhile(Predicate p)` - Removes Elements
1. `limit(long 1)` - Returns a stream of elements limited to a given size.

```java
Stream.of("A","B","C","D","A").distinct().forEach(System.out::print); //ABCD
```

## Short-Circuit Terminal Operations

1. Produce finite result even if presented with inifite output
1. All short circuit operations terminate stream pipeline processing as soon as reuslt is completed
1. `allMatch(Predicate s)` - Returns true if all elements in the stream match predicate
1. `anyMatch(Predicate s)` - Returns true if any element in the stream match predicate
1. `noneMatch(Predicate s)` - Returns true if no elements in the stream match the predicate 
1. `findAny()` - Returns an element from the stream wrapped in the Optional Object.
1. `findFirst()` - Returns the first element from the stream wrapped in Optional Object

```java
String[] values = {"RED","GREEN","BLUE"};
boolean allGreen = Arrays.stream(values).allMatch(s -> s.equals("GREEN")); // false
boolean anyGreen = Arrays.stream(values).anyMatch(s -> s.equals("GREEN")); // true
boolean noneGreen = Arrays.stream(values).noneMatch(s -> s.equals("GREEN")); // false
Optional<String> firstColor = Arrays.stream(values).findFirst(); // RED
```


## Process Stream using `count,min,max,sum,average` Operations

1. Terminal Operations Calculate values from stream content
1. Method `average` returns an `OptionalDouble(primitive vairant for Optional Class)`
1. `min` `max` returns Optional

```java
OptionalDouble average = IntStream.of(1,2,3,4).average();
System.out.println(average.getAsDouble()); // 2.5
```

## Aggregate Stream Data using `reduce` operations

1. Basically a terminal function that I can define 
1. Produce a single result from a stream of values using the reduce oprations
1. `Optional<T> reduce(BinaryOperator<T> accumulator)` performs accumulation of elements
1. `T reduce(T identify,BinaryOperator<T> accumulator)` identify acts as the initial(default) value.
1. `<U> U reduce(U identify,BiFunction<U,T,U> accumulator,BinaryOperator<U> combiner)` 
BiFunction performs both value mapping and accumulation of values
BinaryOperator combines results produces by the BiFunction in parallel stream handling mode.

```java
public class SingleScript {
    public static void main(String[] args) { 
        List<Product> products = new ArrayList<>();
        products.add(new Product("Fridge", 1000));
        products.add(new Product("TV", 2000));
        products.add(new Product("Cycle", 3000));
        Optional<String> joinNames = products.stream().map(p->p.name()).reduce((t, u) -> t + ":" + u);
        System.out.println(joinNames.get()); // Fridge:TV:Cycle
        String joinNames2 = products.stream()
            .map(p->p.name()).reduce("",(t, u) -> t + ":" + u); // Because we have default value "" present. The result isn't optional
        System.out.println(joinNames2); // :Fridge:TV:Cycle
        String joinNames3 = products.stream().parallel().reduce("",(t, u) -> u.name() + ":" + t,(t, u) -> t+u);
        System.out.println(joinNames3); // Fridge:TV:Cycle:
        
    }
}
record Product(String name,int price){}
record Order(List<Product> items){
    public Stream<Product> itemStream(){
        return items.stream();
    }
}
```

1. This is complicated. 200%

## Collect Operations

1. Perform a mutable reduction operation on the elements of the stream.
1. Method `collect` accepts `Collectors` interface implementation which
    1. Produces new result
1. Predefined implementation of the Collector interface supplied by Collectors calls
    1. `Mapping` & `Joining`
    1. `Gathering` - Stream elements into a collection such as list,set or map

```java
public class SingleScript {
    public static void main(String[] args) { 
        List<Product> products = new ArrayList<>();
        products.add(new Product("Fridge", 1000));
        products.add(new Product("TV", 2000));
        products.add(new Product("Cycle", 3000));
        String s = products.stream().collect(Collectors.mapping(p -> p.name(), Collectors.joining(",")));
        System.out.println(s); // Fridge,TV,Cycle
        List<Product> above2K = products.stream().filter(t -> t.price() > 2000).collect(Collectors.toList());
        System.out.println(above2K); // [Product[name=Cycle, price=3000]]
    }
}
record Product(String name,int price){}
record Order(List<Product> items){
    public Stream<Product> itemStream(){
        return items.stream();
    }
}
```

## Perform a Conversion of a Collector Result

1. Add finisher function to a collector to perform conversion of the collect result.
1. Method `Collectors.collectingAndThen` appens a finishing `Function` to a `Collector`.

## Perform Grouping or Partitioning of the Stream Content

1. `Partitioninng` divides content into a map with tow key values(boolean true/false) using Predicate
1. `Grouping` divides content into a map of multiple key values using Function.

```java
public class SingleScript {
    public static void main(String[] args) { 
        List<Product> products = new ArrayList<>();
        products.add(new Product("Fridge", 1000));
        products.add(new Product("TV", 2000));
        products.add(new Product("Cycle", 3000));
        Map<Boolean, List<Product>> productTypesMap = products.stream().collect(Collectors.partitioningBy(t -> t.price() > 2000));
        System.out.println(productTypesMap); // {false=[Product[name=Fridge, price=1000], Product[name=TV, price=2000]], true=[Product[name=Cycle, price=3000]]}
        Map<Integer, List<Product>> productGroupsMap = products.stream().collect(Collectors.groupingBy(t -> t.price()));
        System.out.println(productGroupsMap); // {2000=[Product[name=TV, price=2000]], 3000=[Product[name=Cycle, price=3000]], 1000=[Product[name=Fridge, price=1000]]}
    }
}
record Product(String name,int price){}
```

## Mapping and Filtering with Respect to Groups or Partitions

1. `flatMapping` collector is applied to each input element in the stream before accumulation
1. `filtering` collector eliminates content from the stream without removing an entire group, if the group turns out to be empty.

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            Product tea = new Product("Tea", 1.99);
            Product cake = new Product("Cake", 2.99);
            Product coffee = new Product("Coffe", 1.99);
            List<Product> p1 = Arrays.asList(tea,cake);
            List<Product> p2 = Arrays.asList(coffee);
            List<Product> p3 = Arrays.asList(coffee,cake);
            Order o1 = new Order(new Customer("Joe"),LocalDate.of(2018, 11, 21),p1);
            Order o2 = new Order(new Customer("Bob"),LocalDate.of(2018, 11, 21),p2);
            Order o3 = new Order(new Customer("Joe"),LocalDate.of(2018, 11, 22),p3);
            List<Order> orders = Arrays.asList(o1,o2,o3);
            Map<Customer, Set<Product>> customerProductsMap = orders.stream().collect(Collectors.groupingBy(t -> t.customer(),Collectors.flatMapping(t -> t.items().stream(),Collectors.toSet())));
            System.out.println(customerProductsMap); // {Customer[cName=Joe]=[Product[pName=Coffe, price=1.99], Product[pName=Tea, price=1.99], Product[pName=Cake, price=2.99]], Customer[cName=Bob]=[Product[pName=Coffe, price=1.99]]}
            Map<Customer, Set<Order>> customerOrderOnDateMap = orders.stream().collect(Collectors.groupingBy(t -> t.customer(),Collectors.filtering(t -> t.date().equals(LocalDate.of(2018, 11, 22)), Collectors.toSet())));
            System.out.println(customerOrderOnDateMap); // {Customer[cName=Joe]=[Order[customer=Customer[cName=Joe], date=2018-11-22, items=[Product[pName=Coffe, price=1.99], Product[pName=Cake, price=2.99]]]], Customer[cName=Bob]=[]
        }
    }
    record Product(String pName,double price){}
    record Customer(String cName){}
    record Order(Customer customer,LocalDate date,List<Product> items){}
    ```
1. Tricky.

## Parallel Streams

1. Elements of the stream are subdivided into subsets
1. Subsets are processed in parallel
1. Subsets are then combined.
1. Turn parallelism on or off using the parallel or sequential ( default methods )

```java
public class SingleScript {
    public static void main(String[] args) {
        Product tea = new Product("Tea", 1.99);
        Product cake = new Product("Cake", 2.99);
        Product coffee = new Product("Coffe", 1.99);
        List<Product> p1 = Arrays.asList(tea,cake);
        double sum = p1.stream().parallel().mapToDouble(value -> value.price()).sum(); // 4.98
    }
}
record Product(String pName,double price){}
```

1. It should observe the following guideline
    1. `Stateless` ( state of one element must not effect another element )
    1. `Noninterfeing` ( data source must not be affected)
    1. `Associative` ( result must not be affected by the order of operands)
1. Restriction on Parallel Steam Processing
    1. Incorrect handling of parallel stream can corrupt memory and slow down processing
    1. Do not perform operations that require sequence access to shared resource. ( `Console access`)
    1. Do not perform operations that modify shared resources.
        1. `toMap` in sequential mode.
        1. `toConcurrentMap` in parallel mode
1. Parallel processing can only be beneficial if
    1. Stream contains large number of elements
    1. Multiple CPU cores available.

```java
public class SingleScript {
    public static void main(String[] args) {
        Product tea = new Product("Tea", 1.99);
        Product cake = new Product("Cake", 2.99);
        Product coffee = new Product("Coffe", 1.99);
        List<Product> p1 = Arrays.asList(tea,cake);
        ConcurrentMap<String, Double> collect = p1.stream().parallel().collect(Collectors.toConcurrentMap(t -> t.pName(), t -> t.price())); // {Tea=1.99, Cake=2.99}
        System.out.println(collect);
    }
}
record Product(String pName,double price){}
```

## Spliterator

1. Spliterator is an analogue of iterator, with parallel processing capabilities.
1. Process next elements if exists
1. Process all remaining elements
1. Method `tryAdvance()` is an alternative to the combination of `hasNext()` and `next()` methods of the Iterator.
1. Method `forEachRemaining()` is an alternative to the entire Iterator loop

```java
Spliterator<Integer> s1 = new Random().ints(10,0,10).spliterator();
s1.tryAdvance(System.out::print); // 2
System.out.println();
Spliterator<Integer> s2 = s1.trySplit();
if(s2 == null){
    System.out.println("Did not split");
}else{
    s1.forEachRemaining(System.out::print); // 80451
    System.out.println();
    s2.forEachRemaining(System.out::print); // 8909
}
System.out.println();
```

