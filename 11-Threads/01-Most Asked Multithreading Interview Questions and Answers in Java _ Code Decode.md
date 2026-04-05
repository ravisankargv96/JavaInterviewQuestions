These notes provide a detailed breakdown of the "Most Asked Multithreading Interview Questions and Answers in Java" video by Code Decode.

---

# Multithreading Interview Questions & Answers in Java

## 1. Understanding Multitasking
Multitasking is the ability to perform multiple tasks simultaneously.
*   **Real-world example:** Watching TV while scrolling through Instagram on a phone.
*   **Computing context:** The Operating System (OS) sits between the hardware and the user, allowing multiple softwares (EXE files) to run at once.

### Types of Multitasking
1.  **Process-based Multitasking:** Running multiple programs (processes) concurrently. Example: Having Google Chrome, an IDE (STS), and a Media Player open at the same time.
2.  **Thread-based Multitasking (Multithreading):** Executing multiple parts of the same program simultaneously. Example: Chrome running multiple tabs or background tasks.

---

## 2. Process vs. Thread
### What is a Process?
*   A program in a running state is called a **Process**.
*   Each process is independent and has its own separate address space.
*   **How to see it:** In Windows, you can see active processes in the **Task Manager**.

### What is a Thread?
*   A thread is a "sub-process"—the smallest dispatchable unit of a program.
*   Threads exist within a process and share the process's resources and address space.
*   **Purpose:** To reduce response time and increase performance through parallel execution.
*   **How to see it:** Windows Task Manager doesn't show threads by default. You must use a tool like **Process Explorer**. By viewing the properties of a process (like Chrome) in Process Explorer, you can see the numerous threads working inside it.

---

## 3. Why is Multithreading Better than Process-based Multitasking?
In interviews, the answer is usually that thread-based multitasking is more efficient due to resource management:
*   **Heavyweight vs. Lightweight:** Processes are heavyweight (require their own memory space); Threads are lightweight (share memory).
*   **Context Switching:** Switching between processes is expensive and time-consuming for the CPU. Switching between threads is much faster and lower in cost.
*   **Communication:** Inter-process communication is expensive and complex. Inter-thread communication is inexpensive and easier because they share the same data space.

---

## 4. Threads in Java
Every Java program has at least one thread: the **Main Thread**.

*   The Main Thread is created by the JVM at the start of the program.
*   It is responsible for locating and executing the `main()` method.
*   **Code check:** You can verify the current thread name using:
    `Thread.currentThread().getName();` // Returns "main"

### Types of Threads in Java
1.  **User Threads (Non-Daemon):** These are high-priority threads created by the developer or the JVM (like the Main thread). The JVM will not shut down as long as a User thread is still running.
2.  **Daemon Threads:** These are low-priority threads that run in the background.
    *   **Example:** Garbage Collector.
    *   **Behavior:** They do not prevent the JVM from exiting. Once all User threads finish, the JVM terminates, even if Daemon threads are still running.

---

## 5. How to Create a Thread in Java
There are two primary ways to create a thread:

### Option A: Extending the `Thread` class
1.  Create a class that `extends Thread`.
2.  Override the `run()` method with the logic you want the thread to execute.
3.  Instantiate the class and call the `start()` method.
    *   **Note:** Always call `start()`, not `run()`. `start()` creates a new call stack and then invokes `run()`.

### Option B: Implementing the `Runnable` interface
1.  Create a class that `implements Runnable`.
2.  Provide an implementation for the `run()` method.
3.  Create an instance of your class, pass it into a new `Thread` object, and call `start()`.

---

## 6. Thread Class vs. Runnable Interface
Which one is better? Usually, **Implementing Runnable** is preferred.

*   **Inheritance:** Since Java doesn't support multiple inheritance, if you extend the `Thread` class, your class cannot extend any other class. If you implement `Runnable`, you are still free to extend another class.
*   **Specialization:** Use the `Thread` class only if you intend to override other thread methods (like `interrupt`, `stop`, etc.). If you only need to define a task (the `run` method), use `Runnable`.

---

## 7. Key Execution Concepts
### Context Switching
When multiple threads are running (e.g., a Main thread and a Child thread), the CPU switches back and forth between them. This is why the output of a multithreaded program often looks "mixed" or "interleaved" in the console.

### Thread Scheduler
The order in which threads execute is determined by the **Thread Scheduler**, which is part of the JVM.
*   Execution order is **not guaranteed**.
*   The behavior is "vendor-dependent," meaning it might vary slightly between different JVM implementations (Oracle, OpenJDK, etc.).
*   You can predict possible outcomes, but you can never guarantee the exact order of execution in multithreading.

---

## 8. Summary Checklist for Interviews
*   **Process:** Independent, heavyweight, separate memory.
*   **Thread:** Subset of a process, lightweight, shared memory.
*   **Main Thread:** Created by JVM; runs the `main()` method.
*   **start() vs run():** `start()` triggers a new thread; `run()` just executes the method in the current thread.
*   **Daemon Thread:** Background provider (e.g., GC); dies when user threads die.
*   **Runnable Interface:** Preferred over extending `Thread` due to inheritance flexibility.