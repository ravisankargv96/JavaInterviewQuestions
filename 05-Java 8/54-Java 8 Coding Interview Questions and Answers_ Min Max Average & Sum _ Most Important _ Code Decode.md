# Java 8 Coding Interview Questions & Answers: Summary Statistics, Slicing, and String Joining

These notes are based on the "Code Decode" video covering essential Java 8 Stream API operations frequently asked in technical interviews.

---

## 1. Summary Statistics (Min, Max, Average, Sum, Count)

### The Problem Scenario
In real-time projects, you often need to perform calculations on a collection of objects (e.g., finding the oldest or youngest employee, or the average age in an organization).

**Pre-Java 8 Approach:**
1. Fetch all employees.
2. Extract ages into a list.
3. Sort the list to find Min/Max.
4. Iterate with a `for` loop to calculate Sum and then divide by count for Average.

**Java 8 Approach:**
Use the `summaryStatistics()` method to perform all these operations in a single line.

### Key Implementation Details
To use numeric operations, you must convert a Stream of Objects into a primitive Stream (like `IntStream`, `LongStream`, or `DoubleStream`).

*   **`mapToInt(element -> value)`:** Transforms a stream into an `IntStream`.
*   **`summaryStatistics()`:** A terminal operation that returns an `IntSummaryStatistics` object containing the count, sum, min, max, and average.

### Code Example:
```java
List<Integer> ages = employees.stream()
                              .map(Employee::getAge)
                              .collect(Collectors.toList());

// Perform summary statistics
IntSummaryStatistics stats = ages.stream()
                                 .mapToInt(x -> x)
                                 .summaryStatistics();

System.out.println("Min Age: " + stats.getMin());
System.out.println("Max Age: " + stats.getMax());
System.out.println("Average Age: " + stats.getAverage());
System.out.println("Sum of Ages: " + stats.getSum());
```

---

## 2. Slicing Streams (Skip and Limit)

### The Problem Scenario
How do you "slice" a stream to get a specific subset of data? For example: **Find the 2nd and 3rd youngest employees.**

### Core Methods
1.  **`sorted()`**: Orders the elements (ascending by default).
2.  **`skip(n)`**: Discards the first *n* elements of the stream.
3.  **`limit(n)`**: Truncates the stream so it contains no more than *n* elements. This is a **short-circuit operation**, meaning it stops processing further elements once the limit is reached.

### Implementation:
To find the 2nd and 3rd youngest:
1. Sort the ages.
2. Skip the 1st element (the youngest).
3. Limit the result to the next 2 elements.

### Code Example:
```java
List<Integer> slicedAges = ages.stream()
                               .sorted()
                               .skip(1)  // Discards the youngest
                               .limit(2) // Takes the next two (2nd and 3rd)
                               .collect(Collectors.toList());
```

---

## 3. String Manipulation: Mapping and Joining

### The Problem Scenario
Fetch all employee names, convert them to uppercase, and return them as a single String separated by a specific delimiter (like a comma).

### Key Method: `Collectors.joining()`
The `joining()` collector concatenates the input elements into a single String, separated by a specified delimiter, in the order they are encountered in the stream.

### Implementation Steps:
1.  **`map(String::toUpperCase)`**: Converts each name in the stream to uppercase.
2.  **`collect(Collectors.joining(", "))`**: Merges all the strings into one, separated by a comma and a space.

### Code Example:
```java
String joinedNames = employees.stream()
                              .map(emp -> emp.getName().toUpperCase())
                              .collect(Collectors.joining(", "));

// Output Example: CODE, DECODE, UPDATED1, UPDATED2
```

---

## Summary Table of Operations

| Operation | Type | Description |
| :--- | :--- | :--- |
| `mapToInt()` | Intermediate | Converts a stream to a primitive `IntStream`. |
| `summaryStatistics()` | Terminal | Returns an object containing min, max, sum, avg, etc. |
| `skip(n)` | Intermediate | Skips the first *n* elements. |
| `limit(n)` | Intermediate | Short-circuits the stream after *n* elements. |
| `Collectors.joining()` | Terminal | Concatenates elements into a single string with a delimiter. |

## Key Interview Takeaways
*   **Performance:** Java 8 operations are often more concise and readable than traditional loops.
*   **Primitive Streams:** You cannot call `summaryStatistics()` directly on a `Stream<Integer>`; you must use `mapToInt()` first.
*   **Short-Circuiting:** Methods like `limit()` improve performance because they don't need to process the entire collection once the criteria are met.