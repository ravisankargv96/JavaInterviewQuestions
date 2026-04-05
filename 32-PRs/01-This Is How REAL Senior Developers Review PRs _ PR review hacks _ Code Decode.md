Detailed notes for the video **"This Is How REAL Senior Developers Review PRs | PR review hacks"** by Code Decode:

### **Introduction: The Mindset of a Senior Reviewer**
A Pull Request (PR) review is not just a "check-the-box" activity or a search for syntax errors. For a senior developer, it is a critical process for maintaining code quality, ensuring system stability, and mentoring junior team members. The goal is to catch bugs early, ensure maintainability, and share knowledge across the team.

---

### **1. Understanding the Context (The "Why")**
Before looking at a single line of code, a senior developer seeks to understand the purpose of the PR:
*   **Requirement Alignment:** Does the code actually solve the problem described in the Jira ticket or user story?
*   **Scope Check:** Is the PR focused? If it tries to solve five different problems at once, it should be broken down into smaller PRs.
*   **Business Logic:** Does the developer understand the business rules, or have they just written syntactically correct code that doesn't meet the actual need?

---

### **2. Logic and Functional Correctness**
This is the core of the review. The reviewer mentally executes the code:
*   **Edge Cases:** What happens if the input is null, empty, or an unexpected data type?
*   **Boundary Conditions:** If a loop runs from 0 to N, does it handle 0 and N correctly?
*   **Error Handling:** Are exceptions caught? Is there a fallback mechanism if an external API or database call fails?
*   **Resource Management:** Are database connections, file streams, or network sockets properly closed to avoid leaks?

---

### **3. Code Quality and Clean Code Principles**
Senior developers look for long-term maintainability using established patterns:
*   **SOLID Principles:** Does the code follow Single Responsibility? Is it open for extension but closed for modification?
*   **DRY (Don't Repeat Yourself):** Is there duplicated logic that should be extracted into a reusable utility or service?
*   **Naming Conventions:** Are variables, methods, and classes named meaningfully? (e.g., `calculateMonthlyTax()` is better than `calc()`).
*   **Method Length:** Is a single function doing too much? Senior devs prefer small, testable functions over "God methods."

---

### **4. Performance and Optimization**
Code that works in a local environment might fail in production under load:
*   **N+1 Query Problem:** In database operations, is the code making a new query inside a loop? This should be optimized into a single join or batch query.
*   **Nested Loops:** Is the complexity O(n²)? Can it be optimized using HashMaps or better algorithms?
*   **String Manipulation:** Is the code using `String` in a loop (creating multiple objects) instead of `StringBuilder`?
*   **Caching:** Is the code fetching the same static data repeatedly instead of caching it?

---

### **5. Security Auditing**
Security is a non-negotiable part of a senior review:
*   **Sensitive Data:** Are API keys, passwords, or PII (Personally Identifiable Information) hardcoded? (They should be in environment variables or secret managers).
*   **Injections:** Is the code vulnerable to SQL injection? (Ensure the use of Prepared Statements/Parameterized queries).
*   **Logging:** Are we logging sensitive information like passwords or credit card numbers? (This is a major compliance violation).

---

### **6. Testing and Documentation**
A PR is incomplete without proof that it works:
*   **Unit Tests:** Are there tests for the new logic? Do the tests cover both "happy paths" and "negative paths"?
*   **Meaningful Assertions:** Are the tests actually checking the output, or are they just running the code to increase "code coverage" percentages?
*   **Comments:** Is the "Why" documented? Code should explain "How," but comments should explain "Why" a specific complex decision was made.

---

### **7. The "Art" of Giving Feedback (Soft Skills)**
How a senior developer communicates is as important as what they find:
*   **Be Kind and Professional:** Use a constructive tone. Avoid "You did this wrong."
*   **Use "We" and Questions:** Instead of saying "Your code is slow," say "Can we optimize this loop to improve performance?" or "What happens if this list is empty?"
*   **Explain the Reason:** Don't just say "Change this." Explain *why* it should be changed (e.g., "Changing this to a Set will make the lookup O(1) instead of O(n)").
*   **Praise Good Work:** If you see a clever solution or very clean code, acknowledge it. This builds morale and reinforces good habits.

---

### **Summary Checklist for Senior PR Reviews**
1.  **Read the Ticket:** Understand the requirement.
2.  **Run the App (if possible):** Verify the functionality.
3.  **Check Logic:** Look for edge cases and leaks.
4.  **Review Standards:** Ensure it follows the team's style guide and SOLID principles.
5.  **Check Security/Performance:** Look for bottlenecks and vulnerabilities.
6.  **Verify Tests:** Ensure the code is robust.
7.  **Submit Feedback:** Be clear, educational, and respectful.