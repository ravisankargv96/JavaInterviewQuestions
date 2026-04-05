# Transaction Management in Spring Boot and Hibernate: Detailed Notes

## 1. Introduction to Transaction Management
In a Spring Boot application using Hibernate/JPA, you do not need to manually manage Hibernate transactions. Instead, you rely on **Spring’s Declarative Transaction Management** using the `@Transactional` annotation.

### Why do we need it?
*   **Data Integrity:** Ensures that multiple database operations (e.g., saving an employee and their address) are treated as a single unit of work.
*   **Atomicity:** If one operation fails (throws an exception), the entire set of operations is rolled back, preventing "partial data" from being saved.

---

## 2. Implementation Basics
To use transaction management in Spring Boot:
1.  **Dependency:** Include `spring-boot-starter-data-jpa` in your `pom.xml`. This brings in Hibernate and Spring’s transaction support.
2.  **Configuration:** Define your database connection (URL, username, password) in `application.properties`.
3.  **Annotation:** Apply the `@Transactional` annotation (from `org.springframework.transaction.annotation`) to your service methods or classes.

### Example Scenario
*   **Entities:** `Employee` and `Address` (One-to-One mapping).
*   **Goal:** When saving an Employee, an Address must also be saved. Both must succeed, or neither should be saved.

---

## 3. How `@Transactional` Works Internally
Spring uses **AOP (Aspect Oriented Programming)** and **Proxies** to manage transactions.

1.  **Proxy Creation:** Spring creates a proxy around the class/method annotated with `@Transactional`.
2.  **Bytecode Manipulation:** When the method is called, the proxy intercepts the call.
3.  **Logic Wrap:** It wraps the method logic in a try-catch block:
    *   **Start:** Opens a physical transaction to the database.
    *   **Execute:** Runs your business logic.
    *   **Commit:** If the method finishes successfully, it commits the changes to the database.
    *   **Rollback:** If a runtime exception occurs, it triggers a rollback.

---

## 4. Placement and Precedence
*   **Method Level:** The annotation applies only to that specific method.
*   **Class Level:** All public methods in the class become transactional.
*   **Precedence:** If both are present, the **method-level** annotation takes precedence over the class-level settings.

---

## 5. ACID Properties in Transactions
Transactions aim to provide the following four characteristics:
*   **Atomicity:** The "All or Nothing" principle. Either all operations commit, or the system reverts to the state before the transaction started.
*   **Consistency:** Moves the database from one valid state to another, ensuring all integrity constraints (like foreign keys) are met.
*   **Isolation:** Ensures that the execution of one transaction is not visible to other concurrent transactions until it is committed (prevents dirty reads).
*   **Durability:** Once a transaction is committed, the changes are permanent, even in the event of a system failure.

---

## 6. Transaction Propagation
Propagation defines how transaction boundaries are managed when one transactional method calls another.

### A. `REQUIRED` (Default)
*   **Behavior:** Supports the current transaction. If one exists, it joins it; if not, it creates a new one.
*   **Demo Observation:** If Method A (Outer) calls Method B (Inner) and both are `REQUIRED`, they share the same physical transaction. Nothing is saved to the database until Method A finishes completely.

### B. `REQUIRES_NEW`
*   **Behavior:** Always starts a new transaction. If an existing transaction is running, it is suspended.
*   **Demo Observation:** If Method B (Inner) is marked `REQUIRES_NEW`, it creates its own transaction. When Method B finishes, its data is committed to the database immediately, even if Method A (the caller) is still running or later fails.
*   **Use Case:** Useful for tasks that should be saved independently, like audit logging.

---

## 7. Key Interview Insights
*   **Proxy Limitation:** `@Transactional` only works on **public** methods. It also doesn't work if you call a transactional method from another method within the same class (due to bypassing the proxy).
*   **Rollback Behavior:** By default, Spring only rolls back for **Unchecked Exceptions** (RuntimeException and Error). It does not roll back for Checked Exceptions unless specified using `@Transactional(rollbackFor = Exception.class)`.
*   **Physical vs. Logical Transactions:** `REQUIRED` creates one physical transaction but multiple logical transactions. `REQUIRES_NEW` creates multiple physical transactions.

## 8. Summary Table: Propagation Types

| Type | Description |
| :--- | :--- |
| **REQUIRED** | Default. Joins existing transaction or creates a new one. |
| **REQUIRES_NEW** | Suspends current transaction and starts a new one. |
| **MANDATORY** | Must be called within an existing transaction; otherwise, throws an error. |
| **NEVER** | Must not be called within a transaction; otherwise, throws an error. |
| **NOT_SUPPORTED**| Suspends the transaction and executes non-transactionally. |
| **SUPPORTED** | Runs in a transaction if one exists; otherwise, runs non-transactionally. |
| **NESTED** | Executes within a nested transaction (using savepoints). |