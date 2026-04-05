# Study Notes: Static Polymorphism in Java (Method Overloading)

## 1. Introduction to Static Polymorphism
Static polymorphism is also known as **Compile-time Polymorphism**. In Java, this is primarily achieved through **Method Overloading**. It allows a class to have more than one method with the same name, provided their argument lists are different.

The compiler determines which method to call at compile time based on the method signature (the method name and the parameter list).

---

## 2. Ways to Implement Method Overloading
To successfully overload a method in Java, you must change the **method signature**. This can be done in two ways:

### A. Changing the Number of Arguments
You can define methods with the same name but a different count of parameters.
*   **Example:**
    *   `add(int a, int b)` — Handles two integers.
    *   `add(int a, int b, int c)` — Handles three integers.

### B. Changing the Data Types of Arguments
You can keep the number of arguments the same but change their data types.
*   **Example:**
    *   `add(int a, int b)`
    *   `add(int a, long b)` — Even though there are still two arguments, one is now a `long`, making the signature unique.

---

## 3. What Does NOT Count as Overloading
A common misconception is that changing the return type constitutes overloading.
*   **The Rule:** You **cannot** overload a method by changing only its return type.
*   **Why?** The compiler identifies a method by its name and parameters. If two methods have the same name and parameters but different return types (e.g., one is `void` and one is `int`), the compiler will throw a **compile-time error** stating "duplicate method."

---

## 4. Practical Code Implementation
Based on the demonstration, here is how the class structure looks:

```java
class OverloadingExample {
    
    // Method 1: Two integer arguments
    public void add(int a, int b) {
        System.out.println("Method with two int arguments: " + (a + b));
    }

    // Method 2: Overloaded by changing NUMBER of arguments
    public void add(int a, int b, int c) {
        System.out.println("Method with three arguments: " + (a + b + c));
    }

    // Method 3: Overloaded by changing DATA TYPE of arguments
    public void add(int a, long b) {
        System.out.println("Method with two arguments (one is long): " + (a + b));
    }

    // INVALID: Changing only the return type (will cause error)
    // public int add(int a, int b) { return a + b; } 
}
```

---

## 5. Key Concepts & Observations

### The Importance of Parentheses in Print Statements
When printing results, be careful with string concatenation:
*   `System.out.println("Sum is " + a + b);` 
    *   If `a=1` and `b=2`, this outputs: **Sum is 12**. Java treats the numbers as strings because the expression starts with a string.
*   `System.out.println("Sum is " + (a + b));`
    *   This outputs: **Sum is 3**. The parentheses force the addition to happen before the string concatenation.

### Explicitly Calling Methods (Type Promotion)
*   **Integers:** By default, a whole number like `2` is treated as an `int`.
*   **Longs:** To specifically call a method that requires a `long` parameter, append an `L` or `l` to the number (e.g., `obj.add(2, 3L)`).
*   **Upcasting:** In the method `add(int a, long b)`, if you pass an integer for the second argument where a `long` is expected, Java will automatically "upcast" or promote the `int` to a `long`.

### Method Selection
If you call a method with four arguments but have only defined methods with two or three, the compiler will throw an error: *"Method is not applicable for the arguments."* This reinforces that the binding happens at compile time based strictly on the defined signatures.

---

## 6. Summary Table
| Feature | Supported for Overloading? |
| :--- | :--- |
| **Method Name** | Must be the same |
| **Number of Parameters** | Can be different |
| **Type of Parameters** | Can be different |
| **Return Type** | Cannot be the only difference |
| **Binding Time** | Compile-time |