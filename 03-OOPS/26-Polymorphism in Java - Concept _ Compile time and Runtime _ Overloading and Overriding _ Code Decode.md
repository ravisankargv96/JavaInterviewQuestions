# Polymorphism in Java - Detailed Notes

## 1. Introduction to Polymorphism
*   **Definition:** The word "Polymorphism" is derived from two Greek words: *Poly* (many) and *Morphs* (forms). It refers to the ability of an object to take on multiple forms.
*   **Real-world Example:** A **Frog** is polymorphic. It is an animal, but it is also a terrestrial animal (lives on land) and an aquatic animal (lives in water). It behaves differently depending on the environment.
*   **Java Context:** An object is polymorphic if it passes more than one "IS-A" test (inheritance). 
    *   **Crucial Fact:** Every object in Java is inherently polymorphic because every class implicitly extends the `Object` class.

---

## 2. Types of Polymorphism
There are two main types of polymorphism in Java:
1.  **Static Polymorphism** (Compile-time Polymorphism)
2.  **Dynamic Polymorphism** (Runtime Polymorphism)

---

## 3. Static Polymorphism (Method Overloading)
Static polymorphism is achieved through **Method Overloading**. It is resolved by the compiler during compilation.

### Rules for Overloading:
*   Methods must have the **same name**.
*   Methods must have **different parameters**:
    *   Different number of arguments (e.g., `add(int a, int b)` vs `add(int a, int b, int c)`).
    *   Different types of arguments (e.g., `add(int a, int b)` vs `add(double a, double b)`).
*   **Note:** Overloading happens within the **same class**.

### Important Interview Insights:
*   **Return Type:** You **cannot** overload a method by changing only the return type. If the method names and parameters are identical but return types are different, the compiler will throw an ambiguity error.
*   **Type Promotion:** If no exact match for data types is found, Java promotes smaller data types to larger ones (e.g., `int` is promoted to `long`, `float` to `double`).
    *   *Analogy:* A small item can fit into a big box, but a big item cannot fit into a small box.
*   **Ambiguity Problem:** If the compiler finds two methods that are equally valid after type promotion, it throws a compile-time error. 
    *   *Example:* `method(int, long)` and `method(long, int)`. If you call `method(4, 5)`, the compiler won't know which one to choose because both can be promoted.

---

## 4. Dynamic Polymorphism (Method Overriding)
Dynamic polymorphism is achieved through **Method Overriding**. The method call is resolved at **runtime** by the JVM.

### Rules for Overriding:
*   Requires an **IS-A relationship** (Inheritance).
*   The method in the child class must have the **exact same signature** (name, parameters, and types) as the method in the parent class.
*   The method in the child class provides a specific implementation of a method already provided by its parent.

### Concepts within Dynamic Polymorphism:
*   **Upcasting:** When a parent class reference variable points to a child class object.
    *   *Example:* `Animal myDog = new Dog();`
*   **Execution Logic:** Even if the reference is of the parent type, the method of the **actual object** (the child) is called. This is because the memory is allocated to the child object.
*   **Data Members (Variables):** Runtime polymorphism **does not apply to variables**. 
    *   If a parent and child have a variable with the same name, the variable accessed depends on the **reference type**, not the object type. **Variables cannot be overridden.**

---

## 5. Overloading vs. Overriding: Key Differences

| Feature | Method Overloading (Static) | Method Overriding (Dynamic) |
| :--- | :--- | :--- |
| **Relationship** | Happens within the same class. | Happens between Parent and Child classes. |
| **Parameters** | Must be different. | Must be the same. |
| **Binding** | Compile-time. | Runtime. |
| **Return Type** | Can be different (but not enough alone). | Must be the same (or Covariant). |

---

## 6. Advanced Concepts

### Covariant Return Type
*   Introduced in **Java 5**.
*   It allows an overriding method to have a different return type, provided that the return type is a **subclass** of the return type declared in the parent class.
*   *Example:* If the parent method returns `Animal`, the overriding child method can return `Dog`.

### Why use Polymorphism?
*   It allows for **extensibility**: New child classes can be added with their own logic without changing the code that uses the parent reference.
*   It promotes **code reusability** and clean architecture.

---

## 7. Summary of Interview "Gotchas"
1.  **Can we override a static method?** No, static methods are bound to the class, not the object.
2.  **Can we override private methods?** No, they are not visible to the child class.
3.  **Can we achieve polymorphism with data members?** No, only with methods. Variables are resolved at compile-time based on the reference type.
4.  **Can we overload by changing only the return type?** No, this results in a compile-time error.