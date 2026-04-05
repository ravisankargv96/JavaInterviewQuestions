These notes provide a detailed breakdown of SQL performance tuning and best practices as discussed in the "Code Decode" video. The focus is on identifying common query patterns that degrade performance and providing efficient alternatives.

---

### **1. Avoid Using `SELECT *`**
*   **The Problem:** Using `SELECT *` fetches every column in the table, including those that are not needed for the business logic.
*   **Why it’s Bad:** It puts an unnecessary load on the database resources by querying and transferring redundant data. It can also cause issues if the table schema changes (e.g., adding a large BLOB column).
*   **The Solution:** Explicitly name only the columns required for your application (e.g., fetching only `id`, `name`, and `city` instead of the entire employee record).
*   **Example:**
    *   *Bad:* `SELECT * FROM employee;`
    *   *Good:* `SELECT id, name, city FROM employee;`

### **2. Minimize the Use of `DISTINCT`**
*   **The Problem:** Developers often use `DISTINCT` to remove duplicate rows from the result set.
*   **Why it’s Bad:** To process a `DISTINCT` query, the database engine must perform a "group by" operation on every single row to identify duplicates. This requires significant processing power and memory, especially on large datasets.
*   **The Solution:** 
    *   Understand the data and business logic better. 
    *   Consult with a Database Administrator (DBA) to identify a combination of columns that inherently provides unique results without needing the `DISTINCT` keyword.
    *   By adding specific columns that make a record unique, you eliminate the need for the expensive grouping step.

### **3. Use `INNER JOIN` Instead of `WHERE` for Joins**
*   **The Problem:** Joining two tables using a `WHERE` clause (e.g., `FROM tableA, tableB WHERE tableA.id = tableB.id`).
*   **Why it’s Bad:** This approach creates a **Cartesian Product** first. 
    *   If Table A has 1,000 rows and Table B has 1,000 rows, the database creates a temporary set of 1,000,000 rows (M x N) before applying the filter. 
    *   This is highly resource-intensive and wastes CPU/memory.
*   **The Solution:** Use the `INNER JOIN ... ON` syntax.
*   **Example:**
    *   *Bad:* `SELECT * FROM employee e, project p WHERE e.project_id = p.id;`
    *   *Good:* `SELECT * FROM employee e INNER JOIN project p ON e.project_id = p.id;`
*   **Result:** The performance can increase exponentially (e.g., 9x or more depending on table size) because only matching rows are processed.

### **4. Prefer `WHERE` Over `HAVING`**
*   **The Problem:** Using the `HAVING` clause to filter non-aggregated data.
*   **The Order of Execution:** To understand why this matters, look at the SQL execution order:
    1.  `FROM` (Select the table)
    2.  `WHERE` (Filters the raw rows)
    3.  `GROUP BY` (Groups the filtered rows)
    4.  `HAVING` (Filters the groups)
*   **Why `WHERE` is Better:**
    *   If you use `WHERE`, the database filters the rows **before** grouping. This means the `GROUP BY` operation has fewer rows to process.
    *   If you use `HAVING`, the database groups **all** rows first and then discards the ones that don't match. This is much slower.
*   **The Solution:** Use `WHERE` to filter individual rows. Use `HAVING` **only** when filtering based on an aggregate function (like `COUNT`, `SUM`, or `AVG`).
*   **Example:**
    *   *Bad:* `SELECT name, COUNT(name) FROM employee GROUP BY name HAVING name = 'Code';`
    *   *Good:* `SELECT name, COUNT(name) FROM employee WHERE name = 'Code' GROUP BY name;`

### **5. Optimize Result Sets and Use Pagination**
*   **The Problem:** Fetching thousands of rows and then filtering them in the application layer (e.g., using a Java `for` loop).
*   **Why it’s Bad:** Transferring large amounts of data over the network that will eventually be discarded is a waste of bandwidth and application memory.
*   **The Solution:** 
    *   **Limit your joins:** Use only the joins necessary for the specific task.
    *   **Use `LIMIT`:** If your UI only shows 10 records at a time, use the `LIMIT` and `OFFSET` keywords to fetch only those 10 records from the database.
    *   This is known as **Database-level Pagination**.

### **6. Additional Best Practices (Briefly Mentioned)**
*   **Avoid Cursors:** Cursors process rows one by one (procedural) rather than in sets (declarative), which is extremely slow in relational databases.
*   **Wildcard Caution:** Be careful with the `LIKE` operator. Using a wildcard at the beginning of a string (e.g., `LIKE '%search'`) prevents the database from using indexes, leading to a full table scan.
*   **Limit Joins:** Only join tables when absolutely necessary; the more joins in a query, the slower the execution becomes as the result set complexity increases.

### **Summary Table: SQL Tuning**

| Feature | Avoid | Use Instead | Why? |
| :--- | :--- | :--- | :--- |
| **Columns** | `SELECT *` | `SELECT col1, col2` | Reduces I/O and memory usage. |
| **Uniqueness** | `DISTINCT` | Column combinations | Avoids expensive grouping overhead. |
| **Joins** | `WHERE` clause join | `INNER JOIN ... ON` | Prevents Cartesian Product creation. |
| **Filtering** | `HAVING` | `WHERE` | Filters rows *before* grouping, saving CPU. |
| **Large Data** | Fetch all + Java filter | `LIMIT` / Pagination | Reduces network traffic and memory load. |