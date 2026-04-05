These detailed notes cover the implementation of an API Gateway in a Microservices architecture using Spring Boot, as demonstrated in the "Citizen Vaccination" example.

---

### 1. Introduction to API Gateway
In a microservices architecture, a single application is split into multiple independent services (e.g., Citizen Service, Vaccination Center Service). Without a gateway, a client (like a mobile app or web frontend) would need to know the specific URLs and ports of every single service.

**The API Gateway acts as the "Single Entry Point" for all client requests.**

#### Key Responsibilities:
*   **Routing:** Directing requests to the correct microservice based on the URL path.
*   **Security:** Handling authentication and authorization in one place rather than in every microservice.
*   **Load Balancing:** Distributing traffic across multiple instances of a service.
*   **Cross-cutting concerns:** Logging, Rate Limiting, and Header manipulation.

---

### 2. Problem Statement (The "Before" Scenario)
*   **Citizen Service:** Runs on Port 8081.
*   **Vaccination Center Service:** Runs on Port 8082.
*   **Issue:** The UI/Client has to manage multiple base URLs. If a service moves to a different port or server, the client code must be updated.

---

### 3. Solution: Spring Cloud Gateway
Spring Cloud Gateway is the successor to Netflix Zuul. It is built on Spring Framework 5, Project Reactor, and Spring Boot 2.0, making it non-blocking and highly efficient.

#### Implementation Steps:
1.  **Create a New Spring Boot Project:** This will be the "API Gateway" module.
2.  **Add Dependencies:**
    *   `Gateway` (Spring Cloud Routing).
    *   `Eureka Discovery Client` (To allow the Gateway to find other services by name).
3.  **Main Class Annotation:** Add `@EnableDiscoveryClient` to the main application class.

---

### 4. Configuration (The Core Logic)
The gateway is primarily configured in the `application.yml` file. It uses three main components: **Routes, Predicates, and Filters.**

#### A. Routes
A route is the basic building block of the gateway. It is defined by an ID, a destination URI, and a collection of predicates and filters.

#### B. Predicates
Predicates are "If" conditions. If the condition is met (e.g., the path matches), the request is routed to the specified URI.
*   Example: `Path=/citizen/**` means any request starting with "/citizen/" will be handled by this route.

#### C. Filters
Filters allow you to modify the incoming HTTP request or the outgoing HTTP response.

---

### 5. Sample `application.yml` Configuration
In the video example, the configuration follows this structure:

```yaml
server:
  port: 8080

spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: CITIZEN-SERVICE
          uri: lb://CITIZEN-SERVICE
          predicates:
            - Path=/citizen/**

        - id: VACCINATION-CENTER-SERVICE
          uri: lb://VACCINATION-CENTER-SERVICE
          predicates:
            - Path=/vaccinationcenter/**

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

#### Breakdown of the Config:
*   **`lb://` (Load Balancer):** This prefix tells the Gateway to look up the service name in the Eureka Server rather than using a hardcoded IP address. This enables automatic load balancing.
*   **`Path` Predicate:** If a user hits `http://localhost:8080/citizen/test`, the gateway sees `/citizen/**`, matches it to the Citizen Service route, and forwards the request to the actual Citizen Service instance.

---

### 6. Workflow of the Example
1.  **Start Eureka Server:** The central registry where all services register themselves.
2.  **Start Citizen Service:** Registers itself with Eureka as "CITIZEN-SERVICE".
3.  **Start Vaccination Service:** Registers itself with Eureka as "VACCINATION-CENTER-SERVICE".
4.  **Start API Gateway:** Registers with Eureka and fetches the locations of the other two services.
5.  **Client Request:** The client sends a request to Port 8080 (the Gateway).
6.  **Routing:** The Gateway checks the path, finds the service in Eureka, and redirects the call internally.

---

### 7. Advantages Demonstrated
*   **Port Hiding:** The client only ever interacts with Port 8080. Ports 8081 and 8082 can be blocked from external access by a firewall.
*   **Service Discovery:** Since the gateway uses `lb://`, you can spin up 10 instances of the Citizen Service, and the Gateway will automatically distribute traffic among them without any config changes.
*   **Centralized Logic:** You can add a single "Logging Filter" in the Gateway to log every request entering your microservice ecosystem.

---

### 8. Key Takeaways
*   **Spring Cloud Gateway** is preferred over Zuul for modern Spring Boot applications due to its reactive (non-blocking) nature.
*   **Configuration vs Code:** Most routing logic can be done via YAML properties, though Java-based configuration is also possible.
*   **Integration:** Gateway works best when paired with a Service Registry (Eureka) to avoid hardcoding microservice URLs.