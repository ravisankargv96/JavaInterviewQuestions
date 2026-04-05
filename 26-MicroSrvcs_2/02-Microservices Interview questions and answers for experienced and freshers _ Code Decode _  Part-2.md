These detailed notes are based on the **Code Decode** interview preparation video regarding Microservices (Part 2).

---

### **1. Microservices Architecture: Key Components**
A standard microservices architecture consists of several moving parts that work together to ensure a seamless flow of data and security.

*   **Client & Security/Identity Management:**
    *   The client (user) interacts with the system.
    *   Security (Spring Security, Okta, OAuth2) is usually placed between the client and the microservices.
    *   **Best Practice:** Instead of implementing security in every individual microservice (which leads to code duplication), it is preferred to handle security and identity management at the **API Gateway** level.
*   **API Gateway:**
    *   Acts as the single entry point for all client requests.
    *   Handles **Routing:** It directs requests to the appropriate microservice based on the URL path.
    *   Abstracts the internal architecture; the frontend doesn't need to know the specific port or IP of every service.
*   **Service Discovery (e.g., Netflix Eureka):**
    *   In a cloud environment, IP addresses and port numbers are **dynamic** (they change whenever an instance restarts).
    *   **The Problem:** You cannot hardcode URLs in your code.
    *   **The Solution:** All microservices register themselves with the Service Discovery server (Registry). When Service A needs to talk to Service B, it asks Eureka for Service B's current location.
*   **Individual Microservices:**
    *   Small, modular services (e.g., Citizen Service, Vaccination Service) that focus on specific business logic.

---

### **2. Core Features of Microservices**
*   **Independent Development & Deployment:** Services can be built, tested, and deployed without affecting the rest of the system.
*   **Decentralization:** Data management is decentralized. Each service owns its database or specific tables related to its business logic.
*   **Black Box Nature:** Services are "black boxes" to each other. One service doesn't need to know the internal logic or technology stack (Java, Python, etc.) of another; they only care about the API contract.
*   **Decoupled Security:** Security is handled at the platform level (API Gateway), freeing developers from worrying about authentication/authorization within the business logic.
*   **Polyglot Architecture:** You can use different programming languages, different versions of the same language (Java 8 vs. Java 11), or different database types for different services within the same application.

---

### **3. Inter-Service Communication**
How microservices talk to each other is categorized into two main types:

#### **A. Synchronous Communication**
*   **Mechanism:** The client sends a request and **waits** for a response within a specific time limit.
*   **Protocol:** Usually HTTP/HTTPS via **REST APIs**.
*   **Example:** If a user asks the Vaccination Service for a list of registered citizens, the Vaccination Service must call the Citizen Service and wait for the data before it can reply to the user.
*   **Pros/Cons:** Essential for immediate data needs, but the thread is blocked until the response is received.

#### **B. Asynchronous Communication**
*   **Mechanism:** The client (Message Producer) sends a message to a **Message Broker** (on a "Topic" or "Queue") and continues its work without waiting for a response.
*   **Tools:** Apache Kafka, RabbitMQ, ActiveMQ.
*   **Key Concept:** The thread is **not blocked**. The producer receives an acknowledgment only from the broker (confirming the message was received), not from the final consumer.
*   **Example (Logging):** A service sends log data to a queue. The Logging Service (Consumer) picks it up whenever it's ready. The main application doesn't stop working just because the logs haven't been written yet.

---

### **4. Monolithic vs. SOA vs. Microservices**

#### **Monolithic Architecture**
*   All components are bundled into a single unit/container.
*   **Drawback:** High coupling, low cohesion. If one small part fails or needs an update, the entire application must be redeployed.

#### **Service-Oriented Architecture (SOA)**
*   A collection of services that communicate with each other.
*   **Key Focus:** **Reusability and Sharing.**
*   **Example:** Multiple services might use a single, shared "Google Maps API Wrapper" service to get location data. They share logic and data to reduce redundancy.
*   **Note:** Many people confuse SOA with Microservices. If your services are designed primarily to share and reuse as much data/code as possible, you are likely using SOA.

#### **Microservices Architecture**
*   A collection of small, autonomous services modeled around business domains.
*   **Key Focus:** **Independence and Isolation.**
*   **Difference from SOA:** While SOA encourages sharing/reusing, Microservices focus on sharing as little as possible. Each service is self-contained with its own logic and database.

---

### **5. Interview Tip: The "SOA Trap"**
If an interviewer asks how you implemented microservices and you simply say, *"I have Service A and Service B talking via REST,"* they may challenge you. Simply using REST doesn't make it a Microservice; it could be SOA. 

To answer correctly, emphasize:
1.  **Loose Coupling:** Services don't share data/databases unnecessarily.
2.  **High Cohesion:** Each service does one thing very well.
3.  **Independence:** Services are autonomous and can be updated without impacting others.