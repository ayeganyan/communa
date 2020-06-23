# Communa

Communa is backend REST API used to make communities, book parking lots, etc

### Getting started with Docker
```sh
$ docker-compose up --build
```
Note: Some basic data are prefilled in database

### Features
 - Resident, community, parking lot management APIs
 - Residents can join/leave communities, acquire/release parking lots 
 - Communities can have several parking lots 
 - Basic authentication for residents 
 - Swagger for documentation
 - Separate DB/initial data for tests and production

### Tech
 - Java 8
 - Spring Boot, JPA
 - Postgres
 - Docker

### TODO
 - Implement role and user based authorization
 - Add more docs
 - Add more tests

