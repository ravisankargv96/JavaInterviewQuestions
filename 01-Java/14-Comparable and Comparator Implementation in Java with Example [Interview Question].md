These detailed notes cover the core concepts, implementation details, and interview-focused differences between the `Comparable` and `Comparator` interfaces in Java, as discussed in the tutorial.

---

### **1. Introduction to Sorting in Java**
Sorting simple data types like `Integer`, `String`, or `Double` is straightforward using `Collections.sort()` because these classes already implement the `Comparable` interface. However, when dealing with a list of **Custom Objects** (e.g., `Student`, `Employee`, `Product`), Java does not know which property to use for sorting (e.g., should it sort by ID, Name, or Salary?).

To resolve this, Java provides two interfaces:
1.  **Comparable**
2.  **Comparator**

---

### **2. The Comparable Interface**
The `Comparable` interface is used to define the **Natural Ordering** of an object.

*   **Package:** `java.lang`
*   **Method:** `public int compareTo(T object)`
*   **Implementation:** The class whose objects you want to sort must implement this interface.
*   **Modification:** You must modify the actual class code.

#### **How compareTo() works:**
*   Returns a **positive integer** if the current object is greater than the specified object.
*   Returns a **negative integer** if the current object is less than the specified object.
*   Returns **zero** if both are equal.

#### **Example Usage:**
If you have a `Student` class and want to sort by `rollNo` by default:
```java
class Student implements Comparable<Student> {
    int rollNo;
    String name;

    public int compareTo(Student st) {
        if(this.rollNo == st.rollNo) return 0;
        else if(this.rollNo > st.rollNo) return 1;
        else return -1;
    }
}
```
*To sort:* `Collections.sort(studentList);`

---

### **3. The Comparator Interface**
The `Comparator` interface is used for **Custom Ordering**. It allows you to define multiple sorting sequences for the same object without modifying the class itself.

*   **Package:** `java.util`
*   **Method:** `public int compare(T obj1, T obj2)`
*   **Implementation:** Usually implemented in a separate class or as an anonymous inner class/lambda expression.
*   **Modification:** Does not require modifying the original class.

#### **Example Usage:**
If you want to sort `Student` objects by `Name` instead of `rollNo`:
```java
class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}
```
*To sort:* `Collections.sort(studentList, new NameComparator());`

---

### **4. Key Differences (Interview Cheat Sheet)**

| Feature | Comparable | Comparator |
| :--- | :--- | :--- |
| **Purpose** | Defines default "Natural Ordering". | Defines multiple "Custom Orderings". |
| **Method** | `compareTo(obj)` | `compare(obj1, obj2)` |
| **Package** | `java.lang` | `java.util` |
| **Class Modification** | Original class must be modified. | No need to modify the original class. |
| **Sorting Logic** | Single sorting logic (e.g., only by ID). | Multiple sorting logics (e.g., by Name, Age, etc.). |
| **Call Method** | `Collections.sort(list)` | `Collections.sort(list, comparatorRef)` |

---

### **5. When to use which?**
*   **Use Comparable:** When there is a single, obvious way to sort the objects (like ID for an Employee). It makes the class "naturally" sortable.
*   **Use Comparator:** When you need to sort based on different criteria in different scenarios, or when you cannot modify the source code of the class you are trying to sort.

---

### **6. Modern Java 8+ Implementation**
The video highlights that with Java 8, you can simplify `Comparator` implementation using **Lambda Expressions**, avoiding the need for extra classes:

```java
// Sorting by name using Lambda
Collections.sort(studentList, (s1, s2) -> s1.getName().compareTo(s2.getName()));

// Using Comparator.comparing method
studentList.sort(Comparator.comparing(Student::getName));
```

### **7. Summary of Steps for Implementation**
1.  **For Comparable:** Implement the interface in your POJO class -> Override `compareTo` -> Call `Collections.sort(list)`.
2.  **For Comparator:** Create a separate class/lambda -> Implement `compare` -> Call `Collections.sort(list, comparator)`.