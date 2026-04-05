# Java Design Pattern Interview Notes

## 1. Introduction to Design Patterns
Design patterns are documented, tried, and tested solutions to common software development problems. They provide a standard terminology and approach that software architects, engineers, and developers use to ensure applications are scalable, maintainable, and efficient.

### Why Use Design Patterns?
*   **Proven Solutions:** They reduce technical risk by using tested designs rather than unproven custom solutions.
*   **Standardization:** They provide a common language for developers, making code easier to understand and document.
*   **Efficiency:** They save time and effort during the Software Development Life Cycle (SDLC).
*   **Language Neutral:** While implemented in Java here, these concepts apply to any Object-Oriented Programming (OOP) language (C++, C#, etc.).

---

## 2. Categories of Design Patterns
Design patterns are broadly divided into four categories:
1.  **Creational:** Focus on how objects are created.
2.  **Behavioral:** Focus on communication between objects.
3.  **Structural:** Focus on the composition of classes and objects.
4.  **J2EE (Java 2 Enterprise Edition):** Focus on presentation and business tiers.

*Note: These notes focus specifically on **Creational Design Patterns**.*

---

## 3. Creational Design Patterns
Creational patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation.

### A. Factory Design Pattern
The Factory pattern hides the creation logic from the client and refers to the newly created object using a common interface.

*   **Core Concept:** A "Factory Class" is responsible for creating objects based on a type passed to it.
*   **Implementation Steps:**
    1.  Create an interface (e.g., `Profession`).
    2.  Create concrete classes implementing the interface (e.g., `Doctor`, `Engineer`, `Teacher`).
    3.  Create a `ProfessionFactory` class with a method (e.g., `getProfession(String type)`) that returns the appropriate object using the `new` keyword.
    4.  The client calls the factory to get an object without knowing the internal instantiation logic.
*   **Synonym:** Also known as a "Virtual Constructor."

### B. Abstract Factory Design Pattern
Known as a **"Factory of Factories."** This pattern provides an interface for creating families of related objects without specifying their concrete classes.

*   **Core Concept:** It adds another layer of abstraction over the Factory pattern.
*   **Example from Video:** 
    *   You have a `Profession` interface.
    *   Concrete classes are divided into two categories: **Professional** (Doctor, Engineer) and **Trainee** (Trainee Doctor, Trainee Engineer).
    *   You have two factories: `ProfessionalFactory` and `TraineeFactory`.
    *   A `FactoryProducer` (the Factory of Factories) decides which factory to provide to the client based on a "Trainee" flag.
*   **Usage:** Used when the system needs to be independent of how its products are created and composed.

### C. Singleton Design Pattern
The Singleton pattern ensures that a class has only one instance and provides a global point of access to it.

*   **Core Concept:** One single object is shared across the entire application.
*   **Three Essential Steps for Implementation:**
    1.  **Private Static Variable:** Create a private static instance of the class (e.g., `private static Singleton instance = new Singleton();`).
    2.  **Private Constructor:** Make the constructor private so the class cannot be instantiated from outside using the `new` keyword.
    3.  **Public Static Getter:** Provide a public static method (e.g., `getInstance()`) that returns the single instance.
*   **Eager vs. Lazy Loading:**
    *   *Eager:* Instance is created at the time of class loading.
    *   *Lazy:* Instance is created inside `getInstance()` only when it is requested for the first time.

### D. Prototype Design Pattern
The Prototype pattern is used to create duplicate objects while keeping performance in mind.

*   **Core Concept:** Instead of creating a new object using the `new` keyword (which might be "costly"), it **clones** an existing object.
*   **When to use:** When object creation is expensive (e.g., it involves heavy database calls or high memory/processing time).
*   **Implementation Steps:**
    1.  Implement the `Cloneable` interface.
    2.  Override the `clone()` method.
    3.  Create a "Cache" (like a Map) to store original objects.
    4.  When a client asks for an object, return a `clone` of the cached object rather than a new instance or the original reference.
*   **Interview Tip:** Cloning creates a different memory instance (different hashcode), so it is not a Singleton.

### E. Builder Design Pattern
The Builder pattern is used to construct a complex object step-by-step.

*   **Core Concept:** It separates the construction of a complex object from its representation. This is useful when an object requires many steps or parameters to be initialized.
*   **Key Roles:**
    1.  **Product:** The complex object being built (e.g., a `Home`).
    2.  **Builder (Interface):** Defines the steps to build the product (e.g., `buildFloor()`, `buildWalls()`).
    3.  **Concrete Builder:** Implements the steps for a specific version (e.g., `FloodResistantBuilder` or `EarthquakeResistantBuilder`).
    4.  **Director:** Manages the construction process. The client talks to the Director, and the Director tells the Builder what to do.
*   **Example Workflow:**
    *   Client wants an "Earthquake Resistant Home."
    *   Director takes the `EarthquakeBuilder`.
    *   Director calls the builder steps: build wooden floor -> build wooden walls -> build terrace.
    *   Director returns the final `Home` object to the client.

---

## 4. Summary Table

| Pattern | Intent | Key Feature |
| :--- | :--- | :--- |
| **Factory** | Hides creation logic | Standard Interface, Factory Class. |
| **Abstract Factory** | Factory of Factories | Double layer abstraction; returns factories. |
| **Singleton** | Single instance only | Private constructor, Static instance. |
| **Prototype** | Object Cloning | Uses `clone()`; avoids costly `new` calls. |
| **Builder** | Step-by-step construction | Director manages complex object creation. |