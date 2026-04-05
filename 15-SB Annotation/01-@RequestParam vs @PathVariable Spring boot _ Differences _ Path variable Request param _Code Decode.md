# Detailed Notes: @RequestParam vs @PathVariable in Spring Boot

These notes are based on the "Code Decode" tutorial, which explores the differences, use cases, and technical implementations of `@RequestParam` and `@PathVariable` using Spring Boot 3 and Java 17.

---

### 1. Overview of the Annotations

Both annotations are used to extract values from a URL in a Spring Boot REST controller, but they serve different purposes and occupy different parts of the URI.

*   **@PathVariable**: Extracts values directly from the URI path.
*   **@RequestParam**: Extracts values from the query parameters (after the `?` in the URL).

#### Example URL Structure:
`http://localhost:8080/employees/{id}?startYear=2023&gender=female`

*   **Path Variable**: `{id}` (e.g., `101`)
*   **Request Parameters**: `startYear=2023` and `gender=female`

---

### 2. When to Use Which (The Logic)

| Feature | @PathVariable | @RequestParam |
| :--- | :--- | :--- |
| **Primary Use** | **Identification**: To uniquely identify a specific resource. | **Filtering/Sorting**: To provide additional criteria or filters. |
| **Requirement** | Generally **Mandatory**. | Can be **Optional**. |
| **Data Nature** | Vital to the resource's identity (e.g., ID). | Metadata or search criteria (e.g., page number, date range). |

**Rule of Thumb:**
*   Use `@PathVariable` if the data is required to locate the resource in the database.
*   Use `@RequestParam` if you are filtering a list of resources or providing optional information.

---

### 3. Key Differences

#### A. Location and Syntax
*   **Path Variable**: Defined within curly braces `{}` in the `@GetMapping` or `@RequestMapping` value.
    *   *Code:* `@GetMapping("/employees/{id}")`
*   **Request Parameter**: Appears after the `?` in the URL. Multiple parameters are separated by `&`.
    *   *URL:* `/employees/101?gender=male&year=2023`

#### B. Required Status & Default Values
*   **@RequestParam**:
    *   Can be made optional: `@RequestParam(required = false)`.
    *   Supports default values: `@RequestParam(defaultValue = "2000")`.
    *   If `required = false` and no default is provided, the value becomes `null`.
*   **@PathVariable**:
    *   By default, it is required.
    *   If a path variable is missing from the URL, the application will typically return a **404 Not Found** error.

#### C. Handling Multiple Values
*   **@RequestParam**: Very easy to handle lists or arrays.
    *   *URL:* `?users=1,2,3,4`
    *   *Code:* `public List<User> get(@RequestParam List<Long> users)`
*   **@PathVariable**: Difficult to handle multiple values. It requires manual string parsing (e.g., comma-separated strings) and is generally not recommended for collections.

#### D. URL Structure and Flexibility
*   **Request Params** are more flexible. Adding or removing a query parameter does not change the core URL mapping.
*   **Path Variables** strictly define the URL structure. If you add a new path variable (e.g., `/{id}/{projectId}`), the existing URL mapping breaks unless the code is updated.

#### E. Ordering
*   **Request Params**: The order **does not matter**. Because they are key-value pairs, `?gender=male&year=2023` is the same as `?year=2023&gender=male`.
*   **Path Variables**: The order **matters strictly**. Spring maps the values based on their position in the URI path. Swapping values in the URL will result in data being mapped to the wrong variables.

#### F. Human Readability
*   **Request Params** are highly readable because the key name is visible in the URL (e.g., `?startYear=2023`).
*   **Path Variables** can become confusing if there are many of them (e.g., `/employees/1/101`). It is unclear which number represents the Employee ID versus the Project ID.

---

### 4. Technical Implementation Details

#### Automatic Decoding
Spring Boot automatically decodes URL-encoded characters. For example, if a space is sent as `%20` in the URL (e.g., `code%20decode`), Spring will automatically convert it back to a standard space (`code decode`) before passing it to the method parameters.

#### Code Snippet (Spring Boot 3 / Java 17)
```java
@GetMapping("/employees/{empId}")
public ResponseEntity<Employee> getEmployee(
    @PathVariable("empId") Long empId, // Path Variable
    @RequestParam(value = "startYear", required = false, defaultValue = "2024") Integer startYear, // Optional Param
    @RequestParam("gender") String gender // Required Param
) {
    // Logic to fetch employee by empId and filter by year/gender
    return ResponseEntity.ok(employee);
}
```

#### Handling Lists via @RequestParam
```java
@GetMapping("/users")
public List<Long> getUserList(@RequestParam List<Long> id) {
    // URL: /users?id=1,2,3,4
    return id; // Returns [1, 2, 3, 4]
}
```

---

### 5. Summary Table

| Feature | @PathVariable | @RequestParam |
| :--- | :--- | :--- |
| **URL Placement** | Part of the path (`/id`) | Query string (`?id=1`) |
| **Required** | Yes (Default) | No (can set `required=false`) |
| **Default Value** | No | Yes |
| **Order Dependent** | Yes | No |
| **Form Data** | Not suitable | Highly suitable |
| **Clarity** | Can be ambiguous | Very clear (Key-Value) |