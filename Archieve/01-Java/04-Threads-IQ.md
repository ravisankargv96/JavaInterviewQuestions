### 1) What is process? 
A process is a program in execution. 
Every process have their own memory space. Process are heavy weight and requires their own address space. One or more threads make a process.

### 2) What is thread in java? 
Thread is separate path of execution in program. Threads are 1) Light weight 2) They share the same address space. 3) creating thread is simple when compared to process because creating thread requires less resources when compared to process 4) Threads exists in process. A process have atleast one thread

### 3) Difference between process and thread? 

| S.No | Process                                                  | Thread                                                                           |
| ---- | -------------------------------------------------------- | -------------------------------------------------------------------------------- |
| 1    | Program in execution.                                    | Separate path of execution in program. One or more threads is called as process. |
| 2    | Processes are heavy weight                               | Threads are light weight.                                                        |
| 3    | Processes require separate address space.                | Threads share same address space.                                                |
| 4    | Interprocess communication is expensive.                 | Interthread communication is less expensive compared to processes.               |
| 5    | Context switching from one process to another is costly. | Context switching between threads is low cost.                                   |

### 4) What is multitasking ? 
Multitasking means *performing more than one activity at a time* on the computer. Example Using spreadsheet and using calculator at same time.

### 5) What are different types of multitasking? 
There are two different types of multitasking 
1) Process based multitasking 
2) Thread based multitasking

**Process based multitasking:** It allows to *run two or more programs concurrently*. In process based multitasking a process is the smallest part of code. 
Example: Running Ms word and Ms powerpoint at a time. 

**Thread based multitasking:** It allows to *run parts of a program to run concurrently*. 
Example: Formatting the text and printing word document at same time. 
Java supports thread based multitasking and provides built in support for multithreading. 

### 6) What are the benefits of multi-threaded programming?
Multithreading enables to use idle time of cpu to another thread which results in faster execution of program. In single threaded environment each task has to be completed before proceeding to next task making cpu idle. 

### 7) Explain thread in java? 
1) Thread is independent path of execution with in a program. 
2) A thread consists of three parts Virtual Cpu, Code and data. 
3) At run time threads share code and data i.e they use same address space. 
4) Every thread in java is an object of java.lang.Thread class. 

### 8) List Java API that supports threads? 

**java.lang.Thread:** This is one of the way to create a thread. By extending Thread class and overriding run() we can create thread in java.
**java.lang.Runnable:** Runnable is an interface in java. By implementing runnable interface and overriding run() we can create thread in java.
**java.lang.Object:** Object class is the super class for all the classes in java. In object class we have three methods wait(), notify(), notifyAll() that supports threads. 
**java.util.concurrent:** This package has classes and interfaces that supports concurrent programming.
Ex: Executor interface, Future task class etc. 

### 9) Explain about main thread in java? 
*Main thread is the first thread that starts immediately after a program is started*. 
Main thread is important because: 
1) All the child threads spawn from main thread. 
2) Main method is the last thread to finish execution. 
When JVM calls main method() it starts a new thread. Main() method is temporarily stopped while the new thread starts running. 

### 10) In how many ways we can create threads in java? 
We can create threads in java by any of the two ways: 
1) By *extending Thread* class 
2) By *Implementing Runnable* interface. 

### 11) Explain creating threads by implementing Runnable class?
This is first and foremost way to create threads. By implementing runnable interface and implementing run() method we can create new thread. 

Method signature : 
```java
public void run()
``` 

Run is the starting point for execution for another thread within our program. 

Example: 
```java
public class MyClass implements Runnable { 
	@Override 
	public void run() { 
		// T 
	} 
}
```

### 12) Explain creating threads by extending Thread class?
We can create a thread by extending Thread class. The class which extends Thread class must override the run() method. 
Example: 
```java
public class MyClass extends Thread { 
	@Override 
	public void run() { 
		// Starting point of Execution 
	}
}
```

### 13) Which is the best approach for creating thread ? 
The best way for creating threads is to implement runnable interface. 
When we extend Thread class we can’t extend any other class. 
When we create thread by implementing runnable interface we can implement Runnable interface. In both ways we have to implement run() method. 

### 14) Explain the importance of thread scheduler in java? 
Thread scheduler is part of JVM use to determine which thread to run at this moment when there are multiple threads. Only threads in runnable state are choosen by scheduler.

