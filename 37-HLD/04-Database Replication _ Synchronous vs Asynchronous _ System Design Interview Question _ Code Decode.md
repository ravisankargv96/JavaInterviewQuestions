# Database Replication: Detailed Notes

## 1. Introduction to Database Replication
Database replication is the process of storing multiple copies of the same data across separate nodes or servers. It is a critical component of system design used to ensure high availability and scalability.

### Why Replication is Needed:
*   **Availability:** If a single database instance crashes, the entire application goes down. Replicas ensure the system remains functional even if one node fails.
*   **Performance:** A single server cannot handle unlimited requests. Spreading data across multiple nodes reduces delays (latency) and improves response times for the end user.
*   **Scalability:** Replicas allow the system to handle an increasing number of read/write requests by distributing the load.

---

## 2. Synchronous vs. Asynchronous Replication

When replicating data from a primary node to secondary nodes, there are two main communication patterns:

### A. Synchronous Replication
*   **Mechanism:** The primary node updates its data and sends the update to all secondary nodes. It waits for an **acknowledgment** from every secondary node before telling the client that the write was successful.
*   **Pros:** Data is always in sync. Users will never read "stale" (outdated) data.
*   **Cons:** If even one secondary node fails or experiences a network delay, the primary cannot confirm the write to the client. This leads to high latency and decreased performance.
*   **Best Use Case:** Applications where consistency is non-negotiable (e.g., BookMyShow, where a seat cannot be sold twice).

### B. Asynchronous Replication
*   **Mechanism:** The primary node updates its data, immediately informs the client the write is successful, and sends the update to secondary nodes in the background. It does **not** wait for acknowledgments.
*   **Pros:** Very high performance and low latency. The system remains operational even if secondary nodes are down.
*   **Cons:** There is a risk of **stale data**. A client might write to the primary and then immediately read from a secondary that hasn't received the update yet.
*   **Best Use Case:** Read-heavy applications where slight delays in data updates are acceptable (e.g., a country/state dropdown menu).

---

## 3. Replication Models

### A. Single Leader (Primary-Secondary)
*   One node is designated as the **Primary** (handles all writes).
*   Other nodes are **Secondaries** (handle reads).
*   **Advantage:** Simple to manage; no write conflicts because only one node handles writes.
*   **Disadvantage:** Single point of failure. If the primary node fails, the system cannot process new writes.
*   **Best for:** Read-intensive workloads.

### B. Multi-Leader
*   Multiple nodes act as primaries and can process write requests.
*   Each leader propagates changes to other leaders and their respective secondaries.
*   **Advantage:** Prevents a single point of failure; handles higher write volumes.
*   **Disadvantage:** **Concurrent Write Conflicts.** If two leaders update the same row differently at the same time, the system must resolve which one is correct (often using timestamps).

### C. Leaderless (Peer-to-Peer)
*   Every node is equal and can accept both read and write requests.
*   Popularized by Amazon's **DynamoDB**.
*   **Advantage:** Highly scalable and highly available.
*   **Disadvantage:** Significant complexity in handling data consistency and write conflicts across all nodes.

---

## 4. Real-Time Implementation Methods
How does the data actually move from the Primary to the Secondary?

### A. Statement-Based Replication
*   The primary node sends the actual SQL statements (e.g., `UPDATE employee SET name = 'Decode' WHERE id = 1`) to the secondaries.
*   **Issue:** Non-deterministic functions (like `NOW()` for time or `RAND()` for random numbers) will result in different data on different nodes because the function executes at different times on each server.

### B. Write-Ahead Log (WAL) Shipping
*   In standard databases, every change is first written to a log file (the WAL) before the actual database file is changed.
*   In replication, this log file is sent to secondary nodes. The secondaries apply the logs to their own data.
*   **Advantage:** It captures exactly what changed at the byte level, making it more reliable than statement-based replication.

### C. Logical (Row-Based) Log Replication
*   Instead of sending SQL statements or internal database logs, the actual data of the modified row is sent.
*   **Example:** If a row was updated, the entire new version of that row is copied to the secondary.
*   **Advantage:** Decouples the replication from the storage engine internals, making it easier to replicate between different versions of database software.

---

## 5. Summary Trade-offs
*   **Consistency vs. Performance:** If you need 100% consistency, choose Synchronous. If you need 100% performance, choose Asynchronous.
*   **Read vs. Write Intensive:** Use Single-Leader for read-heavy systems. Use Multi-Leader or Leaderless for write-heavy systems.