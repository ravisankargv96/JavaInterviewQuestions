These notes cover the fundamental concepts, implementations, and interview-related aspects of Stacks and Queues in Java as presented in the video.

---

### **1. Introduction to Data Structures**
*   **Definition:** A data structure is a specialized format for organizing, processing, retrieving, and storing data.
*   **Linear Data Structures:** Elements are arranged in a sequence. Stacks and Queues are primary examples of linear data structures.

---

### **2. The Stack Data Structure**

#### **Concept: LIFO (Last In, First Out)**
The last element added to the stack is the first one to be removed. Think of a stack of plates; you add a plate to the top and take the top one off first.

#### **Key Operations:**
1.  **Push:** Adds an element to the top of the stack.
2.  **Pop:** Removes and returns the top element.
3.  **Peek/Top:** Returns the top element without removing it.
4.  **isEmpty:** Checks if the stack is empty.
5.  **isFull:** Checks if the stack has reached its maximum capacity (relevant for array-based implementation).

#### **Java Implementation (Array-based):**
To implement a stack, you need an array to store data and a variable `top` to keep track of the index of the last element.
*   **Initialization:** Set `top = -1`.
*   **Push Logic:** Increment `top`, then add the element at `arr[top]`.
*   **Pop Logic:** Retrieve `arr[top]`, then decrement `top`.

#### **Common Stack Interview Questions:**
*   **Time Complexity:** All basic operations (push, pop, peek) are **O(1)**.
*   **Applications:**
    *   Function calls and Recursion management (Call Stack).
    *   Undo/Redo mechanisms in editors.
    *   Expression parsing (Infix to Postfix conversion).
    *   Reversing a string.

---

### **3. The Queue Data Structure**

#### **Concept: FIFO (First In, First Out)**
The first element added to the queue is the first one to be removed. Think of a line at a ticket counter; the person who arrives first is served first.

#### **Key Operations:**
1.  **Enqueue (Add):** Adds an element to the rear (end) of the queue.
2.  **Dequeue (Remove):** Removes and returns the element from the front of the queue.
3.  **Peek/Front:** Returns the front element without removing it.
4.  **isEmpty:** Checks if the queue is empty.

#### **Java Implementation (Array-based):**
A simple queue uses two pointers: `front` and `rear`.
*   **Enqueue:** Add the element at the `rear` index and increment `rear`.
*   **Dequeue:** Retrieve the element at the `front` index and increment `front`.
*   **Note:** In a simple array implementation, "shifting" elements might be required to reuse space, or a **Circular Queue** can be used to optimize memory.

#### **Common Queue Interview Questions:**
*   **Time Complexity:** Basic operations are **O(1)**.
*   **Applications:**
    *   CPU Task Scheduling.
    *   Handling website traffic/requests.
    *   Breadth-First Search (BFS) algorithm in graphs.
    *   Printer buffer management.

---

### **4. Implementation Details in Java**

The video demonstrates creating custom classes rather than using the built-in `java.util.Stack` or `java.util.Queue` interface to help understand the internal logic.

#### **Example: Stack Class Structure**
```java
class MyStack {
    private int[] arr;
    private int top;
    private int capacity;

    public MyStack(int size) {
        arr = new int[size];
        capacity = size;
        top = -1;
    }

    public void push(int data) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = data;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }
}
```

---

### **5. Key Differences: Stack vs. Queue**

| Feature | Stack | Queue |
| :--- | :--- | :--- |
| **Principle** | LIFO (Last In First Out) | FIFO (First In First Out) |
| **Ends** | One end (Top) | Two ends (Front and Rear) |
| **Core Method 1** | Push (Add to top) | Enqueue (Add to rear) |
| **Core Method 2** | Pop (Remove from top) | Dequeue (Remove from front) |
| **Visualization** | Vertical pile | Horizontal line |

---

### **6. Practical Interview Tips**
*   **Edge Cases:** Always check for "Overflow" (adding to a full structure) and "Underflow" (removing from an empty structure) during interviews.
*   **Java Collections Framework:** Remember that in real-world Java development, `Deque<Integer> stack = new ArrayDeque<>();` is preferred over the legacy `Stack` class because it is faster and more consistent.
*   **Array vs. Linked List:** Array-based implementations have a fixed size, while Linked List-based implementations are dynamic and can grow as needed.