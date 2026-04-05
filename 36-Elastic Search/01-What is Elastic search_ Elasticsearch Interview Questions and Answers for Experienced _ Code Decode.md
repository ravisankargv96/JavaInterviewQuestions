These detailed notes are based on the "What is Elasticsearch? Elasticsearch Interview Questions and Answers" video by Code Decode.

---

# **Elasticsearch & ELK Stack: Comprehensive Interview Notes**

## **1. What is Elasticsearch?**
Elasticsearch is an **open-source, NoSQL database** and a powerful search engine.
*   **Base Technology:** It is built on **Apache Lucene** and written in **Java**.
*   **Data Structure:** It uses an **Inverted Index** data structure, which is optimized for very fast full-text searches.
*   **Communication:** It provides a **RESTful API** interface, allowing users to interact with it using standard HTTP methods (GET, POST, PUT, DELETE).
*   **Primary Use Case:** It is primarily used for log analytics, real-time application monitoring, and providing fast, flexible search results (like autocomplete features).

## **2. Why Use Elasticsearch Instead of a Traditional Database?**
While relational databases (SQL) and other NoSQL databases (MongoDB) exist, they are often slow when performing complex search operations.
*   **Speed:** Elasticsearch is designed for "Near Real-Time" (NRT) search. 
*   **Full-Text Search:** It can search through massive amounts of unstructured data quickly.
*   **Better User Experience:** For features like Amazon’s search bar (autocomplete), a normal SQL query (`LIKE %product%`) would be too slow and resource-intensive. Elasticsearch retrieves these results almost instantly.

## **3. Data Hierarchy: SQL vs. Elasticsearch**
To understand the structure of Elasticsearch, it is helpful to compare it to a Relational Database (RDBMS):

| RDBMS (e.g., MySQL) | Elasticsearch |
| :--- | :--- |
| Database | **Index** |
| Table | **Type** (Note: Deprecated in newer versions but useful for concept) |
| Row | **Document** (stored as a JSON object) |
| Column | **Field / Property** |

## **4. Core Components: Cluster vs. Node**
*   **Node:** A single instance of the Elasticsearch server. It is created as soon as the Elasticsearch engine starts.
*   **Cluster:** A collection of one or more nodes that work together. A cluster provides a centralized way to index and search data across all its nodes.

## **5. Sharding and Replication**
These are critical concepts for scaling and reliability:

### **Sharding**
*   **Definition:** Sharding is the process of dividing an **Index** into smaller pieces called **Shards**.
*   **Purpose:** If an index becomes too large for a single node’s hardware, sharding allows you to split the data.
*   **Benefit:** Each shard is a fully functional, independent "index" that can be stored on any node in the cluster. This allows for horizontal scaling.
*   **Default:** Traditionally, Elasticsearch provides 5 shards per index by default.

### **Replica**
*   **Definition:** A replica is a copy of a shard.
*   **Fail-Safe Mechanism:** If a node crashes and a shard is lost, the system can recover the data from the replica.
*   **High Availability:** Replicas should **never** be stored on the same node as the primary shard. If the node fails, you lose both the data and the backup.
*   **Performance:** Replicas also help handle increased search requests, as read operations can be performed on replicas to balance the load.

## **6. The ELK Stack (The Elastic Stack)**
The ELK stack is a group of three open-source products designed to help users take data from any source and in any format, then search, analyze, and visualize that data in real-time.

1.  **E – Elasticsearch:** The NoSQL database used to store and index data/logs.
2.  **L – Logstash:** A data processing pipeline. It collects logs from various sources (servers), transforms/aggregates them, and sends them to a "sink" (usually Elasticsearch).
3.  **K – Kibana:** The visualization tool. Since Elasticsearch has no built-in User Interface (UI), Kibana provides the dashboard to view and analyze the data.

### **ELK Architecture Flow:**
**Server Logs** → **Logstash** (Collect/Transform) → **Elasticsearch** (Store/Index) → **Kibana** (Visualize/Search).

## **7. Querying and Configuration**
*   **Query Language:** Elasticsearch uses **Query DSL** (Domain Specific Language) based on Apache Lucene.
*   **Default Port:** The default port is **9200**.
*   **Changing Settings:** You can modify the port number or cluster name by editing the `elasticsearch.yml` file located in the `/config` folder of your Elasticsearch installation.

## **8. Key Operations (CRUD)**
Elasticsearch supports standard operations on documents:
*   **Index:** Create/store a document.
*   **Fetch (Get):** Retrieve a document.
*   **Update:** Modify an existing document.
*   **Delete:** Remove a document.

## **9. Summary of Advantages**
*   **Distributed & Reliable:** High availability through replication.
*   **Scalable:** Can scale horizontally (adding more nodes) or vertically.
*   **Multi-language Support:** Can be used with various programming languages.
*   **APM:** Useful for Application Performance Management and monitoring.