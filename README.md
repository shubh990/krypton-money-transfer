# Krypton Bank - Money Transfer Microservice

## Overview
This microservice is designed to demonstrate a banking application for Krypton Bank, allowing money transfers between accounts. It is built using Spring Boot, Java 17, and an H2 Database for persistence. 
The microservice is also integrated with two other services to serve as the data source.
1. Account-Service to fetch Account data.
2. Core-Money-Transfer to transfer money to destination account.

## Technologies Used
1. Java 17
2. Spring Boot
3. H2 Database
4. Feign Client (for service integration)
5. Spring Data JPA (for database interaction)
6. Maven (for dependency management)

## Prerequisites
1. Java 17: Ensure that Java 17 is installed on your machine.
2. Maven: Used for building the project.

## Build and Run the Application
1. Build : 
```
mvn clean install
```
2. Run
```
mvn spring-boot:run
```

3. Runs in [localhost:8080](http://localhost:8080/api/v1/transfer)
4. H2 Console: http://localhost:8080/h2-console

### Configuration
The application is configured to use an in-memory H2 database. You can access the database console at the /h2-console endpoint. The default credentials are:
```
1. JDBC URL: jdbc:h2:mem:krypton
2. Username: krypton
3. Password: (leave it blank)
```
### Sample Request
To transfer money from one account to another, you can use the following sample JSON request:
```agsl
{
    "cif": "1234561230",
    "sourceAccount": "00176589564",
    "destinationAccount": "KRYP09UN0985156734568923",
    "amount": "10.00",
    "currency" : "USD",
    "reason": "A-1096",
    "notes": "My notes"
}
```
All fields are mandatory with valid checks.

### API call
Send a POST request to /api/v1/transfer with the above JSON payload.
```agsl
curl --location 'http://localhost:8080/api/v1/transfer' \
--header 'Content-Type: application/json' \
--data '{
    "cif": "1234561230",
    "sourceAccount": "00176589564",
    "destinationAccount": "KRYP09UN0985156734568923",
    "amount": "10.00",
    "currency" : "USD",
    "reason": "A-1096",
    "notes": "Hello"
}'
```

Sample Response Success
```agsl
{
    "status": "SUCCESS",
    "data": {
        "timestamp": "2024-09-03T22:54:07.3875194+05:30",
        "transactionNumber": "778966091",
        "transactionStatus": "SUCCESS",
        "failureReason": null
    }
}
```
Sample Response Failure
```agsl
{
    "status": "SUCCESS",
    "data": {
        "timestamp": "2024-09-03T23:31:56.5065385+05:30",
        "transactionNumber": "383639174",
        "transactionStatus": "FAILED",
        "failureReason": "INSUFFICIENT_AMOUNT"
    }
}
```