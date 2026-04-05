# Detailed Interview Notes: Tricky Core Java Questions (Code Decode)

This document provides detailed notes on tricky Core Java interview questions based on the "Code Decode" tutorial, covering design patterns, exceptions, Java 8, and security best practices.

---

## 1. Breaking the Singleton Design Pattern
A Singleton pattern ensures a class has only one instance and provides a global point of access to it. However, there are four primary ways to "break" this pattern, resulting in multiple instances.

### Four Ways to Break Singleton:
1.  **Reflection:** By using the Reflection API, you can access private constructors. By setting `constructor.setAccessible(true)`, you can create a new instance using `newInstance()`, which will have a different hashcode.
2.  **Serialization:** If a singleton class implements `Serializable`, saving its state to a file (serialization) and reading it back (deserialization) creates a new object. To prevent this, implement the `readResolve()` method.
3.  **Cloning:** If the class implements `Cloneable`, calling `clone()` on the instance creates a new copy. To prevent this, override the `clone()` method to throw an exception or return the same instance.
4.  **Executor Service (Multi-threading):** Improperly synchronized "Lazy Initialization" can lead to multiple threads creating multiple instances simultaneously.

---

## 2. `ClassNotFoundException` vs. `NoClassDefFoundError`
These are often confused but represent different issues in the Java Runtime Environment.

### `ClassNotFoundException` (An Exception)
*   **Type:** Checked Exception (occurs at runtime).
*   **Cause:** Occurs when an application tries to load a class explicitly by its name (string) using methods like `Class.forName()`, `ClassLoader.loadClass()`, or `ClassLoader.findSystemClass()`, but the class definition is not found in the classpath.
*   **Example:** Trying to load a JDBC driver (`com.mysql.jdbc.Driver`) without adding the MySQL connector JAR to the project.

### `NoClassDefFoundError` (An Error)
*   **Type:** Error (subclass of `LinkageError`).
*   **Cause:** Occurs when the class was present at **compile-time**, but the Java Virtual Machine (JVM) cannot find the `.class` file at **runtime**.
*   **Example:** You have Class A and Class B. You compile both. Then, you manually delete or rename `B.class` from the bin folder. When you run the application, the JVM fails to find the definition for B, throwing this error.

| Feature | `ClassNotFoundException` | `NoClassDefFoundError` |
| :--- | :--- | :--- |
| **Category** | Exception | Error |
| **Source** | Thrown by the application (Reflective) | Thrown by the JVM/Runtime system |
| **Scenario** | Classpath missing the JAR | `.class` file missing at runtime |

---

## 3. Predefined Classes as Map Keys
While `String` is the most common key in a `HashMap`, any class can be used. Predefined classes that are ideal candidates (because they are immutable and have well-defined `hashCode` and `equals` methods) include:

*   **String** (Most common).
*   **Wrapper Classes:** `Integer`, `Byte`, `Short`, `Long`, `Float`, `Double`, `Character`, and `Boolean`.
*   **Note:** If using custom objects, ensure they are immutable and correctly override `hashCode()` and `equals()`.

---

## 4. Java 8 Streams: Employee List Scenarios
In interviews, you may be asked to perform complex operations on a list of custom objects (e.g., `Employee`) using Java 8 Streams.

### Scenario: Sorting by Salary in Descending Order
To sort a list based on an attribute:
```java
List<Employee> sortedList = empList.stream()
    .sorted((o1, o2) -> (int)(o2.getSalary() - o1.getSalary()))
    .collect(Collectors.toList());
```
*   `o2 - o1` results in **descending** order.
*   `o1 - o2` results in **ascending** order.

### Advanced Operations:
*   **Fetch Top 3 Salaried Employees:**
    Use `.limit(3)` after the sorting operation.
*   **Fetch Employees with Salary less than the 3rd Highest:**
    Use `.skip(3)`. This skips the first three (highest) records and returns the rest.

---

## 5. Security: `char[]` vs. `String` for Passwords
It is a best practice to store passwords in a **Character Array** (`char[]`) rather than a `String`.

### Why Strings are Unsafe for Passwords:
1.  **Immutability:** Strings are immutable. Once created, they stay in the **String Constant Pool** until Garbage Collection (GC) occurs.
2.  **No Control over GC:** You cannot force the JVM to clear a specific string from memory. If a hacker takes a **Memory Dump**, they can find the plain-text password in the heap area even after you've finished using the variable.

### Why `char[]` is Preferred:
1.  **Mutability:** Character arrays are mutable.
2.  **Explicit Clearing:** Once you are done with the password (e.g., after authentication), you can manually overwrite the array with blanks or zeros.
3.  **Security:** Because the data is wiped immediately, a memory dump will not reveal the sensitive information.

---

## Summary Key Terms for Interviews:
*   **Memory Dump:** A snapshot of a computer's memory at a specific time, often used to extract sensitive data.
*   **Reflection:** A feature that allows an executing Java program to examine or "introspect" upon itself.
*   **Serialization:** The process of converting an object into a byte stream.
*   **Stream API:** A Java 8 feature used to process collections of objects in a functional way.