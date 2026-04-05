These notes are based on the **Code Decode** interview preparation video for Spring professionals with 5+ years of experience. The content focuses on architectural decisions, deep-dive container mechanics, and common production-level challenges.

---

### 1. Spring Bean Lifecycle (Deep Dive)
For experienced roles, simply naming the stages isn't enough. You must explain the "why" and "how."

*   **Instantiating:** The container finds the bean definition and creates the instance.
*   **Populating Properties:** Dependency Injection happens here.
*   **Aware Interfaces:** The bean is made "aware" of its environment (e.g., `BeanNameAware`, `BeanFactoryAware`, `ApplicationContextAware`).
*   **BeanPostProcessor (Before Initialization):** The `postProcessBeforeInitialization()` method is called.
*   **Initialization:** 
    *   `@PostConstruct` annotation.
    *   `afterPropertiesSet()` (from `InitializingBean` interface).
    *   Custom `init-method`.
*   **BeanPostProcessor (After Initialization):** The `postProcessAfterInitialization()` method is called (this is where AOP proxies are usually created).
*   **Ready for Use:** The bean is ready in the container.
*   **Destruction:**
    *   `@PreDestroy` annotation.
    *   `destroy()` (from `DisposableBean` interface).
    *   Custom `destroy-method`.

### 2. Bean Scopes and the "Prototype in Singleton" Problem
*   **Singleton (Default):** One instance per Spring IoC container.
*   **Prototype:** A new instance every time it is requested.
*   **Web Scopes:** Request, Session, Application, and WebSocket.
*   **The Problem:** If you inject a Prototype bean into a Singleton bean, the Prototype bean is only injected **once** (when the Singleton is created). It will not behave as a Prototype thereafter.
*   **The Solutions:**
    1.  **Lookup Method Injection:** Use the `@Lookup` annotation on a method that returns the Prototype bean.
    2.  **Scoped Proxy:** Configure the Prototype bean with `proxyMode = ScopedProxyMode.TARGET_CLASS`.
    3.  **ApplicationContext Awareness:** Manually call `context.getBean()` (Note: This couples the code to the Spring Framework and is generally discouraged).

### 3. Dependency Injection: Constructor vs. Setter
For 5+ years experience, you should favor **Constructor Injection**.
*   **Constructor Injection:** 
    *   Ensures mandatory dependencies are not null.
    *   Promotes immutability (fields can be `final`).
    *   Prevents circular dependencies at compile/startup time.
*   **Setter Injection:** 
    *   Better for optional dependencies.
    *   Allows reconfiguration of the bean at runtime.
*   **Field Injection:** Generally discouraged because it makes unit testing difficult (requires reflection) and hides dependencies.

### 4. Circular Dependencies
*   **Definition:** Bean A depends on Bean B, and Bean B depends on Bean A.
*   **The Result:** Spring throws `BeanCurrentlyInCreationException`.
*   **Solutions:**
    *   **Refactoring:** Best approach; extract shared logic to a third bean.
    *   **@Lazy:** Use `@Lazy` on one of the dependencies so it is only initialized when first used, breaking the cycle during startup.
    *   **Setter Injection:** Use setter injection instead of constructor injection. Spring can create the objects first and then populate the dependencies.

### 5. BeanFactory vs. ApplicationContext
*   **BeanFactory:** The simplest container. It provides basic DI support and uses **Lazy Loading** (beans are created only when `getBean()` is called). Best for memory-constrained environments.
*   **ApplicationContext:** Inherits `BeanFactory`. It uses **Eager Loading** (creates all singletons at startup).
    *   **Extra Features:** MessageSource (i18n), Event Publication, Application-layer specific contexts (like WebApplicationContext), and easy integration with AOP.

### 6. @Component vs. @Configuration
*   **@Component:** Used for general-purpose beans. When you call a method inside a `@Component` class to get another bean, it acts like a standard Java call (it might create a new instance).
*   **@Configuration:** Used for bean definitions. Classes marked with `@Configuration` are proxied via CGLIB. If you call a `@Bean` method from another `@Bean` method within the same class, the proxy intercepts the call and returns the existing Singleton instance rather than creating a new one.

### 7. @Autowired vs. @Resource vs. @Inject
*   **@Autowired:** Spring-specific. Matches by **Type**. Use `@Qualifier` to resolve conflicts if multiple beans of the same type exist.
*   **@Resource:** Standard Java (JSR-250). Matches by **Name** first, then by Type.
*   **@Inject:** Standard Java (JSR-330). Matches by **Type**. Similar to `@Autowired` but lacks the `required=false` attribute.

### 8. Singleton Scope vs. Singleton Design Pattern
*   **Design Pattern:** The class guarantees only one instance exists per ClassLoader. The constructor is private.
*   **Spring Singleton:** The container guarantees only one instance exists **per container**. The class itself is a regular POJO with a public constructor; Spring simply manages its lifecycle.

### 9. Thread Safety in Spring Beans
*   By default, Spring beans are **not thread-safe**.
*   Since most beans are Singletons, they are shared across all threads (e.g., HTTP requests in a web app).
*   **Solution:** Avoid using instance variables that hold state (stateful beans). If you must use state, use `ThreadLocal` or synchronize blocks, though the latter can hurt performance.

### 10. BeanPostProcessor (BPP)
*   BPP is a powerful interface that allows you to modify new bean instances before they are initialized or after.
*   **Use Case:** It is the backbone of Spring’s AOP. When Spring sees a bean needs to be wrapped in a transaction or logged, the BPP returns a **Proxy** of the bean instead of the bean itself.