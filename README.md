<p align="center">
  <img src="assets/header.svg" alt="Library System - Java MVC" width="100%">
</p>

<p align="center">
  <img alt="Java 21" src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
  <img alt="SQLite" src="https://img.shields.io/badge/SQLite-JDBC-003B57?style=for-the-badge&logo=sqlite&logoColor=white">
  <img alt="JUnit 5" src="https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white">
  <img alt="Docker" src="https://img.shields.io/badge/Docker-multi--stage-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</p>

<p align="center">
  <img alt="Tests" src="https://img.shields.io/badge/tests-30%20passing-success?style=flat-square&logo=checkmarx&logoColor=white">
  <img alt="Architecture" src="https://img.shields.io/badge/architecture-MVC-blueviolet?style=flat-square">
  <img alt="Database" src="https://img.shields.io/badge/storage-persistent-0ea5e9?style=flat-square">
  <img alt="License" src="https://img.shields.io/badge/license-MIT-yellow?style=flat-square">
</p>

<p align="center">
  <a href="#overview">Overview</a> &nbsp;·&nbsp;
  <a href="#features">Features</a> &nbsp;·&nbsp;
  <a href="#quick-start">Quick start</a> &nbsp;·&nbsp;
  <a href="#architecture">Architecture</a> &nbsp;·&nbsp;
  <a href="#testing">Testing</a> &nbsp;·&nbsp;
  <a href="#docker">Docker</a> &nbsp;·&nbsp;
  <a href="#design-decisions">Design decisions</a> &nbsp;·&nbsp;
  <a href="#roadmap">Roadmap</a>
</p>

---

## Overview

Library System is a console application for managing a book collection, its users and lending operations. It is written in Java 21 with a strict **Model-View-Controller** separation, persists data in **SQLite** through plain **JDBC**, and ships as a multi-stage **Docker** image that runs the full test suite during the build.

The goal was to build a small but complete application structured the way a production service would be: clear layers, SQL isolated from business logic, automated tests wired into the build, and a reproducible runtime.

<p align="center">
  <img src="https://skillicons.dev/icons?i=java,maven,sqlite,docker,git,github&theme=dark" alt="Tech stack">
</p>

---

## Features

<p align="center">
  <img src="assets/features.svg" alt="Library, search and sorting, users, persistence" width="100%">
</p>

---

## Quick start

### With Docker

```bash
git clone https://github.com/MIhajloS07/LibrarySystem-Java.git
cd LibrarySystem-Java

docker build -t library-system .
docker run --rm -it library-system
```

The build runs the tests first. If any test fails, no image is produced.

### With Maven

Requires **JDK 21** and **Apache Maven**.

```bash
mvn test       # run the test suite
mvn package    # run tests and build the JAR into target/
```

---

## Architecture

The application has three layers plus a dedicated persistence tier. The view never touches the database, and SQL never leaks into business logic. The animation below shows a request travelling to the database and the result coming back.

<p align="center">
  <img src="assets/architecture.svg" alt="MVC architecture and request flow" width="100%">
</p>

### Layer responsibilities

| Layer | Classes | Responsibility |
| --- | --- | --- |
| **Model** | `Book`, `User`, `Library` | Domain entities and their state |
| **View** | `MainUI`, `MainView` | Menu, input, output. Delegates everything else to controllers |
| **Controller** | `LibraryController`, `UserController` | Business logic |
| **Persistence** | `BookDbController`, `DbConnectionController` | SQL and connection handling, kept apart from business logic |

Controller contracts are defined as interfaces, which keeps layers loosely coupled and straightforward to test in isolation.

### Domain model

```mermaid
classDiagram
    direction LR
    class Library
    class Book
    class User

    Library "1" o-- "*" Book : holds
    Library "1" o-- "*" User : registers
    User "1" --> "*" Book : borrows
```

### Lending a book

```mermaid
sequenceDiagram
    autonumber
    actor U as User
    participant V as View
    participant C as LibraryController
    participant P as BookDbController
    participant D as SQLite

    U->>V: Choose "lend book"
    V->>C: Lend request
    C->>C: Apply lending rules
    C->>P: Persist new state
    P->>D: SQL via JDBC
    D-->>P: Result
    P-->>C: Done
    C-->>V: Outcome
    V-->>U: Confirmation
```

