# Java Collection API

1. Presents a number of classes that manipulates group of objects(Collection)
1. Uses Generics
1. Provide thread-safe operations
1. Iterate through collections
1. Part of `java.util` pacakge
1. Interfaces - `Iterable<T>,Collection<E>,List<E>`
1. Map is a bit different.
    1. `List<E> -> SequencedCollection<E> -> Collection<E> -> Iterable<T>`
    2. `Map<K, V>` its Own

## ArrayList

1. Allow duplicate elements
1. Initial Capacity of 10 elements
1. Constructor wit specific capacity
1. Read only instance of List can be created using a var-arg method

```java
List<Integer> l1 = new ArrayList<>();
List<Integer> l2 = new ArrayList<>(20);
List<Integer> l3 = Arrays.asList(1,2); // Returns a fixed size. Adding more elements is not possible 
List<Integer> l4 = List.of(1,2); // Fully immutable
List<Integer> l5 = new ArrayList<>(l3);
```

1. Common methods - `add(value),add(index,value),remove(index),remove(value),get(index)`

## Set

1. HashSet - Initial capcity of 16 elements & load factor ( 0.75 )
1. LoadFactor - Measure of how full the hash table is allowed to get before its capcity is automatically increased.
1. No duplicate elements - Duplicate elements are discarded.

```java
Set<Integer> p = new HashSet<>();
p.add(10);
p.add(10);
System.out.println(p); // 10
```

1. Methods `add` & `remove` verify if the elements exists in the set using equals method.
1. Metoods `add` & `remove` will return `false` when attempting to add a duplicate or remove an absent elements.

##  Deque  ( usually pronounced "deck" )

1. Double ended Queue. `FIFO`

## HashMap

1. A readonly instance of Map can be created using `of` method.
1. Operations `put(K,V),remove(K),get(K),containsKey(K),containsValue(V)`

```java
Map<Integer,String> sMap = new HashMap<>();
sMap.put(1, "Ritam");
sMap.put(1, "Syndicate");
System.out.println(sMap); // {1=Syndicate}
```

## Iterate through Collection

1. Iterator also allow to remove content from the collection.
1. List

    ```java
    List<String> names = Arrays.asList("John", "Billy", "Redux");
    for (String name : names) {
        System.out.print(name + ","); // John,Billy,Redux,
    }
    System.out.println();
    Iterator<String> itr = names.iterator();
    while(itr.hasNext()){
        System.out.print(itr.next() + ","); // John,Billy,Redux,
    }
    ```
1. HashMap - No iterators

    ```java
    Map<Integer, String> nameMap = new HashMap<>();
    nameMap.put(1, "John");
    nameMap.put(2, "Billy");
    nameMap.put(3, "Redux");
    Set<Integer> keySet = nameMap.keySet();
    Collection<String> nameVal = nameMap.values();
    for (Integer i : keySet) {
        System.out.print(nameMap.get(i) + ","); // // John,Billy,Redux,
    }
    System.out.println();
    for (String s : nameVal) {
        System.out.print(s + ","); // // John,Billy,Redux,
    }
    ```

## Sequence Collections

1. SequencedCollection is an interface introduced in Java 21 as part of JEP 431. It addressed a long-standing gap in the Collections Framework — there was no unified way to access the first and last elements of a collection, or to iterate it in reverse order.
1. Describe a well defined ecounter order of elements.
1. Support operations for elements at the start & end of the collection.
1. Add the reverse capability
1. `SequencedMap<K,V>,SequencedCollection<E>,SequencedSet<E>`


## Other collection behaviours

1. Convert collection to an array using the `toArray` method.
1. Remove elements from collection based on condition
    1. `removeIf(Predicate<T>)`

```java
public class SingleScript {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("TV"));
        productList.add(new Product("SOFA"));
        productList.add(new Product("PC"));
        Product[] pArray = new Product[productList.size()-1];
        pArray = productList.toArray(pArray);
        for (Product p : pArray) {
            System.out.print(p.name + ","); // TV,SOFA,PC,
        }
        productList.removeIf(t -> t.name.length() < 4);
        System.out.println(productList); // [SOFA]
    }
}

class Product{
    String name;
    public Product(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
```

## java.util.Colllections Class

1. Methods for Handling collections
1. Filing collection with Values
1. Searching through the collections
1. Reodering collection content using
    1. Comparable
    1. Comparator
    1. Reverse
    1. Shuffle

    ```java
    public class SingleScript {
        public static void main(String[] args) {
            List<Product> productList = new ArrayList<>();
            Product p1 = new Product("BED");
            productList.add(new Product("TV"));
            productList.add(new Product("SOFA"));
            productList.add(new Product("PC"));
            productList.add(p1);
            Collections.sort(productList, (o1, o2) -> o1.name.compareTo(o2.name));
            Collections.reverse(productList);
            Collections.shuffle(productList);

        }
    }

    class Product {
        String name;

        public Product(String name) {
            this.name = name;
        }
    }
    ```

# Access Collections Concurrently

1. Collection can be corrupted if accessed concurrently from multiple threads.
1. Fix is discussed later.

## Prevent COllection Corruption 

1. Unmodifiable ( Fast , but read only )
1. Synchronized ( Slow & unscalable)
1. Copy on write ( Fast but consumes memory) - Each thread receives a replica of a collection.

```java
Set<Product> readOnlySet = Collections.unmodifiableSet(set);
Map<Integer,Product> syncMap = Collections.synchronizedMap(map);
List<Product> copyOnWriteList = new CopyOnWriteArrayList<>(list);
```