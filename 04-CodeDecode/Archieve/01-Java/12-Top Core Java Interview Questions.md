Here are detailed notes based on the "Top Core Java Interview Questions" video.

### **Top Core Java Interview Questions & Answers**

**1. Why is Java Platform Independent?**

* **Concept:** Java follows the "Write Once, Run Anywhere" (WORA) philosophy.
* **Mechanism:**
* When you compile Java source code (`.java` file), the compiler (`javac`) does not convert it directly into machine code. Instead, it converts it into an intermediate format called **Bytecode** (`.class` file).
* This Bytecode is not specific to any operating system (OS).
* To run this code, you need a **JVM (Java Virtual Machine)**. The JVM is specific to the operating system (e.g., Windows JVM, Linux JVM, Mac JVM).
* The JVM interprets the Bytecode and converts it into the machine-understandable code for that specific OS.


* **Conclusion:** While the JVM itself is platform-dependent, the Java code (Bytecode) is platform-independent.

**2. Why is String Immutable in Java?**

* **Definition:** Once a String object is created, its value cannot be modified. If you try to change it, a new object is created.
* **Reasons for Immutability:**
* **String Constant Pool (SCP):** Java uses a special memory area called the String Pool to store String literals. If Strings were mutable, changing one reference would affect all other references pointing to the same literal in the pool.
* **Security:** Strings are widely used for critical information like database usernames, passwords, and network connections. Immutability prevents malicious code from altering these values after security checks.
* **Thread Safety:** Since Strings cannot be changed, they are inherently thread-safe. Multiple threads can access the same String instance without synchronization issues.
* **Class Loading:** Strings are used as arguments for class loading. If mutable, a wrong class could be loaded.



**3. What is a Marker Interface?**

* **Definition:** An interface that contains **no methods, fields, or constants**. It is an empty interface.
* **Purpose:** It is used to signal or "mark" to the JVM that the class implementing it has some special behavior or capability.
* **Examples:**
* `Serializable`: Tells the JVM that objects of this class can be converted into a byte stream.
* `Cloneable`: Tells the JVM that the `clone()` method is supported for this class.
* `Remote`: Used in RMI (Remote Method Invocation).



**4. Can We Override Static Methods?**

* **Answer:** **No**, you cannot override static methods in Java.
* **Explanation:**
* Method overriding is based on **runtime polymorphism** (dynamic binding).
* Static methods are bound at **compile time** (static binding) using the class reference, not the object reference.
* If you define a static method in a subclass with the same signature as a static method in the superclass, it is called **Method Hiding**, not overriding. The parent class method is simply hidden, not replaced.



**5. Difference Between `final`, `finally`, and `finalize**`

* **`final` (Keyword):**
* Used to apply restrictions.
* **Final Variable:** Value cannot be changed (constant).
* **Final Method:** Cannot be overridden by a subclass.
* **Final Class:** Cannot be inherited (extended).


* **`finally` (Block):**
* Used in Exception Handling (`try-catch-finally`).
* The code inside the `finally` block **always executes**, regardless of whether an exception occurs or is handled.
* Typically used for cleanup code like closing database connections or file streams.


* **`finalize` (Method):**
* A method in the `Object` class.
* It is called by the **Garbage Collector** just before an object is destroyed to perform cleanup processing (garbage collection).
* *Note: This has been deprecated in newer Java versions.*



**6. How to Create an Immutable Class?**
To create a custom immutable class (like `String`), follow these steps:

1. **Declare the class as `final`:** This prevents inheritance so no subclasses can override methods.
2. **Make all fields `private` and `final`:** `private` ensures direct access is blocked; `final` ensures they are assigned only once.
3. **No Setter Methods:** Do not provide methods that allow modifying fields.
4. **Initialize via Constructor:** Set the values of the fields through the constructor.
5. **Deep Copy:** If the class contains mutable objects (like a `Date` or `List`), do not return the direct reference in the getter methods. Return a deep copy (clone) or a new instance to prevent external modification.

**7. How to Create a Singleton Class?**
A Singleton class allows only **one instance** of the class to exist in the JVM.

* **Steps:**
6. **Private Static Instance:** Create a `static` variable of the class type to hold the single instance.
7. **Private Constructor:** Restrict object creation from outside the class.
8. **Public Static Method:** Provide a global access point (e.g., `getInstance()`) that returns the instance. If the instance is null, create it; otherwise, return the existing one.