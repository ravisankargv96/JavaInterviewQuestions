The following are detailed notes based on the "Saga Design Pattern" video by Code Decode.

### **1\. The Problem: Distributed Transactions**

* **Monolithic Approach:** In a monolith, all components (Order, Payment, Delivery) share a single database. Transactions are **Atomic** (ACID). If one step fails (e.g., delivery), the entire transaction rolls back easily because it's a single database connection.  
* **Microservices Challenge:** In microservices, each service (Order Service, Payment Service, Delivery Service) has its own database.  
  * A transaction spans multiple services and databases.  
  * **Scenario:** A user places an order (Order Service succeeds), pays money (Payment Service succeeds), but the delivery partner assignment fails (Delivery Service fails).  
  * **Issue:** Since the Payment Service transaction is already committed in its own database, a simple database rollback in the Delivery Service cannot revert the payment. This leads to data inconsistency (money lost, no food).

### **2\. The Solution: Saga Design Pattern**

* **Definition:** A Saga is a sequence of local transactions. Each local transaction updates the database and publishes an event or message to trigger the next local transaction in the saga.  
* **Mechanism:**  
  * **Local Transaction:** The unit of work within a single microservice (e.g., "Create Order").  
  * **Event/Message:** The trigger that moves the workflow to the next microservice (e.g., "Order Created" triggers "Make Payment").  
* **Handling Failures (Compensating Transactions):**  
  * Since you cannot "rollback" a committed transaction in another service, you must **compensate** for it.  
  * **Compensating Transaction:** A transaction that effectively reverses the effect of a previous transaction.  
  * **Example Flow:**  
    1. **Order Service:** Creates Order \-\> Success.  
    2. **Payment Service:** Deducts Money \-\> Success.  
    3. **Delivery Service:** Assigns Driver \-\> **Failed**.  
    4. **Failure Handling:** Delivery Service triggers a "Delivery Failed" event.  
    5. **Compensating Action 1:** Payment Service listens to failure, executes "Refund Payment".  
    6. **Compensating Action 2:** Order Service listens to refund success, executes "Cancel Order".

### **3\. Implementation Approaches**

There are two main ways to coordinate sagas:

#### **A. Choreography (Event-Based)**

* **Concept:** Decentralized coordination. Services exchange events without a central controller.  
* **Workflow:**  
  * Order Service does its work and emits an event to a Message Broker (RabbitMQ/Kafka).  
  * Payment Service subscribes to that event, does its work, and emits another event.  
  * Delivery Service subscribes to the payment event, does its work, and emits a completion event.  
* **Pros:**  
  * **Simple:** Easy to implement for small workflows.  
  * **Loose Coupling:** Services don't need to know about each other, only about events.  
  * **No Single Point of Failure:** Responsibilities are distributed.  
* **Cons:**  
  * **Confusion:** Hard to track which service listens to what as the system grows.  
  * **Cyclic Dependency:** Risk of services waiting on each other's events endlessly.  
  * **Testing Difficulty:** Hard to simulate and test the entire flow integration.

#### **B. Orchestration (Command-Based)**

* **Concept:** Centralized coordination. A central "Orchestrator" (often a separate service or component) tells participants what to do.  
* **Workflow:**  
  * The **Orchestrator** sends a command to the Order Service.  
  * Order Service replies back to the Orchestrator upon completion.  
  * Orchestrator then sends a command to the Payment Service.  
  * If a step fails, the Orchestrator is responsible for sending "Compensate" commands to previous services to unwind the transaction.  
* **Pros:**  
  * **Central Control:** Easy to track the state of the workflow.  
  * **No Cyclic Dependencies:** The flow is linear and managed by the Orchestrator.  
  * **Clear Separation:** Business logic is in services; coordination logic is in the Orchestrator.  
* **Cons:**  
  * **Single Point of Failure:** If the Orchestrator goes down, the entire process stops.  
  * **Complexity:** Requires implementing and maintaining the Orchestrator logic.

### **4\. Summary Checklist for Interviews**

* **Why Saga?** To maintain data consistency across distributed transactions without using 2PC (Two-Phase Commit).  
* **Core Concept:** Sequence of local transactions \+ Compensating transactions for rollback.  
* **Choreography:** Decentralized, event-driven (good for simple flows).  
* **Orchestration:** Centralized, command-driven (good for complex flows).  
* **Rollback vs. Compensation:** You cannot rollback a committed DB transaction; you must write a new transaction (compensation) to reverse the effect (e.g., Credit vs. Debit).