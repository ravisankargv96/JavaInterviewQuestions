These detailed notes are based on the tutorial video "Spring Boot MongoDB CRUD Rest API" by Code Decode.

---

### 1. Introduction to MongoDB
MongoDB is an open-source, **NoSQL** database that stores data in a flexible, JSON-like format called **BSON** (Binary JSON). Unlike traditional relational databases, it does not use tables, rows, or columns.

**Key Concepts:**
*   **Documents:** The basic unit of data in MongoDB (equivalent to a row in SQL).
*   **Collections:** A group of MongoDB documents (equivalent to a table in SQL).
*   **BSON:** A binary representation of JSON that supports more data types (like Date and binary data).

### 2. Advantages: MongoDB vs. MySQL (SQL)

| Feature | MongoDB (NoSQL) | MySQL (Relational) |
| :--- | :--- | :--- |
| **Data Model** | Document-oriented (BSON/JSON). | Row/Column-oriented (Tables). |
| **Schema** | Flexible/Dynamic; no pre-defined schema required. | Rigid/Static; schema must be defined before inserting data. |
| **Relationships** | Uses sub-documents/nesting; no joins needed. | Uses Foreign Keys and Joins (Inner, Outer, etc.). |
| **Scalability** | Horizontal scaling (sharding) is easy. | Limited scaling (mostly Vertical or Read Replicas). |
| **Performance** | Optimized for write operations and simple queries. | Optimized for complex joins and fetch operations. |

**Example of Efficiency:**
*   In **SQL**, fetching a student's marks and school details requires joining multiple tables (Student, Marks, School).
*   In **MongoDB**, all this info is stored in one **Sub-document**. A single `find()` query retrieves the entire object without joins.

---

### 3. Installation and Setup
1.  **Download:** Download the MongoDB **Community Server** (free version).
2.  **Installation:** Choose the "Complete" setup. Install "MongoDB as a Service" so it runs in the background.
3.  **MongoDB Compass:** This is the GUI tool (similar to SQL Yog) used to visualize and manage collections and databases.
4.  **Connection:** Default Host is `localhost` and the default Port is `27017`.

---

### 4. Spring Boot Project Setup
Create a project via Spring Initializr with the following dependencies:
*   **Lombok:** To reduce boilerplate code (getters, setters, constructors).
*   **Spring Data MongoDB:** To provide the interface between Spring and Mongo.
*   **Spring Web:** To create RESTful endpoints.
*   **Spring Boot DevTools:** For automatic application restarts during development.

---

### 5. Application Configuration
In `application.yml` (or `application.properties`), define the connection settings:
```yaml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: employee-db
```
*Note: If the database `employee-db` does not exist, Spring Boot will automatically create it upon the first successful connection.*

---

### 6. Implementation Layers

#### A. Model Layer (`Employee.java`)
Create a POJO to represent the data.
*   Use `@Data` for getters/setters.
*   Use `@AllArgsConstructor` and `@NoArgsConstructor`.
*   Fields: `id` (Integer), `name` (String), `address` (String).

#### B. Repository Layer (`EmployeeRepository.java`)
Create an interface that extends `MongoRepository<Employee, Integer>`. 
*   This provides built-in methods like `save()`, `findAll()`, and `delete()`.

#### C. Service Layer (`EmployeeService.java`)
Annotate with `@Service`. Autowire the `EmployeeRepository` to handle business logic.
*   **Save/Update:** `repository.save(emp)`
*   **Find All:** `repository.findAll()`
*   **Delete:** `repository.delete(emp)`

#### D. Controller Layer (`EmployeeController.java`)
Annotate with `@RestController`. Define the API endpoints:
*   `@PostMapping("/save")`: To create or update an employee.
*   `@GetMapping("/findAll")`: To retrieve all employees.
*   `@DeleteMapping("/delete")`: To remove an employee.

---

### 7. CRUD Operations in Action

*   **Create (POST):** Send a JSON body via Postman to the `/save` endpoint. MongoDB will create the document.
*   **Read (GET):** The `/findAll` endpoint returns a list of all documents in the collection.
*   **Delete (DELETE):** Sending a document to the `/delete` endpoint removes it from the collection.

**The `_class` Attribute:**
By default, Spring Data MongoDB adds a field called `_class` to your documents. This is used to map the document back to the specific Java class. This can be disabled via a custom configuration class if desired.

---

### 8. Advanced Topics for Future Learning
The video concludes by mentioning several intermediate to advanced MongoDB concepts:
1.  **Removing `_class` attribute:** How to clean up the document structure.
2.  **Custom Repository Methods:** Creating specific queries beyond the standard CRUD.
3.  **Auto-generated IDs:** Currently, IDs are passed manually; MongoDB can generate them automatically.
4.  **Exception Handling:** Properly managing errors (e.g., "Record Not Found").
5.  **Transaction Management:** Ensuring data integrity across multiple operations (a frequent interview topic).