These notes cover the key Java 8 programming interview questions discussed in the "Code Decode" video, focusing on the Stream API, Functional Interfaces, and Lambda expressions.

---

### 1. Finding Duplicate Elements in a List
**Question:** Given a list of integers, find all the duplicate elements using Stream functions.

**Concept:** 
To find duplicates, you can use a `Set`. The `Set.add()` method returns `false` if the element is already present in the set. By negating this result in a filter, you can identify duplicates.

**Implementation Steps:**
1.  Initialize a `List` of integers.
2.  Create an empty `Set` to track seen elements.
3.  Convert the list to a stream using `.stream()`.
4.  Apply the `.filter()` method: `n -> !set.add(n)`. If the element is already in the set, `add` returns false; negating it makes it true, allowing it through the filter.
5.  **Refinement:** To avoid seeing the same duplicate multiple times (e.g., if the number 80 appears three times), use `.collect(Collectors.toSet())` at the end to get a unique list of duplicate values.

**Code Example:**
```java
List<Integer> list = Arrays.asList(10, 28, 10, 20, 28, 80, 80, 80);
Set<Integer> set = new HashSet<>();

Set<Integer> duplicates = list.stream()
    .filter(n -> !set.add(n))
    .collect(Collectors.toSet());

System.out.println(duplicates); // Output: [10, 28, 80]
```

---

### 2. Multiplying Two Numbers using a Functional Interface
**Question:** Write a Java 8 program to multiply two numbers using a Functional Interface.

**Concept:** 
A Functional Interface is an interface with exactly one abstract method. You can use the `@FunctionalInterface` annotation to ensure this.

**Implementation Steps:**
1.  Define an interface (e.g., `FInterface`).
2.  Define one abstract method: `int multiply(int a, int b);`.
3.  In the main class, use a Lambda expression to provide the implementation for the interface.

**Code Example:**
```java
@FunctionalInterface
interface FInterface {
    int multiply(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        FInterface total = (a, b) -> a * b;
        System.out.println(total.multiply(6, 7)); // Output: 42
    }
}
```

---

### 3. Difference Between `limit()` and `skip()`
**Question:** Explain the difference between the `limit` and `skip` intermediate operations with examples.

**`limit(n)`:**
*   Returns a stream consisting of the elements of the stream, truncated to be no longer than `n` in length.
*   Parameter `n` cannot be negative.
*   It picks the **first** $n$ elements.

**`skip(n)`:**
*   Returns a stream consisting of the remaining elements of the stream after discarding the first `n` elements.
*   Parameter `n` cannot be negative.
*   If `n` is greater than the number of elements in the stream, an empty stream is returned.

**Example:**
*   **List:** `[1, 2, 3, 4, 5, 6, 7, 8, 9]`
*   **limit(3):** Returns `[1, 2, 3]`
*   **skip(3):** Returns `[4, 5, 6, 7, 8, 9]`

---

### 4. Counting Occurrences of Each Word in a String
**Question:** Given a string, count the number of occurrences of each word using Java 8.
*Commonly asked at: Deloitte, EY, PwC, Mdocs.*

**Concept:** 
This is achieved by converting the string into a list of words and then using `Collectors.groupingBy`.

**Implementation Steps:**
1.  **Split the String:** Use `string.split(" ")` to break the string into an array of words based on spaces.
2.  **Convert to List:** Use `Arrays.asList()` to turn the array into a list.
3.  **Stream & Group:** Use the `.stream()` and `.collect()` methods.
4.  **Grouping Logic:** Use `Collectors.groupingBy(Function.identity(), Collectors.counting())`.
    *   `Function.identity()`: Acts as the key (the word itself). Whatever is passed in is returned as the key.
    *   `Collectors.counting()`: Acts as the value (the count of that specific key).

**Code Example:**
```java
String input = "welcome to code decode and code decode welcomes you";
List<String> list = Arrays.asList(input.split(" "));

Map<String, Long> map = list.stream()
    .collect(Collectors.groupingBy(
        Function.identity(), 
        Collectors.counting()
    ));

System.out.println(map);
// Output: {code=2, decode=2, welcome=1, welcomes=1, ...}
```

---

### Key Java 8 Classes/Methods Mentioned:
*   **Stream API:** `stream()`, `filter()`, `collect()`, `limit()`, `skip()`.
*   **Collectors:** `Collectors.toSet()`, `Collectors.groupingBy()`, `Collectors.counting()`.
*   **Function Interface:** `Function.identity()` (Returns a function that always returns its input argument).
*   **Terminal Operations:** `forEach()`, `collect()`.
*   **Intermediate Operations:** `filter()`, `limit()`, `skip()`.