# Maps


    ```groovy
    def paulMap = [
        'name':'Paul',
        'age':35,
        'address':'1234 Main St',
        'list':[1,2,3]
    ]
    println(paulMap['name']) // Paul
    println(paulMap.get('age')) // 35
    println(paulMap['list'][1]) // 
    paulMap.put('City','Kolkata')
    println(paulMap.containsKey('City')); // true
    println(paulMap.size()); // 5
    ```

# Range


```groovy
        
def oneTo10 = 1..10;
def aToZ = 'a'..'z';
def zToA = 'z'..'a';

println(oneTo10.size()) // 10
println(aToZ.contains('b')) // true
println(oneTo10.getTo()); // Gets Last Item -> 10
println(oneTo10.getFrom()); // Gets First Item -> 1
```