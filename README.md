# Library System — Java MVC

![Java](https://img.shields.io/badge/Java-21-blue)
![Maven](https://img.shields.io/badge/Maven-Build-success)
![JUnit 5](https://img.shields.io/badge/Tests-JUnit%205-green)
![SQLite](https://img.shields.io/badge/Database-SQLite-003B57)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)
![License](https://img.shields.io/badge/License-MIT-yellow)

**Library System** is a console-based Java application for managing books, users, and lending operations. The project is built around the **Model-View-Controller (MVC)** architecture and uses **SQLite** for persistent data storage, **JUnit 5** for automated testing, **Maven** for dependency and build management, and **Docker** for containerized execution.

The project demonstrates practical use of **object-oriented programming, MVC architecture, JDBC, relational database integration, automated testing, Java Stream API, Maven, and Docker**.

---

## Table of Contents

* [Features](#features)
* [Technologies](#technologies)
* [Architecture](#architecture)
* [Data Persistence](#data-persistence)
* [Testing](#testing)
* [Project Structure](#project-structure)
* [Getting Started](#getting-started)
* [Running with Maven](#running-with-maven)
* [Running with Docker](#running-with-docker)
* [Docker Architecture](#docker-architecture)
* [Screenshots](#screenshots)
* [Future Improvements](#future-improvements)
* [License](#license)

---

## Features

### Library Management

* Add books to the library
* Remove books
* List all books
* Lend books to users
* Return borrowed books
* Display borrowed books

### Search and Sorting

* Search books by author
* Search books by publication year
* Sort books by title
* Sort books by year

### User Management

* Create and manage users
* Track borrowed books
* Associate lending operations with users

### Persistence

* Persistent storage using **SQLite**
* Database access implemented using **JDBC**
* Dedicated database controllers for persistence operations

### Testing

* Automated tests using **JUnit 5**
* Model and controller testing
* Database-related testing
* Maven test lifecycle integration

### Containerization

* Dockerized application
* Multi-stage Docker build
* Maven build and tests executed during the Docker image build
* Lightweight Java 21 runtime image for the final container

---

## Technologies

| Technology          | Purpose                                      |
| ------------------- | -------------------------------------------- |
| **Java 21**         | Application development                      |
| **Maven**           | Dependency management and build automation   |
| **SQLite**          | Persistent relational database               |
| **JDBC**            | Database connectivity                        |
| **JUnit 5**         | Automated testing                            |
| **Java Stream API** | Collection filtering, sorting and processing |
| **Docker**          | Containerization and reproducible execution  |
| **Git / GitHub**    | Version control and source-code hosting      |

---

## Architecture

The application follows the **Model-View-Controller (MVC)** architectural pattern.

<img width="600" height="463" alt="image" src="https://github.com/user-attachments/assets/b67df1ef-47eb-4145-a4ea-94c03b07ff4a" />


### Model

The model layer contains the main domain objects:

* `Book`
* `User`
* `Library`

These classes represent the application's core entities and their state.

### Controller

The controller layer contains both business and persistence logic.

Examples include:

* `LibraryController`
* `UserController`
* `BookDbController`
* `DbConnectionController`

Interfaces are used for controller contracts, helping keep the application modular and easier to test.

### View

The view layer provides the console-based user interface.

The main view is responsible for:

* displaying the library menu
* reading user input
* displaying results
* delegating operations to controllers

Business logic is kept outside the view layer.

---

## Data Persistence

The application uses **SQLite** as its relational database.

Database communication is handled through **JDBC**, with database-specific operations separated into dedicated controllers.

The database stores information required for:

* books
* users
* lending operations
* book availability/status

This separation keeps SQL/database operations independent from the main business logic.

### SQLite

SQLite was chosen because it provides a lightweight relational database that does not require a separate database server.

This makes the project easy to run locally while still providing experience with:

* relational data
* SQL
* JDBC
* database connections
* CRUD operations
* persistence

---

## Java Stream API

The project uses the **Java Stream API** for collection processing.

Examples include:

* filtering books
* searching by author
* searching by year
* sorting books by title
* sorting books by publication year

Streams help keep collection-processing operations concise and readable.

---

## Testing

The project uses **JUnit 5** for automated testing.

Tests cover important application functionality including:

* model behavior
* controller operations
* library operations
* database-related functionality

The Maven build is configured to execute the test suite automatically.

The current test suite contains **30 tests**, all passing successfully.

```text
Tests run: 30
Failures: 0
Errors: 0
Skipped: 0
```

Tests can be executed with:

```bash
mvn test
```

---

## Project Structure

```text
LibrarySystemMVC/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── controller/
│   │       │   ├── BookDbController.java
│   │       │   ├── DbConnectionController.java
│   │       │   ├── LibraryController.java
│   │       │   ├── UserController.java
│   │       │   └── interfaces...
│   │       │
│   │       ├── model/
│   │       │   ├── Book.java
│   │       │   ├── User.java
│   │       │   └── Library.java
│   │       │
│   │       └── view/
│   │           ├── MainUI.java
│   │           └── MainView.java
│   │
│   └── test/
│       └── java/
│           ├── model/
│           └── controller/
│
├── pom.xml
├── Dockerfile
├── .dockerignore
└── README.md
```

---

# Getting Started

## Prerequisites

### Local development

For running the project directly on your machine:

* **JDK 21**
* **Apache Maven**
* **Git**

### Docker

For containerized execution:

* **Docker Desktop** or Docker Engine

---

## Clone the Repository

```bash
git clone https://github.com/MIhajloS07/LibrarySystem-Java.git
cd LibrarySystem-Java
```

---

# Running with Maven

Build the project:

```bash
mvn package
```

Run the tests:

```bash
mvn test
```

The packaged application is generated in the `target` directory.

---

# Running with Docker

The application includes a **multi-stage Dockerfile** that separates the build environment from the runtime environment.

### Build the Docker image

```bash
docker build -t library-system .
```

### Run the application

```bash
docker run --rm -it library-system
```

The container starts the application automatically and displays the library management menu.

---

# Docker Architecture

The Dockerfile uses two stages.

### Build Stage

The first stage uses Maven with Java 21:

```text
maven:3.9-eclipse-temurin-21
```

It:

1. Copies the Maven configuration and source code.
2. Runs the JUnit test suite.
3. Packages the application into a JAR file.

### Runtime Stage

The final stage uses:

```text
eclipse-temurin:21-jre
```

Only the packaged application JAR is copied into the runtime image.

<img width="580" height="400" alt="image" src="https://github.com/user-attachments/assets/f8ced66d-b949-424c-bf93-2e042bd16e1c" />


This approach keeps the final runtime image separate from the Maven build environment.

---

# Screenshots

## Screenshots

## Screenshots

<table>
  <tr>
    <td>
      <img width="200" height="241"
           src="https://github.com/user-attachments/assets/89eaa3d5-16e3-4744-a005-170663137dfd"
           alt="Library System">
    </td>
    <td>
      <img width="421" height="241"
           src="https://github.com/user-attachments/assets/20c47a09-5035-4488-96d5-aacfc0391151"
           alt="Library System">
    </td>
    <td>
      <img width="241" height="241"
           src="https://github.com/user-attachments/assets/57769431-a6df-4a21-91a8-4c13747f9d08"
           alt="Java">
    </td>
  </tr>
  <tr>
    <td colspan="3" align="center">
      <img width="634" height="125"
           src="https://github.com/user-attachments/assets/5aed7261-ab9f-4058-98fa-5f86a6499e62"
           alt="SQLite database schema">
    </td>
  </tr>
</table>

---

# Future Improvements

Possible future improvements include:

* Docker volume for persistent SQLite storage
* Improved CLI user experience and input validation
* Additional integration tests
* Database migration toward PostgreSQL or MySQL
* REST API layer
* GUI client
* CI/CD pipeline
* Deployment to a cloud environment

---

# License

This project is licensed under the **MIT License**.

![License](https://img.shields.io/badge/License-MIT-yellow.svg)
