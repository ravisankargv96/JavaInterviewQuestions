These notes provide a detailed summary of the "Introduction to Java Language and its Features" video from the Code Decode channel.

### **1. Introduction to Java**
*   **Origin:** Java was developed by James Gosling and his team (the "Green Team") at Sun Microsystems in 1995. It is now owned by Oracle Corporation.
*   **Evolution:** Originally designed for interactive television, it was too advanced for the digital cable industry at the time. It eventually found its primary success in internet programming and enterprise software.
*   **Primary Goal:** The creators wanted a language that was "Write Once, Run Anywhere" (WORA).

### **2. The Core Concept: WORA**
*   **Write Once, Run Anywhere:** This is the most significant advantage of Java. 
*   In languages like C or C++, code is compiled into machine-specific code. If you compile it on Windows, it won't run on Linux.
*   **Java’s Approach:** Java source code (`.java`) is compiled into an intermediate form called **Bytecode** (`.class` file). This bytecode is not specific to any processor. It can be executed on any machine that has a **Java Virtual Machine (JVM)** installed.

### **3. Key Features of Java (The Java Buzzwords)**

#### **Simple**
*   Java was designed to be easy to learn if you understand the basics of C++.
*   It removed complex and confusing features like explicit pointers, operator overloading, and multiple inheritance (at the class level).
*   Automatic garbage collection simplifies memory management for the developer.

#### **Object-Oriented**
*   Everything in Java is an Object. 
*   It follows the four main pillars of OOP:
    1.  **Inheritance:** Reusing code from existing classes.
    2.  **Polymorphism:** Performing a single action in different ways.
    3.  **Abstraction:** Hiding internal details and showing only functionality.
    4.  **Encapsulation:** Binding data and code together into a single unit.

#### **Platform Independent**
*   Java is a platform-independent language because it does not depend on the underlying hardware or operating system.
*   The **Compiler** converts source code to Bytecode, and the **JVM** converts Bytecode to Machine Code. While the JVM itself is platform-dependent (different versions for Mac, Windows, Linux), the Bytecode it executes is the same everywhere.

#### **Robust**
*   "Robust" means strong. Java is robust because:
    *   It uses strong memory management.
    *   It lacks pointers, which prevents unauthorized memory access.
    *   It provides **Exception Handling** to manage runtime errors gracefully.
    *   The Type-checking mechanism in Java is very strict.

#### **Secure**
*   Java is known for its security. Because there are no explicit pointers, a programmer cannot access out-of-bounds memory.
*   Java programs run inside a virtual machine "sandbox," which isolates the code from the host operating system, preventing viruses or malicious code from damaging the system.

#### **Architecture-Neutral**
*   In C programming, the size of data types may vary based on the architecture (e.g., `int` might be 2 bytes on a 32-bit system and 4 bytes on a 64-bit system).
*   In Java, data types have a fixed size regardless of the architecture (e.g., `int` is always 4 bytes), making the language architecture-neutral.

#### **Portable**
*   Because the bytecode is architecture-neutral and platform-independent, Java is highly portable. You can carry the bytecode to any environment without needing to recompile the source code.

#### **High Performance**
*   While interpreted languages are usually slower than compiled languages, Java achieves high performance through the **JIT (Just-In-Time) Compiler**.
*   The JIT compiler compiles bytecode into native machine code on the fly for frequently executed sections of the program.

#### **Distributed**
*   Java is designed for the distributed environment of the internet. 
*   It supports protocols like HTTP and FTP and features like RMI (Remote Method Invocation) and EJB (Enterprise Java Beans), allowing objects on one machine to call methods on objects located on different machines.

#### **Multithreaded**
*   Java allows you to write programs that can perform many tasks simultaneously. 
*   This is achieved by defining multiple "threads." It is highly useful for multimedia and web applications where you want to handle multiple users or processes at once without freezing the UI.

#### **Dynamic**
*   Java is more dynamic than C or C++. It is designed to adapt to an evolving environment.
*   Java programs can carry extensive amounts of run-time type information that can be used to verify and resolve accesses to objects at run-time. It supports dynamic loading of classes (loading classes on demand).

### **4. Summary of the Execution Process**
1.  **Source Code:** Developer writes code in a `.java` file.
2.  **Compilation:** The `javac` compiler converts it into `.class` file (Bytecode).
3.  **Execution:** The JVM interprets/compiles the Bytecode into machine-specific code for the local hardware.