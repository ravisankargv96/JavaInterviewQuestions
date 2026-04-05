These detailed notes are based on the video "All about joins in Sql with Example" by Code Decode.

---

### **1. Introduction to SQL Joins**
*   **Purpose:** Joins are used to retrieve and combine data from two or more tables based on a related column. They provide a "meaningful result set" for users or downstream systems.
*   **When to use:** Whenever you need to fetch records that are spread across multiple tables in a Relational Database Management System (RDBMS).

---

### **2. Sample Tables for Examples**
To demonstrate joins, the video uses two tables:
*   **Employee Table:** Contains `ID`, `Name`, and `Department_ID`.
*   **Department Table:** Contains `ID` and `Department_Name`.
*   *Note:* In the demo, foreign key constraints are intentionally omitted to show how joins handle rows that do not have a corresponding match (e.g., an employee with a Dept ID that doesn't exist in the Dept table).

---

### **3. Types of Joins**

#### **A. Inner Join (The Default Join)**
*   **Logic:** Returns only those rows where there is a match in **both** tables. If a row in the Employee table does not have a matching ID in the Department table, it is hidden.
*   **Venn Diagram:** Only the overlapping (middle) part of the two circles.
*   **Key Feature:** If you use the `JOIN` keyword without specifying a type, SQL defaults to an `INNER JOIN`.
*   **Syntax Example:**
    ```sql
    SELECT * FROM Employee 
    INNER JOIN Department ON Employee.Dept_ID = Department.ID;
    ```

#### **B. Right (Outer) Join**
*   **Logic:** Returns **all rows from the right table** (Department) and the matching rows from the left table (Employee).
*   **Unmatched Records:** If there is a department with no employees, the department will still appear in the result, but the employee columns will be `NULL`.
*   **Venn Diagram:** The entire right circle plus the intersection.
*   **Syntax Example:**
    ```sql
    SELECT * FROM Employee 
    RIGHT JOIN Department ON Employee.Dept_ID = Department.ID;
    ```

#### **C. Left (Outer) Join**
*   **Logic:** Returns **all rows from the left table** (Employee) and the matching rows from the right table (Department).
*   **Unmatched Records:** If an employee belongs to a Department ID that doesn't exist in the Department table, the employee’s name will appear, but the Department Name will be `NULL`.
*   **Venn Diagram:** The entire left circle plus the intersection.
*   **Syntax Example:**
    ```sql
    SELECT * FROM Employee 
    LEFT JOIN Department ON Employee.Dept_ID = Department.ID;
    ```

#### **D. Full (Outer) Join**
*   **Logic:** Returns all records when there is a match in either the left or the right table. It is a combination of both Left and Right joins.
*   **MySQL Limitation:** MySQL does **not** support the `FULL OUTER JOIN` keyword directly.
*   **Workaround (MySQL):** You must perform a `UNION` of a Left Join and a Right Join.
    ```sql
    SELECT * FROM Employee LEFT JOIN Department ON Employee.Dept_ID = Department.ID
    UNION
    SELECT * FROM Employee RIGHT JOIN Department ON Employee.Dept_ID = Department.ID;
    ```

#### **E. Self Join**
*   **Logic:** A table is joined with **itself**. This is useful when comparing rows within the same table.
*   **Common Use Case:** Finding employees who work in the same department or finding managers within an employee list.
*   **Method:** You must use aliases to treat the single table as two different entities (e.g., `E1` and `E2`).
*   **Interview Logic Example:** To find employees in the same department:
    ```sql
    SELECT E1.Name, E2.Name 
    FROM Employee E1, Employee E2 
    WHERE E1.Dept_ID = E2.Dept_ID AND E1.ID <> E2.ID;
    ```
    *(The `ID <> ID` condition ensures you don't match an employee with themselves).*

#### **F. Cross Join (Cartesian Product)**
*   **Logic:** Every row from the first table is joined with **every row** of the second table.
*   **Result Set Size:** Total rows = `(Rows in Table A) * (Rows in Table B)`.
*   **Syntax:** Can be written using the `CROSS JOIN` keyword or simply by putting a comma between tables without an `ON` or `WHERE` clause.
*   **Example:** If Table A has 4 rows and Table B has 3 rows, the result will have 12 rows.

---

### **4. Important Interview Tips**
1.  **The `ON` Clause:** This is critical. If you perform a join without an `ON` condition or a `WHERE` clause to link the IDs, the database will default to a **Cross Join** (Cartesian Product), which can result in massive, irrelevant datasets.
2.  **Default Behavior:** If an interviewer asks what happens if you just write `JOIN`, the answer is `INNER JOIN`.
3.  **Handling NULLs:** In Outer Joins (Left/Right), be prepared to explain that the "missing" side of the relationship is populated with `NULL` values.
4.  **MySQL vs. SQL:** Remember that syntax like `FULL OUTER JOIN` works in standard SQL (like PostgreSQL or SQL Server) but requires a `UNION` workaround in MySQL.