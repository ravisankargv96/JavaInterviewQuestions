These detailed notes cover the core concepts, internal workings, and interview-focused topics discussed in the "Spring Boot Cheat Sheet" video by Code Decode.

### 1. Introduction to Spring Boot
Spring Boot is not a replacement for the Spring Framework; it is an extension that makes it easier to use. It eliminates the "boilerplate" configuration required to set up a Spring application.

**Key Advantages:**
*   **Auto-configuration:** Automatically configures your application based on the dependencies you have added.
*   **Starter Dependencies:** Groups related libraries into a single dependency to simplify build management.
*   **Embedded Servers:** No need to install and configure an external server like Tomcat; it’s built into the JAR.
*   **Production-Ready Features:** Includes tools like Actuator for monitoring and health checks.

---

### 2. Core Differences: Spring vs. Spring Boot
*   **Configuration:** Spring requires heavy XML or Java-based configuration. Spring Boot uses the "Opinionated" approach, reducing the need for manual setup.
*   **Servers:** In Spring, you must deploy a WAR file to an external server. In Spring Boot, you run a JAR file with an embedded server.
*   **Dependency Management:** Spring requires version management for every library. Spring Boot uses `spring-boot-starter-parent` to manage compatible versions automatically.

---

### 3. The `@SpringBootApplication` Annotation
This is the most critical annotation. It is a "meta-annotation" that combines three others:
1.  **`@Configuration`:** Tags the class as a source of bean definitions.
2.  **`@EnableAutoConfiguration`:** Tells Spring Boot to start adding beans based on classpath settings (e.g., if `h2` is on the path, it configures an H2 database).
3.  **`@ComponentScan`:** Tells Spring to look for other components, configurations, and services in the package, allowing it to find your controllers and services.

---

### 4. Spring Boot Starters
Starters are a set of convenient dependency descriptors. They act as "one-stop shops" for all the Spring and related technology you need.
*   **`spring-boot-starter-web`:** Used for building web applications, including RESTful services. It includes Tomcat and Spring MVC.
*   **`spring-boot-starter-data-jpa`:** Used for connecting to relational databases using Hibernate.
*   **`spring-boot-starter-test`:** Includes JUnit, Hamcrest, and Mockito for testing.

---

### 5. Auto-Configuration Internal Mechanism
Spring Boot looks for a file named `spring.factories` inside the `META-INF` directory of your dependencies. 
*   This file contains a list of all the configuration classes that Spring Boot should attempt to run.
*   It uses `@Conditional` annotations (like `@ConditionalOnClass` or `@ConditionalOnMissingBean`) to decide whether to actually initialize a bean. If a bean is already defined by the user, Spring Boot's auto-configuration backs off.

---

### 6. Spring Boot Starter Parent
By inheriting from `spring-boot-starter-parent` in your `pom.xml`, you get:
*   **Default Compiler Level:** Usually set to Java 8 or higher.
*   **Dependency Management:** You don't need to specify `<version>` tags for dependencies covered by the parent.
*   **Resource Filtering:** Standardized directory structures for configuration files.

---

### 7. Embedded Servers
Spring Boot supports three main embedded containers:
1.  **Tomcat** (Default)
2.  **Jetty**
3.  **Undertow**

**How to change servers:** To switch from Tomcat to Jetty, you must exclude the `spring-boot-starter-tomcat` from your `spring-boot-starter-web` dependency and then add `spring-boot-starter-jetty`.

---

### 8. Spring Boot Actuator
Actuator is used to monitor and manage your application when it is pushed to production. It provides "endpoints" (HTTP or JMX) to check:
*   `/health`: Shows if the application is "UP" or "DOWN."
*   `/info`: Displays arbitrary application info.
*   `/metrics`: Shows memory usage, CPU usage, etc.
*   `/beans`: Shows a complete list of all Spring beans in the context.
*   `/env`: Displays environment properties.

---

### 9. Profiles in Spring Boot
Profiles allow you to segregate parts of your application configuration and make it only available in certain environments (Dev, QA, Prod).
*   **Naming Convention:** `application-{profile}.properties` (e.g., `application-dev.properties`).
*   **Activation:** You can activate a profile in `application.properties` using `spring.profiles.active=dev` or via command-line arguments.

---

### 10. Spring Data JPA
Spring Data JPA reduces the boilerplate code required to implement data access layers.
*   **Repositories:** By extending `JpaRepository`, you get standard CRUD operations (Save, Delete, Find) without writing any SQL.
*   **Query Methods:** You can derive queries simply by naming methods correctly (e.g., `findByLastName`).

---

### 11. Essential Interview Questions Quick-Ref
*   **What is the entry point of a Spring Boot application?** The class annotated with `@SpringBootApplication` and the `main` method calling `SpringApplication.run()`.
*   **How do you reload changes without restarting the server?** Use `spring-boot-devtools`.
*   **Where can you define properties?** In `application.properties`, `application.yml`, or via environment variables.
*   **How do you handle exceptions globally?** Use `@ControllerAdvice` and `@ExceptionHandler`.
*   **Difference between `@RestController` and `@Controller`?** `@RestController` is a combination of `@Controller` and `@ResponseBody`, meaning it automatically serializes the return object into JSON/XML.