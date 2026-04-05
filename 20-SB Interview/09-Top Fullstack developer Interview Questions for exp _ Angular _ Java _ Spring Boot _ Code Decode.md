These detailed notes cover the core concepts and interview questions discussed in the "Code Decode" video regarding Fullstack Development, specifically focusing on the Java/Spring Boot and Angular ecosystem for experienced candidates.

### **I. Angular Interview Questions (Frontend Focus)**

**1. Angular Lifecycle Hooks**
*   **ngOnChanges:** Called when an input property (`@Input`) changes. It is the only hook that takes an argument (`SimpleChanges`).
*   **ngOnInit:** Called once after the first `ngOnChanges`. Used for component initialization and fetching data from services.
*   **ngAfterViewInit:** Called after the component’s view (and child views) are initialized. Important for DOM manipulation.
*   **ngOnDestroy:** Used for cleanup (unsubscribing from Observables, detaching event listeners) to prevent memory leaks.

**2. Directives: Structural vs. Attribute**
*   **Structural:** Change the DOM layout by adding or removing elements (e.g., `*ngIf`, `*ngFor`, `*ngSwitch`).
*   **Attribute:** Change the appearance or behavior of an element (e.g., `ngStyle`, `ngClass`).

**3. Pure vs. Impure Pipes**
*   **Pure Pipes:** Angular executes a pure pipe only when it detects a pure change to the input value (primitive types or changed object references). They are performant.
*   **Impure Pipes:** Executed on every change detection cycle (e.g., mouse moves, keystrokes). Use these sparingly as they can degrade performance.

**4. Angular Interceptors**
*   Used to intercept and modify HTTP requests or responses globally.
*   **Common Use Cases:** Adding Authorization tokens (JWT) to headers, logging, or handling global error codes (like 401 Unauthorized).

**5. Forms: Reactive vs. Template-Driven**
*   **Reactive Forms:** More robust, scalable, and testable. Logic is handled in the TypeScript class. Better for complex validation.
*   **Template-Driven Forms:** Simple to use, logic is mostly in the HTML. Good for simple forms but harder to unit test.

---

### **II. Spring Boot & Java Interview Questions (Backend Focus)**

**1. The @SpringBootApplication Annotation**
It is a "shortcut" annotation that combines three others:
*   **@Configuration:** Tags the class as a source of bean definitions.
*   **@EnableAutoConfiguration:** Tells Spring Boot to start adding beans based on classpath settings.
*   **@ComponentScan:** Tells Spring to look for other components, configurations, and services in the package.

**2. Dependency Injection (DI) & Inversion of Control (IoC)**
*   **IoC:** A design principle where the control of object creation is transferred from the programmer to the Spring container.
*   **DI:** The pattern used to implement IoC. Spring "injects" the required dependency into the class (via Constructor, Setter, or Field).

**3. Spring Boot Actuators**
*   Provides production-ready features to help monitor and manage applications.
*   **Endpoints:** `/health` (checks if app is up), `/metrics` (CPU/Memory usage), `/beans` (list of all beans in the context).

**4. Exception Handling**
*   **@ControllerAdvice:** A global exception handler that intercepts exceptions thrown by `@RequestMapping` methods across all controllers.
*   **@ExceptionHandler:** Used within a specific controller or in a global advice class to define how to handle a specific exception type (e.g., `ResourceNotFoundException`).

**5. Spring Data JPA & Hibernate**
*   **Hibernate:** An ORM (Object Relational Mapping) tool.
*   **Spring Data JPA:** An abstraction layer on top of JPA/Hibernate that reduces boilerplate code by providing repository interfaces (like `JpaRepository`).

---

### **III. Microservices & Architecture Questions**

**1. Service Discovery (Eureka)**
*   In a cloud environment, instances of services change frequently. Eureka allows services to find and communicate with each other without hardcoding IP addresses.

**2. API Gateway**
*   The single entry point for all client requests.
*   **Responsibilities:** Authentication, Rate Limiting, Request Routing, and Load Balancing.

**3. Circuit Breaker Pattern (Resilience4j / Hystrix)**
*   Prevents a failure in one service from cascading and bringing down the entire system. If a service is slow or failing, the "circuit opens," and a fallback method is executed.

**4. Spring Cloud Config**
*   Centralizes the configuration for all microservices. Configurations are usually stored in a Git repository, allowing for dynamic updates without restarting the services.

---

### **IV. Fullstack Integration & Performance**

**1. CORS (Cross-Origin Resource Sharing)**
*   When an Angular app (on `localhost:4200`) tries to call a Spring Boot API (on `localhost:8080`), the browser blocks it by default.
*   **Fix:** Use the `@CrossOrigin` annotation in Spring Boot or configure a Global CORS mapping.

**2. Optimizing Performance**
*   **Angular:** Use Lazy Loading for modules to reduce the initial bundle size. Use `trackBy` with `*ngFor`.
*   **Java/Spring Boot:** Use Caching (`@Cacheable`), optimize database queries (avoid N+1 problems), and use Pagination for large datasets.

**3. Security (JWT - JSON Web Token)**
*   **Flow:** Client logs in -> Server validates and generates a JWT -> Client stores JWT (Local Storage/Session Storage) -> Client sends JWT in the Header for every subsequent request -> Server validates the token and allows access.

### **V. Common Practical Scenarios**

*   **Scenario:** How do you handle database transactions across multiple microservices?
    *   **Answer:** Discuss the **Saga Pattern** (Choreography or Orchestration) rather than traditional 2PC (Two-Phase Commit).
*   **Scenario:** How do you handle data consistency between the frontend and backend?
    *   **Answer:** Mention using **DTOs (Data Transfer Objects)** to ensure only necessary data is sent and validating data on both the client and server sides.