# Java Interview Notes: Code Decode (Part 1)

These notes cover high-level Java interview questions specifically tailored for developers with 3 to 10 years of experience, focusing on internal memory management and collection frameworks.

---

## 1. String Internals and the `substring()` Method

### Internal Representation of Strings
*   **Sequence of Characters:** Internally, a String is not stored as a single literal but as a **character array** (`char[]`).
*   **String Object Fields:** When a new String object is created, it typically contains three important fields:
    1.  **Value Array:** The actual array of characters.
    2.  **Count:** The total number of characters in the string.
    3.  **Offset:** The starting index of the string within the character array.

### How `substring()` Works (Pre-JDK 7)
*   **Mechanism:** When you created a substring (e.g., `str.substring(1, 3)`), Java created a new String object.
*   **Shared Memory:** To save memory and time, the new substring object **shared the same character array** as the original string. It simply adjusted its `offset` and `count` fields to point to the relevant part of the original array.
*   **Immutability:** Since Strings are immutable, sharing the internal array was considered safe.

### The Memory Leak Issue (Java 6 and below)
*   **The Problem:** Suppose you have a very large String (e.g., 1GB character array). You only need a small part of it, so you create a substring of 2 characters. 
*   **GC Failure:** Even if you set the original large String reference to `null`, the character array **cannot be Garbage Collected**. This is because the tiny substring still holds a reference to that massive 1GB internal array.
*   **Definition:** This is a memory leak because memory is being retained even though it is no longer required by the application.

### Solutions to the Memory Leak
1.  **Manual Fix (Java 1.6):** Use the `intern()` method. 
    *   `String sub = bigString.substring(0, 2).intern();`
    *   The `intern()` method places the string in the **String Constant Pool**. This allows the substring to stop referencing the massive heap-based character array, making the original array eligible for Garbage Collection.
2.  **JDK 7+ Internal Fix:** Java changed the implementation of `substring()`. 
    *   Instead of sharing the original character array, the JVM now uses `Arrays.copyOfRange()` to create a **brand-new, smaller character array** specifically for the substring.
    *   The substring no longer references the original array, preventing the memory leak automatically.

---

## 2. HashMap: Load Factor and Capacity

### Key Performance Factors
The performance of a `HashMap` depends primarily on two parameters: **Initial Capacity** and **Load Factor**.

### 1. Initial Capacity
*   This is the number of **buckets** created in the hash table at the time the HashMap is instantiated.
*   Default initial capacity is usually 16.

### 2. Load Factor
*   **Definition:** The load factor is a measure of how full the hash map is allowed to get before its capacity is automatically increased.
*   **Default Value:** 0.75 (75%).
*   **Purpose:** It acts as a trigger for resizing. It balances the trade-off between time complexity (lookups) and space complexity (memory usage).

### 3. Resizing (Rehashing)
*   **The Trigger:** When the number of entries in the map exceeds the product of the **capacity** and the **load factor**, the map is "hashed" again.
*   **The Process:** 
    1.  A new array (bucket array) is created with **double** the capacity of the original.
    2.  All existing elements are moved/copied from the old buckets to the new buckets.
*   **Example:** If capacity is 8 and load factor is 0.75, resizing is triggered when the 7th element is added (8 * 0.75 = 6; once you cross 6 elements, it resizes).

---

## 3. Capacity vs. Size in HashMap

It is common to confuse these two terms during interviews:

| Term | Definition |
| :--- | :--- |
| **Capacity** | The total number of buckets the HashMap *can* hold. It denotes the potential storage. |
| **Size** | The actual number of key-value mappings (entries) currently present in the HashMap. |

**Example:** A HashMap might have a **capacity** of 16 buckets, but if you have only put 5 items into it, its **size** is 5.