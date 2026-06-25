# Price Service API

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Quick Start](#quick-start)
- [Build and Run](#build-and-run)
- [API Endpoint](#api-endpoint)
- [Business Rules](#business-rules)
- [Architecture](#architecture)
- [Database (H2)](#database-h2)
- [H2 Console](#h2-console)
- [Swagger / OpenAPI](#swagger--openapi)
- [Testing](#testing)
- [Design decisions](#design-decisions)
- [Author](#author)

## Overview

This project is a Spring Boot REST API that provides the applicable product price for a given:
- application date
- product identifier
- brand identifier

The service determines the correct price based on validity date ranges and priority rules.

It follows a clean architecture approach with separation between:
- domain
- application
- infrastructure (REST + persistence)

---

## Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- H2 in-memory database
- OpenAPI 3 (SpringDoc)
- MapStruct
- Lombok
- JUnit 5 + MockMvc

---

## Quick Start

Option A (recommended for local development):

```bash
./mvnw spring-boot:run
```

Option B (run packaged artifact):

```bash
./mvnw clean package
java -jar target/price-service-0.0.1-SNAPSHOT.jar
```

Open:

- API base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Build and Run

### Prerequisites

- Java 17
- Maven Wrapper (`./mvnw`) included in this repository

### 1. Generate sources and compile

```bash
./mvnw clean compile
```

This step executes `generate-sources` (OpenAPI generation) and compiles the project.
Generated files are written to:

- `target/generated-sources/openapi`

### 2. Run tests

```bash
./mvnw test
```

### 3. Package artifact

```bash
./mvnw clean package
```

Packaged artifact:

- `target/price-service-0.0.1-SNAPSHOT.jar`

### 4. Run the application

Option A: run with Spring Boot plugin

```bash
./mvnw spring-boot:run
```

Option B: run the generated JAR

```bash
java -jar target/price-service-0.0.1-SNAPSHOT.jar
```

By default, the application starts on:

- `http://localhost:8080`

---

## API Endpoint

### Find applicable price

GET /api/v1/prices

### Query parameters

| Parameter        | Type       | Required | Description                      |
|----------------|------------|----------|----------------------------------|
| applicationDate | date-time  | Yes      | Date of price application        |
| productId       | long       | Yes      | Product identifier               |
| brandId         | long       | Yes      | Brand identifier (e.g. ZARA=1)   |

---

### Example request

```bash
curl "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T16:00:00Z&productId=35455&brandId=1"
```

---

### Example response

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```

---

## Business Rules

- Prices are filtered by:
    - productId
    - brandId
    - applicationDate within validity range (startDate <= applicationDate <= endDate)
- If multiple prices match, the one with the highest priority is returned
- Priority is numeric (higher value = higher priority)

---

## Architecture

The project follows a clean architecture approach:

domain → application → infrastructure

### Domain
- Price aggregate
- Domain exceptions

### Application
- Use case: GetApplicablePriceUseCase
- Business orchestration service
- Ports (in/out)

### Infrastructure
- REST controller
- Persistence adapter (Spring Data JPA)
- MapStruct mappers
- Global exception handling

---

## Database (H2)

### Configuration

The application uses an in-memory H2 database.

Data is recreated on every application startup, so changes are not persisted between runs.

The schema and initial data are automatically loaded at startup using:

- schema.sql initialization script
- data.sql initialization script

---

### Preloaded data

The database is initialized with the dataset provided in the exercise statement.

The Brand concept is intentionally NOT modeled as a separate entity because:
- it is not required by the endpoint
- no business logic depends on it
- only the identifier is needed for filtering

Therefore, `brand_id` is stored directly as a column in the `prices` table.

---

## H2 Console

H2 console is enabled for development and debugging purposes.

### Access URL

http://localhost:8080/h2-console

### JDBC configuration

- JDBC URL: jdbc:h2:mem:pricesdb
- User: sa
- Password: (empty)

---

## Swagger / OpenAPI

Swagger UI is available for API documentation and testing.

### Access URL

http://localhost:8080/swagger-ui.html

The OpenAPI specification is generated automatically from:

/api/v1/prices

---

### Security note

This project does NOT include authentication or authorization.

Security was intentionally excluded because it is outside the scope of the technical exercise.

---

## Testing

### Types of tests

- Unit tests (application layer)
- Integration tests (REST + Spring Boot context + H2)
- Repository tests (Spring Data JPA queries)

### Run tests

```bash
./mvnw test
```

---

## Design decisions

### No Brand entity

Brand is represented only as a numeric identifier.

This avoids unnecessary complexity since:
- no brand logic exists
- no relationships are required
- the API only needs the ID

---

### H2 as runtime database

H2 is used both for:
- integration tests
- application runtime (simplified setup)

In a real production environment, this would be replaced by a persistent database (e.g. PostgreSQL).

---

## Author

David Gonzalez

Price Service - Clean Architecture implementation

Technical exercise project (2026)

