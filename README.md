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

### 5. Test Reports

You can view the backend test reports here:

[Surefire HTML Report](target/surefire-reports/html/index.html)

Note: Sweet module related tests involve security and dynamic user contexts, which made unit testing with mocks complex. In future iterations, these tests can be fully automated using integration tests with an in-memory database or proper Spring Security context setup.

## 🖼️ Screenshots

Replace these with actual screenshots from your app
- Welcome Page
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-43-45 Sweet shop" src="https://github.com/user-attachments/assets/8baf33f3-9f60-42f1-b837-13c147d50b2c" />

- Sweet Dashboard (Normal user view)
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-00-04 Sweet shop" src="https://github.com/user-attachments/assets/8b53b595-8cae-4fa0-a1ce-a0eb824c3b79" />

- Sweet Dashboard (Admin user view)
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-10-18 Sweet shop" src="https://github.com/user-attachments/assets/6d67488b-1944-4835-821b-85c1ad383401" />
  
- Sweet purchase
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-22-11 Sweet shop" src="https://github.com/user-attachments/assets/733ca61c-ee58-49d3-a9da-6cef088d05c2" />

- Sweet Restock
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-11-15 Sweet shop" src="https://github.com/user-attachments/assets/823eb85a-cc0f-483b-aa49-462b7a60fe65" />

- Sign up (error)
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-13-15 Sweet shop" src="https://github.com/user-attachments/assets/048cea82-5f3e-46cd-a50f-5459d49279c4" />

- Sign up(Success)
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-16-33 Sweet shop" src="https://github.com/user-attachments/assets/b3024798-95bc-49d5-ae68-30ac750f8cec" />

- Update Sweet form
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-11-31 Sweet shop" src="https://github.com/user-attachments/assets/e07ba45c-5e9f-492d-9773-8e3ad8ce15c7" />

- Create Sweet form
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-11-46 Sweet shop" src="https://github.com/user-attachments/assets/10882754-9a8c-4861-98b5-6797b554a07a" />

- Filters
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-01-07 Sweet shop" src="https://github.com/user-attachments/assets/b6f2ee19-87b5-47e3-9605-b20dee6d1c5a" />

- Not found page
  
  <img width="1920" height="878" alt="Screenshot 2025-09-20 at 20-22-31 Sweet shop" src="https://github.com/user-attachments/assets/7c96633a-6516-4718-ae14-0eb54f8731c8" />



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
