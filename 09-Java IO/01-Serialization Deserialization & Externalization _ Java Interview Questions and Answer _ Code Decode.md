These notes are based on the "Code Decode" video covering the fundamental and advanced concepts of Serialization, Deserialization, and Externalization in Java.

---

### 1. What is Serialization?
**Serialization** is the process of converting a Java object into a **byte stream**. 
*   **Purpose:** To save the current state of an object to a file, database, or transfer it over a network.
*   **Byte Streams:** These are platform-independent. While a Java object is JVM-dependent, the byte stream generated from it can be transferred and recreated on any system.
*   **The `Serializable` Interface:** To make a class serializable, it must implement the `java.io.Serializable` interface.
    *   This is a **Marker Interface** (it has no methods). It serves as a signal to the JVM that the class is eligible for serialization.

### 2. What is Deserialization?
**Deserialization** is the inverse process: converting a byte stream back into a copy of the original Java object.
*   **Requirement:** To successfully deserialize, the system must have the class definition (the POJO) of the object being recreated.
*   **Process:** It reads the byte stream and populates the object’s fields using the class definition.

---

### 3. Key Classes and Methods
To perform these operations, Java provides specific stream classes:

*   **For Serialization:**
    *   `FileOutputStream`: Used to write data to a file.
    *   `ObjectOutputStream`: Used to convert the object to a stream.
    *   **Method:** `writeObject(Object obj)`
*   **For Deserialization:**
    *   `FileInputStream`: Used to read the file.
    *   `ObjectInputStream`: Used to convert the stream back into an object.
    *   **Method:** `readObject()` (Requires typecasting to the specific class).

---

### 4. Important Keywords: `transient` and `static`
During serialization, not all fields are saved.
*   **`static` Fields:** These are **not** serialized. Static fields belong to the class, not the individual object instance. Since serialization saves the state of an *object*, static fields are ignored.
*   **`transient` Fields:** If you want to prevent a specific instance variable from being serialized (e.g., sensitive data like passwords), mark it with the `transient` keyword. 
    *   During deserialization, `transient` variables are initialized to their default values (e.g., `null` for objects, `0` for integers).

---

### 5. Serial Version UID (`serialVersionUID`)
The `serialVersionUID` is a unique identifier (a `long` number) for each serializable class.
*   **Purpose:** It ensures that the sender and receiver of a serialized object have compatible versions of the class. It maintains version control.
*   **Automatic Generation:** If you don't declare one, the JVM generates it automatically at runtime based on class name, fields, and modifiers.
*   **The Risk:** Since the automatic generation is platform/compiler-dependent, a `serialVersionUID` generated on one system might differ from another, leading to an **`InvalidClassException`**.
*   **Best Practice:** Always manually declare a `private static final long serialVersionUID` to ensure consistency across different environments.

---

### 6. Internal Mechanism
Java uses **Reflection** internally to perform serialization and deserialization.
*   It can access and save **private** and **final** fields.
*   It scrapes the data from the object's fields to create the byte stream and uses reflection to inject the data back into fields during recreation.

---

### 7. Externalization
**Externalization** is used when you need **full control** over the serialization process.
*   **Interface:** `java.io.Externalizable` (which extends `Serializable`).
*   **Methods to Implement:** 
    1.  `writeExternal(ObjectOutput out)`
    2.  `readExternal(ObjectInput in)`

#### Why use Externalization instead of Serialization?
1.  **Customization:** If a class has 1000 fields but you only want to serialize 5 of them, marking 995 fields as `transient` is inefficient. With `Externalizable`, you explicitly write only the 5 fields you need.
2.  **Security:** It prevents sensitive information from being automatically handled by the JVM.
3.  **Performance:** Since the developer dictates exactly what is written and read, it can be more efficient than the default JVM process.

#### How it works:
*   When a class implements `Externalizable`, the JVM gives full control to the application.
*   The `writeExternal` method overrides the default `writeObject` logic.
*   The `readExternal` method overrides the default `readObject` logic.
*   **Note:** When reading, you must read the fields in the exact same order they were written (e.g., if you wrote an `int` then a `String`, you must read the `int` then the `String`).

---

### 8. Summary Comparison

| Feature | Serialization (`Serializable`) | Externalization (`Externalizable`) |
| :--- | :--- | :--- |
| **Control** | Handled by JVM (Default) | Handled by Programmer (Custom) |
| **Implementation** | Easy (Marker Interface) | Requires implementing two methods |
| **Methods** | None | `writeExternal()` and `readExternal()` |
| **`transient` keyword** | Respected by JVM | Ignored (Programmer decides via logic) |
| **Efficiency** | Slower (uses reflection on all fields) | Faster (only handles specified fields) |

---

### 9. Common Interview Questions Mentioned
*   What is the difference between `Serializable` and `Externalizable`?
*   What happens to `static` and `transient` variables during serialization?
*   What is the use of `serialVersionUID`?
*   How can you prevent a field from being serialized?
*   Does the order of fields matter in `Externalizable`? (Yes).
*   Can you serialize `private` or `final` fields? (Yes, via reflection).