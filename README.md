# User Profile Post API

A Spring Boot REST API for managing **Users**, **Profiles**, and **Posts** with clean architecture and advanced Spring Data JPA usage. This project is designed both as a real-world CRUD application and as a learning reference for JPA best practices.

---

## 🚀 Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Lombok**
* **Gradle**

---

## ✨ Key Features

### Core Functionality

* Create, update, delete **Users**
* Create and manage **Profiles** (One-to-One with User)
* Create and manage **Posts** (One-to-Many with Profile)
* Fetch data by ID and fetch all records

### Advanced JPA & Spring Data Features

* **Pagination & Sorting** using `Pageable`
* **Derived Query Methods**
* **JPQL Queries** with joins
* **Native SQL Queries**
* **Projections** (Interface-based & DTO-based)
* **Dynamic Filtering** using **JPA Specifications**
* **N+1 Query Problem** demonstration and solutions (`JOIN FETCH`, `@EntityGraph`)
* **Auditing** with automatic `createdAt` and `updatedAt`

---

## 🧩 Domain Model

* **User** ↔ **Profile** : One-to-One
* **Profile** ↔ **Post** : One-to-Many

This structure represents a realistic social-style data model.

---

## 🛠 How to Run

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd user-profile-post-api
   ```

2. **Configure Database**
   Update `application.properties` (or `application.yml`) with your PostgreSQL credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

   or run the main class from your IDE.

---

## 📮 API Documentation

* A complete **Postman collection** is provided
* Location: `/postman` folder
* Import it into Postman to test all endpoints easily

---

## 📚 Learning Highlights

This project demonstrates how to:

* Design clean entity relationships
* Use DTOs, Mappers, Services, and Controllers properly
* Apply Spring Data JPA features in a real project
* Write scalable and maintainable backend code

---

## 📌 Branching Strategy

* `main` → stable version
* `feature/profile-advanced-jpa` → advanced JPA features (pagination, specs, projections, auditing)

---

## 🤝 Contribution

This project is primarily for learning purposes, but suggestions and improvements are welcome.

---

## 👤 Author

**Mohammad Hasibul Hasan**

---

⭐ If you find this project helpful for learning Spring Boot & JPA, feel free to star the repository.
