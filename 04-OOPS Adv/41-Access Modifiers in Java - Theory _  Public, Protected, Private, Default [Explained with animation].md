These detailed notes are based on the video tutorial "Access Modifiers in Java - Theory," covering the definition, purpose, and scope of the four primary access modifiers.

---

# **Java Access Modifiers - Detailed Notes**

## **1. Introduction**
Access modifiers are keywords in Java used to set the **visibility** and **accessibility** of classes, variables, methods, and constructors. They are essential for achieving encapsulation by controlling which parts of a project can interact with specific data.

There are four levels of access:
1.  **Private**
2.  **Default** (no keyword)
3.  **Protected**
4.  **Public**

---

## **2. Detailed Breakdown of Modifiers**

### **A. Private Access Modifier**
*   **Keyword:** `private`
*   **Scope:** Narrowest scope (Class level).
*   **Visibility:** The variable or method is visible **only** within the class in which it is declared.
*   **Restrictions:**
    *   Not accessible in subclasses.
    *   Not accessible by other classes in the same package.
    *   Not accessible anywhere else in the project.

### **B. Default Access Modifier**
*   **Keyword:** None (It is the "default" state if no keyword is provided).
*   **Scope:** Package level.
*   **Visibility:** The variable or method is visible to all classes within the **same package**.
*   **Restrictions:**
    *   Once you go outside that specific package, the variable becomes invisible.
    *   Attempting to access a default variable from another package results in a **compile-time error**.

### **C. Protected Access Modifier**
*   **Keyword:** `protected`
*   **Scope:** Package level + Subclasses.
*   **Visibility:** 
    *   Visible to all classes in the **same package**.
    *   Visible to **subclasses** even if those subclasses are located in different packages.
*   **Restrictions:**
    *   Not accessible to classes in other packages that are *not* subclasses of the parent class.

### **D. Public Access Modifier**
*   **Keyword:** `public`
*   **Scope:** Project level (Widest scope).
*   **Visibility:** The variable or method is accessible from **anywhere** in the entire project.
*   **Restrictions:** No visibility restrictions.

---

## **3. Visibility Comparison (Hierarchy)**

The visibility of these modifiers can be visualized from the most restrictive to the least restrictive:

1.  **Private:** Class only.
2.  **Default:** Class + Package.
3.  **Protected:** Class + Package + Subclasses (even in different packages).
4.  **Public:** Everywhere (Project-wide).

---

## **4. Summary Table**

| Modifier | Class | Package | Subclass (Different Pkg) | Project (Everywhere) |
| :--- | :---: | :---: | :---: | :---: |
| **Private** | Yes | No | No | No |
| **Default** | Yes | Yes | No | No |
| **Protected** | Yes | Yes | Yes | No |
| **Public** | Yes | Yes | Yes | Yes |

---

## **5. Key Examples from the Video**

*   **Declaring a Default Variable:**
    `String animalName = "Lion";` (No keyword used).
*   **Declaring a Private Variable:**
    `private String animalName = "Lion";`
*   **The "Mouse" Variable Analogy:**
    *   If `Mouse` is **Private**: It stays inside the "Class Box."
    *   If `Mouse` is **Default**: It stays inside the "Package Box."
    *   If `Mouse` is **Protected**: It stays in the "Package Box" but can travel to "Subclass Boxes" in other packages.
    *   If `Mouse` is **Public**: It can roam the entire "Project Scope."

## **6. Conclusion**
Access modifiers are the primary tool for managing visibility in Java. Choosing the right modifier ensures that data is hidden where necessary (Private) and available where required (Public), helping to prevent bugs and maintain code security.