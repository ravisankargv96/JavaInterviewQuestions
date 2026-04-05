These notes provide a detailed breakdown of the interview questions and technical concepts discussed in the "Code Decode" video for a Java Developer position with approximately 6 years of experience.

### **1. Core Java & Java 8 Features**

At the 6-year level, the focus shifts from basic syntax to functional programming and internal workings.

*   **Stream API Scenarios:**
    *   **Finding Duplicates:** Use `Collectors.groupingBy` and `Collectors.counting()` to find elements with a count greater than one.
    *   **`map()` vs. `flatMap()`:** 
        *   `map()` is used for data transformation (1-to-1 mapping).
        *   `flatMap()` is used for flattening structures (e.g., converting a `List<List<String>>` into a single `List<String>`).
    *   **Internal Working of Streams:** Understand that streams are "lazy." Intermediate operations (filter, map) are not executed until a terminal operation (collect, findFirst) is called.

*   **Optional Class:**
    *   Used to avoid `NullPointerException`. 
    *   Focus on methods like `ifPresent()`, `orElse()`, and `orElseGet()`.

*   **Memory Management:**
    *   **Metaspace (Java 8+):** Replaced PermGen. It resides in native memory and grows automatically, reducing `OutOfMemoryError: PermGen` issues.
    *   **Garbage Collection:** Expect questions on G1 Garbage Collector (default in Java 9+) and how it handles heap regions.

---

### **2. Spring & Spring Boot**

*   **Bean Lifecycle and Scopes:**
    *   Detailed knowledge of `Singleton`, `Prototype`, `Request`, and `Session` scopes.
    *   The interviewer may ask about the "Prototype Bean inside a Singleton Bean" problem (Solution: Use Method Injection or `ObjectProvider`).

*   **Spring Boot Annotations:**
    *   **`@SpringBootApplication`:** A combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
    *   **`@RestController` vs. `@Controller`:** `@RestController` combines `@Controller` and `@ResponseBody`, ensuring the return value is written directly to the HTTP response body (usually as JSON/XML).

*   **Spring Boot Starters & Auto-configuration:**
    *   How Spring Boot "decides" which beans to create based on the JARs present in the classpath (using `@ConditionalOnClass`, `@ConditionalOnProperty`).

---

### **3. Microservices Architecture**

For a senior role, design patterns are more important than syntax.

*   **Communication Styles:**
    *   **Synchronous:** Using Feign Client or RestTemplate.
    *   **Asynchronous:** Using Message Brokers like Kafka or RabbitMQ to decouple services.

*   **Resilience & Fault Tolerance:**
    *   **Circuit Breaker Pattern:** Explained using Resilience4j (or Hystrix). States include **Closed** (normal), **Open** (failing/blocked), and **Half-Open** (testing if the service recovered).
    *   **Retry Pattern:** Automatically retrying a failed operation.

*   **API Gateway:**
    *   Acts as a single entry point. Responsibilities include routing, security (JWT validation), and rate limiting.

*   **Distributed Tracing:**
    *   Using Sleuth and Zipkin to track a single request across multiple microservices via Trace IDs and Span IDs.

---

### **4. Hibernate & JPA (Data Layer)**

*   **N+1 Select Problem:**
    *   This occurs when the code executes 1 query to fetch parent records and then 'N' additional queries to fetch children for each parent.
    *   **Solutions:** Use `join fetch` in JPQL, Entity Graphs, or Batch fetching.

*   **Caching Strategy:**
    *   **First Level Cache:** Associated with the Session object (enabled by default).
    *   **Second Level Cache:** Associated with the SessionFactory (requires providers like EhCache or Redis).

*   **Transaction Management:**
    *   `@Transactional` propagation levels (e.g., `REQUIRED`, `REQUIRES_NEW`).

---

### **5. Concurrency & Multi-threading**

*   **CompletableFuture:**
    *   Introduced in Java 8 for non-blocking asynchronous programming. It allows chaining multiple tasks together using `thenApply()`, `thenAccept()`, and `thenCombine()`.

*   **Executor Framework:**
    *   Knowledge of `FixedThreadPool`, `CachedThreadPool`, and how to handle thread rejection policies.

---

### **6. System Design & Clean Code (Senior Level Expectations)**

*   **SOLID Principles:**
    *   Interviewers expect real-world examples. For instance, the **Open/Closed Principle** (classes should be open for extension but closed for modification).
*   **Design Patterns:**
    *   **Factory Pattern:** For object creation logic.
    *   **Strategy Pattern:** For switching between different algorithms (e.g., different payment methods).
    *   **Observer Pattern:** For event-driven systems.

*   **Scalability:**
    *   Horizontal vs. Vertical scaling.
    *   Statelessness in REST APIs to facilitate easy scaling.

---

### **7. Common Behavioral/Process Questions**

*   **Agile Methodology:** Understanding of Scrum, Sprint Planning, and Daily Stand-ups.
*   **Code Review Process:** How you ensure code quality and what you look for in a PR (Peer Review).
*   **Production Issues:** Be prepared to describe a time you debugged a critical production bug using logs (ELK Stack) and monitoring tools.

### **Summary of Key Advice for 6+ Year Candidates:**
1.  **Don't just say "What":** Explain "Why." (e.g., Why choose Microservices over Monolith for your specific project?).
2.  **Focus on Trade-offs:** Every architectural choice has a pro and a con. Senior developers are expected to understand these trade-offs.
3.  **Database Optimization:** Know how to write efficient SQL and optimize slow Hibernate queries.