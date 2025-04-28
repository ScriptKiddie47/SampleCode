# Groovy Lists

1. Pretty Flexibe

    ```groovy
    def primes = [2,3,5,7,11,13]
    println(primes) // [2, 3, 5, 7, 11, 13]
    println(primes[0]) // 2
    println(primes.get(0)) // 2
    println(primes.size()) // 6

    def employee = ['Derek',40,6.25,[1,2,3]];
    println(employee[3][1]) // 2
    ```

2. Append

    ```groovy
    def primes = [2,3,5,7,11,13]
    primes.add(17)
    primes<<19;
    println(primes) //  [2, 3, 5, 7, 11, 13, 17, 19]

    def primes2 = primes + [29,31];
    println(primes2) // [2, 3, 5, 7, 11, 13, 17, 19, 29, 31]

    def primes3 = primes2 - [31];
    println(primes3) // [2, 3, 5, 7, 11, 13, 17, 19, 29]
    ```

3. Check isEmpty & Slice


    ```groovy
    def primes = [2,3,5,7,11,13]
    println(primes.isEmpty()); // false
    println(primes[0..2]) // [2, 3, 5]
    ```

4. Matches,Reverse,Sort,Pop Last Item

    ```groovy
    def primes = [2,3,5,7,11,13]
    def primes2 = [2,3,5]
    println(primes.intersect(primes2)); // [2, 3, 5]
    println(primes2.reverse()) // [5, 3, 2]
    println(primes2.sort()) // [2, 3, 5]
    println(primes2.pop()) // 2
    ```