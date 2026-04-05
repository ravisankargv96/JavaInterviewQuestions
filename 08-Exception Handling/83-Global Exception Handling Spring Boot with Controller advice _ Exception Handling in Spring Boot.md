# Detailed Notes: Global Exception Handling in Spring Boot

These notes are based on the video tutorial regarding Global Exception Handling using `@ControllerAdvice` in Spring Boot.

---

## 1. Introduction to Exception Handling
In real-world IT projects, handling errors correctly is vital. It ensures that the application remains robust and provides meaningful, user-friendly error messages to clients instead of raw stack traces.

### Why Global Exception Handling?
*   **Reduces Boilerplate Code:** Traditional custom exception handling requires `try-catch` blocks and `throw/throws` keywords in every class and method. Global handling eliminates this repetition.
*   **Centralized Logic:** It allows you to handle common exceptions (like "Resource Not Found" or "Empty Input") in one single place for the entire application.
*   **Meaningful Responses:** Instead of the client seeing a "500 Internal Server Error," they receive specific HTTP status codes (400, 404, etc.) and descriptive messages.

---

## 2. Core Annotations and Classes
To implement global exception handling, Spring Boot provides several specific annotations:

*   **`@ControllerAdvice`**: This is the most important annotation. It marks a class as a global interceptor for exceptions thrown by any `@Controller` in the application.
*   **`@ExceptionHandler`**: Used on methods within the advice class to specify which type of exception that specific method should handle (e.g., `EmptyInputException.class`).
*   **`@ResponseStatus`**: An optional annotation to define the HTTP status code returned to the client.
*   **`ResponseEntityExceptionHandler`**: A base class that your advice class should extend. It provides default methods for handling standard Spring MVC exceptions (like method not supported or media type not supported).

---

## 3. Implementation Steps

### Step 1: Create a Custom Exception Class
If you have specific business logic errors, create a custom class extending `RuntimeException`.
*   *Example:* `EmptyInputException` (used when a user submits an empty field).

### Step 2: Create the Global Advice Class
Create a new package (e.g., `com.project.advice`) and a class (e.g., `MyControllerAdvice`).
1.  Annotate the class with `@ControllerAdvice`.
2.  Extend `ResponseEntityExceptionHandler`.

### Step 3: Define Exception Handler Methods
Inside the advice class, create methods to catch specific exceptions.

**Example: Handling a Custom Exception**
```java
@ExceptionHandler(EmptyInputException.class)
public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
    return new ResponseEntity<String>("Input field is empty, please look into it.", HttpStatus.BAD_REQUEST);
}
```

**Example: Handling Predefined Exceptions (`NoSuchElementException`)**
When a database lookup fails to find an ID, Spring might throw a `NoSuchElementException`. You can intercept this globally:
```java
@ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> handleNoSuchElement(NoSuchElementException elementException) {
    return new ResponseEntity<String>("No value is present in DB, please change your request.", HttpStatus.NOT_FOUND);
}
```

---

## 4. Overriding Built-in Handlers
By extending `ResponseEntityExceptionHandler`, you can override existing methods to customize standard error responses.

*   **Example:** Overriding `handleHttpRequestMethodNotSupported`.
*   **Scenario:** If a client sends a `GET` request to an endpoint that only accepts `POST`.
*   **Default Behavior:** Returns a blank 405 status.
*   **Custom Behavior:** You can override it to return a specific body message like "Please change your HTTP method type."

---

## 5. Global vs. Custom (Local) Exception Handling

| Feature | Custom (Local) Handling | Global Exception Handling |
| :--- | :--- | :--- |
| **Implementation** | `try-catch` in every method. | One class for the whole app. |
| **Code Length** | High (Boilerplate code). | Low (Clean controllers). |
| **Maintenance** | Difficult (Changes needed everywhere). | Easy (Change in one class). |
| **Best For** | Unique logic specific to one method. | Common errors (NullPointer, Not Found). |

---

## 6. Key Benefits Summary
1.  **Cleaner Controllers:** Controllers are reduced to simple "two-liners" because they no longer need complex error logic.
2.  **Professional API Design:** The client always receives a structured response (Status Code + Message).
3.  **Interception Power:** The `@ControllerAdvice` acts as an interceptor, catching the error *before* the console breaks or the default 500 error page is generated.

## 7. Prerequisites for Practice
*   Basic knowledge of Spring Boot.
*   Understanding of REST API creation (CRUD operations).
*   Familiarity with Java exception concepts (`RuntimeException`, `throw`, etc.).