These detailed notes are based on the video "Java Collections Framework Interview Questions and Answers" by Code Decode, covering the hierarchy, specific implementations, and internal workings of the framework.

---

### 1. The Collection Hierarchy
The Collection Framework is a unified architecture for representing and manipulating collections.

*   **Collection Interface:** The root interface of the hierarchy (located in `java.util`). Most collections (List, Set, Queue) inherit from this.
*   **Map Interface:** This is the only major interface that **does not** extend the `Collection` interface because it handles Key-Value pairs rather than single elements.

#### Sub-Interfaces of Collection:
1.  **List:** 
    *   Maintains insertion order.
    *   Allows duplicate elements.
    *   Supports index-based search and random access.
2.  **Set:**
    *   Does not allow duplicate elements.
    *   Generally unordered (except for specific implementations).
    *   Does not support index-based searches.
3.  **Queue:**
    *   Follows the FIFO (First-In-First-Out) approach.
    *   Elements are added at the rear and removed from the front.

---

### 2. Deep Dive: The List Interface
*   **ArrayList:** 
    *   Dynamic resizing: When full, it grows by **50%** of its original size.
    *   Non-synchronized (not thread-safe).
    *   Fast for random access/search.
*   **LinkedList:**
    *   Implements both `List` and `Deque` interfaces.
    *   Maintains insertion order but does not support random access (must iterate sequentially).
    *   Non-synchronized.
*   **Vector:** 
    *   A legacy class that is **synchronized** (thread-safe).
    *   Increases size by **doubling (100%)** when the capacity is reached.
*   **Stack:** 
    *   Extends `Vector`.
    *   Follows LIFO (Last-In-First-Out) principle.

---

### 3. Deep Dive: The Set Interface
*   **HashSet:** 
    *   Implicitly uses a Hash Table.
    *   Contains unique elements only; allows one `null` element.
    *   Unordered (no guarantee of insertion order).
*   **LinkedHashSet:** 
    *   An ordered version of `HashSet`.
    *   Maintains a doubly-linked list across elements to preserve insertion order.
*   **SortedSet / TreeSet:**
    *   `TreeSet` implements `SortedSet`.
    *   Stores elements in **ascending order** (natural sorting).
    *   Elements must implement the `Comparable` interface.
    *   Internal structure: Uses a **Red-Black Tree** (a self-balancing binary search tree) for fast retrieval and insertion.

---

### 4. Deep Dive: The Queue Interface
*   **PriorityQueue:**
    *   Each element is associated with a priority.
    *   Highest priority elements are served first, regardless of insertion order.
*   **Deque (Double-Ended Queue):**
    *   Elements can be added or removed from both the front and the rear.
*   **ArrayDeque:**
    *   A resizable array implementation of the `Deque` interface.
    *   No capacity restrictions.

---

### 5. Deep Dive: The Map Interface
Maps represent a mapping between a Key and a Value (`K, V`).
*   **HashMap:** 
    *   Non-synchronized.
    *   Allows one `null` key and multiple `null` values.
*   **Hashtable:** 
    *   Synchronized (thread-safe).
    *   **Does not** allow any `null` keys or `null` values.
*   **TreeMap:** 
    *   Implements `SortedMap`.
    *   Maintains entries in ascending order of keys using a **Red-Black Tree**.

**Why Map doesn't extend Collection:**
*   `Collection` methods use `add(Object o)`, while `Map` requires `put(Key k, Value v)`.
*   The structures are fundamentally different: `Collection` is a group of objects; `Map` is a group of Key-Value pairs.

---

### 6. Fail-Fast vs. Fail-Safe Iterators
*   **Fail-Fast Iterators:**
    *   Throw a `ConcurrentModificationException` if the collection is structurally modified (added/deleted) while a thread is iterating over it.
    *   Example: `ArrayList`, `HashMap`.
*   **Fail-Safe Iterators:**
    *   Do not throw exceptions even if the collection is modified.
    *   They work on a **clone/copy** of the collection rather than the original.
    *   Example: `CopyOnWriteArrayList`, `ConcurrentHashMap`.

---

### 7. BlockingQueue
*   Found in `java.util.concurrent`.
*   Thread-safe: Multiple threads can insert or take data concurrently.
*   **Blocking behavior:** If a thread tries to "take" from an empty queue, it blocks until an element is available. If a thread tries to "put" into a full queue, it blocks until space is available.

---

### 8. Synchronized vs. Concurrent Collections
*   **Synchronized Collections (e.g., `Collections.synchronizedMap`):**
    *   Lock the **entire** collection for every operation.
    *   If Thread A is writing, Thread B cannot even read. This leads to poor performance.
*   **Concurrent Collections (e.g., `ConcurrentHashMap`):**
    *   Use **Lock Stripping**: The collection is divided into segments.
    *   Only the specific segment being modified is locked.
    *   Multiple threads can work on different segments simultaneously, making them much faster and more scalable.

---

### 9. Internal Working of HashMap
`HashMap` works on the principle of **Hashing**.

1.  **Put Operation (`put(K, V)`):**
    *   The key’s `hashCode()` is used to calculate an index (bucket location).
    *   A `Node` (or `Entry` object) containing the Key and Value is stored at that index.
2.  **Collision Handling:**
    *   If two different keys produce the same index (a collision), a **Singly Linked List** is formed at that bucket location.
    *   The new entry is appended to the linked list.
3.  **Get Operation (`get(K)`):**
    *   The `hashCode()` of the key is used to find the bucket.
    *   The `equals()` method is then used to compare the provided key with the keys in the linked list at that bucket to find the exact match and return the value.

*Note: Both the Key and the Value are stored in the bucket to facilitate correct retrieval during collisions.*