These notes provide a comprehensive guide to choosing the right database for system design, based on the "Code Decode" video.

---

# **How to Choose the Right Database: System Design Notes**

Selecting a database is a critical decision in system design interviews. The choice depends on performance requirements, data structure, and the specific use case of the application.

---

## **1. Relational (SQL) vs. Non-Relational (NoSQL)**

The first major decision is choosing between SQL (e.g., MySQL, Oracle) and NoSQL (e.g., MongoDB, Cassandra). Five key criteria determine this:

### **A. Read-Intensive vs. Write-Intensive**
*   **Read-Intensive:** If your application reads data frequently but updates it rarely (e.g., a student profile), **NoSQL** is better. It avoids complex joins and views, increasing read speed.
*   **Write-Intensive:** If the application requires frequent updates and high data integrity, **Relational Databases** are often preferred due to their structured nature and transaction handling.

### **B. Structured vs. Unstructured Data**
*   **Structured (SQL):** Use when data follows a strict schema (e.g., ID, Name, Age). Adding new columns in SQL is a hectic process.
*   **Unstructured (NoSQL):** Use when data fields vary (e.g., some records have an address, some have a mobile number, some have neither).

### **C. ACID Properties & Concurrency**
*   **SQL:** Follows ACID (Atomicity, Consistency, Isolation, Durability) properties. Essential for applications requiring high consistency, like **BookMyShow** or **Hotel Booking**, where multiple users shouldn't book the same seat simultaneously.
*   **NoSQL:** Generally prioritizes speed and scalability over strict ACID consistency.

### **D. Cost**
*   **SQL:** Often requires paid licenses (e.g., Oracle) and can be expensive.
*   **NoSQL:** Mostly open-source and freely available (e.g., MongoDB, Redis).

### **E. Redundancy Tolerance**
*   **SQL:** Minimizes redundancy using foreign keys and normalization.
*   **NoSQL:** High redundancy (data is often duplicated across documents to avoid joins), but this allows for faster retrieval as all data for an entity is in one place.

---

## **2. Types of NoSQL Databases**

There are four primary subtypes of NoSQL databases. Choosing the right one depends on how you need to access the data.

### **I. Key-Value Store**
*   **Concept:** The simplest form of NoSQL. It works like a Hash Map (Key $\rightarrow$ Value).
*   **Characteristics:** Fastest performance ($O(1)$ time complexity). Everything is fetched using a unique key.
*   **Pros:** Extreme speed for simple operations.
*   **Cons:** You **cannot** filter based on the value. You must fetch the whole value object to see what’s inside.
*   **Use Cases:** 
    *   **Session Management:** Storing user login sessions.
    *   **Caching:** Temporary data storage (Redis).
    *   **Shopping Carts:** Fast processing of high-volume cart changes.
*   **Examples:** Redis, Amazon DynamoDB.

### **II. Document Database**
*   **Concept:** Stores data in open formats like JSON, XML, or BSON.
*   **Characteristics:** Similar to Key-Value but allows for hierarchical data and **partial updates/filtering** on values.
*   **Use Cases:**
    *   **E-commerce Product Catalogs:** Different products have different attributes (e.g., clothing has size/color, while electronics have voltage/warranty).
*   **Examples:** MongoDB, Cosmos DB.

### **III. Graph Database**
*   **Concept:** Based on Graph Theory (Nodes and Edges). Nodes represent entities (people/objects), and Edges represent relationships.
*   **Characteristics:** Excellent for visualizing and querying complex relationships.
*   **Use Cases:**
    *   **Social Networks:** Facebook/Instagram friend connections and "married to" or "friend of" links.
    *   **Recommendation Engines:** Suggesting products based on user behavior and similar interests.
    *   **Logistics:** Tracking complex routes and entity connections.
*   **Examples:** Neo4j, InfiniteGraph.

### **IV. Columnar Database (Column Family)**
*   **Concept:** Unlike SQL (which is row-oriented), data is stored in columns.
*   **Characteristics:** In SQL, to find an average age among 1,000 rows, the system reads every row (ID, Name, etc.). In Columnar DBs, the system only reads the "Age" column, making it much faster for analytics.
*   **Pros:** High performance for specific column queries and aggregations.
*   **Cons:** Slower for writes and inefficient if you need to fetch an entire row (all columns) frequently.
*   **Use Cases:**
    *   **Data Warehousing & BI:** Analytical applications where you calculate averages, sums, or reports across millions of records.
*   **Examples:** Cassandra, HBase.

---

## **3. Summary Selection Guide**

| Scenario | Recommended Database |
| :--- | :--- |
| **High Consistency (Booking/Finance)** | Relational (SQL) |
| **Simple Caching/Sessions** | Key-Value Store (Redis) |
| **Product Catalogs (Variable Attributes)** | Document DB (MongoDB) |
| **Social Networks/Relationships** | Graph DB (Neo4j) |
| **Big Data Analytics/Reporting** | Columnar DB (Cassandra) |

### **Interview Tip:**
When asked why you chose a specific database, don't just say "it's fast." Explain your choice using the criteria above: **read/write intensity, data structure (schema), ACID requirements, and how the data is related.**