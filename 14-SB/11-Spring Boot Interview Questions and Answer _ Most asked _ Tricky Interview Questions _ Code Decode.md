These detailed notes cover the core concepts, interview questions, and technical explanations provided in the video "Spring Boot Interview Questions and Answers" by Code Decode.

---

### **1. Core Concepts: Spring vs. Spring Boot**
*   **Spring Framework:** A comprehensive framework for Java development that provides features like Dependency Injection (DI) and Aspect-Oriented Programming (AOP). However, it requires significant manual configuration (XML or Java-based).
*   **Spring Boot:** Built on top of the Spring framework. Its primary goal is to **reduce development time** and **eliminate boilerplate configuration**.
*   **Key Differences:**
    *   Spring Boot provides "Starter" dependencies to simplify build configurations.
    *   It offers **Auto-configuration**.
    *   It includes an **Embedded Server** (Tomcat/Jetty), so you don’t need to install a server separately.

---

### **2. Important Annotations**
*   **@SpringBootApplication:** This is the entry point of the application. It is a combination of three annotations:
    1.  **@Configuration:** Tags the class as a source of bean definitions.
    2.  **@EnableAutoConfiguration:** Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
    3.  **@ComponentScan:** Tells Spring to look for other components, configurations, and services in the package, allowing it to find controllers.
*   **@RestController vs. @Controller:**
    *   `@Controller` is used to create a Map of the model object and find a view.
    *   `@RestController` is a specialized version of the controller that includes `@Controller` and `@ResponseBody`. It simplifies the creation of RESTful web services (data is returned directly in the body of the response, usually as JSON).

---

### **3. Spring Boot Starters and Dependency Management**
*   **What are Starters?** They are a set of convenient dependency descriptors you can include in your application. For example, `spring-boot-starter-web` automatically pulls in all dependencies needed for web development (Spring MVC, Tomcat, Jackson for JSON, etc.).
*   **Spring Boot Parent (pom.xml):** The parent project manages the versions of all dependencies. This ensures that all libraries are compatible with each other, preventing "Jar Hell" or version mismatch issues.

---

### **4. Auto-Configuration Explained**
*   **How it works:** Spring Boot looks at the jars present on the classpath. If it sees `HSQLDB` on the classpath, and you haven’t manually configured any database connection beans, Spring Boot auto-configures an in-memory database.
*   **Disabling Specific Auto-configuration:** If you want to disable a specific auto-configuration, use the `exclude` attribute:
    *   Example: `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`

---

### **5. Embedded Servers**
*   Spring Boot includes an embedded server (default is **Tomcat**).
*   **Benefit:** You can run the application as a simple JAR file rather than packaging it as a WAR and deploying it to an external server.
*   **Switching Servers:** You can switch to Jetty or Undertow by excluding the Tomcat dependency and including the desired server dependency in your `pom.xml`.

---

### **6. Spring Boot Actuator**
*   **Purpose:** Provides production-ready features to help you monitor and manage your application.
*   **Key Endpoints:**
    *   `/health`: Shows application health status (UP/DOWN).
    *   `/metrics`: Shows various metrics (memory usage, HTTP requests).
    *   `/info`: Displays arbitrary application info.
    *   `/env`: Displays current environment properties.
*   **Security:** By default, most actuator endpoints are sensitive and require configuration to be exposed.

---

### **7. Configuration and Profiles**
*   **Externalized Configuration:** You can manage settings outside the code using `application.properties` or `application.yml`.
*   **Profiles:** Allows you to segregate parts of your application configuration and make it only available in certain environments (e.g., `dev`, `test`, `prod`).
    *   Naming convention: `application-{profile}.properties`.
    *   Activation: `spring.profiles.active=prod`.

---

### **8. Tricky & Advanced Interview Questions**

#### **How to change the default port of a Spring Boot application?**
In the `application.properties` file, add: `server.port=8081`.

#### **What is the difference between @RequestMapping and @GetMapping?**
*   `@RequestMapping` is method-agnostic (you must specify `method = RequestMethod.GET`).
*   `@GetMapping` is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`.

#### **How can you run custom code at application startup?**
Use the **CommandLineRunner** or **ApplicationRunner** interfaces. By implementing these, you can override the `run()` method, which executes immediately after the application context is loaded.

#### **What is Spring Boot DevTools?**
It is a module that improves the development experience. Key features include:
*   **Automatic Restart:** Restarts the app whenever files on the classpath change.
*   **LiveReload:** Automatically refreshes the browser.

#### **How do you handle exceptions globally in Spring Boot?**
*   Use **@ControllerAdvice**: This allows you to write global code that applies to all controllers.
*   Combine it with **@ExceptionHandler**: To define how specific exceptions (like `ResourceNotFoundException`) should be handled and what status code should be returned.

#### **Can we change the banner in Spring Boot?**
Yes. By placing a `banner.txt` file in the `src/main/resources` folder, you can change the ASCII art displayed when the application starts.

---

### **9. Deployment**
*   Spring Boot applications are typically packaged as an **executable JAR**.
*   The JAR contains all the dependencies and the embedded server, making it highly portable for microservices and cloud deployments (Docker/Kubernetes).