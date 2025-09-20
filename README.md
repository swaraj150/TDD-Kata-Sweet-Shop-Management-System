# TDD-Kata-Sweet-Shop-Management-System
A full-stack web application for managing and buying sweets online.  
It includes **user authentication (Sign In / Sign Up)**, **role-based access (Admin / Customer)**, and **sweet management features** such as creating, updating, deleting, filtering, and searching sweets.  

Admins can manage sweets, while customers can browse and buy them.

---

## 🚀 Features

- ✅ User authentication (Sign Up / Sign In / Logout)
- ✅ Role-based authorization (Admin / Customer)
- ✅ Admin can:
  - Create, update, and delete sweets
- ✅ Customer can:
  - Browse sweets
  - Filter sweets by name, category, and price range
- ✅ Search functionality with reset to default feed
- ✅ Responsive UI built with Chakra UI
- ✅ API communication with secure endpoints
- ✅ Not Found page & protected routes
- ✅ Modern single-page application (SPA) using React Router

---

## 🛠️ Tech Stack

### Frontend:
- React.js (SPA)
- React Router
- Chakra UI
- Axios
- Vite

### Backend:
- Spring Boot
- Spring Security (JWT authentication)
- JPA
- PostgreSQL

---

## 🏁 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/your-username/sweet-shop.git
cd sweet-shop
```

### 2. Backend Setup (Spring Boot)

Navigate to backend:
```bash
cd backend
```

Configure your database in `application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/sweet_management_db
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

Run backend:
```bash
./mvnw spring-boot:run
```
Backend will be available at: http://localhost:8080

### 3. Frontend Setup (React + Vite)

Navigate to frontend:
```bash
cd frontend
```

Install dependencies:
```bash
npm install
```

Start the app:
```bash
npm run dev
```
Frontend will be available at: http://localhost:5173





### 4. Default Admin User

Insert an admin user for testing:
- email: admin@sweets.com
- password: admin@123

---

## 🖼️ Screenshots

Replace these with actual screenshots from your app
- Welcome Page
- Sweet Dashboard
- Admin Create Sweet

---

## 🔒 Authentication Flow

- Public routes: `/`, `/signin`, `/signup`
- Private routes: `/home`, `/sweet-form`
- Admin-only: Create / Update / Delete sweets

---

## 🤖 My AI Usage

I used AI (ChatGPT) during the development of this project to:
- Discuss best packages for efficient frontend development
- Generate starter code snippets for components
- Generate test cases for backend services and controllers
- Debug issues with protected routes
- Suggest SQL seed data for sweets
- Draft this README.md file

All AI-generated code and content were reviewed, customized, and tested by me before inclusion in the final project.
