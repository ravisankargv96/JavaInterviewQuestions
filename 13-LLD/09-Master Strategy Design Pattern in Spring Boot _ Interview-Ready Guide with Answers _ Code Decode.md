# Detailed Notes: Strategy Design Pattern in Spring Boot

These notes are based on the "Code Decode" guide for implementing the Strategy Design Pattern within a Spring Boot environment, focusing on interview readiness and best practices.

---

## 1. What is the Strategy Design Pattern?
The **Strategy Design Pattern** is a **behavioral design pattern** that enables the selection of an algorithm or behavior at runtime. 

*   **Core Concept:** Instead of implementing a single algorithm directly (or using a long list of `if-else` statements), the code receives runtime instructions to choose a specific algorithm from a "family" of algorithms.
*   **Key Benefit:** It promotes the **Open/Closed Principle** (part of SOLID). You can add new features (strategies) by extending the system (adding new classes) rather than modifying existing, tested code.

---

## 2. The Problem: The "If-Else" Approach
Without the Strategy pattern, developers often use `if-else` or `switch` blocks to handle multiple logic paths.

**Example: Data Encryption**
If a client needs to encrypt data using MD5, SHA-1, or SHA-2, a standard approach might look like this:
```java
if (type == MD5) { // encrypt MD5 }
else if (type == SHA1) { // encrypt SHA1 }
...
```
**Why this is bad:**
1.  **Monolithic & Rigid:** The class becomes bloated as more algorithms are added.
2.  **Violates Open/Closed Principle:** Every time a new encryption method is required, you must modify the existing class, increasing the risk of breaking current functionality.
3.  **Tight Coupling:** The host class is tightly coupled to every specific implementation.

---

## 3. Implementation Components (Spring Boot)

To implement the Strategy pattern effectively in Spring Boot, four main components are required:

### A. The Enum
Stores the available strategy types.
*   **Example:** `EncodingPatternEnum` (values: `MD5`, `SHA_1`, `SHA_2`).

### B. The Strategy Interface
Defines the contract that all specific algorithms must follow.
*   **Method 1:** `encrypt(String message)` — The actual business logic.
*   **Method 2:** `getEncryptionType()` — Returns the Enum type associated with the specific implementation.

### C. The Concrete Implementations
Classes that implement the Strategy Interface. 
*   Each class is annotated with `@Service` or `@Component` so Spring can manage them as beans.
*   Each class provides its own logic for `encrypt` and returns its specific Enum in `getEncryptionType`.

### D. The Strategy Factory (The "Magic" Part)
This component is responsible for holding all implementations and delivering the correct one at runtime.
1.  **Map Storage:** It maintains a `Map<EncodingPatternEnum, EncryptionInterface>`.
2.  **Auto-Registration:** It uses Spring’s dependency injection to collect all beans that implement the interface into a `Set<EncryptionInterface>`.
3.  **Initialization:** In the constructor (annotated with `@Autowired`), it iterates through the set and populates the Map using the `getEncryptionType()` method as the key.

---

## 4. Execution Flow in Spring Boot
1.  **Startup:** Spring finds all classes marked `@Service` that implement the `Encryption` interface.
2.  **Factory Creation:** The `EncryptionFactory` bean is created. All identified encryption beans are injected into its constructor.
3.  **Map Population:** The Factory builds a Map where the Key is the Enum and the Value is the specific Bean.
4.  **Runtime Request:** A Controller or Service receives a request with a specific "Type" (Enum).
5.  **Strategy Retrieval:** The Factory's `findStrategy(Enum)` method is called, which performs a `map.get(type)` to return the correct bean.
6.  **Execution:** The code calls `bean.encrypt()`. Because of **Dynamic Binding**, the correct implementation runs.

---

## 5. Real-World Use Cases

*   **Data Encryption:** Selecting between MD5, SHA-256, or AES based on client security requirements.
*   **File Compression:** Choosing between ZIP, GZIP, 7Zip, or LZ4 algorithms based on file type or user preference.
*   **Email Systems:** Switching between an internal SMTP server for dev/test environments and a third-party provider (like SendGrid or AWS SES) for production.
*   **Payment Gateways:** Selecting between PayPal, Stripe, or Credit Card processing at the checkout.

---

## 6. Interview "Cheat Sheet"

**Q: Which design patterns have you used in your project?**
**A:** "I implemented the Strategy Design Pattern to handle [Scenario, e.g., multiple encryption formats]. This allowed us to select the required algorithm at runtime."

**Q: Why did you choose Strategy over a simple switch case?**
**A:** "To adhere to the Open/Closed Principle. If we need to add a new algorithm, we just create a new class implementing our interface. We don't have to touch the existing factory or controller logic, which makes the code modular, testable, and less prone to regression bugs."

**Q: How does Spring Boot help with the Strategy Pattern?**
**A:** "Spring makes it very easy to manage. By using `@Component` on my strategy implementations and injecting a `Set<Interface>` into my Factory, Spring automatically discovers all available strategies. I then map them to an Enum for easy retrieval."

---

## 7. Summary of Benefits
*   **Modular Code:** Logic is encapsulated in separate classes.
*   **Easily Extensible:** Adding a new strategy requires zero changes to existing business logic classes.
*   **Clean Controllers:** Controllers only talk to the Factory and the Interface, not the specific implementations.
*   **Decoupled:** The client doesn't need to know how the encryption works, only which type it wants.