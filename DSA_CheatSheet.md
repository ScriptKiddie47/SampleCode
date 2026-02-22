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


## Set

