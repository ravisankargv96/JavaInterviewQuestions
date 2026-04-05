These detailed notes cover the essential Java interview questions and concepts discussed in the "Code Decode" video designed for beginners and freshers.

---

### **1. Java Platform Independence**
*   **The Concept:** Java is known for the "Write Once, Run Anywhere" (WORA) philosophy.
*   **How it works:** When you compile Java code, it isn't converted into machine-specific code. Instead, it is converted into **Bytecode** (.class file).
*   **The Role of JVM:** The Java Virtual Machine (JVM) sits on top of the OS. While the JVM itself is platform-dependent (different for Windows, Mac, Linux), it can read the same universal Bytecode and translate it into the host machine’s language.

### **2. Difference Between JDK, JRE, and JVM**
*   **JVM (Java Virtual Machine):** The engine that provides the runtime environment to drive the Java Code. It converts bytecode into machine code.
*   **JRE (Java Runtime Environment):** JVM + Libraries (like rt.jar) + Other files. It is what you need to **run** a Java program.
*   **JDK (Java Development Kit):** JRE + Development Tools (javac, debugger, etc.). It is what you need to **write and compile** a Java program.

### **3. Why is Java Not 100% Object-Oriented?**
*   Java is considered "not purely" object-oriented because it supports **Primitive Data Types** (int, float, double, char, etc.). In a pure OOP language, everything must be an object.

### **4. Understanding "public static void main(String[] args)"**
*   **public:** This is an access modifier that allows the JVM to access the method from outside the class.
*   **static:** Allows the JVM to call the method without creating an instance (object) of the class. This is crucial because the main method is the entry point.
*   **void:** The return type; the main method does not return any value to the JVM.
*   **main:** The name of the method searched for by the JVM as the starting point.
*   **String[] args:** An array of type String that stores command-line arguments.

### **5. The Four Pillars of OOPs**
*   **Encapsulation:** Wrapping data (variables) and code (methods) together as a single unit. It is achieved by making variables `private` and providing `public` getters and setters.
*   **Inheritance:** The mechanism where one class acquires the properties of another. It promotes code reusability (using the `extends` keyword).
*   **Polymorphism:** The ability of a variable, function, or object to take on multiple forms.
    *   *Compile-time:* Method Overloading (same name, different parameters).
    *   *Runtime:* Method Overriding (child class providing a specific implementation of a parent class method).
*   **Abstraction:** Hiding internal implementation details and showing only the necessary functionality to the user. Achieved via **Abstract Classes** and **Interfaces**.

### **6. String Handling: String vs. StringBuilder vs. StringBuffer**
*   **String:** Immutable (cannot be changed). Every time you modify a String, a new object is created in the **String Constant Pool (SCP)**.
*   **StringBuffer:** Mutable and **Thread-safe** (synchronized). It is slower because of the overhead of synchronization.
*   **StringBuilder:** Mutable but **not Thread-safe**. It is faster than StringBuffer and preferred for single-threaded environments.

**Why are Strings Immutable?**
*   **Security:** Prevents sensitive data (like passwords) from being changed by another thread.
*   **Caching:** The String Pool saves memory by reusing the same string literals.
*   **Synchronization:** Immutability makes Strings naturally thread-safe.

### **7. "Final", "Finally", and "Finalize"**
*   **final:** A keyword used to apply restrictions.
    *   Final Variable: Becomes a constant (value cannot change).
    *   Final Method: Cannot be overridden.
    *   Final Class: Cannot be inherited.
*   **finally:** A block used in exception handling that **always executes**, whether an exception is handled or not (used for closing resources like database connections).
*   **finalize:** A method called by the **Garbage Collector** just before an object is destroyed to perform cleanup activities.

### **8. Exception Handling**
*   **Checked Exceptions:** Checked at **compile-time** (e.g., IOException, SQLException). The programmer must handle these using try-catch or the `throws` keyword.
*   **Unchecked Exceptions:** Checked at **runtime** (e.g., NullPointerException, ArithmeticException). These usually occur due to bad logic.
*   **throw vs. throws:**
    *   `throw` is used to explicitly throw a single exception.
    *   `throws` is used in a method signature to declare that the method might throw one or more exceptions.

### **9. Collections: ArrayList vs. LinkedList**
*   **ArrayList:**
    *   Uses a dynamic array to store elements.
    *   **Better for accessing data** (O(1) time complexity) because it uses index-based access.
    *   Slow for manipulation (insertion/deletion) because it requires shifting elements.
*   **LinkedList:**
    *   Uses a doubly-linked list.
    *   **Better for manipulation** (O(1) for insert/delete) as it only involves changing pointers.
    *   Slower for accessing data (O(n) time complexity).

### **10. Memory Management**
*   **Stack Memory:** Stores local variables and function calls. It follows LIFO (Last In First Out) and is memory-managed automatically.
*   **Heap Memory:** Stores actual objects and instance variables. This is where the **Garbage Collector** operates to free up memory from objects that are no longer referenced.

---
**Preparation Tip:** The video emphasizes that for a first interview, you should focus on "Why" something is used, not just "What" it is. Understanding the internal memory management of Strings and Collections is often what separates successful candidates from others.