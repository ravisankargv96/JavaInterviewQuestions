These detailed notes cover the core concepts, implementation strategies, and interview-specific insights regarding the Proxy and Flyweight Design Patterns as discussed in the tutorial.

---

# Java Design Patterns: Proxy & Flyweight

## 1. Structural Design Patterns Overview
Both Proxy and Flyweight belong to the **Structural Design Pattern** category. These patterns focus on how classes and objects are composed to form larger structures, ensuring that if one part of a system changes, the entire structure doesn't need to be rebuilt.

---

## 2. Proxy Design Pattern

### Definition
The Proxy pattern provides a surrogate or placeholder for another object to control access to it. It acts as an intermediary, intercepting requests to the original "Real Subject" object.

### Why Use Proxy?
*   **Access Control:** To restrict who can use the object.
*   **Lazy Loading:** To delay the creation of a resource-heavy object until it is actually needed.
*   **Logging/Auditing:** To record who is calling which method.
*   **Remote Access:** To represent an object that exists in a different memory space (RMI).

### Key Components
1.  **Subject (Interface):** Defines the common operations for both the Real Subject and the Proxy.
2.  **Real Subject:** The actual object that performs the core business logic.
3.  **Proxy:** Maintains a reference to the Real Subject and implements the same interface. It handles the "extra" logic (security, caching, etc.) before or after delegating to the Real Subject.

### Real-World Examples
*   **Spring Framework:** Spring uses Proxies extensively for **AOP (Aspect Oriented Programming)**. When you use `@Transactional`, Spring creates a proxy around your bean to manage the database transaction.
*   **Hibernate:** Uses proxies for "Lazy Loading" of database entities.

### Common Interview Question: Proxy vs. Decorator
*   **Proxy:** Controls the lifecycle of the object and manages access. The client often doesn't know it's talking to a proxy.
*   **Decorator:** Adds new responsibilities or behaviors to an object dynamically. The focus is on "decoration" rather than "access control."

---

## 3. Flyweight Design Pattern

### Definition
The Flyweight pattern is used to reduce the memory footprint of a system by sharing as much data as possible with similar objects. It is the "Optimization Pattern."

### Core Concepts: Intrinsic vs. Extrinsic State
To implement Flyweight, you must divide the object's data into two parts:
1.  **Intrinsic State:** Data that is constant, shared, and does not depend on the object's context (e.g., the image of a tree in a game).
2.  **Extrinsic State:** Data that varies and depends on the context. This is passed to the flyweight object by the client (e.g., the X and Y coordinates of where a tree is planted).

### When to Use Flyweight?
*   When an application uses a massive number of objects (e.g., millions of characters in a word processor).
*   When memory storage costs are high.
*   When most object state can be made extrinsic.

### Flyweight in Java (JDK)
*   **String Pooling:** Java’s `String` pool is a classic Flyweight implementation. Instead of creating new objects for the same string literal, Java points to the existing one in the pool.
*   **Integer Caching:** `Integer.valueOf(int)` caches values between -128 and 127.

### Implementation Strategy
A **Flyweight Factory** is usually implemented. This factory contains a Map (Cache). When a client requests an object, the factory checks if it already exists in the map:
*   If yes: Returns the existing object.
*   If no: Creates a new one, stores it in the map, and returns it.

---

## 4. Key Differences: Proxy vs. Flyweight

| Feature | Proxy Pattern | Flyweight Pattern |
| :--- | :--- | :--- |
| **Primary Goal** | Control access to an object. | Minimize memory usage by sharing. |
| **Object Count** | Usually 1 Proxy for 1 Real Subject. | 1 Flyweight object shared by many contexts. |
| **Creation** | Proxy creates/wraps the Real Subject. | Factory manages the pool of objects. |
| **State** | Manages the object's full state. | Splits state into Intrinsic and Extrinsic. |

---

## 5. Interview Questions and Answers

**Q1: How does the Flyweight pattern improve performance?**
**A:** It reduces the number of objects created in the heap, which lowers memory consumption and reduces the frequency of Garbage Collection (GC) cycles.

**Q2: What is a "Virtual Proxy"?**
**A:** A Virtual Proxy is used for "Lazy Initialization." It holds a placeholder for expensive objects (like a high-resolution image) and only instantiates the real object when a method is called that requires it.

**Q3: Is the Flyweight pattern thread-safe?**
**A:** Since Flyweight objects are shared across multiple threads/contexts, their **Intrinsic state must be immutable** to ensure thread safety.

**Q4: How does Spring Boot use the Proxy pattern?**
**A:** Spring uses **JDK Dynamic Proxies** (if the class implements an interface) or **CGLIB Proxies** (if it doesn't) to implement cross-cutting concerns like logging, security, and transaction management without modifying the actual business logic.

**Q5: Can you explain the role of the Factory in Flyweight?**
**A:** The Factory ensures that objects are shared correctly. It acts as a gatekeeper that checks the cache before creating any new instances, ensuring that no duplicate intrinsic states are stored in memory.