# SOLID Design Principles: Interview Questions & Answers

These notes are based on the "Code Decode" video covering SOLID principles, specifically focusing on the "Why" behind these principles, the Single Responsibility Principle (S), and the Open/Closed Principle (O).

---

## 1. Introduction to SOLID Principles

### Why do we need SOLID principles?
Standard design principles are often not enough to handle the complexities of evolving software. SOLID principles are used to:
*   **Reduce Dependencies:** Ensure that changing one area of the software does not impact or break other areas.
*   **Handle Changing Requirements:** In the IT world, requirements change constantly. SOLID helps segregate code so that changes are standalone and don’t ripple through the entire system.
*   **Improve Code Quality:** They make designs easier to understand, maintain, and extend.
*   **Build Agile Software:** They help avoid common issues, making the software more adaptive and testable.

### The SOLID Acronym
*   **S:** Single Responsibility Principle (SRP)
*   **O:** Open/Closed Principle (OCP)
*   **L:** Liskov Substitution Principle (LSP)
*   **I:** Interface Segregation Principle (ISP) — *Similar to SRP, but for interfaces.*
*   **D:** Dependency Inversion Principle (DIP)

---

## 2. Single Responsibility Principle (SRP)

### Definition
A class should have **one, and only one, responsibility**. This means there should be only one reason to change a class.

### Example: Employee and Address
*   **The Violation:** Including address fields (like street, city, etc.) directly inside an `Employee` POJO. If the address format changes (e.g., adding a third address line), you are forced to modify the `Employee` class.
*   **The Solution:** Create a separate `Address` class. The `Employee` class should only be responsible for employee-specific state (ID, Name). It can then contain a reference to an `Address` object.

### The "Worst" Violation of SRP
The most significant violation in real-world applications is **merging the Entity with the Service or Repository layers.**
*   **Proper Segregation:**
    *   **Entity:** Responsible only for state and data transferring.
    *   **Service:** Responsible only for business logic.
    *   **Repository:** Responsible only for DAO (Data Access Object) handling and data manipulation (saving/deleting).
*   **Rule:** You should never write saving logic or complex business logic inside an Entity class.

### Why is SRP Important?
1.  **Lower Frequency of Changes:** The more responsibilities a class has, the more often it will need to change. Frequent changes increase the risk of breaking existing code.
2.  **Easier Testing:** Classes with a single responsibility have fewer test cases, making them easier to validate.
3.  **Readability/Understandability:** It is easier for developers to find where specific logic resides.
4.  **Less Dependencies:** Smaller, focused classes have fewer moving parts.

---

## 3. Open/Closed Principle (OCP)

### Definition
Software components (classes, modules, functions) should be **open for extension but closed for modification.**
*   **Open for Extension:** You should be able to add new functionality.
*   **Closed for Modification:** You should be able to add that new functionality without altering the existing, already-tested implementation.

### Why is OCP Critical?
In the real world, adding a new feature often breaks existing functionality. Implementing OCP reduces the risk of breaking "old" code by nearly **90%** because you aren't touching the original source code to add new features.

### Implementation Strategy
Instead of modifying an existing class, a developer should design the system so that new behavior can be added by **extending** the class and overriding functions.

### Example: Adding Training Details to Employee
*   **The Scenario:** You have an existing `Employee` class with `id` and `name`. Now, a requirement comes to include `trainingLocation` for specific employees.
*   **The Violation (Modification):** Adding `trainingLocation` directly to the `Employee` class and updating the constructor. This will break every single class in the system that currently instantiates an `Employee` object.
*   **The OCP Solution (Extension):**
    1.  Keep the `Employee` class exactly as it is (Closed for modification).
    2.  Create a new class `TraineeEmployee` that **extends** `Employee`.
    3.  Add the `trainingLocation` field and specific logic to the `TraineeEmployee` class (Open for extension).
    4.  The existing code continues to use `Employee` without issues, while the new requirement is handled by the subclass.

### Benefits of OCP
*   **Sustainability:** Code remains stable over time.
*   **Maintainability:** Old code remains untouched and "safe," while new code is isolated in its own space.
*   **Minimizes Risk:** Prevents the "ripple effect" where a change in one place causes errors elsewhere.

---

## Summary Table

| Principle | Core Concept | Main Benefit |
| :--- | :--- | :--- |
| **SRP** | One class = One task | Better testability and fewer reasons to change a class. |
| **OCP** | Extend, don't modify | Prevents existing code from breaking when adding features. |
| **LSP** | Subtypes must be substitutable | Ensures inheritance is used correctly. |
| **ISP** | Split large interfaces | Prevents classes from being forced to implement unused methods. |
| **DIP** | Depend on abstractions | Decouples high-level modules from low-level details. |