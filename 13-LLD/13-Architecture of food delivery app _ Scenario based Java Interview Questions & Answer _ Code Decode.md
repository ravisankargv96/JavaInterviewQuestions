### Architecture of a Food Delivery App (System Design & Java Interview Notes)

These notes cover the architectural design, microservices, database choices, and scenario-based interview questions discussed in the video regarding platforms like Swiggy, Zomato, or UberEats.

---

### 1. High-Level Architecture Overview
A food delivery app is built using a **Microservices Architecture** to ensure scalability, fault tolerance, and independent deployment of features.

#### Key Actors in the System:
*   **Customer:** Browses restaurants, places orders, and tracks deliveries.
*   **Restaurant:** Manages menus, prices, and accepts/prepares orders.
*   **Delivery Partner (Valet):** Receives delivery requests and updates order status.
*   **Admin:** Monitors the entire ecosystem, handles disputes, and manages commissions.

---

### 2. Core Microservices Breakdown

*   **User Service:** Manages customer profiles, authentication (JWT/OAuth2), and addresses.
*   **Restaurant/Catalog Service:** Manages restaurant metadata, menus, food items, images, and availability. Uses **Elasticsearch** for fast searching and filtering.
*   **Order Service:** The heart of the system. Manages the order lifecycle (Placed, Confirmed, Cooking, Out for Delivery, Delivered).
*   **Payment Service:** Integrates with third-party gateways (Stripe, Razorpay). It handles transactions and refunds.
*   **Notification Service:** Sends Push, SMS, and Email alerts. Usually powered by **Apache Kafka** for asynchronous processing.
*   **Delivery/Logistics Service:** Finds the nearest available delivery partner using geospatial data (Google Maps API / Redis Geo).
*   **Review/Rating Service:** Handles feedback for both restaurants and delivery partners.

---

### 3. Database Design Strategy

The system uses a **Polyglot Persistence** approach (choosing the right DB for the right task):

*   **Relational Database (PostgreSQL/MySQL):** Used for **Order Service** and **User Service** because these require high **ACID compliance** (e.g., ensuring a payment is linked to exactly one order).
*   **NoSQL Database (MongoDB):** Used for **Restaurant Catalog** and **Reviews**. Since menus are hierarchical and non-uniform across restaurants, a document store is more flexible.
*   **Redis (In-Memory Cache):** Used for **Live Location Tracking** (storing lat/long of drivers) and caching frequently viewed restaurant menus to reduce DB load.
*   **Elasticsearch:** Used for the **Search Functionality** (fuzzy search for "Pizza" or "Burger").

---

### 4. Step-by-Step Workflow (The Order Flow)

1.  **Search:** User searches for food. API Gateway routes the request to the Catalog Service (Elasticsearch).
2.  **Order Placement:** User adds items to the cart. Order Service creates an order entry with status "Pending."
3.  **Payment:** Order Service calls Payment Service. Once payment is successful, the status changes to "Paid/Confirmed."
4.  **Restaurant Notification:** Through a WebSocket or Push notification, the Restaurant Service alerts the restaurant.
5.  **Assignment:** Once the restaurant accepts, the Delivery Service looks for the nearest valet using a **Geofencing algorithm**.
6.  **Tracking:** The Delivery Partner's app sends location updates to Redis every few seconds. The Customer app polls this data or receives it via WebSockets.

---

### 5. Technical Challenges & Interview Scenarios

#### Q1: How do you handle a sudden surge in traffic (e.g., Friday night or a Cricket match)?
*   **Answer:** Implement **Horizontal Autoscaling** (adding more instances of microservices). Use a **Load Balancer** to distribute traffic. Introduce **Caching** via Redis to reduce DB hits. Use **Rate Limiting** at the API Gateway to prevent system crashes.

#### Q2: What happens if the Payment Service is down but the user's money is deducted?
*   **Answer:** Use the **Saga Design Pattern**. If the payment fails or the service times out, a compensating transaction is triggered to initiate an automatic refund or roll back the order status to "Failed" in the Order Service.

#### Q3: How do you ensure the nearest delivery partner gets the order?
*   **Answer:** Use **Redis Geo-sharding**. The location of all active drivers is stored in Redis. The system queries for all "Available" drivers within a 5km radius using the `GEORADIUS` command.

#### Q4: How do you handle data consistency across microservices?
*   **Answer:** Since distributed transactions (2PC) are slow, use **Eventual Consistency**. When an order is placed, an event is published to **Kafka**. The Inventory and Notification services consume this event and update their respective states.

#### Q5: How to prevent "Double Booking" or ordering an out-of-stock item?
*   **Answer:** Use **Optimistic Locking** in the database. When a user tries to checkout, the system checks the version/timestamp of the stock. If it has changed since the user added it to the cart, the transaction is rejected.

---

### 6. Key Components for Java Developers

*   **Spring Boot:** To build the individual microservices.
*   **Spring Cloud Gateway:** For routing and security.
*   **Netflix Eureka:** For Service Discovery (allowing services to find each other).
*   **Resilience4j:** For implementing **Circuit Breakers** (if the Payment service is slow, don't let it hang the whole system).
*   **Kafka:** For decoupling services and handling high-volume notifications/logs.
*   **Feign Client:** For synchronous communication between services (e.g., Order Service calling User Service).