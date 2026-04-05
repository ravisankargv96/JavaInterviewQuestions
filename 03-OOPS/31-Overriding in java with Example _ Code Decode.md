These notes cover the key concepts, rules, and technical nuances of **Method Overriding in Java** as presented by the Code Decode tutorial.

---

### **1. Introduction to Method Overriding**
Method Overriding occurs when a subclass (child class) provides a specific implementation for a method that is already defined in its superclass (parent class).

*   **Purpose:** It allows a child class to inherit features from a parent class while modifying behavior to suit its specific needs.
*   **Polymorphism:** Overriding is the mechanism used to achieve **Runtime Polymorphism** (or Dynamic Method Dispatch).
*   **Requirement:** There must be an **IS-A relationship** (inheritance) between the classes.

---

### **2. Essential Rules for Overriding**
To successfully override a method, the following conditions must be met:

1.  **Method Signature:** The method name and the parameter list (arguments) must be exactly the same as in the parent class.
2.  **Return Type:** The return type must be the same, or a **Covariant Return Type** (a subclass of the return type declared in the parent).
3.  **Access Modifiers:** The overriding method in the child class cannot have a more restrictive access modifier than the method in the parent class.
    *   *Example:* If the parent method is `protected`, the child method can be `protected` or `public`, but NOT `private` or `default`.
    *   Order of visibility (lowest to highest): `private` -> `default` -> `protected` -> `public`.

---

### **3. Runtime Polymorphism (Dynamic Method Dispatch)**
This is the process where a call to an overridden method is resolved at runtime rather than compile time.

*   **Reference vs. Object:** The JVM determines which method to execute based on the **object** being referred to, not the **reference type**.
*   **Example:**
    ```java
    Parent obj = new Child(); 
    obj.show(); 
    ```
    Even though `obj` is of type `Parent`, the `show()` method of the `Child` class will be executed because the actual object created is of the `Child` class.

---

### **4. Restrictions: What cannot be overridden?**

*   **Final Methods:** A method declared as `final` in the parent class cannot be overridden. This is used when you want to prevent any subclass from changing the implementation.
*   **Static Methods (Method Hiding):** Static methods belong to the class, not the instance. If you define a static method in a child class with the same signature as one in the parent, it is called **Method Hiding**, not overriding.
*   **Private Methods:** Private methods are not visible to subclasses; therefore, they cannot be overridden.
*   **Constructors:** Constructors cannot be inherited, so they cannot be overridden.

---

### **5. Exception Handling Rules in Overriding**
Java imposes specific rules regarding checked exceptions during overriding:

1.  **If the Parent method does not throw an exception:** The Child method can only throw **unchecked** exceptions (RuntimeException). It cannot throw any **checked** exceptions.
2.  **If the Parent method throws an exception:** The Child method can throw the same exception, a subclass of that exception, or no exception at all. However, it **cannot** throw a broader (parent) checked exception.

---

### **6. The `@Override` Annotation**
While not strictly mandatory, using `@Override` is a best practice.
*   It informs the compiler that the method is intended to override a parent method.
*   If the method signature doesn't match the parent exactly, the compiler will throw an error, preventing accidental "overloading" when you intended to "override."

---

### **7. Summary Comparison: Overloading vs. Overriding**

| Feature | Method Overloading | Method Overriding |
| :--- | :--- | :--- |
| **Relationship** | Occurs within the same class. | Occurs between Parent and Child classes. |
| **Parameters** | Must be different. | Must be the same. |
| **Binding** | Compile-time (Static binding). | Runtime (Dynamic binding). |
| **Return Type** | Can be different. | Must be same or covariant. |
| **Purpose** | To increase readability of the program. | To provide specific implementation. |

---

### **8. Practical Example Logic**
*   **Parent Class:** `Bank` with a method `getRateOfInterest()` returning 0.
*   **Child Class A:** `SBI` overrides `getRateOfInterest()` to return 8.
*   **Child Class B:** `ICICI` overrides `getRateOfInterest()` to return 7.
*   **Execution:** If you create a `Bank` reference pointing to an `SBI` object, the JVM ensures the SBI-specific interest rate is fetched at runtime.