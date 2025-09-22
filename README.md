# 🎓 EduCenter Management System

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![AWS Cognito](https://img.shields.io/badge/AWS-Cognito-orange)](https://aws.amazon.com/cognito/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

A **monolithic** web application to manage an educational center — covering **user authentication**, **class scheduling**, and **role-based access control**.  
Built with **Spring Boot**, **PostgreSQL**, **AWS Cognito**, and **AWS S3** for cloud storage.

---

## ✨ Features

- 🔐 **Custom Authentication** via AWS Cognito  
- 👥 **Role-Based Access Control** — Student, Teacher, Admin  
- 📅 **Class & Schedule Management**  
- 📦 **PostgreSQL** database integration  
- ⚙️ **JPA Buddy** for fast CRUD & entity mapping  
- ☁️ **AWS Lambda** — Auto-assign Cognito group on sign-up  
- ✅ **JWT Validation** using Spring Security Resource Server  
- 📸 **AWS S3** for cloud image storage  

---

## 🛠 Tech Stack

| Technology      | Purpose                                   |
|-----------------|-------------------------------------------|
| **Spring Boot** | Backend Framework                         |
| **AWS Cognito** | Authentication & User Pool Management     |
| **PostgreSQL**  | Relational Database                       |
| **Spring Security** | JWT-based role access                 |
| **JPA Buddy**   | CRUD generation & Entity Mapping          |
| **AWS Lambda**  | Auto group assignment on sign-up          |
| **AWS S3**      | Cloud image storage                       |
| **Docker**      | Containerization                          |

---

## 📂 Project Structure

```
bigcenter-app/
│── client/   # React Admin UI: Login, Sign Up, Dashboards (Student, Teacher, Admin)
│── server/   # Spring Boot backend: Configurations, Services, REST Controllers
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/tsnghaoo204/bigcenter-app.git
cd bigcenter-app
```

---

### 2️⃣ Backend Setup (Spring Boot)

**Edit `application.properties`**:
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# AWS Cognito
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://cognito-idp.${AWS_REGION}.amazonaws.com/${COGNITO_USER_POOL_ID}
COGNITO_CLIENT_ID=${COGNITO_CLIENT_ID}

# AWS Keys & S3
AWS_REGION=${AWS_REGION}
AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
AWS_SECRET_KEY=${AWS_SECRET_KEY}
AWS_BUCKET_NAME=${AWS_BUCKET_NAME}
```

**Docker Compose** (`docker-compose.yml`):
```yaml
version: '3.8'

services:
  app:
    build: .
    container_name: springboot_app
    restart: always
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://host.docker.internal:${DB_PORT}/${DB_NAME}
      SPRING_DATASOURCE_USERNAME: ${DB_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
    env_file:
      - .env
```

**Run with Maven**:
```bash
./mvnw clean install
./mvnw spring-boot:run
```

**Run with Docker**:
```bash
docker compose up --build
```

---

### 3️⃣ Frontend Setup (React)

```bash
cd client
npm install
npm start
```

---

## 📜 API Documentation

Once the backend is running, access Swagger UI:  
👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

