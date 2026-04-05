These notes provide a detailed breakdown of the differences between the Spring Framework and Spring Boot, as discussed in the Code Decode video.

---

### **1. Introduction: The Evolution**
*   **Spring Framework:** An older, lightweight, open-source framework widely used for developing Enterprise-level applications.
*   **Spring Boot:** A relatively newer framework built **on top** of the conventional Spring framework. It is specifically designed to simplify the creation of REST APIs and CRUD applications.

---

### **2. Core Philosophy: Why They Exist**
#### **The Purpose of Spring (Solving Coupling)**
Before Spring, developers struggled with **tightly coupled objects**. 
*   **The Problem:** Using the `new` keyword (e.g., `Address add = new Address()`) inside an Employee class created a hard dependency. If the Employee was garbage collected, the Address was too.
*   **The Solution:** Spring introduced **Dependency Injection (DI)**. Instead of the developer creating objects, the **Spring Container** manages the creation and lifecycle of objects. You simply "ask" for an object using `@Autowired`.

#### **The Purpose of Spring Boot (Solving Speed)**
While Spring solved architectural issues, it was still complex to set up.
*   **The Problem:** Spring required heavy configuration and manual management of JAR files.
*   **The Solution:** Spring Boot follows the **Rapid Application Development (RAD)** model. It aims to reduce "time to market" by allowing developers to set up a functional module in days rather than weeks.

---

### **3. Server Configuration**
*   **Spring:** Developers must explicitly configure servers (e.g., IBM WebSphere or Apache Tomcat) and deploy the application to them manually.
*   **Spring Boot:** Comes with **Embedded Servers**. 
    *   **Tomcat** is the default embedded server.
    *   Other servers like **Jetty** can be used by simply excluding the Tomcat dependency and including the Jetty dependency in the configuration.

---

### **4. Boilerplate Code and Starters**
*   **Spring:** Requires writing significant "boilerplate code" (repetitive code required for basic functionality).
*   **Spring Boot:** Drastically reduces boilerplate code through **Starter Dependencies**. 
    *   Common tasks and libraries are compressed into "starter" packages. 
    *   By adding one dependency, you get access to all the necessary JARs for that feature (e.g., Web, Data JPA, Security).

---

### **5. Dependency Management**
*   **Spring:** You have to manually add and manage dozens of individual JAR files in the build path. Forgetting one JAR leads to errors and project delays.
*   **Spring Boot:** Uses **Starter Dependencies** in the `pom.xml`.
    *   **Example (Web):** Adding `spring-boot-starter-web` automatically pulls in all JARs required for web development.
    *   **Example (Security):** Adding `spring-boot-starter-security` automatically configures basic security, providing a default login page and protecting your application with just one line of configuration.

---

### **6. In-Memory Database (H2)**
Spring Boot provides support for in-memory databases like **H2**, which Spring does not offer by default.
*   **How it works:** Data is stored in the **System Memory (RAM)** rather than on the hard disk.
*   **Benefits:**
    *   **Speed:** Accessing RAM is much faster than accessing a physical disk.
    *   **Volatile Storage:** Data is lost as soon as the application restarts.
*   **Use Case:** Ideal for testing or temporary data storage where you don't need to persist data permanently (unlike MySQL or Oracle which store data on the disk).

---

### **7. Configuration: XML vs. Annotations**
*   **Spring:** Historically relies heavily on **XML configurations** to define beans and application behavior.
*   **Spring Boot:** Focuses on eliminating XML. It uses annotations and auto-configuration to get the application running with minimal setup.

---

### **8. Build Plugins**
*   **Spring:** Does not inherently provide plugins to build the application; you must set up Maven or Gradle independently.
*   **Spring Boot:** Provides powerful **build plugins** in the `pom.xml`. These plugins allow you to create **executable JARs** (which contain all dependencies and the embedded server) and offer a variety of management features.

---

### **Summary Table: Spring vs. Spring Boot**

| Feature | Spring Framework | Spring Boot |
| :--- | :--- | :--- |
| **Primary Goal** | Loosely coupled apps via DI | Rapid Application Development (RAD) |
| **Server** | Must be configured externally | Embedded (Tomcat/Jetty) |
| **Configuration** | Heavy XML configuration | Annotation-based / Auto-config |
| **Dependencies** | Manual JAR management | Starter Dependencies (Packages) |
| **Database** | No built-in in-memory DB | Built-in support for H2 |
| **Boilerplate** | High amount of repetitive code | Very low due to auto-configuration |
| **Plugins** | None for building/packaging | Maven/Gradle build plugins included |