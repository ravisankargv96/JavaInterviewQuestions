These notes cover the key concepts, motivations, and technical details regarding **Default Methods** in Java 8 as presented in the "Code Decode" tutorial.

---

### 1. Introduction to Default Methods
Before Java 8, interfaces could only contain abstract methods (methods without a body). If a new method was added to an interface, every single class implementing that interface was forced to provide an implementation, or the code would fail to compile.

**Default Methods** (also known as Defender Methods or Virtual Extension Methods) allow developers to add new methods to interfaces with a default implementation (a method body) without breaking the existing classes that implement those interfaces.

### 2. The "Why": The Need for Default Methods
The primary motivation behind default methods was **Backward Compatibility**.

*   **The Problem:** The Java maintainers wanted to introduce Lambda expressions and Stream API features into the existing Collections framework. For example, they wanted to add the `forEach` method to the `Iterable` interface.
*   **The Risk:** If they added `forEach` as a traditional abstract method, millions of Java applications worldwide would break because their custom collection classes wouldn't have the `forEach` implementation.
*   **The Solution:** By making `forEach` a **default** method in the `Iterable` interface, all existing implementing classes automatically inherited the new functionality without requiring any code changes.

### 3. Syntax and Rules
To create a default method, you must use the `default` keyword within the interface.

**Example:**
```java
interface MyInterface {
    void abstractMethod(); // Traditional abstract method

    default void defaultMethod() {
        System.out.println("This is a default implementation");
    }
}
```

*   **Implementation:** Implementing classes are NOT required to override the default method.
*   **Overriding:** Implementing classes CAN override the default method if they need a different behavior.

### 4. Default Methods and the "Diamond Problem" (Multiple Inheritance)
Java does not support multiple inheritance with classes to avoid the "Diamond Problem," but a class can implement multiple interfaces. This creates a conflict if two interfaces have the same default method signature.

**The Conflict Scenario:**
*   `Interface A` has `default void log(String str)`.
*   `Interface B` has `default void log(String str)`.
*   `Class C` implements both `A` and `B`.

**The Result:** The compiler will throw an error because it doesn't know which `log` method to use.

**The Resolution:** 
The implementing class MUST override the conflicting method. Inside the overridden method, you can either provide custom logic or explicitly call one of the interface's default methods using the following syntax:
```java
public void log(String str) {
    InterfaceA.super.log(str); // Calls the method from Interface A
}
```

### 5. Conflict Resolution Rules
Java follows specific rules to resolve method conflicts:
1.  **Classes Win:** If a class extends a parent class and implements an interface, and both have a method with the same signature, the method in the **class** always takes priority over the interface's default method.
2.  **Sub-interfaces Win:** If `Interface B` extends `Interface A`, and both have a default method, the method in the most specific interface (`Interface B`) will be used.
3.  **Explicit Overriding:** If there is no clear hierarchy (as in the Diamond Problem example above), the programmer must manually resolve the conflict by overriding the method.

### 6. Default Methods vs. Abstract Classes
While default methods make interfaces look similar to abstract classes, there are key differences:

*   **State:** Abstract classes can have instance variables (state). Interfaces cannot have instance variables; they can only have constants (`public static final`).
*   **Constructor:** Abstract classes can have constructors; interfaces cannot.
*   **Inheritance:** A class can extend only one abstract class but can implement multiple interfaces.
*   **Purpose:** Abstract classes are for sharing code among closely related objects. Default methods are primarily for evolving interfaces without breaking existing implementations.

### 7. Summary
*   **Keyword:** `default`.
*   **Purpose:** Backward compatibility and interface evolution.
*   **Key Example:** The addition of `forEach()`, `stream()`, and `parallelStream()` to the Collections API.
*   **Resolution:** If multiple interfaces provide the same default method, the implementing class must provide its own implementation to resolve the ambiguity.