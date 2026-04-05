# Study Notes: Workspace Setup and Hello World in Java 8

These notes are based on the "Code Decode" video regarding the initial setup of a Java development environment and the creation of a first Java program.

---

## 1. Installing Java JDK 8
Before writing any code, you must install the Java Development Kit (JDK).
*   **Source:** Search for "Java JDK 8" in a web browser and navigate to the official Oracle website.
*   **Version:** Select the version suitable for your operating system (e.g., Windows 64-bit).
*   **Installation:** Download the installer and follow the standard installation prompts.

## 2. Setting the Java Path (Environment Variables)
After installation, you must configure the system so it recognizes Java commands from the terminal/command prompt.

1.  **Locate JDK Path:** Navigate to your installation folder (typically `C:\Program Files\Java\jdk1.8.x`). Copy the path to the folder.
2.  **Access System Settings:**
    *   Right-click **This PC** (or My Computer) and select **Properties**.
    *   Click on **Advanced system settings**.
    *   Click the **Environment Variables** button.
3.  **Update Path Variable:**
    *   Under **User variables**, find the variable named **Path** and click **Edit**.
    *   Click **New** and paste the path to your Java JDK folder.
    *   Click **OK** on all windows to save the changes.

## 3. IDE (Integrated Development Environment) Setup
An IDE makes coding easier by providing tools for writing, debugging, and running code.
*   **Recommended IDEs:** The video demonstrates using **STS (Spring Tool Suite)**, but you can also use **Eclipse** or **IntelliJ IDEA**.
*   Open the IDE once it is installed to begin a new project.

## 4. Creating Your First Java Project
1.  Go to **File > New > Java Project**.
2.  **Project Name:** Enter a name (e.g., `code decode`).
3.  Click **Finish**.

## 5. Creating a Package
Packages are used to organize classes and avoid naming conflicts.
1.  Right-click on the `src` folder.
2.  Select **New > Package**.
3.  **Naming Convention:** It is a standard practice to name packages using the reverse of a domain name (e.g., `com.codedecode.startup`). 
4.  Click **Finish**.

## 6. Creating a Java Class
1.  Right-click on your new package.
2.  Select **New > Class**.
3.  **Class Name:** The name must start with a **Capital letter** (e.g., `Demo`).
4.  **Main Method:** Check the box for `public static void main(String[] args)`. This is the entry point where the program starts execution.
5.  Click **Finish**.

## 7. Writing the "Hello World" Program
Inside the `main` method, use the print statement to display text on the console.

```java
public class Demo {
    public static void main(String[] args) {
        // Printing Hello World to the console
        System.out.println("hello world");
    }
}
```

### Key Coding Notes:
*   **System.out.println:** The command used to print a line of text to the console.
*   **Double Quotes:** Text (Strings) must be enclosed in double quotes (e.g., `"hello world"`).
*   **Case Sensitivity:** Java is case-sensitive; ensure `System` starts with a capital 'S'.

## 8. Running the Program
1.  **Save:** Always save your file before running.
2.  **Run:** Click the green **Play** button in the IDE toolbar.
3.  **Output:** The message "hello world" will appear in the **Console** window at the bottom of the IDE.

---

### Summary of Naming Conventions
*   **Packages:** Lowercase, usually starting with `com.`, `org.`, etc.
*   **Classes:** CamelCase, starting with an **Uppercase** letter.
*   **Methods/Variables:** Usually start with a lowercase letter.