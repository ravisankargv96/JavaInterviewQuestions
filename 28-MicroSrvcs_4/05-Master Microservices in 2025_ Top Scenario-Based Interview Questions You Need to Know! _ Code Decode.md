These notes cover the key concepts, scenario-based interview questions, and architectural patterns discussed in the "Master Microservices in 2025" video by Code Decode.

---

### **1. Core Architectural Philosophy**
Microservices are designed to solve the problems of monolithic applications: scalability, independent deployment, and fault isolation. The transition from "What is a Microservice?" to "How do you handle [X] scenario?" is the focus of modern interviews.

---

### **2. Handling Distributed Transactions (The Saga Pattern)**
**Scenario:** An e-commerce user places an order. This requires updating the Order Service, Inventory Service, and Payment Service. If the payment fails, how do you ensure the inventory is not deducted?

*   **The Problem:** Distributed systems lack "ACID" properties across multiple databases.
*   **The Solution: Saga Pattern.** A sequence of local transactions where each service performs its work and publishes an event.
    *   **Choreography-Based Saga:** No central orchestrator. Each service listens to events from others and acts accordingly. If Service B fails, it emits a "Fail" event, and Service A performs a "Compensating Transaction" (reverting the change).
    *   **Orchestration-Based Saga:** A central "Saga Execution Coordinator" tells each service what to do. If one step fails, the orchestrator manages the rollback of all previous steps.
*   **Compensating Transaction:** This is the logic used to "undo" a previous successful step if a later step in the chain fails.

---

### **3. Fault Tolerance and Cascading Failures**
**Scenario:** Service A calls Service B. Service B is experiencing high latency or is down. How do you prevent Service A from crashing or exhausting its thread pool?

*   **The Problem:** Cascading failures. One slow service can drag down the entire ecosystem.
*   **The Solution: Circuit Breaker Pattern (Resilience4j).**
    *   **Closed State:** Normal operation. Requests flow through.
    *   **Open State:** If the failure rate crosses a threshold, the circuit "trips." No requests are sent to the failing service; instead, an immediate error or "Fallback" is returned.
    *   **Half-Open State:** After a timeout, the system allows a few "test" requests to see if the downstream service has recovered.
*   **Fallback Method:** A secondary logic that provides a default response (e.g., cached data or a "try again later" message) so the user experience isn't completely broken.

---

### **4. Communication Strategies: Sync vs. Async**
**Scenario:** You have a process that takes 10 seconds to complete. Should you use REST?

*   **Synchronous (REST/gRPC):** Best for immediate feedback (e.g., Login). However, it blocks threads and increases latency in long chains.
*   **Asynchronous (Kafka/RabbitMQ):** Best for long-running tasks or decoupling services.
    *   **Scenario:** After a user signs up, you need to send a welcome email, generate a profile, and alert the marketing team. Using a Message Broker (Kafka) allows the User Service to return "Success" immediately while other services process the events in the background.

---

### **5. Service Discovery and Routing**
**Scenario:** In a cloud environment, microservices scale up and down, changing their IP addresses and ports dynamically. How does Service A find Service B?

*   **Service Registry (Eureka):** A "phone book" where every service instance registers its location (IP/Port) upon startup and sends periodic "heartbeats."
*   **Client-Side Load Balancing (Spring Cloud LoadBalancer):** The client (Service A) asks the Registry for the addresses of Service B, chooses one (Round Robin, etc.), and calls it directly.
*   **API Gateway (Spring Cloud Gateway):** Acts as a single entry point for all clients. It handles:
    *   **Routing:** Directing traffic to the correct service.
    *   **Security:** Authenticating requests (JWT) before they reach internal services.
    *   **Cross-cutting concerns:** Rate limiting, logging, and CORS.

---

### **6. Data Management: Database per Service**
**Scenario:** Why shouldn't all microservices share one giant SQL database?

*   **Tight Coupling:** If you change the schema for Service A, you might break Service B.
*   **Single Point of Failure:** If the database goes down, the entire system dies.
*   **Scalability:** Different services have different needs (e.g., Order service needs SQL for ACID, while Product Search needs NoSQL/Elasticsearch).
*   **Joining Data:** Since databases are separate, you cannot perform SQL "JOINs" across services.
    *   **Solution:** Use **API Composition** (The Gateway calls multiple services and joins the data) or **CQRS (Command Query Responsibility Segregation)**.

---

### **7. Observability and Troubleshooting**
**Scenario:** A user reports an error on the checkout page. The request passed through 5 different microservices. How do you find which service failed and why?

*   **Distributed Tracing (Sleuth and Zipkin):**
    *   **Trace ID:** A unique ID assigned to the entire request journey across all services.
    *   **Span ID:** An ID for the work done within a single service.
*   **Centralized Logging (ELK Stack):**
    *   **Elasticsearch:** Stores logs.
    *   **Logstash:** Collects and processes logs.
    *   **Kibana:** Visualizes logs. By searching for a specific Trace ID in Kibana, you can see the logs from all services involved in that specific request.

---

### **8. Key Performance & Security Questions**
*   **How do you handle security?** Use OAuth2 and JWT. The API Gateway validates the token; internal services trust the Gateway or re-validate the token.
*   **How do you handle configuration changes?** Use **Spring Cloud Config Server**. Instead of hardcoding properties, services fetch them from a central Git repository. Use `@RefreshScope` to update configurations without restarting the service.
*   **How do you ensure data consistency?** Use the **Eventual Consistency** model rather than Strong Consistency. Accept that data might be "out of sync" for a few milliseconds across the system.

---

### **9. Summary Checklist for Interview Success**
1.  **Understand Patterns:** Saga, Circuit Breaker, CQRS, API Gateway.
2.  **Know the Tools:** Spring Boot, Resilience4j, Eureka, Kafka, Zipkin, Docker/Kubernetes.
3.  **Think in "Trade-offs":** Always explain *why* you chose a specific pattern (e.g., "I used Async communication here to reduce latency, even though it adds complexity").