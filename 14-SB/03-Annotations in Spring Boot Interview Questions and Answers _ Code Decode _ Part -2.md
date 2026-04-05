# Detailed Notes: Annotations in Spring Boot (Part 2)

These notes summarize the interview-focused discussion on Spring Boot annotations from the "Code Decode" video.

---

## 1. Overview: Core Project Annotations
The most common answer to "Which annotations have you used in your project?" begins with **`@SpringBootApplication`**. 
*   **Note:** This is a combination of three annotations (`@SpringBootConfiguration`, `@EnableAutoConfiguration`, and `@ComponentScan`), which were covered in Part 1 of this series.

---

## 2. `@Autowired`
This is the primary way to implement **Dependency Injection (DI)** in a Spring/Spring Boot application.

*   **Usage:** It is used to inject one bean into another (e.g., injecting a Repository into a Service, or a Service into a Controller).
*   **Mechanism:** The Spring Container provides the required bean to the dependent bean at runtime.
*   **Where to use it:**
    *   **Instance Variables (Fields):** Property-level injection.
    *   **Setter Methods:** Method-level injection.
    *   **Constructors:** Constructor-level injection.
*   **Matching:** By default, Spring auto-wires beans **by type**.
*   **Example Scenario:** 
    *   An `Employee` class requires an `Address` object. 
    *   By annotating the `Address` field with `@Autowired`, Spring searches the container for an `Address` bean and injects it.
    *   If the required bean cannot be resolved, the dependent bean (`Employee`) will not be created, and the application will fail to start.

---

## 3. `@Component`
While `@Autowired` handles injection, `@Component` handles **bean creation**.

*   **Definition:** A class-level annotation used to mark a Java class as a Spring Bean.
*   **Process:** During startup, Spring performs a "Component Scan." Any class marked with `@Component` is picked up, configured, and initialized in the **Application Context**.
*   **Consequence of Omission:** If you remove `@Component` from a class and try to `@AutoWire` it elsewhere, Spring will throw an exception (e.g., *"Consider defining a bean of type... in your configuration"*).
*   **Stereotype:** It is the generic stereotype for all Spring-managed components.

---

## 4. Stereotype Specializations
Spring provides specialized versions of `@Component` for different architectural layers. All these annotations are internally annotated with `@Component`.

### A. `@Controller`
*   **Layer:** Presentation Layer (MVC).
*   **Purpose:** Marks the class as a **Web Request Handler**. It is capable of accepting HTTP requests and sending responses back to the client.
*   **Usage:** Typically used with `@RequestMapping`. It usually returns a `String` representing a view name (like a JSP page).
*   **`@Component` vs. `@Controller`:**
    *   **Similarity:** Both create a bean in the container.
    *   **Difference:** If you replace `@Controller` with `@Component`, the class will still be a bean, but it **will not** function as a web handler. The `DispatcherServlet` will not recognize the `@GetMapping` or `@PostMapping` paths inside it, resulting in a "404 Not Found" error.

### B. `@RestController`
*   A specialized version of `@Controller` used for RESTful web services.
*   It combines `@Controller` and `@ResponseBody`, ensuring the return data is written directly to the HTTP response body (usually as JSON/XML) instead of navigating to a JSP/HTML page.

### C. `@Service`
*   **Layer:** Business Layer.
*   **Purpose:** Marks the class as a place where business logic resides.
*   **Technicality:** It is technically identical to `@Component`. However, it is used to provide better intent and readability within the architecture.

### D. `@Repository`
*   **Layer:** Data Access Object (DAO) / Persistence Layer.
*   **Purpose:** Marks the class as a component that accesses the database.
*   **Special Behavior ("Two birds with one stone"):**
    1.  **Bean Creation:** It registers the class as a bean.
    2.  **Exception Translation:** It automatically catches platform-specific exceptions (like JDBC/SQL exceptions) and re-throws them as Spring’s unified **unchecked exceptions**. This keeps the error handling consistent across the application.

---

## 5. Key Interview Takeaways

1.  **Layer Conventions:** Always use the specific annotation for the specific layer (`@Controller`, `@Service`, `@Repository`) rather than the generic `@Component`. This follows best practices and enables layer-specific features (like exception translation in repositories).
2.  **Auto-wiring Logic:** Remember that `@Autowired` searches by **Type**.
3.  **The Role of Containers:** The container is responsible for the lifecycle of these beans—from discovery (via `@Component`) to injection (via `@Autowired`).
4.  **Transient Keyword (Java):** While not a Spring annotation, the transcript mentions `transient`. In a JPA/Hibernate context, marking a field as `transient` prevents it from being mapped to a database column.