### 1. Filter Even Numbers

**Problem:** Given a list of integers, return a list containing only even numbers. 

**Solution:**

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6); // [cite: 5]
List<Integer> evenNumbers = numbers.stream() // [cite: 6]
    .filter(n -> n % 2 == 0) // [cite: 7]
    .collect(Collectors.toList()); // [cite: 7]
```

**Explanation:** The `filter` method is used to apply a condition that keeps only even numbers. The `collect` method gathers the results into a new list. 

### 2. Find Maximum

**Problem:** Find the maximum value in a list of integers. 

**Solution:**


```java
Optional<Integer> max = numbers.stream() // [cite: 13]
    .max(Integer::compare); // [cite: 14]
```

**Explanation:** The `max` method takes a comparator and returns the maximum element wrapped in an `Optional`. 

### 3. Sum of Elements

**Problem:** Calculate the sum of elements in a list of integers. 

**Solution:**

```java
int sum = numbers.stream() // [cite: 20]
    .mapToInt(Integer::intValue) // [cite: 21]
    .sum(); // [cite: 22]
```

**Explanation:** `mapToInt` converts the stream to an `IntStream`, which provides the `sum` method to get the total. 

### 4. List of Names to Uppercase

**Problem:** Convert all strings in a list to uppercase. 

**Solution:**

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie"); // [cite: 27]
List<String> upperNames = names.stream() // [cite: 30]
    .map(String::toUpperCase) // [cite: 31]
    .collect(Collectors.toList()); // [cite: 31]
```

**Explanation:** The `map` function applies `String::toUpperCase` to each element, transforming them to uppercase. 

### 5. Sort List

**Problem:** Sort a list of integers in ascending order. 

**Solution:**

```java
List<Integer> sortedNumbers = numbers.stream() // [cite: 36]
    .sorted() // [cite: 37]
    .collect(Collectors.toList()); // [cite: 38]
```

**Explanation:** The `sorted` method sorts the elements of the stream in natural order. 

### 6. Count Elements

**Problem:** Count the number of elements in a list that are greater than 5. 

**Solution:**

```java
long count = numbers.stream() // [cite: 43]
    .filter(n -> n > 5) // [cite: 44]
    .count(); // [cite: 45]
```

**Explanation:** The `filter` method removes elements that don't satisfy the condition, and `count` returns the number of elements remaining. 

### 7. Get Distinct Elements

**Problem:** Get a list of distinct elements from a list of integers. 

**Solution:**

```java
List<Integer> distinctNumbers = numbers.stream() // [cite: 51]
    .distinct() // [cite: 52]
    .collect(Collectors.toList()); // [cite: 53]
```

**Explanation:** The `distinct` method filters the stream to include only unique elements. 

### 8. Reduce to Sum

**Problem:** Reduce a list of integers to their sum. 

**Solution:**

```java
int total = numbers.stream() // [cite: 58]
    .reduce(0, Integer::sum); // [cite: 59]
```

**Explanation:** The `reduce` method takes an identity (0 in this case) and an accumulator function (`Integer::sum`) to calculate the total. 

### 9. Find Any

**Problem:** Return any element from a list of integers. 

**Solution:**

```java
Optional<Integer> anyElement = numbers.stream() // [cite: 67]
    .findAny(); // [cite: 68]
```

**Explanation:** `findAny` potentially returns any element from the stream, wrapped in an `Optional`. 

### 10. List First Names

**Problem:** Extract first names from a list of full names. 

**Solution:**

```java
List<String> fullNames = Arrays.asList("Alice Johnson", "Bob Harris", "Charlie Lou"); // [cite: 73, 74, 76]
List<String> firstNames = fullNames.stream() // [cite: 78]
    .map(name -> name.split(" ")[0]) // [cite: 77]
    .collect(Collectors.toList()); // [cite: 77]
```

**Explanation:** The `map` function splits each name string and selects the first part.

### 11. All Match

**Problem:** Check if all numbers in a list are positive. 

**Solution:**

```java
boolean allPositive = numbers.stream() // [cite: 84]
    .allMatch(n -> n > 0); // [cite: 85]
```

**Explanation:** `allMatch` returns true if every element in the stream matches the given predicate. 

### 12. None Match

**Problem:** Check if there are no negative numbers in a list. 

**Solution:**

```java
boolean noneNegative = numbers.stream() // [cite: 91]
    .noneMatch(n -> n < 0); // [cite: 92]
```

**Explanation:** `noneMatch` checks that no elements match the negative condition. 

### 13. Find First

**Problem:** Find the first element in a list of integers. 

**Solution:**

```java
Optional<Integer> first = numbers.stream() // [cite: 99]
    .findFirst(); // [cite: 100]
```

**Explanation:** `findFirst` returns the first element of the stream, wrapped in an `Optional`. 

### 14. FlatMap for Nested Lists

**Problem:** Flatten a nested list structure. 

**Solution:**

```java
List<List<Integer>> nestedNumbers = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5)); // [cite: 105]
List<Integer> flatList = nestedNumbers.stream() // [cite: 106]
    .flatMap(List::stream) // [cite: 107]
    .collect(Collectors.toList()); // [cite: 108]
```

**Explanation:** `flatMap` converts each element into its own stream and then merges them into a single stream. 

### 15. Grouping Elements

**Problem:** Group users by age. 

**Solution:**

```java
Map<Integer, List<User>> usersByAge = users.stream() // [cite: 114]
    .collect(Collectors.groupingBy(User::getAge)); // [cite: 115]
```

**Explanation:** The `groupingBy` collector groups elements based on the age property, creating a map where each key is an age and each value is a list of users with that age. 

### 16. Peek Elements

**Problem:** Print elements of a stream during processing without altering the stream.

**Solution:**

```java
List<Integer> peekedAtNumbers = numbers.stream() // [cite: 121]
    .peek(System.out::println) // [cite: 122]
    .collect(Collectors.toList()); // [cite: 122]
```

**Explanation:** `peek` is used for debugging or performing actions without changing the stream. It prints each element before passing it along the stream. 

### 17. Limit Stream

**Problem:** Limit the output to the first 3 elements of the list.

**Solution:**

```java
List<Integer> limited = numbers.stream() // [cite: 131]
    .limit(3) // [cite: 132]
    .collect(Collectors.toList()); // [cite: 133]
```

**Explanation:** `limit` truncates the stream to be no longer than the specified size.

### 18. Skip Elements

**Problem:** Skip the first 2 elements of a list and return the rest.

**Solution:**

```java
List<Integer> skipped = numbers.stream() // [cite: 138]
    .skip(2) // [cite: 139]
    .collect(Collectors.toList()); // [cite: 140]
```

**Explanation:** `skip` discards the first n elements of the stream.

### 19. Convert to Set

**Problem:** Convert a list of integers to a set to remove duplicates.

**Solution:**

```java
Set<Integer> uniqueNumbers = numbers.stream() // [cite: 146]
    .collect(Collectors.toSet()); // [cite: 147]
```

**Explanation:** Collecting the stream into a set automatically removes duplicates. 

### 20. Summarizing Statistics

**Problem:** Get summary statistics for a list of integers.

**Solution:**

```java
IntSummaryStatistics stats = numbers.stream() // [cite: 152]
    .mapToInt(Integer::intValue) // [cite: 153]
    .summaryStatistics(); // [cite: 153]
```

**Explanation:** `summaryStatistics` provides a summary (max, min, average, sum, count) for a stream of integers.