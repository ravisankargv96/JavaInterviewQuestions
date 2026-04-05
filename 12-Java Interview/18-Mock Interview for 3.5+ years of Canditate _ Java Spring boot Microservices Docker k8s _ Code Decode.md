These notes provide a comprehensive summary of the mock interview for a Java Developer with 3.5+ years of experience, focusing on Spring Boot, Microservices, Cloud, and DevOps.

---

### **1. Candidate Overview**
*   **Experience:** 3.5+ years.
*   **Core Tech Stack:** Java, Spring Boot, Spring Cloud.
*   **Architecture:** Microservices with Cloud-native deployment.
*   **Key Tools:** Kafka, Docker, Kubernetes (EKS), AWS, ELK Stack, Grafana, Prometheus.

---

### **2. Microservices Architecture**
*   **Service Discovery & Registry:** 
    *   **Purpose:** Essential for cloud environments where instances have dynamic IP addresses and ports.
    *   **Mechanism:** Acts like a "phone book." Services register themselves upon startup. Other services look up names to find the exact location (IP/Port).
    *   **Resilience:** Service discovery should have multiple instances. If one registry instance crashes, others maintain the mapping to prevent a single point of failure.
*   **API Gateway:**
    *   **Entry Point:** Acts as a single entry point for all client requests.
    *   **Benefits:** Provides abstraction (the UI only needs one URL), handles cross-cutting concerns (Security, SSL termination), and prevents exposing internal microservice URLs to the public.
    *   **Implementation:** Uses Route Locator Builders to define paths to various services.

---

### **3. Security Implementation**
*   **Frameworks:** OAuth2 and OpenID Connect (OIDC).
*   **Authorization Server:** Used **Octa** as a third-party service to manage credentials and provide tokens.
*   **Authentication vs. Authorization:**
    *   **Authentication:** Handled via OIDC.
    *   **Authorization:** Handled via OAuth2 using Role-Based Access Control (RBAC).
*   **JWT (JSON Web Token) Structure:**
    *   **Header:** Contains the token type and encoding algorithm (Base64).
    *   **Payload (Body):** Contains "Claims" (User ID, Name, Roles, etc.).
    *   **Signature:** The most critical part for security. It is generated using a secret key. The backend validates this signature to ensure the token hasn't been tampered with.
*   **Granularity:**
    *   **Class/API Level:** Security Filter Chains.
    *   **Method Level:** `@PreAuthorize` annotations for specific role-based access.

---

### **4. Inter-Service Communication & Performance**
*   **Synchronous:** Handled via **OpenFeign** (declarative REST client).
*   **Asynchronous:** Handled via **Kafka** (Message Broker) for non-blocking tasks like sending emails.
*   **Performance Optimization:**
    *   **Database:** Writing efficient queries and utilizing proper indexing.
    *   **Caching:** Using `@Cacheable` to reduce I/O calls to the database for data that doesn't change frequently.
    *   **Communication:** Converting synchronous calls to asynchronous where possible to free up threads.

---

### **5. Observability (Logging & Monitoring)**
*   **Logging:** Used **SLF4J** with various levels (Info, Debug, Warn, Error).
*   **Centralized Logging (ELK Stack):**
    *   **Elasticsearch:** Stores and indexes the logs.
    *   **Logstash:** Collects and filters logs from different services.
    *   **Kibana/Grafana:** Visualizes the logs and metrics.
*   **Monitoring:** 
    *   **Prometheus:** Scrapes metrics from services via **Micrometer**.
    *   **Spring Boot Actuator:** Provides health, readiness, and liveness endpoints, as well as heap dump and memory info.
    *   **Alerting:** Grafana is configured with thresholds to send alerts via Slack, Microsoft Teams, or Email when failures occur.

---

### **6. Configuration & API Management**
*   **API Documentation:** Used **Swagger (OpenAPI)** to share the contract (endpoints, params, response bodies) with consumers.
*   **Versioning:** Managed breaking changes using URI versioning (e.g., `/v1/api`, `/v2/api`) or request parameters to maintain backward compatibility.
*   **Config Management:** 
    *   **Spring Cloud Config Server:** Centralized property management.
    *   **GitOps:** Properties are stored in a GitHub repository.
    *   **Refresh Scope:** Used `@RefreshScope` to update properties in a running application without a restart.

---

### **7. DevOps and Cloud (AWS)**
*   **Deployment:** GitOps approach using **Jenkins** for CI and **Argo CD** for CD.
*   **Containerization:**
    *   **Google Jib:** Used to create Docker images directly without manually writing a Dockerfile.
    *   **Amazon ECR:** Used as a private container registry to store Docker images securely.
*   **Orchestration:** Managed via **Amazon EKS (Elastic Kubernetes Service)**.
*   **CI/CD Pipeline Flow:**
    1.  Developer pushes code to a Git branch.
    2.  Jenkins triggers the CI pipeline (Maven build, Unit tests, SonarQube analysis).
    3.  A Docker image is built and pushed to Amazon ECR.
    4.  Argo CD detects the new image/manifest change.
    5.  Argo CD pulls the latest image and updates the Kubernetes cluster (EKS) to spin up new containers.

---

### **8. Interviewer Tips & Scenario-Based Advice**
*   **Utility Classes:** Use them to share common logic between different API versions to avoid code duplication and SonarQube issues.
*   **Signature Security:** Always emphasize that the JWT signature is what prevents hackers from modifying the payload.
*   **Cloud Scalability:** Service discovery is vital in the cloud because you cannot hardcode IP addresses of instances that scale up and down dynamically.