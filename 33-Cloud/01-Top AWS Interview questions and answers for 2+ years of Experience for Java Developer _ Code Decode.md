# AWS Interview Study Notes: 2-3 Years Experience (Java Developer Perspective)

These notes are based on the "Code Decode" video regarding fundamental AWS concepts and services relevant to developers with 2+ years of experience.

---

## 1. Introduction to Cloud Computing and AWS
*   **Cloud Computing:** A network of remote servers, data storage, and services accessible over the internet rather than on a local system.
*   **AWS (Amazon Web Services):** A comprehensive cloud platform provided by Amazon that allows users to rent compute power, storage, and databases on a **pay-as-you-go** model.
*   **Key Advantages:**
    *   **No Upfront Cost:** No need to purchase physical hardware.
    *   **Flexibility/Scalability:** Scale resources up during high traffic (e.g., a sale) and down when demand decreases.
    *   **Reduced Maintenance:** AWS handles hardware maintenance, patches, and physical security.
    *   **High Availability:** Data is replicated across different zones to prevent loss during server failures.

---

## 2. Key AWS Components & Services

### Compute & Serverless
*   **EC2 (Elastic Compute Cloud):** Provides virtual servers (instances) in the cloud. It is like renting a computer where you can run your Java applications.
*   **Lambda:** A **serverless** computing service. You upload your code, and AWS manages the infrastructure, scaling it automatically based on incoming requests.

### Storage & Database
*   **S3 (Simple Storage Service):** Scalable object storage for files, images, and videos.
*   **RDS (Relational Database Service):** A managed service for SQL databases (MySQL, PostgreSQL, Oracle, etc.). AWS handles backups and software patching.

### Networking & Security
*   **VPC (Virtual Private Cloud):** Allows you to create a private, isolated network within the AWS cloud for enhanced security.
*   **IAM (Identity and Access Management):** Manages user access and permissions. You can create roles for different employee levels (e.g., Junior Developers vs. Admins) to control who can delete or create resources.

### Messaging & Monitoring
*   **SNS (Simple Notification Service):** A messaging service for sending emails, SMS, or push notifications (e.g., order confirmations).
*   **CloudWatch:** A monitoring and observability service. It tracks metrics, collects logs, and sets alarms to notify you if your production application goes down or hits high error rates.

---

## 3. Deep Dive: Amazon EC2 (Elastic Compute Cloud)

### EC2 Pricing Models
1.  **On-Demand:** Pay for what you use by the second/hour. Best for short-term, unpredictable workloads or testing.
2.  **Reserved:** Commit to a 1-3 year term for a significant discount. Best for steady-state, long-term applications.
3.  **Spot:** Bid for unused AWS capacity at huge discounts (up to 90%). **Risk:** AWS can terminate these instances with 2 minutes' notice if they need the capacity back.
4.  **Dedicated Hosts:** A physical server dedicated entirely to your use. Best for strict licensing or regulatory requirements.

### AMI (Amazon Machine Image)
*   A template containing the OS, application server, and configurations.
*   Used to launch new instances with identical setups.
*   **Sharing:** AMIs can be shared with other AWS accounts by modifying permissions and providing the target Account ID.

### Elastic IP
*   A **static IPv4 address** designed for dynamic cloud computing.
*   Unlike standard public IPs (which change when an instance restarts), an Elastic IP stays associated with your account.
*   **Limit:** Typically 5 Elastic IPs per region per account.

### Stopping vs. Terminating
*   **Stopping:** Like turning off a laptop. The instance stays in your account, and data on the attached EBS volume persists. You aren't charged for "compute" but are charged for "storage."
*   **Terminating:** Like throwing the laptop away. The instance is deleted, and attached volumes are usually deleted. There is no way to recover a terminated instance.

### Key Pairs
*   AWS uses **public-key cryptography** to secure logins.
*   The **Public Key** is stored on the AWS server.
*   The **Private Key** (PEM file) is kept by the developer. Access is only granted if the keys match.

---

## 4. Deep Dive: Amazon S3 (Simple Storage Service)

### Key Terminology
*   **Buckets:** The fundamental containers for data (similar to folders).
*   **Objects:** The actual files (images, logs, videos) stored in buckets.
*   **Keys:** The unique identifier (path) for an object within a bucket.

### S3 Storage Classes
The choice depends on the trade-off between **Frequency of Access, Latency, and Cost.**
1.  **Standard:** High durability, availability, and low latency. For frequently accessed data.
2.  **Standard-IA (Infrequent Access):** Lower storage cost but higher retrieval fee. For data accessed less often but needed quickly when requested.
3.  **One Zone-IA:** Even lower cost, but data is stored in only one availability zone (less resilient).
4.  **Glacier:** Low-cost storage for archiving. Retrieval takes minutes to hours.
5.  **Glacier Deep Archive:** Lowest cost storage. Retrieval can take up to 12 hours. Best for data kept for 7-10 years for regulatory reasons.

### S3 Facts for Interviews
*   **Durability:** Designed for 99.999999999% (11 nines) durability.
*   **Bucket Limit:** 100 buckets per account by default.
*   **Object Size:** Minimum 0 bytes; Maximum **5 Terabytes**.
*   **Static Hosting:** S3 can be used to host static websites (HTML/CSS/JS) without a server.

---

## 5. Use Cases for Java Developers
*   **Application Deployment:** Running Spring Boot JARs on EC2 or inside Docker containers on AWS.
*   **Log Management:** Storing application logs in S3 and using CloudWatch for real-time monitoring.
*   **Database Management:** Connecting Java applications to RDS for automated scaling and backups.
*   **CI/CD:** Deploying code via Jenkins/ArgoCD into AWS environments like EKS (Elastic Kubernetes Service).