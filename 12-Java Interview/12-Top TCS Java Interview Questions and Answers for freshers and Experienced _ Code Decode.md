These detailed notes summarize the key technical interview questions and concepts discussed in the "Top TCS Java Interview Questions" video by Code Decode.

---

### 1. Key Features of the Spring Framework
Spring remains an integral part of Java development. Key features include:

*   **Inversion of Control (IoC):** This transfers the control of object creation and lifecycle management from the developer to the Spring container. It promotes loose coupling and modularity.
*   **Dependency Injection (DI):** A subset/implementation of IoC. It allows the injection of one object's dependency into another, making the application flexible and easy to test.
*   **Aspect-Oriented Programming (AOP):** Used to separate "cross-cutting concerns" (like logging, security, and transaction management) from the actual business logic.
*   **ORM Integration:** Spring provides seamless integration with Object-Relational Mapping frameworks like Hibernate and JPA to handle database operations.
*   **Transaction Management:** Using the `@Transactional` annotation, Spring handles commits and rollbacks automatically, removing the need for low-level JDBC code.
*   **Spring Security:** A robust framework for handling authentication, authorization, and protection against common vulnerabilities.
*   **Spring Integration:** Facilitates communication between different Spring applications using messages and events, supporting event-driven architecture.
*   **Spring Cloud:** Provides tools for building distributed systems and microservices (e.g., service discovery, circuit breakers).
*   **Internationalization (i18n):** Supports applications in multiple languages and regions.

---

### 2. Managing Memory Leakage in Java
A memory leak occurs when the Garbage Collector (GC) fails to recognize unused objects because they are still being referenced, leading to `OutOfMemoryError`.

*   **Symptoms:** Consistently decreasing performance and a continuous increase in memory usage over time.
*   **Common Causes:**
    *   Unwanted references to objects that are no longer needed.
    *   **Static Objects:** These live in memory for the entire lifespan of the application.
    *   **Native Resources:** Resources created by C/C++ (JNI) that are not explicitly cleaned up by the developer.
*   **Prevention & Management:**
    *   Avoid unnecessary object creation and string concatenation (use `StringBuilder` instead).
    *   Explicitly nullify references or close sessions when done.
    *   Use **Try-with-Resources** to ensure connections, statements, and result sets are closed.
    *   Avoid excessive use of static variables.
    *   **Tools:** Use Java VisualVM, JConsole, or Java Flight Recorder to monitor usage. Analyze **Heap Dumps** using Eclipse Memory Analyzer (MAT) to pinpoint the source of the leak.

---

### 3. Internal Working of ConcurrentHashMap
`ConcurrentHashMap` is a thread-safe, high-performance alternative to `HashMap` and `Hashtable`.

*   **Segments:** The map is physically divided into segments (small hash tables). Each segment acts as an independent map with its own lock.
*   **Fine-Grained Locking:** Unlike `Hashtable` (which locks the entire map), `ConcurrentHashMap` only locks the specific segment being written to. This allows multiple threads to access different segments simultaneously.
*   **Put Operation:** 
    1. Calculate the Hash Code of the key.
    2. Determine the Segment Number.
    3. Acquire the lock for that specific segment.
    4. Determine the Bucket Number within that segment and place the value.
*   **Read Operation:** Usually performed without a lock. Multiple threads can read concurrently as long as there is no active write on that specific segment.
*   **Java 8 Enhancement:** It uses a combination of Linked Lists and Trees (when a collision threshold is reached) for better performance. It also uses **Compare and Swap (CAS)** operations for better concurrency.

---

### 4. Lambda Expressions vs. Functional Interfaces
*   **Can we use Lambda without a Functional Interface?** No.
*   **Reasoning:** A Lambda expression is essentially an implementation of the Single Abstract Method (SAM) defined in a Functional Interface. Without the interface, there is no method signature for the Lambda to implement.
*   **Note:** While Method References (`::`) exist as a separate feature, Lambdas are strictly tied to Functional Interfaces.

---

### 5. Distributed Transactions in Microservices
Handling transactions across multiple microservices is complex because each service has its own database.

*   **Saga Design Pattern:** This is the most common solution. It breaks a large transaction into a series of small local transactions.
    *   If one step fails, a **Compensating Transaction** is triggered to undo the changes made by previous steps (e.g., if a payment fails, the "Order Service" reverts the order status).
*   **Two-Phase Commit (2PC):** A traditional approach where all services prepare to commit and then commit together. However, it is often avoided in microservices due to its synchronous/blocking nature.
*   **Event-Driven Architecture:** Services communicate asynchronously via events. This decouples services but requires careful management of eventual consistency.
*   **CQRS (Command Query Responsibility Segregation):** Separates read operations from write operations to manage data more effectively in a distributed environment.

---

### 6. JSON Web Tokens (JWT)
JWT is a compact, self-contained way to securely transmit information between a client and a server. It is primarily used for Authentication and Authorization.

*   **Structure (Header.Payload.Signature):**
    1.  **Header:** Contains the token type (JWT) and the signing algorithm (e.g., HS256).
    2.  **Payload:** Contains "Claims" (user ID, name, roles). **Warning:** Do not put sensitive data (passwords) here, as it is only Base64 encoded and can be easily decoded.
    3.  **Signature:** Created by taking the encoded header, encoded payload, and a secret key, then signing them using the specified algorithm.
*   **Best Practices:** Keep the token compact by only including necessary claims to reduce network overhead.

---

### 7. Validating JWT (Resource Server)
The backend (resource server) must verify if a token is valid before granting access.

*   **Stateless Validation (Preferred):** The server validates the token independently without calling the Authorization Server (e.g., Okta/Auth0). It checks:
    1.  **Signature:** Uses its secret key to re-calculate the signature and see if it matches the token.
    2.  **Expiration:** Checks the `exp` claim against the current time.
    3.  **Claims:** Checks the audience (`aud`) or other custom claims.
*   **Stateful Validation:** The server makes a network call to the Authorization Server to verify the token. This is more secure but adds network overhead and performance lag.