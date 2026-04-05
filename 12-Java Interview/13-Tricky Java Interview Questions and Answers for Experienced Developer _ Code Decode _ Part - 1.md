Here are the detailed notes for the video **"Tricky Java Interview Questions and Answers for Experienced Developer | Code Decode | Part - 1"**.

---

### 1. How to Traverse or Loop Over a Map in Java
Iterating over a Map is trickier than a List because Maps store **Entry objects** (key-value pairs) rather than simple elements. There are four primary ways to do this:

*   **Key Set with For-Each Loop:** 
    *   Use `map.keySet()` to get a set of keys.
    *   Iterate through the keys and use `map.get(key)` to fetch the value.
*   **Key Set with Iterator:** 
    *   Obtain an iterator from the key set: `map.keySet().iterator()`.
    *   Use a `while` loop with `iterator.hasNext()` and `iterator.next()`.
*   **Entry Set with For-Each Loop (Recommended):** 
    *   Use `map.entrySet()` to get a set of `Map.Entry<K, V>` objects.
    *   Call `entry.getKey()` and `entry.getValue()` directly. 
    *   *Efficiency Tip:* This is more efficient than the Key Set method because it avoids the extra `map.get(key)` lookup.
*   **Entry Set with Iterator:** 
    *   Obtain an iterator from the entry set.
    *   Check `hasNext()`, fetch the next `Entry` object, and retrieve keys/values.

---

### 2. WeakHashMap vs. HashMap
The primary difference lies in how the Garbage Collector (GC) treats the keys.

#### Understanding Reference Types:
1.  **Strong Reference:** (e.g., `Integer a = 1;`) The object is not eligible for GC as long as the reference exists.
2.  **Soft Reference:** The object is only GC’d if the JVM absolutely needs memory (out of memory protection).
3.  **Weak Reference:** The GC is eager to collect these. As soon as the strong reference to the object is removed (set to null), the weak reference is eligible for GC immediately.

#### The Difference in Maps:
*   **HashMap:** The Map holds a "strong reference" to its keys. Even if you set your external key reference to `null`, the entry remains in the HashMap because the map itself points to it. The GC cannot remove it.
*   **WeakHashMap:** Internally uses `WeakReference` for keys. If there are no other strong references to a key, the GC will reclaim that key, and the `WeakHashMap` will automatically remove the associated entry. 
*   **Use Case:** Ideal for caching scenarios where you want entries to disappear automatically when the key is no longer used elsewhere in the application.

---

### 3. Spring Boot 3.0: Major Changes and New Features
Spring Boot 3.0 is a major release that breaks backward compatibility in some areas.

*   **Java 17 Baseline:** The minimum required version is Java 17. It supports up to JDK 19 but won't run on Java 8 or 11.
*   **Jakarta EE Migration:** This is the biggest "breaking change." It moves from `javax.*` package namespaces to `jakarta.*`. 
    *   Developers must update imports for Servlets, Persistence (JPA), and Validations.
*   **GraalVM Native Image Support:** Provides native support for compiling Spring applications into native executables. This results in significantly faster startup times and lower memory consumption (ideal for containers/serverless).
*   **Improved Observability:** Integrated support for **Micrometer** and Micrometer Tracing for better monitoring of application internals.
*   **Spring Framework 6:** Internally uses Spring Framework 6.

---

### 4. Evolution of Jakarta EE (J2EE vs. Java EE vs. Jakarta EE)
The names refer to the same Enterprise specifications at different stages of history:
1.  **J2EE (Java 2 Enterprise Edition):** Used until 2006.
2.  **Java EE:** Renamed in 2006 (Java 5 era) and owned by Oracle.
3.  **Jakarta EE:** In 2017, Oracle moved the Enterprise Edition to the **Eclipse Foundation**. Because Oracle owns the "Java" trademark, the Eclipse Foundation had to rename the project to "Jakarta."
4.  **Impact:** This necessitated the package rename from `javax.*` to `jakarta.*`.

---

### 5. ElasticSearch Overview
ElasticSearch is an open-source, NoSQL database/search engine built on **Apache Lucene**.

*   **Why use it?** Relational databases are slow at searching large volumes of text. ElasticSearch is optimized for high-speed retrieval and full-text search.
*   **Inverted Index:** It uses a specialized data structure called an "Inverted Index" which maps words to their locations in documents, allowing for near-instant searching.
*   **Structure Comparison:**
    *   Index = Database
    *   Document = Row
    *   Field = Column
*   **Usage:** Commonly used for log analysis (ELK stack) and real-time search features in applications.

---

### 6. Apache Kafka
Kafka is a distributed, fault-tolerant, publish-subscribe messaging system.

*   **Kafka vs. Traditional Queues (ActiveMQ/RabbitMQ):**
    *   **Traditional Queues:** Point-to-point. Once a consumer reads a message, it is deleted from the broker.
    *   **Kafka (Pub-Sub):** Messages are written to a disk-based storage system. Multiple subscribers can read the same message. Messages are not deleted immediately after reading; they remain based on a retention policy.
*   **Key Capabilities:**
    *   **Messaging:** Decouples producers and consumers.
    *   **Storage:** Acts as a fault-tolerant storage system by replicating data across multiple brokers.
    *   **Stream Processing:** Includes a Streams API to perform complex aggregations and joins on data in real-time.

---

### 7. SOLID Principles: Liskov Substitution Principle (LSP)
This principle states: *"Derived types must be completely substitutable for their base types."*

*   **The Concept:** If you have a method that accepts a Parent class object, you should be able to pass any Child class object to that method without the application breaking or behaving unexpectedly.
*   **Example:** If `TrainedEmployee` extends `Employee`, any method that uses `Employee` should work perfectly when a `TrainedEmployee` instance is passed.
*   **Importance:** It ensures that inheritance is implemented correctly (a true "IS-A" relationship). If a subclass cannot perform the same actions as the superclass or requires specific checks (like `instanceof`), it likely violates LSP.