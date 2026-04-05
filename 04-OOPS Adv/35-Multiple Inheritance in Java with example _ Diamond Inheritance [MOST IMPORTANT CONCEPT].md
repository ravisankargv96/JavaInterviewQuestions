# Study Notes: Multiple Inheritance in Java and the Diamond Problem

## 1. Introduction to Multiple Inheritance
Multiple Inheritance is a feature of some object-oriented programming languages where a class can inherit characteristics and features from more than one parent class. 

In Java, **Multiple Inheritance is not supported through classes** to avoid ambiguity, but it can be achieved using **Interfaces**.

---

## 2. The Problem: Why Classes Can’t Do It (The Diamond Problem)
When a child class extends two or more parent classes, a conflict arises if those parent classes contain methods with the same signature.

### Example: The Frog Scenario
*   Suppose we have a `TerrestrialAnimal` class with a method `breathe()`.
*   Suppose we have an `AquaticAnimal` class also with a method `breathe()`.
*   If a `Frog` class extends both, and you call `frog.breathe()`, the Java compiler wouldn't know which implementation to use: the terrestrial one (lungs) or the aquatic one (gills).

To prevent this confusion (Ambiguity), Java does not allow `class Frog extends TerrestrialAnimal, AquaticAnimal`.

---

## 3. The Solution: Interfaces
Interfaces are the key to solving the multiple inheritance issue and achieving 100% abstraction in Java.

### Key Characteristics of Interfaces:
*   **What vs. How:** An interface defines **what** to do (declaration) but not **how** to do it (definition).
*   **Method Declaration:** Methods in an interface end with a semicolon `;` and have no body.
*   **Implementation:** The class that `implements` the interface is responsible for providing the actual logic (the "how").

---

## 4. Practical Implementation Example

### Step 1: The Base Interface
```java
interface Animal {
    void specialCapability(); // Declaration only
}
```

### Step 2: Implementing Classes
Classes like `Dog` or `Frog` implement the interface and provide their own logic using the `@Override` annotation.
*   **Dog:** Implementation says "It barks."
*   **Frog:** Implementation says "It is an amphibian."

### Step 3: Achieving Multiple Inheritance
To demonstrate multiple inheritance, we create two distinct interfaces with the **same method signature**.

1.  **Interface A:** `TerrestrialAnimal` has method `livesIn()`.
2.  **Interface B:** `AquaticAnimal` has method `livesIn()`.

In a class scenario, this would cause an error. However, with interfaces:
```java
class Frog implements TerrestrialAnimal, AquaticAnimal {
    @Override
    public void livesIn() {
        System.out.println("This is an amphibian and can live on land as well as water.");
    }
}
```

---

## 5. Why Interfaces Solve the Conflict
The reason this works without a compile-time error is because of the **lack of definition**:

1.  **No Ambiguity:** Since `TerrestrialAnimal` and `AquaticAnimal` only provide the *name* of the method and not the *code* inside it, there is no conflict of logic.
2.  **Single Implementation:** The `Frog` class provides exactly **one** implementation for the `livesIn()` method. 
3.  **Compiler Satisfaction:** The compiler is happy because the `Frog` class has fulfilled its contract to both interfaces by providing a single, clear definition for the shared method signature.

---

## 6. Key Takeaways for Interviews
*   **Classes:** Multiple inheritance is disallowed to prevent the "Diamond Problem" (ambiguity in method implementation).
*   **Interfaces:** Multiple inheritance is allowed because interfaces only contain declarations. The implementing class provides the single source of truth for the method logic.
*   **Metadata:** Use the `@Override` annotation to indicate that a method is being implemented from an interface or parent class.
*   **Syntax:** A class can implement multiple interfaces using a comma-separated list: `class MyClass implements Interface1, Interface2`.

---

## 7. Summary Table
| Feature | Inheritance via Classes | Inheritance via Interfaces |
| :--- | :--- | :--- |
| **Multiple Parents?** | No | Yes |
| **Conflict Resolution** | Causes Ambiguity (Diamond Problem) | No conflict (Single Implementation) |
| **Method Body** | Contains Definition | Declaration Only (mostly) |
| **Keyword** | `extends` | `implements` |