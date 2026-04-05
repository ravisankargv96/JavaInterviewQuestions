Here are detailed notes on **API Gateways in Microservices** based on the video:

### **API Gateway: Introduction and Importance**

* **Context:** In a microservices architecture, a frontend (client) interacts with multiple independent microservices (e.g., Vaccination Center Service, Citizen Service, Eureka Server, etc.).  
* **The Problem:**  
  * **Complexity for Client:** As the application grows, a single monolithic function might be split into multiple microservices. If the frontend has to call each microservice individually (e.g., getting citizen data from one service and session data from another), the client code becomes complex and tightly coupled to the backend structure.  
  * **Hardcoded URLs:** Without an abstraction layer, the client might need to know specific ports and addresses (e.g., 8081 for Citizen, 8082 for Vaccination), which is brittle.  
  * **Code Redundancy (Cross-Cutting Concerns):** Functionalities like Authentication, Authorization, Logging, and Monitoring are required for *every* microservice. Implementing these in every single service leads to massive code duplication.  
  * **Versioning Issues:** If a backend service updates from v1 to v2, older mobile clients might break while newer web clients work. Managing this compatibility at the service level is difficult.  
* **The Solution:** **API Gateway**.  
  * It acts as an **abstraction layer** or a single entry point between the client (frontend) and the backend microservices.  
  * The client only talks to the API Gateway. The Gateway handles the routing to the appropriate microservice.

### **Key Functions of an API Gateway**

1. **Routing:** It accepts a request from the client and routes it to the correct microservice based on the request path or parameters.  
2. **Abstraction/Loose Coupling:** The client doesn't need to know the internal architecture (how many microservices exist or where they are located). Changes in the backend don't necessarily require changes in the frontend.  
3. **Centralized Cross-Cutting Concerns:**  
   * **Security:** Handles Authentication and Authorization centrally.  
   * **Logging & Monitoring:** Tracks all incoming requests and outgoing responses in one place.  
4. **Versioning Support:** Can route requests from older clients to v1 services and newer clients to v2 services seamlessly.

### **Common API Gateway Implementations**

1. **Netflix Zuul:** (Historically popular but now in maintenance mode).  
2. **Spring Cloud Gateway:** (The modern successor to Zuul).  
3. **Amazon API Gateway:** (AWS managed service).  
4. **Azure API Gateway:** (Azure managed service).  
5. **MuleSoft.**

### **Netflix Zuul vs. Spring Cloud Gateway**

#### **1\. Netflix Zuul**

* **Status:** In **maintenance mode**. Netflix OSS (including Hystrix and Ribbon) is largely being phased out for new feature development, receiving only bug fixes and security patches. Eureka is the notable exception that is still active.  
* **Architecture:** **Blocking API (Synchronous).**  
  * It uses a thread-per-request model.  
  * If there are 4 threads available (t1, t2, t3, t4) and 4 requests come in (r1, r2, r3, r4), all threads are occupied.  
  * If a 5th request (r5) arrives, it must **wait in a queue** until a thread becomes free.  
  * **Disadvantage:** Resource-intensive and can lead to poor user experience under high load due to blocking.

#### **2\. Spring Cloud Gateway**

* **Status:** The **recommended modern replacement** for Zuul in the Spring ecosystem.  
* **Architecture:** **Non-Blocking API (Asynchronous).**  
  * Built on top of Spring WebFlux and Project Reactor.  
  * It handles requests asynchronously in the background.  
  * A thread is always available to accept an incoming request; it does not block the client while processing.  
  * **Advantage:** Higher throughput, better resource utilization, and improved scalability compared to Zuul.

### **Summary of the Shift**

The industry is moving away from Netflix Zuul due to its blocking nature and maintenance status, transitioning towards **Spring Cloud Gateway** for its non-blocking capabilities and active development within the Spring Cloud ecosystem.

