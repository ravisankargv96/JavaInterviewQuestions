These notes are based on the educational content typically found in Java programming tutorials focusing on the fundamental concepts of variables, specifically within the Java 8 context.

---

# Java Variables: Detailed Notes (Part 1)

In Java, a variable is an identifier used to store data. It acts as a container that holds values during the execution of a program. Based on their location and scope, variables in Java are categorized into three main types.

## 1. Classification of Variables
Java variables are classified into three types based on where they are declared and how they behave:
1.  **Instance Variables** (Object-level variables)
2.  **Static Variables** (Class-level variables)
3.  **Local Variables** (Method-level variables)

---

## 2. Instance Variables
Instance variables are those declared inside a class but outside any method, constructor, or block.

*   **Scope:** They are specific to a particular instance (object) of the class. If you change the value of an instance variable in one object, it does not affect the same variable in another object.
*   **Memory Allocation:** Memory is allocated when an object is created and destroyed when the object is garbage collected.
*   **Storage:** They are stored in the **Heap memory**.
*   **Default Values:** If an instance variable is not explicitly initialized, the JVM provides a default value (e.g., `0` for `int`, `null` for objects, `false` for `boolean`).
*   **Access:** They can be accessed directly by instance methods or via an object reference from static methods.

**Example:**
```java
class Student {
    String name; // Instance variable
    int rollNo;  // Instance variable
}
```

---

## 3. Static Variables (Class Variables)
Static variables are declared with the `static` keyword inside a class but outside any method.

*   **Scope:** They are shared across all instances of the class. There is only one copy of a static variable per class, regardless of how many objects are created.
*   **Memory Allocation:** Memory is allocated when the class is loaded into the JVM and destroyed when the class is unloaded.
*   **Storage:** They are stored in the **Method Area** (or Non-Heap memory).
*   **Default Values:** Like instance variables, the JVM provides default values if they are not initialized.
*   **Access:** It is recommended to access them using the **Class Name** (`ClassName.variableName`), although they can also be accessed via object references.

**Example:**
```java
class Student {
    static String collegeName = "Global University"; // Static variable
}
```

---

## 4. Local Variables
Local variables are declared inside a method, constructor, or a block (like `if` or `for` loops).

*   **Scope:** They are only visible within the method or block where they are declared. Once the method execution is complete, the variable is destroyed.
*   **Memory Allocation:** They are created when the method starts and destroyed when the method finishes.
*   **Storage:** They are stored in the **Stack memory**.
*   **Default Values:** **Important:** The JVM does **not** provide default values for local variables. You must initialize them before using them, or the compiler will throw an error.
*   **Access:** They cannot be accessed from outside the method in which they are declared.

**Example:**
```java
void calculate() {
    int x = 10; // Local variable
    System.out.println(x);
}
```

---

## 5. Comparison Summary

| Feature | Instance Variable | Static Variable | Local Variable |
| :--- | :--- | :--- | :--- |
| **Declaration** | Inside class, outside methods | With `static` keyword in class | Inside a method or block |
| **Storage Location** | Heap Memory | Method Area | Stack Memory |
| **Lifetime** | Object creation to destruction | Class loading to unloading | Method start to end |
| **Default Values** | Provided by JVM | Provided by JVM | Not provided (Must initialize) |
| **Number of Copies** | One per object | One per class | Created on every call |

---

## 6. Key Takeaways for Java 8
*   Variables must have a defined data type (Strongly typed language).
*   Variable names should follow camelCase naming conventions (e.g., `myVariableName`).
*   Understanding the difference between the **Heap** (objects/instance variables) and the **Stack** (method calls/local variables) is crucial for memory management.
*   Static variables are ideal for "constants" or properties shared by all objects (like a company name or database URL).