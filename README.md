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


Step 2: Sample REST Requests to Demonstrate the Implemented Use Cases
1. Create a New User
Method: POST
URL: http://localhost:8081/users

Payload:
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "address": "123 Main St"
}

3. Create a New Event
Method: POST
URL: http://localhost:8082/events

Payload:
{
  "eventName": "Tech Conference 2024",
  "eventDate": "2024-05-10",
  "location": "New York"
}

4. Register a User for an Event
Method: POST
URL: http://localhost:8083/registrations

Payload:
{
  "userId": 1,
  "eventId": 1
}

6. View User Profile
Method: GET
URL: http://localhost:8081/users/1

7. View Registrations for a User
Method: GET
URL: http://localhost:8083/registrations/user/1

8. View Registrations for an Event
Method: GET
URL: http://localhost:8083/registrations/event/1

After registering a user for an event, you can verify notifications in the Notification Service logs, where you will see messages such as:
User 1 registered for event 1
