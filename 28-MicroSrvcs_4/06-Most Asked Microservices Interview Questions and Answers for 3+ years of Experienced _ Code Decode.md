These detailed notes cover the core concepts, architectural patterns, and interview questions discussed in the "Most Asked Microservices Interview Questions" video by Code Decode.

---

### **1. Monolithic vs. Microservices Architecture**

*   **Monolithic Architecture:**
    *   **Definition:** All software components of an application are bundled together into a single unit (e.g., one large JAR or WAR file).
    *   **Pros:** Simple to develop initially, easy to test, and simple to deploy.
    *   **Cons:** Hard to scale (you must scale the whole app, not just one feature), long build times, tech stack lock-in (hard to change languages), and a single point of failure (one bug can crash the entire system).
*   **Microservices Architecture:**
    *   **Definition:** An architectural style where an application is a collection of small, autonomous services modeled around a business domain.
    *   **Pros:** Independent deployment, technology diversity (use Java for one, Python for another), high scalability, and fault isolation.
    *   **Cons:** Complexity in communication, data consistency issues, and difficulty in monitoring/debugging across services.

---

### **2. Core Components of a Microservices Ecosystem**

To manage multiple services effectively, several patterns and tools are used:

*   **Service Discovery (e.g., Netflix Eureka):**
    *   **Problem:** Microservices are dynamic; their IP addresses and ports change frequently (especially in cloud environments). Hardcoding IPs is impossible.
    *   **Solution:** Eureka acts as a "Phonebook." Every service registers itself with Eureka on startup. When Service A needs to call Service B, it asks Eureka for Service B’s current location.
*   **API Gateway (e.g., Spring Cloud Gateway / Zuul):**
    *   **Definition:** A single entry point for all client requests.
    *   **Responsibilities:** 
        *   **Authentication/Authorization:** Check if the user is logged in.
        *   **Routing:** Forward the request to the correct microservice.
        *   **Rate Limiting:** Prevent users from spamming the API.
        *   **Load Balancing:** Distribute traffic across multiple instances of a service.
*   **Load Balancer (e.g., Ribbon / Spring Cloud LoadBalancer):**
    *   Distributes incoming network traffic across a group of backend servers to ensure no single server bears too much load.

---

### **3. Microservices Communication**

*   **Synchronous Communication:**
    *   **Protocol:** Usually REST (HTTP/HTTPS) or gRPC.
    *   **Workflow:** The sender waits for a response from the receiver. If the receiver is slow, the sender is blocked.
*   **Asynchronous Communication:**
    *   **Protocol:** Messaging queues like RabbitMQ or Apache Kafka.
    *   **Workflow:** The sender sends a message to a broker and continues its work. It does not wait for an immediate response. This is better for decoupling and handling high traffic.

---

### **4. Handling Failures: The Circuit Breaker Pattern**

*   **Concept:** Inspired by electrical circuit breakers. If a downstream service is failing or slow, the "circuit" opens.
*   **States:**
    *   **Closed:** Everything is working fine; requests pass through.
    *   **Open:** The service is failing; the circuit breaker rejects requests immediately to prevent cascading failures and give the failing service time to recover.
    *   **Half-Open:** After a timeout, the breaker allows a few "test" requests to see if the service is back up. If they succeed, the circuit closes.
*   **Tools:** Resilience4j (preferred) or Netflix Hystrix (now in maintenance mode).

---

### **5. Configuration Management (Spring Cloud Config)**

*   **Problem:** In a monolith, properties are in `application.properties`. In microservices, managing 50 different properties files across 50 services is a nightmare.
*   **Solution:** Centralized Configuration Server.
    *   All configuration files are stored in a central repository (like Git).
    *   Each microservice fetches its specific configuration from this server at startup.
    *   Changes can be pushed to all services without needing to rebuild the code.

---

### **6. Data Management and Distributed Transactions**

*   **Database per Service:** The recommended approach. Each service owns its data to ensure loose coupling.
*   **The Challenge:** How do you maintain consistency across services (e.g., an Order service and an Inventory service)?
*   **Saga Pattern:**
    *   A sequence of local transactions. If one step fails, the Saga executes **compensating transactions** to undo the previous successful steps.
    *   **Choreography:** Services talk to each other via events (no central leader).
    *   **Orchestration:** A central "Saga Manager" tells each service what to do.

---

### **7. Observability and Monitoring**

*   **Distributed Tracing (Spring Cloud Sleuth & Zipkin):**
    *   **Trace ID:** A unique ID for an entire request journey across multiple services.
    *   **Span ID:** A unique ID for a specific segment of work within a single service.
    *   **Purpose:** Helps developers track a single request as it hops from the Gateway to Service A, then Service B, to find exactly where a bottleneck or error occurred.
*   **Log Aggregation:** Tools like ELK Stack (Elasticsearch, Logstash, Kibana) are used to collect logs from all services into one searchable dashboard.

---

### **8. Security in Microservices**

*   **JWT (JSON Web Token):** Usually, the API Gateway handles authentication. It generates a JWT token. This token is passed in the header of every internal request.
*   Each microservice can then validate the token independently without needing to re-query a central database for every user action.

---

### **Common Interview Questions Summary**

1.  **What is the difference between Monolith and Microservices?** (Focus on scalability and deployment).
2.  **How do microservices communicate?** (Sync vs Async).
3.  **What is the role of Eureka?** (Service Discovery).
4.  **Why do we need an API Gateway?** (Security, Routing, Rate limiting).
5.  **How do you handle a service failure?** (Explain Circuit Breaker).
6.  **How do you handle distributed transactions?** (Explain Saga Pattern).
7.  **How do you track a request across services?** (Trace ID and Span ID).
8.  **What happens if the Config Server goes down?** (Explain caching/failover strategies).