Thread scheduler first allocates the processor time to the higher priority threads. To allocate microprocessor time in between the threads of the same priority, thread scheduler follows round robin fashion. 

### 15) Explain the life cycle of thread? 
A thread can be in any of the five states: 
1) **New:** When the instance of thread is created it will be in New state. 
	Ex : Thread t= new Thread(); 
	In the above example t is in new state. The thread is created but not in active state to make it active we need to call start() method on it. 
2) **Runnable state:** A thread can be in the runnable state in either of the following two ways: 
	a) When the start method is invoked or 
	b) A thread can also be in runnable state after coming back from blocked or sleeping or waiting state. 
3) **Running state:** If thread scheduler allocates cpu time, then the thread will be in running state. 
4) **Waited/Blocking/Sleeping state:** In this state the thread can be made temporarily inactive for a short period of time. A thread can be in the above state in any of the following ways: 
	1) The thread waits to acquire lock of an object. 
	2) The thread waits for another thread to complete. 
	3) The thread waits for notification of other thread. 
5) **Dead State:** A thread is in dead state when thread’s run method execution is complete. It dies automatically when thread’s run method execution is completed and the thread object will be garbage collected. 
 
### 16) Can we restart a dead thread in java? 
If we try to restart a dead thread by using start method we will get run time exception since the thread is not alive. 

### 17) Can one thread block the other thread? 
No one thread cannot block the other thread in java. It can block the current thread that is running. 

### 18) Can we restart a thread already started in java? 
A thread can be started in java using start() method in java. If we call start method second time once it is started it will cause RunTimeException(IllegalThreadStateException). A runnable thread cannot be restarted. 

### 19) What happens if we don’t override run method ? 
If we don’t override run method .Then default implementation of Thread class run() method will be executed and hence the thread will never be in runnable state. 

### 20) Can we overload run() method in java? 
We can overload run method but Thread class start method will always cal run method with no arguments. But the overloaded method will not be called by start method we have to explicitly call this start() method. 

### 21) What is a lock or purpose of locks in java? 
*Lock also called monitor is used to prevent access to a shared resource by multiple threads*. A lock is associated to shared resource. Whenever a thread wants to access a shared resource if must first acquire a lock . If already a lock has been acquired by other it can’t access that shared resource. At this moment the thread has to wait until another thread releases the lock on shared resource. To lock an object we use synchronization in java. 
A lock protects section of code allowing only one thread to execute at at a time. 

### 22) In how many ways we can do synchronization in java? 
There are two ways to do synchronization in java: 
1) Synchronized methods 
2) Synchronized blocks 
To do synchronization we use synchronize keyword. 

### 23) What are synchronized methods? 
If we want a method of object to be accessed by single thread at a time we declare that method with synchronized keyword. 

Signature : 
```java
public synchronized void methodName(){}
```

To execute synchronized method first lock has to be acquired on that object. Once synchronized method is called lock will be automatically acquired on that method when no other thread has lock on that method. once lock has been acquired then synchronized method gets executed. Once synchronized method execution completes automatically lock will be released. The prerequisite to execute a synchronized method is to acquire lock before method execution. If there is a lock already acquired by any other thread it waits till the other thread completes. 

### 24) When do we use synchronized methods in java? 
If multiple threads tries to access a method where method can manipulate the state of object , in such scenario we can declare a method as synchronized. 

### 25) When a thread is executing synchronized methods, then is it possible to execute other synchronized methods simultaneously by other threads? 
No it is not possible to execute synchronized methods by other threads when a thread is inside a synchronized method. 

### 26) When a thread is executing a synchronized method, then is it possible for the same thread to access other synchronized methods of an object ? 
Yes it is possible for thread executing a synchronized method to execute another synchronized method of an object. 

```java
public synchronized void methodName() { 
}
``` 

To execute synchronized method first lock has to be acquired on that object. Once synchronized method is called lock will be automatically acquired on that method when no other thread has lock on that method. once lock has been acquired then synchronized method gets executed. Once synchronized method execution completes automatically lock will be released. The prerequisite to execute a synchronized method is to acquire lock before method execution. If there is a lock already acquired by any other thread it waits till the other thread completes. 

### 27) What are synchronized blocks in java? 
Synchronizing few lines of code rather than complete method with the help of synchronized keyword are called synchronized blocks. 
Signature : 

```java
Synchronized (object reference){
	// code
}
``` 

