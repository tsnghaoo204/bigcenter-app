# EduCenter Management System

A monolythic project to manage an educational center, including user authentication, scheduling, and access control. Built with Spring Boot, PostgreSQL, AWS S3 to store media file and AWS Cognito for secure and scalable user management.

---

## 🚀 Features

- 🔐 **Custom Authentication** via AWS Cognito
- 👥 **Role-Based Access Control** (Student, Teacher, Admin)
- 📅 **Class & Schedule Management**
- 📦 **PostgreSQL** integration
- ⚙️ **CRUD Generation** with JPA Buddy
- ☁️ **AWS Lambda** to auto-assign Cognito group on signup
- ✅ **JWT Validation** via Spring Security Resource Server
- 📸 **AWS S3** to store users image to cloud

---

## 📚 Technologies

| Tech            | Purpose                              |
|-----------------|--------------------------------------|
| Spring Boot     | Backend Framework                    |
| AWS Cognito     | Authentication & User Pool           |
| PostgreSQL      | Relational Database                  |
| Spring Security | Role-based Access via JWT            |
| JPA Buddy       | Generate JPA CRUD & Entity Mapping   |
| AWS Lambda      | Auto group assignment on sign-up     |
| AWS S3          | Store image to cloud                 |
| Docker          | Docker implementation                |

---

## Project Structure
### /client: UI Login, Sign Up, Confirm Email, Teacher Dashboard, Student Dashboard, Admin Dashboard by reacr-admin
### /server: Back-end include configurations, services and REST controllers

---

## ⚙️ Setup Instructions

### Clone the Repository
```bash
https://github.com/tsnghaoo204/bigcenter-app.git
cd bigcenter-app
```
## In Java IDE (IDEA Intellij, Eclipse,..)
### 1. Configure Environment
Update application.properties:
```
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JWT Resource Server Configuration (User Pool issuer)
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://cognito-idp.${AWS_REGION}.amazonaws.com/${COGNITO_USER_POOL_ID}
COGNITO_CLIENT_ID=${COGNITO_CLIENT_ID}
AWS_REGION=${AWS_REGION}
AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
AWS_SECRET_KEY=${AWS_SECRET_KEY}
AWS_BUCKET_NAME=${AWS_BUCKET_NAME}
```
### 2. Configure docker-compose.yml
```
version: '3.8'

services:
  app:
    build: .
    container_name: springboot_app
    restart: always
    ports:
      - "8080:8080"
    environment:
      /If using local PostgreSQL database
      SPRING_DATASOURCE_URL: jdbc:postgresql://host.docker.internal:${DB_PORT}/${DB_NAME}
      SPRING_DATASOURCE_USERNAME: ${DB_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
    env_file:
      - .env
```
### 3. Build & Run
#### Spring Boot Application
```
./mvnw clean install
./mvnw spring-boot:run
```
#### Docker
```
docker compose up --build
```
## In Front-end IDE (VS Code)
### 1. Open /client folder
### 2. Install project libraries
```
cd /client
npm install
```
### 3. Run
```
npm start
```

## Testing API
### Using Swagger: 
http://localhost:8080/swagger-ui/index.html#/



