These notes summarize the key concepts, interview questions, and practical demonstrations from the "Java 8 New Features Part 2" video by Code Decode.

---

### **1. Default Methods in Interfaces**

#### **What are Default Methods?**
Prior to Java 8, interfaces could only contain abstract methods. Adding a new method to an interface would break all implementing classes. Java 8 introduced **Default Methods** (using the `default` keyword) to allow developers to add new methods to interfaces with a "dummy" or default implementation.

*   **Syntax:** `default void methodName() { ... }`
*   **Purpose:** To add new functionality to existing interfaces without affecting implementing classes (**Backward Compatibility**).
*   **Key Benefit:** It prevents compile-time errors in hundreds of implementing classes when a library or the JDK updates an interface.

#### **Is it necessary to override Default Methods?**
No.
*   If the implementing class is satisfied with the dummy implementation provided in the interface, it does not need to override it.
*   If the class needs specific logic, it can override the default method just like a regular method.

#### **The `default` Keyword vs. Access Modifiers**
*   **Interview Trick:** Is `default` an access modifier?
    *   **No.** While we often refer to "package-private" access as "default," the `default` **keyword** used in interfaces is not an access modifier.
    *   By default, all interface methods in Java 8 are **public**.
    *   The `default` keyword was previously only used in `switch` cases. Java 8 repurposed it for interface method implementations.

#### **How to override a Default Method?**
To override a default method in a class:
1.  Remove the `default` keyword.
2.  Use the `public` access modifier (you cannot reduce visibility; interface methods are public, and classes must maintain that).
3.  Provide the new implementation.

---

### **2. Interface Methods and the `Object` Class**

#### **Can you provide a default implementation for `hashCode()` or `equals()` in an interface?**
**No.** 
*   An interface **cannot** provide a default implementation for any method contained in the `java.lang.Object` class (e.g., `hashCode()`, `equals()`, or `toString()`).
*   **Reason:** Every class in Java implicitly extends `java.lang.Object`. The class hierarchy logic dictates that "class wins" over "interface." Since every implementing class already has an implementation of these methods from `Object`, a default method in an interface would be redundant and is therefore prohibited (it will cause a compile-time error).

---

### **3. The Diamond Problem in Java 8**

#### **What is the Diamond Problem?**
The diamond problem occurs through multiple inheritance. If a class implements two interfaces (`InterfaceA` and `InterfaceB`) and both contain a default method with the same signature (e.g., `void m1()`), the compiler becomes confused about which implementation to use.

*   **Result:** A compile-time error: *"Duplicate default methods named m1 are inherited from the types InterfaceB and InterfaceA."*

#### **How to Resolve the Diamond Problem?**
The implementing class must manually override the conflicting method. To call a specific parent interface's version, use the following syntax:
*   **Syntax:** `InterfaceName.super.methodName();`
*   **Example:** `Interface1.super.m1();` inside the overridden method in the class.

---

### **4. Static Methods in Interfaces**

#### **Why were Static Methods introduced in Interfaces?**
Java 8 allows `static` methods inside interfaces to act as **utility methods**.

*   **Syntax:** `static void utilityMethod() { ... }`
*   **Benefit:** You can call the method using only the interface name (`InterfaceName.utilityMethod()`).
*   **Efficiency:** Unlike classes, interfaces do not have constructors or static blocks, making them "lighter" in terms of memory and performance for simple utility logic.

#### **Are Static Methods inherited?**
**No.** 
*   Unlike default methods, static methods are **not** available to implementing classes by default.
*   You cannot call an interface's static method using an object of the implementing class or the class name itself.
*   **Mandatory Usage:** They must be called strictly via the **Interface Name**.

---

### **5. Summary Table: Default vs. Static Methods**

| Feature | Default Method | Static Method |
| :--- | :--- | :--- |
| **Keyword** | `default` | `static` |
| **Inheritance** | Inherited by implementing classes. | Not inherited by implementing classes. |
| **Overriding** | Can be overridden by the class. | Cannot be overridden (belongs to interface). |
| **How to Call** | Via class instance (object). | Via Interface Name only. |
| **Main Goal** | Backward compatibility/API evolution. | General utility methods. |

---

### **Looking Ahead**
The video concludes by mentioning that the next parts will cover functional programming concepts in detail, including:
*   **Predicate:** For boolean-valued functions (filtering).
*   **Function:** For transforming data.
*   **Consumer:** For consuming data without returning a result.
*   **Supplier:** For providing data.
*   **Streams and Parallel Streams:** For bulk data processing.