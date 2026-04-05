These detailed notes cover the core concepts and scenario-based questions discussed in the "Code Decode" JPA interview guide.

---

### **1. Core Concept: JPA vs. Hibernate**
*   **JPA (Jakarta Persistence API):** A specification or a set of guidelines/interfaces that defines how to manage relational data in Java applications. It is not an implementation itself.
*   **Hibernate:** The most popular implementation (provider) of the JPA specification.
*   **Interview Scenario:** If you change your provider from Hibernate to EclipseLink, your code (using JPA annotations) remains mostly the same, which demonstrates why using the JPA specification is better for portability.

---

### **2. Entity Lifecycle States**
An entity can exist in four primary states. Understanding these is crucial for "dirty checking" scenarios:
1.  **New (Transient):** An object is instantiated using `new`, but it is not yet associated with a Database or a Persistence Context (Session).
2.  **Managed (Persistent):** The entity is associated with a session and has a corresponding record in the database. Any changes made to the object in this state will be automatically synced to the DB.
3.  **Detached:** The session is closed, or `evict()`/`clear()` is called. The object still has an ID, but changes are no longer tracked.
4.  **Removed:** The entity is scheduled for deletion from the database.

---

### **3. Dirty Checking in JPA**
*   **Scenario:** You retrieve a User entity, change the username using `user.setName("New Name")`, but you never call `repo.save()`. Does the database update?
*   **Answer:** **Yes**, if this happens within a `@Transactional` block.
*   **Mechanism:** At the end of a transaction, JPA performs "Dirty Checking." It compares the current state of the entity with the snapshot taken when the entity was first loaded. If a change is detected, it automatically triggers an SQL UPDATE statement.

---

### **4. First-Level (L1) vs. Second-Level (L2) Caching**
*   **L1 Cache (Mandatory):**
    *   Scoped to the **Session/EntityManager**.
    *   It is enabled by default and cannot be disabled.
    *   If you request the same object twice in one transaction, JPA fetches it from the L1 cache instead of the DB.
*   **L2 Cache (Optional):**
    *   Scoped to the **Session Factory**.
    *   Available across different sessions/transactions.
    *   Requires third-party providers like Ehcache or Hazelcast.

---

### **5. Fetching Strategies: Lazy vs. Eager**
*   **Eager Fetching:** Child entities are loaded immediately along with the parent. (Default for `@ManyToOne` and `@OneToOne`).
*   **Lazy Fetching:** Child entities are loaded only when they are explicitly accessed (e.g., calling `parent.getChildren()`). (Default for `@OneToMany` and `@ManyToMany`).
*   **Interview Scenario:** What is a `LazyInitializationException`?
    *   This occurs when you try to access a lazily-loaded collection after the Hibernate Session/Transaction has already been closed.

---

### **6. The N+1 Select Problem**
This is a common performance issue in JPA.
*   **The Problem:** You fetch $N$ parent records. If the relationship is Eager or you loop through children in a Lazy setup, JPA executes 1 query to get the parents and then $N$ individual queries to get the children for each parent.
*   **The Solution:**
    1.  **Join Fetch:** Use `JOIN FETCH` in your JPQL query to get everything in one single SQL Join.
    2.  **Entity Graph:** Use `@EntityGraph` to define which attributes should be fetched eagerly for a specific query.
    3.  **Batch Size:** Use `@BatchSize` to fetch children in chunks (e.g., 10 at a time) rather than one by one.

---

### **7. Cascading Operations**
Cascading allows you to propagate state transitions from a Parent entity to its Children.
*   **CascadeType.PERSIST:** Saving a parent saves the children.
*   **CascadeType.REMOVE:** Deleting a parent deletes the children (Be careful with this!).
*   **CascadeType.ALL:** Includes Persist, Merge, Remove, Refresh, and Detach.
*   **Orphan Removal:** If `orphanRemoval = true`, removing a child from the parent's collection list will physically delete that child record from the database.

---

### **8. JPQL vs. Native Queries**
*   **JPQL (Java Persistence Query Language):** Focuses on Entities and their persistent fields rather than DB tables. It is database-independent.
*   **Native Query:** Standard SQL queries.
*   **When to use Native?** When you need to use database-specific features (like JSONB in PostgreSQL or specific performance hints) that are not supported by the JPA abstraction.

---

### **9. Difference between `save()`, `persist()`, and `merge()`**
*   **persist():** Returns `void`. It makes a transient instance persistent. It doesn't guarantee the identifier is assigned immediately unless the flush occurs.
*   **save():** (Hibernate specific) Returns the generated identifier. It ensures the ID is generated immediately.
*   **merge():** Used to copy the state of a detached entity onto a managed entity.

---

### **10. Key Annotations to Remember**
*   **@Entity:** Marks the class as a JPA entity.
*   **@Table:** Maps the entity to a specific DB table.
*   **@Id / @GeneratedValue:** Defines the Primary Key and its generation strategy (Identity, Sequence, Table, Auto).
*   **@Column:** Maps a field to a specific DB column name or defines constraints (nullable, length).
*   **@Transient:** Tells JPA to ignore this field; it won't be saved to the database.
*   **@Modifying:** Required for `@Query` updates or deletes to tell Spring Data JPA that the operation changes data.