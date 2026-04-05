Here are detailed notes on **Microservices API Gateway Implementation in Spring Boot (Part 2\)**, covering the practical implementation using **Spring Cloud Gateway**.

### **Overview**

* **Goal:** Implement an API Gateway to act as a single entry point for multiple microservices (Vaccination Center Service & Citizen Service).  
* **Technology:** **Spring Cloud Gateway** (since Netflix Zuul is in maintenance mode).  
* **Scenario:**  
  * **Citizen Service:** Running on port 8081\.  
  * **Vaccination Center Service:** Running on port 8082\.  
  * **Eureka Server:** Running on port 8761\.  
  * **API Gateway (To be built):** Will run on port 8083\.  
* **Flow:** The client (e.g., Postman/Browser) will hit localhost:8083 (API Gateway), and based on the path (e.g., /citizen/... or /vaccinationcenter/...), the request will be routed to the respective microservice dynamically using Eureka Service Discovery.

### **Step-by-Step Implementation**

#### **1\. Project Setup**

1. **Create a Spring Boot Project:**  
   * Use Spring Initializr or your IDE.  
   * **Name:** API-GATEWAY.  
   * **Dependencies:**  
     * **Gateway** (spring-cloud-starter-gateway): Provides the core Spring Cloud Gateway functionality.  
     * **Eureka Discovery Client** (spring-cloud-starter-netflix-eureka-client): Allows the Gateway to register with Eureka and discover other services.  
     * **Lombok** (Optional): For cleaner code.  
2. **Verify Main Class:**  
   * Ensure @EnableEurekaClient annotation is added to the main class to enable service discovery.

#### **2\. Configuration (application.yml)**

This is the most critical part where routing logic is defined.

1. **Server Port & Application Name:**  
   YAML  
   server:  
     port: 8083  \# API Gateway runs on this port

   spring:  
     application:  
       name: API-GATEWAY

2. **Routing Configuration (spring.cloud.gateway.routes):**  
   * The routing rules consist of three main components: **ID**, **URI**, and **Predicates**.  
   * **Route 1: Citizen Service**  
     YAML  
     spring:  
       cloud:  
         gateway:  
           routes:  
             \- id: CITIZEN-SERVICE          \# Unique identifier for the route  
               uri: lb://CITIZEN-SERVICE    \# Destination URI. 'lb://' enables Load Balancing via Eureka  
               predicates:  
                 \- Path=/citizen/\*\* \# Matches any request starting with /citizen/

   * **Route 2: Vaccination Center Service**  
     YAML  
             \- id: VACCINATION-CENTER-SERVICE  
               uri: lb://VACCINATION-CENTER-SERVICE  
               predicates:  
                 \- Path=/vaccinationcenter/\*\* \# Matches any request starting with /vaccinationcenter/

#### **3\. Key Concepts Explained**

* **ID (id):** A unique name for the route (e.g., CITIZEN-SERVICE).  
* **URI (uri):**  
  * Instead of hardcoding URLs like http://localhost:8081, we use lb://SERVICE-NAME.  
  * **lb://**: Stands for **Load Balancer**. It tells the Gateway to look up the service in the Eureka Registry by its name (CITIZEN-SERVICE) and load balance requests if multiple instances exist.  
* **Predicates (predicates):**  
  * Conditions that must be met for a request to match a route.  
  * **Path=/citizen/\*\***: This specific predicate checks the URL path. If the incoming request path matches this pattern, the route is triggered.  
  * Effectively, it works like an if condition: "If URL path starts with /citizen/, route to CITIZEN-SERVICE".

#### **4\. Execution & Testing**

1. **Start Infrastructure:**  
   * Start **Eureka Server** (8761).  
   * Start **Citizen Service** (8081).  
   * Start **Vaccination Center Service** (8082).  
   * Start **API Gateway** (8083).  
2. **Verify Registration:**  
   * Open Eureka Dashboard (http://localhost:8761).  
   * You should see API-GATEWAY, CITIZEN-SERVICE, and VACCINATION-CENTER-SERVICE listed.  
3. **Test Routing (via Postman):**  
   * **Before API Gateway:**  
     * Call Citizen Service directly: GET http://localhost:8081/citizen/id/1 \-\> Success.  
   * **Using API Gateway:**  
     * Call via Gateway Port (8083): GET http://localhost:8083/citizen/id/1 \-\> Success.  
     * The Gateway receives the request at port 8083\.  
     * It matches the path /citizen/\*\*.  
     * It forwards the request to CITIZEN-SERVICE (which resolves to 8081 internally).  
     * The response is returned to the client.  
   * **Test Vaccination Route:**  
     * GET http://localhost:8083/vaccinationcenter/id/1 \-\> Success.  
     * Matches /vaccinationcenter/\*\* and routes to VACCINATION-CENTER-SERVICE.

### **Summary of Benefits**

* **Single Entry Point:** Clients only need to know the Gateway URL (localhost:8083). They don't need to track individual microservice ports (8081, 8082).  
* **Dynamic Routing:** Uses Eureka for service discovery, so if backend ports change, the Gateway adapts automatically without configuration changes.  
* **Abstraction:** Hides the internal microservices architecture from the external client.