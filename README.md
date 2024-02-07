# POC_SaisieDeTemps Overview

## Backend
- **GitHub Repository:** [POC_SaisieDeTemps/backend](https://github.com/oumaima-medrare/POC_SaisieDeTemps)
- **Language:** Java 18
- **Framework:** SpringBoot

### Project Structure
- `src/main/java`: Implementation of project classes
- `src/main/resources`: Configuration (`application.properties`)

### Packages
- **Domain:** Entity classes and relationships
- **Repository:** JpaRepository interfaces for each domain
- **Service:** Service interfaces for each domain
- **ServiceImpl:** Service implementation classes with business logic
- **Controller:** REST controllers
- **DTO:** Data Transfer Objects
- **Config:** Spring Security configuration
- **Security:** JWT token provider
- **Exporter:** PDF timetable generation
- **Utils:** Database data loading and role assignment

### API Documentation
- [Swagger Documentation](http://localhost:3000/swagger-ui.html)

### Installation
1. Import as Maven project
2. Modify `application.properties` for correct database configuration
3. Run `SaisiedetempsApplication.java`

## Frontend
- **GitHub Repository:** [POC_SaisieDeTemps/client](https://github.com/oumaima-medrare/POC_SaisieDeTemps)
- **Typescript Framework:** Angular
