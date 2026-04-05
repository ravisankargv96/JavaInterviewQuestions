These detailed notes are based on the mock interview for a Senior Software Development Engineer (SDE-1) with 3+ years of experience, focusing on Java 8, Spring Boot, Microservices, and related technologies.

---

### **1. Java 8 & Core Java Fundamentals**

*   **New Features in Java 8:**
    *   **Functional Programming:** Introduced Functional Interfaces (one abstract method) and Lambda Expressions.
    *   **Key Interfaces:** `Predicate`, `Function`, `Consumer`, and `Supplier`.
    *   **Methods:** Default and Static methods in interfaces (to add functionality without breaking implementing classes).
    *   **Streams API:** For functional-style processing of collections.
    *   **Optional Class:** To handle `null` values and avoid `NullPointerExceptions`.
    *   **New Date & Time API:** Improved handling of time zones and dates.
*   **Object-Oriented Status:** Java is not purely object-oriented because it uses **primitive data types** (int, short, byte). To treat them as objects, **Wrapper classes** (Integer, Long) are used, primarily for Collections.
*   **HashMap Internal Changes (Java 8):**
    *   When a bucket (linked list) exceeds a certain threshold (TREEIFY_THRESHOLD = 8), it converts from a **LinkedList to a Red-Black Tree**.
    *   **Performance:** Worst-case time complexity improves from $O(n)$ to $O(\log n)$.
*   **Memory Management:** Java 8 replaced **PermGen** with **Metaspace**. Metaspace is stored in native memory rather than the heap, reducing "Out of Memory" errors related to metadata.
*   **Diamond Problem:** Occurs in multiple inheritance when two parent interfaces have the same default method.
    *   **Solution:** Override the method in the implementing class and specify the parent using `InterfaceName.super.methodName()`.
*   **Optional Class Methods:**
    *   `Optional.empty()`: Returns an empty object.
    *   `Optional.of()`: Returns an object, but throws a NullPointerException if the value is null.
    *   `Optional.ofNullable()`: Can contain a value or be null.
    *   *Usage:* Use `.isPresent()` or `.isEmpty()` instead of `== null`.

---

### **2. Microservices Architecture**

*   **Distributed Transactions:** Handled using the **Saga Design Pattern**.
    *   **Choreography:** Each service produces and listens to events (e.g., using Kafka). No central coordinator. Best for simple workflows.
    *   **Orchestration:** A central "Orchestrator" tells each service what to do. Better for complex workflows but introduces a single point of failure.
*   **API Gateway vs. Load Balancer:**
    *   **API Gateway:** Handles routing of requests from the UI to specific microservices.
    *   **Load Balancer:** Distributes traffic across multiple instances of the *same* microservice to balance load.
*   **Distributed Tracing & Logging:**
    *   **Tools:** Sleuth and Zipkin.
    *   **Trace ID:** Remains the same across all microservices involved in a single transaction.
    *   **Span ID:** Unique to each specific step or microservice call.
*   **Monitoring Stack:** **Kibana** (Log visualization), **Elasticsearch** (Log storage), **Prometheus** (Metrics collection), and **Grafana** (Metrics visualization).
*   **Externalized Configuration:** **Spring Cloud Config Server**. Uses a Git repository to store properties for multiple services, allowing for centralized management.

---

### **3. Spring Boot & Design Patterns**

*   **Spring Boot 3 Changes:** Requires **Java 17**, uses Spring Framework 6, and migrates from `javax` to `jakarta` packages. Introduces better observability via Micrometer.
*   **Web Servers:** Default is Tomcat. To change to Jetty or Undertow, exclude the Tomcat dependency in `pom.xml` and add the desired server dependency.
*   **Bean Scopes:**
    1.  **Singleton (Default):** One instance per Spring Container (ApplicationContext).
    2.  **Prototype:** New instance created every time it is requested.
    3.  **Request:** One instance per HTTP request.
    4.  **Session:** One instance per HTTP session.
    5.  **Global Session:** Used in Portlet environments.
*   **Singleton Design Pattern (Core Java):**
    *   Implementation: Private constructor, private static instance, and a public static `getInstance()` method.
    *   **Thread Safety:** Use a `synchronized` block with **Double-Checked Locking** to avoid performance hits while ensuring only one instance is created.
*   **SOLID Principles:**
    *   **S:** Single Responsibility (one class, one job).
    *   **O:** Open/Closed (open for extension, closed for modification).
    *   **L:** Liskov Substitution (subtypes must be substitutable for base types).
    *   **I:** Interface Segregation (clients shouldn't depend on methods they don't use).
    *   **D:** Dependency Inversion (depend on abstractions, not concretions).

---

### **4. Databases (JPA/Hibernate)**

*   **JPA (Java Persistence API):** An ORM (Object-Relational Mapping) tool that reduces boilerplate JDBC code.
*   **Query Types:**
    *   **Derived/Inbuilt Queries:** `findByID`, `findByName`.
    *   **JPQL:** Querying against entities.
    *   **Native Queries:** Writing raw SQL (use `nativeQuery = true`).
    *   **Named Queries:** Defined in the Entity class rather than the Repository.
*   **Performance Optimization:**
    1.  **Indexing:** To speed up data retrieval.
    2.  **Sharding:** Dividing data across multiple physical databases.
    3.  **Partitioning:** Dividing a table into smaller parts (Horizontal = by rows, Vertical = by columns).
    4.  **Caching:** Reducing DB hits.
    5.  **Multi-threading:** Using `CompletableFuture` for parallel processing of business logic.

---

### **5. Testing & DevOps**

*   **Test Driven Development (TDD):** Writing tests (JUnit/Mockito) before writing the actual code.
*   **Behavior Driven Development (BDD):** Using Cucumber/Gherkin (Given/When/Then syntax) to define business requirements in a human-readable format.
*   **JUnit 5 Annotations:**
    *   `@BeforeEach`: Runs before every test method (setup).
    *   `@BeforeAll`: Runs once before all tests in the class (must be static).
    *   `@Mock`: Creates a mock object.
    *   `@MockBean`: Adds a mock to the Spring Application Context.
*   **CI/CD Pipeline Stages:**
    1.  Build (Maven/Gradle).
    2.  Unit Testing (JUnit).
    3.  Code Quality (SonarQube).
    4.  Containerization (Docker image creation).
    5.  Deployment (Kubernetes/AWS EKS).

---

### **6. Messaging & Security**

*   **Apache Kafka:**
    *   Uses a **Pub-Sub model**.
    *   **Architecture:** Producers send data to Topics; Topics are divided into Partitions; Consumers in Consumer Groups read from Partitions.
    *   **Fault Tolerance:** Managed by Zookeeper (though newer versions are moving away from it); uses Leaders and Followers for brokers.
*   **JWT (JSON Web Token):**
    *   **Header:** Contains algorithm and token type.
    *   **Payload:** Contains Claims (Issuer, Expiry, Subject, or custom user data).
    *   **Signature:** Encoded Header + Encoded Payload + Secret Key.
*   **CORS (Cross-Origin Resource Sharing):** Handled in Spring Boot using the `@CrossOrigin` annotation or by configuring a security filter to allow requests from specific front-end origins.

---

### **Key Takeaways for SDE-1 Interviews:**
*   Understand the **internal working** (e.g., HashMap, JVM memory).
*   Be able to explain **Design Patterns** with real-world coding examples.
*   Know the **"Why"** behind architectural choices (e.g., why choose Orchestration over Choreography).
*   Be familiar with the **deployment lifecycle** (Docker, Kubernetes, Jenkins).