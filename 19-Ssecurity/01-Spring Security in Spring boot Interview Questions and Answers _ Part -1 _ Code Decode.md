These detailed notes cover the core concepts of Spring Security as discussed in the "Spring Security in Spring Boot Interview Questions - Part 1" video by Code Decode.

### 1. Introduction to Spring Security
Spring Security is a powerful, customizable authentication and access-control framework. It is the de facto standard for securing Spring-based applications. It focuses on two main areas:
*   **Authentication:** Verifying the identity of a user (Who are you?).
*   **Authorization:** Deciding if a user has permission to access a specific resource (What are you allowed to do?).

### 2. Authentication vs. Authorization
This is the most fundamental interview question.
*   **Authentication:** The process of validating credentials (Username/Password, OTP, Biometrics). If successful, the application knows the identity of the user.
*   **Authorization:** This occurs after successful authentication. It checks the roles and privileges assigned to the user (e.g., an "ADMIN" can delete users, but a "USER" can only view their own profile).

### 3. How Spring Security Works (High-Level Architecture)
Spring Security is built entirely on **Servlet Filters**.
*   When an HTTP request is sent to a Spring Boot application, it passes through a series of filters before reaching the Controller (`DispatcherServlet`).
*   Spring Security inserts a "Chain of Filters" that intercepts the request to check for authentication and authorization.

### 4. Important Components in the Security Filter Chain
To understand how Spring Security integrates with a Servlet Container (like Tomcat), you must know these three components:

#### A. DelegatingFilterProxy
*   The Servlet Container (Tomcat) does not know about Spring Beans.
*   `DelegatingFilterProxy` is a standard Servlet Filter that acts as a bridge.
*   It sits in the Servlet Container but delegates the actual work to a Spring-managed bean called `FilterChainProxy`.

#### B. FilterChainProxy
*   This is a bean managed by Spring.
*   It acts as the central engine for Spring Security.
*   It holds a list of `SecurityFilterChain` objects and decides which security filters should be applied to a specific URL.

#### C. SecurityFilterChain
*   This is a collection of various filters (usually 15-16 by default) that perform specific tasks like CSRF protection, Logout handling, Username/Password verification, etc.

### 5. The Default Behavior of Spring Security
When you add the `spring-boot-starter-security` dependency to your project, Spring Boot’s auto-configuration sets up several things by default:
*   **All endpoints are protected:** Every URL in your application requires authentication.
*   **Default User:** A user with the name `user` is created.
*   **Default Password:** A random password is generated and printed in the console at startup.
*   **Login Form:** A default, auto-generated login page is provided at the `/login` URL.
*   **Logout:** A default logout page is provided at `/logout`.

### 6. Common Filters in the Chain
During an interview, you might be asked to name a few filters in the Security Filter Chain:
*   **UsernamePasswordAuthenticationFilter:** Extracts the username and password from the login form/request.
*   **BasicAuthenticationFilter:** Handles Basic Authentication headers.
*   **DefaultLoginPageGeneratingFilter:** Generates the default UI for login.
*   **ExceptionTranslationFilter:** Catches security exceptions (like Access Denied) and decides how to respond (e.g., redirecting to the login page).
*   **FilterSecurityInterceptor:** The final filter that checks if the user has the required "Role" to access the requested resource.

### 7. The SecurityContextHolder
*   Once a user is successfully authenticated, their details (the `Authentication` object) are stored in the **SecurityContextHolder**.
*   This is typically stored in a `ThreadLocal`, meaning the user's information is available throughout the entire lifecycle of that specific web request.

### 8. Summary of the Request Flow
1.  The request arrives at the **Servlet Container**.
2.  **DelegatingFilterProxy** intercepts it and hands it to **FilterChainProxy**.
3.  **FilterChainProxy** runs the request through the **SecurityFilterChain**.
4.  If authentication fails, the user is redirected to the login page.
5.  If authentication succeeds, the user's details are stored in the **SecurityContextHolder**, and the request proceeds to the **DispatcherServlet** (Controller).