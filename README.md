# Education Center Management System

Hệ thống quản lý trung tâm đào tạo giúp quản lý học viên, giáo viên, lớp học, khóa học, học phí và lịch học.

## 🚀 Công nghệ sử dụng

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL

### Frontend

* React
* Axios
* React Router

### Database

* MySQL

---

# 📌 Chức năng chính

## 1. Quản lý học viên

* Thêm học viên
* Cập nhật thông tin
* Xóa học viên
* Tìm kiếm học viên
* Xem danh sách lớp đang học

## 2. Quản lý giáo viên

* Thêm giáo viên
* Cập nhật thông tin giáo viên
* Xem lịch dạy

## 3. Quản lý khóa học

* Tạo khóa học
* Chỉnh sửa khóa học
* Xem danh sách khóa học
* Quản lý học phí

## 4. Quản lý lớp học

* Tạo lớp
* Phân công giáo viên
* Thêm học viên vào lớp
* Quản lý phòng học
* Quản lý lịch học

## 5. Quản lý điểm danh

* Điểm danh theo buổi học
* Theo dõi chuyên cần

## 6. Quản lý học phí

* Thanh toán học phí
* Lịch sử thanh toán
* Theo dõi học phí còn lại

---

# 🗂️ Cấu trúc Project

## Backend

```
src/main/java/com/center

controller
service
repository
entity
dto
config
security
```

## Frontend

```
src

components
pages
services
api
router
```

---

# 🗄️ Database

Các bảng chính trong hệ thống:

```
roles
users
students
teachers
courses
classes
class_students
rooms
schedules
attendance
payments
announcements
```

---

# ⚙️ Cài đặt và chạy project

## 1. Clone project

```
git clone https://github.com/your-username/education-center-management.git
```

---

## 2. Cấu hình database

Tạo database:

```
education_center
```

Cấu hình trong `application.yml` hoặc `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/education_center
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3. Chạy Backend

```
mvn spring-boot:run
```

Backend sẽ chạy tại:

```
http://localhost:8080
```

---

## 4. Chạy Frontend

```
cd frontend
npm install
npm start
```

Frontend sẽ chạy tại:

```
http://localhost:3000
```

---

# 📡 API ví dụ

### Student API

```
GET /api/students
POST /api/students
PUT /api/students/{id}
DELETE /api/students/{id}
```

---

### Course API

```
GET /api/courses
POST /api/courses
PUT /api/courses/{id}
DELETE /api/courses/{id}
```

---

# 🔐 Authentication

Hệ thống sử dụng:

* JWT Authentication
* Role-based Authorization

Roles:

```
ADMIN
STAFF
TEACHER
```

---

# 📊 Các tính năng sẽ phát triển thêm

* Dashboard thống kê
* Thông báo cho học viên
* Quản lý bài tập
* Lớp học online
* Mobile app cho học viên

---

# 👨‍💻 Tác giả

Project được xây dựng cho mục đích:

* Học tập
* Đồ án
* Portfolio

---

# 📜 License

MIT License
