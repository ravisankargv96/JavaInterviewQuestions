These detailed notes cover the core concepts of distributed transaction management, focusing on the Two-Phase Commit (2PC) protocol as explained in the "Code Decode" video.

---

# **Mastering Two-Phase Commit (2PC) & Distributed Transactions**

## **1. What is a Transaction?**
A transaction is a series of actions that must be executed as a single unit of work. 
*   **All-or-Nothing:** If even one operation within the sequence fails, the entire transaction must be rolled back to its previous state.
*   **E-commerce Example:** When placing an order, the system must:
    1.  Reduce inventory.
    2.  Process payment.
    3.  Mark the order as successful.
    If payment fails, the inventory must be restored.

### **The ACID Properties**
Every transaction, whether in a monolith or microservice, must follow ACID properties:
*   **Atomicity:** The transaction happens completely or not at all. (e.g., If payment fails, inventory reduction is undone).
*   **Consistency:** The system moves from one valid state to another. (e.g., Inventory cannot drop to a negative value).
*   **Isolation:** Concurrent transactions should not interfere with each other. Customer A's order process should not be visible to Customer B until completed.
*   **Durability:** Once a transaction is committed, the data is permanent (persisted in a database/hard disk) and survives system crashes.

---

## **2. Monolithic vs. Microservices Transactions**
*   **Monolithic Architecture:** All operations happen within a single database. Managing transactions is simple using tools like Spring’s `@Transactional` annotation.
*   **Microservices Architecture:** Follows the **Single Responsibility Principle**. Each service (Order, Inventory, Payment) has its **own database**. 
    *   Because data is spread across multiple databases, transactions become **Distributed Transactions**.
    *   Maintaining ACID properties across multiple services is complex and difficult.

---

## **3. Managing Distributed Transactions**
There are two primary ways to handle distributed transactions:
1.  **Synchronous:** 2-Phase Commit (2PC) and 3-Phase Commit (3PC).
2.  **Asynchronous:** Saga Design Pattern (Event-driven).

---

## **4. The Two-Phase Commit (2PC) Protocol**
2PC is a synchronous protocol used to ensure all participants in a distributed system either commit or rollback. It involves a **Transaction Coordinator** (usually the service that started the process) and **Participants** (other microservices).

### **Phase 1: Prepare Phase (Voting Phase)**
1.  The Coordinator (e.g., Order Service) sends a "Prepare" message to all participants (Inventory, Payment).
2.  Each participant checks if it can fulfill its part (e.g., "Is there enough stock?" or "Are payment details valid?").
3.  Participants perform the work locally but **do not commit** to the database yet.
4.  Each participant votes:
    *   **Yes:** Ready to commit.
    *   **No:** Cannot proceed (leads to a rollback).

### **Phase 2: Commit / Rollback Phase (Decision Phase)**
1.  **If all participants voted "Yes":** The Coordinator sends a "Commit" message. Participants then finalize the changes in their databases and send an "Acknowledgement" (ACK).
2.  **If any participant voted "No" (or timed out):** The Coordinator sends an "Abort" or "Rollback" message. All participants undo their local changes.
3.  **Completion:** Once the Coordinator receives ACKs from everyone, it notifies the client that the order was successful.

---

## **5. Drawbacks of 2PC**
While 2PC ensures consistency, it has several significant disadvantages:
*   **Coordination Overhead:** Multiple rounds of communication between the coordinator and participants reduce performance and scalability.
*   **Blocking Nature:** It is a synchronous process. If one service is slow, all other services are blocked and must wait, leading to performance bottlenecks.
*   **Single Point of Failure:** If the **Coordinator fails** after participants have voted "Yes" but before sending the "Commit" command, all participants are left in a state of uncertainty, holding locks on resources indefinitely.
*   **Scalability Issues:** As the number of microservices increases, the probability of failure and the time spent waiting for ACKs increases.

---

## **6. Failure Scenarios in 2PC**
*   **Participant Failure (Prepare Phase):** If a participant crashes or fails to respond, the coordinator assumes a "No" vote and rolls back everything.
*   **Participant Failure (Commit Phase):** If a participant fails during the actual commit, the coordinator won't receive an ACK, leading to potential inconsistency or retries.
*   **Coordinator Failure:** If the coordinator dies, participants don't know whether to commit or rollback. They usually rely on **timeouts**—if they don't hear back within a certain timeframe, they roll back to free resources.
*   **Network Issues:** Delays in the network might cause a participant to time out and rollback, even if the coordinator eventually sends a "Commit" message.

---

## **7. When to Choose 2PC (Synchronous)**
Despite its drawbacks, 2PC is used in specific scenarios:
*   **High Business Criticality:** When the success or failure of a transaction has a massive impact (e.g., moving money between bank accounts).
*   **Strong Consistency Requirements:** When you cannot afford "Eventual Consistency" and need the data to be accurate across all services immediately.
*   **Financial Transactions:** Where data integrity is more important than system speed or availability.

---

## **Summary Table**
| Feature | Monolithic | Microservices (2PC) |
| :--- | :--- | :--- |
| **Database** | Single Shared DB | Multiple Distributed DBs |
| **Complexity** | Low | High |
| **Performance** | High | Low (due to blocking) |
| **Consistency** | Strong ACID | Strong ACID (but risky) |
| **Pattern** | `@Transactional` | 2PC Protocol |