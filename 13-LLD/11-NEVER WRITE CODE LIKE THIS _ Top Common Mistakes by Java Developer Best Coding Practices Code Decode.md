These notes cover the top common mistakes made by Java developers and the best coding practices to avoid them, as discussed in the video "NEVER WRITE CODE LIKE THIS" by Code Decode.

---

### **1. Improper Naming Conventions**
Developers often fail to distinguish between nouns and verbs or misuse casing, which makes the code less professional and harder to read.
*   **Classes & Interfaces:** Should always be **Nouns** and use **PascalCase** (e.g., `Car`, `UserAccount`).
*   **Methods:** Should always be **Verbs** and use **camelCase** (e.g., `startEngine()`, `calculateTotal()`). 
    *   *Avoid:* Naming a class `DriveCar` (this is a verb phrase, not a noun).
*   **Variables:** Use **camelCase** (e.g., `userName`, `totalPrice`).
*   **Packages:** Should be strictly **lowercase** (e.g., `com.codedecode.service`).

---

### **2. Empty Catch Blocks**
Using `try-catch` to prevent a crash but leaving the `catch` block empty is a dangerous practice.
*   **The Problem:** If an exception occurs (e.g., `ArithmeticException`), the program continues silently. You will never know what went wrong, making debugging impossible.
*   **Best Practice:** Never leave a catch block empty. 
    *   Log the error.
    *   Rethrow a custom exception.
    *   Return a user-friendly message.

---

### **3. Inefficient String Manipulation**
Using the `String` class for repeated concatenation (e.g., in loops or building long messages).
*   **The Problem:** Strings in Java are **immutable**. Every time you use the `+` operator to append text, a new String object is created in the String Constant Pool. This wastes Heap memory.
*   **Best Practice:** Use `StringBuilder` (for non-thread-safe operations) or `StringBuffer` (for thread-safe operations). They modify the same object in memory rather than creating new ones.

---

### **4. Violating DRY and KISS Principles**
*   **DRY (Don't Repeat Yourself):** Avoid duplicating logic in different methods. If two methods perform the same logic, extract that logic into a single reusable helper method.
*   **KISS (Keep It Simple, Stupid):** Do not over-engineer.
    *   *Example:* Creating unnecessary intermediate variables just to print a value complicates the code.
    *   Keep the system as simple as possible to improve readability and reduce memory overhead.

---

### **5. Concurrent Modification Exception**
A common mistake occurs when a developer tries to modify a collection (adding or removing elements) while iterating over it.
*   **The Problem:** Modifying the underlying collection while an iterator is active results in a `ConcurrentModificationException`.
*   **Best Practice:** Avoid structural modifications to a list while looping through it. If you must remove items, use `Iterator.remove()` or specialized concurrent collections.

---

### **6. Comparing Strings/Wrappers with `==`**
Using the double equal operator for object comparison.
*   **The Problem:** `==` compares **memory addresses** (references), not the actual values. Even if two Strings have the same content, `==` will return `false` if they were created as different objects (e.g., using `new String()`).
*   **Best Practice:** Always use the `.equals()` method to compare the content of Strings or Wrapper classes (like `Integer`, `Long`).

---

### **7. Misusing Java Optional**
`Optional` was introduced to prevent `NullPointerException`, but developers often use it incorrectly.
*   **The Problem:** 
    1.  Returning `null` from a method that is supposed to return an `Optional`.
    2.  Comparing an `Optional` object with `null` (e.g., `if (myOptional == null)`).
*   **Best Practice:** 
    *   Always return `Optional.empty()` instead of `null`.
    *   Use `.isPresent()` or `.ifPresent()` to handle the value inside the container.

---

### **8. High Cognitive Complexity**
Cognitive complexity refers to the mental effort required to understand a piece of code.
*   **The Problem:** Deeply nested `if-else` statements, multiple nested loops, and long methods with complex logic make code unmaintainable.
*   **Best Practice:** 
    *   Break down large methods into smaller, Standalone methods.
    *   Give methods descriptive names that explain their logic (e.g., `isValidOrder()`, `applyDiscount()`).
    *   This improves readability and makes the code "self-documenting."

---

### **9. Inefficient Map Iteration**
Using `keySet()` when both the key and the value are needed.
*   **The Problem:** If you use `map.keySet()` and then call `map.get(key)` inside the loop, the JVM has to perform a lookup in the map for every single iteration. This is computationally expensive.
*   **Best Practice:** Use `map.entrySet()`. This returns a set of Entry objects containing both the key and the value, allowing you to access both without additional lookups.

---

### **Course Announcement (Context)**
The video intro mentions a "Full Stack Application" course covering:
*   **Front-end:** Angular.
*   **Back-end:** Java, Spring Boot, Microservices.
*   **Cloud/DevOps:** AWS (EKS, RDS), Docker, Kubernetes, Jenkins, Argo CD (CI/CD).
*   **Databases:** SQL and NoSQL (MongoDB).
*   **Quality:** JUnit for testing, Sonar for code quality checks.