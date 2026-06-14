Restful Booker API Automation Framework

This project is an API automation framework built with Java, Rest Assured, TestNG, Maven, and Lombok using the public Restful Booker API.

The main goal of this project is to demonstrate API test automation skills with a clean layered structure, reusable client methods, POJO-based request/response models, and CRUD test coverage.

Technologies Used
Java 17
Maven
Rest Assured
TestNG
Lombok
Project Structure
src/test/java
├── Base
│   └── BaseApiTest.java
│
├── Clients
│   ├── AuthClient.java
│   └── BookingClient.java
│
├── Models
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── Booking.java
│   ├── BookingDates.java
│   └── BookingResponse.java
│
└── Tests
    ├── AuthTest.java
    └── BookingTest.java
Test Coverage
Authentication
Create authentication token
Validate token is generated successfully
Booking CRUD Operations
Create a booking
Get booking by ID
Update an existing booking
Delete a booking
Verify deleted booking returns 404
Framework Design

The framework is organized into separate layers:

Base

Contains common API setup such as the base URI.

Models

Contains POJO classes used for request and response mapping.

Clients

Contains reusable API methods for each endpoint.

Examples:

createBooking()
getBookingById()
updateBooking()
deleteBooking()
getBookingStatusCode()
Tests

Contains TestNG test scenarios that verify API behavior.

Implemented Test Flows
Create Booking

Creates a booking and verifies:

Booking ID is generated
First name is correct
Last name is correct
Get Booking

Creates a booking, retrieves it by ID, and verifies returned booking details.

Update Booking

Creates a booking, updates selected fields using an authentication token, retrieves the booking again, and verifies updated values.

Delete Booking

Creates a booking, deletes it using an authentication token, and verifies that the deleted booking returns HTTP 404.

How to Run Tests

Clone the repository:

git clone <repository-url>

Navigate to the project folder:

cd restful-booker-api-automation

Run tests with Maven:

mvn test
Notes
Restful Booker is a public test API.
Test data is created dynamically during test execution.
Authentication token is used for protected endpoints such as update and delete operations.
POJO classes are used for serialization and deserialization.
Author

Yiğit Taşdelen
