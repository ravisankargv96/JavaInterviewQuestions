These notes summarize the key concepts and technical answers provided in the "Top 5 Tricky Java 8 Interview Questions" video by Code Decode.

---

### **1. Difference Between `map()` and `flatMap()`**
This is one of the most common Java 8 interview questions, focusing on how data is transformed within a Stream.

*   **`map()` (One-to-One Mapping):**
    *   It takes a Function as an argument and applies it to each element of the stream.
    *   The output is a stream of values.
    *   **Use Case:** When you want to transform an object into another object (e.g., converting a list of Strings to Uppercase or extracting a specific field from an object).
*   **`flatMap()` (One-to-Many Mapping + Flattening):**
    *   It performs a mapping operation followed by a "flattening" operation.
    *   It takes a stream of streams and merges them into a single, flat stream.
    *   **Use Case:** When an object contains a collection (e.g., a `Student` object has a `List<String> phoneNumbers`). Using `map()` would return `Stream<List<String>>`, but `flatMap()` returns `Stream<String>`, putting all phone numbers into a single list.

---

### **2. Intermediate vs. Terminal Operations in Streams**
Java 8 Streams are designed to be efficient through "Lazy Evaluation."

*   **Intermediate Operations:**
    *   These operations return a new Stream.
    *   They are **lazy**; they do not execute until a terminal operation is invoked.
    *   **Examples:** `filter()`, `map()`, `flatMap()`, `distinct()`, `sorted()`, `limit()`.
*   **Terminal Operations:**
    *   These operations trigger the processing of the stream pipeline.
    *   Once a terminal operation is executed, the stream is consumed and can no longer be used.
    *   They return a non-stream result (a primitive, a Collection, or void).
    *   **Examples:** `collect()`, `forEach()`, `reduce()`, `count()`, `anyMatch()`, `findFirst()`.

---

### **3. The `Optional` Class**
The `Optional` class was introduced to handle `NullPointerException` (NPE) more gracefully and to provide a clearer API for methods that might not return a value.

*   **Purpose:** It is a container object which may or may not contain a non-null value.
*   **Key Methods:**
    *   `Optional.of(val)`: Returns an Optional with the specified value. Throws NPE if the value is null.
    *   `Optional.ofNullable(val)`: Returns an Optional with the value, or an empty Optional if the value is null.
    *   `isPresent()`: Returns true if there is a value.
    *   `ifPresent(Consumer)`: Executes a block of code if the value is present.
    *   `orElse(defaultVal)`: Returns the value if present, otherwise returns the provided default value.

---

### **4. Default and Static Methods in Interfaces**
Before Java 8, interfaces could only have abstract methods. Java 8 introduced `default` and `static` methods to allow "Interface Evolution."

*   **Default Methods:**
    *   Defined using the `default` keyword.
    *   They provide a default implementation, meaning existing classes implementing the interface don't have to provide their own implementation (prevents breaking legacy code).
    *   **The Diamond Problem:** If a class implements two interfaces that have the same default method, the compiler forces the developer to override the method and specify which one to use (e.g., `InterfaceName.super.methodName()`).
*   **Static Methods:**
    *   Defined using the `static` keyword.
    *   These belong to the Interface class and cannot be overridden by implementing classes.
    *   They are used for utility methods relevant to the interface.

---

### **5. Functional Interfaces and Lambda Expressions**
These are the foundation of Functional Programming in Java.

*   **Functional Interface:**
    *   An interface that contains **exactly one abstract method** (also known as the SAM - Single Abstract Method principle).
    *   It can have any number of `default` or `static` methods.
    *   Annotated with `@FunctionalInterface` (optional but recommended for compiler-level checking).
    *   **Standard Examples:** `Predicate<T>` (returns boolean), `Function<T,R>` (transforms input), `Consumer<T>` (consumes input, returns nothing), `Supplier<T>` (returns value, takes no input).
*   **Lambda Expressions:**
    *   A concise way to represent an instance of a functional interface.
    *   It provides the implementation of the abstract method directly in-line without needing an anonymous inner class.
    *   **Syntax:** `(parameters) -> { body }`

---

### **Summary Table for Quick Revision**

| Feature | Key Takeaway |
| :--- | :--- |
| **Map** | Transforms T to R (1:1). |
| **FlatMap** | Transforms T to Stream<R> and flattens (1:Many). |
| **Lazy Evaluation** | Intermediate operations only run when a Terminal operation is called. |
| **Optional** | A wrapper to prevent `NullPointerException`. |
| **Default Methods** | Allows adding new methods to interfaces without breaking implementing classes. |
| **SAM** | Single Abstract Method; the rule for Functional Interfaces. |