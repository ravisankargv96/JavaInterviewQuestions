These notes cover the key concepts, syntax, and interview-specific details regarding **Static Methods in Interfaces**, as introduced in Java 8, based on the Code Decode tutorial.

---

### 1. Introduction to Static Methods in Interfaces
Before Java 8, interfaces could only contain abstract methods (methods without a body). Java 8 introduced two ways to add method implementations to interfaces: **Default Methods** and **Static Methods**.

*   **Definition:** A static method in an interface is a method that is associated with the interface itself rather than any object (instance) of a class that implements the interface.
*   **Purpose:** They are primarily used as **utility or helper methods**. They allow you to keep utility logic related to the interface within the interface itself, rather than creating a separate "Utility" class (like `Collections` or `Path`).

### 2. Syntax and Rules
*   **Keyword:** Must use the `static` keyword.
*   **Body:** Unlike abstract methods, static methods **must** have a method body.
*   **Implicit Modifiers:** Static methods in an interface are `public` by default. You cannot make them `protected` or `private` (Note: private methods in interfaces were introduced later in Java 9).
*   **Access:** They can only be accessed using the Interface name.

```java
interface MyInterface {
    static void display() {
        System.out.println("Static Method in Interface");
    }
}
```

### 3. Key Characteristics and Restrictions
1.  **No Inheritance:** Static methods are **not inherited** by the classes that implement the interface.
2.  **Accessing the Method:** 
    *   **Correct way:** `InterfaceName.methodName();`
    *   **Incorrect way:** `ImplementationClassName.methodName();` or `objectName.methodName();` (These will result in a compile-time error).
3.  **No Overriding:** Since static methods are not inherited, they cannot be overridden by the implementing class. 
    *   If you define a method with the exact same signature in the implementing class, it is considered a **different method** entirely, not an override. This is often referred to as "method hiding," though strictly speaking, interface static methods aren't even hidden; they are simply unreachable through the class.

### 4. Static Methods vs. Default Methods
| Feature | Static Method | Default Method |
| :--- | :--- | :--- |
| **Keyword** | `static` | `default` |
| **Inheritance** | Not inherited by implementing classes. | Inherited by implementing classes. |
| **Overriding** | Cannot be overridden. | Can be overridden by the class. |
| **Access** | Accessed via Interface name only. | Accessed via object instance. |
| **Use Case** | Utility/Helper logic common to all. | Providing default behavior to implementations. |

### 5. Why were Static Methods introduced?
*   **Security:** Because they cannot be overridden, developers can ensure that the specific logic in the static method remains unchanged regardless of the implementation.
*   **Organization:** It keeps the utility methods closely coupled with the interface they serve, rather than needing a separate `Helper` class.
*   **Backward Compatibility:** It allows adding utility functions to existing interfaces without breaking the classes that implement them.

### 6. The "Main Method" in an Interface
A popular interview question is: **"Can we run a Java program using an interface's main method?"**
*   **Answer:** **Yes.** Since Java 8, because static methods are allowed, you can define a `public static void main(String[] args)` method inside an interface.
*   **How to run:** You can run the interface directly from the command line (`java InterfaceName`), and the JVM will execute the static main method.

### 7. Important Interview Questions (FAQ)
*   **Can we override a static method of an interface?** 
    No. Static methods belong to the interface, and the implementing class has no knowledge of them.
*   **Why use static methods instead of default methods?** 
    Use static methods when you want to provide a utility function that should **not** be changed or overridden by any implementing class. Use default methods when you want to provide a base implementation that classes *can* override if they choose.
*   **Can an interface have multiple static methods?** 
    Yes, an interface can have any number of static methods.
*   **Can we use `Object` class methods as static methods in an interface?** 
    No. You cannot define a static method in an interface that has the same signature as a method in the `Object` class (e.g., `equals`, `hashCode`, `toString`). This is because the `Object` class is the root of the class hierarchy, and this would lead to ambiguity.

### 8. Summary Checklist
*   Belongs to the Interface class.
*   Call using `InterfaceName.method()`.
*   Requires a body.
*   Does not require an object instance to be called.
*   Cannot be overridden by implementing classes.
*   Ideal for utility/helper logic.