These detailed notes cover the core concepts, technical definitions, and interview-specific explanations provided in the "Top Spring Interview Questions and Answers" video by Code Decode.

### 1. Introduction to Spring Framework
*   **Definition:** Spring is a lightweight, open-source framework used to develop enterprise-level Java applications.
*   **Key Advantage:** It provides a modular architecture, meaning you can use only the parts of Spring you need (e.g., Spring Core, Spring MVC, Spring Security).
*   **The Problem it Solves:** Before Spring, Enterprise JavaBeans (EJB) were heavy and difficult to test. Spring introduced "Plain Old Java Objects" (POJOs), making development simpler and testing easier.

### 2. Inversion of Control (IoC) and Dependency Injection (DI)
This is the most critical concept for any Spring interview.
*   **Inversion of Control (IoC):** It is a design principle where the control of creating and managing objects is transferred from the developer to the framework. Instead of the programmer using the `new` keyword to create an object, the Spring Container handles it.
*   **Dependency Injection (DI):** This is the pattern used to implement IoC. It is the process of "injecting" the dependent objects into a class rather than the class creating them itself.
*   **Benefits:** 
    *   Loose coupling between classes.
    *   Easier unit testing (you can easily mock dependencies).
    *   Code is cleaner and more maintainable.

### 3. The Spring IoC Container
The container is the "brain" of the Spring Framework. It manages the complete lifecycle of a bean from creation to destruction.
*   **Types of Containers:**
    1.  **BeanFactory:** The simplest container. It provides basic support for DI. It uses "Lazy Loading," meaning it only creates a bean when requested.
    2.  **ApplicationContext:** A more advanced container that inherits from BeanFactory. It adds enterprise-specific functionality like AOP, Internationalization, and Event Publication. It uses "Eager Loading," creating all singleton beans at startup.

### 4. Spring Bean Scopes
Interviewers often ask about the different ways a bean can be instantiated.
*   **Singleton (Default):** Only one instance of the bean is created per Spring IoC container. Every request for that bean returns the same object.
*   **Prototype:** A new instance of the bean is created every time it is requested from the container.
*   **Request:** (Web-aware) A new instance is created for every single HTTP request.
*   **Session:** (Web-aware) One instance is created for the lifecycle of an HTTP session.
*   **Global Session:** (Web-aware) Used primarily in Portlet applications.

### 5. Spring Bean Lifecycle
Understanding the lifecycle is vital for experienced candidates.
1.  **Instantiate:** The container finds the bean's definition and instantiates it.
2.  **Populate Properties:** Dependencies are injected (DI).
3.  **Aware Interfaces:** If the bean implements interfaces like `BeanNameAware` or `BeanFactoryAware`, Spring calls the relevant methods.
4.  **Pre-Initialization:** `BeanPostProcessor` methods are called.
5.  **Initialization:** The `@PostConstruct` method or the `init-method` specified in XML is executed.
6.  **Post-Initialization:** `BeanPostProcessor`'s `postProcessAfterInitialization` is called.
7.  **Ready:** The bean is ready for use by the application.
8.  **Destroy:** When the container shuts down, the `@PreDestroy` method or the `destroy-method` is called.

### 6. Autowiring in Spring
Autowiring allows the Spring container to automatically resolve and inject collaborating beans into your bean.
*   **Modes of Autowiring:**
    *   **no:** Default; no autowiring.
    *   **byName:** Injects the dependency based on the name of the property.
    *   **byType:** Injects the dependency if exactly one bean of that type exists in the container.
    *   **constructor:** Similar to byType but applies to constructor arguments.
*   **@Autowired Annotation:** Used to inject beans automatically. If multiple beans of the same type exist, `@Qualifier` is used to specify which one should be used.

### 7. Common Spring Annotations
*   **@Component:** Marks a Java class as a bean so the component-scanning mechanism of Spring can pick it up.
*   **@Repository:** A specialization of @Component for the Data Access Layer (DAO).
*   **@Service:** A specialization of @Component for the Service Layer (Business Logic).
*   **@Controller:** Used to mark a class as a Spring MVC controller.
*   **@Configuration:** Indicates that a class contains bean definition methods.
*   **@Bean:** Used on a method to indicate that it returns a bean to be managed by the Spring context.

### 8. Difference Between @Component and @Bean
*   **@Component:** Used on the class level. You use this when you have the source code for the class and want Spring to manage it automatically via component scanning.
*   **@Bean:** Used on the method level inside a `@Configuration` class. You use this when you want to wrap a third-party library or a class you don't own into a Spring bean, or when you need custom logic to create the bean.

### 9. Spring Boot vs. Spring Framework
*   **Spring:** Requires manual configuration (XML or Java Config), manual dependency management, and an external server (like Tomcat).
*   **Spring Boot:** An extension of Spring that eliminates boilerplate configuration.
    *   **Auto-configuration:** Automatically configures beans based on the dependencies in the classpath.
    *   **Starter POMs:** Simplified dependency management (e.g., `spring-boot-starter-web`).
    *   **Embedded Servers:** Comes with built-in servers (Tomcat/Jetty), so you don't need to install one separately.

### 10. Practical Interview Tips
*   **Explain the "Why":** Don't just define DI; explain that it makes the code easier to maintain and test.
*   **Be Specific about Scopes:** If asked about Singleton, clarify that it is "per container," not "per JVM."
*   **Lifecycle details:** For experienced roles, specifically mention `BeanPostProcessor`, as it shows a deeper understanding of the framework's internal workings.