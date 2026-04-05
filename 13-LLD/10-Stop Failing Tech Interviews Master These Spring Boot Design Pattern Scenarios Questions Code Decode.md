These notes cover the essential design patterns used within the Spring Boot framework as discussed in the "Code Decode" tutorial. The focus is on how these patterns solve real-world architectural problems and how to answer scenario-based interview questions.

---

### **1. Singleton Design Pattern**
*   **Concept:** Ensures a class has only one instance and provides a global point of access to it.
*   **Spring Implementation:** By default, all beans in Spring are **Singletons**.
*   **Interview Scenario:** How does Spring handle stateful vs. stateless beans?
    *   Spring’s Singleton is managed per **ApplicationContext** (container level), unlike the traditional GoF Singleton which is per ClassLoader.
    *   **Use Case:** Service classes, Repositories, and Controllers are usually singletons because they are stateless and can be reused by multiple threads.

### **2. Prototype Design Pattern**
*   **Concept:** Creates a new instance of an object every time it is requested.
*   **Spring Implementation:** Defined using `@Scope("prototype")`.
*   **Interview Scenario:** When would you use Prototype over Singleton?
    *   Use it when a bean holds **mutable state** (data that changes) that should not be shared across different threads or users.
    *   **Warning:** If you inject a Prototype bean into a Singleton bean, the Prototype bean is only injected once. To fix this, use **Lookup Method Injection** or the `ObjectProvider`.

### **3. Factory Design Pattern**
*   **Concept:** Provides an interface for creating objects but allows subclasses to alter the type of objects that will be created.
*   **Spring Implementation:** 
    *   **BeanFactory:** The root interface for accessing the Spring bean container.
    *   **ApplicationContext:** A more advanced factory that adds enterprise-specific functionality.
*   **Interview Scenario:** How does Spring decide which bean to give you?
    *   The container acts as a "Bean Factory." Based on the configuration (XML, Java Config, or Annotations), it instantiates and manages the lifecycle of the requested objects.

### **4. Proxy Design Pattern**
*   **Concept:** A proxy object acts as a surrogate or placeholder for another object to control access to it.
*   **Spring Implementation:** This is the backbone of **Spring AOP (Aspect-Oriented Programming)**.
    *   **JDK Dynamic Proxy:** Used if the target class implements an interface.
    *   **CGLIB Proxy:** Used if the target class does not implement an interface.
*   **Interview Scenario:** How does `@Transactional` work?
    *   Spring creates a proxy around your service bean. When a method is called, the proxy starts a transaction, calls the actual method, and then commits or rolls back the transaction based on the outcome.

### **5. Template Method Design Pattern**
*   **Concept:** Defines the skeleton of an algorithm in a method, deferring some steps to subclasses. It helps in removing boilerplate code.
*   **Spring Implementation:** Any class ending in `Template`.
    *   `JdbcTemplate`, `RestTemplate`, `JmsTemplate`, `TransactionTemplate`.
*   **Interview Scenario:** How does `JdbcTemplate` reduce code?
    *   It handles the repetitive steps (opening connection, creating statement, handling exceptions, closing connection). The developer only provides the SQL and the row mapping logic.

### **6. Adapter Design Pattern**
*   **Concept:** Allows incompatible interfaces to work together. It acts as a bridge.
*   **Spring Implementation:** **Spring MVC HandlerAdapter**.
    *   The `DispatcherServlet` uses `HandlerAdapter` to call different types of controllers (e.g., `@Controller`, `HttpRequestHandler`, or `Servlet`).
*   **Interview Scenario:** How does Spring MVC support different controller types?
    *   The `HandlerAdapter` abstracts the complexity, allowing the `DispatcherServlet` to interact with any controller regardless of its specific implementation.

### **7. Strategy Design Pattern**
*   **Concept:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
*   **Spring Implementation:** This is achieved through **Dependency Injection**.
*   **Interview Scenario:** How do you switch between different implementations at runtime?
    *   If you have an interface `PaymentService` with implementations `PaypalPayment` and `StripePayment`, you can use `@Service` and `@Qualifier` or a `Map<String, PaymentService>` to pick the strategy dynamically based on user input.

### **8. Observer Design Pattern**
*   **Concept:** A one-to-many dependency where when one object changes state, all its dependents are notified automatically.
*   **Spring Implementation:** **Application Events**.
    *   `ApplicationEventPublisher` (to publish events).
    *   `@EventListener` or `ApplicationListener` (to consume events).
*   **Interview Scenario:** How do you decouple the "User Registration" logic from the "Send Email" logic?
    *   After saving the user, publish a `UserRegisteredEvent`. An asynchronous listener can then pick up that event and send the email without blocking the main registration flow.

### **9. Builder Design Pattern**
*   **Concept:** Used for the step-by-step construction of complex objects.
*   **Spring/Java Implementation:** 
    *   `ResponseEntity.ok().header("custom-header", "value").body(user);`
    *   `MockMvcRequestBuilders.get("/url").param("id", "1").build();`
*   **Interview Scenario:** Why use Builder over a Constructor?
    *   When an object has many optional parameters, a builder prevents "constructor telescoping" and makes the code more readable and immutable.

### **10. Front Controller Design Pattern**
*   **Concept:** Provides a single entry point for all incoming requests to an application.
*   **Spring Implementation:** **DispatcherServlet**.
*   **Interview Scenario:** What is the first point of contact for a request in Spring MVC?
    *   The `DispatcherServlet`. It receives the request and delegates it to the appropriate handlers (controllers) for processing.

---

### **Summary Table for Quick Revision**

| Pattern | Spring Component Example |
| :--- | :--- |
| **Singleton** | Default Bean Scope |
| **Prototype** | `@Scope("prototype")` |
| **Factory** | `BeanFactory` / `ApplicationContext` |
| **Proxy** | Spring AOP / `@Transactional` |
| **Template** | `JdbcTemplate` / `RestTemplate` |
| **Adapter** | `HandlerAdapter` in Spring MVC |
| **Strategy** | Dependency Injection (DI) |
| **Observer** | `ApplicationEvent` / `@EventListener` |
| **Builder** | `ResponseEntity` / `UriComponentsBuilder` |
| **Front Controller** | `DispatcherServlet` |