These detailed notes are based on the interview preparation video for Java developers with 8+ years of experience. They cover core architectural patterns, production monitoring, microservices, security, and DevOps tools.

---

### **1. Production Monitoring and Observability**
For an 8+ year experienced developer, simply writing code isn't enough; you must know how to manage applications in production.

*   **Spring Boot Actuator:** Exposes endpoints to monitor application health, metrics, and environment details.
*   **APM Tools (Application Performance Monitoring):** 
    *   **Prometheus & Grafana:** Prometheus collects metrics; Grafana queries Prometheus to visualize data on dashboards.
    *   **Alerting:** Setting up hooks for Slack, Teams, or Email to notify the team of downtime.
    *   **Others:** New Relic, Dynatrace, Data Dog, AppDynamics (Real-time monitoring and tracing).
*   **Log Management (ELK Stack):**
    *   **Elasticsearch:** Search and analytics engine.
    *   **Logstash:** Log processing pipeline.
    *   **Kibana:** Visualization tool.
    *   *Tip:* Use structured logging to make log searching easier.
*   **Distributed Tracing:** 
    *   **Sleuth & Zipkin:** Essential for microservices to trace a request across different services. 
    *   **Trace ID:** Common across one request.
    *   **Span ID:** Changes as the request moves from one microservice to another.

---

### **2. Microservices Architecture**

#### **Service Discovery (Netflix Eureka)**
*   **Problem:** In cloud environments, IP addresses are dynamic. Hardcoding URLs leads to tight coupling.
*   **Solution:** Eureka acts as a "Phonebook" or Registry. Services register their location dynamically.
*   **High Availability:** Always use multiple Eureka servers in a cluster to avoid a "Single Point of Failure."

#### **Circuit Breaker Pattern (Resilience4j/Hystrix)**
*   Acts as a "safety valve."
*   If Microservice B is down, Microservice A should not wait infinitely (causing a thread backup). The circuit "opens," failing fast and returning a fallback response to the user.

#### **Distributed Transactions (Saga Design Pattern)**
*   Used when a transaction spans multiple services (e.g., Order -> Payment -> Inventory).
*   **Choreography:** Services exchange events; each service knows what to do next. (Good for fewer services).
*   **Orchestration:** A central coordinator tells each service what to do. (Best for complex workflows).

#### **Communication Types**
*   **Synchronous:** REST APIs, gRPC. The caller waits for a response (blocking).
*   **Asynchronous:** Kafka, RabbitMQ, ActiveMQ. "Fire and forget." The caller drops a message in a queue and moves on.

---

### **3. Apache Kafka**
Kafka is a distributed streaming platform used for high-throughput, asynchronous communication.

*   **Architecture:**
    *   **Producer:** Sends messages to a Topic.
    *   **Consumer:** Subscribes to Topics.
    *   **Broker:** Kafka servers that store data.
    *   **Partitions:** Topics are divided into partitions for scalability and parallelism.
*   **Zookeeper vs. KRaft:** Traditionally, Zookeeper managed brokers and leader elections. In Kafka 2.8+, Zookeeper is being replaced by **KRaft** (Kafka Raft Metadata mode) to simplify the architecture.
*   **ISR (In-Sync Replicas):** Replicas that are fully caught up with the Leader. If the Leader fails, one ISR is elected as the new Leader.
*   **Consumer Lag:** The difference between the latest message produced and the last message processed by the consumer.
*   **Offset Marking:** Consumers mark their progress (offsets). This ensures that if a consumer restarts, it knows exactly where it left off, preventing duplicate processing.

---

### **4. REST APIs and Documentation**
*   **Swagger / Open API:** Provides a dashboard for clients/teams to view and test your API endpoints, including request bodies, response codes (401, 403, 500), and headers.
*   **WebClient vs. RestTemplate:** `RestTemplate` is for standard blocking calls; `WebClient` is the modern, reactive (non-blocking) alternative.

---

### **5. Spring Security (OAuth2 & JWT with Okta)**
At 8+ years, you must understand the end-to-end security flow.

*   **Authentication:** Who are you? (Handled by Login/Tokens).
*   **Authorization:** What can you do? (Role-Based Access Control).
*   **Okta Flow:**
    1.  User logs in via Angular (Frontend).
    2.  User is redirected to Okta (Authorization Server).
    3.  Okta returns a **JWT (JSON Web Token)** to the Frontend.
    4.  Frontend sends this JWT in the `Authorization: Bearer <token>` header to the Backend.
    5.  Backend (Spring Boot) validates the token's signature, issuer, and expiry before serving data.

---

### **6. Databases and ORM**
*   **Hibernate (SQL):** Used for relational data where **ACID** properties and complex joins are critical (e.g., Banking, Payments).
*   **MongoDB (NoSQL):** Used for unstructured data, high scalability, and performance where strict schemas aren't required (e.g., Product Catalogs).

---

### **7. Docker**
*   **Concept:** Containerization packages the application with its environment (Java version, OS, libraries) so it "works on every machine."
*   **Common Commands:**
    *   `docker build`: Create an image from a Dockerfile.
    *   `docker run`: Start a container from an image.
    *   `docker ps`: List running containers.
    *   `docker rmi`: Remove an image.
*   **Dockerfile Keywords:**
    *   `FROM`: Base image (e.g., OpenJDK).
    *   `WORKDIR`: Setting the internal directory.
    *   `COPY`: Moving the JAR file from local to container.
    *   `EXPOSE`: Port mapping.
    *   `ENTRYPOINT`: Command to run the application (`java -jar`).

---

### **8. Kubernetes (K8s)**
An orchestrator for managing hundreds of containers.

*   **Pod:** The smallest unit in K8s. Usually contains one container.
*   **Deployment:** Manages the desired state of Pods (e.g., "always keep 3 instances running").
*   **Troubleshooting:**
    *   `kubectl get pods`: List all pods.
    *   `kubectl describe pod <name>`: Inspect events and resource issues.
    *   `kubectl logs <name>`: View application errors.
    *   **CrashLoopBackOff:** A common error where a pod fails immediately after starting (often due to missing environment variables or properties).

---

### **9. CI/CD Pipeline (Continuous Integration / Continuous Deployment)**
A standard automated workflow for code delivery:

1.  **Code Commit:** Developer pushes code to GitHub/GitLab.
2.  **Jenkins (CI):** Webhook triggers a build.
    *   Runs `mvn clean install`.
    *   Runs Unit Tests (JUnit).
    *   Code Analysis (SonarQube).
    *   Creates a JAR and then a Docker Image.
3.  **Image Repository:** Image is pushed to Docker Hub or AWS ECR.
4.  **Deployment (CD):** Kubernetes pulls the new image and updates the Pods in the Dev/QA/Prod environment.

---

### **10. Cloud (AWS)**
Commonly used services for Java developers:
*   **EC2:** Virtual servers.
*   **S3:** Storage for files/images.
*   **RDS:** Managed Relational Databases (MySQL, PostgreSQL).
*   **Lambda:** Serverless computing (code runs without managing servers).
*   **EKS:** Elastic Kubernetes Service (Managed K8s).
*   **CloudWatch:** Monitoring and logging for AWS resources.