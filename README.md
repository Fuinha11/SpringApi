# SpringApi
Spring Basic API with H2 database and Active MQ

Simple implementation of a standalone Spring Boot application with ActiveMQ queues and an H2 database to manage its entities

## What this project is made of:
- Java 
- Maven 
- Spring Boot 
- H2 database
- ActiveMQ
- Lombok
- Swagger UI

## How to use it? 
1. Clone the repository
1. Navigate to directory.
1. Download sources. 
    1. mvn install
1. Run SpringapiApplication.java 
    1. mvn spring-boot:run

## Features
- Documentation with Swagger UI
    - Go to: http://localhost:8080/springapi/swagger-ui.html
    - Play around with the request
    - Enjoy 

- H2 Persistent database 
    - Go to: http://localhost:8080/springapi/h2
    - JDBC URL: jdbc:h2:file:./database-file
    - User: sa
    - Password: password

## Notes
- The requirements document is attached as "Desafio Técninco Back-End.pdf" 
- The thought process behind all choices for this application can be found at "Descrição Lógica.md"
- Some parts of this project are in Portuguese, that is to keep names accordingly to specification.
