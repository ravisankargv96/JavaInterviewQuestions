Here are the detailed notes from the video tutorial on setting up a Microservices architecture using Spring Boot and Eureka.

### **Introduction**

* This tutorial is Part 2 of a Microservices series.  
* The goal is to implement a practical Microservices example involving:  
  1. **Eureka Server:** A Service Discovery Agent.  
  2. **Vaccination Center Service:** A microservice to manage vaccination centers.  
  3. **Citizen Service:** A microservice to manage citizen data (users).  
* The project demonstrates how these services communicate and register with Eureka.

### **1\. Creating the Eureka Server**

The Eureka Server acts as a registry where all microservices register themselves. This allows services to find each other without hardcoding URLs.

* **Project Setup:**  
  * Create a new Spring Boot project (e.g., named eureka-server).  
  * **Dependency:** Select Eureka Server (Spring Cloud Discovery).  
* **Configuration (application.properties or application.yml):**  
  * Define the port (default is usually 8761).  
  * Disable self-registration (since it *is* the server, it doesn't need to register with itself).  
  * Example configuration properties:  
    * server.port=8761  
    * eureka.client.register-with-eureka=false  
    * eureka.client.fetch-registry=false  
* **Main Class:**  
  * Add the @EnableEurekaServer annotation to the main application class to activate the Eureka Server registry.  
* **Running the Server:**  
  * Start the application.  
  * Access the dashboard at http://localhost:8761. It shows registered instances (currently empty).

### ---

**2\. Creating the Citizen Service (Microservice 1\)**

This service manages citizen data stored in a database.

* **Project Setup:**  
  * Create a new Spring Boot project (e.g., named citizen-service).  
  * **Dependencies:**  
    * Spring Web (for REST APIs).  
    * Spring Data JPA (for database interaction).  
    * MySQL Driver (or H2 for in-memory).  
    * Eureka Discovery Client (to register with Eureka).  
    * Lombok (for boilerplate code reduction).  
* **Configuration (application.yml):**  
  * server.port=8081 (Running on port 8081).  
  * spring.application.name=CITIZEN-SERVICE (Important: This name is used for service discovery).  
  * Database configurations (URL, username, password, Hibernate ddl-auto settings).  
  * Eureka Client configuration:  
    * eureka.client.service-url.defaultZone=http://localhost:8761/eureka/ (Tells the service where the Eureka Server is).  
* **Key Components:**  
  * **Entity (Citizen):** A JPA entity representing the citizen table with fields like id, name, and vaccinationCenterId.  
  * **Repository (CitizenRepository):** Extends JpaRepository to perform CRUD operations.  
    * Add a custom method findByVaccinationCenterId(Integer id) to fetch citizens by center ID.  
  * **Controller (CitizenController):**  
    * Annotated with @RestController.  
    * @RequestMapping("/citizen").  
    * **POST Endpoint (/add):** Saves a new citizen.  
    * **GET Endpoint (/id/{id}):** Fetches citizens by vaccination center ID.  
  * **Main Class:** Add @EnableEurekaClient.  
* **Testing:**  
  * Start the application.  
  * Check Eureka Dashboard: CITIZEN-SERVICE should appear.  
  * Use Postman to add sample citizens associated with specific vaccination center IDs.

### ---

**3\. Creating the Vaccination Center Service (Microservice 2\)**

This service manages vaccination centers and needs to communicate with the Citizen Service to get a list of citizens registered at a specific center.

* **Project Setup:**  
  * Create a new Spring Boot project (e.g., named vaccination-center-service).  
  * **Dependencies:** Same as Citizen Service (Web, JPA, MySQL, Eureka Client, Lombok).  
* **Configuration (application.yml):**  
  * server.port=8082 (Running on port 8082).  
  * spring.application.name=VACCINATION-CENTER-SERVICE.  
  * Database configurations.  
  * Eureka Client configuration pointing to localhost:8761.  
* **Key Components:**  
  * **Entity (VaccinationCenter):** Represents the vaccination\_center table (id, name, address).  
  * **Repository (CenterRepository):** Extends JpaRepository.  
  * **Wrapper Class (RequiredResponse):** A DTO (Data Transfer Object) created to combine the Center details and the List of Citizens into a single response object.  
  * **Controller (VaccinationCenterController):**  
    * **POST Endpoint (/add):** Adds a new vaccination center.  
    * **GET Endpoint (/id/{id}):**  
      * Fetches the Center details from its own database.  
      * **Inter-Service Communication:** Calls the Citizen Service to get the list of citizens for this center.  
      * Combines both into RequiredResponse and returns it.

### ---

**4\. Inter-Service Communication with RestTemplate**

To allow the Vaccination Center Service to call the Citizen Service:

* **Bean Configuration:**  
  * Define a RestTemplate bean in the main class or a configuration class.  
  * Annotate this bean with @LoadBalanced.  
    * **Importance:** This annotation tells RestTemplate to use the Eureka Service Registry to resolve service names (like CITIZEN-SERVICE) instead of physical IP addresses (like localhost:8081).  
* **Making the Call:**  
  * In the VaccinationCenterController:  
    Java  
    // Using Service Name instead of localhost  
    String url \= "http://CITIZEN-SERVICE/citizen/id/" \+ id;  
    List\<Citizen\> citizens \= restTemplate.getForObject(url, List.class);

  * This logic allows the service to find CITIZEN-SERVICE dynamically via Eureka, even if its port or IP changes.

### **5\. Running the Full Architecture**

1. Start **Eureka Server** (Port 8761).  
2. Start **Citizen Service** (Port 8081).  
3. Start **Vaccination Center Service** (Port 8082).  
4. **Verify:** Check localhost:8761 to see both services registered.  
5. **Test via Postman:**  
   * Add a center via Vaccination Service.  
   * Add citizens via Citizen Service (linking them to the center ID).  
   * Call the Vaccination Service GET endpoint: It should return the Center details **AND** the list of Citizens fetched from the other service.

### **6\. Fault Tolerance (Hystrix)**

The video briefly touches upon what happens if one service goes down.

* **Problem:** If Citizen Service is down, calling it from Vaccination Center Service will throw an error (500 Internal Server Error).  
* **Solution (Future Topic):** Using a Circuit Breaker (like Hystrix) to handle failures gracefully (e.g., returning a default response instead of crashing). *Note: The video mentions this will be covered in the next part.*