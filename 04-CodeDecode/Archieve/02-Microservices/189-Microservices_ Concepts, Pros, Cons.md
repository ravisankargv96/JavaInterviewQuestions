The following are detailed notes based on the "Microservices Interview Questions and Answers" video by Code Decode.

### **1\. Introduction to Microservices**

* **Definition:** Microservices is an architectural style where an application is structured as a collection of small, autonomous services.  
* **Key Characteristics:**  
  * **Loosely Coupled:** Services are independent of one another.  
  * **Independently Deployable:** You can deploy one service without redeploying the entire application.  
  * **Single Responsibility:** Each service performs a unique function or business capability.  
  * **Polyglot Architecture:** Different services can be built using different programming languages (e.g., Java for one, Python for another) and different databases (SQL, NoSQL).

### **2\. Monolith vs. Microservices**

* **Monolithic Architecture:**  
  * Built as a single unit where the presentation, business, and data access layers are tightly integrated.  
  * **Drawbacks:**  
    * **Single Codebase:** Hard to maintain as it grows large.  
    * **Scalability Issues:** You must scale the entire application, even if only one module (e.g., "Payments") is experiencing high load.  
    * **Deployment dependencies:** A small change in one module requires redeploying the whole application.  
    * **Technology Lock-in:** Difficult to introduce new technologies or frameworks once the app is built.  
    * **Single Point of Failure:** A bug in one module (e.g., memory leak) can bring down the entire system.  
* **Microservices Solution:**  
  * Solves the above problems by breaking the application into functional chunks (e.g., User Service, Product Service, Order Service).  
  * Each service has its own database (Database per service pattern) to ensure loose coupling.

### **3\. The "Why, What, When, & How" of Microservices**

#### **Why use Microservices? (Benefits/Pros)**

* **Freedom of Technology:** Teams can pick the best tool for the job (e.g., Node.js for I/O heavy tasks, Python for Data Science).  
* **Focus on Single Capability:** Code is cleaner and easier to understand.  
* **Independent Scaling:** You can scale only the service that needs it (e.g., during a sale, scale "Order Service" but leave "User Profile Service" as is).  
* **Faster Releases:** Smaller codebases allow for quicker build, test, and deployment cycles.  
* **Fault Isolation:** If one service fails, the others can continue running (e.g., if "Ratings" is down, users can still buy products).

#### **Cons (Disadvantages)**

* **Complexity:** Distributed systems are inherently more complex to manage than monoliths.  
* **Data Consistency:** Maintaining transaction integrity across distributed databases is difficult (requires patterns like SAGA).  
* **Network Latency:** Communication between services occurs over a network, introducing latency and potential failures.  
* **Operational Overhead:** Requires advanced DevOps skills, monitoring, CI/CD pipelines, and container orchestration (like Kubernetes).  
* **Debugging:** Tracing a request through multiple services is harder (requires Distributed Tracing).

#### **When to use Microservices?**

* **Team Size:** When the team is large enough to be split into "Two-pizza teams" (small, autonomous teams handling specific services).  
* **Complexity:** When the application is complex enough to warrant breaking it down. Simple apps should remain monoliths.  
* **Scalability Needs:** When different parts of the system have drastically different scaling requirements.  
* **Time to Market:** When you need faster, independent release cycles for different features.

#### **How to Implement/Design?**

* **Split the Monolith:** Identify business domains (e.g., Inventory, Billing, Shipping) and separate them.  
* **Organize Teams:** Align teams with business capabilities, not technical layers (UI team, DB team).  
* **Build for Failure:** Assume services will fail. Implement resilience patterns like retries and circuit breakers.  
* **Monitoring:** Implement centralized logging (ELK Stack) and monitoring (Prometheus/Grafana) to keep track of system health.

### **4\. Advanced Interview Topics Covered**

#### **Communication Styles**

* **Synchronous:** The client waits for a response (e.g., REST, HTTP). Good for immediate feedback but creates tight coupling and blocking.  
* **Asynchronous:** The client sends a message and continues without waiting (e.g., Message Brokers like Kafka, RabbitMQ). Good for decoupling and high performance.

#### **Data Consistency Patterns**

* **Database per Service:** Each microservice owns its data. No other service can access it directly; they must use APIs.  
* **SAGA Pattern:** A sequence of local transactions where each updates data and publishes an event to trigger the next step. If a step fails, "compensating transactions" are executed to undo previous changes.  
* **CQRS (Command Query Responsibility Segregation):** Separating read and write operations into different models to optimize performance.

#### **Resilience Patterns**

* **Circuit Breaker:** Prevents an application from repeatedly trying to execute an operation that's likely to fail (e.g., calling a down service), allowing the system to recover.  
* **Load Balancing:** Distributing network traffic across multiple servers to ensure no single server becomes a bottleneck.

### **5\. Summary Checklist for Interviews**

* Be ready to explain the **difference** between Monolith and Microservices clearly.  
* Understand the **trade-offs** (Microservices are not a silver bullet; they add complexity).  
* Know **design patterns** for failure handling (Circuit Breaker) and data consistency (SAGA).  
* Be familiar with the ecosystem: **Docker** (Containerization), **Kubernetes** (Orchestration), and **Jenkins/GitLab** (CI/CD).