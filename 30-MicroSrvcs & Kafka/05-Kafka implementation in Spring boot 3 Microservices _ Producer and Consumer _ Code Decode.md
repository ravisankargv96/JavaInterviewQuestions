These detailed notes provide a comprehensive guide to implementing Kafka in a Spring Boot 3 microservices architecture, based on the tutorial by Code Decode.

---

### **1. Introduction to Kafka in Microservices**
In a real-world production environment, Kafka is not used within a single application. Instead, it serves as a messaging backbone between multiple microservices. 
*   **Producer:** The service that sends messages to Kafka.
*   **Consumer:** The service (or multiple services) that retrieves and processes messages from Kafka.

### **2. Environment Setup and Installation**

#### **Step 1: Download and Extract**
1.  Download Apache Kafka (Binary downloads, typically the Scala 2.12 or 2.13 version).
2.  Extract the `.tgz` or `.zip` file to a local directory (e.g., `C:\Kafka` or `D:\Kafka`).

#### **Step 2: Run Zookeeper and Kafka (Windows)**
You must open two separate PowerShell windows in **Administrator Mode** to avoid permission issues with log files.
1.  **Start Zookeeper:**
    Navigate to the Kafka base folder and run:
    `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`
2.  **Start Kafka Server:**
    In the second window, run:
    `.\bin\windows\kafka-server-start.bat .\config\server.properties`

*Note: Ensure `JAVA_HOME` and system `Path` are correctly set for Java 17 or above.*

#### **Step 3: Monitoring Tool (Offset Explorer)**
To visualize topics and messages:
1.  Install **Offset Explorer** (formerly Kafka Tool).
2.  **Configuration:**
    *   **Cluster Name:** Local Kafka.
    *   **Bootstrap Server:** `localhost:9092`.
    *   **Settings:** Go to Topic Properties -> Change "Key" and "Value" from `Byte Array` to `String` and click **Update** to view human-readable messages.

---

### **3. Project Initialization**
Create two separate Spring Boot projects using Spring Initializr.

**Common Configurations:**
*   **Project:** Maven
*   **Language:** Java 17
*   **Spring Boot Version:** 3.3.4 (or latest 3.x)
*   **Dependencies:** 
    1.  `Spring Web`
    2.  `Spring for Apache Kafka`

---

### **4. Producer Microservice Implementation**

#### **A. application.properties (Producer)**
```properties
server.port=8081
spring.kafka.bootstrap-servers=localhost:9092

# Serialization: Java objects must be converted to strings for Kafka
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

# Custom property for topic name
kafka.topic.name=kafka-topic
```

#### **B. Producer Service**
The service uses `KafkaTemplate` to send messages.
*   **Constructor Injection:** Use it to inject `KafkaTemplate<String, String>`.
*   **Method:** A `sendMessage(String message)` method that calls `kafkaTemplate.send(topicName, message)`.

#### **C. Producer Controller**
*   Create a `@RestController`.
*   Implement a `@PostMapping("/send")` endpoint.
*   Accept a `@RequestBody String message` and pass it to the service.

---

### **5. Consumer Microservice Implementation**

#### **A. application.properties (Consumer)**
```properties
server.port=8082
spring.kafka.bootstrap-servers=localhost:9092

# Deserialization: Convert Kafka strings back to Java
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

# Consumer Group ID (Essential for group management)
spring.kafka.consumer.group-id=code-decode-group

# Custom property for topic name
kafka.topic.name=kafka-topic
```

#### **B. Consumer Service**
The consumer uses the `@KafkaListener` annotation to monitor the topic.
```java
@Service
public class ConsumerService {
    
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
```

---

### **6. Workflow and Testing**

1.  **Start the Apps:** Run both the Producer (Port 8081) and Consumer (Port 8082).
2.  **Automatic Topic Creation:** When the Producer sends the first message, Kafka automatically creates the topic if it doesn't exist.
3.  **Send Request:** Use Postman to send a POST request:
    *   **URL:** `http://localhost:8081/send`
    *   **Body:** "Hello Kafka from Microservices" (Raw Text).
4.  **Verification:**
    *   **Console:** The Consumer's console should print the received message.
    *   **Offset Explorer:** You can see the message appended to the specific partition and offset in the Kafka topic.

---

### **7. Key Concepts Explained**

*   **Serialization/Deserialization:** Kafka only understands bytes. Serializers convert Java Strings to bytes (Producer side), and Deserializers convert them back (Consumer side).
*   **KafkaTemplate:** A high-level Spring abstraction that simplifies sending data to Kafka topics.
*   **KafkaListener:** A Spring annotation that creates a message listener container behind the scenes to poll Kafka for new data.
*   **Partitions & Offsets:** 
    *   Topics are divided into **Partitions**.
    *   Messages are stored in partitions in an append-only fashion.
    *   Each message in a partition is assigned a unique ID called an **Offset**.
*   **Consumer Groups:** A group of consumers working together to read from a topic. Each message in a partition is only delivered to one consumer instance within a group, ensuring load balancing.

---

### **8. Summary of Steps for Implementation**
1.  **Setup Kafka & Zookeeper** locally.
2.  **Define Properties** for bootstrap servers and serializers in both apps.
3.  **Producer:** Create a Controller and use `KafkaTemplate` in a Service.
4.  **Consumer:** Use `@KafkaListener` to consume messages.
5.  **Test** using Postman and monitor using Offset Explorer.