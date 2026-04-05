The following are detailed notes based on the "Master 2 Phase Commit" video by Code Decode.

### **1\. Introduction to Distributed Transactions**

* **Definition:** A transaction is a series of actions that must be executed as a single unit. If one action fails, the entire sequence must roll back.  
* **ACID Properties:** All transactions (monolithic or microservices) must adhere to these:  
  * **A \- Atomicity:** All operations happen, or none do. (e.g., Inventory is deducted ONLY if payment succeeds).  
  * **C \- Consistency:** The system moves from one valid state to another. (e.g., Inventory cannot drop below zero/become negative).  
  * **I \- Isolation:** Concurrent transactions do not interfere with each other. (e.g., User A's transaction doesn't see User B's partial changes).  
  * **D \- Durability:** Once committed, changes are permanent (saved to DB/Disk) and survive system crashes.

### **2\. Monolith vs. Microservices Transactions**

* **Monolithic:**  
  * Easy to manage.  
  * Single database.  
  * ACID properties are handled by the database engine (e.g., @Transactional in Spring Boot).  
* **Microservices:**  
  * Difficult to manage.  
  * **Database per Service:** Order Service has Order DB, Inventory Service has Inventory DB, etc.  
  * A single business action (placing an order) spans multiple databases.  
  * Requires **Distributed Transaction Management**.

### **3\. Two-Phase Commit (2PC) Protocol**

2PC is a **synchronous** protocol to handle distributed transactions. It ensures all services either commit or roll back changes together.

#### **Phase 1: Prepare Phase (Voting Phase)**

1. **Coordinator:** The service initiating the transaction (e.g., Order Service) acts as the **Coordinator**.  
2. **Request:** The Coordinator asks all participating services (Inventory, Payment) to "Prepare".  
3. **Validation:** Participants check if they can perform the action:  
   * *Inventory Service:* Checks if stock is available. locks the row to prevent others from taking it.  
   * *Payment Service:* Checks if payment details are valid.  
4. **Vote:** Participants send a vote back to the Coordinator:  
   * **Yes:** "I am ready to commit."  
   * **No:** "I cannot complete this request" (e.g., Insufficient funds, Out of stock).

#### **Phase 2: Commit Phase (Decision Phase)**

1. **Decision:** The Coordinator reviews the votes.  
   * **If ALL vote Yes:** Coordinator sends a **Commit** command.  
   * **If ANY vote No:** Coordinator sends a **Rollback** (Abort) command.  
2. **Action:**  
   * *Commit:* Participants persist changes to their actual database and release locks.  
   * *Rollback:* Participants discard pending changes and revert to the previous state.  
3. **Acknowledgement:** Participants send an acknowledgement to the Coordinator after completing the action.  
4. **Final Response:** The Coordinator responds to the client (User) with success or failure.

### **4\. Drawbacks of 2PC**

* **Blocking/Synchronous:** The Coordinator and all Participants are blocked while waiting for responses. This reduces performance and throughput.  
* **Coordinator Overhead:** High communication overhead due to multiple rounds of messages.  
* **Single Point of Failure:** If the **Coordinator fails** after the Prepare phase but before sending the Commit command, Participants are left in a blocked state (holding locks), unsure whether to commit or rollback.  
* **Latency:** Since it waits for the slowest service to vote, the total time equals the time of the slowest participant.

### **5\. Failure Scenarios in 2PC**

* **Participant Failure (Prepare Phase):** If a participant crashes before voting, the Coordinator assumes "No" (after a timeout) and aborts the transaction.  
* **Participant Failure (Commit Phase):** If a participant crashes after voting "Yes" but before receiving the Commit command, it must check the transaction status upon recovery (usually by querying the Coordinator or other participants).  
* **Coordinator Failure:** If the Coordinator crashes, participants may remain blocked indefinitely (until a timeout triggers a rollback).  
* **Network Issues:** Delays can cause timeouts, leading to unnecessary rollbacks even if the operation was successful.

### **6\. When to use 2PC?**

* **Strict Consistency:** When business logic demands immediate consistency (e.g., Financial transactions, transferring money between banks).  
* **Criticality:** When the cost of a failure or inconsistency is extremely high.  
* **Not Recommended:** for high-performance systems where eventual consistency (Saga Pattern) is acceptable.

### **7\. Course Promotion (Mentioned in Video)**

* The creator has launched a full-stack course using Angular (Frontend) and Spring Boot Microservices (Backend).  
* Tech stack includes: Docker, Kubernetes, AWS (EKS, RDS), Jenkins, ArgoCD, MongoDB, and SonarQube.