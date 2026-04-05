These notes provide a comprehensive breakdown of the system design for a URL shortening service (like TinyURL or Bitly) based on the "Code Decode" video.

---

### **1. Introduction to URL Shortening**
A URL shortener takes a long, bulky, and often unreadable URL and converts it into a short, manageable alias (short link). 
*   **Primary Functionality:** When a user hits the short link, the system internally redirects them to the original long URL.
*   **Advantages:** Easier to share, more aesthetic, and saves space in character-limited environments.

---

### **2. Requirement Gathering**
To succeed in a system design interview, you must first define both functional and non-functional requirements.

#### **A. Functional Requirements (What the system does)**
1.  **Short Link Generation:** Create a shorter alias for a given long URL.
2.  **Redirection:** When the short link is accessed, the system must redirect the user to the original long URL.
3.  **Update/Delete:** Authorized users should be able to update the long URL mapping or delete a short link.
4.  **Custom URLs:** (Optional) Users can choose a specific alias (e.g., `tinyurl.com/my-custom-link`).
5.  **Expiration:** Links should have a default or user-defined expiration time.

#### **B. Non-Functional Requirements (How the system performs)**
1.  **High Availability:** The system must be available 24/7. Redirection failure is unacceptable.
2.  **Scalability:** Must handle a massive increase in the number of users and requests.
3.  **Low Latency:** The redirection process must be near-instantaneous so users don’t experience delays.
4.  **Readability:** Short links should be compact and alphanumeric (not just long numeric IDs).

---

### **3. Key System Components**

*   **Database (DB):** Stores the mapping between the short URL, long URL, and user metadata.
*   **Web Server:** Handles incoming client requests.
*   **Application Server:** Contains the business logic (sequencing, encoding, validation).
*   **Sequencer:** Generates a unique 64-bit ID for every new URL to ensure no collisions.
*   **Encoder:** Converts the large numeric ID from the sequencer into a short, readable alphanumeric string (e.g., Base62 encoding).
*   **Load Balancer (LB):** Distributes incoming traffic across multiple server instances to ensure scalability and health-check monitoring.
*   **Cache:** A layer (like Redis) sitting before the DB to store frequently accessed mappings, significantly reducing latency.
*   **Rate Limiter:** Prevents abuse by limiting the number of requests a single user/IP can make in a given timeframe.

---

### **4. Detailed Design & Architecture**

#### **A. The Storage Choice: NoSQL vs. Relational**
*   **Read-Intensive Nature:** URL shorteners are "read-heavy." A link is created once but clicked thousands of times.
*   **Why NoSQL (e.g., MongoDB, Cassandra):**
    *   NoSQL is faster for simple key-value or document lookups.
    *   Avoids complex "Joins" required in relational databases.
    *   Easier to scale horizontally as the number of records grows into billions.
*   **Data Structure:** A simple document containing: `User_ID`, `Short_URL`, `Long_URL`, `Created_At`, `Expiry_Time`.

#### **B. Generating the Short Link (The Logic)**
1.  **Sequencer:** Generates a unique number (e.g., 10001).
2.  **Encoder:** Encodes that number into a shorter string (e.g., `6GH7`). This ensures the link is short and readable while remaining unique.

#### **C. API Design**
*   **CREATE:** `POST /api/v1/shorten` (Input: Long URL; Output: Short URL).
*   **READ:** `GET /{short_url}` (Input: Short URL; Action: Redirect to Long URL).
*   **UPDATE:** `PUT /api/v1/update` (Input: Short URL + New Long URL).
*   **DELETE:** `DELETE /api/v1/delete` (Input: Short URL).

---

### **5. Data Flows**

#### **The Create Flow (Writing Data)**
1.  Client sends a long URL to the **Load Balancer**.
2.  LB forwards it to a healthy **Web Server**.
3.  **Rate Limiter** checks if the user has exceeded their daily limit.
4.  **Application Server** uses the **Sequencer** and **Encoder** to generate the unique short alias.
5.  The mapping is saved in the **Database** and stored in the **Cache**.
6.  The short URL is returned to the user.

#### **The Read Flow (Redirection)**
1.  User clicks the short URL.
2.  The request hits the **Cache** first.
3.  **Cache Hit:** If found, the long URL is returned immediately (Low Latency).
4.  **Cache Miss:** If not in cache, the system queries the **Database**, updates the cache for future use, and redirects the user.

#### **The Expiration Flow**
*   The system uses a **Scheduler (e.g., Quartz)** on the App Server to periodically scan the database.
*   It identifies records where `Current_Time > Expiry_Time` and deletes them to free up storage.

---

### **6. Meeting Non-Functional Requirements**

*   **Availability:** Achieved via the **Load Balancer** (reroutes traffic if one server fails) and **Rate Limiter** (prevents system crashes from bot attacks).
*   **Scalability:** Achieved by using **NoSQL** (horizontal DB scaling) and adding more **Application Server instances** behind the Load Balancer.
*   **Latency:** Reduced using a **Caching layer** and choosing a **NoSQL** database over a Relational one.
*   **Readability:** Handled by the **Encoder**, which keeps the URL length to 6–8 characters.

---

### **7. Potential Interview Variations**
*   **Custom URLs:** If a user wants a specific alias (e.g., `/my-promo`), the App Server must check the DB first to ensure that specific alias isn't already taken by someone else.
*   **Analytics:** Interviewers might ask how to track clicks. This would involve adding a separate service to log every "Read" event into an analytics database.
*   **Security:** Mentioning **Authentication** and **Authorization** is vital for the Update and Delete flows to ensure only the owner can modify a link.