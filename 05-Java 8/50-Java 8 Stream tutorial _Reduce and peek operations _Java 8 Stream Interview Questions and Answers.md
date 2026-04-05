These detailed notes are based on the "Java 8 Stream Tutorial" video by Code Decoder, covering advanced stream operations, the distinction between intermediate and terminal operations, and deep dives into `peek` and `reduce`.

---

### 1. Classification of Stream Operations
Stream operations are categorized into two main types: **Intermediate** and **Terminal**.

#### **Intermediate Operations**
These operations transform a stream into another stream. They are "lazy," meaning they don't execute until a terminal operation is invoked.
*   **Examples:** `filter`, `map`, `distinct`, `sorted`, `limit`, `skip`, `peek`.

#### **Terminal Operations**
These operations produce a result or a side effect (like printing). Once a terminal operation is performed, the stream is consumed and cannot be used again.
*   **Examples:** `forEach`, `toArray`, `collect`, `min`, `max`, `count`, `anyMatch`, `allMatch`, `noneMatch`, `findFirst`, `findAny`, `reduce`.

#### **Short-Circuit Operations**
These are a subset of operations that can stop the processing of the stream as soon as a condition is met, rather than processing the entire dataset.
*   **Short-circuit Terminal:** `anyMatch`, `allMatch`, `noneMatch`, `findFirst`, `findAny`.
*   **Short-circuit Intermediate:** `limit`.

---

### 2. Intermediate vs. Terminal Operations: Key Differences

| Feature | Intermediate Operations | Terminal Operations |
| :--- | :--- | :--- |
| **Return Type** | Always returns a new `Stream`. | Returns a non-stream result (primitive, collection, object, or `void`). |
| **Pipelining** | Can be chained together. | Cannot be chained; must be at the end of the pipeline. |
| **Execution** | Lazily loaded (deferred execution). | Eagerly loaded (triggers processing). |
| **Count** | A pipeline can have multiple. | A pipeline can have exactly one. |

---

### 3. Understanding Lazy Loading
A critical feature of Java 8 Streams is that intermediate operations are not executed until the terminal operation is called.

*   **The Proof:** If you have a stream with a `filter` or a `map` that contains a print statement (e.g., `e -> { System.out.println(e); return e; }`) but you **do not** add a terminal operation like `.collect()` or `.forEach()` at the end, **nothing will be printed**.
*   **Mechanism:** The stream "memorizes" the operations applied to it. Only when the terminal operation is reached does the stream engine iterate through the source and apply the memorized operations.

---

### 4. The `peek` Operation (Intermediate)
The `peek` method is primarily used for **debugging**.

*   **Functionality:** it returns a stream consisting of the elements of the current stream, additionally performing the provided action (a `Consumer`) on each element as elements are consumed from the resulting stream.
*   **Use Case:** If you have a complex pipeline (e.g., `filter -> map -> filter`) and the final output is not what you expect, you can insert `.peek()` between steps to see what the data looks like at that specific stage without interrupting the flow.
*   **Peek vs. Map:** 
    *   `map` is designed to transform the data (e.g., changing an Employee object to a String).
    *   `peek` is designed to "look" at the data (usually for logging) without changing it.

---

### 5. The `reduce` Operation (Terminal)
The `reduce` operation is used to combine elements of a stream into a **single summary value**.

*   **Concept:** It performs a "reduction" on the elements of the stream using an associative accumulation function.
*   **Example (Summing numbers):** Instead of using a `for` loop with a `sum` variable, you can use:
    ```java
    int sum = list.stream().reduce(0, (a, b) -> a + b);
    ```
*   **Parameters (`a, b`):**
    *   `a` (Accumulator): The partial result of the previous reduction step (or the initial identity value).
    *   `b` (Current Element): The next element from the stream.
*   **Return Type:** Usually returns an `Optional<T>` (unless an identity value is provided), which requires a `.get()` or handling to retrieve the value.

---

### 6. Interview Questions & Answers

**Q: Why are intermediate operations called "lazy"?**
**A:** Because they do not perform any processing on the elements of the source collection until a terminal operation is initiated. They just build a pipeline of instructions.

**Q: Can we have two terminal operations in a single stream pipeline?**
**A:** No. A stream pipeline can have any number of intermediate operations but only one terminal operation, which must be the final step.

**Q: What is the difference between `peek()` and `forEach()`?**
**A:** Both take a `Consumer`, but `peek()` is an intermediate operation that returns a Stream, allowing the pipeline to continue. `forEach()` is a terminal operation that returns `void` and terminates the pipeline.

**Q: How does `reduce()` work internally?**
**A:** It applies a binary operator to each element. The first argument of the operator is the result of the previous application, and the second argument is the current element from the stream. This continues until all elements are processed into one value.

**Q: When would you use `peek()`?**
**A:** `peek()` is best used during development and debugging to observe the state of elements as they pass through various stages of a stream pipeline.