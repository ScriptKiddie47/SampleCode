# Java Memory Allocation

1. Java has 2 memory context : Stack & Heap
1. `Stack` : Thread, Local method variables
    1. Can only hold primitives and object references
1. `Heap` : Shared memory area, accessible from different methods and thread context.
    1. Classes and objects are stored in Heap

## Java Memory Cleanup

1. Objects reamin in the heap as long as they are still referenced.
1. An object becomes eligible for garbage collection when there are no references pointing to it.