The following are detailed notes based on the "3-Phase Commit in Distributed Transactions" video by Code Decode.

### **1\. Introduction and Context**

* **Background:** The video follows up on the concept of Two-Phase Commit (2PC) and introduces Three-Phase Commit (3PC) as an enhancement.  
* **Goal:** To solve specific blocking issues found in the 2PC protocol when the Coordinator fails.

### **2\. The Problem with 2-Phase Commit (2PC)**

* **Scenario:** An Order Service (Coordinator) coordinates with Payment and Inventory Services (Participants).  
* **The Failure Case (Blocking Issue):**  
  * In 2PC, if the Coordinator fails **during the Commit Phase** (after everyone voted "Yes" in the Voting Phase), the Participants are left in a "blocked" state.  
  * **State of Participants:**  
    * Payment Service: "I have the money ready, I voted Yes, but I don't know if I should commit or rollback because the Coordinator died."  
    * Inventory Service: "I have the stock locked, I voted Yes, but I am waiting for the final command."  
  * **Consequence:** Resources remain locked until the Coordinator recovers or a timeout occurs (which defaults to rollback), causing performance bottlenecks and potential inconsistency if one received the commit before the crash and others didn't.

### **3\. What is 3-Phase Commit (3PC)?**

* **Definition:** A distributed transaction protocol that adds an extra phase to the standard 2PC to reduce blocking.  
* **Key Innovation:** It splits the **Commit Phase** of 2PC into two sub-phases: **Pre-Commit** and **Do-Commit**.  
* **Safety Net:** If the Coordinator fails during the Pre-Commit phase, participants can safely assume the transaction was aborted and roll back without waiting indefinitely.

### **4\. The Three Phases of 3PC**

#### **Phase 1: CanCommit (Voting Phase)**

* Similar to the "Prepare" phase in 2PC.  
* **Action:** The Coordinator asks all participants, "Can you commit?"  
* **Response:** Participants check resources (e.g., Is stock available? Is payment valid?) and vote **Yes** or **No**.  
* **Outcome:** If anyone votes **No**, the transaction aborts immediately.

#### **Phase 2: PreCommit (The New Phase)**

* **Action:** If everyone voted **Yes** in Phase 1, the Coordinator sends a "PreCommit" message.  
* **Purpose:** This tells participants, "Everyone is ready. Prepare to commit, but don't write to the database yet."  
* **Participant Action:** Participants acknowledge this message.  
* **Benefit:** If the Coordinator dies *before* sending this, participants know that not everyone might have been ready, so they can safely time out and abort. If the Coordinator dies *after* this, participants know that everyone *was* ready to commit.

#### **Phase 3: DoCommit (Final Phase)**

* **Action:** Once the Coordinator receives acknowledgments for the PreCommit, it sends the "DoCommit" message.  
* **Participant Action:** Participants finally persist changes to the database (write to disk) and release locks.  
* **Coordinator Action:** Sends a success response to the client.

### **5\. Recovery and Failure Handling**

* **Coordinator Failure in Phase 2 (Commit Phase):**  
  * A new Coordinator takes over.  
  * It queries participants: "Did anyone receive a PreCommit or DoCommit message?"  
  * **Scenario A:** If *any* participant received a "DoCommit" or is in the commit phase \-\> The new Coordinator tells everyone to **Commit** (because the previous Coordinator must have made that decision).  
  * **Scenario B:** If *no* participant received a "PreCommit" \-\> The new Coordinator tells everyone to **Abort/Rollback** (because the previous Coordinator failed before confirming everyone was ready).  
* **Fail-Stop vs. Fail-Noisy:** 3PC assumes a **Fail-Noisy** model (failed nodes eventually recover). It struggles with **Fail-Stop** models (nodes crash and never return), where it might wait indefinitely or require complex timeout logic.

### **6\. Drawbacks of 3PC**

* **Increased Latency:**  
  * It adds an extra round of communication (Round Trip Time).  
  * Instead of 2 steps (Vote \-\> Commit), it has 3 (Vote \-\> PreCommit \-\> Commit).  
  * This significantly slows down the transaction, especially if services are geographically distributed.  
* **Complexity:** Implementing the extra phase and the recovery logic for a new Coordinator is much more complex than 2PC.  
* **Still Not Perfect:**  
  * Network partitioning can still cause issues (split-brain scenarios).  
  * It is still a **synchronous** (blocking) protocol. If the slowest service takes 10 seconds, the whole transaction waits 10 seconds.  
* **Resource Locking:** Resources are locked for a longer duration due to the extra phase.

### **7\. Comparison: 2PC vs. 3PC vs. Saga**

| Feature | 2-Phase Commit (2PC) | 3-Phase Commit (3PC) | Saga Pattern |
| :---- | :---- | :---- | :---- |
| **Consistency** | Strong (ACID) | Strong (ACID) | Eventual (BASE) |
| **Complexity** | Medium | High | High (Logic complexity) |
| **Performance** | Slow (Blocking) | Slower (More Blocking) | Fast (Non-blocking) |
| **Blocking?** | Yes (Critical if Coord dies) | Reduced (But still synchronous) | No (Asynchronous) |
| **Use Case** | Financial Transactions (Banks) | High-stakes distributed systems | E-commerce (Orders/Emails) |

### **8\. Conclusion**

* **When to use 3PC/2PC:** Only when **Strong Consistency** (ACID) is non-negotiable (e.g., banking transfers where money must not disappear).  
* **When to avoid:** For most modern microservices (like e-commerce), **Saga Pattern** is preferred because it prioritizes availability and performance over immediate consistency.  
* **Final Verdict:** 3PC fixes the blocking issue of 2PC but introduces too much latency and complexity, making it rarely used in practice compared to Saga or standard 2PC.