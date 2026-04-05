 These notes provide a comprehensive guide to implementing a thread-safe Singleton pattern using Double-Checked Locking in Java, as discussed in the Code Decode video.

---

### 1. Introduction to the Singleton Design Pattern
The Singleton pattern ensures that a class has **only one instance** throughout the entire application and provides a global point of access to it.

#### Three Core Requirements for Implementation:
1.  **Private Static Instance:** A private static variable of the same class type to hold the single instance.
2.  **Private Constructor:** This prevents other classes from instantiating the class using the `new` keyword.
3.  **Public Static Access Method:** A method (usually `getInstance()`) that returns the instance. It checks if the instance is null; if yes, it creates it; if no, it returns the existing one.

---

### 2. Basic (Lazy) Implementation
In a single-threaded environment, the code looks like this:

```java
public class Singleton {
    private static Singleton instance; // Step 1

    private Singleton() {} // Step 2

    public static Singleton getInstance() { // Step 3
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```
**Verification:** You can verify this by printing the `hashCode()` of two objects retrieved via `getInstance()`. If they are the same, it is a Singleton.

---

### 3. The Multi-Threading Problem
The basic implementation fails in a multi-threaded environment.
*   **The Scenario:** Thread T1 and Thread T2 both reach the line `if (instance == null)` at the same time.
*   **The Conflict:** Both threads find the instance to be null. Consequently, both execute `new Singleton()`.
*   **Result:** Two different objects are created in memory, breaking the Singleton property.

---

### 4. Evolutionary Solutions

#### Solution A: Synchronized Method
One way to fix the thread safety issue is to make the entire method synchronized:
```java
public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
```
*   **Pros:** It is thread-safe.
*   **Cons:** **Performance Impact.** Synchronization is only needed the first time (when the instance is created). However, with a synchronized method, every subsequent call (even after the object exists) incurs the overhead of locking, which slows down the application.

#### Solution B: Double-Checked Locking (DCL)
To improve performance, we only synchronize the **critical section** (the code that creates the instance).

```java
public static Singleton getInstance() {
    if (instance == null) { // First check (No locking)
        synchronized (Singleton.class) {
            if (instance == null) { // Second check (With locking)
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```
*   **Why two checks?** 
    *   The first check ensures that if the instance is already created, we don't enter the synchronized block at all (improving performance).
    *   The second check ensures that if another thread created the instance while the current thread was waiting for the lock, we don't create a duplicate.

---

### 5. The Critical Role of the `volatile` Keyword
Even with Double-Checked Locking, the code is technically "broken" in Java unless the instance variable is marked as `volatile`.

#### The Problem: Visibility and Reordering
*   **CPU Caching:** Threads may cache the value of the `instance` variable in their local CPU cache rather than reading from main memory. Thread T2 might not "see" that Thread T1 has already created the object.
*   **Half-Initialized Objects:** Without `volatile`, the Java Memory Model allows "instruction reordering." The `new Singleton()` operation involves:
    1. Allocating memory.
    2. Calling the constructor.
    3. Linking the variable to the memory address.
    If the JVM reorders these, a thread might see a non-null instance that hasn't actually finished executing the constructor yet.

#### The Fix:
Declare the instance variable as `volatile`:
```java
private static volatile Singleton instance;
```

#### What `volatile` guarantees:
1.  **Main Memory Visibility:** All reads and writes go directly to main memory, not the CPU cache.
2.  **Happens-Before Relationship (Java 1.5+):** It ensures that a write to the volatile variable happens before any subsequent read. It prevents the "half-initialized" object issue by ensuring the constructor finishes before the reference is visible to other threads.

---

### 6. Final Thread-Safe Implementation
This is the industry-standard way to implement a thread-safe Singleton using Double-Checked Locking:

```java
public class Singleton {
    // Volatile keyword is mandatory for thread safety
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) { // Check 1
            synchronized (Singleton.class) {
                if (instance == null) { // Check 2
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### Summary Checklist for Interviews:
*   **Why Singleton?** One instance, global access.
*   **Why Private Constructor?** To prevent manual instantiation.
*   **Why Synchronized Method is bad?** High performance cost for every call.
*   **Why Double-Checked Locking?** To reduce synchronization overhead.
*   **Why Volatile?** To ensure visibility across threads and prevent issues caused by instruction reordering/half-initialized objects.