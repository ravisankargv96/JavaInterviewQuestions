These notes cover the essential design pattern interview questions and concepts discussed in the "Code Decode" video for 2025 preparation.

---

### 1. What are Design Patterns and why are they used?
Design patterns are well-proven, documented solutions to recurring software design problems. They represent the "best practices" evolved over time by experienced software developers.

**Key Benefits:**
*   **Reusability:** Provides a template for solving common problems.
*   **Standardization:** Offers a common vocabulary for developers (e.g., saying "we need an Adapter here" explains the architecture immediately).
*   **Maintainability:** Decouples code, making it easier to change or scale without breaking existing logic.
*   **Readability:** Code becomes easier to understand for new developers joining a project.

---

### 2. Classification of Design Patterns
Design patterns are categorized into three main types based on their purpose:

*   **Creational Patterns:** Focus on the process of object creation. They help make a system independent of how its objects are created, composed, and represented.
    *   *Examples:* Singleton, Factory, Abstract Factory, Builder, Prototype.
*   **Structural Patterns:** Deal with the composition of classes and objects to form larger structures.
    *   *Examples:* Adapter, Decorator, Facade, Proxy, Bridge.
*   **Behavioral Patterns:** Focus on communication between objects and how responsibilities are assigned.
    *   *Examples:* Observer, Strategy, Command, State, Iterator.

---

### 3. The Singleton Design Pattern (Detailed)
This is the most frequently asked pattern in interviews. It ensures a class has only **one instance** and provides a global point of access to it.

**How to implement it correctly:**
1.  **Private Constructor:** Prevents other classes from instantiating it.
2.  **Private Static Variable:** To hold the single instance.
3.  **Public Static Method:** To return the instance (Global Access).

**The Thread-Safe Approach (Double-Checked Locking):**
In a multi-threaded environment, two threads could enter the `if (instance == null)` block simultaneously. To prevent this, use the `volatile` keyword and a `synchronized` block:
*   **Volatile Keyword:** Ensures visibility of changes to the variable across threads.
*   **Double-Check:** Check if the instance is null, then lock, then check again.

**Ways to "Break" a Singleton and how to prevent it:**
*   **Reflection:** Can access private constructors. *Prevention:* Throw an exception from the constructor if an instance already exists.
*   **Serialization:** Reading an object back creates a new instance. *Prevention:* Implement the `readResolve()` method to return the existing instance.
*   **Cloning:** The `clone()` method creates a copy. *Prevention:* Override `clone()` to throw a `CloneNotSupportedException`.
*   **Best Solution:** Use an **Enum**, as Java handles all the above issues natively.

---

### 4. Factory vs. Abstract Factory Pattern

**Factory Design Pattern:**
*   It creates objects without exposing the instantiation logic to the client.
*   A "Factory" class has a method that returns different subclasses based on the input provided.
*   *Example:* A `ShapeFactory` that returns a `Circle`, `Square`, or `Rectangle` based on a string input.

**Abstract Factory Pattern:**
*   Often called a "Factory of Factories."
*   It provides an interface for creating families of related or dependent objects without specifying their concrete classes.
*   *Example:* A `UIFactory` that creates a `WindowsButton` and `WindowsCheckbox` for Windows OS, or a `MacButton` and `MacCheckbox` for macOS.

---

### 5. The Observer Design Pattern
This is a behavioral pattern used to define a **one-to-many** dependency between objects.

*   **Mechanism:** When one object (the **Subject**) changes state, all its dependents (**Observers**) are notified and updated automatically.
*   **Real-world analogy:** A YouTube Channel (Subject) and its Subscribers (Observers). When a video is uploaded, all subscribers get a notification.
*   **Components:**
    1.  `Subject`: Maintains a list of observers and provides methods to attach/detach them.
    2.  `Observer`: Interface defining the update method.
    3.  `ConcreteObserver`: Implements the update logic.

---

### 6. The Builder Design Pattern
Interviews often focus on this when discussing objects with many attributes.

*   **The Problem:** "Telescoping Constructors" (having constructors with 10+ parameters) or using too many Setters (which makes the object mutable and inconsistent during creation).
*   **The Solution:** The Builder pattern allows you to construct complex objects step-by-step.
*   **Key Benefits:**
    *   Immutable objects (attributes are final).
    *   Improved readability (you can see exactly which field is being set).
    *   No need for null values in constructors for optional parameters.

---

### Summary Table for Quick Revision

| Pattern | Category | Core Purpose |
| :--- | :--- | :--- |
| **Singleton** | Creational | Single instance per JVM. |
| **Factory** | Creational | Creates objects based on logic, hiding instantiation. |
| **Abstract Factory** | Creational | Creates families of related objects. |
| **Builder** | Creational | Step-by-step construction of complex objects. |
| **Adapter** | Structural | Allows incompatible interfaces to work together. |
| **Observer** | Behavioral | Notifies multiple objects of state changes. |
| **Strategy** | Behavioral | Switches between different algorithms at runtime. |