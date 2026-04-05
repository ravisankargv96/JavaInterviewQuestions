These detailed notes are based on the video "Mockito Junit Interview Question and Answer for fresher and experienced with example" from the Code Decode YouTube channel.

---

### **1. Introduction to Mockito and JUnit**
*   **JUnit:** The primary testing framework for Java applications.
*   **Mockito:** A Java-based mocking framework used in conjunction with JUnit (or TestNG). It allows developers to create "mock" objects to isolate the code being tested.
*   **Internal Working:** Mockito uses the **Java Reflection API** to create mock objects for interfaces. These mocks are "proxy" implementations of the actual objects.

---

### **2. What is Mocking?**
*   **Definition:** The process of developing objects that act as clones or "dummies" of real objects.
*   **Purpose:** To test a specific class (e.g., a Service layer) without involving its real dependencies (e.g., a Database or an external API).
*   **Stubbing:** Providing a specific "dummy output" for a specific "dummy input."
    *   *Example:* Telling the mock repository to return a predefined "Employee" object whenever `findById(1)` is called, instead of actually searching the database.

---

### **3. Key Mockito Annotations**
*   **`@Mock`:** Used to create and inject a mock instance of a dependency. It creates a "mirror image" of the class/interface that does not execute real code.
*   **`@InjectMocks`:** Used on the class being tested. It creates the actual instance of the class and automatically injects all the mocks (defined with `@Mock`) into it.

---

### **4. Benefits of Using Mockito**
1.  **No Hardcoding/Setup:** You don't need to set up a complex test database or hardcode values in the database.
2.  **Refactor Safe:** Mocks are created at runtime via Reflection. Renaming or reordering database parameters won't break the test code as easily as manual stubs might.
3.  **Exception Support:** Mockito can simulate exceptions (e.g., `thenThrow`) to test error-handling logic.
4.  **Order Verification:** You can verify the order in which methods are called.
5.  **Annotation Support:** Simplifies the setup of test classes using `@Mock` and `@InjectMocks`.

---

### **5. Limitations of Mockito**
Mockito cannot mock the following out-of-the-box:
*   Final methods/classes (though newer versions have some support, it's generally a limitation).
*   Static methods.
*   The `equals()` method.
*   Object constructors.
*   *Solution:* If these are required, developers use **PowerMock**, though it is recommended to improve the code design first to avoid needing it.

---

### **6. Why is Mocking Necessary?**
*   **Database Connections:** Real DB queries are slow. Mocking saves time and CPU resources.
*   **Performance:** Some classes are "performance intensive" or slow. Mocking allows for fast execution of the CI/CD pipeline.
*   **Avoiding Side Effects:** You don't want to send real emails to clients or trigger real payments during a unit test.
*   **Unfinished Dependencies:** You can mock a component that is still being developed by another team member.
*   **Microservices:** Mocking external REST or gRPC calls prevents tests from failing if an external service is down.

---

### **7. Code Coverage**
*   **Definition:** A metric that measures how many lines/branches of your code are executed by your tests.
*   **Industry Standard:** Most projects require a minimum of **80% code coverage**.
*   **Tools:** **EclEmma** (for Eclipse) or built-in coverage tools in IntelliJ IDEA.
*   **How to achieve high coverage:**
    *   Write multiple test cases for a single method to cover different logical paths (e.g., `if`, `else`, `catch` blocks).
    *   Use Mockito to simulate different scenarios for the same method call.

---

### **8. Practical Code Examples (The "Mantra")**

#### **The "When-Then" Mantra**
This is used to configure the behavior of a mock.
```java
// Logic: When the repo is called with ID 1, return the dummy 'stub' object
when(employeeRepo.findById(1)).thenReturn(stubEmployee);
```

#### **Testing Exceptions (Business Logic)**
If you want to test if your code correctly throws an exception when a certain condition is met:
```java
@Test
void testException() {
    // Stubbing a return value that triggers an 'if' condition in the service
    when(employeeRepo.findById(1)).thenReturn(new Employee("Code"));
    
    // Asserting that a BusinessException is thrown
    assertThrows(BusinessException.class, () -> {
        employeeService.getEmployeeById(1);
    });
}
```

#### **The `thenThrow` Mantra**
Use this to simulate a dependency failing or throwing an error:
```java
// Logic: When the repo is called, force it to throw an exception
when(employeeRepo.findById(1)).thenThrow(new NoSuchElementException());

// This helps cover the 'catch' blocks in your service layer for code coverage.
```

---

### **9. Key Interview Questions Covered**
1.  **What is the difference between `@Mock` and `@InjectMocks`?**
    *   `@Mock` creates the dummy; `@InjectMocks` creates the real object you are testing and puts the dummies inside it.
2.  **Can Mockito mock static methods?**
    *   No, standard Mockito cannot; PowerMock is required for static/final mocking.
3.  **Why use Mocking instead of a real DB?**
    *   Speed, isolation from external failures, and avoiding unwanted side effects like sending emails.
4.  **How do you verify a specific exception is thrown in JUnit 5?**
    *   Using `assertThrows(ExceptionClass.class, () -> { methodCall(); });`.