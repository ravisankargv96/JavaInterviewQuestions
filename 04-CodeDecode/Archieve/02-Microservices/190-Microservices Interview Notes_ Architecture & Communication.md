The following are detailed notes from Part 2 of the "Microservices Interview Questions and Answers" video by Code Decode.

### **1\. Microservices Architecture Components**

* **Client:** The end-user interface (e.g., web or mobile app) that initiates requests.  
* **Identity Provider (IdP):** Handles authentication and authorization (e.g., Okta, Spring Security). Ideally integrated at the API Gateway level to avoid duplicating logic across services.  
* **API Gateway:**  
  * Acts as the single entry point for all client requests.  
  * Handles cross-cutting concerns like security, routing, and rate limiting.  
  * Routes requests to the appropriate backend microservices (e.g., Citizen Service, Vaccination Center).  
* **Service Discovery (Eureka):**  
  * Acts as a phonebook/registry for microservices.  
  * **Problem Solved:** In cloud environments, IP addresses and ports of microservices change dynamically (e.g., due to autoscaling or restarts). Hardcoding URLs is impossible.  
  * **Mechanism:** Services register themselves with Eureka (e.g., "I am Service A at IP 10.0.0.1"). When Service A needs to call Service B, it asks Eureka for Service B’s current address.  
* **Microservices:** Autonomous services (e.g., Citizen Service, Vaccination Service) that handle specific business logic.

### **2\. Key Features of Microservices**

* **Independent Development & Deployment:** Application is broken into smaller chunks. One team can upgrade a service (e.g., to Java 11\) while another remains on an older stack (e.g., Java 8\) without conflict.  
* **Decentralization:**  
  * Each service manages its own data (Database per service).  
  * No centralized database (unlike Monoliths), ensuring loose coupling.  
* **Black Box:**  
  * Services hide their internal implementation details.  
  * A service (e.g., Vaccination) doesn't need to know the tech stack or internal logic of another service (e.g., Citizen), only the API contract.  
* **Security Decoupling:**  
  * Security logic is often offloaded to the platform or API Gateway, freeing developers to focus on business logic.  
* **Polyglot:**  
  * Different services can be written in different languages (Java, Python, Node.js) based on the specific problem they solve or team expertise.

### **3\. Inter-Service Communication**

Services communicate in two primary ways:

#### **A. Synchronous Communication**

* **Mechanism:** The caller waits for a response before proceeding (Blocking).  
* **Protocol:** Typically HTTP/REST.  
* **Use Case:** Real-time requirements where the user needs immediate feedback (e.g., User Login, Fetching User Profile).  
* **Example:** A user requests vaccination center details on the UI. The Vaccination Service calls the Citizen Service to get registered users. The UI waits until both respond.

#### **B. Asynchronous Communication**

* **Mechanism:** The caller sends a message and proceeds without waiting for a response (Non-blocking).  
* **Protocol:** Message Queues (RabbitMQ, ActiveMQ, Kafka).  
* **Use Case:** Background tasks where immediate feedback isn't critical (e.g., Logging, Sending Emails, Notifications).  
* **Example:** A service pushes a log message to a queue (Topic). A separate Logging Service picks it up later to save it. The main service doesn't wait for the save confirmation.

### **4\. Monolithic vs. SOA vs. Microservices**

Understanding the distinction is critical for interviews.

* **Monolithic Architecture:**  
  * Single unit where all components are tightly packed.  
  * High coupling, low cohesion.  
  * Hard to scale individual components.  
* **SOA (Service Oriented Architecture):**  
  * **Key Philosophy:** "Share as much as possible."  
  * Focuses on **reusability**. Common functionality (e.g., a Google Maps API wrapper) is built once and shared across multiple services.  
  * Services are often coarser-grained and share databases or logic.  
* **Microservices Architecture:**  
  * **Key Philosophy:** "Share as little as possible."  
  * Focuses on **decoupling**.  
  * Duplication is acceptable if it preserves independence.  
  * **Example:** If both Citizen Service and Vaccination Service need location logic, in Microservices, they might have their own independent implementations or dedicated libraries rather than a shared centralized service that creates a dependency.

### **5\. Summary Checklist**

* **API Gateway** is the entry point; **Eureka** is the address book.  
* **Synchronous** \= REST (User waits); **Asynchronous** \= Kafka/RabbitMQ (User doesn't wait).  
* **SOA** optimizes for reuse (sharing); **Microservices** optimize for independence (decoupling).