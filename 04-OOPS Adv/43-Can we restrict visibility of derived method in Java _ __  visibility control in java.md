# Detailed Study Notes: Visibility Control in Java Inheritance

These notes are based on the video tutorial regarding whether a derived (child) class can restrict the visibility of an inherited method from a base (parent) class.

---

### 1. The Core Question
In Java, when a child class inherits a method from a parent class (method overriding), a common interview question is: **"Can the overriding method in the child class be more restrictive than the method in the parent class?"**

### 2. Hierarchy of Access Modifiers
To understand visibility control, you must first know the hierarchy of access modifiers from **most accessible** to **least accessible**:

1.  **Public:** Accessible from anywhere.
2.  **Protected:** Accessible within the same package and by subclasses in other packages.
3.  **Default (No keyword):** Accessible only within the same package.
4.  **Private:** Accessible only within the same class.

---

### 3. The Fundamental Rule
In Java, when overriding a method, **you cannot reduce the visibility of the inherited method.**

*   **Rule:** The overriding method in the child class must have the **same or higher** visibility than the method in the parent class.
*   **Reason:** If a parent class promises that a method is public, any object that is an instance of that parent (including child objects) must honor that promise. Reducing visibility would break the principle of polymorphism.

---

### 4. Practical Scenarios & Examples

#### Scenario A: Parent has Higher Visibility (Restricting in Child)
*   **Parent Class (Dog):** Has a method `public void specialCapability()`.
*   **Child Class (Labrador):** Attempts to override it as `private void specialCapability()` or `void specialCapability()` (default).
*   **Result:** **Compile-time Error.** 
*   **Error Message:** Java will prompt you to "Change the visibility of the method to public" or "Change the visibility of the parent method to be more restrictive."

#### Scenario B: Parent has Lower Visibility (Increasing in Child)
*   **Parent Class (Dog):** Has a method `void specialCapability()` (Default/Package-private).
*   **Child Class (Labrador):** Overrides it as `public void specialCapability()`.
*   **Result:** **Success.** The code will compile successfully.
*   **Key Takeaway:** You are allowed to make a method *more* accessible in a child class, just not *less*.

---

### 5. Summary Table of Visibility Rules

| Parent Method Visibility | Allowed in Child (Overriding) | NOT Allowed in Child |
| :--- | :--- | :--- |
| **Public** | Public | Protected, Default, Private |
| **Protected** | Protected, Public | Default, Private |
| **Default** | Default, Protected, Public | Private |
| **Private** | *N/A (Private methods cannot be overridden)* | N/A |

---

### 6. Conclusion
*   **Can we restrict visibility?** No.
*   **Can we expand visibility?** Yes.
*   **Compilation:** Attempting to restrict visibility results in a **Compile-time error**, not a runtime error.
*   **Golden Rule:** Always check the parent class modifier first; the child must be at least that visible or more.