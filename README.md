Step 1: Instructions to Set Up and Run the Application
Clone the Project Repository

If hosted on GitHub, clone the repository using:

git clone [https://github.com/your-username/event-registration-system.git](https://github.com/NightHawk-02/event-registration-system.git)
Navigate into the project directory:
cd event-registration-system

Set Up Apache Kafka
Download and extract Apache Kafka from Kafka Downloads.

Start Zookeeper:
bin/zookeeper-server-start.sh config/zookeeper.properties

Start Kafka Broker:
bin/kafka-server-start.sh config/server.properties

Build the Microservices
Navigate into each microservice folder (e.g., user-service, event-service, registration-service, notification-service) and build the project using Maven:
mvn clean install

Run Each Microservice
Run each microservice using the following command:
mvn spring-boot:run

Alternatively, if you prefer running the JAR files, use:
java -jar target/your-service-name.jar

Test the Application
Use Postman or curl to send REST requests and interact with the application. Follow the sample REST requests below to demonstrate the use cases.

1. User Service: Manage Users
URL: http://localhost:8081/users

1.1 Create a New User
Method: POST
Endpoint: /users
Payload:
{
    "userId": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "street": "456 Elm St",
    "city": "Los Angeles",
    "state": "CA",
    "zipCode": "90001"
}

Action: This creates a new user in the system. The response includes the userId generated for the user.

1.2 Get User Details
Method: GET
Endpoint: /users/{userId}
Example: /users/1
Expected Response:
{
    "userId": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "street": "456 Elm St",
    "city": "Los Angeles",
    "state": "CA",
    "zipCode": "90001"
}
Status Code: 200 OK
Action: This fetches the details of the user with the given userId.

1.3 Update User Details
Method: PUT
Endpoint: /users/{userId}
Example: /users/1
Payload:
{
    "userId": 1,
    "name": "John Doe",
    "email": "newemail@example.com",
    "phoneNumber": "0987654321",
    "street": "789 Pine St",
    "city": "San Francisco",
    "state": "CA",
    "zipCode": "94101"
}

Status Code: 200 OK
Action: This updates the user’s details with the given userId.

1.4 Delete User
Method: DELETE
Endpoint: /users/{userId}
Example: /users/1
Status Code: 200 OK
Action: This deletes the user with the given userId from the system.

2. Event Service: Manage Events
URL: http://localhost:8082/events

2.1 Create a New Event
Method: POST
Endpoint: /events
Payload:
{
    "eventId": 1,
    "title": "Tech Conference 2024",
    "description": "A conference about the latest in technology trends.",
    "date": "2024-05-10",
    "location": "New York",
    "capacity": 500
}

Status Code: 201 Created
Action: This creates a new event in the system.

2.2 Get Event Details
Method: GET
Endpoint: /events/{eventId}
Example: /events/1
Expected Response:
{
    "eventId": 1,
    "title": "Tech Conference 2024",
    "description": "A conference about the latest in technology trends.",
    "date": "2024-05-10",
    "location": "New York",
    "capacity": 500
}

Status Code: 200 OK
Action: This retrieves the details of the event with the given eventId.

2.3 Update Event Details
Method: PUT
Endpoint: /events/{eventId}
Example: /events/1
Payload:
{
    "eventId": 1,
    "title": "Updated Tech Conference 2024",
    "description": "An updated description for the upcoming tech conference.",
    "date": "2024-06-15",
    "location": "Los Angeles",
    "capacity": 1000
}
Status Code: 200 OK
Action: This updates the event’s details.

2.4 Delete Event
Method: DELETE
Endpoint: /events/{eventId}
Example: /events/1
Expected Response:
Status Code: 200 OK
Action: This deletes the event with the given eventId.

3. Registration Service: Register Users for Events
URL: http://localhost:8083/registrations

3.1 Register a User for an Event
Method: POST
Endpoint: /registrations
You should pass userId and eventId as query parameters in the URL, like this:
http://localhost:8083/registrations?userId=1&eventId=1
Expected Response:
{
  "registrationId": 1,
  "userId": 1,
  "eventId": 1,
  "status": "REGISTERED"
}
Status Code: 200 OK
Action: This registers the user for the specified event. This should also publish the UserRegisteredEvent to Kafka, triggering real-time notifications.

3.2 View Registrations by User
In this case, the request URL will contain the userId as a query parameter instead of passing it in the path.
http://localhost:8083/registrations?userId=1
Method: GET
Expected Response:
[
  {
    "registrationId": 1,
    "userId": 1,
    "eventId": 1,
    "status": "REGISTERED"
  }
]

Status Code: 200 OK
Action: This retrieves all events that the user with userId has registered for.
3.3 View Registrations by Event
Similarly, for viewing registrations by event, eventId will be passed as a query parameter in the URL.
Method: GET
Updated URL:
http://localhost:8083/registrations?eventId=1
Expected Response:
[
  {
    "registrationId": 1,
    "userId": 1,
    "eventId": 1,
    "status": "REGISTERED"
  }
]

Status Code: 200 OK
Action: This retrieves all users that have registered for the event with eventId.
4. Notification Service: Verify Event Consumption
The Notification Service consumes events from Kafka when a user registers for an event. After successfully registering a user for an event, check the logs of the Notification Service to verify that it received and processed the event.

Check Logs:
Expected Log Message:
User 1 registered for event 1
This indicates that the UserRegisteredEvent was consumed and the notification process was triggered successfully.
