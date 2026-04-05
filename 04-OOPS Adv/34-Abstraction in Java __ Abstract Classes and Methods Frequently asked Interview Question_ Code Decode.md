These detailed notes are based on the video "Abstraction in Java || Abstract Classes and Methods Frequently asked Interview Question" by Code Decode.

---

### **1. What is Abstraction?**
Abstraction is a fundamental concept in Object-Oriented Programming (OOPs) that focuses on hiding the internal implementation details and showing only the essential features to the user.

*   **Key Idea:** It tells you **what** an object does rather than **how** it does it.
*   **Real-World Example (The Car):** 
    *   When learning to drive, you are taught how to use the gears (e.g., use 1st gear for starting, 3rd gear when over 30 km/h). 
    *   You are **not** taught the internal fluid mechanics, how the engine connects to the wheels, or the complex engineering inside the gearbox.
    *   The complexity is hidden to keep the operation simple for the driver.

---

### **2. How to Achieve Abstraction in Java**
There are two primary ways to implement abstraction in Java:
1.  **Abstract Classes:** Achieve partial to full abstraction (0% to 100%).
2.  **Interfaces:** Achieve total abstraction (100%—noting that this changed slightly after Java 8, but traditionally interfaces represent full abstraction).

---

### **3. Abstract Methods**
An abstract method is a method that is declared without an implementation.
*   **Syntax:** It uses the `abstract` keyword and ends with a semicolon (`;`) instead of curly braces `{}`.
*   **Structure:** It has a method signature (name, return type, parameters) but no body.
*   **Rule:** If a class contains at least one abstract method, the class itself **must** be declared abstract.

---

### **4. Abstract Classes**
An abstract class is a class declared with the `abstract` keyword.

#### **Key Characteristics:**
*   **Instantiation:** You **cannot** create an object (instantiate) of an abstract class using the `new` keyword.
*   **Methods:** It can contain both abstract methods (no body) and concrete methods (with a body).
*   **Constructors:** It can have constructors and static methods.
*   **Final Methods:** It can have `final` methods, which prevent subclasses from changing the method's implementation.

#### **Interview Tip: Why make a class abstract if it has no abstract methods?**
You can declare a class as `abstract` even if all its methods are concrete. This is done for **security purposes** to prevent the class from being instantiated directly. This forces developers to inherit the class if they want to use its functionality.

---

### **5. Interfaces**
Interfaces are used when you want to define a contract of "what" needs to be done, leaving the "how" to the subclasses.

#### **Key Characteristics:**
*   **Keyword:** Declared using the `interface` keyword.
*   **Methods:** By default, all methods in an interface are `public` and `abstract`. You don't need to write these keywords explicitly.
*   **Variables:** All fields/variables in an interface are implicitly `public`, `static`, and `final`. This means their values cannot be changed by the implementing classes.
*   **Purpose:** They are used to achieve 100% abstraction and to support **multiple inheritance**.

---

### **6. Multiple Inheritance in Java**
Java does not support multiple inheritance with classes but does support it with interfaces.

#### **The Ambiguity Issue (Diamond Problem):**
*   If Class C extends Class A and Class B, and both A and B have a method `add()` with different implementations, Class C would not know which version of `add()` to use. This creates ambiguity.
*   Java prevents this by not allowing a class to extend more than one class.

#### **Why Interfaces Solve This:**
*   Interfaces do not have method bodies (implementation).
*   If a class implements two interfaces that have the same method signature, there is no confusion because the child class **must** provide its own single implementation for that method.
*   Since the interfaces provide no logic, there is no conflicting logic to choose from.

---

### **7. Summary Table: Abstract Class vs. Interface**

| Feature | Abstract Class | Interface |
| :--- | :--- | :--- |
| **Abstraction Level** | 0 to 100% | 100% (Total Abstraction) |
| **Methods** | Can have both abstract and concrete methods. | Only abstract methods (prior to Java 8). |
| **Variables** | Can have final, non-final, static, and non-static variables. | Only `public static final` (constants). |
| **Inheritance** | A class can extend only one abstract class. | A class can implement multiple interfaces. |
| **Keyword** | `abstract` | `interface` |
| **Instantiation** | Cannot be instantiated. | Cannot be instantiated. |