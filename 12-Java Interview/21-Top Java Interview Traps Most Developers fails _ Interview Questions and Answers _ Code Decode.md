These notes provide a detailed breakdown of the common Java interview "traps" and senior-level concepts discussed in the Code Decode video.

---

### 1. The True Lifecycle of `User user = new User();`
Most junior developers assume this line simply creates an object. However, a senior developer should explain the complex JVM-level operations happening in the background.

*   **Step 1: Class Loading:**
    *   JVM checks the **Metaspace** to see if the `.class` file is already loaded.
    *   If not, the ClassLoader loads it, verifies the bytecode, prepares static fields, and initializes static blocks. This happens only once per class.
*   **Step 2: Memory Allocation:**
    *   JVM calculates the object size (including headers, instance variables, and inherited fields).
    *   Memory is allocated in the **Eden Space (Heap)**. At this point, the memory is "raw."
*   **Step 3: Default Initialization (Zeroing Memory):**
    *   **Crucial Concept:** Before the constructor runs, JVM performs "zeroing."
    *   Fields are set to default values: `0` for numbers, `false` for booleans, and `null` for objects.
*   **Step 4: Constructor Invocation (`<init>`):**
    *   The constructor does **not** create the object; it **initializes** it.
    *   The `super()` constructor is called, instance initializer blocks run, and finally, the constructor body executes to assign specific values (e.g., setting ID to 10).
*   **Step 5: Reference Assignment:**
    *   The right side (`new User()`) exists on the **Heap**.
    *   The left side (`user`) is a reference variable stored on the **Stack**.
    *   The Stack variable is assigned the memory address of the Heap object.
*   **Step 6: Garbage Collection (GC) Eligibility:**
    *   If the reference is set to `null` or reassigned, the object on the heap becomes eligible for GC. The JVM decides when to actually deallocate the memory.

---

### 2. Java: Pass by Value vs. Pass by Reference
A common interview trap. **Java is strictly Pass by Value.**

*   **The Misconception:** Many think objects are "Pass by Reference" because changing an object inside a method affects the original.
*   **The Reality:** Java passes the **value of the memory address** (the reference).
*   **Proof by Reassignment:**
    *   If you pass a `User` object to a method and **reassign** that local variable to a `new User()`, the original object in the calling method remains unchanged.
    *   Because only a *copy* of the address was passed, changing the address inside the method doesn't change the address held by the caller.
*   **Confusion via Mutation:**
    *   If you call `user.setName("New Name")` inside a method, the change *is* visible to the caller.
    *   This is not "Pass by Reference"; it is simply two different reference variables (caller's and method's) pointing to the same memory location (Heap) and modifying the data there.
*   **Senior Advice:** To avoid "disasters" caused by accidental mutation, use **Immutable Objects**.

---

### 3. The `equals()` and `hashCode()` Contract
Failing to override both correctly leads to silent data loss in collections like `HashMap` or `HashSet`.

*   **The Non-Negotiable Rule:** If `a.equals(b)` is true, then `a.hashCode()` **must** be the same for both.
*   **The Purpose of Each:**
    *   **`hashCode()`:** Used for **Lookup**. It determines the "Bucket Index" in a Map. It is for performance/speed.
    *   **`equals()`:** Used for **Identity**. It checks if two objects are truly the same within a bucket.
*   **The Failure Scenario:**
    *   If you override `equals` (to compare ID) but not `hashCode`, two users with the same ID will have different hash codes.
    *   When you `put` the user, it goes to Bucket A. When you try to `get` the same user, the Map looks in Bucket B (because the hash code is different).
    *   **Result:** The Map returns `null` even though the object exists. No error is thrown, but your data is "lost."

---

### 4. The Hidden Dangers of Lombok’s `@Data`
While `@Data` reduces boilerplate (Getters, Setters, etc.), it can be a disaster in production if used on domain entities.

*   **Issue 1: Mutable Fields in HashCode:**
    *   `@Data` generates `hashCode()` using **all** fields by default.
    *   If a field (like `name`) is changed after the object is added to a `HashMap`, its `hashCode` changes. You will never be able to find that object again in the Map (Memory Leak/Ghost Entries).
*   **Issue 2: Security/Privacy Leaks:**
    *   `@Data` includes `@ToString`. If your class has a `password` field, printing the object in logs will expose the password in plain text.
*   **Issue 3: StackOverflowError (Circular References):**
    *   In a Bi-directional relationship (e.g., `Order` has a `User`, and `User` has a list of `Orders`), the generated `toString()` will call each other infinitely, leading to a `StackOverflowError`.
*   **Issue 4: Business Logic Conflicts:**
    *   Lombok considers two objects equal only if **every** field matches.
    *   In business, two User objects are usually "equal" if their IDs match, even if their names are different. Lombok's default behavior breaks this logic.

**Best Practice:**
Avoid `@Data` for complex entities. Instead, use:
*   `@Getter` / `@Setter` explicitly.
*   Manual `equals()` and `hashCode()` using only **Immutable/Identity fields** (like ID).
*   `@ToString(exclude = "password")` for security.

---

### Summary Checklist for Seniors:
1.  Explain JVM operations (Class loading -> Zeroing -> Constructor -> Reference).
2.  Maintain that Java is always "Pass by Value."
3.  Respect the `equals`/`hashCode` contract to prevent collection bugs.
4.  Use Lombok annotations selectively; avoid `@Data` on persistent entities.