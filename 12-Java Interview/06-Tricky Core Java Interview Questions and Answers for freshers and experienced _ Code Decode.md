These detailed notes cover the core Java interview questions and concepts discussed in the "Code Decode" video titled **"Tricky Core Java Interview Questions and Answers for freshers and experienced."**

---

### 1. Method Overloading with Null Arguments
This is a classic "trick" question regarding how the Java compiler resolves overloaded methods when `null` is passed as an argument.

*   **Scenario A:** You have two methods: `print(Object o)` and `print(String s)`. If you call `print(null)`, which one is executed?
    *   **Answer:** The `print(String s)` method is called.
    *   **Reason:** Java always looks for the **most specific** type. Since `String` is a subclass of `Object`, `String` is more specific than `Object`.
*   **Scenario B:** You have `print(String s)` and `print(StringBuffer sb)`. If you call `print(null)`, what happens?
    *   **Answer:** A **Compile-time error** (Ambiguity).
    *   **Reason:** Both `String` and `StringBuffer` are at the same level in the inheritance hierarchy (they are siblings, not parent-child). The compiler cannot decide which one is "more specific," resulting in an ambiguous method call error.

### 2. String Immutability
Interviewers often ask why Strings are immutable and how they work in memory.

*   **Why Immutability?**
    1.  **String Pool:** Java uses a String Constant Pool (SCP) to save memory. If Strings were mutable, changing the value of one reference would inadvertently change it for all other references pointing to that same literal.
    2.  **Security:** Strings are used for sensitive data like Database URLs, usernames, and passwords. If they were mutable, an untrusted piece of code could change the value after a security check has passed.
    3.  **Thread Safety:** Since they cannot be changed, they are inherently thread-safe and don't require synchronization.
    4.  **Hashcode Caching:** The hashcode of a String is cached when it is created. This makes Strings very fast when used as keys in a `HashMap`.

### 3. Creating a Custom Immutable Class
To create a class that is truly immutable (like `String`), follow these rules:
1.  **Final Class:** Declare the class as `final` so it cannot be extended.
2.  **Private Final Fields:** Make all fields `private` and `final`.
3.  **No Setters:** Do not provide any setter methods.
4.  **Constructor Initialization:** Initialize all fields via a parameterized constructor.
5.  **Deep Copy for Mutable Fields:** If the class contains a reference to a mutable object (like a `Date` or a `List`), do not return the actual object in the getter. Instead, return a **deep copy** (a new instance with the same values) to prevent the caller from modifying the internal state.

### 4. String Literal vs. `new String()`
*   **`String s1 = "Code";`**
    *   This creates an object in the **String Constant Pool (SCP)**.
    *   If "Code" already exists in the pool, `s1` will simply point to the existing object (Memory optimization).
*   **`String s2 = new String("Code");`**
    *   This forces Java to create a **new object in the Heap memory**, regardless of whether "Code" already exists in the SCP.
    *   Technically, this statement may create two objects: one in the Heap and one in the SCP (if it isn't already there).

### 5. Method Overriding: Static and Private Methods
*   **Can we override a Static method?**
    *   **No.** If you define a static method in a subclass with the same signature, it is called **Method Hiding**, not Overriding. Static methods are resolved at compile-time (Static Binding), whereas overriding depends on the actual object at runtime (Dynamic Binding).
*   **Can we override a Private method?**
    *   **No.** Private methods are not visible to the subclass. If you create a method with the same name in the subclass, it is considered a completely new, independent method.

### 6. Difference between `final`, `finally`, and `finalize`
*   **`final` (Keyword):**
    *   Used for variables (to make them constants), methods (to prevent overriding), and classes (to prevent inheritance).
*   **`finally` (Block):**
    *   A block used in exception handling (`try-catch-finally`). It **always executes** (regardless of whether an exception is thrown or caught) to perform cleanup tasks like closing file streams or database connections.
*   **`finalize` (Method):**
    *   A method in the `Object` class called by the Garbage Collector just before an object is destroyed. (Note: It is deprecated in newer Java versions).

### 7. Java 8: `map()` vs. `flatMap()`
This is a frequent question regarding the Stream API.
*   **`map()`:**
    *   Used for **Transformation**.
    *   It takes a Stream and produces a Stream of the same size, where each element is transformed (e.g., converting a list of Strings to uppercase).
    *   Mapping logic: `1 to 1`.
*   **`flatMap()`:**
    *   Used for **Transformation + Flattening**.
    *   It is used when you have a Stream of Collections (e.g., `Stream<List<String>>`). `flatMap` "flattens" the inner lists into a single `Stream<String>`.
    *   Mapping logic: `1 to Many`.

### 8. The `Optional` Class (Java 8)
*   **Purpose:** To deal with `NullPointerException` (NPE) in a more elegant way.
*   Instead of returning `null` from a method (which might cause a crash if the caller doesn't check for it), you return an `Optional` object.
*   It forces the developer to acknowledge the possibility of a missing value using methods like `.isPresent()`, `.ifPresent()`, or `.orElse()`.

### 9. Can we run a Java program without a `main()` method?
*   In older versions of Java (prior to Java 7), you could use a **static block** to print output, and the program would execute that block and then exit.
*   In modern Java (Java 7 and later), the JVM explicitly looks for the `public static void main(String[] args)` method. Without it, the program will throw a `NoSuchMethodError: main`.

### 10. `try-with-resources` (Java 7)
*   Introduced to simplify resource management. Any class that implements the `AutoCloseable` interface can be used inside the `try(...)` parentheses.
*   **Benefit:** Java automatically closes the resource at the end of the block, eliminating the need for an explicit `finally` block to close resources.