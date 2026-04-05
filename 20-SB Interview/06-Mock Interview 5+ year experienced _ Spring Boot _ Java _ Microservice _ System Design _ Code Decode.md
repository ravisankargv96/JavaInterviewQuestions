These detailed interview notes are based on the **'Mock Interview 5+ year experienced | Spring Boot | Java | Microservice | System Design'** video by Code Decode.

---

### **Candidate Profile**
*   **Experience:** 5+ Years.
*   **Current Location:** Canada (Montreal).
*   **Core Tech Stack:** Java, Spring Boot, Microservices, React, Node.js, .NET, Oracle, SQL, NoSQL.
*   **Focus:** Backend Development with Full Stack capabilities.

---

### **1. Spring Security & Authentication**
*   **JWT (JSON Web Token):** Used for securing stateless REST APIs. It avoids the burden of re-authenticating every request by using a hash to authorize users.
*   **Token Components:** Header, Payload (Claims), and Signature.
*   **Refresh Tokens:** Used to obtain a new access token when the current one expires, handled via TTL (Time to Live).
*   **Security Best Practice:** Never store sensitive information (like passwords) in the JWT body/claims because JWTs are typically Base64 encoded and can be easily decoded at `jwt.io`.
*   **Authorization Server:** The candidate used **Octa (OAuth 2.0)**. 
    *   **Workflow:** Frontend hits Octa $\rightarrow$ User logs in $\rightarrow$ Octa returns Access Token $\rightarrow$ Frontend sends Token to Backend $\rightarrow$ Backend validates token signature.

### **2. Spring Boot Schedulers & Shedlock**
*   **Problem:** In a cloud environment with multiple instances of a microservice, a standard `@Scheduled` task will run on every instance simultaneously. For example, if three instances run at 12:00 PM, an "Add Record" task will create three duplicate entries.
*   **Solution:** **Shedlock**. It ensures that a task is executed only once at a time. It uses an external store (like a database table or Redis) to acquire a lock, ensuring "singleton" behavior across distributed instances.

### **3. Caching Strategies (Redis & Hibernate)**
*   **Redis:** Used as an in-memory key-value store to reduce database latency.
*   **Dirty Reads:** Occur when the cache isn't updated after a DB change. 
    *   **Mitigation:** Use TTL or "Eventual Consistency." Caching is best for data where 100% real-time accuracy is less critical than speed (e.g., Twitter feeds).
*   **Hibernate L1 vs. L2 Cache:**
    *   **L1 (First Level):** Associated with the **Session**. Enabled by default. Scope is limited to the transaction.
    *   **L2 (Second Level):** Associated with the **Session Factory**. Must be explicitly enabled (e.g., using Ehcache or Redis). Scope is across all sessions.
*   **Session Factory:** Should be a **Singleton** because it is a "heavyweight" object that maintains DB connections and metadata.

### **4. GraphQL vs. REST API**
*   **REST Limitations:** Endpoint proliferation (too many URLs) and "Over-fetching" (receiving more data than needed).
*   **GraphQL Advantages:**
    *   **Single Endpoint:** One URL handles all queries.
    *   **Partial Response:** The client specifies exactly which fields they want (e.g., just `id` and `name` from a 100-field `Employee` object).
    *   **No Source Code Changes:** Adding a field to a response doesn't require a backend DTO change; the client just updates the query.

### **5. Configuration: Properties vs. YAML**
*   **Properties:** Uses dot-notation (`server.port=8080`). Can be repetitive.
*   **YAML:** Hierarchical and more readable. Supports advanced data types like **Maps and Lists**, which are difficult to represent in standard `.properties` files.

### **6. Microservices: Distributed Transactions**
*   **2PC (Two-Phase Commit):** A synchronous, "all-or-nothing" approach. Can cause performance bottlenecks.
*   **Saga Pattern:** An asynchronous approach using **Compensating Transactions**. If one step fails, the system triggers a "revert" event to undo previous successful steps.

### **7. System Design: E-Commerce Site**
The candidate designed a high-level architecture for a site like Amazon/Flipkart.
*   **Functional Requirements:** Search, Add to Cart, Place Order, Delivery Tracking, Notifications.
*   **Non-functional:** Scalability (handling sale peaks), Latency, Spam prevention (Rate Limiting).
*   **Components:**
    *   **Load Balancer:** Nginx for traffic distribution and proxying.
    *   **Search Service:** Uses **Elasticsearch** for fuzzy searching and **Cassandra (NoSQL)** for eventual consistency.
    *   **Cart/Order Service:** Uses **MySQL** for strict ACID properties and structured data.
    *   **Kafka:** Used for asynchronous communication (Notifications, Inventory updates, Tracking logs).
    *   **CDN (Content Delivery Network):** For serving static images/videos geographically closer to the user to reduce latency.
    *   **Monitoring:** Cloud alerts to trigger **Autoscaling** when CPU utilization exceeds a threshold.

### **8. Database Optimization**
*   **Query Tuning:** Analyzing execution plans and adding **Indexes**.
*   **Scalability:**
    *   **Partitioning:** Dividing a table within the same server.
    *   **Sharding:** Distributing data across multiple database servers (e.g., by geography).

### **9. Frontend Optimization**
*   **Pagination:** 
    *   **Infinite Scrolling:** Triggering a backend call when the user nears the end of the viewport.
    *   **Throttling/Debouncing:** Delaying the API call until the user stops typing in the search bar to prevent hitting the server for every keystroke.

### **10. DevOps & CI/CD**
*   **Branching Strategy:** Feature $\rightarrow$ Dev $\rightarrow$ QA $\rightarrow$ Pre-prod (Staging) $\rightarrow$ Master/Prod.
*   **CI/CD Pipeline:** Jenkins/GitHub Actions.
    *   **CI:** Automates Junit testing and SonarQube static analysis.
    *   **CD:** Builds Docker images and deploys them.
*   **Containerization:** Docker for consistency across environments.
*   **Orchestration:** **Kubernetes (K8s)**.
    *   **Self-healing:** K8s automatically restarts pods if they crash.
    *   **Rollbacks:** K8s maintains image version history, allowing for an automated rollback if a new deployment fails.
*   **Infrastructure as Code (IaC):** Mentioned using **Pulumi** (similar to Terraform) for orchestrating cloud resources.

### **11. Testing & Code Quality**
*   **Unit Testing:** Junit 5 and Mockito for mocking DB calls.
*   **SonarQube:** Used for static code analysis to find "Technical Debt" and code smells.
*   **Code Coverage:** Candidate targets 90-95%.
*   **Exclusion:** DTOs (Getters/Setters) should be excluded from coverage reports using regex patterns in the Maven/Gradle build file, as testing them adds no functional value.