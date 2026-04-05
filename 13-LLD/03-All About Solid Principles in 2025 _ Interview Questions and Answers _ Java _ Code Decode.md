These notes provide a comprehensive breakdown of the SOLID principles as presented in the "Code Decode" guide for 2025. These principles are essential for writing clean, maintainable, and scalable Java code, and they are a frequent topic in technical interviews.

---

### **Overview of SOLID Principles**
The SOLID principles are a set of five design principles in Object-Oriented Programming (OOP) introduced by Robert C. Martin (Uncle Bob). 
*   **Goal:** To make software designs more understandable, flexible, and maintainable.
*   **Benefits:** Reduces code fragility, prevents technical debt, and allows for easier unit testing and team collaboration.

---

### **1. S: Single Responsibility Principle (SRP)**
**Definition:** A class should have one, and only one, reason to change. This means a class should only have one job or responsibility.

*   **Problem:** If a class has multiple responsibilities (e.g., calculating salary, saving to a database, and sending emails), a change in the email logic might accidentally break the salary calculation logic.
*   **Solution:** Break the "God Class" into smaller, specialized classes.
*   **Java Example:**
    *   *Bad:* An `Invoice` class that calculates totals, prints the invoice, and saves it to a DB.
    *   *Good:* Create an `Invoice` class for data, an `InvoicePrinter` class, and an `InvoiceRepository` class for DB operations.

---

### **2. O: Open/Closed Principle (OCP)**
**Definition:** Software entities (classes, modules, functions) should be open for extension but closed for modification.

*   **Problem:** Modifying existing, tested code to add new features is risky and can introduce bugs into stable parts of the system.
*   **Solution:** Use interfaces and abstract classes. When you need to add new functionality, create a new class that implements the interface rather than changing the existing code.
*   **Java Example:**
    *   *Bad:* Using `if-else` or `switch` statements inside a `PaymentProcessor` to handle "CreditCard," "PayPal," and "Crypto." Adding a new type requires modifying the existing class.
    *   *Good:* Create a `Payment` interface with a `process()` method. Each payment type (CreditCard, PayPal) implements this interface. Adding "Crypto" involves creating a new class without touching existing ones.

---

### **3. L: Liskov Substitution Principle (LSP)**
**Definition:** Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

*   **Problem:** Subclasses that override methods in a way that breaks the parent class’s expectations or throws "Not Implemented" exceptions.
*   **The "Square-Rectangle" Classic Problem:** A Square is mathematically a Rectangle, but if a `Rectangle` class has `setWidth()` and `setHeight()`, a `Square` subclass would violate expectations because changing the width also changes the height.
*   **Solution:** Ensure subclasses follow the "contract" of the parent class. If a subclass cannot perform the parent’s actions, rethink the inheritance hierarchy. Use composition over inheritance if necessary.

---

### **4. I: Interface Segregation Principle (ISP)**
**Definition:** Clients should not be forced to depend upon interfaces they do not use.

*   **Problem:** Creating "Fat Interfaces" that contain too many methods. A class implementing the interface might be forced to provide empty implementations for methods it doesn't need.
*   **Solution:** Split large interfaces into smaller, more specific ones.
*   **Java Example:**
    *   *Bad:* A `Worker` interface with `work()` and `eat()`. A `Robot` class implementing this must implement `eat()`, which makes no sense for a robot.
    *   *Good:* Separate into `Workable` and `Eatable` interfaces. `Human` implements both, while `Robot` only implements `Workable`.

---

### **5. D: Dependency Inversion Principle (DIP)**
**Definition:** 
1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
2. Abstractions should not depend on details. Details (concrete implementations) should depend on abstractions.

*   **Problem:** Hardcoding a specific database or service inside a high-level class makes the system rigid and difficult to test.
*   **Solution:** Use Dependency Injection (DI). Pass the dependency (via constructor or setter) as an interface rather than instantiating the concrete class inside the high-level module.
*   **Java Example:**
    *   *Bad:* A `NotificationService` class directly instantiates an `EmailSender` class.
    *   *Good:* `NotificationService` depends on a `MessageSender` interface. At runtime, you can "inject" an `EmailSender`, `SMSSender`, or even a `MockSender` for testing.

---

### **Common Interview Questions and Answers**

**Q1: Why are SOLID principles important in 2025?**
*   **A:** With the rise of Microservices and CI/CD, code needs to be modular and testable. SOLID ensures that changing one service doesn't cause a ripple effect across the system, facilitating faster deployment cycles.

**Q2: What is the difference between SRP and ISP?**
*   **A:** SRP is about the **class** level (a class should have one responsibility). ISP is about the **interface** level (a client should only see methods it needs).

**Q3: Does applying SOLID increase code complexity?**
*   **A:** Initially, it may increase the number of classes and interfaces (boilerplate). However, it significantly reduces "cyclomatic complexity" and long-term maintenance costs.

**Q4: How does DIP relate to Spring Framework?**
*   **A:** Spring’s core concept, Inversion of Control (IoC), is a practical implementation of DIP. The Spring Container manages the creation and injection of dependencies, so your classes only interact with abstractions (Interfaces).

**Q5: Can you explain the Liskov Substitution Principle with an example other than Square/Rectangle?**
*   **A:** Consider an `Ostrich` class inheriting from a `Bird` class that has a `fly()` method. Since an ostrich cannot fly, it violates LSP. To fix this, you should create a `FlyingBird` and `NonFlyingBird` subclass, or move `fly()` to a specific interface called `Flyable`.

---

### **Summary Checklist for Developers**
1.  **SRP:** Is this class doing too much?
2.  **OCP:** Can I add this feature without changing existing code?
3.  **LSP:** Is my subclass truly a "type of" the parent without limitations?
4.  **ISP:** Is this interface too "fat"?
5.  **DIP:** Am I depending on a concrete class or an interface?