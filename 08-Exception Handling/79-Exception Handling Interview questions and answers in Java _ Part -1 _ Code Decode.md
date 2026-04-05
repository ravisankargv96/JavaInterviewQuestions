# Exception Handling Interview Notes - Part 1 (Java)

These notes are based on the "Code Decode" interview preparation video covering basic level Exception Handling in Java.

---

### 1. What is an Exception?
*   **Definition:** An abnormal condition that occurs during the execution of a program.
*   **Impact:** It disrupts the normal flow of the program. If not handled properly, it causes the program or process to terminate abruptly.
*   **Example:** A `NullPointerException`. If you instantiate a class object as `null` (e.g., `Dog dog = null;`) and then try to access a property (e.g., `dog.name`), the compiler won't catch it, but at runtime, an exception will occur because no memory was allocated.

### 2. How to Handle Exceptions
Exceptions are handled using three primary blocks:
*   **Try Block:** Encloses a set of statements that might throw an exception. These statements are "monitored."
*   **Catch Block:** Executes only if an exception is thrown in the try block. It is used to handle the exception (e.g., logging it or providing a fallback).
*   **Finally Block:** Used for cleanup work (e.g., closing file streams or database connections). It executes **regardless** of whether an exception occurred or was caught.

### 3. Hierarchy of Exceptions
The hierarchy starts with the **Throwable** class, which has two main branches:

1.  **Exception:**
    *   **Checked Exceptions (Compile-time):** The compiler forces you to handle these. Examples: `IOException`, `SQLException`, `ClassNotFoundException`.
    *   **Unchecked Exceptions (Runtime):** The compiler does not check for these; they are usually due to logic errors. Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`.
2.  **Error:**
    *   These are serious problems that an application should not try to catch. They are related to the environment/JVM. Examples: `StackOverflowError`, `OutOfMemoryError`, `VirtualMachineError`.

### 4. Exception vs. Error
| Feature | Exception | Error |
| :--- | :--- | :--- |
| **Recovery** | Possible using try-catch blocks. | Impossible; the program will terminate. |
| **Type** | Includes Checked and Unchecked. | Always Unchecked. |
| **Cause** | Related to the application logic. | Related to the environment (JVM/JRE). |
| **Example** | `NullPointerException`, `IOException`. | `StackOverflowError`, `OutOfMemoryError`. |

### 5. Rules for Try-Catch-Finally Blocks
*   **Try alone?** No. A `try` block must be followed by at least a `catch` or a `finally` block. Otherwise, a compile-time error occurs.
*   **Statements between blocks?** No. You cannot write any code between a `try`, `catch`, or `finally` block.
*   **Flow after Exception:** If an exception occurs at a specific line in a `try` block, the remaining lines in that `try` block are **skipped**, and the flow jumps directly to the `catch` or `finally` block.

### 6. 'throw' vs. 'throws'
| Feature | throw | throws |
| :--- | :--- | :--- |
| **Usage** | Used **within** a method to explicitly throw an exception. | Used in the **method signature** to declare exceptions. |
| **Purpose** | To actually trigger an exception. | To warn the caller that this method might throw an exception. |
| **Propagation** | Cannot propagate checked exceptions on its own. | Used to propagate checked exceptions up the call stack. |
| **Quantity** | Can only throw one exception at a time. | Can declare multiple exceptions (comma-separated). |

### 7. Exceptions in the Main Method
If the `main` method throws an exception and has no try-catch block, there is no "parent" to handle it. The **Java Runtime Environment (JRE)** handles it by:
1.  Terminating the program.
2.  Printing the exception stack trace to the console.

### 8. Unreachable Catch Blocks
*   This is a compile-time error.
*   It occurs when you catch a **Parent Exception** (like `Exception`) before a **Child Exception** (like `NullPointerException`).
*   **Rule:** Always catch the most specific (subclass) exception first and the most generic (superclass) exception last.

### 9. Multi-Catch Block (Java 7+)
Introduced to reduce code duplication and improve maintainability.
*   **Syntax:** Allows catching multiple exceptions in a single block using the pipe `|` operator.
*   **Example:** `catch (NullPointerException | SQLException e) { ... }`
*   This is useful when different exceptions require the same handling logic.

### 10. final vs. finally vs. finalize
*   **final:** A keyword/modifier used for restrictions.
    *   *Class:* Cannot be inherited.
    *   *Method:* Cannot be overridden.
    *   *Variable:* Value cannot be changed (constant).
*   **finally:** A block used in exception handling to execute important code (like cleanup) regardless of the exception outcome.
*   **finalize:** A method in the `Object` class. It is called by the **Garbage Collector** just before an object is destroyed to perform cleanup activities. (Note: This is generally discouraged in modern Java).