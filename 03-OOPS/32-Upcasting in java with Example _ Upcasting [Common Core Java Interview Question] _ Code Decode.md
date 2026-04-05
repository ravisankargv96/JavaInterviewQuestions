Detailed notes based on the video **"Upcasting in Java with Example"** by Code Decode.

---

### **1. Introduction to Casting**
Casting is the process of converting one data type into another. In Java, when we deal with Object-Oriented Programming (Inheritance), casting involves converting a reference from one class type to another within the same inheritance hierarchy.

### **2. What is Upcasting?**
Upcasting is the process of casting a **Subclass (Child)** type to a **Superclass (Parent)** type. 
*   **Direction:** Bottom to Top (Child $\rightarrow$ Parent).
*   **Nature:** It is **Implicit**. The Java compiler does this automatically because it is always safe. Since a "Dog" is always an "Animal," Java doesn't require explicit syntax to treat a Dog as an Animal.

### **3. Syntax of Upcasting**
```java
Parent p = new Child();
```
*   **`Parent p`**: This is the reference variable of the Superclass type.
*   **`new Child()`**: This is the actual object created in the heap memory of the Subclass type.

### **4. Key Rules of Upcasting**

#### **A. Access to Methods**
When you upcast a child object to a parent reference, the reference variable can **only** access methods and variables defined in the Parent class. It loses access to methods that are unique to the Child class.

#### **B. Method Overriding (Dynamic Method Dispatch)**
If the Child class has overridden a method from the Parent class:
*   Calling that method via the upcasted reference will execute the **Child's version** of the method.
*   This happens because, in Java, method resolution is done at runtime based on the actual object type (which is Child), not the reference type.

#### **C. Safety**
Upcasting is always safe because the Child class inherits all members of the Parent class. Therefore, the Child object is guaranteed to have everything the Parent reference expects.

---

### **5. Practical Example**

Consider the following classes:

```java
class Parent {
    void printData() {
        System.out.println("Method of Parent class");
    }
}

class Child extends Parent {
    @Override
    void printData() {
        System.out.println("Method of Child class (Overridden)");
    }
    
    void onlyChildMethod() {
        System.out.println("Special method of Child class");
    }
}

public class Main {
    public static void main(String[] args) {
        // Upcasting: Child object referred by Parent reference
        Parent obj = new Child(); 
        
        // 1. Calling overridden method
        obj.printData(); // Output: Method of Child class (Overridden)
        
        // 2. Accessing child-specific method
        // obj.onlyChildMethod(); // COMPILE TIME ERROR
    }
}
```

**Explanation of the example:**
1.  `obj.printData()`: Even though the reference is `Parent`, it calls the `Child` version because the actual object is a `Child`. This is Polymorphism.
2.  `obj.onlyChildMethod()`: This fails because the compiler looks at the reference type (`Parent`) to decide what can be called. The `Parent` class has no knowledge of `onlyChildMethod`.

---

### **6. Why do we need Upcasting?**
Upcasting is essential for achieving **Polymorphism** and **Code Generalization**.

1.  **Flexibility in Arguments:** You can write a single method that accepts a Parent type as an argument, and it will be able to accept any of its Child objects.
    *   *Example:* A method `makeSound(Animal a)` can accept `Dog`, `Cat`, or `Lion` objects via upcasting.
2.  **Interface Usage:** When we use interfaces (e.g., `List list = new ArrayList()`), we are performing upcasting. This allows us to change the implementation (e.g., to `LinkedList`) later without changing the rest of the code.

### **7. Comparison: Upcasting vs. Downcasting**

| Feature | Upcasting | Downcasting |
| :--- | :--- | :--- |
| **Direction** | Child to Parent | Parent to Child |
| **Implicit/Explicit** | Implicit (Automatic) | Explicit (Manual casting required) |
| **Safety** | Always safe | Risky (May throw `ClassCastException`) |
| **Syntax** | `Parent p = new Child();` | `Child c = (Child) p;` |

### **8. Interview Summary Tips**
*   **Definition:** Casting a subtype to a supertype.
*   **Automation:** It's done automatically by the compiler.
*   **Restriction:** You can only access members defined in the Parent class, but overridden methods follow the Child's implementation.
*   **Purpose:** To achieve runtime polymorphism and write generic code.