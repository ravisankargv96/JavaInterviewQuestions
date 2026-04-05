These detailed notes cover the key concepts of Concurrent Collections as discussed in the 'Code Decode' video, specifically focusing on the transition from traditional to concurrent collections and the internal mechanics of `ConcurrentHashMap`.

---

### 1. The Evolution of Collections in Java
Before Java 1.5, developers faced three main issues with collections in multi-threaded environments:
*   **Non-Thread Safety:** Standard collections like `HashMap` and `ArrayList` are not thread-safe.
*   **Performance Bottlenecks:** Legacy classes like `Vector` and `Hashtable`, or using `Collections.synchronizedMap()`, provide thread safety by locking the **entire object**. This means if one thread is writing, all other threads (even those wanting to read unrelated data) must wait, significantly decreasing performance.
*   **ConcurrentModificationException:** In traditional collections, if one thread is iterating over a collection while another thread makes a structural change (adding or removing an element), a `ConcurrentModificationException` is thrown. This is known as "Fail-Fast" behavior.

### 2. Solving the `ConcurrentModificationException`
The video demonstrates that `ConcurrentHashMap` (introduced in Java 1.5) solves the fail-fast problem.
*   **Traditional HashMap Example:** If you have a map of size 3 and try to `put()` a 4th element while an iterator is active, the code will crash with a `ConcurrentModificationException`.
*   **ConcurrentHashMap Solution:** It allows for simultaneous iteration and modification without throwing an exception. It provides "Fail-Safe" (or more accurately, weakly consistent) iterators.

### 3. ConcurrentHashMap vs. Hashtable/SynchronizedMap
The primary difference lies in the **Locking Strategy**:
*   **Hashtable/SynchronizedMap:** Uses a single lock for the whole collection. Only one thread can access the map at a time.
*   **ConcurrentHashMap:** Uses **Segment Locking** (or Bucket-level locking). The map is divided into segments, and only the specific segment being modified is locked. This allows multiple threads to perform write operations on different segments simultaneously.

### 4. Concurrency Level
The **Concurrency Level** is a configuration that determines how many threads can simultaneously update the map without contention.
*   **Default Value:** 16.
*   **Mechanism:** It divides the map into segments. By default, with 16 buckets and a concurrency level of 16, each segment manages 1 bucket.
*   **Segment Calculation:** If you have 16 buckets and set the concurrency level to 8, then each segment will handle 2 buckets (16 / 8 = 2).
*   **Locking:** A thread acquires a lock on a **Segment**, not the whole map. Therefore, if Thread A is working on Segment 0, Thread B can still work on Segment 1.

### 5. Read and Write Operations
*   **Read (get):** This is a **non-blocking** operation. Multiple threads can read from the same or different segments simultaneously without any locks.
*   **Write (put/update):**
    *   **Different Segments:** Two threads can write to different segments at the same time without blocking each other.
    *   **Same Segment:** If two threads try to write to the same segment, the second thread must wait until the first thread releases the segment lock.
*   **Read vs. Write:** A thread can read from a segment while another is writing to it (weakly consistent results).

### 6. The "Null" Restriction in ConcurrentHashMap
Unlike `HashMap`, `ConcurrentHashMap` **does not allow null keys or null values**.
*   **The Reason (Ambiguity):** In a multi-threaded environment, if `map.get(key)` returns `null`, it creates ambiguity:
    1.  Is the value actually `null`?
    2.  Or was the key-value pair removed by another thread between the time you checked `containsKey()` and called `get()`?
*   To prevent this uncertainty (ambiguity), `ConcurrentHashMap` forbids nulls entirely.

### 7. Internal Working of ConcurrentHashMap
*   **Segmented Architecture:** Each segment acts as an independent hash table.
*   **Locking Mechanism:** It uses **ReentrantLock** or **Striped Locking** (locking strips of the data structure).
*   **The Put Process:**
    1.  Calculate the Hash Code of the key.
    2.  Identify the **Segment Index**.
    3.  Acquire the lock for that specific segment.
    4.  Identify the **Bucket Index** within that segment.
    5.  Store the object.
*   **The Get Process:**
    1.  Identify the Segment Index.
    2.  Identify the Bucket Index.
    3.  Retrieve the value (no locking required).

### 8. Key Terminologies for Interviews
*   **Fail-Fast Iterator:** Throws `ConcurrentModificationException` (e.g., `HashMap`).
*   **Fail-Safe/Weakly Consistent Iterator:** Does not throw exception during modification (e.g., `ConcurrentHashMap`).
*   **ModCount:** A variable used in `HashMap` to track structural changes. If `modCount` changes during iteration, the exception is thrown. `ConcurrentHashMap` does not rely on `modCount` in the same way, avoiding the exception.
*   **Segment Locking/Striped Locking:** The technique of locking only a portion of the data structure to increase concurrency.