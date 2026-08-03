# MultiThreading

`Time Slicing` :  CPU scheduling technique where the OS divides CPU time into small units &  allocates them to multiple threads/processes 
`Context Switch `: CPU switches from one thread/process to another, it needs to save the current state and load the next one's state
`Thread` : Smallest unit of execution within a program. Each threads has its own `stack`,`program counter` & `local variables`
`Create Thread` : 
    extends `Thread` class and `override run()` method -> create Object and run `object.start()`
    implement `Runnable` -> `override run()` -> `new Thread(object).start()`

`Thread LifeCycle` : `NEW → RUNNABLE → BLOCKED/WAITING/TIMED_WAITING → TERMINATED`

`Daemon Thread` : Background thread for which the JVM doesn't wait to exit
`synchronized` - Ensures only one thread at a time can execute a block/method — provides `mutual exclusion`
`Critical Section` - Part or method of our code that is accessed by multiple threads 
Locks - `intrinsic (Automatic)` vs `Explicit (Manual)`
When we use synchronized keyword `intrinsic` locks are automatically placed

`wait()` - Releases the lock while waiting, Must be inside synchronized block
`sleep()` - Just pauses execution — holds onto lock
`yield()` - Just a hint to JVM

Define Lock `private final Lock lock = new ReentrantLock();`
`lock.tryLock()` -> attempts to acquire the lock immediately; returns true/false without blocking. `DOESN't TRY AGAIN`
`lock.tryLock(1000, TimeUnit.MILLISECONDS)` -> waits up to the specified time to acquire the lock, then moves on if unsuccessful. `DOESN't TRY AGAIN`
`lock.lock();` -> Thread waits until it acquires the lock. Equal to synchronized
`lock.unlock();` -> Unlock lock


Why should we `interrupt()` thread in Exception Handling ?
-> We can cleanup , log by checking its status later down the line- `if(Thread.currentThread().isInterrupted()) System.out.println("");`

Fairness - `Lock lock = new ReentrantLock(true); // true = fair` - Threads are served in `FIFO` order.

Deadlock
    - `Mutual Exclusion` - Only 1 thread can access a resource at a time.
    - `Hold & Wait` - A thread holding at least one resource is waiting
    - `No Preemption` - Resource cannot be forcibly taken from threads holding them
    - `Circular Wait` - A set of threads is waiting for each other in a circular chain

`start() vs run()` - start() → hands off to JVM to create a new thread, then calls run() internally. run() → just a regular method call, no new thread, runs on whoever called it

`ThreadLocal` -  Map for threads. Each thread has it's own key and value
`Interleaved access vs sequential access`
`ReentrantLock` - A thread that has acquired the lock can acquire it again as many times as it wants. Once done, it must unlock the same number of times before another thread can acquire it. Failing to do so causes `reentrant lockout`

## Singleton Class

```java
class Singleton{
    // static: one shared instance across all callers
    // volatile: forces all threads to read from main memory, not their CPU cache
    //           also prevents instruction reordering (JVM can't half-construct the object then publish it)
    private static volatile Singleton instance;

    // private constructor: no one outside this class can call `new Singleton()`
    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){                    // First check (no lock): fast path for when instance already exists
            synchronized(Singleton.class){       // Lock on the class object — only one thread enters at a time
                if(instance == null){            // Second check (with lock): guards against two threads both passing the first null check
                    instance = new Singleton();  // Safe to create now — we're inside the lock
                }
            }
        }
        return instance; // Return the single shared instance
    }
}
```

# Executor

`Executors` - Factory class that creates thread pools:
- `newFixedThreadPool(n)` - Fixed number of threads. Extra tasks queue up until a thread is free
- `newCachedThreadPool()` - Grows as needed, reuses idle threads. Good for short-lived tasks
- `newSingleThreadExecutor()` - One thread, tasks run sequentially in submission order
- `newScheduledThreadPool(n)` - Supports delayed or periodic task execution

`ExecutorService` - Interface that manages the lifecycle of a thread pool:
- `execute(Runnable)` - Fire and forget, no return value
- `submit(Callable)` - Returns a `Future<T>` to retrieve the result or handle exceptions later
- `invokeAll(list)` - Submits all tasks, blocks until all complete, returns list of `Future`
- `invokeAny(list)` - Returns result of the first task to complete successfully, cancels the rest
- `shutdown()` - Stops accepting new tasks, waits for running tasks to finish
- `shutdownNow()` - Attempts to cancel running tasks, returns list of tasks that never started

`Future<T>` - Represents the result of an async task. `future.get()` blocks until the result is ready. `future.isDone()` checks without blocking.

`Runnable` vs `Callable` w.r.t Executor:
- `Runnable` - `void run()` — no return value, cannot throw checked exceptions. Use with `execute()` or `submit()`
- `Callable<T>` - `T call() throws Exception` — returns a value and can throw checked exceptions. Use with `submit()` only, which wraps it in a `Future<T>`

```java
ExecutorService ex = Executors.newFixedThreadPool(2);

// Runnable — fire and forget
ex.execute(() -> System.out.println("task"));

// Callable — get result back
Future<Integer> future = ex.submit(() -> 42);
int result = future.get(); // blocks until done
ex.shutdown();
```

