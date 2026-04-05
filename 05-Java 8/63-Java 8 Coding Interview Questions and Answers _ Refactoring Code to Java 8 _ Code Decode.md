These notes cover the key coding scenarios and refactoring techniques presented in the "Java 8 Coding Interview Questions" video by Code Decode. The focus is on transitioning from traditional imperative code to functional programming using the Stream API and Lambda expressions.

---

### **1. Core Concepts Overview**
The video emphasizes refactoring boilerplate code into concise Java 8 syntax. The primary tools used are:
*   **Stream API:** To process collections of objects.
*   **Lambda Expressions:** To provide inline implementations of functional interfaces.
*   **Method References:** (e.g., `Employee::getSalary`) for cleaner code.
*   **Collectors Class:** Used for grouping, averaging, and summarizing data.

---

### **2. The Data Model (Employee Class)**
Most examples use a list of `Employee` objects with the following attributes:
*   `id`, `name`, `age`, `gender`, `department`, `yearOfJoining`, `salary`.

---

### **3. Common Interview Coding Scenarios**

#### **Scenario A: Counting Male and Female Employees**
*   **Old Way:** Initialize two counters, loop through the list, use `if-else` to increment.
*   **Java 8 Refactoring:**
    *   Use `groupingBy` with `Collectors.counting()`.
    *   **Code:** `employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));`

#### **Scenario B: Printing All Department Names (Distinct)**
*   **Old Way:** Use a `Set` to store names while looping to ensure uniqueness.
*   **Java 8 Refactoring:**
    *   Use `map` to extract department names, then `distinct()`.
    *   **Code:** `employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);`

#### **Scenario C: Average Age of Male and Female Employees**
*   **Java 8 Refactoring:**
    *   Use `groupingBy` combined with `averagingInt`.
    *   **Code:** `employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));`

#### **Scenario D: Get Details of the Highest Paid Employee**
*   **Old Way:** Variable to track `maxSalary`, loop and update if a higher salary is found.
*   **Java 8 Refactoring:**
    *   Use `max()` with a comparator.
    *   **Code:** `employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));`
    *   *Note:* This returns an `Optional`. Use `.get()` to retrieve the object.

#### **Scenario E: Employees who joined after 2015**
*   **Java 8 Refactoring:**
    *   Use `filter` to check the condition, then `map` to get names.
    *   **Code:** `employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out::println);`

#### **Scenario F: Count Number of Employees in Each Department**
*   **Java 8 Refactoring:**
    *   Group by department and count.
    *   **Code:** `employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));`

#### **Scenario G: Average Salary of Each Department**
*   **Java 8 Refactoring:**
    *   Group by department and use `averagingDouble` on the salary field.
    *   **Code:** `employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));`

---

### **4. Advanced Refactoring Techniques**

#### **Finding the Youngest Male Employee in a Specific Department**
To solve this, the stream must be filtered first, then reduced using `min`.
*   **Logic:** `filter` (gender == Male && dept == "Product Development") -> `min` (Comparator on Age).

#### **Most Working Experience in the Organization**
This is determined by the earliest `yearOfJoining`.
*   **Logic:** `employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();`

#### **Separating Employees by Age**
For example, separating those older than 25 from those 25 or younger.
*   **Logic:** Use `Collectors.partitioningBy`. Unlike `groupingBy`, `partitioningBy` always returns a Map with two keys: `true` and `false`.
*   **Code:** `employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));`

---

### **5. Key Stream Terminology to Remember**

1.  **Intermediate Operations:** These are "lazy" and return a new Stream (e.g., `filter`, `map`, `distinct`, `sorted`).
2.  **Terminal Operations:** These trigger the processing and produce a result or side-effect (e.g., `collect`, `forEach`, `count`, `min`, `max`).
3.  **Optional Class:** Used to prevent `NullPointerException`. Methods like `maxBy` or `findFirst` return an `Optional<T>`.

---

### **6. Why Refactor to Java 8? (Interview Talking Points)**
*   **Conciseness:** Reduces lines of code significantly (from 10-15 lines to 1-2 lines).
*   **Readability:** The code describes *what* to do (declarative) rather than *how* to do it (imperative).
*   **Parallelism:** Streams can be easily converted to `parallelStream()` to utilize multi-core processors without writing complex multi-threading code.
*   **Efficiency:** Stream operations are optimized; for instance, lazy evaluation prevents unnecessary computations.