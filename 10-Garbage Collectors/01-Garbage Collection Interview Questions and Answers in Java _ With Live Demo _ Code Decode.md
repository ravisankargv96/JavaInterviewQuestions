These notes cover the essential concepts, mechanisms, and interview-specific questions discussed in the "Garbage Collection in Java" video by Code Decode.

---

### **1. Introduction to Garbage Collection (GC)**
*   **Definition:** Garbage Collection is the process by which Java programs perform automatic memory management. It identifies unused objects in the Heap memory and deletes them to free up space for new objects.
*   **The Problem in C/C++:** In older languages, developers had to manually allocate (`malloc`) and deallocate (`free`) memory. Forgetting to deallocate led to memory leaks; deallocating too early led to "dangling pointers."
*   **Java's Solution:** Java provides an automatic Garbage Collector. It is a **daemon thread** that runs in the background.

---

### **2. When is an Object Eligible for GC?**
An object becomes eligible for garbage collection when it is no longer reachable. This happens in several ways:
*   **Nulling the reference:** `Student s = new Student(); s = null;`
*   **Reassigning the reference variable:** `Student s1 = new Student(); Student s2 = new Student(); s1 = s2;` (The original object s1 was pointing to is now eligible).
*   **Local objects:** Objects created inside a method become eligible once the method finishes execution (unless returned).
*   **Island of Isolation:** A group of objects that reference each other but are not referenced by any "live" part of the application.

---

### **3. Java Heap Memory Structure**
The Heap is divided into generations to optimize GC performance (based on the observation that most objects die young).

#### **A. Young Generation**
*   **Eden Space:** Where all new objects are initially created.
*   **Survivor Spaces (S0 and S1):** When Eden is full, a **Minor GC** occurs. Surviving objects move to S0 or S1.
*   **Mechanism:** Every time an object survives a Minor GC, its "age" increases.

#### **B. Old (Tenured) Generation**
*   When objects reach a certain age threshold in the Survivor spaces, they are moved (promoted) to the Old Generation.
*   A **Major GC** (or Full GC) happens when the Old Generation is full. This is more "expensive" and takes longer than a Minor GC.

#### **C. Metaspace (formerly PermGen)**
*   Introduced in Java 8 to replace PermGen.
*   Stores class metadata, method data, and the constant pool.
*   It is located in **Native Memory**, not the JVM Heap, allowing it to grow dynamically.

---

### **4. How Garbage Collection Works (Mark and Sweep)**
The most common algorithm used by GC involves two main steps:
1.  **Marking:** The GC traverses the object graph starting from "GC Roots" (Stack references, static variables). It marks all objects that are reachable as "Live."
2.  **Sweeping:** The GC scans the heap and identifies memory segments containing objects not marked as "Live." These are deleted.
3.  **Compaction (Optional):** After sweeping, the GC may move live objects together to remove gaps (fragmentation) in memory.

---

### **5. Important Methods in GC**
*   **`System.gc()` and `Runtime.getRuntime().gc()`:**
    *   These are used to **request** the JVM to run the Garbage Collector.
    *   **Interview Tip:** Does it guarantee GC? **No.** It is just a hint to the JVM. The JVM makes the final decision based on its internal logic.
*   **`finalize()` method:**
    *   Defined in the `Object` class.
    *   The GC calls this method on an object exactly once before destroying it.
    *   It is used for "cleanup" activities (e.g., closing a database connection).
    *   **Interview Tip:** If an exception is thrown in `finalize()`, it is ignored, and the object is still collected. It is generally recommended to avoid using `finalize()` (it is deprecated in newer Java versions).

---

### **6. Types of Garbage Collectors**
1.  **Serial GC:** Uses a single thread for GC. It pauses all application threads (Stop-The-World). Good for small applications.
2.  **Parallel GC:** The default in Java 8. Uses multiple threads for the Young Generation.
3.  **CMS (Concurrent Mark Sweep):** Aims to minimize "Stop-The-World" pauses by doing most work concurrently with application threads. (Deprecated/Removed in later versions).
4.  **G1 (Garbage First):** Designed for large heap sizes. It divides the heap into regions and prioritizes regions with the most garbage. Default in Java 9+.
5.  **ZGC (Z Garbage Collector):** A scalable, low-latency collector introduced in later Java versions for ultra-fast performance.

---

### **7. Common Interview Questions and Answers**
*   **Q: Can we force Garbage Collection?**
    *   A: No. We can only suggest it using `System.gc()`.
*   **Q: What is "Stop-The-World"?**
    *   A: It is a phase where the JVM stops all application threads so the GC can safely identify and delete objects without the heap state changing.
*   **Q: What is the difference between Minor and Major GC?**
    *   A: Minor GC happens in the Young Generation (Eden/Survivor). Major GC happens in the Old Generation. Minor is frequent and fast; Major is infrequent and slow.
*   **Q: Can an object be "un-garbaged"?**
    *   A: Yes, inside the `finalize()` method, you could technically assign `this` to a static variable, making the object reachable again. However, `finalize()` is only called once; if the object becomes eligible again, `finalize()` won't run a second time.

---

### **8. Live Demo Summary**
In the video demo, the presenter creates a class and overrides the `finalize()` method with a print statement.
1.  They create objects inside a loop or nullify them.
2.  They call `System.gc()`.
3.  The console shows the `finalize()` output, proving the GC thread picked up the eligible objects.
4.  The demo highlights that `finalize()` only runs for objects that are truly eligible and that the GC thread is non-deterministic (it might take a moment to trigger).