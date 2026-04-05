These detailed notes are based on the "Spring Interview Question and Answers Part 2" video by Code Decode.

---

### **1. Overview of the Spring IoC Container**
The Inversion of Control (IoC) container is the core of the Spring Framework. It is responsible for instantiating, configuring, and assembling objects known as **Beans**.

*   **The Workflow:** 
    1.  You provide **POJOs** (Plain Old Java Objects).
    2.  You provide **Configuration Metadata**.
    3.  The **IoC Container** consumes these to produce a fully configured system of Beans (e.g., a `Car` bean with an `Engine` bean already injected).

---

### **2. Configuration Metadata**
Metadata tells the Spring Container how to instantiate, configure, and assemble the objects in your application. There are three primary ways to provide this:

#### **A. XML-Based Configuration**
*   **How it works:** Definitions are managed in an external XML file (e.g., `applicationContext.xml`).
*   **Pros:** High readability/performance because all definitions are in one place.
*   **Cons:** High redundancy; you must switch between Java code and XML files constantly.

#### **B. Annotation-Based Configuration**
*   **How it works:** Uses annotations directly on classes (e.g., `@Component`, `@Autowired`, `@Service`, `@Repository`).
*   **Pros:** Saves time, reduces redundancy, and keeps configuration close to the code.
*   **Cons:** Lower performance in very large applications because the container must scan every package (Component Scanning) to find annotations.

#### **C. Java-Based Configuration**
*   **How it works:** Uses Java classes annotated with `@Configuration` and methods with `@Bean`.
*   **Pros/Cons:** Similar to XML in terms of centralization but written in type-safe Java.

---

### **3. Types of IoC Containers**
There are two main types of containers in Spring: **BeanFactory** and **ApplicationContext**.

#### **BeanFactory**
*   **Basic Container:** It provides the basic object-pooling and DI features.
*   **Lazy Loading:** It instantiates beans only when the `getBean()` method is called. It does not occupy memory for beans until they are specifically requested.
*   **Usage:** Used in environments where resources are very constrained (e.g., mobile devices or lightweight applets).
*   **Limitation:** Does not support annotation-based dependency injection or internationalization.

#### **ApplicationContext**
*   **Advanced Container:** It extends `BeanFactory` and adds enterprise-specific functionality.
*   **Eager Loading:** It instantiates all singleton beans at the time of container startup. This allows errors (like missing properties) to be caught immediately during deployment.
*   **Features:** Supports internationalization (i18n), event publication, and is the preferred choice for most Spring applications.
*   **Implementation Example:** `ClassPathXmlApplicationContext` or `AnnotationConfigApplicationContext`.

---

### **4. Dependency Injection (DI) Methods**
Dependency Injection is the process where the container "injects" one bean into another. The video demonstrates two primary methods via XML:

#### **A. Setter Injection**
*   **XML Tag:** `<property name="engine" ref="engineBean" />`
*   **Requirement:** The target class must have a public **setter method** (e.g., `setEngine(Engine e)`).
*   **Behavior:** Spring creates the object using the no-argument constructor and then calls the setter to inject the dependency.

#### **B. Constructor Injection**
*   **XML Tag:** `<constructor-arg ref="engineBean" />`
*   **Requirement:** The target class must have a **constructor** that accepts the dependency as a parameter.
*   **Behavior:** The dependency is injected at the time of object creation.

---

### **5. Key Comparison: BeanFactory vs. ApplicationContext**

| Feature | BeanFactory | ApplicationContext |
| :--- | :--- | :--- |
| **Loading Strategy** | Lazy Loading (on request) | Eager Loading (at startup) |
| **Annotations** | Does not support `@Autowired` | Full support for annotations |
| **Internationalization** | Not supported | Supported |
| **Performance** | Lightweight | Heavier due to extra features |
| **Usage** | Rarely used (Legacy/Mobile) | Standard for all Spring apps |

---

### **6. Technical Implementation Details (Live Demo Summary)**
*   **Converting POJO to Bean:** To turn a simple Java class into a Spring bean without annotations, you define it in XML:
    ```xml
    <bean id="carbBean" class="com.package.Car">
        <!-- Dependency Injection via Setter -->
        <property name="engine" ref="engineBean" />
    </bean>
    <bean id="engineBean" class="com.package.Engine" />
    ```
*   **Common Exceptions:**
    *   `NoSuchBeanDefinitionException`: Occurs if you try to fetch a bean that hasn't been defined in XML or via annotations.
    *   `InvalidPropertyException`: Occurs during Setter Injection if the setter method is missing in the Java class.

---

### **7. Summary of Interview Tips**
*   Understand that `ApplicationContext` is an interface that **extends** `BeanFactory`.
*   Be ready to explain why **Annotation-based** configuration is faster to develop but might have a **performance hit** during startup due to package scanning.
*   Know the difference between **Lazy** and **Eager** loading, as this is a frequent "fresher" interview question.