# Detailed Notes: Introduction to System Design (HLD vs. LLD)

## 1. Overview of System Design
System Design is the process of defining the architecture, modules, interfaces, and data for a system to satisfy specified requirements. It is increasingly required in interviews for both product-based and service-based companies, even for junior roles.

*   **Real-world Context:** System design is something developers do daily (e.g., building an e-commerce site or a management system), often without labeling it as "HLD" or "LLD."
*   **The Backbone:** The core of system design is the **Architecture**, which defines the structure, behavior, and view of the system.

---

## 2. Core Concepts: Modules and Components
An architecture is composed of multiple modules, and each module consists of several components.

*   **Modules:** Handle specific tasks (e.g., a "Data Integration" module or a "Web Interaction" module).
*   **Components:** Functional groups of related functions that make a module capable of performing its task.
    *   *Example:* In the Spring Framework, "Data Access" is a module, while ORM, OXM, and Transactions are components within it.
*   **Data Flow:** The exchange of information between classes and interfaces. Managing this flow is vital for handling system growth and high data volumes.

---

## 3. High-Level Design (HLD) vs. Low-Level Design (LLD)

### High-Level Design (HLD)
*   **Responsibility:** Primary task of **Technical Architects**.
*   **Focus:** The "Big Picture."
*   **Key Decisions:**
    *   Overall system structure and interaction between modules.
    *   Selection of technology stacks (Cloud vs. On-premise).
    *   Database selection (Relational vs. NoSQL vs. Graph).
    *   High-level components like Load Balancers, Caching layers, and CDNs.

### Low-Level Design (LLD)
*   **Responsibility:** Handled by **Software Developers** (SD1, SD2, etc.).
*   **Focus:** Deep dive into specific components and implementation details.
*   **Key Deliverables:**
    *   **ER Diagrams:** Entity-relationship modeling.
    *   **UML Diagrams:** Class diagrams, activity diagrams, and state diagrams.
    *   **Logic:** Defining specific attributes, time-to-live (TTL) for cache, and eviction strategies (e.g., Least Frequently Used).
    *   **Machine Coding:** Writing the actual deliverable code.

---

## 4. Why is HLD asked of Junior Developers?
Even with 2-3 years of experience, candidates are expected to know HLD because:
1.  **Terminology Knowledge:** Developers must understand Load Balancers, Caching, and SQL vs. NoSQL to contribute effectively.
2.  **Bottleneck Identification:** While architects design the initial system, developers encounter performance issues in daily work and must modify the design to solve them.

---

## 5. How to Approach a System Design Interview

### Step 1: Clarify Requirements
Before designing, you must narrow down the goals to avoid overcomplicating the system.
*   **Functional Requirements:** What the system *must do* (e.g., "User must be able to add items to a cart").
*   **Non-Functional Requirements:** How the system *performs* (e.g., Scalability, Availability, Security, Reliability).
    *   *Example:* A functional requirement is "loading the cart"; a non-functional requirement is "the cart must load in under 2 seconds."

### Step 2: Decide Trade-offs and Estimations
Perform cost analysis and resource estimation.
*   **Cloud vs. On-premise:** Based on budget and storage needs.
*   **Data Usage:** Frequently accessed data vs. archival data (which can be moved to cheaper storage like AWS S3).

### Step 3: Data Flow and Database Selection
Choose a database based on the specific needs of the data:
*   **Relational (SQL):** Use for fixed schemas.
*   **NoSQL (MongoDB/Cassandra):** Use for flexible schemas or high availability (e.g., Cassandra is preferred for high availability).
*   **Graph Databases:** Use for highly interconnected data.

### Step 4: Iterative Design (The HLD to LLD Flow)
System design is **iterative**. Never try to provide a "perfect" solution immediately.
1.  Start with a basic working model.
2.  Identify bottlenecks (e.g., "This query is too slow").
3.  Optimize (e.g., "Let's add an Executor Service for async calls to reduce response time from 5s to 3s").
4.  Refine based on interviewer feedback.

---

## 6. Key Takeaways for Interviews
*   **No Single Right Answer:** There are thousands of tools. Your design is valid as long as you can justify your choices and meet the requirements.
*   **Avoid "Mugging Up":** Don't just dump a memorized diagram of Twitter or YouTube. Interviewers look for your **approach** and how you handle specific bottlenecks.
*   **Focus on Logic:** System design is language-independent. It’s about conceptual knowledge of tools and techniques available in the market.