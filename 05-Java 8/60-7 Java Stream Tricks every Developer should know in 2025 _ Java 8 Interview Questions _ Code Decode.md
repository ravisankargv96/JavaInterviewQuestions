These notes cover the essential Java Stream API techniques and "tricks" discussed in the tutorial by Code Decode. These are frequently asked in technical interviews and are vital for writing clean, functional Java code in 2025.

---

### **1. Finding the Frequency of Each Element in a List**
A common requirement is to count how many times each element appears in a collection.
*   **The Trick:** Use `Collectors.groupingBy` combined with `Collectors.counting`.
*   **Logic:** `groupingBy` organizes the elements into a Map where the key is the element itself (`Function.identity()`), and the value is the count.
*   **Example Code:**
    ```java
    List<String> items = Arrays.asList("apple", "apple", "banana", "orange", "banana", "apple");
    Map<String, Long> frequencyMap = items.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    // Output: {orange=1, banana=2, apple=3}
    ```

### **2. Finding Duplicate Elements in a List**
Building on the frequency logic, you can easily filter out which elements appear more than once.
*   **The Trick:** Group the elements first, then filter the resulting map's entry set.
*   **Logic:** After counting frequencies, filter the entries where the value (count) is greater than 1.
*   **Example Code:**
    ```java
    List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5);
    Set<Integer> duplicates = numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
    // Output: [2, 3]
    ```

### **3. Concatenating Two Streams**
Sometimes you need to merge two different data sources into a single stream for processing.
*   **The Trick:** Use the static method `Stream.concat()`.
*   **Logic:** It takes two streams and creates a lazily concatenated stream that contains all the elements of the first stream followed by all the elements of the second stream.
*   **Example Code:**
    ```java
    Stream<String> s1 = Stream.of("Java", "Python");
    Stream<String> s2 = Stream.of("C++", "Go");
    Stream<String> merged = Stream.concat(s1, s2);
    merged.forEach(System.out::println);
    ```

### **4. Partitioning Data into Two Groups**
While `groupingBy` can create multiple groups, `partitioningBy` is specialized for "true/false" scenarios.
*   **The Trick:** Use `Collectors.partitioningBy(Predicate)`.
*   **Logic:** This always returns a `Map<Boolean, List<T>>`. It is more efficient than `groupingBy` when you only have two categories.
*   **Example Case:** Separating passing grades from failing grades.
    ```java
    List<Integer> grades = Arrays.asList(45, 80, 30, 95, 60);
    Map<Boolean, List<Integer>> passingMap = grades.stream()
        .collect(Collectors.partitioningBy(grade -> grade >= 50));
    // Key 'true' contains [80, 95, 60], 'false' contains [45, 30]
    ```

### **5. Sorting a Map Based on Values**
Standard map sorting is usually done by key. Sorting by value requires a specific stream pipeline.
*   **The Trick:** Stream the `entrySet`, use `Map.Entry.comparingByValue()`, and collect into a `LinkedHashMap`.
*   **Logic:** A `LinkedHashMap` is necessary because a regular `HashMap` does not guarantee iteration order.
*   **Example Code:**
    ```java
    Map<String, Integer> map = new HashMap<>();
    map.put("Alice", 85);
    map.put("Bob", 95);
    map.put("Charlie", 80);

    Map<String, Integer> sortedMap = map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(
            Map.Entry::getKey, 
            Map.Entry::getValue, 
            (e1, e2) -> e1, LinkedHashMap::new));
    ```

### **6. Removing Duplicates While Preserving Order**
Using `distinct()` is the standard way to remove duplicates, but if you want to ensure the result is stored in a specific ordered collection:
*   **The Trick:** Use `Collectors.toCollection(LinkedHashSet::new)`.
*   **Logic:** While `distinct()` works in the pipeline, collecting specifically into a `LinkedHashSet` guarantees both uniqueness and insertion order for the final collection.
*   **Example Code:**
    ```java
    List<Integer> numbers = Arrays.asList(5, 1, 2, 1, 5, 3);
    Set<Integer> orderedSet = numbers.stream()
        .collect(Collectors.toCollection(LinkedHashSet::new));
    // Output: [5, 1, 2, 3]
    ```

### **7. Using `peek()` for Debugging vs. Transformation**
Interviewers often ask why `peek()` isn't producing output.
*   **The Trick:** Understand that `peek()` is an **intermediate operation** and is primarily for debugging.
*   **The Logic:**
    1.  `peek()` does not trigger the execution of the stream. You must have a terminal operation (like `collect()` or `forEach()`).
    2.  `peek()` should not be used to modify the state of the elements; use `map()` for transformations.
*   **Example Code:**
    ```java
    List<String> result = Stream.of("one", "two", "three")
        .filter(s -> s.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e)) // Debugging
        .map(String::toUpperCase)
        .collect(Collectors.toList());
    ```

---

### **Summary of Interview Tips**
*   **Laziness:** Always remember that intermediate operations (filter, map, sorted) are lazy and won't execute until a terminal operation (collect, count, forEach) is called.
*   **Identity Function:** `Function.identity()` is a cleaner way to write `t -> t`.
*   **Efficiency:** Use `partitioningBy` instead of `groupingBy` when the condition is a simple boolean predicate to improve performance and readability.
*   **Stateful Operations:** Be careful with `distinct()` and `sorted()` on infinite streams, as these are stateful operations that require seeing all elements before proceeding.