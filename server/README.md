# EduCenter Management System

A modern multi-tenant platform to manage educational centers, user authentication, scheduling, and access control. Built with Spring Boot, PostgreSQL, and AWS Cognito for secure and scalable user management.

---

## 🚀 Features

- 🔐 **Custom Authentication** via AWS Cognito (custom UI)
- 👥 **Role-Based Access Control** (Student, Instructor, Admin)
- 🏢 **Multi-Tenant Support** for multiple education centers
- 📅 **Class & Schedule Management**
- 📦 **PostgreSQL** integration
- ⚙️ **CRUD Generation** with JPA Buddy
- ☁️ **AWS Lambda** to auto-assign Cognito group on signup
- ✅ **JWT Validation** via Spring Security Resource Server
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
git clone https://github.com/your-org/edu-center-system.git
cd edu-center-system
```
### 2. Configure Environment
Update application.yml or application.properties:
```bash
yaml
Copy
Edit
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
### 3. Build & Run

```bash
./mvnw clean install
./mvnw spring-boot:run
```
