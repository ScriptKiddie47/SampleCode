# Java Coding Question

## Numbers

1. Prime Number
1. Reverse a Number - Extend to Check Palindrome

## Strings

1. Reverse a String - Extend to Check Palindrome
1. FizzBuzz - String Concatnation
1. Print longest non repetitive substring abcabcdeaab 
 

## Arrays

1. Find the second largest element in Array - Use `Arrays.sort()`. Then  `arr[arr.length-2]`
1. Find repeated elements in the array. `HashMap,Streams`
    1. `Arrays.stream(intArray).boxed().collect(Collectors.groupingBy(t -> t,Collectors.counting())).entrySet().stream().filter(t -> t.getValue() > 1).mapToInt(t -> t.getKey()).distinct().toArray();`
1. Bubble Sort - O(n^2)
1. Binary Search - O(logn)


## ArrayList

1. Common elements between 2 ArrayList -> use `filter( c -> contains.(c))`,`copyList.retainAll(list2)`


## Java Steams

1. Find the max of all elements in the stream. `stream()->mapToInt()->max()->getAsInt() or max().orElseThrow()-> OptionalInt is returned` 
1. Find the sum of all elements in the stream. `stream()->mapToInt()->sum()`
1. Find even numbers. `filter(t -> t % 2 == 0).collect(Collectors.toList())`
1. List of Strings to sort in Alphabetical order. `stream().sorted()`
1. Write a program to find the longest string in a list of strings. `stream().max((o1, o2) -> o1.length() - o2.length()).`
1. Given a list of integers, write a program to find and print the second largest number using Java Stream API. `stream().sorted((o1, o2) -> o2 - o1).skip(1).findFirst().orElseThrow()`
1. Write a program to remove all the duplicate elements from a list using Java Stream API. `stream().distinct().collect(Collectors.toList())`
1. Given a list of strings, write a program to find and print the shortest string using Java Stream API. `stream().min((o1, o2) -> o1.length() - o2.length()).get()`
1. Write a program to convert a list of integers to a list of their squares using Java Stream API. `stream().map(t -> t*t).collect(Collectors.toList())`
1. Given a list of strings, write a program to find and print the strings starting with a specific prefix ‘a’ using Java Stream API. `.filter(t -> t.startsWith("a")).collect(Collectors.toList())`
1. Given an integer array, find the sum of digits of each element and return the result as an array. Use Java Stream. `Arrays.stream(intArray).map(v -> String.valueOf(v).chars().map(o -> Character.getNumericValue(o)).sum()).toArray();`
1. Given a String, find the frequency/count of each character and store it in a Map. `s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c,Collectors.counting()));`
1. Write a program to convert a list of strings to a comma-separated string using Java Stream API. `strings.stream().collect(Collectors.joining(","));`
1. Write a program to convert a list of strings to a comma-separated string using Java Stream API. `strings.stream().collect(Collectors.mapping(t -> t,Collectors.joining(",")));`
1. Given a list of integers, write a program to find and print the index of the first occurrence of a specific number using Java Stream API. `numbers.indexOf(7);`
1. Write a program to find the union of two lists of integers using Java Stream API.  `union.stream().flatMap(t -> t.stream()).distinct().collect(Collectors.toList());`
1. Write a program to find the union of two lists of integers using Java Stream API. `Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());`
1. `FOCUS` Given a list of strings, write a program to find and print the strings containing duplicate characters using Java Stream API. `strings.stream().filter(t -> t.length() != t.chars().distinct().count()).collect(Collectors.toList());`
1. `FOCUS` Write a program to check if all elements in a list of strings are of the same length using Java Stream API. `strings.stream().map(e -> e.length()).distinct().count() == 1;`
1. Given a list of integers, write a program to find and print the difference between the maximum and minimum numbers using Java Stream API. `numbers.stream().mapToInt(value -> value).max().getAsInt()..`
1. Write a program to remove all whitespace from a list of strings using Java Stream API. `strings.stream().map(t -> t.replaceAll(" ", "")).collect(Collectors.toList());`
1. Given a list of strings, write a program to find and print the strings with the minimum length using Java Stream API. `strings.stream().min((o1, o2) -> o1.length() - o2.length()).get();`
1. Given a list of Strings, create a Map where the key is the String and the value is its length. `strings.stream().collect(Collectors.toMap(t -> t, t -> t.length()));`
1. `FOCUS` Given a list of strings, write a program to find and print the strings with the maximum number of vowels using Java Stream API.
    1. `strings.stream().collect(Collectors.toMap(t -> t, t -> t.chars().filter(c -> (String.valueOf((char)c).matches("[aeiouAEIOU]+"))).count()));`
    1. `collectMap.values().stream().mapToLong(value -> value).max().getAsLong();`
    1. `collectMap.keySet().stream().filter( t -> collectMap.get(t) == longest).collect(Collectors.toList());`

