# Detailed Notes: Inheritance in Java

## 1. Introduction to Object-Oriented Programming (OOP)
Java is an Object-Oriented Programming language, meaning the entire language revolves around **Objects**. An object is a real-world entity (like a dog) that can be represented in code.

### The Six Basic OOP Concepts:
1.  **Object:** An entity that has **state** (attributes like name, color, breed) and **behavior** (actions like barking, eating, wagging tail).
2.  **Class:** A logical entity or a **blueprint** for creating objects. It does not consume memory; memory is only allocated when an object is created.
3.  **Inheritance:** (The focus of this session).
4.  **Polymorphism**
5.  **Abstraction**
6.  **Encapsulation**

*Note: Other advanced concepts include coupling, cohesion, association, aggregation, and composition.*

---

## 2. What is Inheritance?
Inheritance is a mechanism in which one object (the child) acquires all the properties and behaviors of a parent object.

*   **Example:** If there is a class `Animal` with features like "two eyes" and "four legs," and a class `Dog` inherits from it, the `Dog` class automatically gets those features without having to redefine them.
*   **The "Is-A" Relationship:** Inheritance represents the **IS-A** relationship. 
    *   *Example:* A Dog **is an** Animal. A Cat **is an** Animal.

### Why Use Inheritance?
*   **Method Overriding:** To achieve runtime polymorphism.
*   **Code Reusability:** It allows developers to reuse the fields and methods of an existing class, reducing the amount of redundant code.

---

## 3. Key Terminologies
*   **Superclass (Parent Class):** The class whose features are inherited.
*   **Subclass (Child Class):** The class that inherits from another class. It can also add its own specific methods and fields.
*   **Reusability:** The facility to use the methods and fields of an existing class when you create a new class.
*   **`extends` Keyword:** This is the Java keyword used to perform inheritance. It indicates that you are making a new class that derives from an existing class.

---

## 4. Types of Inheritance in Java

### A. Single Inheritance
In single inheritance, one child class extends only one parent class. It involves a simple two-class relationship.
*   **Example:** `Animal` (Parent) $\rightarrow$ `Dog` (Child).
*   The Dog inherits the properties of the Animal.

### B. Multi-level Inheritance
In multi-level inheritance, a class extends a child class, creating a chain.
*   **Example:** `Animal` (Parent) $\rightarrow$ `Dog` (Child/Intermediate) $\rightarrow$ `Labrador` (Grandchild).
*   In this case, `Dog` acts as a subclass for `Animal` but acts as a superclass for `Labrador`. 
*   `Labrador` inherits features from both `Dog` (e.g., barking) and `Animal` (e.g., eyes/legs).

### C. Hierarchical Inheritance
In hierarchical inheritance, one parent class is extended by multiple child classes.
*   **Example:** `Animal` is the parent, and `Dog`, `Fish`, and `Frog` are all subclasses.
*   All three child classes share common functionality (like "having eyes") from the same parent.

### D. Hybrid Inheritance
Hybrid inheritance is a combination of two or more types of inheritance (e.g., a mix of Single and Hierarchical).
*   **Example:** A structure where `Animal` has children `Dog` and `Fish`, and `Dog` has a child `Labrador`.

### E. Multiple Inheritance (Not Supported via Classes)
Multiple inheritance is when one class tries to extend more than one parent class.
*   **Example:** An `Amphibian` class trying to inherit from both `Terrestrial Animals` and `Aquatic Animals`.
*   **Why Java does not allow this:** It creates complexity and ambiguity. If two parent classes have the same method name but different implementations, the compiler wouldn't know which one to pick (the "Diamond Problem"). To keep the language simple and reduce complexity, Java **does not support** multiple inheritance through classes.

---

## 5. Summary
*   Inheritance promotes **reusability**.
*   The **`extends`** keyword is the core of inheritance.
*   Java supports **Single, Multi-level, and Hierarchical** inheritance.
*   Java **does NOT support Multiple Inheritance** with classes to avoid technical complexity.