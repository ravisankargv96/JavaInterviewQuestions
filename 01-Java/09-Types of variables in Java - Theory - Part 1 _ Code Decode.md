### Detailed Notes: Types of Variables in Java (Theory - Part 1)

**Video Title:** Types of variables in Java - Theory - Part 1  
**Channel:** Code Decode

---

#### 1. What is a Variable?
A variable is a container that holds data during the execution of a Java program. It is a name assigned to a memory location. Every variable in Java must be declared with a specific data type, which determines the size and layout of the variable's memory.

**Syntax:**
`data_type variable_name = value;`

---

#### 2. Categories of Variables in Java
Java categorizes variables into three main types based on their scope, location of declaration, and lifetime:
1.  **Local Variables**
2.  **Instance Variables** (Member Variables)
3.  **Static Variables** (Class Variables)

---

#### 3. Local Variables
Local variables are defined inside a method, constructor, or a block (like loops or if-statements).

*   **Scope:** The scope of a local variable is limited to the block in which it is defined. It cannot be accessed from outside that specific method or block.
*   **Initialization:** Java does **not** provide default values for local variables. You must initialize a local variable before using it for the first time. If you try to use an uninitialized local variable, the compiler will throw an error.
*   **Storage:** They are stored in the **Stack memory**.
*   **Lifetime:** They are created when the method/block is entered and destroyed once the method/block is exited.
*   **Access Modifiers:** You cannot use access modifiers (like `public`, `private`, or `protected`) with local variables.

---

#### 4. Instance Variables
Instance variables are declared inside a class but outside any method, constructor, or block.

*   **Scope:** They are accessible to all methods, constructors, and blocks within the class (depending on the access modifier).
*   **Initialization:** Java **does** provide default values for instance variables if they are not explicitly initialized (e.g., `0` for integers, `null` for objects, `false` for booleans).
*   **Storage:** They are stored in the **Heap memory** because they are part of the object.
*   **Lifetime:** They are created when an object is instantiated using the `new` keyword and are destroyed when the object is destroyed (Garbage Collected).
*   **Association:** Each object of the class has its own separate copy of the instance variable. If one object changes its instance variable, it does not affect the variable in other objects.

---

#### 5. Static Variables (Class Variables)
Static variables are declared using the `static` keyword inside a class but outside any method.

*   **Scope:** Similar to instance variables, they can be accessed throughout the class. They can also be accessed globally using the class name (e.g., `ClassName.variableName`).
*   **Initialization:** Like instance variables, they receive default values if not explicitly initialized.
*   **Storage:** They are stored in the **Method Area** (or Static Memory).
*   **Lifetime:** They are created when the class is loaded into memory and destroyed when the class is unloaded.
*   **Association:** There is only **one copy** of a static variable per class, regardless of how many objects are created. All instances of the class share the same static variable. If one object modifies the value, the change is reflected across all objects.

---

#### 6. Summary Comparison Table

| Feature | Local Variable | Instance Variable | Static Variable |
| :--- | :--- | :--- | :--- |
| **Declared in** | Inside a method/block | Inside class, outside method | Inside class with `static` keyword |
| **Scope** | Inside the method/block | Throughout the class | Throughout the class |
| **Default Value** | No (Compile error if used) | Yes (Default based on type) | Yes (Default based on type) |
| **Memory Location** | Stack Memory | Heap Memory | Method Area/Static Memory |
| **Creation Time** | When method starts | When object is created | When class is loaded |
| **Deletion Time** | When method finishes | When object is GC'd | When class is unloaded |
| **Access via** | Variable name directly | Object reference | Class name or Object reference |

---

#### 7. Key Takeaways for Interviews
*   **Difference between Instance and Static:** Instance variables belong to the object (multiple copies), while static variables belong to the class (one shared copy).
*   **Initialization Rule:** This is a common trick question. Remember that **local variables must be initialized**, but instance/static variables do not require manual initialization to compile.
*   **Memory Management:** Understand that Stack is for local/method execution and Heap is for objects/instance data.