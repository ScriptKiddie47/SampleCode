# Java Concurrency Concepts

1. Thread actions are implemented by using the `run` method of `Runnable` interface.
1. Thread is scheduled to run using the `start` method of a `Thread` class.
1. Thread scheduler will allocate portions of CPU time ( time slice ) to execute thread actions
1. Java threads time-slice hardware threads ( Processors ) provided by the CPU cores can be interrupted at any time to give way to another thread,making the oredr of actions performed by different threads `stochastic`.
1. The return of the main or run method terminates the thread.
 
```java
public class SingleScript {
    public static void main(String[] args) {
        Thread t = new Thread(new Lateral()); // option 1
        t.start();
        Runnable r = () -> {};
        Thread t2 = new Thread(r); // option 2
        t2.start();
    }    
}
class Lateral implements Runnable{
    @Override
    public void run(){
    }
}
```

## Thread Life Cycle

1. NEW -> RUNNABLE -> BLOCKED -> WATING -> TIMED_WAITING -> TERMINATED 

```java
public class SingleScript {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> { try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }};
        Thread t1 = new Thread(r);
        System.out.println("State:" +t1.getState());
        System.out.println("Alive:" + t1.isAlive());
        t1.start();
        System.out.println("Alive:" + t1.isAlive());
        System.out.println("State:" + t1.getState());
        Thread.sleep(2000);
        System.out.println("State:" + t1.getState());
        System.out.println("Alive:" + t1.isAlive());
    }    
}
```

```ls
$ java SingleScript.java
State:NEW
Alive:false
Alive:true
State:RUNNABLE
State:TERMINATED
Alive:false
```

## Block Thread

1. Monitor object helps to coordinate the order of execution of threads.
1. Any object or a class can be used as a monitor.
1. It allows threads to enter blocked or waiting states.
1. They keyword `synchronized` enforces exclusive access to the block of code.
1. A thread that first enters the syncronized block reamins in `runnable` state.
1. All other threads accessing the same block enter the `blocked` state.
1. When a runnable thread exits the synchronized block, the lock is released.
1. Monitor examples are :
    1. Current Object(`this`) 
    1. `Some.class`
    1. Object `s`

### Thread Wait

1. `wait` methods puts a thread into `waiting state` against a specific monitor
1. Any number of threads can be waiting against the same monitor
1. The `notify` method wakes up one of the waiting threads ( stochastic )
1. The `notifyAll` method wakes up all waiting threads.
1. 