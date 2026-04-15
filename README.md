# 📚 Digital Library Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-green?style=for-the-badge&logo=springboot)
![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-brown?style=for-the-badge)
![JDBC](https://img.shields.io/badge/JDBC-Database%20Connectivity-darkblue?style=for-the-badge)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Auth-green?style=for-the-badge&logo=springsecurity)
![Redis](https://img.shields.io/badge/Redis-Caching-red?style=for-the-badge&logo=redis)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
![REST API](https://img.shields.io/badge/REST-API-lightgrey?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

## 🚀 Overview

A full-stack **Digital Library System** built using Spring Boot that enables secure user management, book handling, and transaction processing with role-based access control.

---

## 🚀 Features

### 🔐 Authentication & Security
- Implemented Spring Security for authentication and authorization  
- Used BCrypt password encoding for secure password storage  
- Designed role-based access control (RBAC) with ADMIN and STUDENT roles  
- Secured APIs using HTTP Basic Authentication  

### 👨‍🎓 Student Management
- Register new students using public API  
- Fetch student details by ID  
- Retrieve logged-in student details using Security Context  

### 👨‍💼 Admin Management
- Admins can create and manage other admin accounts  
- Restricted admin APIs to ADMIN role only  

### 📚 Book Management
- Add and manage books (Admin only)  
- Fetch all books  
- Search books by name and genre  

### 🔄 Transaction Management
- Issue and return books  
- Maintain transaction records  
- View transaction history with filters

### ⚡ Performance Optimization
- Integrated **Redis caching** to improve response time and reduce database load  

---

## 🛠️ Tech Stack

- Backend: Spring Boot, Spring Security  
- Database: MySQL  
- ORM: Hibernate, JDBC  
- Caching: Redis  
- API: RESTful APIs  
- Tools: Lombok, Maven  

---

## 🧩 Architecture


Controller → Service → Repository → Database


---

## ⚙️ Setup & Installation

### 📥 Clone Repository
```bash
git clone https://github.com/KArtickCode/digial-library.git
cd digial-library
```

### 🗄️ Configure Database
```bash
Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### ▶️ Run Application
```bash
mvn clean install
mvn spring-boot:run
```
---

## 🔗 API Endpoints

### 👨‍🎓 Student
- `POST /students`  
- `GET /students/{id}`  
- `GET /students/details`  

### 👨‍💼 Admin
- `POST /admin`  

### 📚 Books
- `POST /books`  
- `GET /books/all`  
- `GET /books/find-by-name?name=`  
- `GET /books/find-by-genre?genre=`  

### 🔄 Transactions
- `POST /transactions/issue`  
- `POST /transactions/return`  
- `GET /transactions/all`  
- `GET /transactions`  

---

## 🔐 Security

- Spring Security with UserDetailsService  
- BCrypt password encryption  
- Role-based access (ADMIN / STUDENT)  
- SecurityContext for logged-in user  

---

## 🚀 Future Enhancements

- JWT authentication  
- Cloud deployment  
- Notification system  
