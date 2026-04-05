# Study Notes: Java 8 Interview Questions — Sort, Limit, and Skip

These notes are based on the "Code Decode" tutorial focusing on Java 8 Stream API operations for real-world interview scenarios.

---

## 1. Problem Scenario
In many Java 8 interviews, you are given a collection of custom objects (like `Employee`) and asked to perform data manipulation using Streams.

**The Tasks:**
1.  Sort the employees based on their salaries in **descending order**.
2.  Fetch the **top 3** highest-salaried employees.
3.  Fetch all employees whose salary is **less than the 3rd highest salary**.

---

## 2. Defining the Data Model
To perform these operations, we first need a standard Java class.

### The Employee Class
*   **Fields:** `int id`, `long salary`.
*   **Requirements:**
    *   Parameterized constructor to easily add data.
    *   Getters and Setters.
    *   Overridden `toString()` method to print results clearly in the console.

```java
class Employee {
    private int id;
    private long salary;

    public Employee(int id, long salary) {
        this.id = id;
        this.salary = salary;
    }

    public long getSalary() { return salary; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", salary=" + salary + "}";
    }
}
```

---

## 3. Implementation Steps

### Step 1: Sorting in Descending Order
To sort custom objects using Streams, we use the `.sorted()` method, which accepts a `Comparator`.

*   **Logic:** By default, `(o1, o2) -> o1 - o2` sorts in ascending order. To achieve **descending order**, we reverse the logic: `(o2, o1) -> o2 - o1`.
*   **Code Snippet:**
```java
List<Employee> sortedList = empList.stream()
    .sorted((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()))
    .collect(Collectors.toList());
```
*   **Explanation:** We open a `.stream()`, apply `.sorted()` with a lambda expression comparing salaries in reverse, and then `.collect()` the results back into a list.

### Step 2: Fetching the Top 3 Salaried Employees (Using `limit`)
The `limit(n)` method is a short-circuiting intermediate operation that truncates the stream to contain no more than `n` elements.

*   **Requirement:** Fetch the 3 highest salaries.
*   **Code Snippet:**
```java
List<Employee> top3 = empList.stream()
    .sorted((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()))
    .limit(3)
    .collect(Collectors.toList());
```
*   **Result:** This returns only the first three employees from the descending-sorted list.

### Step 3: Fetching Employees with Salary Less Than the 3rd Highest (Using `skip`)
The `skip(n)` method discards the first `n` elements of the stream and returns a stream consisting of the remaining elements.

*   **Requirement:** Find all employees whose salary is lower than the 3rd highest.
*   **Logic:** If we sort by salary descending and then skip the first 3 (the highest three), the remaining employees will all have salaries lower than the 3rd highest.
*   **Code Snippet:**
```java
List<Employee> lessThanThirdHighest = empList.stream()
    .sorted((o1, o2) -> (int) (o2.getSalary() - o1.getSalary()))
    .skip(3)
    .collect(Collectors.toList());
```

---

## 4. Key Java 8 Methods Used

| Method | Type | Description |
| :--- | :--- | :--- |
| `stream()` | Starting | Converts the Collection into a Stream. |
| `sorted(Comparator)` | Intermediate | Sorts elements based on the provided logic. |
| `limit(long n)` | Intermediate | Returns a stream consisting of the first `n` elements. |
| `skip(long n)` | Intermediate | Discards the first `n` elements and returns the rest. |
| `collect()` | Terminal | Converts the stream back into a List, Set, or other collection. |

---

## 5. Summary Table of Scenarios

| Interview Question | Stream Solution |
| :--- | :--- |
| **Sort Descending** | `.sorted((o1, o2) -> o2.getSalary() - o1.getSalary())` |
| **Get Top N items** | `.sorted(...).limit(N)` |
| **Get items after Top N** | `.sorted(...).skip(N)` |

---

## 6. Pro-Tip for Interviews
*   **Data Types:** When subtracting long values in a Comparator (like `o2.getSalary() - o1.getSalary()`), ensure you cast the result to an `int` or use `Long.compare(o2.getSalary(), o1.getSalary())` to avoid precision issues or compilation errors.
*   **Order Matters:** Always perform `sorted()` before `limit()` or `skip()` if the requirement is based on a ranking (like "highest salary"). Otherwise, `limit` and `skip` will just take elements based on their original insertion order in the list.