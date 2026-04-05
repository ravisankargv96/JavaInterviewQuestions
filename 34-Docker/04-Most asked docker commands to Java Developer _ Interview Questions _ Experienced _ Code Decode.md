# Detailed Notes: Most Asked Docker Commands for Java Developers

This guide covers the essential Docker commands required for dockerizing Spring Boot applications, frequently asked in interviews for Java developers.

---

### 1. System Information & Configuration
These commands provide a high-level overview of the Docker installation and current system state.

*   **`docker version`**: 
    *   Displays detailed information about the Docker Client and Server architectures.
    *   Includes OS details, API versions, Go version, and Git commit info.
*   **`docker info`**: 
    *   Provides system-wide information including the number of containers (running, paused, stopped) and the number of images.
    *   Shows storage drivers, CPU/Memory usage, and proxy settings.

---

### 2. Working with Docker Images
Images are the blueprints for containers. These commands handle the lifecycle of an image.

*   **`docker build -t <namespace>/<image-name>:<tag> <path>`**:
    *   Builds an image from a `Dockerfile`.
    *   `-t`: Flags the image with a tag (name and version).
    *   **Namespace**: Usually your Docker Hub username.
    *   **Path**: Use `.` to indicate the current directory as the build context.
*   **`docker images`**: 
    *   Lists all Docker images currently stored on your local system.
*   **`docker push <namespace>/<image-name>:<tag>`**: 
    *   Uploads your local image to a registry like Docker Hub. 
    *   *Note:* You must use the correct namespace/username for the push to succeed.
*   **`docker pull <image-name>:<tag>`**: 
    *   Downloads an image from Docker Hub to your local machine.
*   **`docker rmi <image-id>`**: 
    *   Removes an image from the local system.
    *   **Note**: If an image is being used by a container (even a stopped one), you must delete the container first or use the force flag (`-f`).

---

### 3. Container Management
Containers are the running instances of images.

*   **`docker run [options] <image-name>`**: 
    *   Creates and starts a container from an image.
    *   `-it`: Interactive mode with a TTY (terminal) attached.
    *   `-d`: Detached mode (runs the container in the background).
    *   `--name <name>`: Assigns a custom name to the container for easier management.
*   **`docker ps`**: 
    *   Lists all currently **running** containers.
*   **`docker ps -a`**: 
    *   Lists **all** containers, including those that have exited or stopped.
*   **`docker stop <container-id>`**: 
    *   Gracefully stops a running container.
*   **`docker start <container-id>`**: 
    *   Starts a container that was previously stopped.
*   **`docker kill <container-id>`**: 
    *   Forces a container to stop immediately.
*   **`docker rm <container-id>`**: 
    *   Deletes a stopped container.

---

### 4. Interacting with Running Containers
Commands used to debug or inspect containers while they are active.

*   **`docker exec -it <container-id> bash`**: 
    *   Allows you to enter a running container and run commands inside it via a bash shell.
    *   *Example*: Entering a MySQL container to run SQL queries via the command line (`mysql -u admin -p`).
*   **`docker logs <container-id>`**: 
    *   Fetches and displays the console output/logs of a specific container. This is crucial for debugging Spring Boot application startup errors.

---

### 5. Docker Networking
*   **`docker network ls`**: 
    *   Lists all networks configured in your Docker environment. Networks allow different containers (e.g., a Spring Boot app and a MySQL database) to communicate with each other.

---

### Summary Tips for Interviews
1.  **Tagging**: Always tag your images with a version (e.g., `1.0.0`) instead of relying on `latest` for better production tracking.
2.  **Cleanup**: To delete an image, you generally need to stop and remove the associated containers first.
3.  **GUI vs. CLI**: While Docker Desktop (GUI) is user-friendly, developers are expected to know the CLI commands as they are used in CI/CD pipelines (Jenkins, GitLab CI, etc.).
4.  **Namespace**: When pushing to Docker Hub, your image name must be prefixed with your Docker Hub username/namespace.