---

## Data persistence

SQLite was chosen deliberately: it is a real relational database with full SQL support, but needs no server, which keeps the project trivial to run locally and inside a container.

The database stores books, users, lending operations and book availability. All access goes through JDBC and is confined to the persistence controllers.

<p align="center">
  <img src="https://github.com/user-attachments/assets/5aed7261-ab9f-4058-98fa-5f86a6499e62" alt="SQLite database schema" width="80%">
</p>

Searching and sorting use the **Java Stream API** instead of hand-written loops, which keeps filtering by author or year and ordering by title or year short and readable.

---

## Testing

Tests are written with **JUnit 5** and run automatically as part of the Maven lifecycle.

<p align="center">
  <img src="assets/tests.svg" alt="30 of 30 tests passing" width="100%">
</p>

| Area | Covered |
| --- | --- |
| Model | Entity behaviour |
| Controllers | Business operations |
| Library | Lending and returning flow |
| Database | Persistence operations |

```bash
mvn test
```

---

## Docker

The Dockerfile uses a multi-stage build so the final image contains only what is needed to run the application.

<p align="center">
  <img src="assets/docker.svg" alt="Multi-stage Docker build" width="100%">
</p>

| Stage | Base image | Purpose |
| --- | --- | --- |
| Build | `maven:3.9-eclipse-temurin-21` | Compile, run tests, package |
| Runtime | `eclipse-temurin:21-jre` | Run the packaged JAR on a JRE only |

---

## Project structure

```text
LibrarySystemMVC/
├── src/
│   ├── main/java/
│   │   ├── controller/
│   │   │   ├── BookDbController.java
│   │   │   ├── DbConnectionController.java
│   │   │   ├── LibraryController.java
│   │   │   ├── UserController.java
│   │   │   └── (interfaces)
│   │   ├── model/
│   │   │   ├── Book.java
│   │   │   ├── User.java
│   │   │   └── Library.java
│   │   └── view/
│   │       ├── MainUI.java
│   │       └── MainView.java
│   └── test/java/
│       ├── model/
│       └── controller/
├── assets/
├── pom.xml
├── Dockerfile
├── .dockerignore
└── README.md
```

---

## Design decisions

| Decision | Reason |
| --- | --- |
| **SQL isolated in persistence controllers** | The rest of the code does not depend on how data is stored. Switching databases means changing one layer. |
| **Interfaces for controller contracts** | Layers depend on abstractions, which keeps coupling low and tests simple. |
| **Thin view** | The view reads input and prints results. No business rules live there. |
| **Tests gate the Docker build** | An image can never be produced from failing code. |
| **Multi-stage image** | Maven and the JDK stay in the build stage. The runtime image carries only a JRE and the JAR. |
| **SQLite over a database server** | Real SQL and JDBC practice without operational overhead. |

---

## Screenshots

<table>
  <tr>
    <td><img width="200" src="https://github.com/user-attachments/assets/89eaa3d5-16e3-4744-a005-170663137dfd" alt="Library System"></td>
    <td><img width="421" src="https://github.com/user-attachments/assets/20c47a09-5035-4488-96d5-aacfc0391151" alt="Library System menu"></td>
    <td><img width="241" src="https://github.com/user-attachments/assets/57769431-a6df-4a21-91a8-4c13747f9d08" alt="Java"></td>
  </tr>
</table>

---

## Roadmap

- [x] MVC architecture with interface-based controllers
- [x] SQLite persistence over JDBC
- [x] JUnit 5 test suite (30 tests)
- [x] Multi-stage Docker build with tests in the pipeline
- [ ] Docker volume for persistent SQLite storage
- [ ] Input validation and improved CLI experience
- [ ] Additional integration tests
- [ ] CI pipeline with GitHub Actions
- [ ] REST API layer
- [ ] Migration path to PostgreSQL or MySQL
- [ ] GUI client

---

## License

Released under the [MIT License](LICENSE).

<p align="center">
  <img src="assets/footer.svg" alt="" width="100%">
</p>

<p align="center">
  <sub>Built by <a href="https://github.com/MIhajloS07">MihajloS07</a></sub>
</p>
