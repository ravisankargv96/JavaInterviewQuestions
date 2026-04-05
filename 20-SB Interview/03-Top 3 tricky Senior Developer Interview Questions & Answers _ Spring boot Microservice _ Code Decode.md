Detailed notes based on the "Top 3 tricky Senior Developer Interview Questions & Answers" video from the Code Decode channel, focusing on Spring Boot and Microservices architecture.

---

### **Overview**
The video focuses on high-level architectural challenges that senior developers face during interviews. These questions move beyond basic syntax and focus on distributed systems, data consistency, and security patterns.

---

### **Question 1: How do you handle exceptions globally in a Microservices architecture?**

**The Challenge:**
In a monolithic application, a single `@ControllerAdvice` can manage all exceptions. However, in microservices, multiple services are running independently. If Service A calls Service B and Service B fails, the error response format might differ, leading to inconsistent error handling on the frontend or API Gateway.

**The Solution:**
1.  **Standardized Error Response DTO:** Create a common Java library (JAR) containing a standard `ErrorResponse` class (including fields like `errorCode`, `message`, `timestamp`, and `details`).
2.  **Shared Library Approach:** Include this common library as a dependency in all microservices.
3.  **Global Exception Handler:** Each microservice implements its own `@ControllerAdvice` using the standard DTO from the shared library.
4.  **Feign Client Error Handling:** Use `ErrorDecoder` (in Feign) to intercept 4xx and 5xx errors from downstream services. Instead of passing a raw "500 Internal Server Error," the decoder can wrap the specific message from the downstream service into a custom exception that the calling service understands.

---

### **Question 2: How do you manage Distributed Transactions across Microservices?**

**The Challenge:**
Since each microservice has its own database, traditional ACID transactions (using `@Transactional`) do not work across service boundaries. If an "Order Service" succeeds but the "Payment Service" fails, you cannot simply "roll back" the database entry in the Order Service automatically.

**The Solution: The Saga Pattern**
The Saga pattern manages data consistency across microservices in distributed transaction scenarios. It breaks a large transaction into a sequence of local transactions.

**Two Implementation Types:**
1.  **Choreography-Based Saga:**
    *   There is no central coordinator.
    *   Each service performs its transaction and publishes an event (e.g., via Kafka or RabbitMQ).
    *   Other services listen to those events and execute their local transactions.
    *   *Pros:* Simple for small workflows.
    *   *Cons:* Can become confusing as the number of services grows.

2.  **Orchestration-Based Saga:**
    *   A central "Saga Execution Coordinator" (Orchestrator) tells each service what to do.
    *   The orchestrator handles the flow and decides which service to call next.
    *   *Pros:* Better for complex workflows; centralized logic.

**Compensating Transactions:**
If any step in the Saga fails, the system must execute "Compensating Transactions" (undo operations). For example, if the Payment fails, the Saga triggers a "Cancel Order" command in the Order Service to maintain eventual consistency.

---

### **Question 3: How do you implement Security (Authentication & Authorization) in Microservices?**

**The Challenge:**
You cannot have every microservice prompt the user for a username and password. You need a centralized way to verify identity and permissions.

**The Solution: OAuth2 and JWT with API Gateway**
1.  **Identity Provider (Auth Service):** A dedicated service (like Keycloak, Okta, or a custom Spring Security service) handles user login. Upon successful login, it issues a **JWT (JSON Web Token)**.
2.  **API Gateway as a Guard:** All client requests first hit the API Gateway (e.g., Spring Cloud Gateway).
    *   The Gateway validates the JWT's signature and expiration.
    *   The Gateway can also perform high-level authorization (checking if the user has a "User" or "Admin" role).
3.  **Token Propagation:** Once validated, the Gateway forwards the request to the internal microservices, passing the JWT in the `Authorization` header.
4.  **Service-Level Security:** Internal microservices extract the user info and roles from the JWT claims to perform fine-grained authorization (using `@PreAuthorize` annotations).

**Key Benefit:** This ensures "Statelessness." Microservices don't need to store session data; they simply trust the cryptographically signed JWT.

---

### **Key Takeaways for Senior Roles**
*   **Eventual Consistency:** Accept that in microservices, data might not be consistent across all services instantly, but it will be eventually (via Saga).
*   **Standardization:** Use shared libraries for cross-cutting concerns like logging and exception handling.
*   **Centralization vs. Decentralization:** Know when to use a Gateway for security and when to let services handle their own logic.