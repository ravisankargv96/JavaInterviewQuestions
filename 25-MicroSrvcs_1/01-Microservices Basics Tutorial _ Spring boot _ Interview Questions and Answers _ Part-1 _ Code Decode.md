These detailed notes are based on the **"Microservices Basics Tutorial | Part-1"** video by Code Decode. The tutorial uses a real-time scenario of a **Vaccination Center and User Base** (similar to the CoWIN platform) to explain architectural concepts.

---

### **1. Monolithic Architecture**
A monolithic application is built as a single, unified unit. All software components of the application are interconnected and interdependent.

#### **Key Layers of Monolith:**
*   **Client Layer (UI):** Built using technologies like Angular, React, or JSP/Servlets.
*   **Server-Side Application Layer:** Handles the business logic (e.g., Spring Boot or Spring MVC).
*   **Database Layer:** A single database stores all data (e.g., both User data and Vaccination Center data).

#### **Internal Architectural Flow:**
1.  **Presentation Layer:** The website interface (e.g., cowin.gov.in).
2.  **Controller Layer:** Handles REST API calls (fetching slots, registration).
3.  **Business Layer:** Contains the logic (e.g., "Only allow registrations for age > 18").
4.  **Repository Layer:** Acts as an interface between Java logic and the database (POJOs/Entities).

#### **Disadvantages of Monoliths:**
*   **Large Codebase:** Over time, the code becomes massive and difficult to manage.
*   **Technology Lock-in:** It is extremely difficult to adopt new technologies. Changing one part requires rewriting the whole application.
*   **Single Point of Failure:** A single bug in one module (e.g., Vaccination Center search) can crash the entire application, making even the User Registration module unavailable.
*   **Scaling Issues:** You cannot scale a single module; you must scale the entire application, which is resource-intensive.
*   **Deployment Hurdles:** Even a minor update requires a full redeployment of the entire system, causing potential downtime.

---

### **2. Microservices Architecture**
Microservices architecture breaks a large application into a collection of small, independent, standalone applications. Each service serves one specific business requirement.

#### **Real-World Example Breakdown:**
*   **Service A:** User Service (handles user profiles and registration).
*   **Service B:** Vaccination Center Service (handles center details and slot availability).

#### **Key Characteristics:**
*   **Independence:** Services are independent deployable modules.
*   **Communication:** Services communicate via **RESTful Web Services** or **Messaging** (like RabbitMQ or Kafka).
*   **Decoupled Databases:** Each microservice can have its own database to ensure complete independence.

#### **Advantages:**
*   **Independent Deployment & Testing:** You can update the "User Service" without touching or restarting the "Vaccination Service."
*   **Fault Isolation:** If the Vaccination Service crashes, the User Service remains functional.
*   **Technology Flexibility:** Different services can be written in different languages or frameworks (Polyglot architecture).
*   **Scalability:** You can scale only the service that is under heavy load.

---

### **3. Interview Question: How to convert a Monolith to Microservices?**
If asked this in an interview, follow this structured approach:

1.  **Identify Modules:** Identify standalone functionalities that can exist independently (e.g., User vs. Vaccination Center).
2.  **Create Standalone Projects:** Use a framework like **Spring Boot** for rapid development to create separate projects for each module.
3.  **Establish Communication:** Define how they will talk to each other (Rest APIs or Messaging).
4.  **Beyond Basics:** **Crucially**, simply having two REST APIs is *not* a microservice architecture. You must implement:
    *   **Service Discovery (Eureka)**
    *   **Load Balancing**
    *   **API Gateways**
    *   **Cloud Deployment readiness**

---

### **4. Key Components Explained**

#### **A. Eureka Server (Service Discovery)**
In a production or cloud environment, service URLs are not static.
*   **The Problem:** If you have multiple instances of "User Service" (load balancing), they will have different IP addresses and ports (e.g., 8081, 8082). You cannot hardcode these into other services.
*   **The Solution:** Eureka acts as a phonebook. Every microservice registers itself with Eureka using a **Service Name**.
*   **How it works:** Instead of calling `http://localhost:8081/user`, the Vaccination Service asks Eureka: "Where is the 'USER-SERVICE'?" Eureka provides the location of an available, healthy instance.

#### **B. API Gateway**
*   Acts as a single entry point for the UI/Client.
*   It routes requests to the appropriate microservice based on the URL path.

---

### **5. Summary of the Planned Project Structure**
In the next part of the tutorial, the following three services will be created using Spring Boot:
1.  **Vaccination Center Service:** To handle center-related API calls (Add/Delete/Update).
2.  **User Service:** To handle user-related operations.
3.  **Eureka Discovery Service:** To allow the User and Vaccination services to find and communicate with each other dynamically without hardcoded URLs.