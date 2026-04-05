These notes summarize the key interview questions and concepts discussed in the "Code Decode" video for Java backend developers with 5+ years of experience.

---

### 1. Java Core & Collections

#### **HashMap in Multi-threaded Environments**
*   **The Scenario Matters:** You *can* use `HashMap` in a multi-threaded environment if the architecture follows a **CQRS (Command Query Responsibility Segregation)** pattern where a specific microservice is "read-only."
*   **Read-Only:** If there are only `GET` requests and no data modifications, `HashMap` is safe and offers the best performance.
*   **Concurrent Modification:** If the application performs CRUD operations (specifically writes/updates) concurrently, `HashMap` will throw a `ConcurrentModificationException`.
*   **Solution:** Use `ConcurrentHashMap`. It is preferred over `Hashtable` because it provides better concurrency by locking only segments of the map rather than the entire object.

#### **String Literal vs. New Keyword**
*   **String S1 = "Hello" (Literal):** Created in the **String Constant Pool (SCP)**, which is a part of the Heap. It prevents duplication; if "Hello" exists, S1 points to the existing object.
*   **String S2 = new String("Hello"):** This creates **two objects** (if "Hello" isn't already in the pool): one in the regular Heap area and one in the SCP. 
*   **Why Literal?** To prevent memory wastage. Since Strings are immutable, multiple references can safely point to the same instance in the SCP.

---

### 2. Design Patterns

#### **Factory Design Pattern**
*   **Classification:** Creational Design Pattern.
*   **Purpose:** It hides the complexity of object creation from the client. The client uses an interface or a "factory" to get an object without knowing the internal logic of how that object is instantiated.
*   **Analogy:** A car's gear system. You know that shifting to "Gear 1" manages speed between 0–10mph, but you don't need to know the internal mechanical logic to use it.
*   **Key Term:** Often called a "Virtual Constructor."

---

### 3. Java 8 Features
Interviewers expect you to name these and explain how you used them:
*   **Lambda Expressions & Stream API:** Used for functional-style processing of collections.
*   **Default & Static Methods:** Allows adding methods to interfaces without breaking existing implementation classes.
*   **Optional Class:** Used to prevent `NullPointerException`.
*   **Date/Time API:** Improved handling of time zones and date math.
*   **Functional Interfaces:** Interfaces with a single abstract method (e.g., `Predicate`, `Function`).

---

### 4. Spring Boot Essentials

#### **Starter Dependencies**
*   **Definition:** These are curated Maven/Gradle templates containing a collection of transitive dependencies.
*   **Purpose:** They simplify build configurations. For example, adding `spring-boot-starter-web` automatically pulls in Tomcat, Spring MVC, and Jackson.
*   **Benefit:** Resolves "version mismatch" issues. You don't have to manually manage compatible versions of 20 different jars.

#### **Spring Boot Actuator**
*   **Purpose:** Provides production-ready features to monitor and manage your application.
*   **Key Info:** It exposes HTTP endpoints (like `/health`, `/metrics`, `/beans`, `/env`) to check the status of the app in real-time.

#### **Profiling (Environment Management)**
*   **Concept:** Used to map different configurations to specific environments (Dev, QA, Prod).
*   **Implementation:** Use `application-dev.properties`, `application-prod.properties`, etc.
*   **Activation:**
    1.  Via properties file: `spring.profiles.active=dev`
    2.  Via Java code: `SpringApplication.setArguments(...)`
    3.  **Recommended:** Via JVM Parameter during deployment: `-Dspring.profiles.active=prod`.

#### **Aspect-Oriented Programming (AOP)**
*   **Goal:** Separates "cross-cutting concerns" (logging, security, transaction management) from the core business logic.
*   **Four Pillars of AOP:**
    1.  **Aspect:** The class containing the cross-cutting logic.
    2.  **Advice:** The actual action taken (e.g., logging a message).
    3.  **Join Point:** A point in the execution of the program (usually a method execution).
    4.  **Pointcut:** An expression that defines which Join Points the Advice should be applied to.

---

### 5. REST & Microservices

#### **API Contracts & Swagger**
*   **Contract:** The agreement between the API provider and consumer (URLs, HTTP methods, headers, request/response body).
*   **Swagger (OpenAPI):** A tool used to document the contract. It allows clients to see and test the API structure without the developer manually explaining every endpoint.

#### **Monolithic to Microservices Transition**
*   **Decomposition Strategy:** Divide by **Business Capability** (e.g., Order service, User service) or **Domain-Driven Design (DDD)**.
*   **Principles:** 
    *   Single Responsibility.
    *   High Cohesion & Low Coupling.
    *   **Bounded Context:** Each service has its own data and logic boundary.

#### **Fault Tolerance (Resilience4j vs. Hystrix)**
*   **Hystrix:** Originally by Netflix; now in maintenance mode.
*   **Resilience4j:** Modern, lightweight, inspired by Hystrix, and designed for Java 8+.
*   **Circuit Breaker Pattern:** If a service fails repeatedly, the "circuit" opens, and a **fallback method** is triggered to provide a user-friendly response instead of a 500 error.

#### **Microservice Communication**
1.  **Synchronous:** Client waits for a response (e.g., using `RestTemplate`, `WebClient`, or `OpenFeign`).
2.  **Asynchronous:** Client sends a message and continues its work (non-blocking). Uses **Message Brokers** like Apache Kafka, RabbitMQ, or ActiveMQ.
    *   *Example:* An Order service places an order and immediately allows the user to keep shopping, while an Email service processes the "Send Email" task in the background.

---

### 6. SQL Databases

#### **Types of Joins**
*   **Inner Join:** Only returns rows where there is a match in both tables.
*   **Left Outer Join:** All rows from the left table + matching rows from the right.
*   **Right Outer Join:** All rows from the right table + matching rows from the left.
*   **Full Outer Join:** All rows from both tables. 
    *   *Note:* **MySQL does not support** `FULL OUTER JOIN` directly; you must use a `UNION` of a Left Join and a Right Join.

#### **Indexes**
*   **Purpose:** To speed up data retrieval. Without an index, the DB performs a "Full Table Scan" (O(n) complexity).
*   **Internal Structure:** Usually a **B-Tree**, which reduces search complexity to O(log n).
*   **Trade-off:** Indexes speed up reads but can slow down writes (INSERT/UPDATE) because the index must also be updated.

---

### 7. Hibernate (ORM)

#### **Caching Mechanism**
*   **First Level Cache:** Associated with the `Session` object. Enabled by default and cannot be disabled.
*   **Second Level Cache:** Associated with the `SessionFactory`. Disabled by default; survives across different sessions until the application is shut down.

#### **Criteria API vs. Native SQL**
*   **Criteria API:** An object-oriented way to write queries. It is type-safe and good for dynamic queries (Selects with many optional filters). Note: It is generally used for retrieval, not updates/deletes.
*   **Native SQL:** Hibernate allows you to write raw SQL using `session.createSQLQuery()`. This is useful for complex queries that are difficult to express in HQL or Criteria.