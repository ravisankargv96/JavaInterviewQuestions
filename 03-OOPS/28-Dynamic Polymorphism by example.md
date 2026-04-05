These notes provide a detailed breakdown of the concepts covered in the video "Dynamic Polymorphism by example," which focuses on implementing polymorphism in C++.

---

### **1. Definition of Polymorphism**
*   **Etymology:** Derived from Greek, "Poly" means many and "Morph" means forms.
*   **Programming Context:** Polymorphism is a core pillar of Object-Oriented Programming (OOP) that allows an object to behave differently depending on the context. 
*   **Dynamic Polymorphism:** Specifically refers to polymorphism that is resolved at **runtime** rather than compile-time.

### **2. The Core Problem (Static Binding)**
In standard C++, when you have a base class and a derived class, the compiler typically uses "Early Binding" (Static Binding).

*   **Example Scenario:**
    *   **Base Class:** `Instrument` with a method `MakeSound()`.
    *   **Derived Class:** `Accordion` which inherits from `Instrument` and overrides `MakeSound()`.
*   **The Issue:** If you create a pointer of the base class type that points to a derived class object (`Instrument* i1 = new Accordion();`), and then call `i1->MakeSound()`, the compiler will execute the version of the function in the **Base Class**.
*   **Why?** By default, the C++ compiler looks at the type of the pointer (Instrument), not the type of the actual object the pointer is pointing to (Accordion).

### **3. The Solution: Virtual Functions**
To achieve Dynamic Polymorphism, C++ uses the `virtual` keyword.

*   **Definition:** A virtual function is a member function in a base class that you expect to redefine in derived classes.
*   **Implementation:** By adding the keyword `virtual` before the function signature in the base class, you tell the compiler to perform **Late Binding** (Dynamic Binding).
*   **Mechanism:** At runtime, the system looks at the actual object being pointed to and executes the most specific version of that function defined in the inheritance hierarchy.

### **4. Practical Code Example**

#### **Step 1: The Base Class**
```cpp
class Instrument {
public:
    // Virtual keyword enables dynamic polymorphism
    virtual void MakeSound() {
        cout << "Instrument playing..." << endl;
    }
};
```

#### **Step 2: The Derived Classes**
```cpp
class Accordion : public Instrument {
public:
    void MakeSound() {
        cout << "Accordion playing..." << endl;
    }
};

class Piano : public Instrument {
public:
    void MakeSound() {
        cout << "Piano playing..." << endl;
    }
};
```

#### **Step 3: Utilizing Polymorphism**
Because of the `virtual` keyword, we can treat different objects as their base type while retaining their unique behaviors.

```cpp
int main() {
    Instrument* i1 = new Accordion();
    Instrument* i2 = new Piano();

    // Even though pointers are of type Instrument*, 
    // the correct derived methods are called.
    i1->MakeSound(); // Outputs: Accordion playing...
    i2->MakeSound(); // Outputs: Piano playing...
}
```

### **5. Real-World Application: Homogeneous Collections**
The most powerful aspect of dynamic polymorphism is the ability to store different derived objects in a single collection (like an array or vector) of the base class type.

*   **Scenario:** A band needs to play many instruments.
*   **Implementation:**
    ```cpp
    Instrument* instruments[2] = { new Accordion(), new Piano() };

    for (int i = 0; i < 2; i++) {
        instruments[i]->MakeSound();
    }
    ```
*   **Benefit:** This makes the code highly extensible. If you add a new instrument class (e.g., `Guitar`), you do not need to change the loop logic. You simply add the new object to the array.

### **6. Key Takeaways**
1.  **Inheritance Requirement:** Dynamic polymorphism requires a base class and at least one derived class.
2.  **Pointer usage:** It is typically implemented using base class pointers or references pointing to derived class objects.
3.  **The Virtual Keyword:** Without `virtual` in the base class, you cannot achieve dynamic polymorphism; the compiler will default to the base class implementation.
4.  **Extensibility:** Polymorphism allows you to write "clean" code that can handle new object types without being rewritten.