These notes cover the essential interview questions and architectural concepts discussed in the video regarding Spring Security 6, specifically tailored for experienced Java/Spring developers.

---

### 1. Introduction to Spring Security 6
Spring Security is a powerful, customizable authentication and access-control framework. In version 6 (aligned with Spring Boot 3), there are significant architectural shifts designed to move toward a more "component-based" configuration rather than an "inheritance-based" one.

### 2. Major Changes in Spring Security 6 vs. 5
This is a frequent interview question for experienced developers.
*   **Removal of `WebSecurityConfigurerAdapter`**: In previous versions, you extended this class. In Spring Security 6, it is completely removed. You now define a `SecurityFilterChain` bean.
*   **Lambda DSL**: Configuration now uses Lambda expressions for better readability and to avoid method chaining issues.
*   **Method Security**: `@EnableGlobalMethodSecurity` is deprecated in favor of `@EnableMethodSecurity`.
*   **Authorize Requests**: `authorizeRequests()` is replaced by `authorizeHttpRequests()`.
*   **Standardization**: Greater alignment with Jakarta EE (changing `javax.*` to `jakarta.*` packages).

### 3. The Spring Security Filter Chain
The heart of Spring Security is a chain of Servlet Filters.
*   **DelegatingFilterProxy**: A standard Servlet Filter that bridges the Servlet container’s lifecycle with Spring’s ApplicationContext.
*   **FilterChainProxy**: A specialized filter that contains the list of `SecurityFilterChain` instances.
*   **SecurityFilterChain**: Determines which filters should be applied to a specific request.

**Key Filters in the Chain:**
1.  `UsernamePasswordAuthenticationFilter`: For form-based login.
2.  `BasicAuthenticationFilter`: For Basic Auth headers.
3.  `DefaultLoginPageGeneratingFilter`: Generates the default login page.
4.  `FilterSecurityInterceptor`: Handles the final authorization check.

### 4. Authentication Architecture
The video outlines the step-by-step flow of how a user is authenticated:

1.  **Authentication Filter**: Intercepts the request and extracts credentials (username/password).
2.  **AuthenticationToken**: The credentials are wrapped into an `Authentication` object (like `UsernamePasswordAuthenticationToken`).
3.  **AuthenticationManager**: The main entry point. It usually delegates the task to one or more `AuthenticationProvider`s.
4.  **AuthenticationProvider**: Contains the logic to validate credentials. It uses:
    *   **UserDetailsService**: To load user data from a database or memory.
    *   **PasswordEncoder**: To compare the provided password with the stored hash.
5.  **SecurityContextHolder**: Once authenticated, the `Authentication` object is stored here for the duration of the request.

### 5. Authorization (RBAC)
Authorization happens after authentication.
*   **Role-Based Access Control (RBAC)**: Assigning permissions to roles (e.g., `ROLE_ADMIN`, `ROLE_USER`).
*   **Authority vs. Role**: An Authority is a fine-grained permission (e.g., `READ_PRIVILEGE`), while a Role is a collection of authorities.
*   **Method Level Security**: Using `@PreAuthorize("hasRole('ADMIN')")` or `@PostAuthorize` to secure specific service methods.

### 6. UserDetailsService vs. UserDetailsManager
*   **UserDetailsService**: A read-only interface with one method: `loadUserByUsername(String username)`.
*   **UserDetailsManager**: Extends `UserDetailsService` and adds methods to create, update, and delete users (`createUser`, `updateUser`, etc.). Examples include `InMemoryUserDetailsManager` and `JdbcUserDetailsManager`.

### 7. Password Management
*   **PasswordEncoder**: An interface for one-way transformation of passwords.
*   **BCryptPasswordEncoder**: The recommended implementation. It uses a BCrypt strong hashing function with a random salt.
*   **DelegatingPasswordEncoder**: Allows the application to support multiple encoding formats simultaneously (useful for legacy migrations).

### 8. Handling CSRF (Cross-Site Request Forgery)
*   Spring Security enables CSRF protection by default.
*   **Mechanism**: It generates a unique token for the session. Any state-changing request (POST, PUT, DELETE) must include this token.
*   **Spring Security 6 Change**: The way CSRF tokens are handled in Single Page Applications (SPAs) has changed; by default, the token is now deferred until it is actually needed, improving performance.

### 9. CORS (Cross-Origin Resource Sharing)
*   CORS is a browser security feature.
*   In Spring Security 6, you configure CORS via the `cors()` method inside the `SecurityFilterChain` bean. It must be configured *before* the security filters to ensure pre-flight requests (OPTIONS) are handled correctly.

### 10. Practical Code Example: Defining the SecurityFilterChain
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Only for testing/stateless APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
```

### 11. Top Interview Summary Tips
*   **Explain the "Why"**: Don't just say `WebSecurityConfigurerAdapter` is gone; explain that the community moved toward a component-based model for better testability and flexibility.
*   **Understand the Filter Order**: Knowing that `UsernamePasswordAuthenticationFilter` comes before `FilterSecurityInterceptor` shows deep architectural knowledge.
*   **Stateless vs. Stateful**: Be prepared to explain how Security differs between a standard MVC app (Sessions) and a Microservice (JWT/OAuth2).