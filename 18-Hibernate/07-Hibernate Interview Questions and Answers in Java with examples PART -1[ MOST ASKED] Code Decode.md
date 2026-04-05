These detailed notes are based on the video "Hibernate Interview Questions and Answers in Java with examples PART -1" by Code Decode.

---

### **1. Introduction to Hibernate**
*   **Definition:** Hibernate is an **ORM (Object Relational Mapping)** tool.
*   **Purpose:** It is used to map Java Objects (POJOs) to Relational Database tables.
*   **Relationship with JPA:** Hibernate is an implementation of **JPA (Java Persistence API)**. JPA is the interface (specification), and Hibernate is the class (implementation). This is why Hibernate can use JPA annotations.
*   **Mapping Methods:**
    1.  **Annotations:** Using JPA annotations within the Java class.
    2.  **XML Configurations:** Using `.hbm.xml` files.

---

### **2. Why Hibernate over JDBC?**
Interviewers frequently ask why we should use Hibernate instead of the standard JDBC.
*   **Eliminates Boilerplate Code:** Hibernate handles the repetitive code required for database connections, statements, and result sets.
*   **HQL (Hibernate Query Language):** It is object-oriented and independent of the database engine.
*   **Transaction Management:** Hibernate provides implicit transaction management (JDBC requires manual handling).
*   **Exception Handling:** Hibernate wraps JDBC’s checked exceptions into **unchecked exceptions** (`HibernateException`), so developers aren't forced to write try-catch blocks everywhere.
*   **Caching:** Hibernate supports internal caching, which significantly improves performance.

---

### **3. Core Interfaces in Hibernate**
There are three primary interfaces used to manage database operations:
1.  **SessionFactory:**
    *   Used to create `Session` objects.
    *   Initialized once per database. If you connect to both Oracle and MySQL, you need two SessionFactories.
    *   It is thread-safe and usually cached for reuse.
2.  **Session:**
    *   The "factory" for transactions.
    *   Used to establish a physical connection with the database.
    *   Used for CRUD operations (Create, Read, Update, Delete).
    *   It is not thread-safe.
3.  **Transaction:**
    *   Represents a single atomic unit of work.
    *   Used to `commit` or `rollback` operations.

**Programmatic Workflow:**
`Metadata` → `SessionFactory` → `openSession()` → `beginTransaction()` → Perform Operations (save/update/delete) → `commit()` → `closeSession()` → `closeFactory()`.

---

### **4. Key Hibernate Annotations**
*   **@Entity:** Specifies that the class is an entity bean to be stored in the database.
*   **@Table:** Defines the table name in the DB that maps to the entity.
*   **@Id:** Defines the Primary Key.
*   **@GeneratedValue:** Specifies the strategy for primary key generation (e.g., `GenerationType.IDENTITY`).
*   **@Column:** Defines specific column properties (name, length, etc.).
*   **@EmbeddedId:** Used for composite primary keys.
*   **@OneToOne, @ManyToOne, @ManyToMany:** Used to define associations between tables.
*   **@Cascade:** Used to propagate operations (like save/delete) from a parent to a child entity.
*   **@PrimaryKeyJoinColumn:** Defines a foreign key property.

---

### **5. Association Mappings**
1.  **One-to-One:** One instance of an entity is mapped to exactly one instance of another (e.g., Employee to Phone Number).
2.  **Many-to-One:** Multiple instances of an entity map to one instance of another (e.g., many Students living at one Address).
3.  **Many-to-Many:**
    *   Example: Students and Degrees (A student has many degrees; a degree belongs to many students).
    *   **The Third Table:** Hibernate automatically creates a junction table.
    *   **Join Column:** The primary key of the current class.
    *   **Inverse Join Column:** The primary key of the associated class.

---

### **6. Important Configuration Files**
1.  **hibernate.cfg.xml (Configuration File):**
    *   Contains database-specific properties: Driver class, URL, Username, Password, and Dialect.
    *   If you have multiple databases, you must have multiple SessionFactories defined by different configuration properties.
2.  **hbm.xml (Mapping File):**
    *   Maps the Java class to the DB table.
    *   Includes `<id>` for the primary key, `<generator>` for ID generation strategy, and `<property>` for other columns.

---

### **7. Important Method Comparisons**

#### **OpenSession vs. GetCurrentSession**
*   **getCurrentSession():** Returns the session currently bound to the context. It is automatically closed when the SessionFactory is closed.
*   **openSession():** Always opens a **new** session. The developer is responsible for manually closing it. It is preferred in multi-threaded environments.

#### **Get vs. Load**
*   **get():**
    *   Hits the database immediately.
    *   Returns `null` if the object is not found.
    *   Use this if you are not sure the data exists.
*   **load():**
    *   Uses **Lazy Loading**. It returns a "proxy" object and only hits the DB when a property (other than the ID) is accessed.
    *   Throws an **ObjectNotFoundException** if data is not found.
    *   Better for performance if you are certain the data exists.

---

### **8. Hibernate Caching (Highly Asked)**
Caching is used to reduce the number of database hits to improve performance.

#### **First-Level Cache**
*   Associated with the **Session** object.
*   **Default:** Always enabled; cannot be disabled.
*   Scope: Data is only visible to the specific session. Once the session is closed, the cache is lost.

#### **Second-Level Cache**
*   Associated with the **SessionFactory**.
*   **Default:** Disabled (must be configured).
*   Scope: Shared across all sessions.
*   Common implementations: **EHCache**, Infinispan.

#### **Query-Level Cache**
*   Used in conjunction with the Second-Level Cache to cache the results of specific queries.

---

### **9. Steps to Enable EHCache (Second-Level Cache)**
1.  **Add Dependency:** Include the `hibernate-ehcache` JAR or Maven dependency.
2.  **Update Configuration:** In `hibernate.cfg.xml`, set `hibernate.cache.use_second_level_cache` to `true` and specify the `RegionFactory` class.
3.  **Create Configuration File:** Create an `ehcache.xml` file to define cache regions, memory limits, and expiration times (Time to Live).
4.  **Annotate Entities:** Use `@Cacheable` and `@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)` on the entity classes you want to cache.

---

### **10. Summary of Creating a Hibernate Application**
1.  Create a **POJO** (Java Class).
2.  Create a **Mapping File** (`.hbm.xml` or use Annotations).
3.  Create the **Configuration File** (`hibernate.cfg.xml`) for DB connection.
4.  Write a **Utility/Test Class** to handle the SessionFactory and perform operations.
5.  Run the application.