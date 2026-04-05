These notes are based on the concepts and demonstrations provided in the video "Garbage Collection Interview Questions and Answers in Java - Part 2." This part focuses on the different types of garbage collectors, their algorithms, performance tuning, and practical demonstrations using JVM arguments.

---

### **1. Types of Garbage Collectors in Java**
Java provides several garbage collection (GC) implementations, each designed for specific use cases (throughput vs. latency).

*   **Serial GC:**
    *   Uses a single thread for garbage collection.
    *   It triggers "Stop the World" (STW) events, where all application threads are paused during GC.
    *   Best for small applications with small heaps (client-side machines).
    *   **JVM Argument:** `-XX:+UseSerialGC`

*   **Parallel GC (Throughput Collector):**
    *   The default collector in Java 8.
    *   Uses multiple threads for young generation garbage collection.
    *   It aims to maximize throughput by reducing the total time spent in GC relative to application time.
    *   **JVM Argument:** `-XX:+UseParallelGC`

*   **CMS (Concurrent Mark Sweep) GC:**
    *   Designed for low-latency applications.
    *   Most of its work (marking and sweeping) is done concurrently with application threads to minimize STW pauses.
    *   *Note:* It is deprecated in newer Java versions in favor of G1 and ZGC.
    *   **JVM Argument:** `-XX:+UseConcMarkSweepGC`

*   **G1 (Garbage First) GC:**
    *   The default collector since Java 9.
    *   It divides the heap into many small regions rather than two or three massive contiguous sections.
    *   It tracks which regions are "mostly garbage" and collects those first (hence "Garbage First").
    *   **JVM Argument:** `-XX:+UseG1GC`

*   **ZGC (Z Garbage Collector):**
    *   A scalable, low-latency collector introduced in more recent Java versions.
    *   Designed to handle heaps ranging from a few hundred MBs to many Terabytes with pause times under 10ms.

---

### **2. Understanding GC Events: Minor, Major, and Full GC**

*   **Minor GC:**
    *   Occurs in the **Young Generation** (Eden space).
    *   Fast and frequent.
    *   Triggered when the Eden space is full.
*   **Major GC:**
    *   Occurs in the **Old Generation** (Tenured space).
    *   Slower than Minor GC because it involves a larger memory area.
*   **Full GC:**
    *   Cleans the entire heap (both Young and Old generations).
    *   Causes the longest STW pauses and can impact application performance significantly if it happens frequently.

---

### **3. Key Interview Questions and Answers**

**Q: What is the difference between Throughput and Latency in the context of GC?**
*   **Throughput:** The percentage of total time spent executing the application vs. time spent on GC. High throughput is vital for batch processing jobs.
*   **Latency:** The time it takes for a single GC event to complete (pause time). Low latency is vital for interactive applications (like web servers).

**Q: What is the "Humongous" region in G1 GC?**
*   In G1, if an object occupies more than 50% of a region's capacity, it is considered a "Humongous Object." These objects are allocated in special Humongous regions within the heap to avoid frequent copying during GC.

**Q: How do you choose the right Garbage Collector?**
*   If the application is small/single-processor: **Serial GC**.
*   If peak performance/throughput is the priority: **Parallel GC**.
*   If response time/low latency is the priority: **G1 GC** or **ZGC**.

**Q: What are the phases of the CMS Collector?**
1.  **Initial Mark:** (STW) Marks objects directly reachable from roots.
2.  **Concurrent Mark:** (Concurrent) Traverses the object graph.
3.  **Remark:** (STW) Finds objects missed during concurrent mark.
4.  **Concurrent Sweep:** (Concurrent) Collects unreachable objects.

---

### **4. Practical Demo: Monitoring and Configuration**

The video demonstrates how to monitor GC behavior using the command line and JVM tools.

#### **JVM Arguments for Logging:**
To see what the GC is doing in real-time, you can use:
*   ` -XX:+PrintGCDetails` (Older versions)
*   ` -Xlog:gc*` (Modern versions/Java 9+)

#### **Monitoring Tools:**
*   **jstat:** A command-line utility. Example: `jstat -gc <pid> 1000` (shows GC stats every 1 second).
*   **VisualVM:** A GUI tool to visualize heap memory, thread activity, and GC cycles.
*   **JConsole:** Used for monitoring JMX-compliant applications.

#### **Demo Scenario:**
1.  A Java program is run that creates many objects in a loop to trigger memory pressure.
2.  By changing the JVM argument (e.g., swapping `-XX:+UseSerialGC` for `-XX:+UseG1GC`), the video shows how the number of GC threads and the frequency of pauses change in the logs.
3.  The demo highlights that **System.gc()** is merely a "hint" to the JVM and does not guarantee immediate collection.

---

### **5. Best Practices for GC Tuning**
*   **Avoid Manual GC Calls:** Do not use `System.gc()`. It disrupts the JVM's optimized collection schedule.
*   **Set Heap Size Appropriately:** Use `-Xms` (initial heap) and `-Xmx` (max heap). Setting these to the same value can sometimes improve performance by preventing heap resizing.
*   **Analyze Logs:** Always use GC logs to identify memory leaks before attempting to tune parameters.
*   **Understand Object Life Cycle:** Most objects should die young. If many objects move to the Old Generation too quickly, you may need to increase the size of the Young Generation (`-XX:NewRatio`).