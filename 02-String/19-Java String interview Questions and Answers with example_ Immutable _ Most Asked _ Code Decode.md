These detailed notes are based on the video "Java String Interview Questions and Answers" by Code Decode. They cover the fundamental concepts of Strings in Java, memory management, and common interview scenarios.

---

### 1. Ways to Create a String Object
There are two primary ways to create a String in Java:
*   **String Literal:** `String s1 = "code";`
    *   The string is created/stored in the **String Constant Pool (SCP)**.
*   **Using the `new` Keyword:** `String s2 = new String("code");`
    *   The string is created as an object in the **Heap memory**.

---

### 2. String Constant Pool (SCP)
*   **Definition:** A special memory area within the Heap memory dedicated to storing string literals.
*   **Purpose:** To facilitate **reusability**. If a string literal already exists in the pool, JVM returns the existing reference instead of creating a new object.
*   **Uniqueness:** No two string objects in the SCP can have the same value.
*   **Benefits:** Saves memory and improves application performance.
*   **Garbage Collection:** Objects in the SCP are generally not eligible for garbage collection to ensure they are available for future reuse throughout the application lifecycle.

---

### 3. Memory Allocation: Literal vs. New Keyword
*   **Literal Assignment (`String s1 = "code";`):**
    *   JVM checks the SCP. If "code" exists, it points `s1` to it. If not, it creates a new object in the SCP.
*   **New Keyword (`String s2 = new String("code");`):**
    *   This **always** creates a new object in the Heap memory.
    *   Simultaneously, the JVM checks if the literal ("code") exists in the SCP. If it doesn’t, it creates a copy in the SCP for future use.

---

### 4. Common Interview Question: "How many objects are created?"

**Scenario A:** `String s1 = new String("code");`
*   **Answer:** 2 objects.
*   **Explanation:** One object is created in the Heap (pointed to by `s1`). One object is created in the SCP (unreferenced) for future reusability.

**Scenario B:**
```java
String s1 = "code";
String s2 = new String("code");
```
*   **Answer:** 2 objects.
*   **Explanation:** `s1` creates one object in the SCP. `s2` creates one object in the Heap. Since "code" already exists in the SCP (from `s1`), `s2` does not create a duplicate in the SCP.

**Scenario C:**
```java
String s1 = new String("code");
String s2 = new String("code");
```
*   **Answer:** 3 objects.
*   **Explanation:** Line 1 creates 2 objects (1 Heap, 1 SCP). Line 2 creates 1 object in the Heap. It checks the SCP, finds "code" already exists, so it doesn't create another one there.

---

### 5. String Comparison: `==` vs. `.equals()`
*   **`==` Operator:** Compares the **memory addresses** (references).
    *   If `s1` is in SCP and `s2` is in Heap, `s1 == s2` is `false`.
*   **`.equals()` Method:** Compares the **actual content** of the strings.
    *   If `s1` is "code" and `s2` is "code", `s1.equals(s2)` is `true`, regardless of where they are stored.

---

### 6. The `intern()` Method
*   **Function:** When `intern()` is called on a string object, the JVM looks for that string in the SCP.
    *   If the string is found in the SCP, the reference to the pool object is returned.
    *   If the string is not found, the string is added to the SCP and that reference is returned.
*   **Example:**
    ```java
    String s1 = new String("code");
    String s2 = s1.intern(); // s2 now points to the object in the SCP
    String s3 = "code";
    System.out.println(s2 == s3); // Result: true
    ```

---

### 7. String Immutability
*   **Definition:** Immutable means "unmodifiable" or "unchangeable." Once a String object is created, its content cannot be changed.
*   **Why is String Immutable?**
    1.  **Security:** Strings are used to store sensitive data like usernames, passwords, file paths, and database URLs. If strings were mutable, an unauthorized connection could change the path or password value.
    2.  **Thread Safety:** Since they cannot change, they are inherently thread-safe.
    3.  **SCP Efficiency:** Reusing strings in the pool only works if the strings are immutable. If one reference changed the value, it would affect all other references pointing to that literal.

#### How to prove Immutability with code?
If you try to modify a string, the JVM creates a brand new object rather than modifying the existing one.
```java
String s1 = "code";
s1.concat("decode");
System.out.println(s1); // Prints "code", not "code decode"
```
In the example above, `s1` still points to "code". To see "code decode", you would have to assign the result to a new reference: `s1 = s1.concat("decode");`. At this point, the original "code" object still exists in memory unchanged; `s1` just points to a new memory address. You can verify this by checking the **HashCodes** before and after the operation; they will be different.