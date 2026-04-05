These notes are based on the video from **Code Decode** regarding **Static Variables in Java**, covering their definition, memory management, and practical interview applications.

---

### **1. Introduction to Static Variables**
*   **Definition:** A static variable is a variable that is declared with the `static` keyword inside a class but outside any method, constructor, or block.
*   **Belongs to the Class:** Unlike instance variables, static variables belong to the **class** rather than any specific object (instance).
*   **Single Copy:** Only one copy of a static variable is created and shared among all instances of the class. If one object modifies the value of a static variable, the change is reflected across all other objects.

### **2. Memory Management**
*   **Storage Location:** Static variables are stored in the **Method Area** (specifically in the Metaspace in modern Java versions), not in the Heap (where objects are stored).
*   **Initialization Timing:** They are initialized only once, at the time of **Class Loading**. This happens before any objects of the class are created and before any instance variables are initialized.
*   **Efficiency:** Using static variables saves memory because a common property (like a company name or college name) doesn't need to be allocated separately for every single object created.

### **3. Static vs. Instance Variables**
| Feature | Static Variable | Instance Variable |
| :--- | :--- | :--- |
| **Keyword** | Declared with `static`. | Declared without `static`. |
| **Scope** | Shared by all objects of the class. | Unique to each object. |
| **Memory Allocation** | Allocated when the class is loaded. | Allocated when an object is created. |
| **Access** | Accessed via Class Name (`ClassName.var`). | Accessed via Object Reference (`obj.var`). |
| **Life Cycle** | Exists until the program/class-loader ends. | Exists until the object is Garbage Collected. |

### **4. How to Access Static Variables**
While you *can* access static variables using an object reference, it is **not recommended**. The standard and best practice is to use the class name:

*   **Recommended:** `Student.collegeName = "Stanford";`
*   **Not Recommended:** `studentObj.collegeName = "Stanford";` (This is confusing because it implies the variable belongs to the object).

### **5. Real-World Example: Student System**
Imagine a class `Student` with 1,000 students.
*   **Instance Variables:** `rollNo`, `name` (Each student has a different name/ID).
*   **Static Variable:** `collegeName` (All 1,000 students belong to the same college).
*   **Benefit:** Instead of storing "Stanford" 1,000 times in the heap (wasting memory), Java stores it once in the Method Area, and all 1,000 students point to that single location.

### **6. Key Interview Questions and Answers**

#### **Q1. Can we declare a static variable inside a method?**
**No.** Static variables are class-level variables. If you try to declare a `static int x` inside a method, it will result in a compilation error. Variables inside methods are "local variables" and cannot be static.

#### **Q2. Why is the `main` method static?**
Because the `main` method must be called by the JVM before any objects of the class exist. If `main` were not static, the JVM would have to instantiate the class to run the program, which creates a "chicken and egg" problem.

#### **Q3. Can a static method access a non-static (instance) variable?**
**No.** A static method belongs to the class and is loaded before any objects. Because instance variables only exist once an object is created, the static method "doesn't know" which object's instance variable to look at.

#### **Q4. Can a non-static method access a static variable?**
**Yes.** Non-static methods (instance methods) can always access static variables because static variables exist as soon as the class is loaded.

#### **Q5. Are static variables thread-safe?**
**No.** Since static variables are shared among all instances, if multiple threads try to modify a static variable simultaneously, it can lead to data inconsistency. Proper synchronization is required if static variables are used in a multi-threaded environment.

### **7. Summary of Advantages**
1.  **Memory Efficiency:** Reduces the memory footprint by sharing common data.
2.  **Global Access:** Provides a way to maintain a "global" state within a class context.
3.  **Initialization:** Useful for constants or settings that need to be available immediately when the class is loaded.