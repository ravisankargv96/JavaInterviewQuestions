These detailed notes are based on the "Code Decode" video regarding RESTful API interview questions and answers.

---

### 1. Introduction to REST and RESTful Web Services
*   **REST (Representational State Transfer):** It is an architectural style, not a protocol. It refers to the transfer of the "state" of a resource (data) from a server/database to a client over a network.
*   **RESTful Web Services:** These are web services that follow the REST architectural concepts.
*   **State Transfer Example:** An employee record stored in a database (the state) is converted into a format like JSON or XML and transferred to a client (like Postman) via an API.
*   **Key Formats:** While JSON is most common, REST can use XML, Text, or HTML.

---

### 2. Core Architectural Concepts
*   **Client-Server Architecture:** There is a clear separation between the client (UI/Postman) and the server (where the data and logic reside).
*   **Statelessness:** The server does not store any client context or session data. Every request from the client must contain all the information (headers, authentication, etc.) needed to fulfill that request.
*   **HTTP Protocol:** RESTful services almost exclusively use HTTP or HTTPS for communication over a network.

---

### 3. Understanding Resources and URIs
*   **What is a Resource?** In REST, every piece of content is a resource (e.g., an image, a text file, an HTML page, or an Employee object).
*   **URI (Uniform Resource Identifier):** This is the address used to identify a specific resource on the server.
*   **URI Format:** `[Protocol]://[Service Name]/[Resource Type]/[Resource ID]`
    *   *Example:* `https://en.wikipedia.org/wiki/Representational_state_transfer`
    *   `https` = Protocol
    *   `en.wikipedia.org` = Service Name
    *   `wiki` = Resource Type
    *   `Representational_state_transfer` = Resource ID (Unique Identifier)

---

### 4. HTTP Methods (CRUD Operations)
REST uses standard HTTP "verbs" to perform actions on resources:

| HTTP Method | CRUD Action | Description |
| :--- | :--- | :--- |
| **GET** | Read | Fetches data from the database. It is a read-only operation. |
| **POST** | Create | Creates a new resource on the server. |
| **PUT** | Update | Updates or replaces an existing resource. |
| **DELETE** | Delete | Removes a resource from the server. |

---

### 5. Idempotency in REST APIs
**Definition:** An operation is idempotent if performing it multiple times with the same parameters produces the same result and has no additional effect on the server's state (specifically regarding memory/database records).

*   **GET (Idempotent):** Calling GET multiple times will return the same data without changing anything in the database.
*   **POST (NOT Idempotent):** Every time you hit a POST request, a new record is created. If you hit it 5 times, 5 new rows are added to the database, consuming more memory each time.
*   **PUT (Idempotent):** PUT is used for updates. If you update Employee ID 1 to "Name X" multiple times, the database remains in the same state after the first call. No new rows are added; only the existing row is modified.
*   **DELETE (Idempotent):** The first time you delete a resource, it is removed. Subsequent requests will not find the resource (often returning a 404 or 500 error), but the state of the database remains the same as it was after the first deletion (the record is still gone).

---

### 6. Detailed Look at Statelessness
*   **Definition:** The server does not remember the client’s previous requests.
*   **Client Responsibility:** The client must pass the context (like headers) every single time.
*   **Example:** If a client requires a response in JSON format, they must send the header `Accept: application/json` with every request. The server will not "remember" that the client wanted JSON in the previous call.
*   **Session Management:** Sessions are generally stored on the **client-side**, not the server-side.

---

### 7. Key Features of RESTful Web Services
1.  **Based on Client-Server model:** Allows independent evolution of the front-end and back-end.
2.  **Uses HTTP Protocol:** Leverages existing web infrastructure.
3.  **Messaging:** Communication between client and server happens through request/response messages.
4.  **URI-based:** Every resource is uniquely identifiable.
5.  **Stateless:** Improves scalability because the server doesn't have to manage memory for client sessions.

---

### 8. Common Interview Comparisons

#### **POST vs. PUT**
*   **POST** is used to create a new resource. It is **not** idempotent (repeated calls create multiple resources).
*   **PUT** is used to update an existing resource or create one if the ID is known. It **is** idempotent (repeated calls result in the same state).

#### **PUT vs. PATCH**
*   **PUT** is typically used for a full update (replacing the entire resource).
*   **PATCH** is used for partial updates (changing only one specific field of a resource). *(Note: Detailed demonstration of PATCH and OPTIONS was reserved for the 'Advanced' video).*