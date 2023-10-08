# moviecrud.app
Simple movie database crud 

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Maven
- Docker
- Docker Compose
- A PostgreSQL client (optional)

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/sample-spring-boot-project.git
   cd moviecrud.app
Set up your development environment, if necessary.

## Install project dependencies:

    ```sh
    mvn clean install

## API Documentation
The API in this project is documented using Swagger. Once the application is up you can access the Swagger UI to explore and test the API at the following URL:
[http://localhost:8080/swagger-ui/index.html]


## Database
The project connects to a PostgreSQL database using Docker Compose. The database is automatically provisioned when running the application with Docker Compose.

Test data is provided on the import.sql file in the resources folder.

## Configuration
The project can be configured using the application.properties file.

## Build and Run
To build and run the project, use the following commands:

    ```sh
    mvn spring-boot:run


## License
This project is licensed under the MIT License - see the LICENSE file for details.