### 28) When do we use synchronized blocks and advantages of using synchronized blocks? 
If very few lines of code requires synchronization then it is recommended to use synchronized blocks. The main advantage of synchronized blocks over synchronized methods is it reduces the waiting time of threads and improves performance of the system.  

### 29) What is class level lock? 
Acquiring lock on the class instance rather than object of the class is called class level lock. The difference between class level lock and object level lock is in class level lock lock is acquired on class. class instance and in object level lock, lock is acquired on object of class. 

### 30) Can we synchronize static methods in java? 
Every class in java has a unique lock associated with it. If a thread wants to execute static synchronize method it need to acquire first class level lock. When a thread was executing static synchronized method no other thread can execute static synchronized method of class since lock is acquired on class. 
But it can execute the following methods simultaneously : 
1) Normal static methods 
2) Normal instance methods 
3) synchronize instance methods 
**Signature:** 
```java
synchronized(Classname.class){
}
``` 

### 31) Can we use synchronized block for primitives? 
Synchronized blocks are applicable only for objects if we try to use synchronized blocks for primitives we get compile time error. 

### 32) What are thread priorities and importance of thread priorities in java? 
When there are several threads in waiting, thread priorities determine which thread to run. In java programming language every thread has a priority. A thread inherits priority of its parent thread. By default thread has normal priority of 5. Thread scheduler uses thread priorities to decide when each thread is allowed to run. Thread scheduler runs higher priority threads first. 

### 33) Explain different types of thread priorities ? 
Every thread in java has priorities in between 1 to 10. By default priority is 5 (Thread.NORM_PRIORITY). The maximum priority would be 10 and minimum would be 1.Thread class defines the following constants(static final variables) to define properties. 
Thread.MIN_PRIORITY = 1; 
Thread.NORM_PRIORITY=5; 
Thread. MAX_PRIORITY=10; 

### 34) How to change the priority of thread or how to set priority of thread? 
Thread class has a set method to set the priority of thread and get method to get the priority of the thread. 
Signature: 
```java
final void setPriority(int value);
``` 
The setPriority() method is a request to jvm to set the priority. JVM may or may not oblige the request. We can get the priority of current thread by using getPriority() method of Thread class. 

```java
final int getPriority() { 
}
``` 

### 35) If two threads have same priority which thread will be executed first? 
We are not guaranteed which thread will be executed first when there are threads with equal priorities in the pool. It depends on thread scheduler to which thread to execute. The scheduler can do any of the following things : 1) It can pick any thread from the pool and run it till it completes. 2) It can give equal opportunity for all the threads by time slicing. 

### 36) What all methods are used to prevent thread execution? 
There are three methods in Thread class which prevents execution of thread. 1) yield() 2) join() 3) sleep() 

### 37) Explain yield() method in thread class? 
Yield() method makes the current running thread to move in to runnable state from running state giving chance to remaining threads of equal priority which are in waiting state. yield() makes current thread to sleep for a specified amount of time. There is no guarantee that moving a current running thread from runnable to running state. It all depends on thread scheduler it doesn’t gurantee anything. 
Calling yield() method on thread does not have any affect if object has a lock. The thread doesn't lose any lock if it has acquired a lock earlier. 

**Signature:** 
```java
public static native void yield() { 
}
``` 

### 38) Is it possible for yielded thread to get chance for its execution again ? 
Yield() causes current thread to sleep for specified amount of time giving opportunity for other threads of equal priority to execute. Thread scheduler decides whether it get chance for execution again or not. It all depends on mercy of thread scheduler. 

### 39) Explain the importance of join() method in thread class? 
A thread can invoke the join() method on other thread to wait for other thread to complete its execution. Assume we have two threads, t1 and t2 threads . A running thread t1 invokes join() on thread t2 then t1 thread will wait in to waiting state until t2 completes. Once t2 completes the execution, t1 will continue. join() method throws Interrupted Exception so when ever we use join() method we should handle Interrrupted Exception by throws or by using try catch block. Signature : public final void join() throws InterruptedException { } public final synchronized void join(long millis) throws InterruptedException { } public final synchronized void join(long millis, int nanos) throws InterruptedException { } 

### 40) Explain purpose of sleep() method in java? 
sleep() method causes current running thread to sleep for specified amount of time . sleep() method is the minimum amount of the time the current thread sleeps but not the exact amount of time. Signature : public static native void sleep(long millis) throws InterruptedException { } public static void sleep(long millis, int nanos) throws InterruptedException { } 

