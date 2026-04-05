# Detailed Notes: Comparable vs. Comparator

These notes are based on the video by "Code Decode," focusing on the key differences, use cases, and interview-relevant points regarding the `Comparable` and `Comparator` interfaces in Java.

---

### 1. Introduction
When sorting a collection of custom objects (like an `Employee` class), Java needs to know the logic for comparison. Without implementing `Comparable` or `Comparator`, Java will throw an error because it doesn't know whether to sort by ID, Name, or another attribute.

---

### 2. Key Differences

#### A. Package and Imports
*   **Comparable:** Belongs to the `java.lang` package. Since `java.lang` is automatically imported in every Java class, you do not need an extra import statement to use it.
*   **Comparator:** Belongs to the `java.util` package. This is considered a utility interface used to enhance the capabilities of your classes, and it requires an explicit import.

#### B. Sorting Method Arguments
*   **Comparable:** When using `Collections.sort(list)` or `Arrays.sort(array)`, you do **not** need to pass any additional arguments. Java automatically calls the `compareTo()` method defined inside the object's class.
*   **Comparator:** You must pass the comparator instance as an argument: `Collections.sort(list, new NameComparator())`. This tells the sorting method exactly which custom logic to use.

#### C. Natural Ordering vs. Custom Ordering
*   **Comparable (Natural Ordering):** Provides a single, default way to sort objects. For example, if you always want your `Employee` objects sorted by `ID`, you implement `Comparable`.
*   **Comparator (Custom Ordering):** Provides multiple ways to sort the same set of objects. You can create multiple comparator classes (e.g., `NameComparator`, `AgeComparator`, `SalaryComparator`) and use them as needed.

#### D. Modification of Source Code
*   **Comparable:** Requires you to modify the source code of the class you want to sort. Your class must implement the `Comparable` interface and override the `compareTo()` method.
*   **Comparator:** Does not require modifying the original class source code. You can create a separate class that implements `Comparator` and defines the sorting logic there. This is particularly useful when you don't have access to the source code of the class you are trying to sort.

---

### 3. Comparison Summary Table

| Feature | Comparable | Comparator |
| :--- | :--- | :--- |
| **Package** | `java.lang` | `java.util` |
| **Method Name** | `compareTo(Object obj)` | `compare(Object obj1, Object obj2)` |
| **Number of Sorts** | Only one (Natural Ordering) | Multiple (Custom Ordering) |
| **Class Modification** | Original class must be modified | Original class remains unchanged |
| **Sorting Method** | `Collections.sort(list)` | `Collections.sort(list, Comparator)` |

---

### 4. Advantages and Disadvantages

#### Comparable
*   **Advantage:** Easier to use for "default" sorting; no need to manage extra objects for sorting logic.
*   **Disadvantage:** You are stuck with one sorting logic. If you want to change the sorting from "ID" to "Name," you have to change the source code of the actual class.

#### Comparator
*   **Advantage:** Highly flexible. You can define multiple sorting strategies without touching the original entity class. It follows the Open/Closed Principle (Open for extension, Closed for modification).
*   **Disadvantage:** Requires creating additional classes or logic and passing them explicitly every time you sort.

---

### 5. Common Interview Questions
*   **Which one should I use if I want to sort by multiple fields?** Use `Comparator`.
*   **Which one should I use if I cannot modify the class code?** Use `Comparator`.
*   **What is "Natural Ordering"?** It refers to the default sorting logic provided by `Comparable` (like alphabetical for Strings or numerical for Integers).
*   **Can a class implement both?** Yes, a class can have a natural ordering via `Comparable` and still be sorted using various `Comparator` implementations for specific use cases.