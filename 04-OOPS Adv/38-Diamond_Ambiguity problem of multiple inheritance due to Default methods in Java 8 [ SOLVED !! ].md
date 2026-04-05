# Detailed Notes: Diamond Problem & Default Methods in Java 8

These notes are based on the video from the "Code Decode" channel, explaining how Java 8 handles the Diamond Problem (ambiguity) arising from the introduction of default methods in interfaces.

---

### 1. Understanding the Background: The Original Diamond Problem
In Java (prior to version 8), multiple inheritance was not allowed with **classes**.

*   **The Conflict:** If a child class extends two parent classes, and both parent classes have a method with the same signature but different implementations, the child class would be "confused" about which version of the method to inherit.
*   **The Old Solution:** Java used **Interfaces** to avoid this. Because interfaces only contained method declarations (no bodies), there was no implementation to be confused about. The child class was forced to provide its own single implementation.

### 2. The Introduction of Default Methods (Java 8)
Java 8 introduced **Default Methods**, which allow interfaces to have methods with actual code blocks (definitions).
*   **Syntax:** Uses the `default` keyword.
*   **Purpose:** To allow interfaces to evolve without breaking existing implementation classes.
*   **The New Risk:** Because interfaces can now have implementations, the Diamond Problem returns. If a class implements two interfaces that both provide a default method with the same name and signature, the compiler faces an ambiguity error.

---

### 3. Case Study: The "Frog" Scenario
The video uses a practical example to demonstrate the conflict:

*   **Interface 1:** `AquaticAnimal`
    *   Method: `default void livesIn() { System.out.println("Lives in water"); }`
*   **Interface 2:** `TerrestrialAnimal`
    *   Method: `default void livesIn() { System.out.println("Lives on land"); }`
*   **The Child Class:** `Frog`
    *   A frog is both aquatic and terrestrial, so it implements both: `class Frog implements AquaticAnimal, TerrestrialAnimal`.

#### The Conflict:
The Java compiler will throw a **Compile-Time Error**:
> *"Duplicate default method names 'livesIn' are inherited from the types TerrestrialAnimal and AquaticAnimal."*

Unlike standard default methods (where the child class can simply ignore them and use the parent's logic), in a multiple inheritance scenario, the developer **must** explicitly resolve the conflict.

---

### 4. The Solution: Manual Override
To solve the Diamond Problem in Java 8, the developer must override the conflicting method in the implementation class and specify which interface's method should be used.

#### The Syntax:
To call a specific parent interface’s default method, use the following format:
`InterfaceName.super.methodName();`

#### Implementation in the `Frog` class:
```java
public class Frog implements AquaticAnimal, TerrestrialAnimal {

    @Override
    public void livesIn() {
        // To choose the Aquatic implementation:
        AquaticAnimal.super.livesIn();
        
        // OR to choose the Terrestrial implementation:
        // TerrestrialAnimal.super.livesIn();
        
        // OR provide a completely new implementation:
        // System.out.println("Lives in both water and land");
    }
}
```

---

### 5. Summary
*   **The Problem:** Ambiguity occurs when a class inherits two different implementations of the same method from two different interfaces.
*   **The Compiler's Role:** Java does not "guess" which method to use. It stops the build and forces the programmer to make a choice.
*   **The Resolution:** 
    1. Override the conflicting method in the child class.
    2. Use `InterfaceName.super.methodName()` to explicitly point to the desired implementation.
*   **Result:** This ensures that the Diamond Problem is solved while still allowing the flexibility of multiple inheritance through interfaces.