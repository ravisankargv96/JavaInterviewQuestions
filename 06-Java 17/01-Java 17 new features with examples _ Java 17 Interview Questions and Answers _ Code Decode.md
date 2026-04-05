These notes provide a comprehensive overview of the "Java 17 New Features" video by Code Decode, structured for both learning and interview preparation.

---

### **Overview: Why Java 17 is Important**
Java 17 is a **Long-Term Support (LTS)** version. This makes it a critical update for enterprises because it offers stability and several years of support. Many modern frameworks, such as Spring Boot 3.0, now require Java 17 as the minimum version.

---

### **1. Sealed Classes and Interfaces (JEP 409)**
This is one of the most significant features of Java 17. It allows a developer to restrict which classes or interfaces can extend or implement them.

*   **The Problem:** Previously, a class was either `final` (no one can extend it) or `public` (anyone can extend it). There was no middle ground to allow only specific classes to extend a superclass.
*   **The Solution:** Use the `sealed` and `permits` keywords.
*   **Rules for Subclasses:**
    1.  **Final:** The subclass cannot be extended further.
    2.  **Sealed:** The subclass can be extended, but only by its own permitted subclasses.
    3.  **Non-sealed:** The subclass opens the hierarchy back up to anyone.

**Example:**
```java
public sealed class Shape permits Circle, Square, Triangle {
    // Class body
}

final class Circle extends Shape {}
non-sealed class Square extends Shape {} // Any class can now extend Square
sealed class Triangle extends Shape permits EquilateralTriangle {}
```

---

### **2. Records (JEP 395)**
Records are a concise way to create "Data Carrier" classes (DTOs/POJOs). They significantly reduce boilerplate code.

*   **Automatic Generation:** When you define a record, Java automatically generates:
    *   Private final fields.
    *   A constructor.
    *   Getter methods (named the same as the fields, e.g., `id()` instead of `getId()`).
    *   `equals()`, `hashCode()`, and `toString()` methods.
*   **Immutability:** Records are immutable by design and are implicitly `final`.

**Example:**
```java
// Traditional way: 50 lines of code
// Record way: 1 line
public record Employee(int id, String name) {}

// Usage:
Employee emp = new Employee(101, "John");
System.out.println(emp.name()); // Output: John
```

---

### **3. Pattern Matching for instanceof (JEP 394)**
This feature simplifies the common task of checking an object's type and then casting it.

*   **Old Way:** Check type $\rightarrow$ Cast to variable $\rightarrow$ Use variable.
*   **New Way:** Check and bind to a variable in a single step.

**Example:**
```java
// Old Way
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.toLowerCase());
}

// New Way (Java 17)
if (obj instanceof String s) {
    System.out.println(s.toLowerCase()); // 's' is already cast and ready
}
```

---

### **4. Switch Expressions (JEP 361)**
Switch statements were enhanced to be used as expressions (returning a value) and to use a cleaner syntax.

*   **Arrow Syntax (`->`):** Eliminates the need for `break` statements, preventing accidental "fall-through" bugs.
*   **Yield Keyword:** Used if a multi-line block is needed within a switch case to return a value.

**Example:**
```java
String day = "MONDAY";
int len = switch (day) {
    case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
    case "TUESDAY" -> 7;
    default -> {
        int result = day.length();
        yield result; // Returns value from a block
    }
};
```

---

### **5. Text Blocks (JEP 378)**
Text blocks allow for multi-line string literals without the need for messy concatenation or escape sequences (`\n`).

*   **Syntax:** Use triple quotes `"""`.
*   **Use Case:** Highly useful for SQL queries, JSON strings, or HTML templates.

**Example:**
```java
String json = """
    {
        "id": 1,
        "name": "Code Decode",
        "status": "Active"
    }
    """;
```

---

### **6. Helpful NullPointerExceptions (JEP 358)**
Before Java 17, a `NullPointerException` (NPE) would simply say "Null Pointer Exception" at a specific line. If you had a chain like `a.getB().getC()`, you wouldn't know if `a` or `b` was null.

*   **Improvement:** Java 17 now provides the exact name of the method or variable that was null.
*   **Message Example:** *"Cannot invoke 'B.getC()' because the return value of 'A.getB()' is null."*

---

### **Common Interview Questions & Answers**

**Q1: What is the difference between a normal Class and a Record?**
*   **Answer:** A Record is a specialized class for data storage. It is immutable, final, and automatically generates boilerplate (getters, constructors, etc.). A normal class requires manual implementation of these features and can be mutable.

**Q2: Can a Record extend another class?**
*   **Answer:** No. Records implicitly extend `java.lang.Record`. Since Java doesn't support multiple inheritance of classes, a record cannot extend another class. However, it *can* implement interfaces.

**Q3: What are the three options for a subclass of a Sealed class?**
*   **Answer:** The subclass must be declared as either `final` (stops inheritance), `sealed` (restricted inheritance), or `non-sealed` (open inheritance).

**Q4: How do Switch Expressions improve code safety?**
*   **Answer:** By using the `->` operator, the "fall-through" logic of the traditional switch (where missing a `break` causes multiple cases to execute) is eliminated. It also forces exhaustive coverage (you must cover all possible cases or use a `default`).

**Q5: Is Java 17 a LTS version? Why does it matter?**
*   **Answer:** Yes. LTS means it receives security updates and bug fixes for many years. This makes it the preferred choice for production environments compared to non-LTS versions like Java 18 or 19.