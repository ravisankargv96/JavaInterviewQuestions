These notes summarize the key concepts and technical interview questions covered in the "Java CODING Interview Questions and Answers (Part 1)" video by Code Decode.

---

### **1. String Object Creation and Memory Management**

**Question: How many objects are created in the following scenarios?**

*   **Scenario A:** `String s = "Code";`
    *   **Result:** 1 object.
    *   **Explanation:** It is created in the **String Constant Pool (SCP)** within the Heap memory. If "Code" already exists in the pool, no new object is created; the reference is simply returned.
*   **Scenario B:** `String s = new String("Decode");`
    *   **Result:** 2 objects.
    *   **Explanation:** One object is created in the **Heap memory** (via the `new` keyword). Another object is created in the **SCP** for future reuse, provided it doesn't already exist there.

**Key Difference: Stack vs. Heap**
*   **Stack:** Stores local variables and reference variables (the "address" of the object).
*   **Heap:** Stores the actual objects and instance variables. SCP is a special area inside the Heap.

---

### **2. Why is String Immutable in Java?**

Immutability means once a String object is created, its value cannot be changed. If you try to modify it (e.g., using `.concat()`), a new String object is created.

**Reasons for Immutability:**
1.  **String Pool (Memory Efficiency):** Multiple reference variables can point to the same String literal. If Strings were mutable, changing the value for one reference would accidentally change it for all others.
2.  **Security:** Strings are widely used for sensitive data (usernames, passwords, database URLs, network connections). If Strings were mutable, a hacker could change the value of a reference after it has been verified.
3.  **Thread Safety:** Because they cannot change, String objects are naturally thread-safe. They can be shared across multiple threads without synchronization issues.
4.  **Caching HashCode:** Since the value doesn't change, the `hashCode` is calculated once and cached. This makes Strings very fast when used as keys in a `HashMap`.

---

### **3. `==` Operator vs. `.equals()` Method**

This is one of the most common interview questions for freshers.

*   **`==` (Reference Comparison):** Checks if both references point to the exact same memory location (address).
*   **`.equals()` (Content Comparison):** Checks if the actual characters inside the String objects are the same.
    *   *Note:* The `Object` class's default implementation of `.equals()` uses `==`. However, the `String` class overrides this method to compare content.

---

### **4. String vs. StringBuffer vs. StringBuilder**

| Feature | String | StringBuffer | StringBuilder |
| :--- | :--- | :--- | :--- |
| **Mutability** | Immutable | Mutable | Mutable |
| **Thread Safety** | Safe (Immutable) | Safe (Synchronized) | Not Safe |
| **Performance** | Slow (creates new objects) | Slower (due to overhead) | Fast |
| **Storage** | String Pool / Heap | Heap | Heap |

**Usage Tip:** Use `StringBuilder` for single-threaded operations involving many string manipulations (like loops) to save memory and time. Use `StringBuffer` only if you need thread safety.

---

### **5. Final, Finally, and Finalize**

These three terms are often confused but serve completely different purposes:

1.  **final (Keyword):**
    *   Used with a **variable**: Makes it a constant (cannot change value).
    *   Used with a **method**: Prevents method overriding.
    *   Used with a **class**: Prevents inheritance.
2.  **finally (Block):**
    *   A block used in `try-catch-finally` structures.
    *   It **always executes** (even if an exception occurs) to perform cleanup tasks like closing database connections or file streams.
3.  **finalize() (Method):**
    *   A method in the `Object` class.
    *   Called by the Garbage Collector (GC) just before an object is destroyed to perform cleanup. 
    *   *Note:* It is not guaranteed when this will run, and it is deprecated in newer Java versions.

---

### **6. Internal Working of HashMap (Brief Introduction)**

While covered more deeply in Part 2, this video touches on:
*   HashMap works on the principle of **Hashing**.
*   It uses `hashCode()` to find the "bucket" location and `.equals()` to find the specific element if multiple objects are in the same bucket (Collision).
*   **Collision:** When two different keys produce the same hash code. Java handles this using a Linked List (or a Balanced Tree in Java 8+).

---

### **Top Interview Tips Mentioned:**
*   **Explain "Why":** Don't just say what a feature does; explain the architectural reason (e.g., "Strings are immutable *because* of the String Pool").
*   **Memory Diagrams:** Mentioning Stack vs. Heap during an interview shows a deeper understanding of the JVM.
*   **Code Examples:** Always be ready to write a quick comparison of `==` and `.equals()` on a whiteboard or shared screen.