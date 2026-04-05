These notes cover the key concepts, technical differences, and practical implementations discussed in the "Hibernate Interview Questions and Answers - Part 2."

---

### 1. Hibernate Object States
One of the most critical concepts in Hibernate is the lifecycle of an entity object. There are four primary states:

*   **Transient State:**
    *   An object is created using the `new` operator but is not yet associated with a Hibernate Session.
    *   It has no representation in the database (no primary key assigned yet).
    *   If the object is garbage collected, the data is lost.
*   **Persistent State:**
    *   The object is associated with an active Session (via `save()`, `persist()`, `update()`, or `load()`).
    *   Hibernate tracks any changes made to this object.
    *   When the transaction is committed, changes are automatically synchronized with the database (Dirty Checking).
*   **Detached State:**
    *   The object was previously persistent, but the Session has been closed or `clear()`/`evict()` was called.
    *   The data still exists in the database, but Hibernate is no longer tracking changes to the object.
    *   It can be re-attached to a new session using `update()` or `merge()`.
*   **Removed State:**
    *   The object is associated with a session, but a `delete()` call has been made.
    *   It is scheduled to be deleted from the database upon transaction commit.

---

### 2. Hibernate Caching Mechanism
Hibernate uses a two-level caching architecture to reduce the number of database hits.

#### First-Level Cache (Session Cache)
*   **Scope:** Associated with the `Session` object.
*   **Nature:** Mandatory/Default. You cannot disable it.
*   **Function:** If you request the same entity multiple times within the same session, Hibernate returns the object from the L1 cache instead of querying the DB.
*   **Life:** It dies when the session is closed.

#### Second-Level Cache (SessionFactory Cache)
*   **Scope:** Associated with the `SessionFactory`.
*   **Nature:** Optional and pluggable (requires external providers like Ehcache, Hazelcast, or Infinispan).
*   **Function:** It is shared across all sessions. If an entity is not found in L1, Hibernate looks in L2 before hitting the DB.
*   **Configuration:** Must be explicitly enabled in `hibernate.cfg.xml`.

---

### 3. HQL vs. Criteria API
Both are used to retrieve data, but they serve different purposes:

*   **HQL (Hibernate Query Language):**
    *   **Type:** String-based (similar to SQL but uses Class names and Property names).
    *   **Pros:** Easy to write for complex joins and static queries.
    *   **Cons:** Errors are caught at runtime (not compile-time); difficult to build dynamic queries.
*   **Criteria API:**
    *   **Type:** Programmatic/Object-oriented API.
    *   **Pros:** Type-safe; errors are caught at compile-time; excellent for building dynamic "Search" filters where the user selects various parameters.
    *   **Cons:** Can become verbose and hard to read for very complex queries.

---

### 4. Fetching Strategies: Lazy vs. Eager
This determines how associated objects (collections or child entities) are loaded.

*   **Lazy Loading (Default for Collections):**
    *   Hibernate loads the parent object but creates a **Proxy** (placeholder) for the child objects.
    *   The database is only queried for the children when you explicitly call `getParent().getChildren()`.
    *   **Benefit:** Improved performance by avoiding unnecessary data loading.
*   **Eager Loading:**
    *   Hibernate loads the parent and all its associated child objects in a single query (usually via a JOIN).
    *   **Benefit:** Prevents `LazyInitializationException` if the session is closed.
    *   **Risk:** Can lead to memory issues and slow performance if the data graph is large.

---

### 5. Difference between `update()` and `merge()`
These methods are used to move a "Detached" object back to "Persistent" state.

*   **`update()`:**
    *   Attempts to re-attach the object to the session.
    *   **Limitation:** If the session already contains another persistent instance with the same Identifier (ID), `update()` throws a `NonUniqueObjectException`.
*   **`merge()`:**
    *   Checks if an instance with the same ID already exists in the session.
    *   If it exists, it copies the state of the detached object into the existing persistent object and returns the persistent instance.
    *   It is safer than `update()` when dealing with complex session management.

---

### 6. Hibernate Dirty Checking
Dirty checking is a feature that allows Hibernate to automatically determine if an object has been modified.
*   When a session is flushed (usually at commit), Hibernate compares the current state of persistent objects with the "snapshot" it took when the object was loaded.
*   If they differ, Hibernate automatically triggers an `UPDATE` SQL statement.
*   **Benefit:** Developers don't need to manually call `session.update()` every time they change a field.

---

### 7. Pagination in Hibernate
Hibernate provides a database-independent way to perform pagination using two methods in the `Query` or `Criteria` interface:

1.  **`setFirstResult(int start)`:** Specifies which row to start from (offset).
2.  **`setMaxResults(int limit)`:** Specifies how many records to retrieve.

**Example Code Snippet:**
```java
Query query = session.createQuery("from Employee");
query.setFirstResult(0); // Start at row 0
query.setMaxResults(10); // Get 10 rows
List results = query.list();
```

---

### 8. The `n+1` Select Problem
This is a common performance issue in Hibernate:
*   **The Problem:** You fetch a list of $N$ objects. If you then iterate through them and access a lazy-loaded collection for each, Hibernate executes 1 query for the main list and $N$ additional queries for the children.
*   **The Solution:** 
    1.  Use **HQL Join Fetch** (`from Parent p join fetch p.children`).
    2.  Use **Batch Fetching** (`@BatchSize`).
    3.  Use **Entity Graphs**.

---

### 9. Component Mapping (@Embeddable & @Embedded)
*   **Concept:** Used when you have a class (e.g., `Address`) that you want to store as part of another table (e.g., `User`) rather than in its own separate table.
*   **`@Embeddable`:** Applied to the class that will be embedded (Address).
*   **`@Embedded`:** Applied to the field in the main entity (User).
*   **Result:** The fields of the Address class become columns in the User table.