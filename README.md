# microservicios-futfem-competitions

`microservicios-futfem-competitions` provides the competition catalog for the Tikitakas Women Football platform. It exposes the CRUD operations and persistence layer for competition data and integrates with the rest of the microservice ecosystem through Eureka, the gateway, Docker, and Swagger/OpenAPI.

The service is implemented with Java 21, Spring Boot, Spring Data JPA, MySQL, Springdoc OpenAPI, and Maven Wrapper. It follows the shared patterns defined in `microservicios-common`, which helps keep controllers and services consistent across the backend. In `v0.1.0`, this repository also includes gateway-aware Swagger configuration so its API documentation works correctly when accessed through the `zuul` gateway.

Typical local execution:

```bash
./mvnw spring-boot:run
```

Main runtime dependencies:

- MySQL database
- Eureka discovery server
- Zuul / Spring Cloud Gateway for external routing

In Docker deployments, this service connects internally to MySQL using the `mysql` hostname on the Docker network, while external consumers normally use it through the gateway route:

- `/api/futfem/competitions/**`

This repository is a good reference implementation for the rest of the domain services, because it already includes the current CI, Docker, Swagger, and deployment conventions used across the platform.