`invokeAll(callableList)` - Submits all `Callable` tasks at once, runs them in parallel, then **blocks until every task is done**. Returns a `List<Future<T>>` where every Future is guaranteed to be complete (done or failed) — no need to check `isDone()`.

```java
ExecutorService ex = Executors.newFixedThreadPool(3);

List<Callable<String>> tasks = List.of(
    () -> "result1",
    () -> "result2",
    () -> "result3"
);

List<Future<String>> futures = ex.invokeAll(tasks); // blocks until ALL 3 finish

for (Future<String> f : futures) {
    System.out.println(f.get()); // won't block — already done
}
ex.shutdown();
```

- If a task throws, `future.get()` throws `ExecutionException` — wrap in try/catch per future
- `invokeAll(tasks, timeout, unit)` — overload that cancels any task still running after the timeout


# CompletableFuture

`CompletableFuture<T>` - Introduced in Java 8. Like `Future<T>` but **non-blocking** and chainable. You define what happens *when* the result is ready, instead of blocking with `get()`.

**Future vs CompletableFuture:**
| Feature | `Future` | `CompletableFuture` |
|---|---|---|
| Non-blocking | No (`get()` blocks) | Yes (callbacks) |
| Chaining | No | Yes (`thenApply`, etc.) |
| Combine multiple | No | Yes (`allOf`, `anyOf`) |
| Exception handling | Only at `get()` | `exceptionally`, `handle` |
| Manual completion | No | Yes (`complete()`) |

## Creating

```java
// Runs task on ForkJoinPool.commonPool() (default)
CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 42);

// Runs on a custom executor
CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 42, executorService);

// Fire-and-forget (no return value)
CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("done"));

// Already-completed future (useful for testing/defaults)
CompletableFuture<Integer> cf = CompletableFuture.completedFuture(100);
```

## Chaining (Pipeline)

Each method returns a new `CompletableFuture` — the chain runs stage by stage.

```java
CompletableFuture.supplyAsync(() -> 10)         // stage 1: produce 10
    .thenApply(n -> n * 2)                       // stage 2: transform → 20  (has return value)
    .thenAccept(n -> System.out.println(n))      // stage 3: consume → prints 20 (no return)
    .thenRun(() -> System.out.println("done"));  // stage 4: just a side-effect, ignores result
```

- `thenApply(fn)` — transform the result (like `map`) — `Function<T, R>`
- `thenAccept(fn)` — consume the result, returns `Void` — `Consumer<T>`
- `thenRun(fn)` — run something after, ignores result — `Runnable`
- Add `Async` suffix (`thenApplyAsync`) to run that stage on a different thread

## Combining Two Futures

```java
CompletableFuture<Integer> price = CompletableFuture.supplyAsync(() -> 100);
CompletableFuture<Integer> discount = CompletableFuture.supplyAsync(() -> 20);

// thenCombine — both run in parallel, combine when BOTH are done
CompletableFuture<Integer> final = price.thenCombine(discount, (p, d) -> p - d);
System.out.println(final.get()); // 80

// thenCompose — chain where stage 2 depends on stage 1's result (flatMap)
CompletableFuture<String> result = CompletableFuture
    .supplyAsync(() -> "user-123")
    .thenCompose(id -> CompletableFuture.supplyAsync(() -> fetchUser(id)));
```

- `thenCombine` — two independent futures, merge results → like `zip`
- `thenCompose` — second future depends on first's result → like `flatMap` (avoids `CompletableFuture<CompletableFuture<T>>`)

## Waiting on Multiple

```java
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "A");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "B");
CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "C");

// allOf — waits for ALL to complete (returns Void, must join individually)
CompletableFuture.allOf(f1, f2, f3).join(); // blocks until all 3 done
String result = f1.get() + f2.get() + f3.get(); // safe now

// anyOf — returns as soon as ANY ONE completes
Object first = CompletableFuture.anyOf(f1, f2, f3).get();
```

- `allOf` — parallel fan-out, wait for everything (like `invokeAll` but non-blocking)
- `anyOf` — return fastest result (like `invokeAny` but non-blocking)

## Exception Handling

```java
CompletableFuture.supplyAsync(() -> {
    if (true) throw new RuntimeException("oops");
    return 42;
})
.exceptionally(ex -> {
    System.out.println("caught: " + ex.getMessage());
    return -1; // fallback value
})
.thenAccept(System.out::println); // prints -1
```

```java
// handle — runs whether success or failure, lets you decide what to return
.handle((result, ex) -> {
    if (ex != null) return "error: " + ex.getMessage();
    return "ok: " + result;
});
```

- `exceptionally(fn)` — recovery only; skipped if no exception
- `handle(fn)` — always runs; receives `(result, exception)` — one will be null
- `whenComplete(fn)` — like handle but can't change the return value (side-effects only)

## get() vs join()

- `get()` — throws checked `InterruptedException` + `ExecutionException` — must be in try/catch
- `join()` — throws unchecked `CompletionException` — cleaner in lambdas/streams

```java
// Common pattern: build pipeline, block at the end
String result = CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> process(data))
    .join(); // block here only — rest of chain was non-blocking
```

