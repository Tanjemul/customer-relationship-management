
# Customer Relationship Management (Backend)
This project is a Spring Boot-based backend application designed to handle basic functionalities for a customer relationship management solution. It includes customer account management, multiple address management and chatbot backend support.
This project is not a comprehensive solution for customer relationship management solution. Rather it provides the basic skeleton, which could serve as a template for further development. 
### Features

- Customer Account Management: Create, update, and delete customers.
- Multiple Address creation for the same customer.
- Customer Address Management: Create, update, and delete, fetch addressess.
- STOMP Websocket based chatting.

### Tech Stack
- Java 21
- Spring Boot 3.4.1
- Spring Data JPA (Hibernate)
- Postgresql (Database)
- REST APIs
- STOMP Websocket

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Tanjemul/customer-relationship-management.git

2. Navigate to the project directory: cd \CRM

3. Configure the database:
Update application.yaml with your database credentials.
4. Build and run the project:
    ```bash
   ./mvn clean
   ./mvn dependency:tree
   ./mvn spring-boot:run

## API Documentation

The complete API documentation is available [here](https://documenter.getpostman.com/view/25076611/2sAYQWHsKd).

You can explore all endpoints, parameters, and responses interactively.

     
