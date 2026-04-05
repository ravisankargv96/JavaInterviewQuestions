### Detailed Notes: Internal Working of HashMap and HashSet in Java

These notes cover the internal mechanisms, data structures, and logic used by Java's `HashMap` and `HashSet` collections, specifically focusing on the changes introduced in Java 8.

---

### 1. Internal Structure of HashMap
A `HashMap` is essentially an array of **Nodes** (also referred to as buckets). 

**The Node Structure:**
Each element in the array is a `Node<K, V>` object which contains four fields:
1.  **int hash:** The hash value of the key.
2.  **K key:** The key stored in the map.
3.  **V value:** The value associated with the key.
4.  **Node next:** A pointer to the next node in the case of a collision (forming a linked list).

---

### 2. How the `put(K key, V value)` Method Works
When you call `map.put(key, value)`, the following steps occur internally:

1.  **Hash Code Calculation:** The `hashCode()` method of the key is called. Java then applies an internal hash function to this value to ensure the bits are distributed well (minimizing collisions).
2.  **Index Calculation:** The index of the bucket is calculated using the formula:  
    `index = hash & (n-1)` (where `n` is the current capacity of the array).
3.  **Handling Null Keys:** `HashMap` allows one null key. Null keys always result in a hash of 0, meaning they are always stored at index 0.
4.  **Bucket Placement:**
    *   **Scenario A (Empty Bucket):** If the calculated index is empty, a new Node is created and placed there.
    *   **Scenario B (Collision):** If a Node already exists at that index:
        *   The keys are compared using the `equals()` method.
        *   If `equals()` returns `true`, the old value is replaced by the new value.
        *   If `equals()` returns `false`, the new Node is added to the Linked List (at the end in Java 8).

---

### 3. Collision Handling: Java 8 Improvements
Before Java 8, collisions were handled strictly via Linked Lists. This led to a worst-case time complexity of **O(n)** for searches if many keys hashed to the same index.

**The "Treeification" Process:**
*   In Java 8, if a bucket (Linked List) exceeds a certain threshold—specifically **8 elements** (TREEIFY_THRESHOLD)—the Linked List is converted into a **Balanced Tree (Red-Black Tree)**.
*   **Performance:** This improves the worst-case search time from **O(n)** to **O(log n)**.
*   **Reverse:** If the number of elements in a bucket drops below **6**, the tree is converted back into a Linked List (UNTREEIFY_THRESHOLD).

---

### 4. How the `get(K key)` Method Works
1.  Calculate the `hashCode()` of the key.
2.  Calculate the index using the formula `index = hash & (n-1)`.
3.  Go to the bucket at that index.
4.  Compare the provided key with the key in the bucket using `equals()` and `hashCode`.
5.  If there is a Linked List or Tree at that index, traverse it until the `equals()` method returns `true`.
6.  Return the value associated with the key, or `null` if not found.

---

### 5. Internal Working of HashSet
A common interview revelation is that **HashSet does not have its own unique mechanism.** It uses a `HashMap` internally.

*   When you create a `HashSet`, a private `HashMap` object is instantiated in the background.
*   **The Add Operation:** When you call `set.add(value)`, the `HashSet` takes that value and puts it into the internal `HashMap` as a **Key**.
*   **The Value:** Since a Map requires a key-value pair, the `HashSet` uses a constant dummy object called `PRESENT` (a private static final Object) as the **Value** for every entry.
*   **Uniqueness:** Since `HashMap` keys must be unique, `HashSet` automatically ensures uniqueness by leveraging the Map's key-handling logic.

---

### 6. Key Constants and Parameters
*   **Initial Capacity:** The default initial capacity is **16**. It is always a power of 2.
*   **Load Factor:** The default is **0.75**. This is the measure of how full the map can get before it increases its capacity.
*   **Threshold:** `Capacity * Load Factor`. When the number of elements exceeds this (e.g., 12 for a capacity of 16), the Map is **resized (rehashed)**.
*   **Resizing:** The capacity is doubled, and all existing elements are redistributed into the new, larger array based on their hash.

---

### 7. The contract between `hashCode()` and `equals()`
To work correctly in a `HashMap`, custom objects used as keys must follow this contract:
1.  If `obj1.equals(obj2)` is true, then `obj1.hashCode()` **must** equal `obj2.hashCode()`.
2.  If two objects have the same `hashCode`, they are **not necessarily** equal (this is a collision).
3.  If `hashCode()` is not overridden, the default implementation (memory address) will likely cause the Map to treat identical objects as different keys.

---

### 8. Summary of Complexity
*   **Average Case (Get/Put):** O(1)
*   **Worst Case (Pre-Java 8):** O(n)
*   **Worst Case (Post-Java 8):** O(log n) due to Red-Black Tree optimization.