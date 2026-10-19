# 🏋️ SmartFit — Gym & Fitness Management Platform

**SmartFit** is a gym and fitness management platform designed to help members track their fitness journey, manage personal fitness profiles, access workout programs, and connect with coaches.

The backend is built with **Java 21, Spring Boot, Spring Security, JWT, and PostgreSQL**. The current development phase focuses on authentication, user management, and personal fitness profiles.

> **Development status:** Backend foundations are implemented. Authentication and fitness profile endpoints require end-to-end verification. The login endpoint currently has a reported `401 Unauthorized` issue under investigation.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Main Objectives](#-main-objectives)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation and Setup](#-installation-and-setup)
- [Environment Configuration](#-environment-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Authentication Flow](#-authentication-flow)
- [Database Design](#-database-design)
- [Testing with Postman](#-testing-with-postman)
- [Current Development Status](#-current-development-status)
- [Roadmap](#-roadmap)
- [Troubleshooting](#-troubleshooting)
- [Security Notes](#-security-notes)
- [Author](#-author)

---

## 🎯 Project Overview

SmartFit aims to provide a centralized platform for managing gym activities and personal fitness progress.

The platform is intended to support three main roles:

- **Member:** Manage personal information, track workouts and body measurements, and explore training and nutrition programs.
- **Coach:** Create training programs, assign workouts to clients, and monitor authorized client progress.
- **Administrator:** Manage users, coaches, platform content, and administrative operations.

The current implementation focuses on establishing a secure and maintainable backend before developing the remaining modules and frontend.

## 🎯 Main Objectives

- Build a secure REST API for fitness management.
- Implement user registration and login.
- Protect passwords using BCrypt hashing.
- Authenticate users with JSON Web Tokens (JWT).
- Store application data in PostgreSQL.
- Manage personal fitness profiles.
- Organize the backend using a layered architecture.
- Prepare the system for future workout, nutrition, and coaching modules.

---

## ✨ Features

### Implemented backend foundations

- [x] Spring Boot project initialization.
- [x] PostgreSQL database configuration.
- [x] User entity and repository.
- [x] User registration request validation.
- [x] Email normalization and duplicate-email checks.
- [x] Password hashing using BCrypt.
- [x] User response DTO to avoid exposing password data.
- [x] Login and JWT authentication components.
- [x] Spring Security configuration.
- [x] Stateless authentication architecture.
- [x] Fitness goal and activity level enums.
- [x] User fitness profile entity and repository.
- [x] Profile request and response DTOs.
- [x] Fitness profile service and controller.
- [x] Profile creation/update and retrieval endpoints.

### Verification still required

- [ ] Resolve and verify the login `401 Unauthorized` issue.
- [ ] Complete the registration-to-login-to-profile integration test.
- [ ] Verify invalid, expired, and malformed JWT handling.
- [ ] Complete validation and duplicate-email tests.

### Planned features

- [ ] Exercise library with descriptions and videos.
- [ ] Training programs and workout logging.
- [ ] Weight and strength progress tracking.
- [ ] Coach-client relationships and program assignments.
- [ ] Nutrition and macronutrient calculator.
- [ ] Group chat and progress sharing.
- [ ] Experience points, ranks, and achievements.
- [ ] Gym memberships and subscriptions.
- [ ] Angular frontend.
- [ ] Automated tests and production deployment.

---

## 🛠 Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | REST API endpoints |
| Spring Security | Authentication and request authorization |
| Spring Data JPA | Database access and persistence |
| Hibernate | ORM implementation |
| PostgreSQL 18 | Relational database |
| BCrypt | Password hashing |
| JJWT 0.12.6 | JWT generation and validation |
| Jakarta Bean Validation | Request data validation |
| Maven Wrapper | Dependency management and build |
| Postman | API testing |
| IntelliJ IDEA | Development environment |
| DBeaver | Database inspection |
| Angular | Planned frontend |

---

## 🏗 Architecture

SmartFit follows a layered architecture to separate HTTP handling, business logic, database operations, and security.

```text
Client (Postman / Future Angular Frontend)
                    |
                    v
             REST Controllers
                    |
                    v
              Service Layer
                    |
                    v
             Repository Layer
                    |
                    v
              PostgreSQL DB
```

### Main layers

**1. Controller**

Receives HTTP requests, validates incoming data through the application flow, and returns HTTP responses.

**2. Service**

Contains business logic, including registration, authentication, and fitness profile operations.

**3. Repository**

Uses Spring Data JPA to retrieve and persist entities.

**4. Entity**

Represents persistent application data, such as users and fitness profiles.

**5. DTO (Data Transfer Object)**

Defines the request and response formats exposed through the API, helping prevent sensitive entity fields from being returned to clients.

**6. Security and authentication**

Spring Security manages access rules. JWT components generate and validate tokens and authenticate protected requests.

---

## 📁 Project Structure

The following tree represents the documented backend organization. Minor filenames or package locations may differ in the actual source code.

```text
smartfit/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── smartfit/
        │           └── smartfit/
        │               ├── SmartfitApplication.java
        │               │
        │               ├── config/
        │               │   └── SecurityConfig.java
        │               │
        │               ├── auth/
        │               │   ├── AuthController.java
        │               │   ├── AuthService.java
        │               │   ├── AuthResponse.java
        │               │   ├── JwtService.java
        │               │   └── JwtAuthenticationFilter.java
        │               │
        │               ├── user/
        │               │   ├── controller/
        │               │   │   └── UserController.java
        │               │   ├── dto/
        │               │   │   ├── RegisterRequest.java
        │               │   │   ├── LoginRequest.java
        │               │   │   └── UserResponse.java
        │               │   ├── entity/
        │               │   │   └── User.java
        │               │   ├── repository/
        │               │   │   └── UserRepository.java
        │               │   └── service/
        │               │       └── UserService.java
        │               │
        │               └── profile/
        │                   ├── controller/
        │                   │   └── UserProfileController.java
        │                   ├── dto/
        │                   │   ├── ProfileRequest.java
        │                   │   └── ProfileResponse.java
        │                   ├── entity/
        │                   │   └── UserProfile.java
        │                   ├── enums/
        │                   │   ├── FitnessGoal.java
        │                   │   └── ActivityLevel.java
        │                   ├── repository/
        │                   │   └── UserProfileRepository.java
        │                   └── service/
        │                       └── UserProfileService.java
        │
        └── resources/
            └── application.properties
```

---

## ⚙️ Prerequisites

Install and configure the following:

- Java Development Kit (JDK) 21.
- PostgreSQL 18 or a compatible PostgreSQL installation.
- IntelliJ IDEA or another Java IDE.
- Postman for API testing.
- Git for version control.

The Maven Wrapper is included in the project, so a separate global Maven installation is not required.

Check your Java installation:

```powershell
java -version
```

Verify that Java 21 is being used.

---

## 🚀 Installation and Setup

### 1. Clone the repository

Replace the placeholder URL with your actual Git repository URL.

```powershell
git clone <YOUR_REPOSITORY_URL>
cd smartfit
```

If the project is already on your computer:

```powershell
cd D:\smartfit
```

### 2. Create the PostgreSQL database

Open PostgreSQL using DBeaver or `psql` and create the database:

```sql
CREATE DATABASE smartfit_db;
```

Make sure your PostgreSQL service is running and that your database credentials are correct.

### 3. Configure the application

Open the project's `application.properties` or `application.yml` file and configure the PostgreSQL connection.

Example for `application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/smartfit_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

This is an example configuration, not a replacement for your actual project settings. Adapt it to the existing configuration and avoid defining the same properties twice.

For local development, supply `DB_PASSWORD` through your environment or IDE run configuration. Review your Hibernate schema-generation settings before deploying to production.

### 4. Configure the JWT secret

Set the `JWT_SECRET` environment variable in IntelliJ IDEA:

1. Open **Run → Edit Configurations**.
2. Select the SmartFit Spring Boot run configuration.
3. Locate **Environment variables**.
4. Add a strong, randomly generated secret in the format expected by your `JwtService`.
5. Save and restart the application.

Do not commit the secret to Git or publish it in your README.

### 5. Build the project

From the project root, run:

```powershell
.\mvnw.cmd clean package
```

To compile without running tests:

```powershell
.\mvnw.cmd clean package -DskipTests
```

Skipping tests is useful for checking compilation only; it does not verify that the application behaves correctly.

---

## ▶️ Running the Application

Start the backend with the Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, run `SmartfitApplication.java` from IntelliJ IDEA.

The base API URL is:

```text
http://localhost:8080
```

When startup succeeds, check the console for the Spring Boot startup message and any database connection errors.

---

## 🔌 API Documentation

All examples below assume the documented routes are configured in the application. Check your actual controller mappings if a route differs.

### 1. User registration

**Endpoint**

```http
POST /api/users/register
```

**Request body**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "ChooseAValidPassword123"
}
```

Use **No Auth** in Postman and send the body as raw JSON.

Expected response: `201 Created`.

The response should contain the user's public information, such as ID, first name, last name, and email, without the password.

> The registration route shown here is an example consistent with the documented user controller. Confirm its exact mapping in `UserController.java`.

### 2. User login

**Endpoint**

```http
POST /api/auth/login
```

**Request body**

```json
{
  "email": "john.doe@example.com",
  "password": "ChooseAValidPassword123"
}
```

Use **No Auth** in Postman.

Expected response when authentication succeeds: `200 OK`, containing the user information and JWT token according to `AuthResponse`.

Example response shape:

```json
{
  "user": {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "token": "<JWT_TOKEN>"
}
```

This is an illustrative response shape. The actual JSON field names depend on your DTO.

**Current issue:** Login has returned `401 Unauthorized` during testing. Successful login has not yet been verified end to end.

### 3. Create or update a fitness profile

**Endpoint**

```http
PUT /api/profile/me
```

**Headers**

```http
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Request body**

```json
{
  "dateOfBirth": "2005-05-20",
  "heightCm": 179,
  "currentWeightKg": 62.3,
  "fitnessGoal": "MUSCLE_GAIN",
  "activityLevel": "MODERATELY_ACTIVE"
}
```

These are example values. Use values appropriate for your test account and ensure that enum names match the actual Java enums.

Expected response: `200 OK` with a profile response.

### 4. Retrieve the current user's profile

**Endpoint**

```http
GET /api/profile/me
```

**Headers**

```http
Authorization: Bearer <JWT_TOKEN>
```

Expected responses:

- `200 OK` if the authenticated user has a profile.
- `404 Not Found` if no profile exists, according to the documented service behavior.
- An authentication error if the request does not contain valid credentials.

The endpoint should return the profile associated with the authenticated user, not a profile selected using a client-supplied user ID.

### API summary

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/users/register` | Register a user; verify exact mapping |
| POST | `/api/auth/login` | Authenticate and obtain a JWT |
| PUT | `/api/profile/me` | Create or update the current user's profile |
| GET | `/api/profile/me` | Retrieve the current user's profile |

---

## 🔐 Authentication Flow

SmartFit uses JWT-based stateless authentication.

```text
1. User submits registration details
             |
             v
2. Backend validates request
             |
             v
3. Password is hashed with BCrypt
             |
             v
4. User is stored in PostgreSQL
             |
             v
5. User submits login credentials
             |
             v
6. Backend verifies email and password
             |
             v
7. JWT is generated and returned
             |
             v
8. Client sends Authorization: Bearer <token>
             |
             v
9. JWT filter validates the token
             |
             v
10. Protected endpoint processes request
```

Registration and login should be publicly accessible. Fitness profile endpoints should require valid authentication.

The backend should never store a plaintext password or return a password hash in a public user response.

---

## 🗄️ Database Design

### User

The `User` entity represents an account.

Expected responsibilities:

- Store basic user information.
- Store the hashed password.
- Support email lookup during authentication.
- Provide the account identity used by protected endpoints.

### UserProfile

The `UserProfile` entity represents personal fitness information.

Documented fields include:

| Field | Description |
|---|---|
| `dateOfBirth` | User's date of birth |
| `heightCm` | Height in centimeters |
| `currentWeightKg` | Current body weight in kilograms |
| `fitnessGoal` | Fitness objective |
| `activityLevel` | Estimated activity level |

The profile has a one-to-one relationship with the user.

### Enums

**FitnessGoal** describes the user's training objective. The documented example is `MUSCLE_GAIN`.

**ActivityLevel** describes the user's general activity level. The documented example is `MODERATELY_ACTIVE`.

Use the exact enum values defined in the source code when submitting requests.

---

## 🧪 Testing with Postman

Follow this sequence to test the backend.

1. **Start the application.** Confirm that Spring Boot and PostgreSQL initialize successfully.
2. **Register a test user.** Use a new email address and verify the response.
3. **Log in.** Send the same email and password to `/api/auth/login`.
4. **Copy the JWT locally.** Keep it private.
5. **Create a profile.** Send a `PUT` request to `/api/profile/me` with a valid Bearer token.
6. **Read the profile.** Send a `GET` request to `/api/profile/me`.
7. **Test security.** Repeat the profile request without a token and verify that access is denied.
8. **Test validation.** Submit invalid data and duplicate registration details to confirm the expected errors.

Do not share real passwords or JWT tokens in screenshots, documentation, or issue reports.

---

## 📊 Current Development Status

| Component | Status |
|---|---|
| Java and Spring Boot setup | Configured |
| PostgreSQL connection | Configured; verify at startup |
| User entity and repository | Implemented |
| Registration logic | Implemented; test verification pending |
| BCrypt password hashing | Implemented |
| Login and JWT components | Implemented; login `401` unresolved |
| Spring Security configuration | Implemented; integration verification pending |
| Fitness profile entity and DTOs | Implemented |
| Fitness profile service and controller | Implemented; end-to-end test pending |
| Exercise and workout modules | Planned |
| Nutrition module | Planned |
| Coach features | Planned |
| Angular frontend | Planned |

---

## 🐛 Troubleshooting

### Login returns `401 Unauthorized`

Check the following before changing your security configuration:

- Confirm that the request uses `POST`, not `GET` or `PUT`.
- Confirm the URL is `http://localhost:8080/api/auth/login`.
- Set Postman Authorization to **No Auth**.
- Use raw JSON and `Content-Type: application/json`.
- Confirm that the user exists and the submitted password matches the original registration password.
- Check Spring Security and application console logs at the exact time of the request.
- Confirm that the password verification uses the same BCrypt encoder configuration as registration.
- Verify that the login route is permitted in `SecurityConfig.java`.
- Determine whether the request reaches `AuthController` and `AuthService`.

A `401` without the expected application error message may originate in the security filter chain or another exception handler. The precise cause has not yet been established.

Do not disable security or permit all routes as a workaround.

### Database connection failure

- Verify that the PostgreSQL service is running.
- Confirm that `smartfit_db` exists.
- Check the database URL, username, and password.
- Review the application startup logs for connection or schema errors.

### JWT secret error

- Check that `JWT_SECRET` exists in the environment used by the running application.
- Confirm that the secret format and length meet the requirements of your JWT implementation.
- Restart the application after updating the environment variable.
- Never publish the secret in source control.

### Port 8080 is already in use

Identify and stop the other process using port 8080, or configure SmartFit to use another available port.

---

## 🔒 Security Notes

- Never commit database passwords, JWT secrets, access tokens, or real user credentials.
- Store passwords using BCrypt rather than plaintext or reversible encryption.
- Validate and normalize incoming user data.
- Use DTOs to control which fields are returned to clients.
- Require authentication for personal profile endpoints.
- Derive the current user from the authenticated security context.
- Add a database-level unique constraint for email addresses.
- Use HTTPS, secure secret management, database migrations, and production-safe logging before deployment.
- Review schema generation settings before deploying to production.

---

## 🗺️ Roadmap

### Phase 1 — Backend foundations

- [x] Initialize Spring Boot and Java 21.
- [x] Configure PostgreSQL.
- [x] Implement the user model and registration components.
- [x] Add BCrypt password hashing.
- [x] Create JWT authentication components.
- [x] Implement the fitness profile module.
- [ ] Resolve the login `401` issue.
- [ ] Verify the complete authentication and profile flow.

### Phase 2 — Improve reliability

- [ ] Add automated unit and integration tests.
- [ ] Add role-based access control for members, coaches, and administrators.
- [ ] Enforce unique email addresses at the database level.
- [ ] Improve exception handling and API validation.
- [ ] Add API documentation.

### Phase 3 — Training management

- [ ] Build exercise and training program modules.
- [ ] Record workout sessions.
- [ ] Track weight and strength progress.
- [ ] Add coach assignments and client progress views.

### Phase 4 — Nutrition and community

- [ ] Calculate calorie and macronutrient targets.
- [ ] Track food portions.
- [ ] Add group communication and progress sharing.
- [ ] Implement experience points, ranks, and achievements.

### Phase 5 — Frontend and deployment

- [ ] Build the Angular frontend.
- [ ] Configure development CORS rules.
- [ ] Connect frontend authentication and profile screens.
- [ ] Add database migrations and deployment configuration.
- [ ] Harden production security.

---

## 📚 Glossary

| Term | Meaning |
|---|---|
| API | Interface that lets applications communicate |
| REST | Architectural style for HTTP-based APIs |
| Entity | Java class mapped to database data |
| DTO | Object defining the shape of API requests or responses |
| Repository | Component responsible for database access |
| Service | Layer containing business logic |
| Controller | Layer that handles HTTP requests |
| Dependency Injection | Mechanism through which Spring supplies dependencies |
| JWT | Signed token used to carry authentication claims |
| BCrypt | Password hashing algorithm |
| JPA | Java persistence API for object-relational mapping |
| Hibernate | ORM implementation used by the application |
| CORS | Browser security mechanism controlling cross-origin requests |
| HTTP 401 | Unauthorized request or failed authentication |
| HTTP 404 | Requested resource not found |
| HTTP 409 | Conflict, commonly used for duplicate resources |

---

## 👨‍💻 Author

**Aweb Aouichi**

SmartFit — Gym and Fitness Management Platform

**Backend:** Java 21 · Spring Boot · Spring Security · JWT · PostgreSQL

---

*This README reflects the documented project state as of 19 October 2026. Verify endpoint mappings, configuration properties, and test results against the actual source code before treating examples as definitive.*
