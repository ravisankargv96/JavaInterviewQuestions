These detailed notes are based on the video "Most Asked Java Collection Framework Interview Questions and Answers | Part 2" by Code Decode.

---

### 1. Java LinkedList Implementation
*   **Underlying Data Structure:** Java’s `LinkedList` is implemented as a **Doubly Linked List**.
*   **Mechanism:** Each node contains a pointer to both the **previous** and the **next** element in the sequence.

### 2. ArrayList vs. LinkedList: When to use which?
The choice depends on the underlying data structure and the specific operation frequency:
*   **ArrayList (Dynamic Array):**
    *   **Best for Search:** Supports **Random Access** via index. Search complexity is **O(1)**.
    *   **Worst for Updates:** Adding or removing elements in the middle requires shifting all subsequent elements, making it performance-heavy.
*   **LinkedList (Doubly Linked List):**
    *   **Best for Updates:** Adding or deleting elements only requires changing pointers (previous/next). It does not require shifting elements.
    *   **Worst for Search:** Does not support random access; you must traverse the list from the start to find an element.

---

### 3. Comparison: HashMap vs. TreeMap vs. LinkedHashMap
| Feature | HashMap | TreeMap | LinkedHashMap |
| :--- | :--- | :--- | :--- |
| **Ordering** | Random/No Order | **Sorted Order** (Natural or Comparator) | **Insertion Order** |
| **Complexity** | O(1) for search/insert | **O(log n)** for search/insert | O(1) for search/insert |
| **Null Keys** | Allows **one** null key | **No null keys** (throws NullPointerException) | Allows **one** null key |
| **Structure** | Hash Table (Buckets) | Red-Black Tree | Hash Table + Doubly Linked List |
| **Requirement** | Needs `equals()` & `hashCode()` | Needs `Comparable` or `Comparator` | Needs `equals()` & `hashCode()` |

---

### 4. PriorityQueue
*   **Definition:** A specialized queue where elements are processed based on **priority** rather than the standard FIFO (First-In-First-Out) order.
*   **Behavior:** A higher priority element is served before a lower priority element.
*   **Ordering:** Elements are ordered according to their natural ordering or by a `Comparator` provided at queue construction time.

---

### 5. Using a Custom Class as a Map Key
To use a custom object as a key in a `HashMap`, you must adhere to these rules:
1.  **Override `equals()` and `hashCode()`:** This is mandatory to ensure the Map can locate the correct bucket and identify the specific object.
2.  **Follow the Contract:** If two objects are equal according to `equals()`, they must have the same `hashCode()`.
3.  **Immutability (Best Practice):** It is highly recommended to make the key class **immutable** (e.g., using `final` fields).
    *   **Why?** If the field used in `hashCode()` changes after the object is put into the Map, the `hashCode` changes. You will then look in the wrong bucket during retrieval and "lose" your data.
    *   **Caching:** Immutable keys allow for caching the hash code, improving performance.

---

### 6. How to make an ArrayList Read-Only
To prevent any modifications (add, remove, or set) to an existing `ArrayList`, use the `Collections` utility class:
*   **Method:** `Collections.unmodifiableList(yourList);`
*   **Result:** This returns a "view" of the list that throws an `UnsupportedOperationException` if any modification is attempted.

---

### 7. Four Ways to Traverse/Loop over a Map
Since `Map` does not implement the `Iterable` interface directly, you must iterate over a "view" of the map:
1.  **KeySet with For-Each Loop:** Iterate over `map.keySet()` and use `map.get(key)` to fetch the value.
2.  **KeySet with Iterator:** Use an `Iterator` on the `keySet()`.
3.  **EntrySet with For-Each Loop (Most Efficient):** Iterate over `map.entrySet()`. Each `Entry` object contains both the key and the value, preventing the need for an extra `map.get()` call.
4.  **EntrySet with Iterator:** Use an `Iterator` on the `entrySet()`.

---

### 8. Removing Duplicates from an ArrayList
The most efficient way to remove duplicates while preserving order:
1.  Copy all elements from the `ArrayList` into a **`LinkedHashSet`**. (A `Set` removes duplicates; `Linked` preserves the insertion order).
2.  Clear the original `ArrayList`.
3.  Copy the elements back from the `LinkedHashSet` to the `ArrayList`.

---

### 9. WeakHashMap vs. HashMap
The primary difference is how the **Garbage Collector (GC)** treats the keys:
*   **HashMap:** The Map holds a **Strong Reference** to its keys. Even if the key is no longer used elsewhere in the code, the GC cannot remove it because the HashMap is still referencing it.
*   **WeakHashMap:** Uses **Weak References** for its keys. If a key is no longer referenced anywhere else in the application (pointed to `null`), the Garbage Collector is free to discard that key and remove its corresponding entry from the map automatically.
*   **Use Case:** Ideal for implementing caches where you want entries removed automatically when memory is needed and the keys are no longer in use.

---

### 10. Best Practices in Java Collections Framework
*   **Choose the Right Tool:** Use `Arrays` instead of `ArrayList` if the data size is fixed for better performance.
*   **Initial Capacity:** If you know how many elements you will store, specify the initial capacity in the constructor (e.g., `new ArrayList(100)`). This avoids expensive **resizing** (creating a new array and copying elements).
*   **Program to Interfaces:** Always use the interface as the reference type (e.g., `List list = new ArrayList();`). This allows you to switch implementations (like `LinkedList`) later without changing the rest of your code.
*   **Type Safety:** Always use **Generics** (e.g., `List<String>`) to avoid `ClassCastException` at runtime.
*   **Key Immutability:** Always use immutable classes (like `String` or `Integer`) or your own immutable custom classes as Map keys.