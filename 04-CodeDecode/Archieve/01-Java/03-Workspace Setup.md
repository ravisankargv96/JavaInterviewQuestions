Here are the detailed notes from the video on setting up a Java workspace and creating a "Hello World" program, with the links removed:

### **Java Workspace Setup and Hello World Program**

**1. Installing Java JDK 8**

* **Download:** Navigate to your web browser and search for "Java JDK 8". Select the official Oracle link and download the version suitable for your operating system (e.g., Windows 64-bit).
* **Set Environment Path:**
* Open "This PC" (My Computer) and go to **Properties** > **Advanced system settings** > **Environment Variables**.
* Under "User variables," locate the `Path` variable.
* Copy the path where Java was installed (typically `C:\Program Files\Java\jdk1.8...`).
* Click "New," paste the copied path, and save changes by clicking "OK" through the dialog boxes.



**2. Installing an IDE**

* An Integrated Development Environment (IDE) is required for coding. Common choices include Eclipse, IntelliJ IDEA, or Spring Tool Suite (STS).
* The demonstration uses Spring Tool Suite (STS).

**3. Creating a Java Project**

* Open the IDE and go to **File** > **New** > **Java Project**.
* Enter a project name (e.g., "Code Decode") and click **Finish**.
* If prompted to open a specific "perspective," you can choose "No" to stay in the current view.

**4. Creating a Package**

* Expand the project structure and right-click on the `src` (source) folder.
* Select **New** > **Package**.
* **Naming Convention:** It is good practice to name packages using the reverse of a domain name (e.g., `com.code_decode.startup`).
* Click **Finish**.

**5. Creating a Class**

* Right-click on the newly created package and select **New** > **Class**.
* **Naming Convention:** Class names should always start with a capital letter (e.g., `Demo`).
* **Main Method:** Check the box for `public static void main(String[] args)` to automatically generate the main method, which is required to run the code.
* Click **Finish** to generate the `Demo.java` file.

**6. Writing the Hello World Program**

* Inside the generated `main` method, type the command to print text to the console:
`System.out.println("Hello World");`.
* **Note:** The text "Hello World" must be enclosed in double quotes because it is a String.

**7. Running the Program**

* Save the file.
* Click the **Run** (Play) button in the IDE toolbar.
* The console output at the bottom of the screen should display: `Hello World`.