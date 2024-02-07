# School CRUD Project

## Table of Contents
- [About](#about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)

## About
This project is a pet store application to manage school related data including students information such as student names, courses, exams and scores. The application follows a layered architecture comprising Entity classes, Data Access Objects (DAO), Service layer, and Controllers for handling HTTP requests.

## Technologies Used
- Java
- Spring Framework
- Jakarta Persistence (JPA)
- JAVA RESTful API (REST)
- Jakarta Bean Validation
- Lombok

## Project Structure
The project follows a typical structure for a Jakarta EE application:

- **Entity**: Contains POJO (Plain Old Java Object) classes representing entities in the application, such as student personal informations, courses, exams, and scores.
- **DAO**: Data Access Objects responsible for interacting with the database. These classes typically contain methods for CRUD (Create, Read, Update, Delete) operations.
- **Service**: Service layer classes that implement business logic and orchestrate interactions between DAOs and controllers.
- **Controller**: Controllers handle incoming HTTP requests and delegate processing to the appropriate service methods. They also handle data validation and transformation.

## Getting Started
To run the application locally, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd schoolSystemCRUD`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Use the [Advanced Rest Client](https://install.advancedrestclient.com/) or any third party program to use the endpoints
