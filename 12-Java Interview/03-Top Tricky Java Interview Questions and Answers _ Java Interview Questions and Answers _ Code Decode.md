These detailed notes are based on the "Top Tricky Java Interview Questions and Answers" video by Code Decode. The content covers Java 8 enhancements, design patterns, Spring Framework, Hibernate, and Microservices.

---

### 1. HashMap Enhancements in Java 8
*   **Collision Handling:** Prior to Java 8, hash collisions were handled using a Linked List. In the worst-case scenario (all elements in one bucket), performance was **O(n)**.
*   **Balanced Trees:** In Java 8, once a certain threshold (TREEIFY_THRESHOLD) is reached, the linked list is converted into a **Balanced Tree (Red-Black Tree)**.
*   **Performance:** This improves the worst-case performance from **O(n)** to **O(log n)**. The best-case performance remains **O(1)**.
*   **Other Changes:** Alternative string hash functions added in Java 7 were removed in Java 8.

### 2. Java 8 Optional Class
*   **Purpose:** A container object used to represent the presence or absence of a value. Its primary goal is to prevent `NullPointerException`.
*   **Package:** `java.util.Optional`.
*   **Key Methods:**
    *   `isPresent()`: Returns `true` if a value is present, otherwise `false`.
    *   `get()`: Retrieves the value if present (otherwise throws an exception).
    *   It acts as a utility to handle nulls more gracefully than traditional null checks.

### 3. Java 8: Map vs. FlatMap
*   **Map:** Performs a "one-to-one" mapping. It transforms each element in a stream into another value. 
    *   *Example:* Incrementing every employee's salary by 500.
*   **FlatMap:** Performs "one-to-many" mapping and **flattening**. It is used when each element in a stream contains another collection (a stream of streams).
    *   *Example:* An employee has a list of books. `flatMap` can be used to consolidate all books from all employees into a single list of book titles.
*   **Key Difference:** Map is for simple transformation; FlatMap is for transformation plus flattening nested structures.

### 4. Factory vs. Abstract Factory Design Pattern
*   **Factory Pattern:** A single factory class that creates different objects based on input. It provides a way to encapsulate object creation.
*   **Abstract Factory:** Often called a "factory of factories." It provides an interface for creating families of related objects without specifying their concrete classes.
*   **Abstraction Level:** Factory has one level of abstraction; Abstract Factory has two layers.

### 5. Spring Bean Scopes
Spring supports five main scopes:
1.  **Singleton (Default):** Only one instance of the bean is created per Spring container.
2.  **Prototype:** A new instance is created every time the bean is requested.
3.  **Request:** One bean instance per HTTP request (Web-aware).
4.  **Session:** One bean instance per HTTP session (Web-aware).
5.  **Global Session:** Used in Portlet applications; creates a global session bean.

### 6. Spring vs. Spring Boot
*   **Spring Framework:** Provides core features like Dependency Injection (DI), AOP, and Data Access. However, it requires heavy configuration (XML or Java) and boilerplate code.
*   **Spring Boot:** An extension of Spring that favors "Convention over Configuration."
    *   **Rapid Application Development (RAD):** Built for quick deployment.
    *   **Starter Dependencies:** Bundles multiple dependencies into one (e.g., `spring-boot-starter-web`).
    *   **Embedded Server:** Comes with built-in servers like Tomcat or Jetty (no need to deploy WAR files manually).
    *   **Auto-configuration:** Automatically configures beans based on the classpath.

### 7. Spring AOP (Aspect-Oriented Programming)
AOP is used to separate **cross-cutting concerns** (like logging, security, or transaction management) from the actual business logic.
*   **Aspect:** A class that implements the cross-cutting concern.
*   **Advice:** The specific action taken (the method). Types include: *Before, After, Around, AfterThrowing, AfterReturning*.
*   **Pointcut:** An expression that defines where (at which Join Points) the Advice should be applied.
*   **Join Point:** A specific point in the application (like a method execution) where an Aspect can be plugged in.

### 8. JVM Memory: PermGen vs. Metaspace
*   **PermGen (Java 7 and earlier):** Stored class metadata in a fixed-size heap memory. It was prone to `OutOfMemoryError` because it couldn't grow dynamically.
*   **Metaspace (Java 8+):** Replaced PermGen. It uses **Native Memory** (RAM) rather than the JVM heap.
    *   **Benefits:** It is dynamically resizable, improves garbage collection auto-tuning, and reduces the chance of memory errors related to class metadata.

### 9. Multi-Catch Block (Java 7+)
Allows a single `catch` block to handle multiple types of exceptions using the pipe (`|`) operator.
*   *Example:* `catch (NullPointerException | ArrayIndexOutOfBoundsException e) { ... }`
*   **Benefit:** Reduces code redundancy when multiple exceptions require the same handling logic.

### 10. Hibernate Criteria API
*   **Definition:** A programmatic, object-oriented way to retrieve data from a database.
*   **Benefits:**
    *   **Dynamic Queries:** Easier to build queries based on dynamic runtime conditions.
    *   **Compile-time Safety:** Errors can be caught during compilation rather than at runtime (unlike HQL/SQL strings).
    *   **No Hard-coded Strings:** Avoids syntax errors common in HQL/SQL strings.

### 11. JPA Cascade Types
Cascading allows changes to a parent entity to automatically propagate to its child entities.
*   **PERSIST:** If parent is saved, children are saved.
*   **MERGE:** If parent is updated, children are updated.
*   **REMOVE:** If parent is deleted, children are deleted.
*   **DETACH:** Removes entities from the persistent context/cache.
*   **REFRESH:** Re-reads the values from the database.
*   **ALL:** Applies all the above operations.

### 12. Microservices Architecture
Microservices involve breaking a monolithic application into small, independent services that communicate with each other.
*   **Why use them?**
    *   **Resilience:** If one service fails, the entire system doesn't necessarily crash (fault tolerance).
    *   **Scalability:** You can scale only the specific services that are under high load.
    *   **Technology Diversity:** Different services can use different languages or databases.
    *   **Faster Time to Market:** Smaller teams can work on, test, and deploy individual services independently.
    *   **Easier Maintenance:** Smaller codebases are easier to debug and maintain.