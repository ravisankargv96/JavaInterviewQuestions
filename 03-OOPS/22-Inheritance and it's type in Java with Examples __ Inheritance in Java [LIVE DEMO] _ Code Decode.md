These notes provide a comprehensive breakdown of the video "Inheritance and its types in Java" by Code Decode, focusing on the practical implementation of Object-Oriented Programming (OOP) concepts.

---

### **1. Introduction to Inheritance**
Inheritance is a fundamental OOP concept that establishes an **"Is-A" relationship** between two classes. It allows a child class to inherit and reuse the properties (fields) and behaviors (methods) of a parent class.

*   **Parent Class:** Also known as the Base Class or Super Class.
*   **Child Class:** Also known as the Subclass or Derived Class.
*   **Keyword:** In Java, the `extends` keyword is used to implement inheritance.

---

### **2. Single Inheritance**
Single inheritance occurs when one class extends another single class.

*   **Scenario:** An `Animal` class is the parent, and a `Dog` class is the child.
*   **Implementation Example:**
    *   **Class Animal:** Contains properties like `int eyes = 2` and `int legs = 4`.
    *   **Class Dog:** Uses the syntax `class Dog extends Animal`.
*   **Key Takeaway:** Even if the `Dog` class is empty, an object of the `Dog` class (e.g., `Tommy`) can access the `eyes` and `legs` variables because they are inherited from the `Animal` class.
*   **Benefit:** Code reusability. You don't need to redefine common animal traits in every specific animal class.

---

### **3. Multi-level Inheritance**
Multi-level inheritance involves a chain of classes where a class acts as both a child and a parent.

*   **Scenario:** `Animal` (Grandparent) $\rightarrow$ `Dog` (Parent) $\rightarrow$ `Labrador` (Child).
*   **Implementation:**
    *   `Dog` extends `Animal`.
    *   `Labrador` extends `Dog`.
*   **Behavior:** The `Labrador` class inherits properties from the `Dog` class as well as the `Animal` class.
*   **Example:** A `Labrador` object can call a `bark()` method defined in the `Dog` class and access the `eyes` variable defined in the `Animal` class.

---

### **4. Hierarchical Inheritance**
In hierarchical inheritance, one parent class is extended by multiple child classes.

*   **Scenario:** `Animal` is the parent to `Dog`, `Fish`, and `Frog`.
*   **Logic:**
    *   `Dog extends Animal`: Inherits 2 eyes; has a unique `bark()` method.
    *   `Fish extends Animal`: Inherits 2 eyes; has a unique `swim()` method.
    *   `Frog extends Animal`: Inherits 2 eyes; has a unique property of being an `amphibian`.
*   **Key Takeaway:** All children share the common features of the parent (`Animal`) but implement their own specific behaviors.

---

### **5. Hybrid Inheritance**
Hybrid inheritance is a combination of two or more types of inheritance (e.g., Single + Hierarchical).

*   **Example from Video:**
    *   The relationship between `Animal`, `Dog`, `Fish`, and `Frog` is **Hierarchical**.
    *   The relationship between `Dog` and `Labrador` is **Single**.
    *   The entire structure combined represents **Hybrid Inheritance**.

---

### **6. Multiple Inheritance (Not Supported via Classes)**
Multiple inheritance is when one child class attempts to inherit from more than one parent class simultaneously.

*   **Scenario Attempted:** A `Frog` class trying to extend both `TerrestrialAnimal` (lives on land) and `AquaticAnimal` (lives in water).
*   **Java's Limitation:** Java **does not allow** multiple inheritance using classes. 
    *   **Syntax Error:** `class Frog extends TerrestrialAnimal, AquaticAnimal` will result in a compile-time error.
*   **Reasoning:** This restriction exists to reduce complexity and avoid ambiguity (the "Diamond Problem").
*   **The Solution:** While not possible via classes, multiple inheritance can be achieved in Java using **Interfaces**.

---

### **7. Summary of Benefits**
1.  **Reusability:** Common code is written once in the parent class and reused across all children.
2.  **Extensibility:** New features can be added to specific child classes without modifying the parent or other children.
3.  **Organization:** It allows developers to model real-world entities accurately using hierarchies.