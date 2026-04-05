These notes provide a comprehensive overview of the key concepts discussed in the tutorial regarding Java 8 features. Java 8 is considered one of the most significant releases in the history of the language because it introduced functional programming capabilities to the object-oriented world.

---

### 1. Introduction to Java 8
Java 8 was released in March 2014. Its primary objective was to make code more concise, improve performance (especially for multi-core processors), and introduce a functional style of programming.

**Key Objectives:**
*   Concise and readable code.
*   Enable functional programming.
*   Improve performance through parallel processing.
*   Easier management of collections.

---

### 2. Lambda Expressions
A Lambda expression is an anonymous function—a function that does not have a name, a return type, or access modifiers.

*   **Syntax:** `(parameters) -> { body }`
*   **Example:**
    *   Old Way: 
        ```java
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread running");
            }
        }).start();
        ```
    *   Java 8 Way: 
        ```java
        new Thread(() -> System.out.println("Thread running")).start();
        ```
*   **Benefit:** Reduces boilerplate code significantly.

---

### 3. Functional Interfaces
A functional interface is an interface that contains exactly one abstract method. It can have any number of default or static methods.

*   **Annotation:** `@FunctionalInterface` is used to ensure the interface doesn't accidentally have more than one abstract method.
*   **Predefined Functional Interfaces:**
    *   `Predicate<T>`: Returns a boolean (test method).
    *   `Function<T, R>`: Takes an argument and returns a result.
    *   `Consumer<T>`: Takes an argument and returns nothing.
    *   `Supplier<T>`: Takes no argument and returns a result.

---

### 4. Default and Static Methods
Before Java 8, interfaces could only have abstract methods. If you added a method to an interface, all implementing classes would break.

*   **Default Methods:** Defined using the `default` keyword. They provide a default implementation that implementing classes can use or override. This allows for "backward compatibility."
*   **Static Methods:** Defined using the `static` keyword. They are utility methods that belong to the interface itself and cannot be overridden by implementing classes.

---

### 5. Method References
Method references are a shorthand notation of a Lambda expression to call an existing method.

*   **Syntax:** `Object::methodName`
*   **Types:**
    *   Reference to a static method: `Math::max`
    *   Reference to an instance method: `System.out::println`
    *   Reference to a constructor: `ClassName::new`

---

### 6. Stream API
The Stream API is used to process collections of objects. A "stream" is a sequence of objects that supports various methods which can be pipelined to produce the desired result.

*   **Key Characteristics:**
    *   It does not store data; it operates on the source (Collection, Array).
    *   It is functional in nature (does not modify the source).
*   **Common Operations:**
    *   `filter()`: Selects elements based on a condition.
    *   `map()`: Transforms each element into another form.
    *   `sorted()`: Sorts the elements.
    *   `forEach()`: Iterates through each element.
    *   `collect()`: Converts the stream back into a collection (like a List).

---

### 7. Optional Class
To handle `NullPointerException`, Java 8 introduced the `java.util.Optional<T>` class. It is a container object which may or may not contain a non-null value.

*   **Methods:**
    *   `isPresent()`: Returns true if there is a value.
    *   `ifPresent()`: Executes a block of code if the value exists.
    *   `orElse()`: Returns a default value if the object is empty.

---

### 8. Date and Time API (JSR 310)
Prior to Java 8, `java.util.Date` and `java.util.Calendar` were not thread-safe and had poor design. The new API (found in `java.time`) is immutable and thread-safe.

*   **Key Classes:**
    *   `LocalDate`: Represents a date (yyyy-MM-dd).
    *   `LocalTime`: Represents time (HH:mm:ss).
    *   `LocalDateTime`: Represents both date and time.
    *   `ZonedDateTime`: Handles time zones.
    *   `Period` / `Duration`: Calculates the difference between dates/times.

---

### 9. Nashorn JavaScript Engine
Java 8 introduced Nashorn, a significantly faster JavaScript engine that allowed developers to embed JavaScript within Java applications. Note: This has since been deprecated/removed in later versions of Java (Java 15+), but it was a core feature of the Java 8 release.

---

### 10. Key Interview Questions and Answers

**Q: What is the main benefit of Java 8?**
A: It facilitates functional programming, reduces code verbosity through Lambdas, and improves processing speed using the Stream API and parallel processing.

**Q: Difference between `map()` and `flatMap()`?**
A: `map()` is used for simple transformation (one-to-one mapping). `flatMap()` is used for transformation and flattening (one-to-many mapping), useful when dealing with lists within lists.

**Q: Can a functional interface have more than one method?**
A: It can only have one **abstract** method, but it can have multiple **default** and **static** methods.

**Q: What is the purpose of the `forEach()` method?**
A: It is a terminal operation used to iterate over every element of a stream or collection, typically used for printing or performing an action on each item.

**Q: Why was the Optional class introduced?**
A: To provide a type-level solution for representing optional values instead of using `null` references, thereby reducing the frequency of `NullPointerException`.