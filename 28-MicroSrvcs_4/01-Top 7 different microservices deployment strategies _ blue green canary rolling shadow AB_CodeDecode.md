These notes provide a detailed breakdown of the microservices deployment strategies discussed in the video "Top 7 different microservices deployment strategies" by CodeDecode.

---

# Microservices Deployment Strategies: Detailed Notes

## 1. Introduction
Deployment strategies define how new code is transitioned into a production environment. While these strategies are crucial for microservices, they are also applicable to monolithic applications, native mobile apps, and web services.

---

## 2. Recreate Deployment
This is the simplest, most traditional method of deployment.

*   **Process:** 
    1.  Shut down the old version of the application (Version 1).
    2.  Deploy and start the new version (Version 2).
*   **Key Characteristics:**
    *   **Downtime:** There is a guaranteed period of downtime between the deletion of the old pods/instances and the startup of the new ones.
    *   **Simplicity:** Very straightforward to implement.
    *   **Rollback:** If Version 2 fails, you must shut it down and restart Version 1, causing a second period of downtime.
*   **Best Use Case:** Non-critical systems or applications with low traffic periods (e.g., internal tools or maintenance during off-peak hours like 4:00 AM).

---

## 3. Rolling Deployment
This strategy involves a gradual replacement of instances to avoid downtime.

*   **Process:**
    1.  Instances are replaced one by one (or in small batches).
    2.  The system ensures a minimum number of instances are always available.
    3.  Once a new instance is confirmed healthy, another old instance is taken down.
*   **Key Concepts:**
    *   **Max Surge:** Defines how many additional instances can be created above the desired replica count during the update.
    *   **Max Unavailable:** Defines how many instances can be offline during the update.
*   **Pros:** Minimal downtime.
*   **Cons:** During the rollout, both Version 1 and Version 2 coexist. A user’s first request might hit V1, and their second might hit V2, which can cause consistency issues if the versions are significantly different.

---

## 4. Blue-Green Deployment
This strategy requires maintaining two identical production environments.

*   **Process:**
    1.  **Blue Environment:** The current live production environment (Version 1).
    2.  **Green Environment:** The new version (Version 2) is deployed here.
    3.  Once the Green environment is fully tested and stable, the **Load Balancer** redirects all traffic from Blue to Green.
*   **Pros:**
    *   **Zero Downtime:** The switch happens almost instantaneously at the load balancer level.
    *   **Instant Rollback:** If an issue is found in Green, traffic is simply routed back to Blue.
*   **Cons:** Expensive, as it requires double the hardware/resource capacity (two full sets of production environments).

---

## 5. Canary Deployment
Named after the "canary in a coal mine" concept, this involves testing the new version on a small subset of users.

*   **Process:**
    1.  Deploy the new version (Version 2) to a small percentage of the infrastructure (e.g., 5-10% of traffic).
    2.  The Load Balancer routes most users to V1 and a few to V2.
    3.  Monitor error rates and user feedback.
    4.  If stable, gradually increase the percentage of traffic to V2 until V1 is completely phased out.
*   **Pros:** Low risk; allows for real-world testing without impacting the entire user base.
*   **Cons:** Requires sophisticated traffic routing and monitoring tools.

---

## 6. A/B Testing
While similar to Canary, A/B testing is focused on business metrics and feature experimentation rather than just technical stability.

*   **Process:**
    1.  The user base is split into groups (Group A and Group B).
    2.  Group A receives Feature A; Group B receives Feature B (or the old version).
    3.  Data is sent to an **Analytic Service** to measure which version performs better (e.g., better conversion rates or user engagement).
*   **Outcome:** The "winner" is eventually promoted to 100% of the traffic, and the other version is discarded.
*   **Key Use Case:** UI/UX changes and behavioral testing.

---

## 7. Shadow Deployment
This is a risk-free way to test performance under real production load.

*   **Process:**
    1.  The new version (Version 2) is deployed alongside Version 1.
    2.  The **Load Balancer "shadows" (duplicates) the traffic.**
    3.  A request comes in; it is sent to Version 1 (which responds to the user) AND to Version 2 (which processes it in the background).
    4.  The response from Version 2 is logged and compared for accuracy but is **never sent to the user.**
*   **Pros:** Validates performance and logic against real production data with zero impact on user experience.
*   **Cons:** Very expensive (double resources) and complex to manage (especially regarding side effects like duplicate database writes or emails).

---

## 8. Feature Toggle Deployment
This strategy decouples code deployment from feature release.

*   **Process:**
    1.  Code for a new feature is deployed to production but remains "hidden" or "disabled."
    2.  A configuration or **Properties File** contains a toggle (True/False).
    3.  The feature can be turned on for specific user groups (e.g., Premium users or internal employees) without redeploying the code.
*   **Pros:** Allows for granular control over who sees what feature and when. If a feature causes issues, it can be "toggled off" instantly.
*   **Key Use Case:** "Members only" content or early-access features.

---

## Summary Table

| Strategy | Downtime | Risk | Cost | Description |
| :--- | :--- | :--- | :--- | :--- |
| **Recreate** | High | Medium | Low | Kill V1, then start V2. |
| **Rolling** | None/Low | Medium | Low | Replace instances one by one. |
| **Blue-Green** | None | Low | High | Switch traffic between two identical environments. |
| **Canary** | None | Low | Medium | Test V2 on a small subset of real users. |
| **A/B Testing** | None | Low | Medium | Compare two features for performance metrics. |
| **Shadow** | None | Lowest | High | Duplicate live traffic to V2 without user impact. |
| **Feature Toggle**| None | Low | Low | Enable/disable features via configuration. |