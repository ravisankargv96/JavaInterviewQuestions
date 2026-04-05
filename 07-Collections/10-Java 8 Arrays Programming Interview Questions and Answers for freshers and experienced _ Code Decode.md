These detailed notes are based on the video from the **Code Decode** YouTube channel, focusing on Java 8 coding interview questions involving arrays.

---

### **1. Find the Second Smallest Element in an Array**
The goal is to find the second smallest integer in a given array (e.g., in `[5, 2, 8, 3, 1]`, the output should be `2`).

**Initial Logic:**
1.  Convert the array into a Stream: `Arrays.stream(numbers)`.
2.  Sort the stream in ascending order: `.sorted()`.
3.  Skip the first (smallest) element: `.skip(1)`.
4.  Fetch the first element of the remaining stream: `.findFirst()`.

**Handling Duplicates (The "Tricky" Part):**
If the array is `[1, 1, 2, 3]`, sorting and skipping one element would return `1` again. To solve this, you must use `.distinct()`.

**Code Implementation:**
```java
int secondSmallest = Arrays.stream(numbers)
    .distinct()
    .sorted()
    .skip(1)
    .findFirst()
    .orElseThrow(() -> new IllegalArgumentException("Array does not have a second smallest element"));
```

**Key Concepts:**
*   **`Arrays.stream(arr)`**: Returns an `IntStream` for an `int[]`.
*   **`.distinct()`**: Removes duplicate values.
*   **`.skip(n)`**: Discards the first *n* elements of the stream.
*   **`.findFirst()`**: A short-circuit terminal operation that returns an `Optional`.
*   **`.orElseThrow()`**: Handles cases where the array might have fewer than two unique elements.

---

### **2. Find Common Elements Between Two Arrays**
Given two arrays, find the elements that appear in both (e.g., `[1, 2, 3, 4, 5]` and `[4, 5, 6, 7, 8]` result in `[4, 5]`).

**Logic:**
Iterate through the first array and filter out elements that are also present in the second array.

**Code Implementation:**
```java
List<Integer> commonElements = Arrays.stream(array1)
    .filter(number -> Arrays.stream(array2).anyMatch(n2 -> n2 == number))
    .boxed()
    .collect(Collectors.toList());
```

**Key Concepts:**
*   **`.filter()`**: Retains elements that match the given predicate.
*   **`anyMatch()`**: A short-circuiting terminal operation that returns `true` as soon as a match is found in the second stream.
*   **`.boxed()`**: Converts the primitive `IntStream` to a `Stream<Integer>`. This is necessary because `Collectors.toList()` works with Objects (Integer), not primitives (int).

---

### **3. Reverse an Array of Integers "In-Place"**
Reversing "in-place" means you cannot create a second array; you must modify the original one to save memory.

**Logic:**
Swap the first element with the last, the second with the second-to-last, and so on. You only need to iterate up to the **middle** of the array (`length / 2`).

**Code Implementation:**
```java
IntStream.range(0, array.length / 2).forEach(i -> {
    int temp = array[i];
    array[i] = array[array.length - i - 1];
    array[array.length - i - 1] = temp;
});
```

**Key Concepts:**
*   **`IntStream.range(start, end)`**: Generates a stream of indexes from 0 to the midpoint.
*   **Swapping Logic**: Use a `temp` variable to exchange values at index `i` and index `(length - i - 1)`.
*   **Efficiency**: This is $O(n/2)$ time complexity and $O(1)$ space complexity.

---

### **4. Find the Length of the Longest String in an Array**
Given an array of strings (e.g., `["Apple", "Banana", "Avocado"]`), find the length of the longest string (e.g., `7` for "Avocado").

**Logic:**
If the interviewer only asks for the **length** (and not the string itself), you can map the strings to their lengths first.

**Code Implementation:**
```java
int maxLength = Arrays.stream(strings)
    .mapToInt(String::length) // Method reference
    .max()
    .orElse(0);
```

**Key Concepts:**
*   **`.mapToInt()`**: Transforms a `Stream<String>` into an `IntStream`.
*   **Method Reference (`String::length`)**: A cleaner way to call the `length()` method on each string.
*   **`.max()`**: Returns the highest value in the `IntStream`. Since it returns an `OptionalInt`, use `.orElse()` to provide a default value if the array is empty.

---

### **Quick Reference Table: Java 8 Stream Operations Used**

| Operation | Type | Description |
| :--- | :--- | :--- |
| `distinct()` | Intermediate | Removes duplicates. |
| `sorted()` | Intermediate | Sorts elements in natural order. |
| `skip(n)` | Intermediate | Skips the first *n* elements. |
| `filter()` | Intermediate | Keeps elements matching a condition. |
| `mapToInt()` | Intermediate | Converts Object stream to Primitive Int stream. |
| `boxed()` | Intermediate | Converts Primitive stream to Object stream. |
| `anyMatch()` | Terminal | Returns true if any element matches (Short-circuit). |
| `findFirst()` | Terminal | Returns the first element (Short-circuit). |
| `max()` | Terminal | Returns the maximum element. |
| `collect()` | Terminal | Converts stream results into a Collection (List/Set). |

---

### **Future Topics Teased:**
1.  Removing duplicates while **preserving the original order** (Note: `HashSet` does not preserve order; `LinkedHashSet` or specific Stream logic is required).
2.  The "Product of Array Except Self" problem (Calculating the product of all elements except the one at the current index).