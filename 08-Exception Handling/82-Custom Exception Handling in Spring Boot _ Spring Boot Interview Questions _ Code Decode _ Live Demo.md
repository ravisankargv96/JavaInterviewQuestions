These notes provide a comprehensive overview of custom exception handling in Spring Boot based on the "Code Decode" tutorial.

### 1. Overview of Exception Handling in Spring Boot
In a standard Spring Boot application, if an error occurs (e.g., a resource is not found or an input is null), the framework returns a default "WhiteLabel Error Page" or a generic JSON response. These are often unhelpful for the client and can leak internal stack traces.

**Custom Exception Handling** allows developers to:
*   Interpose a specific logic when an error occurs.
*   Return a structured, user-friendly JSON response.
*   Provide appropriate HTTP status codes (e.g., 400 Bad Request instead of 500 Internal Server Error).

---

### 2. Core Annotations
Two primary annotations are used to manage exceptions globally:

*   **@ControllerAdvice:** This is a specialization of `@Component`. It acts as an interceptor that "surrounds" all controllers in the application. It allows you to handle exceptions across the whole application in one global place.
*   **@ExceptionHandler:** This annotation is used within the `@ControllerAdvice` class on specific methods. It defines which exception type the method should handle.

---

### 3. The Implementation Workflow
To implement custom exception handling, follow these five steps:

#### Step 1: Create a Custom Exception Class
Create a class that extends `RuntimeException`. It is better to extend `RuntimeException` than `Exception` because it is "unchecked," meaning you don't have to add `throws` clauses to every method signature.
*   *Example:* `class EmptyInputException extends RuntimeException { ... }`

#### Step 2: Create a Custom Error Response POJO
Instead of returning a simple string, create a class to represent the error structure. This typically includes:
*   Error Code (custom string or integer).
*   Error Message.
*   Timestamp.

#### Step 3: Create the Global Exception Handler
Create a class and annotate it with `@ControllerAdvice`. Inside this class, create methods for each exception you want to catch.
*   Annotate the method with `@ExceptionHandler(YourCustomException.class)`.
*   Return a `ResponseEntity<ErrorResponseObject>`.

#### Step 4: Throw the Exception from the Service/Controller
In your business logic (usually the Service layer), perform validation. If validation fails, use the `throw` keyword to trigger your custom exception.
*   *Example:* `if(name.isEmpty()) { throw new EmptyInputException("601", "Input name is empty"); }`

#### Step 5: Map HTTP Status Codes
Use the `HttpStatus` enum to ensure the client receives the correct status (e.g., `HttpStatus.BAD_REQUEST` or `HttpStatus.NOT_FOUND`).

---

### 4. Comparison: Local vs. Global Handling

*   **Local Handling:** Using `try-catch` blocks inside every controller method. This leads to code duplication and makes the controller messy.
*   **Global Handling (@ControllerAdvice):** Centralizes error logic. If you want to change the error format, you only change it in one class. This keeps business logic clean and separated from error-handling logic.

---

### 5. Practical Example Scenario (The "Employee" Use Case)
The video demonstrates an Employee Management System:
1.  **Scenario:** A user tries to save an employee with an empty name.
2.  **Service Layer:** Checks if the name is null or blank.
3.  **Throw:** The Service throws `BusinessException`.
4.  **Catch:** The Global Exception Handler catches `BusinessException`.
5.  **Response:** The client receives a JSON object: 
    *   `{"errorCode": "601", "errorMessage": "Please send a proper name", "date": "2023-10-01..."}`
    *   The HTTP Status is set to 400 (Bad Request).

---

### 6. Key Interview Questions Addressed
*   **What is the use of @ControllerAdvice?** It is used for global exception handling and data binding.
*   **Why extend RuntimeException instead of Exception?** To avoid "checked exception" overhead where every method must declare the exception.
*   **How do you return a custom HTTP status code?** By returning a `ResponseEntity` object and passing the `HttpStatus` in the constructor.
*   **Can you have multiple @ExceptionHandler methods?** Yes, one class can handle `EmptyInputException`, `NoSuchElementException`, and generic `Exception` separately.

---

### 7. Summary of Benefits
*   **Consistency:** Every API endpoint returns errors in the same format.
*   **Separation of Concerns:** Business logic stays in the Service; error formatting stays in the Handler.
*   **Debugging:** Custom error codes help developers and mobile/frontend clients identify exactly what went wrong without reading the logs.