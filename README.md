## Prerequisites
- Java 17+
- Maven 3.9+
- Docker & Docker Compose
- Optional: IntelliJ IDEA or any Java IDE

## Overview 1
This project implements the following:
- Programming & Algorithm
- Database & SQL
- Web application with REST API for student scores and reports


## Access applications on:
- Student Service: http://localhost:8080/api/students
- Report Service: http://localhost:8081/api/reports
- API Gateway Swagger UI: http://localhost:8989/swagger-ui.html


## Application Endpoints
Student Service (http://localhost:8081/api/students)
- POST /api/students – Create student scores
- GET /api/students/{id} – Retrieve student by ID
- GET /api/students – List all students

Report Service (http://localhost:8082/api/reports)
- GET /api/reports – List all student reports (supports pagination & filtering)
- GET /api/reports/{id} – Retrieve a single student report

## Database
This project uses Flyway for schema migrations located in `db/migration`.

## How to Run Web application under ..\TechiePlanetAssessment\application_development
*  Routes to specific microservice (e.g: `cd report-service, cd student-service, cd api-gateway`)

1. Ensure Docker and Docker Compose are installed: 
  - cd deployment/docker-compose  
  - docker compose up -d 
  - docker ps

2. Application runs on http://localhost:<`port`>
3. Swagger API documentation available at: http://localhost:<`port`>/swagger-ui.html
4. Set JAVA_HOME(PowerShell)
   - $env:JAVA_HOME = "C:\Users\User\.jdks\jdk-17.0.12"
   - $env:JAVA_HOME = "C:\Program Files\Java\jdk-17.0.12"
5. Compile without tests: mvn clean -DskipTests 
6. Package application: mvn clean package
7. Run Spring Boot app: mvn spring-boot:run
   oR run               java -jar target/your-app.jar

## Testing
- Unit and Integration tests: `mvn test` or `mvn clean package`
- Database migrations handled by Flyway





## Notes
- All endpoints and data validation implemented
- Pagination and filtering included for reports
- Scores are integers 0-100




