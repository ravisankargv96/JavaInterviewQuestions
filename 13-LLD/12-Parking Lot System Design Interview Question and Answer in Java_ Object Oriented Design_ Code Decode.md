These notes provide a comprehensive guide to designing a Parking Lot system using Object-Oriented Design (OOD) principles in Java, as discussed in the "Code Decode" tutorial.

---

### 1. Interview Strategy & Communication
*   **Communication is Key:** Success in system design interviews depends more on how you communicate and gather requirements than on pure coding knowledge.
*   **Don't Rush to Complexity:** Avoid jumping straight into a complex, multi-story design. Start with a High-Level Design (HLD) and move into a Lower-Level Design (LLD) based on the interviewer’s feedback.
*   **The "Right" Answer:** There is no single "correct" design. The goal is to create a system that is **efficient, scalable, and maintainable**.
*   **Process:**
    1.  Ask clarifying questions.
    2.  Gather requirements.
    3.  Explain your approach and thought process clearly.

---

### 2. Problem Statement & Requirements
*   **Definition:** A parking lot is an area designated for parking various types of vehicles for specific durations.
*   **Core Requirements:**
    *   The lot has multiple slots/spots.
    *   Support for different vehicle types (e.g., Cars, Trucks, Motorcycles).
    *   The system must track the number of available spots.
    *   If the lot is full, the system should show a "Full" message and deny entry.
    *   Ability to park and unpark vehicles.

---

### 3. Object-Oriented Design (OOD) Principles
A key rule in OOD is: **"Everything you see in the real world becomes a class or an enum."**

#### **A. Core Classes Identified:**
1.  **ParkingLot:** The central class that manages the entire operation.
2.  **ParkingSpot:** Represents individual slots where vehicles are parked.
3.  **Vehicle:** Represents the vehicles entering the lot.

#### **B. Enums (Fixed Types):**
Use enums when the number of objects/types is finite and known.
*   **VehicleType:** `CAR`, `TRUCK`, `BIKE`.
*   **ParkingSpotType:** `COMPACT`, `LARGE`, `MOTORBIKE`.

---

### 4. Class Structure and Attributes

#### **Vehicle Class**
*   **Attributes:**
    *   `vehicleNumber` (String)
    *   `vehicleType` (Enum)
    *   `parkingSpot` (A reference to the spot assigned to it — 1:1 mapping).
*   **Constructor:** Initializes the vehicle type.

#### **ParkingSpot Class**
*   **Attributes:**
    *   `type` (Enum: Compact, Large, or Motorbike)
    *   `isFree` (Boolean)
    *   `vehicle` (The vehicle currently occupying the spot).
*   **Subclasses:** `CompactSpot`, `LargeSpot`, `BikeSpot` (These extend the base `ParkingSpot` class).

#### **ParkingLot Class**
*   **Attributes:**
    *   Lists to track spots: `List<CompactSpot>`, `List<LargeSpot>`, `List<BikeSpot>`.
    *   Counters for free slots: `freeCompactSpots`, `freeLargeSpots`, `freeBikeSpots`.
*   **Methods:**
    *   `parkVehicle(Vehicle v)`
    *   `unParkVehicle(Vehicle v)`

---

### 5. Implementation Logic

#### **Parking Logic (Allocation):**
The system should attempt to park vehicles in the most appropriate spot first, then look for larger alternatives if the primary type is unavailable.
*   **Truck:** Can only be parked in a `Large` spot.
*   **Car:** 
    1.  Check `Compact` spot.
    2.  If none, check `Large` spot.
    3.  If both full, show "Full" message.
*   **Bike:** 
    1.  Check `Bike` spot.
    2.  If none, check `Compact` spot.
    3.  If none, check `Large` spot.

**Steps during parking:**
1.  Verify spot availability.
2.  Create/assign a `ParkingSpot` object.
3.  Set `isFree` to `false`.
4.  Link the vehicle to the spot and vice versa.
5.  Decrement the count of free spots.
6.  Add the spot to the occupied list.

#### **Unparking Logic (Deallocation):**
1.  Retrieve the `ParkingSpot` from the `Vehicle` object.
2.  Set the spot's `isFree` status to `true`.
3.  Remove the vehicle reference from the spot.
4.  Remove the spot from the active "occupied" list.
5.  Increment the count of free spots.

---

### 6. Code Structure Example (Java)

*   **Enums:** Define `VehicleType` and `ParkingSpotType`.
*   **Inheritance:** `LargeSpot` extends `ParkingSpot`. In the constructor, call `super(ParkingSpotType.LARGE)`.
*   **Data Structures:** Use `List` (like `ArrayList`) to manage collections of spots.
*   **Encapsulation:** Use private fields with getters and setters.

---

### 7. Scalability and Enhancements
Once the basic design is established, you can discuss how to make the system more robust:
*   **Multi-Floor Parking:** Introduce a `Level` or `Floor` class. The `ParkingLot` would then contain a `List<Level>`, and each `Level` would contain the lists of `ParkingSpots`.
*   **Payment System:** Add a `Ticket` class and a `Payment` module to calculate fees based on hours parked.
*   **Entrance/Exit Terminals:** Add classes for `EntryGate` and `ExitGate` to handle ticket dispensing and payment collection.
*   **Concurrency:** Mention handling multiple cars trying to park at the same time using thread-safe structures or synchronization.

---

### Summary Checklist for the Interview
*   [ ] Define Classes and Enums.
*   [ ] Establish relationships (e.g., Vehicle has a ParkingSpot).
*   [ ] Define the parking strategy (which vehicle fits where).
*   [ ] Implement the increment/decrement of available counts.
*   [ ] Discuss edge cases (lot full, vehicle not found during unparking).