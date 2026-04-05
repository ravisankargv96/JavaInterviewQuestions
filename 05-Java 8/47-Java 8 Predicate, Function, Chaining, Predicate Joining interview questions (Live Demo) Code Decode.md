These detailed notes cover the core concepts, practical demonstrations, and interview-centric insights from the "Code Decode" video regarding Java 8 Functional Interfaces, specifically focusing on **Predicate** and **Function**.

---

### 1. Introduction to Functional Interfaces
*   A Functional Interface is an interface that contains **exactly one abstract method**.
*   Java 8 introduced these to facilitate Lambda Expressions.
*   The video focuses on two primary interfaces found in the `java.util.function` package: `Predicate<T>` and `Function<T, R>`.

---

### 2. Predicate Interface
The `Predicate` interface is used for conditional checks (boolean requirements).

*   **Abstract Method:** `boolean test(T t)`
*   **Purpose:** It takes an input of type `T` and returns a `boolean` (true/false).
*   **Common Use Case:** Filtering data in a collection or Stream.

#### Predicate Chaining (Joining)
You can combine multiple predicates using the following default methods:
1.  **and():** Performs a logical "AND". Both conditions must be true.
    *   *Example:* `p1.and(p2).test(input)`
2.  **or():** Performs a logical "OR". At least one condition must be true.
    *   *Example:* `p1.or(p2).test(input)`
3.  **negate():** Reverses the result (logical NOT).
    *   *Example:* `p1.negate().test(input)`

#### Live Demo Scenario:
*   **Problem:** Check if a string length is greater than 5 AND check if the string is even-numbered.
*   **Logic:** 
    *   `Predicate<String> lenCheck = s -> s.length() > 5;`
    *   `Predicate<String> evenCheck = s -> s.length() % 2 == 0;`
    *   `lenCheck.and(evenCheck).test("CodeDecode");` // Returns true.

---

### 3. Function Interface
The `Function` interface is used for transformation—taking an input and producing an output of a potentially different type.

*   **Abstract Method:** `R apply(T t)`
*   **Type Parameters:** `<T>` is the type of the input, `<R>` is the type of the result.
*   **Purpose:** To perform a calculation or transformation.

#### Function Chaining
Functions can be chained together to create a pipeline of operations:
1.  **andThen():** Executes the caller function first, then the function passed as a parameter.
    *   *Sequence:* `f1.andThen(f2)` -> f1 executes, then f2.
2.  **compose():** Executes the function passed as a parameter first, then the caller function.
    *   *Sequence:* `f1.compose(f2)` -> f2 executes, then f1.

#### Live Demo Scenario:
*   **Problem:** Multiply a number by 2 and then square it.
*   **Logic:**
    *   `Function<Integer, Integer> doubleIt = i -> i * 2;`
    *   `Function<Integer, Integer> squareIt = i -> i * i;`
    *   `doubleIt.andThen(squareIt).apply(2);` // (2*2) = 4, then (4*4) = 16.
    *   `doubleIt.compose(squareIt).apply(2);` // (2*2) = 4, then (4*2) = 8.

---

### 4. Key Differences: Predicate vs. Function

| Feature | Predicate | Function |
| :--- | :--- | :--- |
| **Method Name** | `test(T t)` | `apply(T t)` |
| **Return Type** | Always `boolean` | Any type `R` |
| **Primary Use** | Filtering / Conditional checks | Transformation / Mapping |
| **Chaining Methods** | `and()`, `or()`, `negate()` | `andThen()`, `compose()` |

---

### 5. Interview Questions & Practical Tips

**Q1: Can a Functional Interface have more than one method?**
*   **Answer:** It can only have **one abstract method**, but it can have multiple `default` or `static` methods.

**Q2: What is the difference between `andThen` and `compose`?**
*   **Answer:** It is about the order of execution. `f1.andThen(f2)` means f1 happens first. `f1.compose(f2)` means f2 happens first.

**Q3: When should you use a Predicate over a Function?**
*   **Answer:** Use a Predicate when you need to answer a "Yes/No" question about an object (e.g., is the employee's salary > 5000?). Use a Function when you need to convert an object into something else (e.g., extracting the name of an employee as a String).

**Q4: How do these relate to Streams?**
*   **Answer:** 
    *   The `.filter()` method in Streams takes a `Predicate`.
    *   The `.map()` method in Streams takes a `Function`.

---

### 6. Summary of Code Demo
1.  **Setup:** The demo uses a simple Java class with a `main` method.
2.  **Predicate Example:** Showcased checking if a number is even. Used `.test(10)`.
3.  **Function Example:** Showcased converting a String to its length. Used `.apply("Java")`.
4.  **Complex Chaining:** Demonstrated how to combine multiple predicates to filter a list of strings based on length and content (e.g., starts with 'A').