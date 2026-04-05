These notes cover the key concepts, technical interview questions, and practical demonstrations discussed in the video regarding Java Garbage Collection (GC).

---

### **1. Understanding Object Eligibility for Garbage Collection**
An object becomes eligible for garbage collection when it is no longer "reachable" by any live thread. There are four primary ways to make an object eligible:

*   **Nullifying the reference variable:** Setting a reference to `null` (e.g., `student = null;`).
*   **Reassigning the reference variable:** Pointing a reference to a new object, leaving the previous object orphaned.
*   **Object created inside a method:** Once the method execution is complete, local variables are popped off the stack, and the objects they referenced on the heap become eligible for GC.
*   **Island of Isolation:** A situation where Object A references Object B, and Object B references Object A, but no other live references point to either of them. Both will be garbage collected.

### **2. Can we force Garbage Collection in Java?**
The short answer is **No**. 
*   You can *request* the JVM to run the Garbage Collector using `System.gc()` or `Runtime.getRuntime().gc()`.
*   However, there is no guarantee that the JVM will execute the request immediately. The JVM decides the best time to run GC based on heap memory availability.
*   **Interview Tip:** In production code, calling `System.gc()` is generally discouraged as it can trigger a "Stop-the-World" event, pausing all application threads and degrading performance.

### **3. The `finalize()` Method**
*   **Purpose:** The `finalize()` method is defined in the `Object` class. It was designed to perform cleanup activities (like closing a database connection or a file) before an object is destroyed.
*   **Execution:** The Garbage Collector calls `finalize()` exactly once on an object before removing it from memory.
*   **Deprecation:** As of Java 9, `finalize()` is deprecated because it is unpredictable, slow, and can lead to performance issues or deadlocks.
*   **Exception Handling:** If the `finalize()` method throws an uncaught exception, the exception is ignored, and the GC process continues.

### **4. Key Interview Questions and Answers**

**Q1: What is the difference between `final`, `finally`, and `finalize`?**
*   **`final`:** A keyword used to make a variable constant, a method un-overridable, or a class un-inheritable.
*   **`finally`:** A block used in try-catch-finally for cleanup code that must execute regardless of whether an exception is thrown.
*   **`finalize`:** A method called by the GC before destroying an object.

**Q2: How many times does the GC call the `finalize()` method for an object?**
*   Exactly once. If an object "resurrects" itself (by assigning `this` to a static reference inside `finalize`), and later becomes eligible for GC again, the GC will not call `finalize()` a second time.

**Q3: Does Garbage Collection happen in the Stack or Heap?**
*   Garbage collection happens exclusively in the **Heap** memory. The Stack memory is managed automatically via stack frames; when a method finishes, its local variables are removed immediately.

**Q4: Which thread is responsible for Garbage Collection?**
*   A low-priority daemon thread called the "Garbage Collector Thread."

### **5. Live Demo Concepts: Observing GC Behavior**
The video demonstrates how to observe GC in action using code:

1.  **Creating a Class with `finalize()`:** To see when objects are destroyed, override the `finalize()` method with a print statement: 
    ```java
    @Override
    protected void finalize() {
        System.out.println("Garbage Collector called: Object destroyed.");
    }
    ```
2.  **Creating Multiple Objects:** In a loop, create thousands of objects without storing them in a list.
3.  **Calling `System.gc()`:** Manually calling the method in the code to trigger the print statement in the `finalize()` block.
4.  **Monitoring Memory:** Using tools like **JConsole** or **VisualVM** (built into the JDK) to visualize heap memory usage, showing the "sawtooth" pattern where memory builds up and then drops sharply after a GC cycle.

### **6. Types of Garbage Collectors (Brief Overview)**
The video touches upon the different algorithms the JVM uses:
*   **Serial GC:** Uses a single thread for GC; suitable for small applications.
*   **Parallel GC:** Uses multiple threads for GC to speed up processing (the default in older Java versions).
*   **G1 (Garbage First) GC:** Designed for multi-processor machines with large heap memory. It divides the heap into regions.
*   **ZGC (Z Garbage Collector):** A scalable, low-latency garbage collector introduced in later Java versions.

### **7. Important JVM Arguments for GC**
Interviewers often ask how to tune GC. Common flags mentioned include:
*   `-Xms`: Set initial heap size.
*   `-Xmx`: Set maximum heap size.
*   `-XX:+PrintGCDetails`: Provides a detailed log of when GC runs and how much memory was reclaimed.
*   `-XX:+UseG1GC`: Instructs the JVM to use the G1 Garbage Collector.

### **Summary Table: GC Eligibility Scenarios**

| Scenario | Code Example | GC Eligibility |
| :--- | :--- | :--- |
| **Nullifying** | `obj = null;` | Eligible immediately |
| **Reassigning** | `obj1 = obj2;` | `obj1`'s old object is eligible |
| **Out of Scope** | Method returns | All local method objects eligible |
| **Island of Isolation** | `a.ref = b; b.ref = a; a=null; b=null;` | Both A and B are eligible |