1. Write a program to check if a list of integers is sorted in ascending order using Java Stream API. `myList.stream().sorted().collect(Collectors.toList()).equals(myList);`
1. Given a list of strings, write a program to find and print the strings containing a specific character at least twice using Java Stream API. `strings.stream().filter(t -> t.chars().filter(c -> c == sC).count() > 2).collect(Collectors.toList());`
1. Write a program to find the kth smallest element in a list of integers using Java Stream API. `numbers.stream().sorted().skip(k-1).findFirst().get();`
1. Given a list of strings, write a program to find and print the strings with the maximum number of consonants using Java Stream API.
    1. `strings.stream().collect(Collectors.toMap(t -> t, t -> t.chars().filter(c -> consonent.indexOf(c) != 1).count()));`
    1. `collect.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toList());`
    1. `collect2.get(0).getKey()`
1. Merging Two Different Lists in Java 
    1. `record Employee(String name,int salary,String dept_name){}`
    1. `record Dept(String dept_name,int deptCount){}`
    1. `List<String> list = employees.stream().flatMap(e -> depts.stream().filter(d -> d.dept_name().equals(e.dept_name())).map(dt -> e.name() + " > " + dt.dept_name() + " count(" + dt.deptCount())).toList();`
    1. Note -> Flatmap ends at the very end.
1. LinkedHahSet - 

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class R {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Female", "28", "Engineering", 85000),
                new Employee("Bob", "Male", "35", "Marketing", 72000),
                new Employee("Clara", "Female", "42", "Engineering", 95000),
                new Employee("David", "Male", "29", "HR", 60000),
                new Employee("Eva", "Female", "31", "Finance", 78000),
                new Employee("Frank", "Male", "45", "Engineering", 110000),
                new Employee("Grace", "Female", "27", "Marketing", 65000),
                new Employee("Henry", "Male", "38", "Finance", 88000),
                new Employee("Isla", "Female", "33", "HR", 62000),
                new Employee("James", "Male", "40", "Engineering", 102000));

        // Group by Dept and total salary
        employees.stream().collect(Collectors.groupingBy(t -> t.dept(),Collectors.summingLong(t -> t.salary())));
        // Group By Employee with Max Salary per Dept
        employees.stream().collect(Collectors.groupingBy(t -> t.dept(),Collectors.maxBy((o1, o2) -> o1.salary() - o1.salary())));
        // Find the Employee with the highest salary
        employees.stream().sorted((o1, o2) -> o2.salary() - o1.salary()).findFirst().get();
        // Total Salary of all employees
        employees.stream().mapToInt(value -> value.salary()).sum();
        // Count the number of Emplyees in each dept
        employees.stream().collect(Collectors.groupingBy(t -> t.dept(),Collectors.counting()));
        // Find the Average Salary in Each Dept
        employees.stream().collect(Collectors.groupingBy(t -> t.dept(),Collectors.averagingInt(s -> s.salary())));
        // Find the highest paid male and female employee in each dept
        employees.stream().collect(Collectors.groupingBy(t -> t.dept(),Collectors.groupingBy(t -> t.gender(),Collectors.maxBy((o1, o2) -> o2.salary() - o1.salary()))));
        // Get department wise average salary sorted by average salary
        List<Entry<String, Double>> collect = employees.stream()
                .collect(Collectors.groupingBy(t -> t.dept(), Collectors.averagingInt(value -> value.salary())))
                .entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}

record Employee(String name, String gender, String age, String dept, int salary) {
}
```

# Java Threads

1. Odd-Even Printing with Two Threads

```java

public class Code {
    public static void main(String[] args) {
        Object lock = new Object();
        Runnable r1 = new EvenAndOddPrinterBy2Threads(lock);
        Runnable r2 = new EvenAndOddPrinterBy2Threads(lock);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.setName("even");
        t2.setName("odd");
        t1.start();
        t2.start();
    }
}
class EvenAndOddPrinterBy2Threads implements Runnable {

    static int count = 1;
    Object object;

    public EvenAndOddPrinterBy2Threads(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        while (count <= 10) {
            if (count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (object) {
                    System.out.println("Thread Name:" + Thread.currentThread().getName() + " value:" + count);
                    count++;
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }

            if (count % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (object) {
                    System.out.println("Thread Name:" + Thread.currentThread().getName() + " value:" + count);
                    count++;
                    object.notify();
                }
            }
        }
    }
}
```