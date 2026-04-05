These notes cover the core concepts, implementation details, and interview-specific logic for sorting objects in Java using the `Comparator` interface, as presented in the Code Decode video.

---

### **1. Core Concept: Comparator vs. Comparable**

*   **Natural Sorting (`Comparable`):** Used when there is one standard way to sort an object (e.g., sorting Employees by ID). You implement the `compareTo(Object o)` method inside the class itself.
*   **Custom Sorting (`Comparator`):** Used when you need to sort the same list of objects in multiple ways (e.g., by Age, then by Name, then by Address). You create separate classes that implement the `compare(T o1, T o2)` method.

### **2. The Logic of the `compare()` Method**
When implementing the `compare` method in a `Comparator` or the `compareTo` method in `Comparable`, the return values must follow these rules:
*   **Negative Value:** If the first parameter is **less than** the second parameter.
*   **Positive Value:** If the first parameter is **greater than** the second parameter.
*   **Zero:** If both parameters are **equal**.

---

### **3. Implementation Step-by-Step**

#### **Step 1: Create the Custom Objects**
To demonstrate sorting, the video uses an `Employee` class and a nested custom object `Address`.

*   **Employee Class Fields:** `id` (int), `age` (int), `name` (String), `address` (Address object).
*   **Address Class Fields:** `streetName` (String), `pinCode` (int).

#### **Step 2: Define Natural Sorting for Nested Objects**
If you want to sort a parent object (Employee) based on a child object (Address), the child object should ideally implement `Comparable`.
*   **Example:** To sort Addresses by pin code:
    ```java
    public int compareTo(Address other) {
        return this.pinCode - other.pinCode; 
        // Returns negative if this < other, positive if this > other, 0 if equal.
    }
    ```

#### **Step 3: Importance of `toString()`**
In technical interviews, remember that printing a list of objects will result in memory addresses unless you override the `toString()` method in both the `Employee` and `Address` classes. This makes the output readable during debugging or demonstration.

---

### **4. Writing Multiple Comparators**

The video demonstrates three specific types of comparators:

#### **A. Age Comparator (Numeric Comparison)**
For primitive or wrapper numeric types, you can simply subtract the values.
```java
public class AgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getAge() - e2.getAge();
    }
}
```

#### **B. Name Comparator (String Comparison)**
Since the `String` class already implements `Comparable`, you don't need subtraction logic. Just call the existing `compareTo` method.
```java
public class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}
```

#### **C. Address Comparator (Custom Object Comparison)**
Since the `Address` class was given a natural sorting (via `Comparable`) in Step 2, the comparator for Employee can leverage that logic.
```java
public class AddressComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getAddress().compareTo(e2.getAddress());
    }
}
```

---

### **5. Executing the Sort**

To sort the list in the main application, use `Collections.sort()`. This method is overloaded:
1.  `Collections.sort(list)`: Uses natural sorting (requires the class to implement `Comparable`).
2.  `Collections.sort(list, Comparator)`: Uses the specific custom logic provided by the Comparator.

**Example Usage:**
```java
// Sorting by Age
Collections.sort(employeeList, new AgeComparator());

// Sorting by Name
Collections.sort(employeeList, new NameComparator());

// Sorting by Address (Pin Code)
Collections.sort(employeeList, new AddressComparator());
```

---

### **6. Interview Tips & Summary**

*   **Scenario-Based Choice:** If the interviewer asks to sort by multiple fields, immediately suggest `Comparator`.
*   **String/Wrappers:** Mention that classes like `String` and `Integer` already have natural sorting implemented.
*   **Code Cleanliness:** Using a constructor instead of multiple setters makes the code more concise during a live coding interview.
*   **Logic Precision:** Always double-check your subtraction order (`o1 - o2` for ascending, `o2 - o1` for descending) to ensure the logic matches the requirement.