### 41) Assume a thread has lock on it, calling sleep() method on that thread will release the lock? 
Calling sleep() method on thread which has lock does’nt affect. Lock will not be released though the thread sleeps for a specified amount of time. 

### 42) Can sleep() method causes another thread to sleep? 
No sleep() method causes current thread to sleep not any other thread. 

### 43) Explain about interrupt() method of thread class ?
Thread class interrupt() method is used to interrupt current thread or another thread. It doesnot mean the current thread to stop immediately, it is polite way of telling or requesting to continue your present work. That is the reason we may not see the impact of interrupt call immediately. Initially thread has a boolean property(interrupted status) false. So when we call interrupt() method status would set to true. This causes the current thread to continue its work and does not have impact immediately. If a thread is in sleeping or waiting status (i.e thread has executed wait () or sleep() method) thread gets interrupted it stops what it is doing and throws an interrupted exception. This is reason we need to handle interrupted exception with throws or try/ catch block. 

### 44) Explain about interthread communication and how it takes place in java? 
Usually threads are created to perform different unrelated tasks but there may be situations where they may perform related tasks. Interthread communication in java is done with the help of following three methods : 1) wait() 2) notify() 3) notifyAll() 

### 45) Explain wait(), notify() and notifyAll() methods of object class ? 
wait() : wait() method() makes the thread current thread sleeps and releases the lock until some other thread acquires the lock and calls notify(). notify() :notify() method wakes up the thread that called wait on the same object. notfiyAll() :notifyAll() method wakes up all the threads that are called wait() on the same object. The highest priority threads will run first. All the above three methods are in object class and are called only in synchronized context. All the above three methods must handle InterruptedException by using throws clause or by using try catch clause. 

### 46) Explain why wait() , notify() and notifyAll() methods are in Object class rather than in thread class? 
First to know why they are in object class we should know what wait(), notify(), notifyAll() methods do. wait() , notify(), notifyAll() methods are object level methods they are called on same object.wait(), notify(), notifyAll() are called on an shared object so to they are kept in object class rather than thread class. 


### 47) Explain IllegalMonitorStateException and when it will be thrown? 
IllegalMonitorStateException is thrown when wait(), notify() and notifyAll() are called in non synchronized context. Wait(), notify(),notifyAll() must always be called in synchronized context other wise we get this run time exception. 

### 48) when wait(), notify(), notifyAll() methods are called does it releases the lock or holds the acquired lock? 
wait(), notify(), notifyAll() methods are always called in synchronized context. When these methods are called in synchronized context. So when they enter first in synchronized context thread acquires the lock on current object. When wait(), notify(), notifyAll() methods are called lock is released on that object. 

### 49) Explain which of the following methods releases the lock when yield(), join(),sleep(),wait(),notify(), notifyAll() methods are executed? 
Method Releases lock (Yes or No) yield() No sleep() No join() No wait() Yes Notify() Yes notifyAll() Yes 

### 50) What are thread groups? 
Thread Groups are group of threads and other thread groups. It is a way of grouping threads so that actions can be performed on set of threads for easy maintenance and security purposes. For example we can start and stop all thread groups. We rarely use thread group class. By default all the threads that are created belong to default thread group of the main thread. Every thread belongs to a thread group. Threads that belong to a particular thread group cannot modify threads belonging to another thread group. 


### 51) What are thread local variables? 
Thread local variables are variables associated to a particular thread rather than object. We declare ThreadLocal object as private static variable in a class. Everytime a new thread accesses object by using getter or setter we are accesing copy of object. Whenever a thread calls get or set method of ThreadLocal instance a new copy is associated with particular object. 

### 52) What are daemon threads in java? 
Daemon threads are threads which run in background. These are service threads and works for the benefit of other threads. Garbage collector is one of the good example for daemon threads. By default all threads are non daemon. Daemon nature of a thread can be inherited. If parent thread is daemon , child thread also inherits daemon nature of thread. 

### 53) How to make a non daemon thread as daemon? 
By default all threads are non daemon. We can make non daemon nature of thread to daemon by using setDaemon() method. The important point to note here we can call setDaemon() only before start() method is called on it. If we call setDaemon() after start() method an IllegalThreadStateException will be thrown. 

### 54) Can we make main() thread as daemon? 
Main thread is always non daemon. We cannot change the non daemon nature of main thread to daemon.