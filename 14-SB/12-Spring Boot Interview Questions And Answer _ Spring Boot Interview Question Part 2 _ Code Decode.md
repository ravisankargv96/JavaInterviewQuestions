These detailed notes are based on the **Code Decode** interview preparation video regarding Spring Boot. This second part of the series focuses on application configuration, management, and environment-specific settings.

---

### 1. How to change the default port in Spring Boot?
By default, Spring Boot applications run on port **8080**. There are several ways to change this:

*   **Application Properties File:** Add `server.port=9090` in `application.properties`.
*   **YAML File:** Use the hierarchy:
    ```yaml
    server:
      port: 9090
    ```
*   **Command Line:** Pass it as an argument when running the JAR:
    `java -jar app.jar --server.port=9090`
*   **Programmatically:** Use `WebServerFactoryCustomizer` or set it in `System.setProperty("server.port", "9090")` inside the Main method.

### 2. How to exclude specific Auto-configuration classes?
If you want to disable a specific auto-configuration (for example, if you have the MongoDB dependency but don't want to auto-configure the connection yet), you can use the `exclude` attribute.

*   **Via Annotation:**
    `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`
*   **Via Property File:**
    `spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration`

### 3. Spring Boot Profiles
Profiles provide a way to segregate parts of your application configuration and make it only available in certain environments (Dev, QA, Prod).

*   **Naming Convention:** Create files like `application-dev.properties` and `application-prod.properties`.
*   **Activation:** Set the active profile in your main `application.properties` file:
    `spring.profiles.active=dev`
*   **Bean Specific Profiles:** You can use the `@Profile("dev")` annotation on a class or a `@Bean` method so that it only loads when that specific profile is active.

### 4. Spring Boot DevTools
DevTools is a module designed to improve the development experience.

*   **Property Defaults:** It automatically disables caching for templates (like Thymeleaf) so you can see changes instantly.
*   **Automatic Restart:** Whenever a file on the classpath changes, DevTools triggers an application restart. (Note: This is different from a "Hot Swap").
*   **LiveReload:** It includes an embedded LiveReload server that can trigger a browser refresh when a resource is changed.
*   **Remote Updates:** It allows for remote debugging and updates.

### 5. Spring Boot Actuator
Actuator is a sub-project that provides "production-ready" features to monitor and manage your application.

*   **Usage:** Add the `spring-boot-starter-actuator` dependency.
*   **Endpoints:** It exposes HTTP endpoints to gather data:
    *   `/health`: Shows application health status (Up/Down).
    *   `/info`: Displays arbitrary application info.
    *   `/metrics`: Shows metrics like memory usage, CPU, etc.
    *   `/beans`: Returns a list of all beans configured in the ApplicationContext.
    *   `/env`: Shows the environment properties.
    *   `/mappings`: Shows a list of all `@RequestMapping` paths.
*   **Security:** By default, most endpoints are sensitive and hidden. You must expose them via:
    `management.endpoints.web.exposure.include=*`

### 6. Externalizing Configuration
Spring Boot allows you to store configuration outside of your packaged JAR so you can run the same JAR in different environments.

*   **Order of Precedence:** Spring Boot looks for properties in a specific order:
    1.  Command-line arguments.
    2.  Properties from `java:comp/env` (JNDI).
    3.  Java System properties (`System.getProperties()`).
    4.  OS environment variables.
    5.  Application properties outside the JAR.
    6.  Application properties inside the JAR.

### 7. Reading Properties in Code
There are two primary ways to access property values in your Java code:

*   **@Value Annotation:** Used for simple values.
    `@Value("${server.port}") private int port;`
*   **@ConfigurationProperties:** Used for hierarchical data or mapping a whole group of properties to a POJO. This is type-safe and recommended for complex configurations.
    ```java
    @ConfigurationProperties(prefix = "mail")
    public class MailProperties { ... }
    ```

### 8. Logging in Spring Boot
Spring Boot uses **Commons Logging** for all internal logging but leaves the underlying log implementation open.

*   **Default Framework:** If you use "Starters", **Logback** is used by default.
*   **Configuration:** You can set logging levels in `application.properties`:
    `logging.level.root=INFO`
    `logging.level.org.springframework.web=DEBUG`
*   **Custom File:** To use complex configurations, place a `logback-spring.xml` or `log4j2-spring.xml` in the resources folder.

### 9. Difference between @SpringBootApplication and @EnableAutoConfiguration
*   **@EnableAutoConfiguration:** Tells Spring Boot to start adding beans based on classpath settings (e.g., if `tomcat-embedded.jar` is there, it sets up a server).
*   **@SpringBootApplication:** This is a "convenience" annotation that wraps three annotations:
    1.  `@Configuration`: Tags the class as a source of bean definitions.
    2.  `@EnableAutoConfiguration`: Enables the magic of Spring Boot's auto-config.
    3.  `@ComponentScan`: Tells Spring to look for other components/services/controllers in the current package and sub-packages.