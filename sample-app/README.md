# Sample App - Customer Management

A standalone Spring Boot application demonstrating a Customer management API generated from the `customer-api` OpenAPI module.

## Overview

This sample application showcases a TMF-style Customer API where `customer-app` reads `customer-api/src/main/resources/openapi/customer.yml` and generates the API/model classes during the Maven build.

## Project Structure

```
sample-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/learnings/projects/spec/driven/sample/
│   │   │   │       ├── SampleAppApplication.java
│   │   │   │       └── customer/
│   │   │   │           ├── CustomerController.java
│   │   │   │           ├── CustomerService.java
│   │   │   │           ├── Customer.java
│   │   │   │           ├── ContactMedium.java
│   │   │   │           ├── RelatedParty.java
│   │   │   │           ├── CustomerNotFoundException.java
│   │   │   │           └── CustomerApiExceptionHandler.java
│   │   │   └── resources/
│   │   │       └── application.yaml
│   │   └── test/
│   │       └── java/
│   │           └── com/learnings/projects/spec/driven/sample/
│   │               └── SampleAppApplicationTests.java
│   └── pom.xml
├── customer-api/
│   ├── src/main/resources/openapi/customer.yml
│   └── pom.xml
└── pom.xml
```

## Dependencies

- Spring Boot 3.3.0
- Spring Web
- Spring Validation
- OpenAPI Generator for source generation

## API Endpoints

### Health Check
```bash
GET /customers/health
```

### List Customers
```bash
GET /customers
```

### Create Customer
```bash
POST /customers
Content-Type: application/json
```

### Retrieve Customer
```bash
GET /customers/{id}
```

### Update Customer
```bash
PUT /customers/{id}
Content-Type: application/json
```

### Patch Customer
```bash
PATCH /customers/{id}
Content-Type: application/json
```

### Delete Customer
```bash
DELETE /customers/{id}
```

## Running the App

### Build
```bash
cd spec-driven-parent
mvn clean install
cd ..\sample-app
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Generation Flow

The `customer-app` module runs OpenAPI Generator during `generate-sources` and emits generated interfaces and models under `target/generated-sources/openapi`.

The generated code is based on `../customer-api/src/main/resources/openapi/customer.yml`.

## Example Customer Payload

```json
{
  "name": "ACME Corporation",
  "status": "ACTIVE",
  "externalId": "ext-1001",
  "description": "Primary enterprise customer",
  "partyRole": [
    {
      "id": "party-1",
      "name": "John Doe",
      "role": "CUSTOMER"
    }
  ],
  "contactMedium": [
    {
      "type": "email",
      "preferred": true,
      "medium": "john.doe@acme.example"
    }
  ]
}
```

## Notes

- `customer-api` contains the OpenAPI contract for the customer domain.
- `customer-app` should implement the generated API interfaces or delegate beans, not hand-written duplicate models.
- If you change the contract, rerun the Maven build to regenerate the sources.
