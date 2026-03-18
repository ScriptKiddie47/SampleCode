# DSA CheatSheet

### Note : I am dumb in DSA.

## Singly Linked List

1. Null values can be assigned to a value but cannot be worked upon.

| Question            | Common Topic                                | Solution                                                                                                                                                                                                                                                                                                                      | LeetCode                                                                         |
| ------------------- | ------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| Find Middle Node    | Floyd's Tortoise and Hare algorithm         | Define slow and fast pointer.&nbsp;Slow moves once.Fast moves twice.                                                                                                                                                                                                                                                          | https://leetcode.com/problems/middle-of-the-linked-list/                         |
| Has Loop            | Floyd's Tortoise and Hare algorithm         | Define slow and fast pointer.&nbsp; Will get to the point where slow & fast are pointing to the same node.                                                                                                                                                                                                                    | https://leetcode.com/problems/linked-list-cycle/                                 |
| Kth Node from End   | Floyd's Tortoise and Hare algorithm         | Move Fast forward K times for 1 Turn if null then k > length. Then slow & fast move together until fast reaches the end. When Fast is null slow is pointing to K                                                                                                                                                              | https://leetcode.com/problems/remove-nth-node-from-end-of-list/                  |
| Remove Duplicates   | Use 2 Pointers. Previous and current        | Loop through once.Use a Set. If data matches remove attribute.                                                                                                                                                                                                                                                                | https://leetcode.com/problems/remove-duplicates-from-sorted-list/                |
| Binary to Decimal   | Easy But tricky to remmember                | Easy But tricky to remmember . Must Watch video                                                                                                                                                                                                                                                                               | https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/ |
| Partition List      | Partition a Linked List based on a conditon | Create Dummy Nodes with value 0. D1 & D2. Define var prev1 & prev2 as part of D1 & D2. Increment them as required. Point prev1 to D2 to join the list. Remmember to terminate prev2 by setting it as null.                                                                                                                    | https://leetcode.com/problems/partition-list/                                    |
| Reverse Between     | Dummy Node                                  | Set dummy node before Head. Prev node navigates to the first index. Then assign currrent in the next node. We must run a loop (larger index - smaller index) times. Create a variable called toMove. Prev will point to toMove.Head points to node Dummy Node is pointing to. No need to reassign. Trickey AF.Need to Revisit | https://leetcode.com/problems/partition-list/                                    |
| Swap Nodes in Pairs | Dummy Node                                  | Create variables prev,first,second. Set second at the start of the loop. Note even and odd.                                                                                                                                                                                                                                   | https://leetcode.com/problems/swap-nodes-in-pairs/                               |

## Doubly Linked List

| Question            | Common Topic   | Solution                                                                                                  | LeetCode |
| ------------------- | -------------- | --------------------------------------------------------------------------------------------------------- | -------- |
| Palindrome Checker  | NA             | Iterate from Head & Tail. Loop length/2. Length will be given                                             | ?        |
| DLL Reverse         | Dummy          | Use prev & current. Prev points to Dummy Node. Reverse the pointers. Switch head & tail. Tricky Ass Logic | ?        |
| DLL Partition List  | Partition List | Carefully about prev & next.                                                                              | ?        |
| DLL Reverse Between | Dummy Node     | Carefully about prev & next.                                                                              | ?        |

## Stack & Queue

1. STACK - Last In First Out ( Implemented in Linked List. Push & Pop operation happens at the start of the List so its O(1) ).
   If implemented on Arraylist we use add() to insert & remove(arrayList.size()-1) to pop
1. QUEUE - First In First Out ( Implement in Linked List. Enque happens at end of LL & Deque happens at start of LL. This way O(1) )

| Question            | Common Topic | Solution                                                                                                                                                                                                                                     | LeetCode                                         |
| ------------------- | ------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------ |
| Reverse String      | Stack        | Loop over String & push everything to a stack. Pop into a new String                                                                                                                                                                         | https://leetcode.com/problems/reverse-string/    |
| Parathesis Balanced | Stack        | Iterate over Parathesis.Open -> Add to Stack. Closed -> pop. If stack is empty before complete iteration we return false.                                                                                                                    | https://leetcode.com/problems/valid-parentheses/ |
| Stack: Sort Stack   | Stack        | Use an additonal stack & a temp variable. Pop from stack & store in temp and push to additional stack if temp is less than additional stack peek. Else pop from additional stack & store in stack. Be carefull about the second loop. O(n^2) |                                                  |
| Queue using Stacks  | Stack        | Tricky. Copy data to an additonal stack then pop all of. Dequeue is just pop                                                                                                                                                                 |                                                  |

## Binary Tree

1. Full B Tree
2. Perfect B Tree
3. Complete B Tree -> Left to Right
4. A node with no children is called 'Leaf' Node.
5. Best : O(log n) Worst : o(n)

## Hash Tables

1. HashMaps only but some twists.
1. Deal with Collitions
   1. Separate Chaining ( Store the the new item alongside old item) - Using Linked List
   1. Linear Probing ( Got the next open spot and place the item there )

