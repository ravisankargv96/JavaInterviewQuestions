# System Design Notes: YouTube (Video Sharing Platform)
**Source:** Code Decode Mock Interview (5+ Years Experience)

---

## 1. Requirements Gathering
The first step in any system design is defining the scope through functional and non-functional requirements.

### Functional Requirements
*   **Video Upload:** Users (Creators) should be able to upload videos.
*   **Video Streaming:** Users should be able to watch/stream videos with minimal lag.
*   **Search/Browse:** Functionality to search for videos by title or tags.
*   **Metadata Management:** Storing titles, descriptions, tags, and thumbnails.
*   **Analytics:** Creators should see how their videos are performing.
*   **Multi-device Support:** Seamless experience across Mobile, TV, Laptop (Web), and Tablets.
*   **Recommendations:** A feed page suggesting videos based on user behavior.
*   **Social Interactions:** Like, comment, share, and subscribe.

### Non-Functional Requirements
*   **High Availability:** The system must be available 24/7; downtime is not acceptable.
*   **Scalability:** Must handle millions of concurrent users and massive data spikes.
*   **Reliability:** Uploaded videos should not be lost.
*   **Low Latency:** Streaming should start instantly (buffering should be minimal).
*   **Consistency:** Eventual consistency is acceptable (e.g., it's okay if a video takes 2–5 minutes to appear in search after upload).
*   **Cost Optimization:** Efficient use of CDNs and storage to manage costs.

---

## 2. Capacity Planning & Estimations
*   **Daily Active Users (DAU):** ~5 Million.
*   **Uploaders:** ~10% of DAU (500,000 creators uploading per day).
*   **Average Video Size:** 300 MB (assuming 15–20 min HD content).
*   **Daily Storage Requirement:** 500,000 * 300 MB ≈ **150 TB per day**.
*   **Replication Factor:** 3x (Standard for fault tolerance) ≈ **450 TB to 1.5 PB** of storage management including transcoded versions.
*   **CDN Costs:** Estimated at $0.02 per GB. High-traffic platforms like YouTube require billions in bandwidth investment.

---

## 3. High-Level Architecture: The Upload Flow
The upload process is complex because a single raw file must be converted into multiple formats.

1.  **Load Balancer:** Distributes incoming upload requests to API Servers.
2.  **API Servers:** Handle metadata (title, tags) and route the video file.
3.  **Metadata DB & Cache:** 
    *   Stores video info (SQL is preferred if schema is fixed; NoSQL for scalability).
    *   **Redis** is used as a cache for quick metadata retrieval.
4.  **Original Storage (S3):** The raw video is first uploaded to a temporary "Blob" storage (e.g., Amazon S3).
5.  **Transcoding Server & Completion Queue:** 
    *   Once the upload is finished, a message is sent to a **Completion Queue** (e.g., Kafka).
    *   The **Transcoding Server** picks the task from the queue.
6.  **Transcoding Process:** The video is split into chunks and encoded into various resolutions (360p, 720p, 1080p, 4K) and formats (HLS, DASH).
7.  **Transcoded Storage & CDN:** The processed chunks are stored in a final S3 bucket and pushed to **CDN (Content Delivery Networks)** for global distribution.

---

## 4. High-Level Architecture: The Streaming/Viewing Flow
1.  **Client Request:** The user clicks "Play."
2.  **CDN Interaction:** The client fetches video chunks from the **Edge Server (CDN)** closest to their geographical location to reduce latency.
3.  **Adaptive Bitrate Streaming:** 
    *   The system detects the user's network speed.
    *   If the connection is slow, it fetches 360p chunks; if fast, it switches to 1080p.
4.  **Analytics Service:** While streaming, heartbeats are sent to the Analytics service to track "watch time" and "click-through rates."

---

## 5. Deep Dive: Video Transcoding & DAG
Transcoding is computationally expensive. To manage this, the system uses a **DAG (Directed Acyclic Graph)** model.

*   **Splitter:** Breaks the video into small chunks (GOP - Group of Pictures).
*   **DAG Scheduler:** Orchestrates tasks in stages:
    *   **Stage 1:** Split video, extract audio, extract metadata.
    *   **Stage 2:** Parallel processing (Encoding 720p, Encoding 1080p, generating thumbnails, applying watermarks).
    *   **Stage 3:** Merge audio and video segments back together.
*   **Resource Manager:** Manages the worker nodes (pods) to ensure high parallelism and efficient CPU/GPU usage.

---

## 6. Database Design & Sharding
*   **Metadata DB:** Contains `Video_ID`, `Creator_ID`, `Title`, `Description`, `Thumbnail_URL`.
*   **Database Sharding:** Since the data is massive, we shard the database.
    *   **Sharding Key:** `Video_ID`.
    *   **Routing:** A "Key-Value Store" (like Zookeeper) can act as a service discovery layer to map a `Video_ID` to a specific database instance.
*   **Video Engagement DB:** A separate SQL database handles high-frequency writes like "Likes" and "View Counts" using counters.

---

## 7. Optimizations & Error Handling
*   **Fault Tolerance in Upload:** If a chunk fails during upload, the system uses **Checkpoints**. The user doesn't have to re-upload the whole 300MB; they only resume the failed chunk.
*   **Cost Optimization (CDN):** 
    *   **Popular/Trending Videos:** Kept in CDN for instant access.
    *   **Cold Videos:** Older, rarely watched videos are evicted from CDN and kept in standard S3 storage to save costs.
*   **Upload Speed:** To speed up uploads for creators, we can use **CDN as an Upload Server**. The creator uploads to the nearest Edge location, which then transfers the file to the main data center via a high-speed backbone network.
*   **User Analytics:** Uses a **Wide-Column Store** (like Cassandra) to store dynamic user behavior data for the recommendation engine.

---

## 8. Summary of Components
| Component | Technology | Purpose |
| :--- | :--- | :--- |
| **Storage** | S3 / Blob Storage | Storing raw and transcoded video files. |
| **Cache** | Redis | Storing frequently accessed metadata and user sessions. |
| **Message Queue** | Kafka / RabbitMQ | Decoupling upload from transcoding. |
| **Database** | MySQL / PostgreSQL | Structured metadata storage (with sharding). |
| **Analytics** | Cassandra / ClickHouse | Storing massive streams of user interaction data. |
| **Distribution** | Cloudfront / Akamai | Global Content Delivery Network. |