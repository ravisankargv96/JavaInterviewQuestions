These notes provide a comprehensive guide to dockerizing a Spring Boot application with a MySQL database using Docker Compose, based on the "Code Decode" video tutorial.

---

### **1. Introduction to Dockerization**
*   **Goal:** Learn how to dockerize a Spring Boot application, connect it to a MySQL database container, and manage both using Docker Compose.
*   **Core Concepts:** 
    *   **Dockerizing:** Wrapping an application in a container.
    *   **Networking:** Allowing containers to communicate on the same network.
    *   **Docker Compose:** A tool for defining and running multi-container Docker applications with a single command.

---

### **2. Docker Desktop vs. Docker Engine**
*   **Docker Engine:** The core client-server application. It uses a CLI (Command Line Interface).
*   **Docker Desktop:** Includes Docker Engine, Docker CLI, Docker Compose, a GUI, and Kubernetes support.
*   **Licensing Note:** 
    *   Docker Desktop is free for small businesses (under 250 employees and less than $10 million in revenue), personal use, and open-source projects.
    *   Large enterprises require a paid subscription. In these cases, companies often use the standalone Docker Engine to avoid license costs.

---

### **3. Minimum System Requirements**
*   **Windows:** Windows 10 or higher.
*   **Hardware:** At least 4GB RAM.
*   **Virtualization:** Must enable **Hyper-V** and **WSL2** (Windows Subsystem for Linux) in BIOS and Windows features. Docker creates a virtualization layer over the hardware to run containers.

---

### **4. Step 1: Create the Spring Boot Application**
1.  **Spring Initializr:** Generate a project with:
    *   **Project:** Maven
    *   **Language:** Java 17 (or 8/11)
    *   **Dependencies:** Spring Web, Spring Data JPA, MySQL Driver.
2.  **Basic Controller:** Create a `RestMapping` to test the application locally before dockerizing.
3.  **Local Testing:** Run the app and verify the endpoint (e.g., `localhost:8080/docker/test`) via Postman.

---

### **5. Step 2: Creating the Dockerfile**
A `Dockerfile` is a text document containing all the commands to assemble an image.
*   **FROM:** Specifies the base image (e.g., `openjdk:17-alpine`).
*   **WORKDIR:** Sets the working directory inside the container (e.g., `/opt`).
*   **COPY:** Copies the generated `.jar` file from the local `target` folder to the container.
*   **EXPOSE:** Informs Docker that the container listens on port 8080.
*   **ENTRYPOINT:** The command to execute the app: `["java", "-jar", "app.jar"]`.

---

### **6. Building and Running a Single Container**
1.  **Generate JAR:** Run `mvn install` to create the JAR file in the `target` folder.
2.  **Build Image:** `docker build -t <tag-name> .`
    *   *Note:* Image names must be lowercase.
3.  **Run Container:** `docker run -p 8080:8080 --name <container-name> <image-name>`
    *   **Port Forwarding (`-p`):** Maps the host machine's port (Windows) to the container's internal port.
    *   **Host URL:** Once inside Docker, use `host.docker.internal` instead of `localhost` to refer to the host machine.

---

### **7. Step 3: Docker Compose for Multi-Container Setup**
Docker Compose manages the Spring Boot app and MySQL database together.
*   **File Name:** `docker-compose.yml`
*   **Key Sections:**
    *   **Services:** Defines the containers (e.g., `app` and `db`).
    *   **Image:** Specifies which image to use (the Spring app image or the official `mysql:8.0` image).
    *   **Environment Variables:** Sets MySQL root password, database name, and user credentials.
    *   **Depends_on:** Ensures the database container starts before the application container.

#### **Sample Configuration logic:**
*   The `app` service points to the `db` service using the service name as the hostname (e.g., `jdbc:mysql://db:3306/db_name`).

---

### **8. Docker Networking**
*   **Default Bridge Network:** When Docker Compose starts, it creates a default network. All containers defined in the same `docker-compose.yml` are added to this network.
*   **Communication:** Containers on the same network can talk to each other using their service names (e.g., the Spring app can find MySQL by looking for the hostname `db`).
*   **Isolation:** This network provides security by isolating these containers from outside access unless ports are explicitly forwarded.

---

### **9. Implementation Live Demo (CRUD)**
1.  **Define Entity & Repository:** Create a simple JPA entity (ID, Name, Address).
2.  **Application Properties:** While `application.properties` contains local DB info, the values in `docker-compose.yml` will override them when running in Docker.
3.  **Commands:**
    *   `docker-compose up`: Starts all services.
    *   `docker-compose down`: Stops and removes containers.

---

### **10. Verifying Data inside the Container**
To prove the data is being stored in the Docker MySQL container and not a local DB:
1.  **Access Container CLI:** `docker exec -it <container_id> bash`
2.  **Login to MySQL:** `mysql -u admin -p`
3.  **Commands:**
    *   `show databases;`
    *   `use <database_name>;`
    *   `select * from <table_name>;`
4.  **Result:** You will see the records added via Postman stored inside the container's virtualized database.

---

### **Key Takeaways for Interviews**
*   **Port Forwarding:** Necessary to access the app from the host browser/Postman.
*   **Docker Compose Utility:** Best for microservices where multiple apps need to start and communicate simultaneously.
*   **Networking:** Docker Compose creates a project-specific bridge network by default.
*   **Persistence:** In a real-world scenario, you would use **Docker Volumes** to ensure database data is not lost when the container is deleted (mentioned as a future topic).