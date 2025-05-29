# Patient Microservice

This is a simple Patient Management microservice built with Spring Boot and MongoDB. It supports full CRUD operations via a RESTful API and is containerized using Docker and Docker Compose.

## Features

- Add a patient
- Get all patients
- Get a single patient by ID
- Update a patient
- Delete a patient
- Unit and Integration Testing
- Dockerized Service & Database
- GitLab CI Pipeline (Build + Test stages)

---

## Technologies Used

- Java 17+
- Spring Boot
- MongoDB
- Docker & Docker Compose
- GitLab CI/CD
- JUnit & Mockito

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone url
cd patient-service
```

### 2. Run Locally with Maven

Make sure MongoDB is running locally on port 27017.

```bash

Edit
mvn clean install
mvn spring-boot:run
```

### Running with Docker Compose

Ensure Docker and Docker Compose are installed.

#### Build & Run

```bash
docker-compose up --build
```

### Running Tests

With Maven (Locally)

```bash
mvn test
```

### GitLab CI/CD

The .gitlab-ci.yml pipeline includes:

Maven build and test

Docker build with Dockerfile and Dockerfile.test

Test report artifact upload (surefire-reports)
