These notes are based on the "Encapsulation in Java-theory" video by Code Decode. They cover the fundamental concepts, implementation strategies, and the key advantages of using encapsulation in Java.

---

# Detailed Notes: Encapsulation in Java

## 1. Introduction to Encapsulation
Encapsulation is one of the four fundamental principles of Object-Oriented Programming (OOP). It is often referred to as **Data Hiding**. The primary goal is to keep data and the logic that operates on that data safe from external interference and misuse.

### The Chocolate Analogy
To remember encapsulation, think of a **chocolate bar with a wrapper**:
*   **The Wrapper:** Represents the **Class**.
*   **The Content (Chocolate):** Represents the **Data (variables)** and the **Logic (methods)**.
*   The wrapper protects the contents from the outside world. Only when you interact with the "wrapper" (the class) in the correct way can you access what is inside.

---

## 2. Definition and Core Concept
Encapsulation is the process of wrapping variables (data) and methods (code) into a single unit known as a **Class**.

*   **Logic and Data Integration:** In Java, variables and the methods that act upon them are bundled together.
*   **Access Restriction:** Data inside the class is hidden from the outside world. It cannot be seen or accessed directly by other classes.
*   **Access via Objects:** To access the data, you must create an object of the class and use its public methods.

---

## 3. How to Implement Encapsulation
To achieve encapsulation in Java, you follow two main steps:

1.  **Declare variables as `private`:** This ensures that no outside class can access the variables directly (e.g., `private int age;`).
2.  **Provide public Getter and Setter methods:** These methods allow the outside world to read or update the variable values in a controlled manner.

**Example Workflow:**
*   Create a class (e.g., `Dog`).
*   Define a private variable (e.g., `name`).
*   Create a public method `getName()` (Getter) and `setName()` (Setter).
*   In the main program, you create an object: `Dog myDog = new Dog();`.
*   You then call `myDog.setName("Tommy");` to modify the data.

---

## 4. Advantages of Encapsulation

### A. Read-Only or Write-Only Access
Encapsulation gives you the flexibility to control how data is accessed:
*   **Read-Only:** Provide only **Getter** methods. Other classes can see the value but cannot change it.
*   **Write-Only:** Provide only **Setter** methods. Other classes can set or update the value but cannot retrieve it.

### B. Control Over Data (Validation)
Encapsulation allows you to control *what* is stored in your variables. Since data is changed only through setter methods, you can add logic/validation inside the setter.
*   *Example:* In a `setAge(int value)` method, you can add a condition: "Only save the value if it is greater than 0." This prevents invalid data from being stored in your object.

### C. Data Hiding
This is a security benefit. Other classes do not need to know *how* a class stores its data; they only need to know how to interact with the public methods. This prevents different objects from interfering with each other's internal states.

### D. Ease of Development (IDE Support)
Modern Integrated Development Environments (IDEs) like **Eclipse** make implementing encapsulation very easy.
*   You can automatically generate Getters and Setters by right-clicking, selecting "Source," and choosing "Generate Getters and Setters."
*   This allows you to quickly choose which variables should be accessible to the outside world and which should remain strictly private.

---

## 5. Summary Key Points
*   **Encapsulation** = Data + Methods wrapped in a Class.
*   **Implementation** = Private Variables + Public Getters/Setters.
*   **Primary Benefit** = Security, data validation, and control over how data is read or modified.
*   **Logical Entity** = The class acts as a protective container that prevents unauthorized access to the internal logic.