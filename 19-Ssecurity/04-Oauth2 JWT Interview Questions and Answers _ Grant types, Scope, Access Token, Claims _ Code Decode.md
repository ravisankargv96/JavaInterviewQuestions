These detailed notes are based on the video "Oauth2 JWT Interview Questions and Answers" by Code Decode, covering the fundamental concepts of security, OAuth2, OCTA, and JWT.

---

### **1. Authentication vs. Authorization**
*   **Authentication:** Verifies **who you are**. (e.g., A watchman checking your ID card at a building entrance).
*   **Authorization:** Verifies **what you can access**. (e.g., After checking your ID, the watchman calls a flat owner to see if you are allowed to enter a specific room).
*   **OAuth2 Focus:** OAuth2 is strictly an **Authorization** protocol. It does not handle authentication itself but delegates it to a service provider.

### **2. What is OAuth2?**
*   **Definition:** An open-standard framework or protocol for authorization.
*   **Mechanism:** It allows a third-party application (e.g., Twitter) to access limited information (e.g., name, email) from another service (e.g., Google) without the user sharing their Google password with Twitter.
*   **Delegation:** The responsibility of authentication is delegated to the service that holds the user account (Google).
*   **Versions:** OAuth 1.0 is deprecated and not backward compatible with OAuth 2.0. OAuth 2.0 is the current market standard.

### **3. Roles in OAuth2**
*   **Resource Owner:** The user who owns the data and grants access to it.
*   **Resource Server:** The backend API or database containing the protected data (e.g., your Spring Boot application).
*   **Client:** The application requesting access (e.g., an Angular frontend).
*   **Authorization Server:** The engine that issues "Grants" and "Access Tokens" (e.g., OCTA, Auth0).

### **4. What is OCTA?**
*   **Definition:** An organization/platform providing a "One-Stop Solution" for security.
*   **Function:** It handles both Authentication (via OIDC - OpenID Connect) and Authorization (via OAuth2).
*   **Workflow:** Instead of building a local security database, you configure your app in OCTA. You hit OCTA endpoints (like `/authorize` and `/token`) to get tokens.

### **5. Scopes and Tokens**
*   **Token:** Think of it as a **Movie Ticket**. Without it, you cannot enter.
*   **Scope:** Think of the **details on the ticket** (Movie name, seat number, time). It defines the specific permissions (e.g., `read`, `write`, `profile`).
*   **Access Token:** A piece of data used to authorize the client to access the resource server.

### **6. OAuth2 Grant Types (Flows)**
Grant types are different ways an application can obtain an access token.
1.  **Client Credentials:** Used for machine-to-machine communication (Microservices, batch processes). It uses a `Client ID` and `Client Secret`.
2.  **Authorization Code:** Used for web apps. The server provides a code, which is then exchanged for a token.
3.  **Authorization Code with PKCE (Proof Key for Code Exchange):** A more secure version of the Auth Code flow, specifically for Single Page Applications (SPA) like Angular or mobile apps where the `Client Secret` cannot be stored securely.
4.  **Implicit Grant:** A simplified flow where the token is returned directly (now largely deprecated in favor of PKCE).
5.  **Resource Owner Password Credentials:** The user gives their username/password directly to the client. This is rarely used and only recommended for highly trusted applications.
6.  **Device Code:** Used for devices with limited input capabilities (e.g., Smart TVs, IoT) that don't have a browser.

### **7. JSON Web Token (JWT)**
*   **Definition:** JWT is a **format** for an access token, not the token itself. It is an open industry standard (RFC 7519).
*   **Structure:** A JWT consists of three parts separated by dots (`x.y.z`):
    1.  **Header (x):** Contains the signing algorithm (e.g., HS256) and the type of token (JWT).
    2.  **Payload (y):** Contains the "Claims" (data about the user).
    3.  **Signature (z):** Created by taking the encoded header, encoded payload, and a secret key, then hashing them together. This ensures the token hasn't been tampered with.

### **8. JWT Claims**
Claims are key-value pairs of information stored in the payload.
*   **Reserved Claims:** Predefined and recommended (though not always mandatory).
    *   `iss` (Issuer)
    *   `exp` (Expiration Time)
    *   `sub` (Subject/User ID)
    *   `aud` (Audience)
*   **Public Claims:** Defined by those using JWTs but should be collision-resistant.
*   **Private/Custom Claims:** Custom data shared between parties (e.g., `shopID`, `role`).

### **9. Why use JWT instead of Plain JSON?**
*   **Security:** Plain JSON can be intercepted and tampered with by a malicious user.
*   **Integrity:** Because JWT is signed using a secret key, the backend can verify if even a single character in the payload was changed. If the data changes, the signature will no longer match.

### **10. How the Backend Validates a JWT**
When the Resource Server (Backend API) receives a token, it performs these checks:
1.  **Header Check:** Decodes the header to ensure the type is JWT and the algorithm is valid.
2.  **Signature Verification:** The backend uses its secret key to re-calculate the signature from the received Header and Payload. If the calculated signature matches the one in the token, the token is authentic.
3.  **Expiration Check:** Decodes the payload to check the `exp` claim. If the current time is past the expiration time, the token is rejected.
4.  **Claims Check:** Ensures the user has the required permissions (scopes) to perform the requested action.