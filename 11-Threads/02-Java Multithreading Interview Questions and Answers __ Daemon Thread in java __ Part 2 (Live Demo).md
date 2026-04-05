These notes cover the key concepts, technical details, and interview-focused insights from the video regarding **Daemon Threads in Java**.

---

### **1. Definition of Daemon Thread**
*   **Core Concept:** A Daemon thread is a service provider thread that provides services to the user threads (non-daemon threads) for background support.
*   **Life Cycle:** Its life depends entirely on the user threads. If all user threads finish their execution, the JVM automatically terminates the daemon threads, even if they are still running.
*   **Example:** The Garbage Collector (GC) in Java is the most common example of a daemon thread. It runs in the background to free up memory but stops once the application (user threads) finishes.

### **2. Key Characteristics**
*   **Priority:** They usually run at a low priority, though this is not a strict rule.
*   **Background Execution:** They perform tasks like background processing, memory management, or periodic cleanups.
*   **JVM Behavior:** The JVM does not wait for daemon threads to finish before shutting down. It only waits for user threads.

### **3. Essential Methods**
The `java.lang.Thread` class provides two specific methods to handle daemon threads:

1.  **`public void setDaemon(boolean status)`**
    *   Used to mark a thread as either a daemon thread or a user thread.
    *   `true` marks it as daemon; `false` marks it as a user thread.
2.  **`public boolean isDaemon()`**
    *   Used to check if a specific thread is a daemon thread.

### **4. Critical Interview Rule: The IllegalThreadStateException**
*   **The Rule:** You must call `setDaemon(true)` **before** starting the thread (before calling the `start()` method).
*   **The Consequence:** If you attempt to call `setDaemon(true)` after the thread has already started, Java will throw an `IllegalThreadStateException` at runtime.

### **5. Live Demo Breakdown**
The video demonstrates the difference in behavior between a User Thread and a Daemon Thread using a code example:

#### **Scenario A: User Thread (Default)**
*   **Code:** A thread is created with a `for` loop printing 1 to 10 with a delay.
*   **Result:** The program continues to run until the loop finishes, even after the `main` method (the main thread) has ended.

#### **Scenario B: Daemon Thread**
*   **Code:** `t1.setDaemon(true);` is called before `t1.start();`.
*   **Result:** As soon as the `main` thread finishes its last line of code, the program terminates immediately. The daemon thread `t1` might only print one or two lines (or nothing at all) because the JVM kills it as soon as no user threads are left.

### **6. Thread Inheritance**
*   A child thread inherits the "daemon nature" of its parent thread.
*   If the Main thread (which is a user thread) creates a thread, that new thread is by default a user thread.
*   If a Daemon thread creates another thread, the newly created thread will also be a daemon thread by default.

### **7. Common Interview Questions & Answers**

**Q1: Can we make the Main thread a Daemon thread?**
*   **A:** No. The main thread is always a user thread. You cannot call `Thread.currentThread().setDaemon(true)` because the main thread is already started by the JVM before your code runs.

**Q2: Why does the JVM kill Daemon threads when User threads finish?**
*   **A:** Since the primary purpose of a daemon thread is to provide background support to user threads, if no user threads exist, there is no one left to "serve." Therefore, there is no reason for the daemon thread to continue running.

**Q3: What is the default nature of a thread?**
*   **A:** By default, any thread created by the user is a non-daemon (user) thread, unless it is created by another daemon thread.

**Q4: Where are Daemon threads used in real-time applications?**
*   **A:** They are used for tasks like monitoring memory, cleaning up cache, or handling background heartbeats in a network connection.

### **8. Summary Table**

| Feature | User Thread | Daemon Thread |
| :--- | :--- | :--- |
| **Purpose** | To execute main application logic. | To provide background support/services. |
| **JVM Shutdown** | JVM waits for it to finish. | JVM does not wait; terminates it immediately. |
| **Creation** | Default for threads created in `main`. | Must be explicitly set via `setDaemon(true)`. |
| **Priority** | Usually higher/normal. | Usually lower. |