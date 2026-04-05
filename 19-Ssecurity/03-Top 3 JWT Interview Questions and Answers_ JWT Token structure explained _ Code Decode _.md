These detailed notes are based on the video "Top 3 JWT Interview Questions and Answers" by Code Decode, covering the structure, security, and validation of JSON Web Tokens (JWT).

---

### **1. What is JWT (JSON Web Token)?**
JWT is a compact, self-contained way to represent information securely between two parties (typically a client and a server). 
*   **Purpose:** Primarily used for **Authentication** (verifying who a user is) and **Authorization** (verifying what a user is allowed to do).
*   **Transmission:** Because they are compact, they are easily sent via HTTP headers (as Bearer tokens), query parameters, or within cookies.

---

### **2. The Structure of a JWT**
A JWT is a string consisting of three distinct parts separated by dots (`.`): **Header.Payload.Signature** (often represented as X.Y.Z).

#### **A. Header**
The header typically consists of two parts:
*   **Type of Token:** Usually "JWT".
*   **Signing Algorithm:** The algorithm being used, such as HMAC SHA256 (HS256) or RSA.

#### **B. Payload (Claims)**
The payload contains "claims," which are statements about an entity (typically the user) and additional data.
*   **Types of Claims:**
    1.  **Registered Claims:** Predefined claims like `iss` (issuer), `exp` (expiration time), `sub` (subject), and `aud` (audience).
    2.  **Public Claims:** Custom names defined by the user to share information.
    3.  **Private Claims:** Custom claims created to share information between parties that agree on using them (e.g., `userId` or `userRole`).

#### **C. Signature**
The signature is used to verify that the sender of the JWT is who it says it is and to ensure that the message wasn't changed along the way.
*   **How it’s created:** It takes the encoded header, the encoded payload, a secret key, and the algorithm specified in the header to sign the token.
*   **Integrity:** If even one character in the header or payload changes, the signature will no longer match, alerting the server that the token has been tampered with.

---

### **3. Key Security Best Practices**
*   **Do Not Store Sensitive Data:** JWTs are **Base64 encoded**, not encrypted. This means anyone can easily decode the token and read the content. Never put passwords, social security numbers, or sensitive personal IDs in the header or payload.
*   **Keep it Compact:** Only include necessary information. Large tokens increase network overhead.
*   **Avoid Redundancy:** If data (like a full user profile) can be fetched from a database using a `userId`, do not include the redundant data in the token.
*   **Use Secret Keys:** Always sign tokens with a strong, secure secret key or a public/private key pair to ensure integrity.

---

### **4. How the Server Validates a JWT**
When a client sends a JWT to a backend server, the server must validate it before granting access to resources.

#### **A. Stateless Validation (Recommended)**
The resource server validates the token independently without calling an external authorization server (like Okta or Keycloak).
1.  **Signature Verification:** The server uses its secret key to re-calculate the signature of the incoming header and payload. If the calculated signature matches the one on the token, the token is authentic.
2.  **Expiration Check:** The server checks the `exp` claim. If the current time is later than the expiration time, the token is rejected.
3.  **Claims Check:** The server may check the `aud` (audience) claim to ensure the token was intended for that specific application.

#### **B. Stateful Validation**
The resource server makes an API call to the Authorization Server to verify if the token is still valid. 
*   **Pros:** Allows for immediate revocation of tokens.
*   **Cons:** Increases network overhead and can reduce application performance.

---

### **5. Summary of Interview Tips**
*   **Why use JWT?** It allows for a "Stateless" authentication mechanism. The server doesn't need to store session data in memory.
*   **Validation:** Emphasize that the backend performs the signature check using a secret key to ensure the token wasn't tempered with.
*   **Data Choice:** Always mention that you only store the minimum required data (like `userId` and `roles`) to keep the token lightweight and secure.