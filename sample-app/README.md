# Sample App - Customer Management

`customer-app` is a Spring Boot sample that uses the OpenAPI contract from `customer-api` to generate API interfaces and model classes.

## Modules

- `customer-api` holds the OpenAPI contract in [`customer.yml`](./customer-api/src/main/resources/openapi/customer.yml)
- `customer-app` contains the Spring Boot application, persistence layer, and controller implementation

## What is generated

The OpenAPI generator in `customer-app` generates only:

- API interfaces
- model classes

The app provides its own controller and business logic separately.

## Project Layout

```text
sample-app/
  customer-api/
    src/main/resources/openapi/customer.yml
  customer-app/
    src/main/java/
      com/learnings/projects/customer/api/
      com/learnings/projects/customer/app/
    src/main/resources/
      application.yaml
      application-dev.yaml
      application-prod.yaml
```

## API Summary

- `GET /customers` returns a plain array of customers
- `POST /customers` creates a customer
- `GET /customers/{id}` retrieves one customer
- `PUT /customers/{id}` updates one customer
- `DELETE /customers/{id}` deletes one customer

## Important Behavior

- `externalId` is treated as an idempotency key during create
- if a customer already exists with the same `externalId`, the existing customer is returned instead of creating a duplicate row

## Local Development

The app uses profile-based config:

- `dev` profile uses H2 and loads `.env-dev`
- `prod` profile is prepared for PostgreSQL

### Dev env file

Create a local `.env-dev` file in `customer-app` using the example file as a template.

Typical values:

```properties
DB_URL=jdbc:h2:file:./data/customer-db-dev
DB_USERNAME=sa
DB_PASSWORD=
```

## Build and Run

From `sample-app/customer-app`:

```bash
mvn clean test
mvn spring-boot:run
```

## Sample Requests

The file [`customer-api-curls.txt`](./customer-app/customer-api-curls.txt) contains sample curl requests that can be imported directly into Postman.

## Notes

- Regenerate sources after changing [`customer.yml`](./customer-api/src/main/resources/openapi/customer.yml)
- The generator config is intentionally kept minimal so only interfaces and models are emitted
- The application layer owns the controller, service, mapper, repository, and entity classes
