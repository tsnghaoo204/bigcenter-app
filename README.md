# EduCenter Management System

A modern platform to manage an educational center, including user authentication, scheduling, and access control. Built with Spring Boot, PostgreSQL, and AWS Cognito for secure and scalable user management.

---

## 🚀 Features

- 🔐 **Custom Authentication** via AWS Cognito (custom UI)
- 👥 **Role-Based Access Control** (Student, Instructor, Admin)
- 📅 **Class & Schedule Management**
- 📦 **PostgreSQL** integration
- ⚙️ **CRUD Generation** with JPA Buddy
- ☁️ **AWS Lambda** to auto-assign Cognito group on signup
- ✅ **JWT Validation** via Spring Security Resource Server
- 🏫 **Single Center Mode** – designed to manage one education center only
- .........(updating)

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
| ............    | ...............................      |

---

## ⚙️ Setup Instructions

### 1. Clone the Repository
```bash
https://github.com/tsnghaoo204/bigcenter-microservice.git
cd bigcenter-microservice
```
### 2. Configure Environment
Update application.yml or application.properties:
```
aws:
  cognito:
    userPoolId: your_user_pool_id
    clientId: your_client_id
    region: your_region
    jwtJwkSetUri: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/edu_center
    username: postgres
    password: your_password
```
3. Build & Run
```
./mvnw clean install
./mvnw spring-boot:run
```
