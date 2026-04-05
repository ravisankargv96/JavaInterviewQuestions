# Java Interview Notes: ArrayList, LinkedList, Vector, and Array

These notes are based on the "Code Decode" tutorial regarding the fundamental differences between various collection types and arrays in Java.

---

## 1. Array vs. ArrayList

### **Key Definitions**
*   **Array:** A fixed-size, basic data structure in Java that can hold primitives or objects.
*   **ArrayList:** Part of the Collection Framework, it is a class that internally uses a dynamic array to store elements.

### **Detailed Differences**
| Feature | Array | ArrayList |
| :--- | :--- | :--- |
| **Size** | **Static:** Size must be defined at initialization and cannot be changed. | **Dynamic:** Resizes automatically when the capacity threshold is reached. |
| **Internal Logic** | Basic data structure. | Internally backed by an **Object Array**. |
| **Data Types** | Supports both **primitives** (int, char) and **objects** (Integer, String). | Supports **only objects**. Primitives are automatically autoboxed into wrapper classes. |
| **Performance** | Generally faster due to fixed size. | Slower during resizing because it must copy elements from the old array to a new one. |
| **Initialization** | Mandatory to specify size or initialize values: `int[] arr = new int[10];` | No need to specify size; default initial capacity is **10**. |
| **Methods/Properties** | Uses the `.length` **variable** to find size. | Uses the `.size()` **method** to find size. |
| **Dimensions** | Can be **multi-dimensional** (e.g., `int[][]`). | Always **single-dimensional**. |
| **Iteration** | Loops (for, for-each) only. | Supports Loops and **Iterators**. |
| **Generics** | Does not support Generics. | Supports **Generics** for type safety. |

**How ArrayList Resizes:** 
When the 11th element is added to an ArrayList of size 10, it creates a new array that is **50% larger** than the original (Size 10 becomes 15). The old elements are copied to the new array.

---

## 2. ArrayList vs. Vector

### **Key Definitions**
*   **Vector:** A legacy collection class introduced in JDK 1.0.
*   **ArrayList:** Introduced in JDK 1.2 as a more efficient alternative to Vector.

### **Detailed Differences**
| Feature | ArrayList | Vector |
| :--- | :--- | :--- |
| **Synchronization** | **Not Synchronized.** Not thread-safe. | **Synchronized.** Thread-safe (only one thread can access a method at a time). |
| **Performance** | **Fast** because there is no overhead for locking/synchronization. | **Slow** because of the overhead of thread safety. |
| **Growth Rate** | Increases size by **50%** of current size. | Increases size by **100%** (it doubles its size). |
| **Legacy Status** | Newer (JDK 1.2). | Legacy (JDK 1.0). |
| **Iteration** | Uses **Iterator**. | Uses **Iterator** and the legacy **Enumeration**. |

**Recommendation:** Programmers generally prefer `ArrayList` over `Vector`. If thread safety is required, it is better to use `Collections.synchronizedList()` on an ArrayList rather than using the legacy Vector class.

---

## 3. ArrayList vs. LinkedList

### **Key Definitions**
*   **ArrayList:** Internally uses a dynamic array. Best for search/retrieval.
*   **LinkedList:** Internally uses a **doubly-linked list** (nodes pointing to previous and next nodes). Best for manipulation (add/delete).

### **Detailed Differences**
| Feature | ArrayList | LinkedList |
| :--- | :--- | :--- |
| **Implementation** | Implements the `List` interface. | Implements both `List` and `Queue/Deque` interfaces. |
| **Manipulation** | **Slower:** Adding/removing in the middle requires shifting all subsequent bits/elements. | **Faster:** Only requires changing the pointers of the neighboring nodes. No shifting required. |
| **Access (Get)** | **Faster:** Supports **Random Access** via index. Time complexity is $O(1)$. | **Slower:** Must traverse the list from the start or end to reach an index. Worst case $O(n)$. |
| **Memory Overhead** | **Lower:** Only stores the actual data in an index. | **Higher:** Each node must store the data plus the addresses of the **next** and **previous** nodes. |
| **Iteration** | Standard Iterator. | Standard Iterator + **Descending Iterator** (to traverse backward). |
| **Initial Capacity** | Default is **10**. | **No default capacity**; an empty list is created initially. |

### **Summary Rule of Thumb:**
*   **Use ArrayList** if your application involves frequent **search and retrieval** operations.
*   **Use LinkedList** if your application involves frequent **insertions or deletions** (data manipulation).

---

## 4. Key Takeaways for Interviews

1.  **Thread Safety:** If a data structure is **not synchronized**, it is usually **faster** but **not thread-safe** (e.g., ArrayList). If it **is synchronized**, it is **thread-safe** but **slower** (e.g., Vector).
2.  **Growth Formula:** 
    *   ArrayList = `Current Capacity + (Current Capacity / 2)`
    *   Vector = `Current Capacity * 2`
3.  **Efficiency:** ArrayList is better for memory and searching. LinkedList is better for constant-time additions/deletions.
4.  **Interfaces:** Remember that `LinkedList` can act as a `Queue` or `Deque`, whereas `ArrayList` cannot.