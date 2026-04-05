These detailed notes are based on the SQL interview preparation video by **Code Decode**, specifically tailored for Java Developers.

---

### 1. Finding the Nth Highest Salary
One of the most common interview questions is finding a specific rank of salary (e.g., the 3rd highest) from an `Employee` table.

#### A. The Naive Approach (Nested Subqueries)
You can find the highest salary using `SELECT MAX(salary)`. To find the second highest, you find the max salary where the salary is less than the absolute maximum.
*   **Query for 3rd Highest:**
    ```sql
    SELECT MAX(salary) FROM Employee 
    WHERE salary < (SELECT MAX(salary) FROM Employee 
                    WHERE salary < (SELECT MAX(salary) FROM Employee));
    ```
*   **Drawback:** This is not scalable. If an interviewer asks for the 100th or 1000th highest salary, you cannot keep nesting queries. It significantly degrades performance.

#### B. The Optimized Approach (Using `LIMIT`)
The best way to solve this is to sort the data first and then skip to the desired row.
1.  **Order by salary in descending order:** This puts the highest at the top.
2.  **Use the `LIMIT` clause:** `LIMIT` takes two parameters: `[offset, number of rows]`.
    *   **Offset:** The starting index (starts at 0).
    *   **Rows:** How many records to fetch.

*   **Query for 3rd Highest:**
    ```sql
    SELECT salary FROM Employee 
    ORDER BY salary DESC 
    LIMIT 2, 1;
    ```
    *   *Explanation:* `2` tells SQL to skip the first two records (1st and 2nd highest). `1` tells it to pick the next single record (the 3rd highest).

---

### 2. SQL Indexes
Indexes are database objects used to retrieve records quickly and efficiently.

#### A. Why use Indexes?
*   Without an index, the database performs a **Full Table Scan**. It checks every row from top to bottom to find a match.
*   In a table with millions of rows, a full scan is extremely slow. Indexes reduce the retrieval time significantly.

#### B. Syntax
*   **Create Index:**
    ```sql
    CREATE INDEX index_name ON table_name (column_name);
    ```
*   **Drop Index:**
    ```sql
    DROP INDEX index_name ON table_name;
    ```

#### C. Internal Mechanism
SQL uses data structures to manage indexes. The most common is the **B-Tree**.
*   **B-Tree Benefits:** Operations like insertion, deletion, and lookup take **O(log n)** time, which is much faster than the **O(n)** time required for a full scan.
*   **Pointers:** The index doesn't just store the column value; it stores a **pointer** that links the index entry to the physical location of the full record in the database.
*   **Other structures:** Hash Tables and Bitmaps are also used but B-Tree is the standard for most general-purpose queries.

#### D. Disadvantages of Indexes
1.  **Extra Space:** Indexes are separate data structures and require additional disk space.
2.  **Performance Overhead on DML:** Every time you `INSERT`, `UPDATE`, or `DELETE` a row, the database must also update the corresponding index. Too many indexes can slow down data modification.

---

### 3. Dropping Tables: What Happens to Related Objects?
When you execute a `DROP TABLE` command, the database handles different related objects differently.

*   **Objects that ARE dropped:**
    *   Constraints (Primary Key, Foreign Key, Unique, etc.)
    *   Indexes (built specifically for that table)
    *   Columns and Default values.
*   **Objects that ARE NOT dropped:**
    *   **Views**
    *   **Stored Procedures**
    *   *Reason:* These objects exist **outside** the scope of the table. While they will become "invalid" or "broken" (because the underlying table they reference is gone), the definitions themselves remain in the database metadata.

---

### 4. SQL Performance Tuning Best Practices
To write high-performance SQL, developers should follow these guidelines:

1.  **Avoid `SELECT DISTINCT` if possible:** It requires extra processing to sort and filter duplicates.
2.  **Use `INNER JOIN` instead of `WHERE` for joins:** Using `WHERE` to join tables often results in a **Cartesian Product** (Cross Join) before filtering, which consumes massive memory. `INNER JOIN` is more efficient.
3.  **Avoid using Views inside loops/heavy queries:** Views can sometimes hide complex logic that slows down the main query.
4.  **Use specific column names:** Instead of `SELECT *`, specify only the columns you need to reduce data transfer.

---

### Summary Table for Quick Revision

| Question | Answer/Key Concept |
| :--- | :--- |
| **Nth Highest Salary** | Use `ORDER BY salary DESC LIMIT N-1, 1`. |
| **What is an Index?** | A data structure (usually B-Tree) to speed up data retrieval. |
| **Full Table Scan** | Searching every row sequentially (O(n)); avoided by using indexes. |
| **Index Trade-off** | Faster reads, but slower writes and more storage space. |
| **Drop Table scope** | Drops constraints/indexes; does NOT drop views/stored procedures. |
| **Join Performance** | `INNER JOIN` is preferred over comma-separated `WHERE` joins. |