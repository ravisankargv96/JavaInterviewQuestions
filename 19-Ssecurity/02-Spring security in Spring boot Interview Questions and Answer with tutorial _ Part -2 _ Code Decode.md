These detailed notes are based on the **Code Decode** tutorial regarding Spring Security customization in Spring Boot.

---

### **1. Default Authentication in Spring Security**
By adding the `spring-boot-starter-security` dependency, Spring Boot automatically secures all endpoints.
*   **Default Behavior:** It generates a random password in the console logs and uses "user" as the default username.
*   **Mechanism:** This is handled by the **Authentication Manager** using Basic Authentication.
*   **The Problem:** In real-world applications, you cannot rely on a single auto-generated password or a single hardcoded user.

---

### **2. Customizing Security via `application.properties`**
The simplest way to override the default credentials is through the properties file.
*   **Properties to use:**
    *   `spring.security.user.name=YourUsername`
    *   `spring.security.user.password=YourPassword`
*   **Pros:** Quick and easy; prevents the console from logging a random password.
*   **Cons:** Not suitable for production; only supports a single user.

---

### **3. Java-Based Custom Authentication Configuration**
To handle multiple users and custom logic, you must create a configuration class.

#### **Key Requirements:**
1.  **Class Annotation:** Annotate the class with `@EnableWebSecurity`.
2.  **Inheritance:** Extend the `WebSecurityConfigurerAdapter` class.
3.  **Override `configure(AuthenticationManagerBuilder auth)`:** This method is used to define how users are authenticated.

#### **In-Memory Authentication Example:**
Using the **Builder Design Pattern**, you can define users directly in the code:
```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user").password("pass").roles("USER")
        .and()
        .withUser("admin").password("admin").roles("ADMIN");
}
```

---

### **4. The Role of Password Encoders**
If you try to use plain-text passwords in your configuration, Spring Security will throw an error: **"There is no PasswordEncoder mapped for id null."**

*   **Why?** Spring Security requires passwords to be hashed (encoded) for security reasons.
*   **The Fix (Development/Demo only):** Create a `@Bean` for `NoOpPasswordEncoder`. Note that this is deprecated and should **never** be used in production.
*   **Code Snippet:**
    ```java
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    ```

---

### **5. Customizing Authorization (Access Control)**
Authorization determines which roles can access specific URLs. This is configured by overriding a different version of the `configure` method.

#### **Override `configure(HttpSecurity http)`:**
This method allows you to define URL patterns and restrict them based on roles.

*   **Ant Matchers:** Used to define the URL pattern to match.
*   **Role Restriction:** Use `hasRole()` to specify which role is required.
*   **Permit All:** Use `permitAll()` for endpoints that should be public (like a home page).

#### **Example Configuration:**
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/code/admin").hasRole("ADMIN")
        .antMatchers("/code/user").hasRole("USER")
        .antMatchers("/").permitAll() // Allow everyone to see the home page
        .and().formLogin(); // Use standard form-based login
}
```

---

### **6. Important Interview Concepts**

#### **A. 403 Forbidden vs. 401 Unauthorized**
*   **401 Unauthorized:** The system doesn't know who you are (Authentication failed).
*   **403 Forbidden:** The system knows who you are (e.g., you are logged in as "User"), but you do not have the required permissions/roles to access the "Admin" page (Authorization failed).

#### **B. The Order of Ant Matchers**
*   **Crucial Rule:** Always order your matchers from **Most Restrictive to Least Restrictive**.
*   If you put `permitAll()` for a broad pattern (like `/**`) at the top, Spring will allow access to everything below it without checking roles, effectively breaking your security.

#### **C. Authentication Manager Builder**
*   This uses the **Builder Pattern** to construct the Authentication Manager. It allows you to pipeline different authentication types (In-Memory, JDBC, LDAP, etc.) using the `.and()` operator.

---

### **Summary of Configuration Steps**
1.  Extend `WebSecurityConfigurerAdapter`.
2.  Use `AuthenticationManagerBuilder` to define users, passwords, and roles.
3.  Define a `PasswordEncoder` bean to handle password hashing.
4.  Use `HttpSecurity` to map URL patterns (Ant Matchers) to specific roles.
5.  Set up the login type (e.g., `formLogin()`).