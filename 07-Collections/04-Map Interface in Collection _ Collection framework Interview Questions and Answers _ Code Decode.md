These notes cover the essential concepts of the **Map Interface** within the Java Collection Framework as discussed in the Code Decode tutorial.

---

### **1. Introduction to Map Interface**
*   **Definition:** A Map is an object that maps **keys to values**. It is used to store data in key-value pairs.
*   **Key Characteristics:**
    *   A Map cannot contain duplicate keys.
    *   Each key can map to at most one value.
    *   It is **not** a subtype of the `Collection` interface, but it is considered a core part of the Collection Framework.
    *   Maps are ideal for searching, updating, or deleting elements based on a unique identifier (key).

---

### **2. Map Hierarchy**
The Map interface is implemented by several classes and extended by sub-interfaces:
*   **HashMap:** The most common implementation. It does not guarantee any order of elements.
*   **LinkedHashMap:** Inherits from HashMap but maintains the **insertion order** of elements.
*   **TreeMap:** Implements `SortedMap`. It stores elements in a **sorted order** (natural ordering or a custom comparator).
*   **Hashtable:** A legacy class. It is synchronized and does not allow null keys or values.

---

### **3. Key Differences: HashMap vs. Hashtable**
This is a frequent interview question.
*   **Synchronization:** `HashMap` is non-synchronized (not thread-safe), whereas `Hashtable` is synchronized (thread-safe).
*   **Null Values:** `HashMap` allows one null key and multiple null values. `Hashtable` does not allow any null key or value.
*   **Performance:** `HashMap` is generally faster because it is not synchronized.
*   **Iterating:** `HashMap` uses an Iterator, while `Hashtable` uses an Enumerator (and Iterator).

---

### **4. Internal Working of HashMap (Critical for Interviews)**
The internal implementation of HashMap is based on **Hashing**.

*   **Bucket:** An array of Nodes (Entry objects). Each Node contains:
    1.  `int hash`
    2.  `K key`
    3.  `V value`
    4.  `Node next` (pointer to the next node in case of collision)
*   **The put() Method Process:**
    1.  The `hashCode()` of the key is calculated.
    2.  An index is generated using the formula: `index = hash & (n-1)` (where n is the bucket array size).
    3.  If the bucket at that index is empty, the node is placed there.
    4.  **Collision Handling:** If the index is already occupied, the `equals()` method checks if the keys are the same.
        *   If `equals()` returns true: The old value is replaced by the new value.
        *   If `equals()` returns false: The new node is added to the **Linked List** at that bucket.
*   **Java 8 Improvement (Thresholding):**
    *   Previously, collisions led to long Linked Lists, resulting in $O(n)$ performance.
    *   In Java 8+, if a bucket's linked list exceeds a threshold (8 nodes), it converts into a **Balanced Tree (Red-Black Tree)**, improving performance to $O(\log n)$.

---

### **5. Important Map Methods**
*   `V put(K key, V value)`: Inserts an entry. Returns the previous value associated with the key, or null.
*   `V get(Object key)`: Retrieves the value for a specific key.
*   `V remove(Object key)`: Deletes the entry for the specified key.
*   `boolean containsKey(Object key)`: Checks if a specific key exists.
*   `Set<K> keySet()`: Returns a Set view of all keys in the map.
*   `Collection<V> values()`: Returns a collection view of all values.
*   `Set<Map.Entry<K, V>> entrySet()`: Returns a Set of the mappings contained in the map (useful for iteration).

---

### **6. Why aren't Maps part of the Collection Interface?**
Interviewers often ask why `Map` does not extend `Collection`.
*   **Structure:** The `Collection` interface works with individual objects (e.g., a List of Strings). A `Map` works with key-value pairs.
*   **Methods:** The methods in `Collection` (like `add(E e)`) are incompatible with the Map requirement of two parameters (`put(K, V)`).
*   **Logic:** A Map is a "mapping," whereas a Collection is a "group of objects." They serve fundamentally different architectural purposes.

---

### **7. SortedMap and TreeMap**
*   **SortedMap:** An interface that ensures keys are maintained in an ascending order.
*   **TreeMap:** The implementation of SortedMap.
    *   It uses a **Red-Black Tree** structure.
    *   It provides $O(\log n)$ time cost for `containsKey`, `get`, `put`, and `remove` operations.
    *   Keys must be comparable (either via `Comparable` or a `Comparator`).

---

### **8. Summary Table for Implementation Choice**
| Feature | HashMap | LinkedHashMap | TreeMap |
| :--- | :--- | :--- | :--- |
| **Iteration Order** | Random/Unpredictable | Insertion Order | Natural/Sorted Order |
| **Null Keys/Values** | Allowed | Allowed | Values allowed, Null keys NOT allowed |
| **Performance** | Fastest ($O(1)$) | Slightly slower than HashMap | Slowest ($O(\log n)$) |
| **Internal Data Structure** | Hash Table | Hash Table + Linked List | Red-Black Tree |