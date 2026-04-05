These detailed notes are based on the "Code Decode" interview preparation video for Amdocs. They cover data structures, Java 8, multi-threading, databases, and Spring framework concepts.

---

### **1. Core Interview Themes at Amdocs**
*   **Data Structures:** High emphasis on Linked Lists, Arrays, and Strings.
*   **Java 8:** Deep dives into Streams and functional programming.
*   **Core Concepts:** Strong focus on the "how" and "why" behind Java basics.
*   **Tricky Questions:** Expect logic-based questions that test your understanding of "lazy loading" or corner cases.

---

### **2. Linked List: Delete the Kth Node from the End**
Amdocs frequently asks Linked List questions. Deleting from the end is trickier than the start because a singly linked list only has a `head` pointer.

**The Problem:**
Given a linked list and a number `K`, remove the $K^{th}$ node counting from the end.

**Logic (Length-based approach):**
1.  **Calculate Length:** Traverse the list to find the total number of nodes ($L$).
2.  **Find Index from Start:** The $K^{th}$ node from the end is the $(L - K)$ index from the start (using 0-based indexing).
3.  **Traverse to Target:** Use a `temp` pointer to move to the node just *before* the target (index $L-K-1$).
4.  **Re-link:** Set `temp.next = temp.next.next`. This bypasses the $K^{th}$ node, effectively deleting it.

**Corner Case:**
*   **Deleting the Head:** If $K$ equals the length of the list, you are deleting the first node. In this case, simply update the head: `head = head.next`.

**Alternative Approach:**
*   **Slow/Fast Pointers:** Move a "fast" pointer $K$ steps ahead. Then move both "slow" and "fast" pointers one step at a time. When the "fast" pointer reaches the end, the "slow" pointer will be at the $K^{th}$ node from the end. (This avoids calculating the total length).

---

### **3. Java Multi-threading: Thread Life Cycle**
A thread can exist in one of the following states:
1.  **New:** The thread is created but `start()` has not been called.
2.  **Runnable:** `start()` has been called. The thread is ready to run and waiting for CPU allocation.
3.  **Blocked:** Waiting to acquire a monitor lock to enter a synchronized block/method.
4.  **Waiting:** Waiting indefinitely for another thread to perform an action (e.g., `object.wait()` or `thread.join()`).
5.  **Timed Waiting:** Waiting for a specific period (e.g., `thread.sleep(ms)` or `wait(ms)`).
6.  **Terminated:** The thread has finished executing its `run()` method.

---

### **4. Database: DELETE vs. TRUNCATE**
| Feature | DELETE | TRUNCATE |
| :--- | :--- | :--- |
| **Type** | DML (Data Manipulation Language) | DDL (Data Definition Language) |
| **Filtering** | Supports `WHERE` clause to delete specific rows. | Does not support `WHERE`; removes all rows. |
| **Speed** | Slower (deletes row by row). | Faster (operates on data pages). |
| **Rollback** | Can be rolled back. | Cannot be rolled back (generally). |
| **Space** | Leaves empty space; logs every row. | Deallocates pages; less log space used. |

---

### **5. Spring Framework: IoC, DI, and @Qualifier**

#### **Inversion of Control (IoC) vs. Dependency Injection (DI)**
*   **IoC:** A general **design principle** where the control of object creation and lifecycle is transferred from the programmer to a container/framework.
*   **DI:** A **design pattern** used to implement IoC. It involves "injecting" the required dependencies (objects) into a class (via Constructor, Setter, or Field) rather than the class creating them itself using the `new` keyword.

#### **@Qualifier Annotation**
*   Used when you have multiple beans of the same type.
*   **Example:** If you have an interface `Animal` and two classes `Cat` and `Dog` implementing it, Spring will be confused about which one to inject.
*   **Solution:** Use `@Qualifier("cat")` alongside `@Autowired` to specify exactly which implementation to use.

---

### **6. Internal Working of ArrayList**
*   **Internal Structure:** Uses a dynamic/resizable array.
*   **Initial Capacity:** Defaults to **10**.
*   **Growth Mechanism:**
    1.  Uses a **Load Factor** (typically 0.75).
    2.  When the threshold is reached, a new array is created.
    3.  **New Size:** Usually 50% larger than the old size (Current Capacity + Current Capacity/2).
    4.  The elements from the old array are copied to the new array using `Arrays.copyOf`.
    5.  The old array is marked for garbage collection.

---

### **7. Design Patterns**
*   **Definition:** Reusable solutions to common software problems. They increase development speed and provide proven paradigms.
*   **Singleton Pattern:** Often used for **Database Connections**. Instead of opening a new connection for every query (which is resource-heavy and limited), a Singleton ensures only one instance of the connection exists, managing the load efficiently.
*   **Note:** If a project doesn't face specific "common problems," it might not use certain design patterns. They are tools, not requirements.

---

### **8. Java 8: Intermediate vs. Terminal Operations (The "Lazy" Trap)**
A common "trick" question involves a Stream with intermediate operations but no terminal operation.

**Code Example Logic:**
```java
list.stream()
    .filter(e -> {
        System.out.println("Filtering...");
        return true;
    }); 
```
*   **Result:** Nothing prints to the console.
*   **Why?** Intermediate operations (like `filter`, `map`) are **lazy**. They will not execute unless a **terminal operation** (like `collect`, `forEach`, `count`) is invoked.
*   **Fix:** Adding `.collect(Collectors.toList())` or `.forEach(System.out::println)` triggers the stream pipeline.