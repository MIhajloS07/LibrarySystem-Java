# Library System (Java + SQLite)

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#) [![Java Version](https://img.shields.io/badge/Java-8%2B-blue.svg)](#)  ![Maven](https://img.shields.io/badge/Maven-Build-success) ![JUnit5](https://img.shields.io/badge/Tests-JUnit5-green)


**Library System** is a production‑minded, console‑based Java application that implements a clean **Model‑View‑Controller (MVC)** architecture, persistent storage with **SQLite**, automated unit tests with **JUnit 5**, and a Maven build lifecycle. The codebase is organized, documented, and designed to be used as a professional portfolio piece or a technical demonstration.

---

## Table of Contents
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Data Model and Persistence](#data-model-and-persistence)
- [Java Stream API Usage](#java-stream-api-usage)
- [Project Structure](#project-structure)
- [Testing and Quality](#testing-and-quality)
- [Getting Started](#getting-started)
- [Advantages and Integration Opportunities](#advantages-and-integration-opportunities)
- [License](#license)

---

## Key Features
- **CRUD operations** for books: add, remove, update, list
- **Lending workflow**: lend, return, and track borrowed items per user
- **Search and sort** capabilities (title, author, year)
- **Persistent storage** using a single SQLite database file (runtime DB excluded from VCS)
- **Unit tests** covering models and controllers (JUnit 5)
- **Maven** for build, run and test automation
- **Streamlined code** using Java Stream API for collection processing and queries

---

## Architecture
**Model View Controller (MVC)** separation keeps concerns isolated and code maintainable.

### Model
- Domain objects such as **Book**, **User**, and **Library** encapsulate state and small, testable behaviors.
- Models are simple POJOs with clear validation and equality semantics.

### Controller
- **Business controllers** (e.g., `LibraryController`, `UserController`) implement domain rules (lend, return, search, sort).
- **Persistence controllers** (e.g., `BookDbController`, `DbConnectionController`) encapsulate all SQLite access and map `ResultSet` rows to model objects.
- All controllers expose interfaces to enable mocking and easy substitution in tests.

### View
- Thin CLI (`mainView`) handles input/output and delegates all logic to controllers. The view contains no business logic.

### Design Principles
- **Separation of Concerns**: Isolated layers for easier testability and extensibility.
- **Dependency Inversion**: Persistence layers can be swapped (mock, file, RDBMS) without changing business logic.
- **Single Responsibility**: One clear purpose per class and clean contracts defined via interfaces.

---

## Data Model and Persistence
- **Single Source of Truth**: Persistent state is stored in a single SQLite file. The persistence layer is responsible for schema creation, migrations, and mapping between relational rows and domain objects.
- **Data Model Highlights**: `Book` includes identifiers and metadata (title, author, year, status). `User` tracks borrower identity and borrowed items. Relationships are simple and normalized to keep queries predictable.
- **Persistence Design Principles**:
  - **Encapsulation**: SQL and JDBC code live only in the DB controllers.
  - **Testability**: Persistence controllers accept connection parameters so tests can use an isolated, temporary test database.
  - **Resilience**: Prepared statements and transaction boundaries are used to avoid SQL injection and ensure atomic operations for lend/return flows.
- **Operational Notes**: The runtime database file is excluded from version control so each environment can initialize its own data; the codebase includes clear hooks for seeding test data.

---

## Java Stream API Usage
Collection processing, filtering, sorting, and mapping in business controllers leverage the **Java Stream API** for concise, readable, and efficient code. Examples of Stream usage in the codebase include:
- Filtering search results by author or year.
- Sorting book lists by title or year using `stream().sorted(...)`.
- Mapping database result sets to domain objects and collecting them into lists with `Collectors.toList()`.

---

## Project Structure
```text
src/
 ├── main/
 │   └── java/
 │       ├── model/          # Book, User, Library
 │       ├── controller/     # Business and persistence controllers
 │       └── view/           # CLI entry point and menu
 └── test/
     └── java/
         ├── model/          # Model unit tests
         └── controller/     # Controller and DB tests
```

---

## Testing and Quality

### Unit Testing Strategy
Tests are organized to mirror the MVC separation:
- **Model Tests**: Validate constructors, invariants, equality, and small business methods.
- **Controller Tests**: Validate business flows (lend, return, search, sort) using in‑memory controllers or mocked persistence.
- **Integration Tests**: Exercise `BookDbController` against an isolated SQLite test file with setup/teardown to keep tests deterministic.

### Quality Practices
- **Deterministic Tests**: Implemented with explicit setup and teardown routines.
- **Comprehensive Assertions**: Tests check both return values and side effects (database state).
- **Code Style & Documentation**: Public classes and methods include Javadoc; code follows consistent naming and formatting to meet professional standards.
- **CI Readiness**: The project is structured to be run in CI (Maven `mvn test`) with a temporary test database created dynamically during the build.

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)** 8 or higher
- **Apache Maven**

### Installation
<!-- TODO: Replace with specific repository details if different -->
```bash
# Clone the repository
git clone <repository-url>
cd library-system

# Build the project and download dependencies
mvn clean install
```

### Running Tests
To execute the automated test suite:
```bash
mvn test
```

### Running the Application
<!-- TODO: Replace with the actual main class path if different -->
```bash
mvn exec:java -Dexec.mainClass="view.MainView"
```

---

## Advantages and Integration Opportunities
- **Professional Organization**: Demonstrates industry-standard clean architecture and separation of concerns.
- **Testable and Maintainable**: Interfaces and isolated persistence make unit and integration testing straightforward.
- **Extensible**: Easily add a **GUI, REST API**, or alternative persistence engines (PostgreSQL, MySQL) without altering business logic.
- **Streamlined Data Processing**: Extensive **Java Stream API** usage improves readability and performance for collection operations.
- **CI-Friendly**: Maven lifecycle and deterministic tests make the project ready for continuous integration pipelines.
- **Easy DB Inspection**: The single **SQLite** file enables quick manual verification with DB tools during development and testing.
- **Production Path**: Package as a JAR for simple deployments; for multi‑user scenarios, migrate persistence to a **client‑server RDBMS**.

---
## Screenshots
<img width="200" height="241" alt="image" src="https://github.com/user-attachments/assets/89eaa3d5-16e3-4744-a005-170663137dfd" style="margin-right:20px;"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<img width="421" height="241" alt="image" src="https://github.com/user-attachments/assets/20c47a09-5035-4488-96d5-aacfc0391151" />
&nbsp;&nbsp;&nbsp;&nbsp;
<img width="241" height="241" alt="icons8-java-logo-480" src="https://github.com/user-attachments/assets/57769431-a6df-4a21-91a8-4c13747f9d08" />
<br>
<img width="634" height="125" alt="sqlscheme" src="https://github.com/user-attachments/assets/5aed7261-ab9f-4058-98fa-5f86a6499e62" />

## License
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](#)
<br>
This project is licensed under the MIT License.
