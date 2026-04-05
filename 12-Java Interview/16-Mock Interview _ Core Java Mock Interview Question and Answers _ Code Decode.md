These detailed notes are based on the Core Java mock interview conducted by **Code Decode** with a fresher candidate, Yash.

---

### **1. Java Fundamentals**

*   **Why Java?**
    *   It is an **Object-Oriented Programming (OOP)** language.
    *   Widely used in **Finance and Banking** due to its security features and multi-threading capabilities.
    *   Extensive library support, large developer community, and powerful frameworks like **Spring and Spring Boot** for microservices.
    *   Platform independence via Bytecode.
*   **Why is Java not a "Pure" Object-Oriented Language?**
    *   Java is not 100% pure because it supports **primitive data types** (e.g., `int`, `char`, `float`, `double`) which are not objects.
    *   **Wrapper Classes** (e.g., `Integer`, `Double`) are used to "objectify" these primitives when working with Collections.

### **2. Java Architecture: JDK, JRE, and JVM**

*   **JDK (Java Development Kit):** The full toolset used to develop Java applications. It includes the JRE, compilers (`javac`), and debuggers.
*   **JRE (Java Runtime Environment):** Provides the minimum requirements to *run* a Java application. It contains the JVM and supporting libraries.
*   **JVM (Java Virtual Machine):** The engine that executes Java Bytecode.
*   **JIT (Just-In-Time) Compiler:** Located inside the JVM/JRE. It improves performance by compiling frequently used blocks of Bytecode into machine code at runtime, rather than interpreting line-by-line.
*   **Scenario:** 
    *   To write and compile code: You need **JDK**.
    *   To simply run a `.class` file (provided by someone else): You only need **JRE**.

### **3. OOP (Object-Oriented Programming) Pillars**

1.  **Abstraction:** Hiding implementation details and showing only functionality (e.g., a banking UI). Achieved via **Abstract Classes** (0-100% abstraction) and **Interfaces** (historically 100%, but changed with Java 8).
2.  **Encapsulation:** Wrapping data (variables) and code (methods) into a single unit (Class). Uses access modifiers to protect data.
3.  **Inheritance:** One class acquiring properties of another ("is-a" relationship). Uses the `extends` keyword.
4.  **Polymorphism:** One entity taking many forms.
    *   **Static (Compile-time):** Method Overloading (same name, different parameters).
    *   **Dynamic (Runtime):** Method Overriding (child class provides specific implementation of a parent method).

### **4. Variable Types**

*   **Local Variables:** Defined inside a method; only accessible within that method.
*   **Instance (Global) Variables:** Defined in a class but outside methods; accessible by all methods in the class.
*   **Static Variables:** Defined with the `static` keyword; belong to the class rather than an instance. Can be accessed without creating an object.

### **5. SOLID Principles**

*   **S (Single Responsibility):** A class should have only one reason to change.
*   **O (Open/Closed):** Software entities should be open for extension but closed for modification.
*   **L (Liskov Substitution):** Subtypes must be substitutable for their base types.
*   **I (Interface Segregation):** Many specific interfaces are better than one general-purpose interface.
*   **D (Dependency Inversion):** Depend on abstractions (interfaces), not concretions (classes). Spring’s Inversion of Control (IoC) is a primary example.

### **6. Design Patterns**

*   **Factory Pattern:** Creates objects without exposing the instantiation logic to the client.
*   **Singleton Pattern:** Ensures a class has only one instance and provides a global point of access.
    *   *Implementation:* Private constructor, static instance of the class, and a static `getInstance()` method.
*   **Builder Pattern:** Used for constructing complex objects step-by-step using an inner class.

### **7. Access Modifiers**

*   **Public:** Accessible everywhere.
*   **Private:** Accessible only within the same class.
*   **Protected:** Accessible within the same package and by subclasses in different packages.
*   **Default (No keyword):** Accessible only within the same package.
*   **The `default` keyword:** Used in `switch` cases and to define methods with implementation in **Interfaces** (Java 8+).

### **8. Inheritance & The Diamond Problem**

*   Java does **not** support multiple inheritance with classes (extending two classes) to avoid ambiguity (the Diamond Problem).
*   **Solution:** Use **Interfaces**. If two interfaces have the same default method, the implementing class must override the method and explicitly choose which one to call using `InterfaceName.super.methodName()`.

### **9. Exception Handling**

*   **Try-Catch-Finally:** `try` blocks hold risky code, `catch` handles the exception, and `finally` runs regardless of whether an exception occurs (used for cleanup).
*   **Throw vs. Throws:** 
    *   `throw`: Used to explicitly throw an exception.
    *   `throws`: Used in a method signature to declare that the method might throw an exception.
*   **Propagation:** If an exception is not handled, it propagates up the call stack. If never caught, the JVM handles it by printing the stack trace and terminating the program.

### **10. Memory Management & Strings**

*   **Stack vs. Heap:** 
    *   **Stack:** Stores local variables and reference variables.
    *   **Heap:** Stores actual objects.
*   **String Constant Pool (SCP):** A special area in the Heap for String literals.
    *   `String s1 = "Hello";` (Stored in SCP)
    *   `String s2 = new String("Hello");` (Stored in Heap)
*   **Comparison:** `==` compares memory references; `.equals()` compares the actual content/value.

### **11. Serialization**

*   **Serialization:** Converting an object into a byte stream (to save to a file or send over a network).
*   **Deserialization:** Reconstructing the object from the byte stream.
*   **Transient Keyword:** Used to prevent specific variables (like passwords) from being serialized.
*   **Externalization:** A way to implement custom/manual serialization.

### **12. Important Keywords**

*   **Final:** 
    *   Variable: Value cannot be changed (constant).
    *   Method: Cannot be overridden.
    *   Class: Cannot be inherited.
*   **This vs. Super:** 
    *   `this`: Refers to the current class instance/object.
    *   `super`: Refers to the immediate parent class.
*   **Static:** Allows members to be accessed without creating an instance of the class.

### **13. The Main Method**

*   `public static void main(String[] args)`
    *   **Public:** So JVM can access it from outside.
    *   **Static:** So JVM can call it without instantiating the class.
    *   **Void:** It doesn't return any value to the JVM.
    *   **String[] args:** Command-line arguments.

### **14. Finalize & Garbage Collection**

*   **Garbage Collector (GC):** Automatically deletes unreferenced objects from the Heap.
*   **finalize():** A method called by the GC just before an object is destroyed.
*   **Note:** Calling `System.gc()` is just a request; the JVM does not guarantee it will run immediately.

### **15. Pass by Value vs. Pass by Reference**

*   **Java is strictly "Pass by Value."**
*   However, when passing an object, the "value" passed is actually the **reference/memory address**. Therefore, changes to the object's attributes inside a method *will* affect the original object, leading to common confusion that Java is pass by reference.