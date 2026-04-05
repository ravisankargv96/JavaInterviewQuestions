These notes cover scenario-based Java 8 interview questions focusing on the `Optional` class, specifically comparing `orElse`, `orElseGet`, and `orElseThrow`.

---

### **1. Scenario: Handling Null Values with Default Defaults**

**Requirement:** Given an employee database, fetch the employee name by a specific ID. If the name is missing (the user chose to remain anonymous/null), return a default value "anonymous".

#### **Traditional Approach (Pre-Java 8)**
Developers typically used `if-else` blocks or null checks. If you directly called `.get()` on a null value, it would throw a `NoSuchElementException`.

#### **Java 8 Solution: `orElse()`**
The `orElse()` method is used to retrieve the value wrapped inside an `Optional` instance.
*   **Syntax:** `optionalObject.orElse("default_value")`
*   **How it works:** If the value is present in the container, it returns the value. If the container is empty, it returns the argument passed to it.
*   **Benefit:** It handles the extraction logic internally so you don't have to manually check `isPresent()` or call `.get()`.

---

### **2. Scenario: Using `orElseGet()` for Dynamic Defaults**

**Requirement:** Same as above, but using a functional approach.

#### **Java 8 Solution: `orElseGet()`**
The `orElseGet()` method also returns a default value if the `Optional` is empty, but it takes a **Supplier** functional interface as an input.
*   **Syntax:** `optionalObject.orElseGet(() -> "anonymous")`
*   **How it works:** It uses a lambda expression to provide the default value.

---

### **3. Critical Interview Question: `orElse` vs `orElseGet`**

While both methods seem to do the same thing, there is a significant performance difference regarding **Lazy vs. Eager evaluation**.

| Feature | `orElse(T other)` | `orElseGet(Supplier<? extends T> other)` |
| :--- | :--- | :--- |
| **Parameter** | Takes a direct value/object. | Takes a Supplier (Lambda). |
| **Evaluation** | **Eager:** The argument is evaluated/created **every time**, even if the value is present. | **Lazy:** The Supplier is invoked **only if** the `Optional` is empty. |
| **Performance** | Lower (due to redundant object creation/method calls). | Higher (avoids unnecessary execution). |

#### **Practical Example of the Difference:**
If you pass a method call to both:
1.  **`orElse(callMeMethod())`**: Even if the `Optional` has a value, `callMeMethod()` will execute. This creates redundant objects in memory and triggers unnecessary logic.
2.  **`orElseGet(() -> callMeMethod())`**: If the `Optional` has a value, `callMeMethod()` is **never** called.

**Why this matters for Experienced Developers:**
If `callMeMethod()` involves a database hit or a REST API call, using `orElse` will result in a performance hit because those costly operations will run every single time. `orElseGet` ensures these operations only run when absolutely necessary.

---

### **4. Scenario: Mandatory Data & Exception Handling**

**Requirement:** Fetch an employee by ID. If the name is not present, the program must stop execution and throw an exception.

#### **Java 8 Solution: `orElseThrow()`**
If a value is required for the business logic to continue, use `orElseThrow()`.

*   **Standard Usage:** `optionalObject.orElseThrow()`
    *   By default, this throws a `NoSuchElementException` if the value is missing.
*   **Custom Exception Usage (Supplier):**
    *   You can pass a lambda to provide a user-friendly or custom exception.
    *   **Example:** `orElseThrow(() -> new IllegalArgumentException("The ID passed has no name associated with it."));`

**Advantages of `orElseThrow`:**
1.  **Readability:** It clearly signals that the value is mandatory.
2.  **User-Friendly:** Allows you to wrap technical errors into custom exceptions with meaningful messages for the front-end or client.
3.  **No Manual Unwrapping:** You don't need to return an `Optional` object or call `.get()` manually.

---

### **Summary of Optional Methods Covered**

1.  **`ofNullable()`**: Creates an `Optional` object that may or may not contain a null value.
2.  **`orElse(defaultValue)`**: Returns a default value; evaluated eagerly (always runs).
3.  **`orElseGet(Supplier)`**: Returns a default value; evaluated lazily (runs only if empty).
4.  **`orElseThrow(Supplier)`**: Throws an exception if the value is missing; allows for custom exception types and messages.