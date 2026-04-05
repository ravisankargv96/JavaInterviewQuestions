These detailed notes are based on the **Capgemini Java Interview Questions** video by Code Decode. The content covers Java Core, Hibernate, Spring Boot, Design Patterns, and interview strategies for experienced candidates.

---

### **1. Java Core: Garbage Collection (GC)**
Java uses a **Generational Mark and Sweep** strategy to manage memory.

*   **The Three Phases of Mark & Sweep:**
    1.  **Marking:** The GC identifies which objects are currently in use (live) and which are not.
    2.  **Sweeping:** The GC removes the "dead" objects (those not marked) to free up space.
    3.  **Compaction (Optional):** To prevent memory fragmentation, the GC moves remaining live objects to the start of the heap, creating a contiguous block of free space for easier allocation of new objects.
*   **Generational Strategy:** Based on the principle that "most objects die young."
    *   **Young Generation:** Includes **Eden Space** (where new objects are born) and two **Survivor Spaces (S0 and S1)**. Minor GC happens here.
    *   **Old Generation:** Objects that survive multiple rounds of Minor GC are moved here. Major GC happens here (slower than Minor GC).
    *   **Permanent Generation (PermGen) vs. Metaspace:** In older Java versions, PermGen held metadata. It had a fixed size leading to `OutOfMemoryError`. In Java 8+, it was replaced by **Metaspace**, which resides in native memory and auto-resizes.

---

### **2. The Object Class Methods**
The `Object` class is the parent of all Java classes. Key methods include:
*   **equals(Object obj):** Compares object content (must be overridden; default uses `==`).
*   **hashCode():** Returns a unique integer (memory address representation).
*   **toString():** Returns a string representation of the object.
*   **clone():** Creates a copy of the object (requires implementing `Cloneable`).
*   **getClass():** Returns the runtime class (metadata).
*   **finalize():** Called by the GC just before an object is destroyed (deprecated in newer versions).
*   **wait(), notify(), notifyAll():** Used for inter-thread communication.

---

### **3. Access Specifiers for Top-Level Classes**
Only two access specifiers can be used for a top-level class:
1.  **Public:** Accessible from any other class.
2.  **Default (No keyword):** Accessible only within the same package.
*Note: `private` and `protected` cannot be used for top-level classes.*

---

### **4. Immutable Classes**
An immutable class is one whose state cannot be changed after creation (e.g., `String`, `Integer`).

*   **Why use them?** They are thread-safe, and their `hashCode` can be cached, making them excellent keys for `HashMap`.
*   **How to create one:**
    1.  Make the class **final** (no inheritance).
    2.  Make all fields **private and final**.
    3.  No **setter** methods.
    4.  Initialize all fields via a **constructor**.
    5.  **Deep Cloning:** If the class contains mutable fields (like `Date` or `List`), use deep cloning in the constructor and the getter to ensure the internal reference is never leaked.

---

### **5. Hibernate Concepts**

#### **Load vs. Get**
*   **get():** Eagerly loaded. Hits the database immediately. Returns `null` if the object is not found.
*   **load():** Lazily loaded. Returns a **Proxy** object and only hits the database when an attribute (other than ID) is accessed. Throws `ObjectNotFoundException` if the record doesn't exist. Use `load` only if you are certain the object exists.

#### **Criteria API**
A programmatic, type-safe way to fetch data without writing HQL or SQL. It uses a `Criteria` object and `Restrictions` (like `eq`, `like`, `gt`) to add `WHERE` clauses.

#### **Caching Mechanism**
*   **First Level Cache:** Associated with the **Session** object. Enabled by default. It is local to the current session.
*   **Second Level Cache:** Associated with the **SessionFactory**. It is global across sessions. Must be explicitly enabled (e.g., using EHCache).

#### **Mappings**
JPA/Hibernate allows mapping POJOs to tables:
*   **One-to-One:** One entity belongs to exactly one other entity.
*   **One-to-Many / Many-to-One:** One record in Table A relates to many in Table B (e.g., Department to Employees).
*   **Many-to-Many:** Requires a third "Join Table" to manage the relationship.

---

### **6. Design Patterns: Breaking Singleton**
A Singleton (only one instance per JVM) can be broken in four ways:
1.  **Reflection:** Using `setAccessible(true)` on a private constructor.
2.  **Serialization:** Deserializing a stored object creates a new instance (can be fixed by implementing `readResolve()`).
3.  **Cloning:** Calling `clone()` creates a new object.
4.  **Multiple Class Loaders:** If the same class is loaded by two different class loaders.

---

### **7. Spring Boot: YAML vs. Properties**
While both handle configuration, `.yml` is generally preferred because:
*   **Hierarchy:** Supports a structured, nested format (cleaner than flat `.properties`).
*   **Data Types:** Supports maps, lists, and booleans; `.properties` treats everything as a String.
*   **Profiles:** You can define multiple profiles (Dev, QA, Prod) in a single YAML file using `---` separators.

---

### **8. Multi-threading & Singleton Objects**
**Question:** How can one Singleton DAO object handle multiple requests?
**Answer:** The Singleton object sits in the **Heap** memory. However, every time a request hits the application, a new **Thread** is created. Each thread has its own **Stack** memory. The local variables and execution state are stored in the thread's stack, while they all point to the same DAO reference in the heap. Therefore, as long as the DAO doesn't have instance variables (state), it is thread-safe.

---

### **9. Career Strategy: Explaining New Topics**
If asked about a technology you haven't used (like Microservices) while working on a Monolithic application:

*   **Be Diplomatic:** Don't say "I don't know."
*   **The "Transition" Approach:** Explain that your current project is a Monolith, but the team is currently moving toward a Microservice architecture.
*   **Show Proactivity:** Mention that you are creating **POCs (Proof of Concepts)** and learning the advantages, such as:
    *   Independent deployment.
    *   Better team segregation (e.g., small teams of 5-6 responsible for one service).
    *   Fault isolation (if one service fails, others continue to run).
*   **Result:** This shows you are familiar with the concepts and are actively evolving with industry trends.