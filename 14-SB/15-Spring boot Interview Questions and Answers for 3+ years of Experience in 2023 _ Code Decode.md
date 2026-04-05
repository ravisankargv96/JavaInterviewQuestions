These detailed notes are based on the video **"Spring Boot Interview Questions and Answers for 3+ years of Experience"** by Code Decode.

---

### 1. Spring Boot vs. Spring Framework
Spring Boot is a framework built on top of the Spring Framework designed to simplify development and reduce "Time to Market."

**Key Features of Spring Boot:**
*   **Auto-configuration:** Automatically configures beans based on the dependencies present in the classpath.
*   **Starter Dependencies:** Aggregated dependencies (e.g., `spring-boot-starter-web`) that simplify build configuration.
*   **Embedded Servers:** Comes with built-in servers like Tomcat or Jetty, eliminating the need for external server setup.
*   **Actuator:** Provides production-ready features like health checks and metrics.
*   **Opinionated:** It makes assumptions about how you want to build your application and provides default configurations.

**Comparison Table:**

| Feature | Spring Framework | Spring Boot |
| :--- | :--- | :--- |
| **Configuration** | Manual (XML or Java-based) | Auto-configuration (Opinionated) |
| **Dependencies** | Manual version management | Starter dependencies manage versions |
| **Server** | Requires external deployment | Embedded (Tomcat, Jetty, etc.) |
| **Flexibility** | High (Complete control) | Less (Due to defaults) |
| **Productivity** | Slower (Boilerplate heavy) | Faster (Focus on business logic) |

---

### 2. Configuring Database Connections
There are two primary ways to configure a Data Source in Spring Boot:

1.  **Via `application.properties` or `application.yaml` (Recommended):**
    *   Define properties like `spring.datasource.url`, `username`, `password`, and `driver-class-name`.
    *   **Advantage:** You can change credentials/URLs without modifying or recompiling the source code.
2.  **Via a `@Configuration` Java Class:**
    *   Create a class annotated with `@Configuration`.
    *   Define a `@Bean` of type `DataSource` using `DataSourceBuilder`.
    *   **Disadvantage:** Requires code changes and recompilation if database details change.

---

### 3. The `@SpringBootApplication` Annotation
This is a "composed" annotation that combines three essential annotations:

1.  **`@SpringBootConfiguration`:** Indicates that the class provides Spring Boot application configuration (similar to `@Configuration`).
2.  **`@EnableAutoConfiguration`:** Tells Spring Boot to start adding beans based on classpath settings, other beans, and property settings.
3.  **`@ComponentScan`:** Tells Spring to scan the current package and all sub-packages for components (Services, Repositories, Controllers).
    *   *Note:* This is why the main class should always be in the **root package**.

---

### 4. Implementing Caching in Spring Boot
Caching improves performance by storing frequently accessed data in memory.

**Implementation Steps:**
1.  Add `spring-boot-starter-cache` dependency in `pom.xml`.
2.  Add `@EnableCaching` to the main application class.
3.  Annotate service methods with `@Cacheable(value="cacheName")`.

**Types of Caching Mechanisms:**
*   **In-Memory Caching:** Stores data in local memory (default uses `ConcurrentHashMap`). Suitable for small, static datasets.
*   **Distributed Caching:** Stores data across multiple nodes (e.g., **Redis**, **Hazelcast**). Provides high availability and scalability.
*   **External Caching:** Integration with third-party providers like **EhCache** or **Memcached**.
*   **Page Caching:** Caches the entire HTML response for static content (like Wikipedia).
*   **Query Caching:** Caches the results of specific database queries to avoid repeated heavy DB hits.

---

### 5. Significance of `@Autowired`
This annotation is used for **Dependency Injection (DI)** and is a core part of the **Inversion of Control (IoC)** principle.

*   **Function:** It allows Spring to resolve and inject collaborating beans into your bean.
*   **Benefit:** It promotes **loose coupling** between classes (e.g., a `Car` class doesn't need to manually instantiate an `Engine` using the `new` keyword).
*   **Interview Tip:** A major advantage of `@Autowired` is making code **unit-testable**. Using `new` makes mocking nearly impossible, whereas auto-wired dependencies can be easily replaced with `@Mock` or `@MockBean` in JUnit/Mockito tests.

---

### 6. Exception Handling in Spring Boot
Spring Boot provides several ways to handle errors gracefully rather than showing a "White Label Error Page" or 500 Internal Server Error.

1.  **Global Exception Handling:**
    *   Use **`@ControllerAdvice`** on a class to handle exceptions globally across the whole application.
    *   Use **`@ExceptionHandler`** on methods within that class to target specific exceptions (e.g., `NullPointerException`).
2.  **Custom Exception Handling:**
    *   Create custom classes that extend `RuntimeException`.
    *   Throw these exceptions in the service layer and catch them in the global handler to return meaningful messages to the client.
3.  **Try-Catch Blocks:** Used for local, scenario-specific exception management.

---

### 7. Profiles in Spring Boot
Profiles allow you to segregate parts of your application configuration and make them available only in certain environments (Dev, Test, Prod).

*   **Why use them?** For example, you might use a local H2 database for Development but a costly AWS RDS instance for Production.
*   **How to define:** Create files like `application-dev.properties` or `application-prod.properties`.
*   **How to activate:** Set `spring.profiles.active=dev` in your main `application.properties` file.
*   **`@Profile` Annotation:** You can annotate a `@Bean` or `@Component` with `@Profile("test")` so it only loads into the Spring container during testing.

---

### 8. Teaser for Advanced Topics (5+ and 7+ Years Exp)
The video concludes by mentioning topics for more senior roles:
*   Asynchronous processing.
*   Transaction management.
*   Handling Cross-Origin Resource Sharing (CORS).
*   Actuator deep-dives.
*   Scheduling tasks (Cron jobs).
*   Internal workings of Auto-configuration.