These detailed notes provide a comprehensive guide to implementing Kafka with a Producer and Consumer in a Spring Boot application, based on the tutorial by Code Decode.

---

### **1. Introduction to the Kafka Architecture**
The goal is to create an end-to-end flow where a message travels from a client to a consumer through Kafka.

*   **Client (Third-party):** Hits a REST API with a message.
*   **Rest Controller:** Receives the message from the client and passes it to the Producer.
*   **Producer (Service):** Uses `KafkaTemplate` to push the message into a Kafka Topic.
*   **Kafka Topic/Broker:** Acts as the message store/buffer. Topics are internally divided into partitions for scalability.
*   **Consumer (Service):** Subscribes to the topic and listens for incoming messages.

---

### **2. Environment Setup**
Before coding, you must have Kafka and Zookeeper running locally.

*   **Download:** Download the Kafka binary (e.g., Scala 2.12 version). Zookeeper comes bundled with Kafka.
*   **Zookeeper:** Manages the Kafka cluster, handles leader election for partitions, and maintains metadata.
*   **Kafka Server:** The broker that handles the actual data.
*   **Default Ports:**
    *   Kafka: `9092`
    *   Zookeeper: `2181`

---

### **3. Project Configuration**

#### **A. Dependencies**
Create a Spring Starter project with the following dependencies:
1.  **Spring Web:** To create the REST API.
2.  **Spring for Apache Kafka:** To provide Kafka integration and `KafkaTemplate`.

#### **B. Application Configuration (application.yml)**
You must define where the Kafka server is running and how data should be serialized.
```yaml
spring:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: code-decode-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```
*   **Serialization/Deserialization:** Kafka transmits data in **bytes**. Since we are sending strings, we use `StringSerializer` (to convert String to bytes) and `StringDeserializer` (to convert bytes back to String).

---

### **4. Implementation Steps**

#### **Step 1: Create the Producer Service**
The Producer's job is to send messages to a specific topic.
*   Use `@Service` annotation.
*   Auto-wire `KafkaTemplate<String, String>`.
*   **Method:** `kafkaTemplate.send(topicName, message)`.

#### **Step 2: Create the REST Controller**
The controller provides an endpoint for the user to trigger a message.
*   Use `@RestController` and `@RequestMapping`.
*   Create a `@GetMapping` that accepts a `@RequestParam String message`.
*   Call the Producer Service within this method.

#### **Step 3: Create the Consumer Service**
The Consumer listens to the topic and processes the message.
*   Use `@Service` annotation.
*   **@KafkaListener:** Annotate the listener method with `@KafkaListener(topics = "topic-name", groupId = "group-id")`.
*   When a message arrives in the topic, this method is automatically triggered.

---

### **5. Key Concepts Explained**

#### **Group ID**
*   Kafka is designed to be fault-tolerant and scalable. 
*   **Load Balancing:** If multiple consumers are in the same Group ID, Kafka balances the load. Each partition in a topic is consumed by only one consumer in a group to prevent duplicate processing and prevent bombarding a single partition.
*   **Scalability:** You can add more consumers to a group to handle higher volumes of data.

#### **Zookeeper’s Role**
*   It manages the Kafka cluster state.
*   It keeps track of which brokers are part of the cluster.
*   It manages the status of topics and partitions.

---

### **6. Running the Demo**

To test the implementation, follow this specific order:

1.  **Start Zookeeper:** 
    *   Navigate to the Kafka `bin/windows` folder.
    *   Run: `zookeeper-server-start.bat ../../config/zookeeper.properties`
2.  **Start Kafka Server:**
    *   Run: `kafka-server-start.bat ../../config/server.properties`
3.  **Run Spring Boot Application:**
    *   Ensure the application connects to the broker at `9092`.
4.  **Hit the API:**
    *   Open a browser or Postman and enter: `http://localhost:8080/api/rest/producer-message?message=HelloKafka`
5.  **Verify:**
    *   Check the IDE console. You should see the Consumer log printing the message received from the topic.

---

### **7. Summary Checklist**
*   [ ] Kafka/Zookeeper installed and running.
*   [ ] `spring-kafka` dependency added.
*   [ ] `application.yml` configured with bootstrap servers and serializers.
*   [ ] Producer created using `KafkaTemplate`.
*   [ ] Consumer created using `@KafkaListener`.
*   [ ] REST Controller created to bridge the client and producer.