These detailed notes are based on the "Code Decode" video regarding Spring Boot interview questions tailored for candidates with 7+ years of experience.

---

### 1. Embedded Containers in Spring Boot
For senior roles, it is insufficient to simply state that Spring Boot "comes with Tomcat." You must understand the **architectural "why."**

*   **Definition:** An embedded container is a lightweight server (Tomcat, Jetty, or Undertow) integrated directly into the application JAR.
*   **Importance:**
    *   **Standalone Applications:** It eliminates the need to install and configure external application servers (like WebSphere or standalone Tomcat).
    *   **Simplified Deployment:** You can develop, run, and test the application instantly.
    *   **Default Configuration:** It provides a pre-configured, lightweight environment, reducing the "boilerplate" server setup.
    *   **Hot Reloading Support:** Embedded containers facilitate the immediate reflection of code changes without manual redeploys.

---

### 2. Hot Reloading & Spring Boot DevTools
Hot reloading is the ability to update the running application context with code changes without a full restart.

*   **Internal Mechanism:** 
    *   **Monitoring:** DevTools continuously monitors the class path (Java classes, property files, static resources).
    *   **Detection:** It identifies if a file was added, modified, or deleted.
    *   **Dynamic Update:** It reloads only the modified parts.
*   **State Preservation:** DevTools attempts to keep the application state intact (e.g., keeping the HTTP session alive and database connections open) while refreshing the application context.
*   **Implementation:** Spring Boot does **not** support hot reloading natively; it requires the `spring-boot-devtools` dependency.
*   **Hot Reloading vs. Hot Redeployment:** 
    *   **Reloading:** Recompiles and updates only the modified part.
    *   **Redeployment:** Recompiles and restarts the entire application (slower).

---

### 3. Implementing DevTools in IntelliJ IDEA
While DevTools works almost automatically in Eclipse/STS, **IntelliJ requires manual configuration:**

1.  **Add Dependency:** Include `spring-boot-devtools` in your `pom.xml`.
2.  **Compiler Setting:** Go to `Settings` -> `Build, Execution, Deployment` -> `Compiler` -> Check **"Build project automatically"**.
3.  **Advanced Settings:** Search for "Allow auto-make" and check **"Allow auto-make to start even if developed application is currently running"**.
    *   *Note:* In older IntelliJ versions, this was found in the "Registry" under `compiler.automake.allow.when.app.running`.
4.  **Verification:** Check the console logs for "LiveReload server is running on port..." and verify that `System.out` changes appear without manual restarts.

---

### 4. Limitations of Embedded Tomcat
There are specific scenarios where an embedded container is **not** ideal:

*   **Extremely High Traffic:** For massive scale (e.g., Flipkart/Amazon sales), a standalone, highly-tuned server cluster may be preferred.
*   **Complex Customization:** If the project requires deep, low-level server-side configuration that the lightweight embedded version doesn't expose.
*   **Heavyweight Applications:** For very large monolithic apps, separating the code from the server might be better for resource management.
*   **Specialized Protocols:** If you require advanced protocols or specific WebSocket versions not supported by the default lightweight Tomcat.

---

### 5. Externalizing Configuration
Senior developers should focus on the **Separation of Concerns**.

*   **Concept:** Keeping the codebase separate from environment-specific values (DB credentials, API keys).
*   **Methods of Externalization:**
    1.  **Properties/YAML Files:** Located in `src/main/resources`.
    2.  **Environment Variables:** Configured at the OS or Container level.
    3.  **VM Arguments:** Using the `-D` flag (e.g., `-Dserver.port=8099`).
    4.  **Spring Config Location:** You can point to a secure file outside the project folder using the `spring.config.location` property.
*   **Benefits:**
    *   **Security:** Prevents sensitive keys from being hardcoded in the source code.
    *   **Flexibility:** Use different databases for Dev, QA, and Prod without changing code.
    *   **Ease of Deployment:** Change a property and restart without recompiling the whole JAR.

---

### 6. YAML vs. Properties Files
Spring Boot supports both `application.properties` and `application.yml`.

*   **Why YAML is Preferred:**
    *   **Hierarchical Structure:** Better for nested properties (e.g., `spring.datasource.url`).
    *   **Readability:** Reduces repetitive prefixes.
    *   **Data Types:** Better support for lists, maps, and integers.
*   **Precedence Order (Highest to Lowest):**
    1.  **Command Line / VM Arguments** (Overrides everything).
    2.  **Environment Variables**.
    3.  **Profile-specific YAML/Properties** (e.g., `application-dev.yml`).
    4.  **Default YAML** (`application.yml`).
    5.  **Default Properties** (`application.properties`).
*   *Key Takeaway:* If a key exists in both YAML and Properties, **YAML takes precedence** in Spring Boot.

---

### 7. Hibernate as Default JPA Implementation
Interviewers often ask why Hibernate works even if you don't explicitly add a "Hibernate" dependency.

*   **Transitive Dependencies:** When you add `spring-boot-starter-data-jpa`, Spring Boot's dependency management automatically pulls in `hibernate-core`.
*   **Auto-Configuration:** Spring Boot uses "Conditional" annotations. It detects Hibernate on the classpath and automatically configures it as the implementation for the JPA specification.
*   **Transparency:** This allows developers to use JPA annotations (`@Entity`, `@Table`) while Hibernate performs the actual ORM work in the background.