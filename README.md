# Banking System Transaction Management Application

## Overview
This is a simple banking system application developed using Java 21 and Spring Boot. It allows users to manage financial transactions without user authentication. All data is stored in memory.

## Features
- Create, delete, modify, and list transactions.
- RESTful API design.
- Caching using Spring Cache.
- Unit and stress testing.
- Containerization with Docker.


## Dependencies
- Spring Boot Starter Web: Used for building web applications, including RESTful services.
- Spring Boot Starter Actuator: Provides production-ready features to help you monitor and manage your application.
- Spring Boot Starter Cache: Provides caching support.
- Spring Boot Starter Test: Used for testing purposes.


## Setup
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run` to start the application.


## API Endpoints
- `GET /transactions`: Get all transactions.
- `POST /transactions`: Create a new transaction. Use query parameters `description` and `amount`.
- `DELETE /transactions/{id}`: Delete a transaction by ID.
- `PUT /transactions/{id}`: Modify a transaction by ID. Use query parameters `description` and `amount`.


## Testing
- Unit tests are located in the `src/test/java` directory.
- Use `mvn test` to run unit tests.
- Use tools like JMeter or Gatling for performance and stress testing.


## Containerization
- A `Dockerfile` is provided to containerize the application.
- Use `docker build -t banking .` to build the Docker image.
- Use `docker run -p 8080:8080 banking` to run the container.


## Further Improvements
- Add more validation and error handling.
- Implement more sophisticated caching strategies.
- Improve system performance.
