# Spring Redis - Item Management
This is a sample project to demonstrate the integration of Spring Boot with Redis for item management. The project provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on items, storing them both in a database and a Redis cache to improve read performance.

# Prerequisites
Before getting started, make sure you have the following installed in your development environment:

* Java JDK 17
* Maven
* Redis
* Docker
  
# Installation and Execution
1. Clone the repository:
```bash
git clone https://github.com/seu-usuario/spring-redis.git
```
Or download and extract the project manually.

2. Navigate to the project directory:
```bash
cd spring-redis
```
3. Run the project using Maven:
```bash
mvn spring-boot:run
```
The application will start and be accessible at http://localhost:8080.

# Endpoints
The application provides the following endpoints:

* GET /api/items: Returns all stored items.
* GET /api/items/{id}: Returns a specific item by its ID.
* POST /api/items: Creates a new item.
* PUT /api/items/{id}: Updates an existing item.
* DELETE /api/items/{id}: Deletes an item by its ID.

# Configuration
The application's settings can be found in the application.properties file. Make sure to configure the properties related to Redis and the database according to your environment.
```application.yml
spring:
  application:
    name: spring-redis
  redis:
    host: localhost
    port: 6379
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: ""
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
```

# Contributing
If you wish to contribute to this project, follow these steps:

1. Fork the project.
2. Create a branch for your feature: git checkout -b feature-new-feature.
3. Make your changes and commit them: git commit -m 'Add a new feature'.
4. Push to the branch: git push origin feature-new-feature.
5. Submit a pull request.
