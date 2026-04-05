These notes cover the key concepts discussed in the "Code Decode" video regarding Java 8 Streams, focusing specifically on the distinction between intermediate and terminal operations.

---

### 1. Introduction to Java 8 Streams
A **Stream** in Java 8 is a sequence of objects that supports various methods which can be pipelined to produce a desired result. It is not a data structure; instead, it takes input from Collections, Arrays, or I/O channels.

**The Stream Pipeline consists of three parts:**
1.  **Source:** (e.g., a List, Set, or Array).
2.  **Intermediate Operations:** Transform the stream into another stream.
3.  **Terminal Operation:** Produces a result or a side effect and ends the stream.

---

### 2. Intermediate Operations
Intermediate operations process the elements of a stream and return a **new stream** as a result.

*   **Lazy Evaluation:** This is the most important characteristic. Intermediate operations are not executed until a terminal operation is invoked. They are simply "recorded" as a set of instructions to be performed.
*   **Chaining:** Since they return a stream, multiple intermediate operations can be linked together (pipelined).
*   **Transformation:** They are used to filter, map, or sort data.

**Common Intermediate Operations:**
*   **`filter(Predicate)`:** Filters elements based on a condition (e.g., finding all numbers greater than 10).
*   **`map(Function)`:** Transforms each element into another object (e.g., converting a list of strings to uppercase).
*   **`sorted()`:** Sorts the elements in natural or custom order.
*   **`distinct()`:** Removes duplicate elements based on the `equals()` method.
*   **`limit(long n)`:** Truncates the stream to contain no more than *n* elements.
*   **`skip(long n)`:** Discards the first *n* elements of the stream.

---

### 3. Terminal Operations
Terminal operations mark the end of the stream pipeline.

*   **Eager Evaluation:** When a terminal operation is called, the stream pipeline is processed, and the intermediate operations are actually executed.
*   **Result Type:** They return a non-stream result, such as a primitive value (int, long), a collection (List, Map), or nothing (`void`).
*   **Consumption:** Once a terminal operation is performed, the stream is considered "consumed" and cannot be reused. Attempting to use the stream again will result in an `IllegalStateException`.

**Common Terminal Operations:**
*   **`collect(Collector)`:** Converts the stream into a different structure, like a `List`, `Set`, or `Map`.
*   **`forEach(Consumer)`:** Iterates over each element (often used for printing).
*   **`count()`:** Returns the total number of elements in the stream as a `long`.
*   **`reduce(BinaryOperator)`:** Combines stream elements into a single summary result (e.g., summing all integers).
*   **`min()` / `max()`:** Finds the minimum or maximum element based on a comparator.
*   **`anyMatch()` / `allMatch()` / `noneMatch()`:** Returns a boolean based on whether elements match a specific condition.

---

### 4. Key Differences: Intermediate vs. Terminal

| Feature | Intermediate Operations | Terminal Operations |
| :--- | :--- | :--- |
| **Return Type** | Always returns a new `Stream`. | Returns a result (List, Integer, etc.) or `void`. |
| **Laziness** | Lazy (executed only when terminal starts). | Eager (triggers the execution). |
| **Pipeline Position** | Can be followed by other operations. | Must be the final operation in the chain. |
| **Purpose** | Used for transformation/filtering. | Used to produce a final result or side effect. |
| **Multiple Uses** | Can have multiple in one pipeline. | Only one per pipeline. |

---

### 5. Short-Circuiting Operations
The video highlights a specific category of operations that can stop the processing of the stream as soon as a condition is met, improving performance.

*   **Short-circuit Intermediate:** `limit()` (it stops processing once the limit is reached).
*   **Short-circuit Terminal:** `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()`, `noneMatch()`. These do not require processing the entire collection to return a result.

---

### 6. Example Walkthrough
Consider a list of names where we want to find the first two names that start with "A" and convert them to uppercase.

```java
List<String> names = Arrays.asList("Amit", "Anil", "Ajay", "Vijay");

List<String> result = names.stream()
    .filter(s -> s.startsWith("A")) // Intermediate (Lazy)
    .map(String::toUpperCase)      // Intermediate (Lazy)
    .limit(2)                      // Intermediate (Short-circuit)
    .collect(Collectors.toList()); // Terminal (Triggers execution)
```

**Execution Flow:**
1. The stream starts.
2. "Amit" is checked by `filter` (True), mapped to "AMIT", and stored.
3. "Anil" is checked by `filter` (True), mapped to "ANIL", and stored.
4. Because of `limit(2)`, the stream **stops immediately**.
5. It does **not** check "Ajay" or "Vijay". This demonstrates the efficiency of lazy evaluation and short-circuiting.

---

### 7. Interview Summary Tips
*   If an interviewer asks why streams are efficient, mention **Lazy Evaluation**.
*   If asked how to tell the difference between operations, check the **return type**: if it returns a `Stream`, it is intermediate.
*   Always remember that a stream is **not reusable** once a terminal operation is called.