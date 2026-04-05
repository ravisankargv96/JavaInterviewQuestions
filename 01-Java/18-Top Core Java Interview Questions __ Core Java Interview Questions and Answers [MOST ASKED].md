These notes provide a comprehensive summary of the core Java interview concepts discussed in the video, organized by topic for easy study and review.

---

### 1. Java Platform Architecture (JDK vs. JRE vs. JVM)
*   **JVM (Java Virtual Machine):** An abstract machine that provides the runtime environment in which Java bytecode can be executed. It is platform-dependent (different JVMs for Windows, Mac, Linux) but enables Java’s "Write Once, Run Anywhere" (WORA) capability.
*   **JRE (Java Runtime Environment):** The environment required to execute Java applications. It contains the JVM, core classes, and supporting libraries. It does not include development tools like compilers.
*   **JDK (Java Development Kit):** A full-featured software development kit for Java. It contains everything in the JRE plus development tools like the Java compiler (`javac`), debugger, and documentation tools.

### 2. Primary Features of Java
*   **Platform Independent:** Java code is compiled into bytecode, which runs on any machine with a JVM.
*   **Object-Oriented:** Everything in Java is treated as an object, focusing on data and methods.
*   **Robust:** Strong memory management, lack of pointers (to prevent unauthorized memory access), and built-in exception handling.
*   **Multithreaded:** Allows concurrent execution of two or more parts of a program for maximum CPU utilization.

### 3. Object-Oriented Programming (OOPs) Concepts
*   **Inheritance:** The process where one class acquires the properties and behaviors of another class. It promotes code reusability (uses the `extends` keyword).
*   **Polymorphism:** The ability of an object to take on many forms.
    *   *Compile-time (Static):* Method Overloading.
    *   *Runtime (Dynamic):* Method Overriding.
*   **Encapsulation:** Wrapping data (variables) and code (methods) together as a single unit. This is achieved by making fields `private` and providing public `getter` and `setter` methods.
*   **Abstraction:** Hiding internal implementation details and showing only the essential functionality to the user. This is achieved through Abstract Classes and Interfaces.

### 4. Constructors in Java
*   A constructor is a block of code similar to a method that is called when an instance of an object is created.
*   **Rules:** It must have the same name as the class and no explicit return type.
*   **Types:** 
    *   *Default Constructor:* Inserted by the Java compiler if no constructor is defined.
    *   *Parameterized Constructor:* Used to initialize objects with specific values.

### 5. The "static" Keyword
*   **Static Variables:** Belong to the class rather than instances; all objects share the same copy.
*   **Static Methods:** Can be called without creating an object of the class. They can only access static data.
*   **Static Blocks:** Used to initialize static data members; executed once when the class is loaded into memory.

### 6. String Handling
*   **Immutability:** Strings in Java are immutable, meaning once a String object is created, its value cannot be changed. Any modification creates a new String object.
*   **String Pool:** A special memory area in the Heap where the JVM stores string literals to improve memory efficiency.
*   **String vs. StringBuilder vs. StringBuffer:**
    *   *String:* Immutable.
    *   *StringBuffer:* Mutable and synchronized (thread-safe).
    *   *StringBuilder:* Mutable but not synchronized (faster, preferred for single-threaded environments).

### 7. Exception Handling
*   **Checked Exceptions:** Checked at compile-time (e.g., `IOException`, `SQLException`).
*   **Unchecked Exceptions:** Occur at runtime; usually due to programming logic errors (e.g., `NullPointerException`, `ArrayIndexOutOfBoundsException`).
*   **Keywords:**
    *   `try`: Block of code to monitor for exceptions.
    *   `catch`: Block of code to handle the exception.
    *   `finally`: Block of code that always executes (used for closing resources like database connections).
    *   `throw`: Used to explicitly throw an exception.
    *   `throws`: Used in method signatures to declare exceptions.

### 8. Difference Between "final", "finally", and "finalize"
*   **final:** A modifier used for classes (cannot be inherited), methods (cannot be overridden), and variables (value cannot be changed).
*   **finally:** A block used in exception handling that executes regardless of whether an exception is thrown or caught.
*   **finalize:** A method called by the Garbage Collector just before an object is destroyed to perform cleanup operations (Note: deprecated in newer Java versions).

### 9. Method Overloading vs. Method Overriding
*   **Overloading:** Multiple methods in the same class have the same name but different parameters (compile-time polymorphism).
*   **Overriding:** A subclass provides a specific implementation for a method already defined in its parent class (runtime polymorphism). Requires the same method signature.

### 10. Java Collections Framework Basics
*   **List:** An ordered collection that allows duplicate elements (e.g., `ArrayList`, `LinkedList`).
*   **Set:** A collection that does not allow duplicate elements (e.g., `HashSet`, `LinkedHashSet`).
*   **Map:** An object that maps keys to values; keys must be unique (e.g., `HashMap`, `TreeMap`).
*   **ArrayList vs. Vector:** `ArrayList` is non-synchronized (faster), while `Vector` is synchronized (thread-safe but slower).

### 11. Interface vs. Abstract Class
*   **Abstract Class:** Can have both abstract (without body) and concrete (with body) methods. Can have instance variables and constructors. Supports single inheritance.
*   **Interface:** Traditionally only allowed abstract methods (though Java 8+ allows default and static methods). Used to achieve 100% abstraction and multiple inheritance. All variables are implicitly `public static final`.