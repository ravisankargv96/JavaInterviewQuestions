These notes cover the key concepts, interview questions, and technical details discussed in the "Top Java 8 Streams API Interview Questions" video by Code Decode.

### 1. What is Java 8 Stream API?
*   **Definition:** A Stream is a sequence of objects that supports various methods which can be pipelined to produce a desired result.
*   **Purpose:** It is used to process collections of objects in a functional style.
*   **Key Characteristic:** A stream is not a data structure; it does not store data. Instead, it takes input from Collections, Arrays, or I/O channels.

### 2. Difference Between Collections and Streams
*   **Storage:** Collections are used to store data in a particular data structure (like List or Set). Streams do not store data; they are computational in nature.
*   **Modification:** Collections allow you to add or remove elements. Streams do not modify the actual source data; they only transform it and return a new result.
*   **Iteration:** Collections require **External Iteration** (using loops like `for-each` or `Iterator`). Streams use **Internal Iteration**, where the library controls the iteration process.
*   **Laziness:** Streams are lazy-evaluated (intermediate operations are not performed until a terminal operation is invoked). Collections are eagerly constructed.
*   **Traversability:** Collections can be traversed multiple times. A Stream can be traversed only once; if you need to traverse it again, you must create a new stream.

### 3. Intermediate vs. Terminal Operations
The Stream API works as a pipeline consisting of a source, followed by zero or more intermediate operations, and one terminal operation.

#### Intermediate Operations
*   **Function:** They transform a stream into another stream.
*   **Evaluation:** They are lazy. They are not executed until a terminal operation is called.
*   **Examples:**
    *   `filter(Predicate)`: Filters elements based on a condition.
    *   `map(Function)`: Transforms each element into another object.
    *   `sorted()`: Sorts the elements.
    *   `distinct()`: Removes duplicates.
    *   `limit(n)`: Truncates the stream to a specific size.

#### Terminal Operations
*   **Function:** They produce a result (non-stream) or a side-effect.
*   **Evaluation:** Once a terminal operation is performed, the stream is consumed and cannot be used again.
*   **Examples:**
    *   `collect()`: Converts the stream into a collection (List, Set, Map).
    *   `forEach()`: Iterates over each element.
    *   `reduce()`: Combines elements into a single value.
    *   `count()`: Returns the number of elements.
    *   `min()` / `max()`: Returns the minimum or maximum element.

### 4. Parallel Streams
Parallel streams are used to execute operations concurrently to leverage multi-core processors and improve performance for large datasets.

*   **Creation:** Use `list.parallelStream()` or `stream.parallel()`.
*   **Internal Mechanism:** They use the **Fork-Join Framework**. The source is split into multiple chunks using a **Spliterator**, processed in parallel threads, and then the results are combined.
*   **When to use:** Use them when you have a massive dataset and the operations are independent of each other.
*   **When to avoid:**
    *   Small datasets (overhead of managing threads exceeds the gain).
    *   Operations that depend on order.
    *   Stateful operations where thread safety is a concern.

### 5. Common Interview Coding Scenarios
*   **Filtering and Collecting:** How to filter a list of integers and collect only even numbers into a new list.
    *   `list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());`
*   **Mapping:** How to convert a list of strings to uppercase.
    *   `list.stream().map(String::toUpperCase).collect(Collectors.toList());`
*   **Counting:** Count the number of strings in a list that start with a specific character.
    *   `list.stream().filter(s -> s.startsWith("A")).count();`

### 6. Key Advantages of Using Streams
*   **Conciseness:** Reduces boilerplate code significantly.
*   **Readability:** The code looks cleaner and more declarative.
*   **Parallelism:** Easy to switch to parallel processing without writing complex multi-threading code.
*   **Pipeline efficiency:** Because of lazy evaluation, multiple intermediate operations can be fused into a single pass over the data.

### 7. Important Terminology
*   **Short-circuiting:** Some operations (like `findFirst`, `anyMatch`, or `limit`) do not need to process the entire stream to produce a result.
*   **Stateless vs. Stateful:**
    *   **Stateless:** Operations like `filter` and `map` don't need to know about other elements.
    *   **Stateful:** Operations like `sorted` or `distinct` require the entire stream's state to be known before proceeding to the next step.