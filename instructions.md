# AI Agent Instructions for AayuSkiNet

This document provides guidance for AI agents interacting with the AayuSkiNet e-commerce solution.

## 1. High-Level Overview

AayuSkiNet is a full-stack e-commerce application. The primary technology stack consists of:

-   **Backend:** .NET (C#) using an ASP.NET Core Web API.
-   **Frontend:** Angular (TypeScript) with Tailwind CSS.
-   **Database:** Relational database managed by Entity Framework Core.
-   **Containerization:** Docker is used for environment setup.
-   **Alternative API:** A secondary, less-developed Java-based API exists in the `java-api` directory.

The solution follows a clean, N-tier architecture.

## 2. Project Structure

-   `AayuSkiNet.sln`: The main Visual Studio solution file for the .NET projects.
-   `API/`: The ASP.NET Core Web API project. This is the presentation layer for the backend, handling HTTP requests, DTOs, and controllers.
-   `Core/`: The domain layer. It contains business logic, entities, and interfaces. **This project should not depend on any other project in the solution.**
-   `Infrastructure/`: The persistence and services layer. It contains data access logic (Entity Framework Core repositories, `StoreContext`), database migrations, and implementations for interfaces defined in `Core`.
-   `client/`: The Angular frontend application.
-   `java-api/`: A separate Maven-based Java API. Treat this as a secondary or alternative implementation.
-   `docker-compose.yml`: Defines the services to run the full application stack.

## 3. Backend (.NET) Development

The backend follows Clean/Onion Architecture principles.

-   **API Layer (`API/`)**: Exposes endpoints. Controllers should be lean and delegate business logic to services.
-   **Domain Layer (`Core/`)**: Contains the core business models (`Entities`) and contracts (`Interfaces`).
-   **Infrastructure Layer (`Infrastructure/`)**: Implements the data access and other external concerns.
    -   **Repository Pattern**: Data access is abstracted via `IGenericRepository` and `IUnitOfWork`.
    -   **Specification Pattern**: Used to build dynamic queries (`Specifications/`).
    -   **Entity Framework Core**: The ORM used. Migrations are located in `Infrastructure/Migrations`.

### Common Backend Tasks

-   **Building the solution:**
    ```shell
    dotnet build
    ```
-   **Running the API:**
    ```shell
    dotnet run --project API
    ```
-   **Adding a Database Migration:**
    1.  Ensure your working directory is `Infrastructure/`.
    2.  Run the command:
    ```shell
    dotnet ef migrations add <MigrationName> --startup-project ../API
    ```
-   **Applying Database Migrations:**
    ```shell
    dotnet ef database update --startup-project API
    ```

## 4. Frontend (Angular) Development

The frontend is a standard Angular CLI project.

-   **Styling**: The project uses SCSS (`styles.scss`) and Tailwind CSS (`tailwind.config.js`).
-   **State Management**: Analyze existing services in `src/app/core` and `src/app/features` to understand state management patterns before adding new features.
-   **API Interaction**: Services that communicate with the backend are typically found within feature modules. The API base URL is configured in the environment files (`src/environments/`).

### Common Frontend Tasks

-   **Installing Dependencies:**
    ```shell
    cd client
    npm install
    ```
-   **Running the Development Server:**
    ```shell
    cd client
    ng serve
    ```
-   **Building for Production:**
    ```shell
    cd client
    ng build
    ```

## 5. Running the Full Stack with Docker

The most reliable way to run the entire application is by using Docker Compose.

```shell
docker-compose up --build
```

This will build the required images and start containers for the API and the client, respecting the dependencies between them.

## 6. General Coding Conventions

-   **Adhere to Existing Patterns**: Before adding or modifying code, analyze the surrounding files to understand and replicate the established coding style, naming conventions, and architectural patterns.
-   **.NET**: Follow standard C# and ASP.NET Core conventions. Use the implemented Repository and Specification patterns for data access.
-   **Angular**: Follow standard TypeScript and Angular conventions. Create components, services, and modules as is idiomatic for the existing structure.
-   **Commits**: Write clear and concise commit messages that explain the "why" behind the change.
