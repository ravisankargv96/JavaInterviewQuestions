# Detailed Notes: Custom Annotations and Validation in Spring Boot

These notes are based on the "Code Decode" tutorial regarding the creation and implementation of custom validations and annotations in a Spring Boot application.

---

## 1. Introduction to Custom Validation
Standard JSR-303/JSR-380 annotations (like `@NotBlank`, `@Email`, `@Min`, `@Max`) are useful for basic constraints. However, they cannot handle complex business logic, such as checking if an email address already exists in a database. For these scenarios, Spring Boot allows the creation of **Custom Annotations** and **Custom Validators**.

### The Problem Scenario
*   **Existing Validations:** `@Email` ensures the format is correct but doesn't check the database.
*   **Requirement:** Ensure an email address is unique across the entire database before saving a new record.
*   **Constraint:** No built-in Spring Boot annotation exists for "database uniqueness," necessitating a custom solution.

---

## 2. Steps to Create a Custom Annotation

To create a custom annotation, you must define an interface using the `@interface` keyword and apply specific meta-annotations.

### Meta-Annotations Required:
1.  **`@Target`**: Defines where the annotation can be applied.
    *   Commonly used: `ElementType.FIELD` (for class variables/fields).
    *   Other options: `METHOD`, `PARAMETER`, `TYPE`.
2.  **`@Retention`**: Defines how long the annotation is retained.
    *   `RetentionPolicy.RUNTIME`: Necessary for validation so the annotation is available to the JVM during application execution.
3.  **`@Constraint`**: This is the most critical meta-annotation. It links the annotation to the logic class that will perform the actual validation.
    *   Syntax: `@Constraint(validatedBy = UniqueEmailValidator.class)`

### Standard Attributes for Custom Annotations:
Spring's validation framework requires three specific methods to be present in the annotation interface:
*   **`String message()`**: The default error message to display if validation fails.
*   **`Class<?>[] groups()`**: Used to categorize validations (e.g., only validate on update, not on create).
*   **`Class<? extends Payload>[] payload()`**: Used by clients of the Bean Validation API to assign custom details to a constraint.

---

## 3. Implementing the Validator Logic

The logic for the validation resides in a separate class that implements the `ConstraintValidator` interface.

### The Validator Class
*   **Interface:** `ConstraintValidator<A, T>`
    *   `A`: The custom annotation type (e.g., `UniqueEmail`).
    *   `T`: The type of data being validated (e.g., `String` for an email).
*   **Dependency Injection:** You can autowire Spring components (like Repositories) directly into the validator class.
*   **The `isValid` Method:** This is where the business logic is written.
    *   It receives the value of the field.
    *   It queries the database via the repository.
    *   **Return `true`**: If validation passes (e.g., email not found in DB).
    *   **Return `false`**: If validation fails (e.g., email already exists).

---

## 4. Practical Example: Unique Email Validator

### The Annotation Definition
```java
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email address is already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### The Validator Implementation
```java
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // Business logic: check if email exists in DB
        List<Employee> employees = repository.findByEmailAddress(email);
        return employees.isEmpty(); // returns true if unique
    }
}
```

---

## 5. Usage in DTO and Controller

Once created, apply the annotation to the field in your Data Transfer Object (DTO).

### In the DTO:
```java
public class EmployeeDTO {
    @NotBlank
    @Email
    @UniqueEmail(message = "Email must be unique")
    private String emailAddress;
    // other fields...
}
```

### In the Controller:
To trigger the validation, use the `@Valid` annotation in the controller method signature:
```java
@PostMapping("/save")
public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDTO dto) {
    // process saving
    return ResponseEntity.ok("Saved successfully");
}
```

---

## 6. Advanced Concept: Validation Groups

Validation groups allow you to apply different validation rules to the same DTO depending on the operation (e.g., Create vs. Update).

### Marker Interfaces
You create "Marker Interfaces" (empty interfaces) to represent different stages:
1.  `interface CreateValidation {}`
2.  `interface UpdateValidation {}`

### Using Groups in Annotation
In the DTO, you specify which group a validation belongs to:
`@UniqueEmail(groups = UpdateValidation.class)`

### Triggering Groups with `@Validated`
In the controller, instead of `@Valid`, use `@Validated`:
```java
@PutMapping("/update")
public ResponseEntity<String> updateEmployee(@Validated(UpdateValidation.class) @RequestBody EmployeeDTO dto) {
    // Only validations assigned to the UpdateValidation group will run
}
```

---

## 7. Summary
*   **Custom Annotations** are created using `@interface` and meta-annotations.
*   **ConstraintValidator** connects the annotation to the database/business logic.
*   **Autofilling Repository:** Spring allows injecting repositories into validators to check for data integrity.
*   **Groups:** Help in executing specific validations only during specific CRUD operations using the `@Validated` annotation.