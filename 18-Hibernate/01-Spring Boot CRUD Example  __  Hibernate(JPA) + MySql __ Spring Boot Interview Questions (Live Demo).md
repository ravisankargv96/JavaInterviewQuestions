These notes provide a comprehensive overview of the Spring Boot CRUD tutorial, covering project setup, architecture, implementation, and common interview topics discussed in the video.

### **1. Introduction and Tech Stack**
The tutorial demonstrates building a RESTful API using the following technologies:
*   **Framework:** Spring Boot
*   **Database:** MySQL
*   **ORM:** Hibernate (via Spring Data JPA)
*   **Build Tool:** Maven
*   **Utility:** Lombok (to reduce boilerplate code)
*   **Testing:** Postman

---

### **2. Project Initialization**
The project is created using **Spring Initializr** with the following configurations:
*   **Project:** Maven Project
*   **Language:** Java
*   **Spring Boot Version:** (Latest stable version used in the video)
*   **Dependencies:**
    *   **Spring Web:** For building REST APIs using Spring MVC.
    *   **Spring Data JPA:** For database interaction using Hibernate.
    *   **MySQL Driver:** To connect to the MySQL database.
    *   **Lombok:** To automatically generate getters, setters, and constructors.

---

### **3. Database Configuration**
Configuration is done in the `src/main/resources/application.properties` file:
*   **Spring Datasource:** Define `url`, `username`, and `password`.
*   **JPA Properties:** 
    *   `spring.jpa.hibernate.ddl-auto=update`: Automatically creates or updates table schemas.
    *   `spring.jpa.show-sql=true`: Prints SQL queries in the console for debugging.
    *   `spring.jpa.properties.hibernate.dialect`: Set to MySQL dialect.

---

### **4. Layers of the Application**
The video follows a standard layered architecture:

#### **A. Entity Layer (Model)**
*   Defines the table structure.
*   **Annotations used:** 
    *   `@Entity`: Marks the class as a JPA entity.
    *   `@Id`: Specifies the primary key.
    *   `@GeneratedValue(strategy = GenerationType.AUTO)`: Handles primary key generation.
    *   `@Data` (Lombok): Generates getters, setters, `toString`, etc.

#### **B. Repository Layer**
*   Create an interface that extends `JpaRepository<EntityName, IdType>`.
*   This provides built-in methods like `save()`, `findAll()`, `findById()`, and `deleteById()`.

#### **C. Service Layer**
*   This layer contains the business logic.
*   It consists of an **Interface** and an **Implementation class** (`@Service`).
*   The Service calls the Repository methods.

#### **D. Controller Layer**
*   Annotated with `@RestController`.
*   Handles HTTP requests and maps them to service methods.
*   **Key Annotations:** 
    *   `@PostMapping`: For Create operations.
    *   `@GetMapping`: For Read operations.
    *   `@PutMapping`: For Update operations.
    *   `@DeleteMapping`: For Delete operations.
    *   `@RequestBody`: To bind the incoming JSON to a Java object.
    *   `@PathVariable`: To extract data from the URL.

---

### **5. CRUD Implementation Details**

1.  **Create (POST):** Accepts a JSON object and saves it using `repository.save()`.
2.  **Read (GET):** 
    *   `findAll()` to get a list of all records.
    *   `findById()` to get a specific record (wrapped in an `Optional`).
3.  **Update (PUT):** 
    *   Fetch the existing object by ID.
    *   Check for null or empty values provided by the user.
    *   Update specific fields and call `save()`.
4.  **Delete (DELETE):** Takes an ID as a Path Variable and calls `repository.deleteById()`.

---

### **6. Spring Boot Interview Questions Covered**

The video highlights several key concepts often asked in interviews:

*   **What is @SpringBootApplication?**
    It is a combination of three annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. It bootstraps the application.
*   **Difference between @Controller and @RestController?**
    `@RestController` is a convenience annotation that combines `@Controller` and `@ResponseBody`. It ensures the return value is written directly to the HTTP response body as JSON/XML.
*   **What is Dependency Injection (DI)?**
    The process where the Spring Container provides the required objects (beans) to a class rather than the class creating them manually. This is usually done using the `@Autowired` annotation.
*   **What is Spring Data JPA?**
    It is a part of the Spring Framework that reduces the amount of boilerplate code required to implement data access layers. It uses Hibernate under the hood as the default JPA implementation.
*   **What is the use of application.properties?**
    It is used to manage application configurations such as database connections, server ports, and logging levels without changing the code.
*   **What is Lombok?**
    A Java library that helps reduce boilerplate code (getters, setters, constructors) by using annotations.

---

### **7. Testing with Postman**
*   **POST:** Send a JSON payload to the endpoint.
*   **GET:** Retrieve the data to verify the POST was successful.
*   **PUT:** Modify an existing entry by passing the ID and updated data.
*   **DELETE:** Remove an entry by ID and verify the deletion with a subsequent GET request.