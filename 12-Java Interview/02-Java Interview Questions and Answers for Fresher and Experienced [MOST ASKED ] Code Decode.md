These detailed notes cover the core Java interview concepts discussed in the "Code Decode" video, structured to help both freshers and experienced professionals.

---

### **1. Java Fundamentals: JDK, JRE, and JVM**
*   **JVM (Java Virtual Machine):** It is the heart of Java. It is an abstract machine that provides a runtime environment in which Java bytecode can be executed. It is platform-dependent (different JVMs for Windows, Linux, Mac).
*   **JRE (Java Runtime Environment):** This is the implementation of the JVM. It contains the JVM plus the set of libraries and other files that Java programs use at runtime. It does not contain development tools like compilers.
*   **JDK (Java Development Kit):** It is a full-featured software development kit for Java. It contains the JRE plus development tools like the compiler (`javac`), debugger, and documentation tools.

### **2. Platform Independence in Java**
*   Java is "Write Once, Run Anywhere" (WORA).
*   When you compile Java code, it isn't converted into machine code for a specific processor; instead, it is converted into **Bytecode** (.class file).
*   The JVM on any operating system can understand this bytecode and convert it into the specific machine code of that OS. While the JVM is platform-dependent, the bytecode is platform-independent.

### **3. Object-Oriented Programming (OOP) Concepts**
The video emphasizes the four pillars:
*   **Abstraction:** Hiding internal implementation details and showing only the functionality (using Abstract classes and Interfaces).
*   **Encapsulation:** Wrapping data (variables) and code (methods) together as a single unit. It is achieved by making variables `private` and providing `public` getters/setters.
*   **Inheritance:** The mechanism where one class acquires the properties and behaviors of a parent class. Java supports single, multilevel, and hierarchical inheritance, but **not multiple inheritance** with classes (to avoid the Diamond Problem).
*   **Polymorphism:**
    *   **Compile-time (Static):** Method Overloading (same method name, different parameters in the same class).
    *   **Runtime (Dynamic):** Method Overriding (same method signature in parent and child classes).

### **4. String Handling**
*   **Immutability:** Strings in Java are immutable, meaning once created, their value cannot be changed. If you try to alter a string, a new object is created in the **String Constant Pool (SCP)**.
*   **Why Immutable?** For security, caching (String Pool), and thread safety.
*   **String vs. StringBuilder vs. StringBuffer:**
    *   **String:** Immutable.
    *   **StringBuffer:** Mutable and Synchronized (Thread-safe, but slower).
    *   **StringBuilder:** Mutable and Not Synchronized (Not thread-safe, but faster).
*   **`==` vs `.equals()`:** `==` compares memory addresses (references), while `.equals()` compares the actual content of the strings.

### **5. Exception Handling**
*   **Checked Exceptions:** Checked at compile-time (e.g., `IOException`, `SQLException`).
*   **Unchecked Exceptions:** Occur at runtime; usually due to programming logic (e.g., `NullPointerException`, `ArithmeticException`).
*   **Final vs. Finally vs. Finalize:**
    *   **`final`:** Keyword used to restrict classes, methods, and variables (cannot be inherited/overridden/changed).
    *   **`finally`:** A block used with try-catch to execute important code (like closing resources) regardless of whether an exception is thrown.
    *   **`finalize`:** A method called by the Garbage Collector just before an object is destroyed.

### **6. Collections Framework**
*   **List vs. Set:**
    *   **List:** Allows duplicate elements and maintains insertion order (ArrayList, LinkedList).
    *   **Set:** Does not allow duplicates and does not guarantee order (HashSet, LinkedHashSet).
*   **ArrayList vs. LinkedList:**
    *   **ArrayList:** Uses a dynamic array. Fast for data retrieval (`O(1)`) but slow for manipulation (shifting required).
    *   **LinkedList:** Uses a doubly linked list. Fast for manipulation (`O(1)`) but slow for retrieval (`O(n)`).
*   **HashMap Internals:** Works on the principle of **Hashing**.
    *   It uses `hashCode()` to find the bucket location and `equals()` to find the value in the bucket if there is a collision.
    *   **Java 8 Change:** When a bucket exceeds a certain threshold (8 elements), the linked list is converted into a Balanced Tree (TreeMap) to improve performance from `O(n)` to `O(log n)`.

### **7. Java 8 Features (Highly Asked)**
*   **Lambda Expressions:** Enables functional programming by treating functionality as a method argument.
*   **Functional Interfaces:** An interface with exactly one abstract method (e.g., `Runnable`, `Callable`). Can be annotated with `@FunctionalInterface`.
*   **Streams API:** Used for processing collections of objects in a functional style (e.g., `filter`, `map`, `reduce`).
*   **Default Methods:** Allows adding new methods to interfaces without breaking the classes that implement them.
*   **Optional Class:** A container object used to represent the existence or absence of a value, helping to avoid `NullPointerException`.

### **8. Multithreading**
*   **Creating a Thread:** By extending the `Thread` class or implementing the `Runnable` interface (Runnable is preferred as it allows extending other classes).
*   **`start()` vs. `run()`:** `start()` creates a new thread and then calls `run()`. Calling `run()` directly does not create a new thread; it executes in the current thread like a normal method.
*   **`wait()` vs. `sleep()`:**
    *   `wait()`: Defined in `Object` class, releases the lock, and is used for inter-thread communication.
    *   `sleep()`: Defined in `Thread` class, does not release the lock, and is used to pause execution for a specific time.

### **9. Interface vs. Abstract Class**
*   **Abstract Class:** Can have both abstract and concrete methods. Can have state (instance variables). A class can extend only one abstract class.
*   **Interface:** Traditionally only had abstract methods (though Java 8/9 added default, static, and private methods). Cannot have instance variables (only `public static final` constants). A class can implement multiple interfaces.