These detailed notes are based on the video from Code Decode, focusing on the differences between Checked and Unchecked Exceptions in Java.

---

### **1. Java Exception Hierarchy**
Understanding the hierarchy is the first step to differentiating between exception types.

*   **Throwable:** The parent class of all exceptions and errors in Java.
    *   **Error:** These are issues that occur outside the programmer's control (usually hardware or JVM issues). Examples include `StackOverflowError` and `OutOfMemoryError`. We do not handle these in our code.
    *   **Exception:** These are issues caused by the program/developer and are within our control. This branch is further divided into:
        1.  **Checked Exceptions:** Direct subclasses of `Exception`.
        2.  **Unchecked Exceptions:** Subclasses of `RuntimeException` (which itself is a child of `Exception`).

---

### **2. Checked Exceptions (Compile-time Exceptions)**
Checked exceptions are those that the Java compiler forces you to acknowledge during development.

*   **Definition:** These are exceptions checked by the compiler at **compile-time**. If your code has the potential to throw a checked exception, the compiler will refuse to compile the code into bytecode until the exception is handled.
*   **Hierarchy:** They are direct children of the `Exception` class. There is no intermediate "CheckedException" class.
*   **Scenario:** They occur in situations with a high failure rate that are external to the code's logic (e.g., a file might be missing, or a database connection might fail).
*   **Handling Requirement:** You **must** handle them using one of two methods:
    1.  **Try-Catch Block:** Providing a "Plan B" if the resource is unavailable.
    2.  **Throws Keyword:** Declaring in the method signature that this method might throw the exception, passing the responsibility to the caller.
*   **Examples:**
    *   `IOException`
    *   `SQLException`
    *   `FileNotFoundException`
    *   `ClassNotFoundException`
    *   `InterruptedException`

---

### **3. Unchecked Exceptions (Runtime Exceptions)**
Unchecked exceptions are those that occur during the execution of the program.

*   **Definition:** These are **not checked** by the compiler. The Java compiler will compile the code successfully even if these exceptions are likely to occur. They are discovered only when the program is running.
*   **Hierarchy:** These are all subclasses of the `RuntimeException` class.
*   **Scenario:** These are usually the result of **programmatic mistakes** or logic errors (e.g., trying to access a null object or dividing by zero).
*   **Handling Requirement:** The JVM does not force you to handle these with `try-catch` or `throws`. However, as a developer, you should write clean logic (like checking for nulls or valid indices) to prevent them.
*   **Examples:**
    *   `ArithmeticException` (e.g., divide by zero)
    *   `NullPointerException` (NPE)
    *   `ArrayIndexOutOfBoundsException`
    *   `IllegalArgumentException`

---

### **4. Key Differences at a Glance**

| Feature | Checked Exceptions | Unchecked Exceptions |
| :--- | :--- | :--- |
| **Also Known As** | Compile-time Exceptions | Runtime Exceptions |
| **Compiler Check** | Checked by the compiler. | Ignored by the compiler. |
| **Handling** | Mandatory (`try-catch` or `throws`). | Optional (handled by logic). |
| **Hierarchy** | Direct subclass of `Exception`. | Subclass of `RuntimeException`. |
| **Cause** | External factors (High failure rate). | Programming/Logic errors. |
| **Force Handling** | Yes, compiler forces it. | No, JVM/Compiler does not force it. |

---

### **5. Practical Examples from the Video**

#### **Checked Exception Example (`FileNotFoundException`)**
If you attempt to read a file using `FileReader`, the compiler will immediately flag an error: *"Unhandled exception: java.io.FileNotFoundException"*.
*   **The Fix:** You must wrap it in a `try-catch` block to define what the system should do if the file isn't found, or use `throws FileNotFoundException` in the method header.

#### **Unchecked Exception Example (`ArithmeticException`)**
If you write `int result = 5 / 0;`, the code will compile perfectly. The IDE will not show a red line because it is a runtime issue.
*   **The Result:** When you run the program, the console will show `Exception in thread "main" java.lang.ArithmeticException: / by zero`.

---

### **6. Important Interview Tips**
*   **The Misconception:** Many developers think "Checked Exception" is a class name. Clarify that "Checked" is just a category of exceptions that extend `Exception` directly.
*   **Hierarchy Proof:** In the Java source code, `RuntimeException` extends `Exception`. However, all classes extending `RuntimeException` become "Unchecked," while all other classes extending `Exception` remain "Checked."
*   **Best Practice:** For Unchecked Exceptions like `NullPointerException`, it is better to prevent them (e.g., using `Optional` or null checks) rather than just catching them with a `try-catch` block.