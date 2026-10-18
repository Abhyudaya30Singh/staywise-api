# Staywise API

A Spring Boot REST API for hotel discovery and management, guest profiles, room inventory, bookings, Stripe Checkout, and dynamic pricing. This repository contains the backend only; it does not include a web or mobile frontend.

## Tech stack

- Java 21
- Spring Boot, Spring Web, Spring Security, Spring Data JPA
- PostgreSQL 14 or later
- JWT authentication
- Stripe Checkout and webhooks
- Maven

## Prerequisites

- JDK 21
- PostgreSQL
- Stripe test-mode API credentials for payment flows

## Run locally

Create a PostgreSQL database (the default URL expects a database named `staywise`), then configure the required environment variables. Do not put real credentials in source files or commit a local `.env` file.

```sh
export DATABASE_URL='jdbc:postgresql://localhost:5432/staywise'
export DB_USERNAME='postgres'
export DB_PASSWORD='your-local-database-password'
export JWT_SECRET_KEY="$(openssl rand -hex 32)"
export STRIPE_SECRET_KEY='your-Stripe-test-secret-key'
export STRIPE_WEBHOOK_SECRET='your-Stripe-webhook-signing-secret'

./mvnw spring-boot:run
```

The JWT secret must contain at least 32 bytes. The sample command generates a new local value; changing it invalidates tokens signed with the previous value. Use Stripe test-mode credentials only for local development, and keep them in a private secret store.

For local webhook testing, use the Stripe CLI to forward events to:

```text
http://localhost:8080/api/v1/webhook/payment
```

Set `STRIPE_WEBHOOK_SECRET` to the signing secret printed by the Stripe CLI for that listener. The default API context path is `/api/v1`.

## API documentation

When the application is running, open [Swagger UI](http://localhost:8080/api/v1/swagger-ui/index.html). OpenAPI JSON is available at `/api/v1/v3/api-docs`.

## Tests

Tests connect to a separate, disposable PostgreSQL database named `staywise_test` by default. Create that database and configure `TEST_DB_USERNAME`, `TEST_DB_PASSWORD`, and, if needed, `TEST_DATABASE_URL` for your local PostgreSQL instance before running the suite. The test configuration uses Hibernate `create-drop`; it drops and recreates schema objects in that test database, so never point it at a database containing data you need.

Run the automated test suite with:

```sh
./mvnw test
```

Tests use test-only Stripe/JWT values and do not require real Stripe credentials.

## Configuration

| Environment variable | Purpose | Required |
| --- | --- | --- |
| `DATABASE_URL` | JDBC URL for PostgreSQL | No; defaults to local `staywise` database |
| `DB_USERNAME` | PostgreSQL username | No; defaults to `postgres` |
| `DB_PASSWORD` | PostgreSQL password | No; defaults to empty |
| `JWT_SECRET_KEY` | HMAC key used to sign JWTs (at least 32 bytes) | Yes |
| `STRIPE_SECRET_KEY` | Stripe API secret key | Yes |
| `STRIPE_WEBHOOK_SECRET` | Stripe webhook signing secret | Yes |
| `FRONTEND_URL` | Allowed frontend origin for CORS | No; defaults to `http://localhost:8080` |
| `JPA_SHOW_SQL` | Enable SQL statement logging | No; defaults to `false` |
| `JPA_FORMAT_SQL` | Format SQL statement logging | No; defaults to `false` |

