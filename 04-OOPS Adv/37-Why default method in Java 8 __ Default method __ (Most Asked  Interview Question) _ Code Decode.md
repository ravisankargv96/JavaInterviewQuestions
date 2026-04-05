# Detailed Notes: Why Default Methods in Java 8 (Code Decode)

## 1. Introduction to Default Methods
With the release of **Java 8**, the definition of an interface underwent a massive change. Traditionally, a Java interface was a reference type that contained **only method declarations** (method signatures followed by a semicolon) but **no definitions** (no method bodies).

The introduction of **default methods** (also known as defender methods or virtual extension methods) allowed developers to add method bodies inside an interface using the `default` keyword.

## 2. The Problem: Breaking the "Soul" of Interfaces
Before Java 8, if you added a new method to an existing interface, it would immediately break all classes that implemented that interface.

### The Scenario:
*   Imagine a base interface called `Animal`.
*   Imagine this interface is implemented by hundreds of classes (e.g., `Dog`, `Cat`, `Lion`, `Labrador`, etc.) in a large-scale project.
*   If you decide to add a new method, such as `public void legs();`, to the `Animal` interface:
    1.  **Compiler Error:** Every single implementing class will throw an error because they haven't implemented the new method.
    2.  **Maintenance Nightmare:** In a massive codebase, you would have to manually go into every class to provide an implementation, which is time-consuming and error-prone.
    3.  **Abstraction Issue:** If you don't want to implement the method in every class, you would have to mark all those classes as `abstract`, which is often not feasible.

## 3. The Solution: The `default` Keyword
Java 8 solved this issue by allowing interfaces to provide a **default implementation**. 

### How it works:
By adding the `default` keyword before the method signature, you can provide a body `{ ... }` for the method directly within the interface.

**Example Code Structure:**
```java
public interface Animal {
    // Standard abstract method
    void eat();

    // Default method added in Java 8
    default void legs() {
        System.out.println("This animal has 4 legs.");
    }
}
```

### Key Benefits:
1.  **Backward Compatibility:** You can add new functionality to existing interfaces without breaking the existing implementation classes. As soon as the `default` keyword is used, the compiler errors in the implementing classes vanish.
2.  **Instant Inheritance:** All implementing classes automatically "inherit" the default behavior. For instance, a `Dog` class will automatically have access to the `legs()` method without the developer writing a single line of code in the `Dog` class.

## 4. Overriding Default Methods
While default methods provide a "one-size-fits-all" solution, Java 8 also allows for flexibility through **Method Overriding**.

### The "Fish" Example:
*   Most animals have legs, so a default implementation of "4 legs" works for `Dog`, `Cat`, etc.
*   However, a `Fish` class also implements `Animal`, but a fish does not have legs.
*   **The Beauty of Default Methods:** Only the classes that *need* a different behavior need to override the method.
*   The `Fish` class can provide its own specific implementation of `legs()`:

```java
public class Fish implements Animal {
    @Override
    public void eat() {
        System.out.println("Fish eats sea plants.");
    }

    @Override
    public void legs() {
        System.out.println("Fish has no legs.");
    }
}
```

## 5. Summary for Interviews
*   **Definition:** Default methods allow interfaces to have methods with implementation bodies using the `default` keyword.
*   **Primary Reason for Introduction:** To provide **backward compatibility**. It allows developers to evolve interfaces (add new methods) without breaking existing client code.
*   **Impact:** It allows the Java API (like the Collections Framework) to add new methods (like `forEach` in the `Iterable` interface) without requiring every Java developer to update their custom collection classes.
*   **Flexibility:** Implementing classes can either use the default implementation or override it to provide custom logic.