1. The second question here should be solved using 'Floyd's Tortoise and Hare algorithm' but I don't know how.

| Question                                | Common Topic | Solution                                                                                                                                                          | LeetCode                                                              |
| --------------------------------------- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------- |
| Find Common Elements Between Two Arrays | HashTable    | Create 2 Maps.Loop 4 Times. Create 2 Counters.O(n)                                                                                                                | https://leetcode.com/problems/find-common-elements-between-two-arrays |
| Find Duplicates in an Array             | HashTable    | Check HashMap                                                                                                                                                     | hhttps://leetcode.com/problems/find-the-duplicate-number/             |
| First Unique Character                  | HashTable    | Loop Loop                                                                                                                                                         | https://leetcode.com/problems/first-unique-character-in-a-string/     |
| Group Anagrams                          | HashTable    | Sort String and use it as Key. Best way to sort String would be to modify to char Array. Use Arrays.sort(charArray) then convert it back to new String(charArray) | https://leetcode.com/problems/group-anagrams/                         |
| Two Sum                                 | HashTable    | magicNumber = Target - Number. Check if magicNumber exists as key in HashMap. The value will be index. Return value & current index.                              | https://leetcode.com/problems/two-sum/                                |
| Subarray Sum                            | HashTable    | This is Tricky AF. Must Watch Video. Easy to solve though                                                                                                         | ?                                                                     |

## Set

1. Does't allow Duplicates.
1. Don't have Indexes.
1. O(1) for Lookup
1. ArrayList to Set -> `Set<Integer> set = new HashSet<>(list)`
1. Set to ArrayList -> `ArrayList<>(set)`

| Question                          | Common Topic | Solution                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | LeetCode                                                   |
| --------------------------------- | ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------- |
| Remove Duplicates                 | Set          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                            |
| String Has Unique Characters      | Set          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                            |
| Set: Find Pairs                   | Set          | You have 2 arrays. Convert one to Set. Comp = Target - Array1. Check if Comp is present in Set.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                            |
| Set: Longest Consecutive Sequence | Set          | Store all numbers in a **HashSet** for O(1) lookups. Then iterate through the **Set only not the array. Saves us from duplication** — for each number, check if `num - 1` exists in the set. If it doesn't, that means `num` is the **start of a new sequence**. From there, keep incrementing (`num + 1`, `num + 2`, ...) and counting how long the streak goes before hitting a gap. Track the maximum streak seen. This avoids redundant work — you only "walk" a sequence from its starting point, making the overall time complexity **O(n)** despite the nested-looking logic. | https://leetcode.com/problems/longest-consecutive-sequence |

## Graph

1. Node is called a `Vertex` or multiple Nodes are called `vertices`
1. `Edge` is the connection with other Nodes.
1. No limits how many other `vertices` a vertex can connect to.
1. We have weighted edges in Graph.
1. Adjacency Matrix - Weight are stored instead of 1. We can use HashMap to represent.
1. Time Complexity : Adjacency List O(1) , Adjacency Matrix O(V^2) [ V -> Number of Vertices ]
1. Adjacency List is better way to store Graphs

## Heap

1. Represented as a Tree
1. Always complete Tree ( Compelte - All spots are populated )
1. Max Heap : Max value at the top
1. Min Heap : Min value at the top ( PriorityQueue in Java )
1. Implemented using an Array List ( start at index - > 1)
1. Heap is always used to implement a priority queue because of Big(O).

### Fetch Child

1. Formula to fetch left & right child
1. Left Child = 2 \* parentIndex
1. Right Child = 2 \* parentIndex + 1

### Find Parent

1. Formula to find parent
1. Integer division -> Index / 2

### Big Note

1. `PriorityQueue<Integer> minHeap = new PriorityQueue<>();`
1. If a priority queue is defined as [1, 3, 2, 5, 6, 4]
1. `5`,`6`,`4` are all on the same level in the tree. PriorityQueue doesn't mean `sort`.

| Question                        | Common Topic   | Solution                                                                | LeetCode                                                      |
| ------------------------------- | -------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------- |
| findKthSmallest                 | Priority Queue | Keep polling till you get to the number                                 |                                                               |
| Kth Largest Element in an Array | Priority Queue | pop once priorityQueue size is greater than k. Tricky but just practice | https://leetcode.com/problems/kth-largest-element-in-an-array |


## Recursion

1. Function that calls itself.

### Head Recursion

1. Head Recursion — recursive call happens first, work is done on the way back up

```java
public static int factorial(int n) {
    if(n < 1) return 1;
    return n * factorial(n-1);
}
```

### Tail Recursion

1. Tail Recursion — recursive call is the last thing the function does. No pending work after it returns.
1. Instead of waiting for results on the way back up, you carry the running result forward via an extra parameter called an accumulator.

```java
// factorial(5, 1)
public static int factorial(int n, int acc) {
    if (n == 0)
        return acc;
    return factorial(n - 1, n * acc);
}
```


## BST

1. For every node, all values in the left subtree are smaller, and all values in the right subtree are larger.
1. Because of the ordering, you can find any value in O(log n) time on a balanced tree
