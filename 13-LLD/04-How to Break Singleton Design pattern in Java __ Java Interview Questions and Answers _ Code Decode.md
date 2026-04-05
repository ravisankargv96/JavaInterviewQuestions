These notes provide a detailed technical breakdown of how to implement and subsequently break the Singleton Design Pattern in Java, as discussed in the Code Decode video.

---

### **1. Introduction to Singleton Design Pattern**
The Singleton pattern ensures that a class has only one instance and provides a global point of access to it.

#### **Three Key Requirements for Implementation:**
1.  **Private Static Instance Variable:** A variable of the class type to hold the single instance.
2.  **Private Constructor:** To prevent other classes from instantiating the class using the `new` keyword.
3.  **Public Static Access Method:** Usually named `getInstance()`, which returns the instance. If the instance is null, it creates one (Lazy Loading); otherwise, it returns the existing one.

**Example Code:**
```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {} // Private Constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

### **2. Verifying the Singleton Pattern**
To verify the pattern is working, compare the hash codes of two instances obtained via `getInstance()`. If the hash codes are identical, they point to the same memory location, confirming it is a Singleton.

---

### **3. Way #1: Breaking via Reflection API**
Reflection allows you to inspect and modify the runtime behavior of applications. It can be used to change the visibility of a private constructor.

**How it breaks Singleton:**
1.  Get the `Class` object of the Singleton class using `Class.forName()`.
2.  Access the private constructor using `getDeclaredConstructor()`.
3.  Use `setAccessible(true)` to bypass the `private` modifier.
4.  Call `newInstance()` to create a second instance.

**Code Snippet:**
```java
Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
constructor.setAccessible(true);
Singleton brokenInstance = constructor.newInstance();
```
*   **Result:** The `brokenInstance` will have a different hash code than the original instance.

---

### **4. Way #2: Breaking via Serialization/Deserialization**
Serialization is the process of converting an object into a byte stream, and Deserialization is the reverse.

**How it breaks Singleton:**
1.  The Singleton class must implement the `Serializable` interface.
2.  Serialize the original instance to a file using `ObjectOutputStream`.
3.  Deserialize the object back from the file using `ObjectInputStream`.

**The Issue:**
By default, deserialization always creates a new instance of the class, regardless of the private constructor.

**Code Snippet:**
```java
// Serialization
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.ser"));
out.writeObject(originalInstance);

// Deserialization
ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.ser"));
Singleton brokenInstance = (Singleton) in.readObject();
```
*   **Result:** The deserialized object will have a different hash code.

---

### **5. Way #3: Breaking via Cloning**
The `clone()` method is used to create a copy of an object.

**How it breaks Singleton:**
1.  The Singleton class implements the `Cloneable` interface.
2.  The `clone()` method is overridden.
3.  Calling `originalInstance.clone()` creates a new object in memory.

**Code Snippet:**
```java
Singleton brokenInstance = (Singleton) originalInstance.clone();
```
*   **Result:** Java creates a new memory address for the cloned object, resulting in a different hash code and violating the Singleton principle.

---

### **6. Summary of Findings**

| Method | Technique used to break pattern |
| :--- | :--- |
| **Reflection** | Changes constructor accessibility from `private` to `public`. |
| **Serialization** | Deserialization process creates a new instance by default. |
| **Cloning** | The `clone()` method explicitly creates a copy of the object. |

---

### **7. Additional Notes**
*   **Hash Code Comparison:** In all "broken" scenarios, the video demonstrates that the original instance and the new instance have different hash codes, proving they are different objects.
*   **Other Methods:** The video mentions that the pattern can also be challenged using **Multithreading** and **Executor Services**, though these require more advanced synchronization techniques (like `synchronized` blocks or `volatile` keywords) to prevent multiple threads from creating separate instances simultaneously.