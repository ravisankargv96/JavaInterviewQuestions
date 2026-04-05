These notes cover the core concepts of **Variable Shadowing in Java** as explained in the video by Code Decode.

---

### **1. What is Variable Shadowing?**
Variable shadowing occurs when a variable declared within a specific scope (like a method, constructor, or inner block) has the same name as a variable declared in an overlapping outer scope (like an instance variable of a class).

*   **The Conflict:** When both variables have the same name, the variable in the "inner" scope hides or "shadows" the variable in the "outer" scope.
*   **Result:** Inside the inner scope, referring to the variable name will default to the local version, not the instance version.

---

### **2. Common Scenarios of Shadowing**

#### **A. Shadowing Instance Variables with Local Variables**
This is the most frequent occurrence. If you define a local variable inside a method with the same name as a class-level instance variable, the local variable takes precedence.
*   **Example:**
    ```java
    class Student {
        String name = "Global Name"; // Instance variable

        void display() {
            String name = "Local Name"; // Shadows instance variable
            System.out.println(name); // Prints: Local Name
        }
    }
    ```

#### **B. Shadowing with Method Parameters**
This often happens in **Constructors** or **Setter methods**.
*   **Example:**
    ```java
    class Employee {
        int id;
        Employee(int id) {
            id = id; // This does nothing! It assigns the parameter to itself.
        }
    }
    ```
    In the case above, the parameter `id` shadows the instance variable `id`. To fix this, you must use the `this` keyword.

---

### **3. How to Access Shadowed Variables**
If a variable is shadowed, you can still access the "hidden" outer variable using specific keywords:

1.  **`this` Keyword:** Used to refer to the current class instance variable.
    *   *Correction for the example above:* `this.id = id;`
2.  **`super` Keyword:** Used to refer to a variable in the parent class if it is being shadowed by a variable in the child class.
3.  **Class Name:** If the shadowed variable is **static**, you can access it using `ClassName.variableName`.

---

### **4. Shadowing vs. Overriding**
It is important to distinguish between these two concepts for interviews:

*   **Method Overriding:** Applies to methods. It uses **Dynamic Polymorphism** (Runtime). The method called is determined by the actual object type at runtime.
*   **Variable Shadowing:** Applies to variables. It uses **Static Binding** (Compile-time). Variables are not polymorphic. If you have a variable in both a Parent and Child class, the one that gets accessed depends on the **reference type**, not the object type.

---

### **5. Shadowing in Inheritance (Data Hiding)**
When a child class declares a variable with the same name as one in the parent class, it is often called **Data Hiding**.
*   Unlike methods, variables are not overridden.
*   If you have `Parent p = new Child();`, and you call `p.x`, Java will look at the type of the reference (`Parent`) and print the parent's value, even if the `Child` class has a variable named `x`.

---

### **6. Key Interview Takeaways**
*   **Is Shadowing a bug?** Not necessarily, but it is considered a bad practice because it makes code less readable and prone to errors (like forgetting to use `this` in a setter).
*   **Scope Priority:** The narrowest scope always wins.
*   **Static Context:** Shadowing also applies to static variables. A local variable can shadow a static class variable.
*   **Best Practice:** Use distinct names for local variables or always use `this.` when assigning values to instance variables to avoid ambiguity.

---

### **7. Summary Code Example**
```java
class Demo {
    int x = 10; // Instance Variable

    void show() {
        int x = 20; // Local Variable (Shadowing)
        System.out.println("Local x: " + x);         // 20
        System.out.println("Instance x: " + this.x); // 10
    }
}
```