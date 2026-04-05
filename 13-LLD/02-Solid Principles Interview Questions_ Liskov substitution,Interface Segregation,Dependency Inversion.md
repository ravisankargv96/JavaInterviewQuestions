These notes cover the second part of the SOLID principles: **Liskov Substitution (L)**, **Interface Segregation (I)**, and **Dependency Inversion (D)**.

---

### 1. Liskov Substitution Principle (LSP)

**Definition:**
Derived types must be completely substitutable for their base types. If a program is using a base class, it should be able to use any of its subclasses without the program failing or behaving unexpectedly.

**Key Concepts:**
*   **Proper Inheritance:** It ensures that the "is-a" relationship is implemented correctly.
*   **Contract Fulfillment:** A subclass must fulfill the "contract" of the base class. It should behave in the same way as the superclass so that the client code doesn't need to know which specific subclass it is handling.
*   **Desirable Results:** Replacing a parent object with a child object should not alter the desirable result of the software.

**Example from Video:**
*   **Base Class:** `Employee` with a method `printMe()`.
*   **Derived Class:** `TrainedEmployee` which extends `Employee`.
*   **Method:** A static method `useMeToPrintYourData(Employee e)` that calls `e.printMe()`.
*   **Application:** If you pass an instance of `Employee` to the method, it works. If you pass an instance of `TrainedEmployee` (the child), the method should still work perfectly without any changes to the code. If it breaks, you have violated LSP.

**Common Violation (Square-Rectangle Problem):**
In mathematics, a square is a rectangle. However, in code, if a `Rectangle` class has independent `setHeight` and `setWidth` methods, and a `Square` class overrides them to set both dimensions simultaneously, the `Square` fails to substitute the `Rectangle` because it breaks the expectation that height and width can be changed independently.

---

### 2. Interface Segregation Principle (ISP)

**Definition:**
Clients should not be forced to implement unnecessary methods that they do not use. This principle is essentially the "Single Responsibility Principle" applied to interfaces.

**The Problem (Fat Interfaces):**
When an interface is too "fat," it contains methods that aren't applicable to all its implementations. This forces implementing classes to provide empty or "dummy" implementations for methods they don't need.

**Example from Video:**
*   **The Bad Way:** A single `PizzaApp` interface containing methods for `acceptOrderOnline`, `acceptOrderOffline`, `acceptTelephonicOrder`, `payOnline`, and `payCash`.
*   **The Consequence:** A small local pizza shop that only accepts walk-ins and cash would still be forced to implement the `acceptOrderOnline` and `payOnline` methods because they are part of the interface.

**The Solution:**
Split large interfaces into smaller, more specific ones. 
*   Create an `OnlinePizzaApp` interface for online orders/payments.
*   Create an `OfflinePizzaApp` interface for walk-ins and cash payments.
*   Clients can then choose to implement only the interface(s) that are relevant to them.

**Benefit:**
Prevents the client from getting "stuck" with unwanted code and keeps the system modular.

---

### 3. Dependency Inversion Principle (DIP)

**Definition:**
Depend on abstractions, not on concretions. High-level modules should not depend on low-level modules; both should depend on abstractions (interfaces).

**Key Concepts:**
*   **Tight Coupling:** If Class A creates an instance of Class B using the `new` keyword, Class A is tightly coupled to Class B. If Class B changes, Class A may break.
*   **Loose Coupling:** By using abstractions (interfaces), we decouple the classes.

**Example from Video:**
*   **Tight Coupling (Violation):** A `Student` class where the constructor initializes an `Address` object using `new Address()`. If the `Address` class name changes or its constructor changes, you must manually update the `Student` class and every other class that uses `Address`.
*   **Loose Coupling (Solution):** Use **Dependency Injection**. Instead of the `Student` class creating the `Address`, the `Address` is "injected" into the `Student` class via a constructor or setter. 
*   In frameworks like Spring, this is achieved through `@Autowired`.

**How it helps:**
1.  **Removes Hardcoding:** Dependencies are managed externally (e.g., in an XML file or configuration class).
2.  **Maintainability:** If a dependency needs to change, you change it in one configuration place rather than in hundreds of individual classes.
3.  **Extensibility:** It becomes much easier to swap one implementation for another.

---

### Summary Checklist

| Principle | Core Idea | Focus Area |
| :--- | :--- | :--- |
| **Liskov Substitution** | Subclasses must be able to replace parent classes without errors. | Inheritance / Polymorphism |
| **Interface Segregation** | Split big interfaces into smaller, specific ones. | Interface Design |
| **Dependency Inversion** | Depend on interfaces/abstractions, not concrete classes. | Coupling / Dependency Injection |