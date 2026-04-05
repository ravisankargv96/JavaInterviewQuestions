Here are detailed notes on instance variables in Java:

### **Instance Variables in Java**

**1. Definition**

* Instance variables (also known as non-static fields) are properties or "states" of a class (e.g., a Dog class having `color`, `name`, and `breed`).
* They are defined within a class but **outside** of any method, constructor, or block.

**2. Lifecycle**

* **Creation:** They are created and allocated memory in the **Heap** area when an object of the class is instantiated using the `new` keyword.
* **Destruction:** They are destroyed when the object is destroyed (i.e., when the object is garbage collected). Memory management in Java is handled internally by the Garbage Collector.

**3. Default Values**

* Instance variables are automatically initialized with default values if they are not explicitly assigned.
* For objects (like `String`), the default value is `null`.

**4. Purpose & Usage**

* **Shared Scope:** They are used when a variable needs to be shared across multiple methods within a class. If a variable is only needed within a single method, a local variable should be used instead to save memory.
* **Memory Impact:** Since they exist as long as the object exists, defining too many unnecessary instance variables can lead to memory issues (like `OutOfMemoryError`).

**5. Access Modifiers**

* Instance variables can use access modifiers:
* **Default** (no modifier)
* **Private**
* **Public**
* **Protected**


* This contrasts with local variables, which **cannot** have access modifiers.

**6. Accessing Instance Variables**

* **Inside Instance Methods:** They can be accessed directly by name without creating an object.
* **Inside Static Methods (e.g., `main`):** They cannot be accessed directly. You must first create an object of the class and then access the variable using the object reference (e.g., `objectName.variableName`).

**7. Declaration Order (Interview Tip)**

* Unlike local variables, instance variables can be declared **after** they are used in the code within the class.
* **Why?** Because instance variables receive memory as soon as the object is created, regardless of where they are textually defined in the class file.
* *Best Practice:* It is still recommended to declare them at the top of the class for readability.