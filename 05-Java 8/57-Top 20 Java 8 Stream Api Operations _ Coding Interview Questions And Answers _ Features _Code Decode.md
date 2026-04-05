These detailed notes cover the core concepts and the top 20 operations of the Java 8 Stream API as discussed in the "Code Decode" tutorial.

---

### **1. Introduction to Java 8 Streams**
*   **Definition:** A Stream is a sequence of elements supporting sequential and parallel aggregate operations.
*   **Key Characteristics:**
    *   **Not a Data Structure:** It doesn't store data; it carries data from a source (Collection, Array, I/O channel).
    *   **Functional in Nature:** Operations performed on a stream do not modify the source.
    *   **Lazy Evaluation:** Intermediate operations are not executed until a terminal operation is invoked.
    *   **Pipelining:** Most stream operations return a stream themselves, allowing them to be chained.

---

### **2. Types of Stream Operations**
The Stream API distinguishes between two types of operations:
1.  **Intermediate Operations:** Transform a stream into another stream. They are lazy.
2.  **Terminal Operations:** Produce a result or a side-effect (like a list, a single value, or printing). Once a terminal operation is performed, the stream is consumed and cannot be used again.

---

### **3. Top 20 Stream API Operations**

#### **I. Intermediate Operations**

**1. filter(Predicate)**
Used to filter elements based on a boolean condition.
*   *Example:* `list.stream().filter(n -> n % 2 == 0)` (Filters even numbers).

**2. map(Function)**
Transforms each element in the stream into another object.
*   *Example:* `list.stream().map(String::toUpperCase)` (Converts strings to uppercase).

**3. flatMap(Function)**
Used to flatten a stream of collections into a single stream of elements (one-to-many mapping).
*   *Example:* Turning a `List<List<Integer>>` into a single `Stream<Integer>`.

**4. distinct()**
Returns a stream consisting of unique elements (removes duplicates) based on `equals()`.

**5. sorted()**
Sorts the elements in natural order.

**6. sorted(Comparator)**
Sorts the elements based on a custom comparator.
*   *Example:* `list.stream().sorted(Comparator.reverseOrder())`.

**7. peek(Consumer)**
Primarily used for debugging. It performs an action on each element as they flow through the pipeline without modifying them.

**8. limit(long n)**
A short-circuiting operation that truncates the stream to contain no more than `n` elements.

**9. skip(long n)**
Discards the first `n` elements of the stream and returns the remaining elements.

---

#### **II. Terminal Operations**

**10. forEach(Consumer)**
Iterates over each element of the stream and performs an action (e.g., printing).

**11. toArray()**
Converts the elements of the stream into an array.

**12. reduce(BinaryOperator)**
Combines elements of the stream into a single summary result (e.g., sum or concatenation).
*   *Example:* `list.stream().reduce(0, (a, b) -> a + b)`.

**13. collect(Collector)**
Converts the stream into a different structure like a List, Set, or Map.
*   *Example:* `stream.collect(Collectors.toList())`.

**14. min(Comparator)**
Returns the minimum element of the stream wrapped in an `Optional` based on the provided comparator.

**15. max(Comparator)**
Returns the maximum element of the stream wrapped in an `Optional`.

**16. count()**
Returns the total number of elements in the stream as a `long`.

**17. anyMatch(Predicate)**
Returns `true` if **at least one** element matches the given predicate. (Short-circuiting).

**18. allMatch(Predicate)**
Returns `true` if **all** elements match the given predicate. (Short-circuiting).

**19. noneMatch(Predicate)**
Returns `true` if **no** elements match the given predicate.

**20. findFirst()**
Returns an `Optional` containing the first element of the stream.

**Bonus: findAny()**
Returns an `Optional` containing any element from the stream (useful in parallel streams where the first element isn't guaranteed).

---

### **4. Key Interview Questions & Coding Scenarios**

*   **Difference between `map()` and `flatMap()`:**
    *   `map()` is for transformation (1 to 1). If you use `map()` on a list of lists, you get a stream of lists.
    *   `flatMap()` is for transformation and flattening (1 to many). It returns a stream of elements.

*   **Short-circuiting Operations:**
    *   Operations like `limit()`, `findFirst()`, and `anyMatch()` do not need to process the entire stream to return a result. This improves performance significantly on large datasets.

*   **Stateful vs. Stateless Operations:**
    *   **Stateless:** `filter` and `map` process elements independently.
    *   **Stateful:** `sorted` and `distinct` require knowledge of all elements before producing a result.

*   **Common Scenario: Find the second highest number in a list.**
    *   *Logic:* `list.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();`

*   **Common Scenario: Grouping elements.**
    *   *Logic:* Use `Collectors.groupingBy()` within the `collect()` method to group data (e.g., grouping employees by department).

---

### **5. Summary Table for Quick Revision**

| Operation | Type | Input | Purpose |
| :--- | :--- | :--- | :--- |
| **filter** | Intermediate | Predicate | Filtering data |
| **map** | Intermediate | Function | Transforming data |
| **flatMap** | Intermediate | Function | Flattening nested collections |
| **distinct** | Intermediate | None | Removing duplicates |
| **limit/skip** | Intermediate | long | Truncating/Skipping elements |
| **forEach** | Terminal | Consumer | Iterating |
| **collect** | Terminal | Collector | Packaging results into a List/Set/Map |
| **reduce** | Terminal | BinaryOperator | Aggregating into a single value |
| **matches** | Terminal | Predicate | Validation (anyMatch, allMatch) |