These detailed notes summarize the core concepts, patterns, and interview questions covered in the "Top Microservices Interview Questions and Answers in 2025" video by Code Decode.

### 1. Introduction to Microservices
Microservices are an architectural style where an application is built as a collection of small, autonomous services modeled around a specific business domain.
*   **Key Characteristics:** Loosely coupled, independently deployable, and maintained by small, cross-functional teams.
*   **The Shift:** Moving from a "Monolith" (one large codebase) to "Microservices" allows for faster scaling and technology flexibility.

### 2. Monolithic vs. Microservices Architecture
*   **Monolith:** All modules (UI, Business Logic, Data Access) are in a single package. 
    *   *Drawbacks:* Difficult to scale specific parts, long build times, a single bug can bring down the entire system, and tech stack "lock-in."
*   **Microservices:** Each business function is a separate service.
    *   *Benefits:* Scalability (scale only the high-traffic service), fault isolation (one service crashing doesn't kill others), and the ability to use different languages for different services (Polyglot persistence/programming).

### 3. Core Design Patterns
Interviewers frequently focus on how microservices handle distributed challenges:

*   **API Gateway:**
    *   Acts as a single entry point for all clients.
    *   Handles cross-cutting concerns: Authentication, SSL termination, rate limiting, and request routing.
    *   Example: Spring Cloud Gateway.

*   **Service Discovery (Eureka):**
    *   In a dynamic environment, IP addresses of services change frequently.
    *   Services register themselves with the Service Discovery server (Registry).
    *   Clients query the Registry to find the location of a service.

*   **Circuit Breaker Pattern (Resilience4j):**
    *   Prevents cascading failures. If Service A calls Service B and Service B is down, the Circuit Breaker "trips" and returns a fallback response instead of hanging or wasting resources.
    *   States: Closed (normal), Open (failure detected, requests blocked), Half-Open (testing if the service recovered).

*   **Saga Pattern (Distributed Transactions):**
    *   Since each service has its own database, traditional ACID transactions don't work.
    *   **Choreography:** Services communicate via events without a central coordinator.
    *   **Orchestration:** A central controller tells each service what to do. If a step fails, the Orchestrator triggers "compensating transactions" to undo previous steps.

*   **CQRS (Command Query Responsibility Segregation):**
    *   Separates the "write" model (Commands) from the "read" model (Queries). This is useful when read and write workloads vary significantly.

### 4. Communication Between Services
*   **Synchronous:** Using REST or gRPC. The client waits for a response.
*   **Asynchronous:** Using message brokers like RabbitMQ or Apache Kafka. The client sends a message and moves on; the consumer processes it whenever it's ready. This improves system decoupling.

### 5. Data Management
*   **Database per Service:** Each microservice should have its own private database to ensure loose coupling. No service should access another service's database directly.
*   **Externalized Configuration:** Using tools like Spring Cloud Config to manage properties/secrets outside of the jar file, allowing changes without rebuilding the code.

### 6. Fault Tolerance and Resilience
*   **Bulkhead Pattern:** Isolating elements of an application into pools so that if one fails, the others continue to function (similar to a ship's hull).
*   **Retry Pattern:** Automatically retrying a failed operation a certain number of times before giving up.
*   **Timeout:** Setting a strict limit on how long a service will wait for a response from another service.

### 7. Security in Microservices
*   **OAuth2 and JWT (JSON Web Tokens):** Instead of session-based security, microservices use stateless JWTs. 
*   The API Gateway usually authenticates the user and passes the token/user info to the downstream services via headers.

### 8. Monitoring and Tracing
*   **Distributed Tracing:** Tools like Zipkin or Sleuth (Micrometer Tracing in newer versions) help track a single request as it travels through multiple services by using a "Trace ID."
*   **Log Aggregation:** Using ELK Stack (Elasticsearch, Logstash, Kibana) to search logs from all services in one place.

### 9. Frequently Asked Interview Questions
1.  **What is the difference between Orchestration and Choreography in Sagas?** (Know the central coordinator vs. event-based distinction).
2.  **How do you handle data consistency across services?** (Mention Saga pattern and eventual consistency).
3.  **What happens if the Service Registry goes down?** (Discuss client-side caching of service locations).
4.  **How do Microservices communicate?** (Rest template vs. WebClient vs. Feign Client).
5.  **What are the challenges of Microservices?** (Mention complexity, network latency, and operational overhead).

### 10. Summary for 2025
The video emphasizes that for 2025, candidates should not just know what a microservice is, but how to handle **observability** (metrics/tracing), **resilience** (Circuit Breakers), and **distributed data integrity** (Sagas), as these are the most common pain points in real-world production environments.