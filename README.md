# Communa

Communa is backend REST API used to making communities, book parking lots and etc

### Getting started with Docker
```sh
$ mvn clean install
$ docker-compose up --build
```
Note: Some basic data is prefilled in database

# Features
 - CRUD operations on residents, communities, parking lots
 - Residents can join/leave communities, acquire/release parking lots
 - Communities can have several parking lots
 - Basic authentication for residents
 - Swagger for documentation

### Tech
 - Java 8
 - Spring Boot, JPA
 - Postgres
 - Docker

# TODO
 - Implement role and user based authorization
 - Add more docs
 - Add more tests

