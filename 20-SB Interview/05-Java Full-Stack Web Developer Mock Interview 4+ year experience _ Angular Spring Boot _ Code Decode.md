These notes summarize the Java Full-Stack Mock Interview for a developer with 4+ years of experience, focusing on Spring Boot and Angular.

---

### **1. Java 8 Features**
*   **Lambda Expressions:** Used for functional interfaces; makes code concise and focuses on logic rather than boilerplate.
*   **Method References:** Used to reuse existing method implementations for functional interfaces, enhancing reusability.
*   **Streams API:** 
    *   Provides functions like `map`, `filter`, and `reduce`.
    *   **Parallel Streams:** Allow performing operations simultaneously on multiple elements for better performance.
    *   **Lazy Evaluation:** Intermediate operations (like `map`) do not execute until a terminal operation (like `collect` or `forEach`) is called.
*   **Date-Time API:** Introduced `LocalDate`, `LocalTime`, and `ZonedDateTime` to handle time zones and date manipulation more effectively than the old `Calendar` class.

---

### **2. Spring & Spring Boot Backend**
*   **Bean Management:**
    *   **`@Component`:** Used to mark a class as a Spring-managed bean for Inversion of Control (IoC).
    *   **`@Bean`:** Used within `@Configuration` classes on methods. It provides more manual control over object initialization.
*   **Bean Scopes:**
    *   **Singleton (Default):** One instance per Spring container.
    *   **Prototype:** New instance created every time it is requested.
    *   **Request/Session:** Tied to HTTP request/session lifecycles (Web applications).
*   **Spring Containers:**
    *   **BeanFactory:** Basic container, lazy-loads beans (initialized only when called).
    *   **ApplicationContext:** Advanced container, eager-loads singleton beans (initialized at startup). Supports AOP and internationalization.
*   **Pagination:** Implemented using the `Pageable` interface and `JpaRepository`. The repository returns a `Page<T>` object containing the data and metadata (page size, total elements).
*   **Caching:**
    *   Enabled via `@EnableCaching` and `@Cacheable`.
    *   **Hibernate Caching:** First-level cache is associated with the **Session** (enabled by default). Second-level cache is associated with the **SessionFactory** (requires configuration).

---

### **3. Hibernate & Exception Handling**
*   **ORM Mapping:** Uses `@Entity`, `@Table(name="...")`, `@Id`, and `@Column` to map POJOs to database tables.
*   **Exception Handling:**
    *   **`@ControllerAdvice`:** Used for global exception handling.
    *   **`@ExceptionHandler`:** Specific method-level annotation to handle defined exception types and return custom HTTP responses (e.g., 404, 500).
    *   **Checked vs. Unchecked:** The Repository layer in Spring typically converts checked SQL exceptions into unchecked DataAccessExceptions.
*   **Criteria API:** Used for building type-safe, programmatic queries. It is excellent for fetching data with complex filters but generally does not support `Update` or `Delete` operations.

---

### **4. Build Tools & DevOps**
*   **Maven vs. Gradle:**
    *   **Maven:** Uses XML (`pom.xml`) for dependency management.
    *   **Gradle:** Uses Groovy/Kotlin DSL. It is generally faster due to incremental builds and has distinct "configuration" and "execution" phases.
*   **CI/CD:** Continuous Integration (CI) involves automating code builds and tests (e.g., using Jenkins). Continuous Deployment (CD) automates the delivery of code to servers (Dev, QA, or Production).

---

### **5. Coding Logic (Java 8 Streams)**
*   **Sum of Even Numbers:**
    ```java
    int sum = list.stream()
                  .filter(n -> n % 2 == 0)
                  .mapToInt(Integer::intValue)
                  .sum();
    ```
*   **List to Uppercase & Join:**
    ```java
    String result = list.stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(","));
    ```

---

### **6. Angular Frontend**
*   **Data Binding:**
    *   **Interpolation:** `{{ value }}` – Component to Template.
    *   **Property Binding:** `[property]="value"` – Component to Template.
    *   **Event Binding:** `(event)="handler()"` – Template to Component.
    *   **Two-way Binding:** `[(ngModel)]` – Synchronization between both.
*   **Single Page Application (SPA):** Only the content of the page changes; the outer shell (header/footer) remains, avoiding full page reloads and saving bandwidth.
*   **TypeScript:** A superset of JavaScript that offers static typing and better error detection at compile time. Browsers cannot run TS directly; it must be **transpiled** to JS.
*   **AOT vs. JIT Compilation:**
    *   **AOT (Ahead-of-Time):** Compiled during the build process. Result is smaller, more secure, and faster to load in the browser.
    *   **JIT (Just-in-Time):** Compiled in the browser at runtime. Larger bundle size because it includes the Angular compiler.
*   **Pipes:** Used to transform UI data (e.g., `DatePipe`, `CurrencyPipe`). Custom pipes implement the `PipeTransform` interface.
*   **Observables vs. Promises:**
    *   **Promises:** Eagerly executed, handle a single event, cannot be canceled.
    *   **Observables:** Lazy (only execute when subscribed), handle multiple values over time, can be canceled.
*   **Component Communication:**
    *   **Parent to Child:** `@Input()` decorator.
    *   **Child to Parent:** `@Output()` decorator with `EventEmitter`.
    *   **Unrelated Components:** Use a shared **Service**.
*   **HTTP Interceptors:** Used to modify outgoing requests (e.g., attaching a JWT Bearer token) or incoming responses globally.

---

### **7. Full-Stack Flow & Security**
*   **The Request Cycle:**
    1.  Angular Component triggers an event.
    2.  Angular Service fires an HTTP request (returns an **Observable**).
    3.  Spring Boot **Controller** receives the request.
    4.  **Service Layer** processes business logic.
    5.  **Repository Layer** fetches data from DB using Hibernate.
    6.  Data returns as JSON to Angular.
    7.  Component **subscribes** to the Observable and updates the UI.
*   **CORS (Cross-Origin Resource Sharing):** When the frontend (Port 4200) calls the backend (Port 8080), a CORS error occurs. This is handled in Spring Boot using the `@CrossOrigin` annotation or a global Security Filter configuration to whitelist specific origins.