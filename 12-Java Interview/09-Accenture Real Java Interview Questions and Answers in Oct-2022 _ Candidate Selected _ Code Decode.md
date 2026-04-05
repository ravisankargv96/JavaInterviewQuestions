These detailed notes are based on the Accenture Java Interview questions and answers provided in the video transcript (October 2022).

---

### **1. Introduction and Project Explanation**
*   **Context:** These questions are from a real Accenture first-round interview where the candidate was successfully selected.
*   **Project Summary:** While not detailed in this specific video, the interviewer emphasizes that you should be able to explain your project's architecture and your specific role efficiently, focusing on technical touchpoints.

---

### **2. ConcurrentHashMap**
*   **Introduction:** Introduced in Java 1.5 as part of the `java.util.concurrent` package.
*   **Underlying Data Structure:** Hash Table.
*   **Why it was introduced:** To improve the performance of `Hashtable`. `Hashtable` locks the entire object during updates, whereas `ConcurrentHashMap` uses **Segment Locking** (or bucket-level locking).
*   **Internal Working (Three Key Parameters):**
    1.  **Concurrency Level:** The number of threads that can work simultaneously. The default is 16.
    2.  **Load Factor:** The threshold for resizing. Default is 0.75.
    3.  **Initial Capacity:** The number of elements the map can initially hold.
*   **Read vs. Write:**
    *   **Reads:** Threads can read any number of times without a lock.
    *   **Writes:** Only the specific segment/bucket being updated is locked. This allows up to 16 threads (by default) to update different segments simultaneously.
*   **Put/Get Logic:** The hash code is used twice—first to find the **segment index** and then to find the **bucket index** within that segment.

---

### **3. Read-Only Lists**
*   **Method:** To make a list read-only, use the utility method: `Collections.unmodifiableList(yourList)`.
*   **Result:** This returns a version of the list where any attempt to modify (add/remove) results in an `UnsupportedOperationException`.

---

### **4. Java Reflection API**
*   **Definition:** A process to analyze and modify the behavior of classes, methods, and interfaces at runtime. It focuses on the "blueprint" (the Class) rather than just the object.
*   **Advantages:**
    *   Allows manipulation of private members.
    *   Useful for debugging and testing.
    *   Enables creation of custom adapters and decorators at runtime.
*   **Disadvantages:**
    *   **Not Thread-Safe:** Can lead to dirty reads/wrong manipulations in multi-threaded environments.
    *   **Performance Overhead:** Operations are slower because they are resolved dynamically.
    *   **Breaks Abstraction:** It bypasses access modifiers (like private), which can violate the intended design of the platform.

---

### **5. Serializable vs. Externalizable**
| Feature | Serializable | Externalizable |
| :--- | :--- | :--- |
| **Control** | JVM takes full control. | Developer has full control. |
| **Interface Type** | Marker Interface (no methods). | Not a Marker Interface (has 2 methods). |
| **Methods** | None. | `writeExternal()` and `readExternal()`. |
| **Performance** | Slower (serializes everything). | Faster (you choose what to serialize). |
| **Transient Keyword** | Vital for skipping fields. | Not really needed (logic handles it). |
| **Partial Saving** | Not possible. | Possible via custom logic. |

---

### **6. Object Lock vs. Class Lock**
*   **Object Lock:**
    *   Every object in Java has a unique lock.
    *   Used to protect non-static methods or blocks.
    *   Multiple threads can access different instances of the same class simultaneously if they hold different object locks.
*   **Class Lock:**
    *   Every class has exactly one lock, regardless of how many instances exist.
    *   Used to protect static methods or static data.
    *   Acquired using the `.class` syntax or synchronized static methods.

---

### **7. Java Fundamentals & Inheritance**
*   **Casting to Interface:** An object reference can only be cast to an interface reference if the object’s class actually implements that interface. (e.g., `Animal a = new Dog();` where `Dog implements Animal`).
*   **The Root Class:** The `Object` class is the default superclass for every class in Java. It is the root of the hierarchy.

---

### **8. Method Overloading vs. Method Overriding**
#### **Method Overloading (Static Polymorphism)**
*   **Location:** Occurs within the same class.
*   **Rules:** Same name, but different parameters (number, type, or order).
*   **Restriction:** Changing only the **return type** is not enough; it will cause a compile-time error (duplicate method).

#### **Method Overriding (Dynamic Polymorphism)**
*   **Location:** Occurs between Parent and Child classes.
*   **Visibility:** You cannot reduce visibility (e.g., if parent is `public`, child cannot be `private`). You can, however, increase visibility.
*   **Final/Static/Private:** These methods cannot be overridden.
*   **Return Type:** Supports **Covariant Return Types** (Java 1.5+). You can return a subclass of the original return type.
*   **Exceptions:**
    *   If the parent doesn't throw a checked exception, the child cannot throw one (except runtime exceptions).
    *   If the parent throws an exception, the child can throw the same exception or its subclass, but not a broader/parent exception.

---

### **9. Transient Variables**
*   **Purpose:** Used in serialization to indicate that a field should not be saved.
*   **Usage:** Commonly used for sensitive data like passwords or temporary connection strings.
*   **Behavior:** When the object is deserialized, transient variables are restored to their default values (e.g., `null` for Objects, `0` for integers).

---

### **10. Coding Exercise: Java 8 Streams**
**Problem:** Given a list of integers, return a list of all even numbers greater than 5.

**Code Solution:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 5, 6, 8, 10, 11, 12);

List<Integer> result = numbers.stream()
    .filter(x -> x % 2 == 0 && x > 5) // Filter: Even AND > 5
    .collect(Collectors.toList());    // Collect to list

System.out.println(result); // Output: [6, 8, 10, 12]
```