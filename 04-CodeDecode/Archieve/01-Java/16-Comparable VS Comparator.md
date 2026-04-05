Here are detailed notes from the video comparing **Comparable** and **Comparator** in Java:

### **Comparable vs. Comparator in Java**

**1. Usage in Sorting Methods**

* **Comparable:**
* When using `Arrays.sort()` or `Collections.sort()`, you do **not** need to pass any additional arguments.
* The sorting method automatically calls the `compareTo()` method defined within the class itself to determine the order.
* This represents the "Natural Ordering" of the object.


* **Comparator:**
* You **must** pass the comparator instance as a second argument to the sort method (e.g., `Arrays.sort(list, new NameComparator())`).
* This instructs the sort method to ignore the natural ordering and use the specific logic defined in the comparator.



**2. Package Location**

* **Comparable:**
* Resides in the `java.lang` package.
* Since `java.lang` is automatically imported by default in all Java classes, no explicit import statement is needed to use the `Comparable` interface.


* **Comparator:**
* Resides in the `java.util` package.
* This package contains utility classes and interfaces that enhance capabilities. You **must** explicitly import `java.util.Comparator` to use it.



**3. Sorting Flexibility**

* **Comparable:**
* Provides **single** sorting logic (Natural Ordering).
* You can only implement `compareTo` once in a class. For example, if you define sorting by "Employee ID," that becomes the default and only way to sort using `Comparable`.


* **Comparator:**
* Provides **multiple** sorting logics (Custom Ordering).
* You can create multiple separate comparator classes (e.g., `NameComparator`, `AddressComparator`, `SalaryComparator`) to sort the same object in various ways depending on the requirement.



**4. Impact on Source Code**

* **Comparable:**
* **Modifies Source Code:** You must modify the original class (e.g., the `Employee` class) to implement the `Comparable` interface and override the `compareTo` method.


* **Comparator:**
* **No Source Code Modification:** You do not need to touch the original class code. You can create a completely separate class (e.g., `ABCClass`) that implements `Comparator<Employee>`. This keeps the original model class clean and follows the "Open/Closed Principle" (open for extension, closed for modification).



### **Summary Table**

| Feature           | Comparable                     | Comparator                             |
| ----------------- | ------------------------------ | -------------------------------------- |
| **Ordering Type** | Natural Ordering (Default)     | Custom Ordering                        |
| **Method**        | `compareTo(Object o)`          | `compare(Object o1, Object o2)`        |
| **Package**       | `java.lang` (No import needed) | `java.util` (Import required)          |
| **Flexibility**   | Single sorting sequence        | Multiple sorting sequences possible    |
| **Code Impact**   | Must modify the original class | Can be implemented in a separate class |
| **Argument**      | Not passed to sort method      | Passed as an argument to sort method   |