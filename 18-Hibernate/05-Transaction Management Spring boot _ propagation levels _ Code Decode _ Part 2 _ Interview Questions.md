These notes provide a detailed breakdown of Spring Boot Transaction Propagation levels as discussed in the "Code Decode" tutorial (Part 2).

---

### **1. REQUIRES_NEW Propagation (Deep Dive)**
The `REQUIRES_NEW` level ensures that the business logic always runs in a separate, independent transaction.

*   **Mechanism:** It instructs the container to always create a new physical transaction. It does **not** inherit from the outer transaction.
*   **Connection Handling:** It opens a new database connection for the inner transaction.
*   **Suspension:** This is a synchronous process. While the inner (`REQUIRES_NEW`) transaction is running, the outer transaction is **suspended**.
*   **Risk:** Avoid overusing `REQUIRES_NEW`. Since every call opens a new database connection, a high volume of nested calls can quickly exhaust the database connection pool.
*   **Execution Flow:** 
    1. Outer transaction starts.
    2. Inner method (`REQUIRES_NEW`) is called.
    3. Outer transaction and its connection are suspended.
    4. Inner transaction starts, executes, and commits/rolls back.
    5. Outer transaction resumes and completes.

---

### **2. NESTED Propagation**
`NESTED` is similar to `REQUIRED` in that it uses the existing transaction, but it introduces a unique mechanism called a **Savepoint**.

*   **Savepoint:** If an active transaction exists, Spring marks a "savepoint."
*   **Partial Rollback:** If an exception occurs within the nested method, the transaction rolls back **only to the savepoint**.
*   **Benefit:** The outer transaction remains intact. For example, if saving an `Employee` (outer) and an `Address` (nested), an error in `Address` will only roll back the address data, allowing the `Employee` record to still be saved.
*   **Behavior without Transaction:** If no active transaction exists, it behaves exactly like `REQUIRED` (it creates a new transaction).

---

### **3. MANDATORY Propagation**
`MANDATORY` is a "lazy" but strict propagation level. It demands an existing transaction but refuses to create one itself.

*   **Requirement:** It **must** be called within the boundary of an existing transaction.
*   **No Creation:** It will never open a physical transaction on its own.
*   **Exception:** If called without an existing transaction, it throws an exception:
    *   *Error:* `No existing transaction found for transaction marked with propagation 'mandatory'`.
*   **Precedence Note:** If using `@Transactional` at the class level, it will apply to all methods unless specifically overridden. To test `MANDATORY` failure, ensure no transaction is started by the caller (at the class or method level).

---

### **4. NEVER Propagation**
This is the polar opposite of `MANDATORY`. It strictly prohibits the use of transactions.

*   **Constraint:** No physical transaction should exist when this method is called.
*   **Exception:** If a transaction is found (inherited from a caller), it throws an exception:
    *   *Error:* `Existing transaction found for transaction marked with propagation 'never'`.
*   **Usage:** Used for logic that should never be executed within a transactional context.

---

### **5. NOT_SUPPORTED Propagation**
`NOT_SUPPORTED` ensures that the method executes without any transaction, but it is more "polite" than `NEVER`.

*   **Suspension:** If a transaction exists, Spring **suspends** it for the duration of this method.
*   **Execution:** The business logic executes non-transactionally.
*   **Resumption:** Once the method finishes, the suspended outer transaction automatically resumes.
*   **Difference from NEVER:** Unlike `NEVER`, it does not throw an exception if a transaction exists; it simply puts the transaction on hold.

---

### **6. SUPPORTS Propagation**
`SUPPORTS` is the most flexible propagation level. It adapts to the environment of the caller.

*   **With Transaction:** If a transaction exists, it will be used.
*   **Without Transaction:** If no transaction exists, it will execute non-transactionally.
*   **No Creation:** Like `MANDATORY`, it will never start a transaction on its own. It simply "supports" whatever context is currently active.

---

### **Summary Table for Interview Prep**

| Propagation | Behavior | If Transaction Exists? | If NO Transaction Exists? |
| :--- | :--- | :--- | :--- |
| **REQUIRED** | Default. Needs a transaction. | Joins existing. | Creates new. |
| **REQUIRES_NEW** | Always separate. | Suspends existing; Creates new. | Creates new. |
| **NESTED** | Uses Savepoints. | Marks savepoint in existing. | Creates new. |
| **MANDATORY** | Requires existing. | Joins existing. | **Throws Exception.** |
| **NEVER** | Forbids transactions. | **Throws Exception.** | Executes non-transactionally. |
| **NOT_SUPPORTED** | Non-transactional. | Suspends existing. | Executes non-transactionally. |
| **SUPPORTS** | Flexible. | Joins existing. | Executes non-transactionally. |

---

### **Upcoming Topics**
The next section of the series will cover **Transaction Isolation Levels**, including:
*   Dirty Reads
*   Non-repeatable Reads
*   Phantom Reads
*   How to prevent these issues using isolation settings.