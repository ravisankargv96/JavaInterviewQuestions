These notes provide a comprehensive summary of the "Parallel Streams" tutorial by Code Decode, covering the concepts, internal mechanisms, and common interview questions regarding Java 8 Stream processing.

---

### **1. Introduction to Parallel Streams**
In Java 8, streams can be executed either sequentially or in parallel.
*   **Sequential Stream:** Processes elements one by one in a single thread. The order of processing is predictable.
*   **Parallel Stream:** Leverages multi-core processors by dividing the workload into multiple chunks and processing them across different threads simultaneously.

### **2. How to Create Parallel Streams**
There are two primary ways to create a parallel stream:
1.  **Using `parallelStream()` on a Collection:**
    ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
    numbers.parallelStream().forEach(System.out::println);
    ```
2.  **Converting a Sequential Stream to Parallel:**
    ```java
    Stream.of(1, 2, 3).parallel().forEach(System.out::println);
    ```

### **3. Internal Mechanism: The Fork/Join Framework**
Parallel streams are built on top of the **Fork/Join Framework** (introduced in Java 7).
*   **Splitting (Fork):** The data source is split into smaller sub-tasks. This is managed by a `Spliterator`.
*   **Execution:** These sub-tasks are assigned to different threads in the **ForkJoinPool.commonPool()**.
*   **Combining (Join):** Once all sub-tasks are completed, the results are merged back together to produce the final output.

### **4. Sequential vs. Parallel Stream Performance**
A common misconception is that parallel streams are always faster. This is not true.
*   **Overhead:** Parallelism involves overhead for splitting the source, creating threads, managing the pool, and merging results.
*   **The NQ Model:**
    *   **N:** Number of data elements.
    *   **Q:** Cost of performing the operation per element.
    *   If **N * Q** is small, a sequential stream is usually faster. If **N * Q** is very large, a parallel stream will significantly improve performance.

###  **5. When NOT to Use Parallel Streams**
You should avoid parallel streams in the following scenarios:
*   **Small Data Sets:** The overhead of management outweighs the execution time.
*   **Stateful Lambda Expressions:** If your stream logic relies on a shared state or a variable outside the stream, parallelism will lead to race conditions.
*   **Order-Sensitive Operations:** Operations like `findFirst()`, `limit()`, or `skip()` are expensive in parallel because they require coordination between threads to maintain order.
*   **I/O Intensive Tasks:** Parallel streams are designed for CPU-intensive tasks. Blocking I/O operations (like database calls or network requests) can bottleneck the common ForkJoinPool and degrade the entire application's performance.
*   **Boxing/Unboxing:** Using `Stream<Integer>` instead of `IntStream` causes significant performance hits due to boxing costs.

### **6. Key Interview Questions and Answers**

**Q1: Which thread pool is used by Parallel Streams?**
**A:** They use the `ForkJoinPool.commonPool()`. By default, the number of threads is equal to the number of processors available to the JVM minus one.

**Q2: Is the order of execution guaranteed in a Parallel Stream?**
**A:** No. Elements are processed concurrently by different threads, so the order is non-deterministic. If you must maintain order, use `forEachOrdered()` instead of `forEach()`, though this reduces the performance benefit.

**Q3: Can we increase the number of threads in a Parallel Stream?**
**A:** Yes, by setting a system property: `System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");`. However, this affects the entire application because the pool is shared.

**Q4: What is the difference between `stream().parallel()` and `parallelStream()`?**
**A:** `parallelStream()` is a method available on the `Collection` interface. `stream().parallel()` is a transformation on an existing stream. Effectively, they both result in a parallel stream.

**Q5: How does a Parallel Stream handle a `reduce` operation?**
**A:** The reduction happens in three steps:
1.  Data is partitioned.
2.  Each partition is reduced by a thread (Accumulator).
3.  The partial results are combined (Combiner).

### **7. Summary Checklist for Using Parallel Streams**
*   Is the data set large enough?
*   Are the operations independent (stateless)?
*   Is the task CPU-intensive?
*   Is the data source easy to split (e.g., `ArrayList` is better than `LinkedList` because `ArrayList` supports random access for splitting)?