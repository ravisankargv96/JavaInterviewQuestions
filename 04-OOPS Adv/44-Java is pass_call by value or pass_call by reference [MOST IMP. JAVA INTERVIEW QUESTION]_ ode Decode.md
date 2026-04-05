These notes provide a comprehensive breakdown of the concepts discussed in the "Code Decode" video regarding Java’s evaluation model.

---

### **1. The Core Conclusion**
In Java, everything is **Pass-by-Value**. There is no such thing as "Pass-by-Reference" in Java. Whether you are dealing with primitive data types or objects, Java always passes a copy of the value held by the variable.

---

### **2. Definitions**
*   **Pass-by-Value:** The method parameter receives a copy of the actual value. Changes made to the parameter inside the method do not affect the original variable in the calling function.
*   **Pass-by-Reference:** The method parameter receives a reference (an alias) to the actual variable. Changes made to the parameter directly affect the original variable. (Java **does not** do this).

---

### **3. Pass-by-Value with Primitives**
When you pass a primitive type (like `int`, `float`, `boolean`) to a method, Java copies the bits representing the value into a new variable on the Stack.

*   **Example Scenario:**
    1.  You have `int x = 10;`.
    2.  You pass `x` to a method `updateValue(int val)`.
    3.  Inside the method, you set `val = 20;`.
    4.  **Result:** The original `x` remains `10`. The change only happened to the copy (`val`).

---

### **4. Pass-by-Value with Objects**
This is where most developers get confused. In Java, when you create an object (e.g., `Balloon b = new Balloon();`), the variable `b` does not hold the object itself. Instead, it holds a **reference** (the memory address) to the object on the Heap.

When you pass an object to a method:
1.  Java copies the **value of the reference** (the memory address).
2.  Both the original variable and the method parameter now point to the **same object** in memory.

#### **A. Modifying Object State**
Because both variables point to the same memory address, if you use the parameter to change a property of the object (e.g., `obj.setColor("Red")`), the change **will** be visible outside the method. This often looks like Pass-by-Reference, but it is actually "Passing a reference by value."

#### **B. Reassigning the Reference**
The proof that Java is Pass-by-Value lies in reassignment.
*   If you pass an object `o1` to a method and inside that method you do `o1 = new Object();`, the original object in the calling method **does not change**.
*   The method parameter was simply a copy of the address; you changed the copy to point to a new address, leaving the original variable pointing to the old address.

---

### **5. Memory Management (Stack vs. Heap)**
*   **Stack Memory:** Stores local variables and the method calls. When a method is called, a new block (frame) is created. This frame contains the copies of the values passed.
*   **Heap Memory:** Stores the actual objects.
*   **The Process:** When a reference variable is passed, the "address" is copied from the caller’s stack frame to the callee’s stack frame. Both frames have different variables pointing to the same Heap location.

---

### **6. Illustrative Example: The Swap Method**
The video explains why a swap method fails in Java:

```java
public void swap(Balloon o1, Balloon o2) {
    Balloon temp = o1;
    o1 = o2;
    o2 = temp;
}
```
*   **What happens?** The method receives copies of the references for `o1` and `o2`.
*   Inside the method, the copies are swapped, so `o1` points to `o2`'s object and vice-versa.
*   **The Outcome:** Once the method finishes, the stack frame is destroyed. The original variables in the main method still point to their original objects. No swap occurred in the caller's scope.

---

### **7. Summary for Interviews**
When asked this question in an interview, you should provide these three points:

1.  **Strictly Pass-by-Value:** Java copies the value of the variable, whether it is a primitive or a reference.
2.  **Reference vs. Object:** For objects, the "value" being passed is the memory address. This allows you to modify the object's attributes, but not the original reference itself.
3.  **The Proof:** If you reassign a passed-in object reference to a new object inside a method, the original reference outside the method remains unchanged. This is impossible in true Pass-by-Reference languages (like C++ with `&` references).

---

### **8. Key Takeaway Tip**
*   **Can you change the object's data?** Yes (because you share the address).
*   **Can you change which object the original variable points to?** No (because you only have a copy of the pointer/address).