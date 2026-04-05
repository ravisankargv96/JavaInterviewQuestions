These notes cover the practical implementation of Rest APIs, specifically focusing on API documentation and the use of **Swagger** in a Spring Boot environment, as discussed in the Code Decode tutorial.

---

### **1. Scenario: Sharing API Contracts**
A common interview question asks: *"How do you share your REST API contract with the front-end team or another microservice?"*

*   **The Inefficient Way (Manual URL):** Sending a raw URL (e.g., `http://localhost:8080/code/all`).
    *   **Problem:** It doesn't specify the HTTP verb (GET, POST, etc.), required headers (Authorization, API keys), or the request body structure.
*   **The Better Way (CURL):** Sharing the "CURL" command from tools like Postman. 
    *   **Pros:** Includes headers, the verb, and the body.
    *   **Cons:** Hard to maintain. If the API changes, you must manually update and re-send the CURL to all stakeholders.
*   **The Best Practice (Swagger):** Implementing an automated documentation tool.

---

### **2. What is Swagger?**
Swagger is a toolset for API design and documentation. 
*   **Purpose:** It automatically generates and maintains documentation as your code evolves.
*   **Key Benefit:** It ensures documentation stays up-to-date. If you add a field to an endpoint, Swagger updates the documentation the moment the server restarts.
*   **Backward Compatibility:** Swagger allows you to maintain and document multiple versions of your API (e.g., v1 and v2) simultaneously.

---

### **3. Implementing Swagger in Spring Boot (3 Steps)**

#### **Step 1: Add Maven Dependencies**
You need two primary dependencies in your `pom.xml`:
1.  **`springfox-swagger2`**: The backend logic that scans your code and generates the documentation.
2.  **`springfox-swagger-ui`**: Provides the visual interface (HTML page) to view and interact with the APIs.

#### **Step 2: Create a Configuration Class**
Create a class (e.g., `SwaggerConfig`) and use the following annotations:
*   **`@Configuration`**: Makes it a Spring bean.
*   **`@EnableSwagger2`**: Enables the Swagger support provided by SpringFox.

#### **Step 3: Define the `Docket` Bean**
The `Docket` is the primary configuration object for Swagger. It is a builder that allows you to customize how your documentation is generated.

```java
@Bean
public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("public-apis") // Groups APIs for easier management
            .apiInfo(getApiInfo())    // Customizes title, version, etc.
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) // Filters what to show
            .build();
}
```

---

### **4. Customizing the Documentation**

#### **API Information (`ApiInfo`)**
By default, Swagger provides generic info. Use `ApiInfoBuilder` to customize:
*   **Title:** e.g., "Code Decode APIs"
*   **Description:** A summary of what the APIs do.
*   **Version:** e.g., "1.0"
*   **License/Contact:** Legal and support info.

#### **Grouping APIs**
The `.groupName()` method allows you to categorize APIs. This is useful for large projects where you might want to separate "Admin APIs," "Business APIs," and "Public APIs" into a dropdown menu in the UI.

---

### **5. Troubleshooting: The "Basic Error Controller" Issue**
By default, Swagger scans all controllers, including those built into Spring Boot (like the `Basic Error Controller`). To remove these and only show your custom APIs:

*   **Option A (Base Package):** Use `.apis(RequestHandlerSelectors.basePackage("your.package.name"))`.
*   **Option B (Annotation - Recommended):** Use `.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))`. This ensures only classes marked with `@RestController` appear in the documentation, which is a cleaner, more flexible approach.

---

### **6. Swagger UI Features**
Once configured, you can access the documentation at: `http://localhost:8080/swagger-ui.html`

*   **Visualization:** View all endpoints, expected input models, and response formats.
*   **Abstraction:** Front-end teams can understand the API without seeing the backend logic.
*   **"Try It Out":** This feature acts like a built-in Postman. You can execute requests directly from the browser, view real-time response codes (200, 404, 500), and see response headers/bodies.

---

### **Summary of Annotations & Classes**
*   **`@EnableSwagger2`**: Activates Swagger 2.
*   **`Docket`**: The main configuration bean.
*   **`RequestHandlerSelectors`**: Used to filter which controllers are documented.
*   **`ApiInfoBuilder`**: Used to set the metadata for the documentation page.