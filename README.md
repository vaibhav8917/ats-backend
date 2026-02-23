# 🏢 Application Tracking System Backend (Spring Boot + JWT)

## 📌 Overview

This project is a production-grade Applicant Tracking System (ATS) backend built using:

- Spring Boot
- Spring Security (JWT Authentication)
- MySQL
- JPA / Hibernate
- Role-Based Access Control
- Multipart Resume Upload
- RESTful API Architecture

---

## 🚀 Features

### 🔐 Authentication & Security
- JWT-based Authentication
- Stateless Session Management
- Role-Based Authorization (HR, Candidate, Interviewer)
- BCrypt Password Encoding
- Logged-in User Identity Isolation

### 📋 Recruitment Workflow
- HR creates jobs
- Candidate applies
- Resume upload (file handling)
- HR schedules interview
- Interviewer submits feedback
- Final selection decision

### 🧠 Architecture
- Layered architecture (Controller → Service → Repository)
- DTO pattern
- Global Exception Handling
- Pagination & Sorting
- Validation
- Clean REST API design

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Security 6+
- MySQL
- JJWT (0.13)
- Maven

---

## 🔑 Roles

- HR
- Candidate
- Interviewer

---

## ⚙️ How to Run

1. Clone repository
2. Create MySQL database
3. Configure `application.properties`
4. Run: mvn spring-boot:run


---

## 📂 Project Structure
com.ats \
├── controller  \
├── service \
├── repository  \
├── entity \
├── dto \
├── security \
├── config \
└── exception 


---

## 📌 Author

Vaibhav More  
Email : [morev8917@gmail.com]()
