# Detailed Notes: Okta Implementation in Spring Boot with Spring Security and OAuth2

These notes are based on the "Code Decode" tutorial regarding the practical implementation of Okta as an authorization server for a Spring Boot application.

---

## 1. Introduction to the Tech Stack
*   **Spring Boot:** The framework used to build the REST APIs.
*   **Spring Security:** The underlying security framework for authentication and authorization.
*   **Okta:** Acts as the **Authorization Server**. It issues access tokens (JWTs) to clients so they can access secured resources.
*   **OAuth2 / OIDC:** The protocols used for securing the communication between the client, the application, and the authorization server.

---

## 2. Setting Up Okta (The Authorization Server)
Before coding, you must configure the Okta developer console.

### Create an Application Integration
1.  Log in to the Okta Developer Console.
2.  Navigate to **Applications > Create App Integration**.
3.  **Selection:** For this demo, choose **API / Machine-to-Machine** (as the focus is on securing backend APIs rather than a browser-based Single Sign-On flow).
4.  **Credentials:** Once the app is created, Okta provides a **Client ID** and a **Client Secret**.
    *   **Client ID:** A public identifier for the client.
    *   **Client Secret:** Acts as the password for the client application.

### Authorization Server Details
Navigate to **Security > API** to find the following essential values:
*   **Issuer URI:** The base URL for the authorization server (e.g., `https://{yourOktaDomain}/oauth2/default`). This is used to fetch metadata and tokens.
*   **Audience:** Usually `api://default`. It identifies the intended recipient of the JWT token.
*   **Token URL:** Found by appending `/v1/token` to the Issuer URI.

### Creating a Custom Scope
1.  In the **API** tab, select the **Default** authorization server.
2.  Go to the **Scopes** tab and click **Add Scope**.
3.  Example: Create a scope named `code_decode`. This scope will be requested during the token generation process.

---

## 3. Spring Boot Project Configuration

### Dependencies
In the `pom.xml`, the most critical dependency is:
*   **`okta-spring-boot-starter`**: This dependency automatically pulls in Spring Security and OAuth2 resource server configurations. It essentially places a "Watchman" in front of your APIs.

### Application Properties
The application needs to know which authorization server to trust. Add this to `application.properties`:
```properties
okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
```
*Note: If this property is missing, the application will fail to start.*

---

## 4. Securing the REST API

### The Controller
A simple REST controller is created with a GET mapping (e.g., `/octa-secured/get-api`). 

### Default Security Behavior
As soon as the `okta-spring-boot-starter` is added:
*   Every endpoint in the application becomes secured by default.
*   Attempting to hit the API via a browser or Postman without a token will result in an **HTTP 401 Unauthorized** error.

---

## 5. Fetching the Access Token (OAuth2 Flow)
To access the secured API, you must first obtain a JWT access token from Okta using the **Client Credentials Grant Type**.

### Postman Configuration:
1.  **Method:** POST
2.  **URL:** `https://{yourOktaDomain}/oauth2/default/v1/token`
3.  **Authorization:** Basic Auth (Username = Client ID, Password = Client Secret).
4.  **Body (x-www-form-urlencoded):**
    *   `grant_type`: `client_credentials`
    *   `scope`: `code_decode` (the custom scope created earlier).
5.  **Result:** Okta returns a JSON object containing the `access_token`.

---

## 6. Accessing the Secured API
Once you have the token:
1.  Go to your Spring Boot API request in Postman.
2.  Set the **Authorization** header to **Bearer Token**.
3.  Paste the access token (ensure no quotes are included).
4.  **Common Mistake:** Do not manually prefix the token with the word "Bearer" if your tool already handles the "Bearer" type selection; otherwise, it results in a "double bearer" error and a 401 status.

---

## 7. Understanding the JWT (JSON Web Token)
You can inspect the generated token using tools like `jwt.io`. Key claims inside the token include:
*   **iss (Issuer):** Matches the Okta Issuer URI.
*   **aud (Audience):** Matches the audience defined in Okta (e.g., `api://default`).
*   **scp (Scope):** Lists the permissions/scopes associated with the token (e.g., `code_decode`).
*   **exp (Expiry):** The timestamp when the token expires.

---

## 8. Summary of Implementation Steps
1.  **Register on Okta:** Create a Machine-to-Machine application.
2.  **Note Credentials:** Save Client ID, Client Secret, and Issuer URI.
3.  **Bootstrap Spring Boot:** Add the `okta-spring-boot-starter` dependency.
4.  **Configure Issuer:** Add the issuer URI to `application.properties`.
5.  **Create API:** Build your REST controller.
6.  **Get Token:** Call the Okta `/token` endpoint with client credentials.
7.  **Authorize API:** Use the JWT in the Authorization header to successfully hit the secured endpoint.