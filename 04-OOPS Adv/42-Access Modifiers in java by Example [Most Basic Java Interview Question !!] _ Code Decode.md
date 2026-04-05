These notes provide a comprehensive breakdown of the video "Access Modifiers in Java" by Code Decode, covering the definitions, levels of accessibility, and practical interview-related rules.

---

### **1. Introduction to Access Modifiers**
Access modifiers in Java are keywords used to set the accessibility (visibility) of classes, interfaces, variables, methods, constructors, and data members. They are the fundamental tools for achieving **Encapsulation** and data hiding.

### **2. The Four Types of Access Modifiers**

#### **A. Private**
*   **Scope:** Only within the **same class**.
*   **Visibility:** It is the most restrictive modifier. Data members or methods marked as `private` cannot be accessed from outside the class, even by subclasses.
*   **Use Case:** Used for sensitive data (like passwords or internal logic) that should not be modified directly from outside.
*   **Note:** Top-level classes or interfaces cannot be declared `private`.

#### **B. Default (No Keyword)**
*   **Scope:** Only within the **same package**.
*   **Visibility:** If you do not specify any access modifier, Java assigns the "default" level. It is also known as "Package-Private."
*   **Use Case:** When you want to hide logic from the outside world but share it among classes within the same functional module (package).

#### **C. Protected**
*   **Scope:** Within the **same package** AND in **subclasses** (even if they are in different packages).
*   **Visibility:** It acts like the default modifier but allows access through inheritance. If a class in Package B extends a class in Package A, it can access the `protected` members of the parent class.
*   **Use Case:** Useful in framework development where you want subclasses to customize or use parent behavior but keep it hidden from unrelated classes.

#### **D. Public**
*   **Scope:** **Everywhere** (the entire world).
*   **Visibility:** The least restrictive modifier. Any class in any package can access `public` members as long as the class itself is visible.
*   **Use Case:** Used for APIs, constants, or methods that need to be accessible to any user of the library.

---

### **3. Access Levels Summary Table**

| Modifier | Class | Package | Subclass (Same Pkg) | Subclass (Diff Pkg) | World |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Private** | Yes | No | No | No | No |
| **Default** | Yes | Yes | Yes | No | No |
| **Protected** | Yes | Yes | Yes | Yes | No |
| **Public** | Yes | Yes | Yes | Yes | Yes |

---

### **4. Key Rules and Interview Insights**

#### **Class-Level Restrictions**
*   **Outer Classes:** Can only be `public` or `default`. They **cannot** be `private` or `protected`. (An outer class being private would make it unusable).
*   **Inner Classes:** Can be `private`, `public`, `protected`, or `default`.

#### **Access Modifiers and Method Overriding**
One of the most common interview questions is: *"Can we change the access modifier while overriding a method?"*
*   **Rule:** You cannot assign a **more restrictive** access modifier to an overridden method. You can, however, make it **less restrictive**.
*   **Example:**
    *   If a parent class method is `protected`, the child class can override it as `protected` or `public`, but **not** as `default` or `private`.
    *   If a parent class method is `public`, the child must also keep it `public`.

#### **The "Protected" Nuance**
*   A `protected` member is accessible in a different package **only via inheritance**. 
*   If you try to access a `protected` method of Class A from Class B (in a different package) using an object reference (e.g., `a.method()`), it will fail unless Class B is a child of Class A and you use the `super` keyword or the inherited reference.

---

### **5. Why Use Access Modifiers?**
1.  **Security:** Prevents unauthorized access to sensitive internal data.
2.  **Maintainability:** By limiting access, you reduce the dependencies between different parts of the code.
3.  **Flexibility:** You can change the internal implementation of a class without affecting external users as long as the public API remains the same.

### **6. Conclusion**
*   Use **Private** by default for all fields.
*   Use **Public** only for methods meant for the user.
*   Use **Protected** for methods intended to be overridden by subclasses.
*   Use **Default** for package-level internal utility logic.