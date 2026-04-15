<h1 align="center">📚 Digital Library Management System</h1>

<p align="center">
A scalable and secure <b>Spring Boot-based Digital Library System</b> with authentication, role-based access control, Redis caching, and RESTful APIs.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-Backend-green?style=for-the-badge&logo=springboot"/>
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql"/>
  <img src="https://img.shields.io/badge/Spring%20Security-Auth-green?style=for-the-badge&logo=springsecurity"/>
  <img src="https://img.shields.io/badge/Redis-Caching-red?style=for-the-badge&logo=redis"/>
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven"/>
</p>

---

## ✨ Overview

The **Digital Library Management System** is a backend project built using Spring Boot that allows secure management of books, students, and transactions with role-based access control.

---

## 🚀 Key Features

### 🔐 Security & Authentication
- Spring Security-based authentication system  
- Role-Based Access Control (ADMIN / STUDENT)  
- BCrypt password encryption  
- Secure REST APIs  

### 👨‍🎓 Student Module
- Register new students  
- View student details  
- Access personal data securely  

### 📚 Book Management
- Add / update / delete books (Admin only)  
- Search books by name & genre  
- Fetch all books  

### 🔄 Transaction System
- Issue and return books  
- Maintain transaction history  
- Secure access to records  

### ⚡ Performance
- Redis caching for faster response time  
- Optimized database queries  

---

## 🧠 Tech Stack

- ☕ Java 17  
- 🌱 Spring Boot  
- 🔐 Spring Security  
- 🗄️ MySQL  
- ⚡ Redis  
- 🔧 Hibernate / JDBC  
- 📦 Maven  

---

## 🏗️ Architecture

Controller → Service → Repository → Database


---

## 📡 API Endpoints

### 👨‍🎓 Student
- `POST /students`
- `GET /students/{id}`
- `GET /students/details`

### 📚 Books
- `POST /books`
- `GET /books/all`
- `GET /books/find-by-name`
- `GET /books/find-by-genre`

### 🔄 Transactions
- `POST /transactions/issue`
- `POST /transactions/return`
- `GET /transactions/all`

---

## 🚀 Why This Project Stands Out

✔ Real-world backend system  
✔ Secure authentication & authorization  
✔ Redis performance optimization  
✔ Clean layered architecture  
✔ RESTful API design  

---

## 👨‍💻 Author

- GitHub: [KArtickCode](https://github.com/KArtickCode)

---

## ⭐ Support

If you like this project, don’t forget to give it a ⭐ on GitHub!
