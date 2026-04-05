Here are detailed notes based on the video about **Comparable and Comparator Implementation in Java**:

### **Comparable and Comparator Implementation**

**1. The Problem Recap**

* Sorting a primitive array (like `int[]`) works automatically using `Arrays.sort()`.
* Sorting an array of custom objects (like `Employee[]`) throws a `ClassCastException` because Java doesn't know *how* to compare two custom objects by default.

**2. Solution 1: Using Comparable Interface**

* **Purpose:** Defines the **default natural sorting order** for a class.
* **Implementation Steps:**
1. The custom class (e.g., `Employee`) must implement the `Comparable<T>` interface.
2. Override the `compareTo(T o)` method.


* **The `compareTo` Logic:**
* The method compares the current object (`this`) with the passed object (`o`).
* **Return Values:**
* `Positive Integer` (e.g., `1`): Current object is greater than the passed object.
* `Negative Integer` (e.g., `-1`): Current object is smaller than the passed object.
* `Zero` (`0`): Both objects are equal.


* **Example Logic (Sorting by ID):**
```java
return this.id - o.id;

```


* If `this.id` (5) > `o.id` (1), result is positive → 5 comes after 1 (Ascending).
* If `this.id` (1) < `o.id` (5), result is negative → 1 comes before 5.




* **Execution:** Once implemented, calling `Arrays.sort(employeeArray)` automatically uses this logic to sort the objects.
* **Limitation:** You can only define **one** sorting logic (e.g., sort by ID) within the class itself. If you change it to sort by Name, you lose the ability to sort by ID easily. It modifies the original class.

**3. Solution 2: Using Comparator Interface**

* **Purpose:** Defines **custom or multiple sorting orders** without modifying the original class. Useful when you can't change the class code (e.g., third-party classes) or need multiple ways to sort.
* **Implementation Steps:**
1. Create a separate class (e.g., `NameComparator`) that implements `Comparator<T>`.
2. Override the `compare(T o1, T o2)` method.


* **The `compare` Logic:**
* Compares two distinct objects `o1` and `o2`.
* **Example Logic (Sorting by Name):**
```java
return o1.getName().compareTo(o2.getName());

```


* *Note:* Since `Name` is a String, we leverage the existing `compareTo` method of the String class.




* **Execution:**
* You must pass the comparator instance to the sort method:
`Arrays.sort(employeeArray, new NameComparator());`


* **Advantage:** You can create unlimited comparator classes (e.g., `SalaryComparator`, `NameComparator`, `DeptComparator`) to sort the objects in different ways without touching the `Employee` class code.

### **Key Differences Summary**

* **Comparable:**
* **Package:** `java.lang`
* **Method:** `compareTo(Object o)` (Takes 1 argument)
* **Usage:** For "Natural" default sorting (single logic).
* **Location:** Implemented inside the class itself.


* **Comparator:**
* **Package:** `java.util`
* **Method:** `compare(Object o1, Object o2)` (Takes 2 arguments)
* **Usage:** For "Custom" sorting (multiple logics allowed).
* **Location:** Implemented in a separate class.