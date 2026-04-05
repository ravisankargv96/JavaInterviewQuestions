These notes cover the essential concepts of the **Queue Interface** in the Java Collections Framework, as presented in the "Code Decode" tutorial.

---

### 1. Introduction to Queue
*   **Definition:** A Queue is a data structure designed for holding elements prior to processing. It follows the **FIFO (First-In-First-Out)** principle.
*   **Hierarchy:** The `Queue` is an interface that extends the `Collection` interface. 
*   **Package:** It is part of the `java.util` package.
*   **Ordering:** In a FIFO system, the element added first is the one removed first. However, exceptions exist, such as the `PriorityQueue`, where elements are ordered based on a comparator or natural ordering.

### 2. Key Characteristics
*   **Head and Tail:** Elements are typically inserted at the "Tail" (end) and removed from the "Head" (front).
*   **Null Elements:** Most Queue implementations (like `PriorityQueue`) do not allow the insertion of `null` elements because `null` is used as a special return value by methods like `poll()` and `peek()`.
*   **Capacity:** Queues can be "bounded" (having a fixed capacity) or "unbounded."

### 3. Queue Interface Methods
The Queue interface provides two forms for each of its core operations (Insertion, Removal, Inspection). One form throws an exception if the operation fails, while the other returns a special value (`null` or `false`).

#### A. Insertion (Adding elements)
1.  **`add(E e)`**: Inserts the element. If the element cannot be added (e.g., in a capacity-constrained queue that is full), it throws an `IllegalStateException`.
2.  **`offer(E e)`**: Inserts the element. If the insertion fails (e.g., queue is full), it returns `false` instead of throwing an exception.

#### B. Removal (Deleting elements)
1.  **`remove()`**: Returns and removes the head of the queue. If the queue is empty, it throws a `NoSuchElementException`.
2.  **`poll()`**: Returns and removes the head of the queue. If the queue is empty, it returns `null`.

#### C. Inspection (Viewing the head)
1.  **`element()`**: Retrieves, but does not remove, the head of the queue. If the queue is empty, it throws a `NoSuchElementException`.
2.  **`peek()`**: Retrieves, but does not remove, the head of the queue. If the queue is empty, it returns `null`.

| Operation Type | Throws Exception | Returns Special Value |
| :--- | :--- | :--- |
| **Insert** | `add(e)` | `offer(e)` |
| **Remove** | `remove()` | `poll()` |
| **Examine** | `element()` | `peek()` |

---

### 4. Important Implementations

#### PriorityQueue
*   Elements are ordered according to their **natural ordering** or by a **Comparator** provided at queue construction time.
*   The head of the queue is the *least* element with respect to the specified ordering.
*   It is not thread-safe.

#### Deque Interface (Double Ended Queue)
*   Extends the `Queue` interface.
*   Supports element insertion and removal at both ends (Front and Back).
*   Common implementations include `ArrayDeque` and `LinkedList`.

#### LinkedList
*   While `LinkedList` is a `List`, it also implements the `Deque` (and thus `Queue`) interface.
*   It is ideal when you need a FIFO structure but also require the ability to access elements by index or perform list-based operations.

---

### 5. BlockingQueue (Concurrent Collections)
*   Found in `java.util.concurrent`.
*   A `BlockingQueue` is used in producer-consumer scenarios.
*   It "blocks" the thread:
    *   When trying to enqueue to a full queue.
    *   When trying to dequeue from an empty queue.

---

### 6. Common Interview Questions
*   **Difference between `poll()` and `remove()`?**
    *   `poll()` returns `null` if the queue is empty; `remove()` throws an exception.
*   **Why use `offer()` instead of `add()`?**
    *   `offer()` is preferred in capacity-constrained queues to avoid the overhead of handling exceptions when the queue is full.
*   **Does Queue allow null values?**
    *   Standard implementations like `PriorityQueue` do not allow `null` because `poll()` and `peek()` return `null` to signal the queue is empty.
*   **Which implementation would you use for a stack-like behavior within a Queue?**
    *   The `Deque` interface (using methods like `push()` and `pop()`).