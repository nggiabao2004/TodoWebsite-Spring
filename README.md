# Todo Website - Spring Boot & Vanilla UI 🚀

Một dự án quản lý công việc (Todo List) hiện đại được phát triển bằng **Spring Boot** (Java) kết hợp giao diện **Vanilla HTML/CSS/JS** sành điệu theo phong cách thiết kế **Glassmorphism** và nền Gradient mượt mà. Đồ án luyện tập giúp nắm bắt luồng Backend API và xác thực.

## ✨ Tính Năng Nổi Bật

- **Tài khoản cá nhân (Authentication)**: Đăng ký & Đăng nhập phân quyền theo mỗi cá nhân sử dụng Session Cookie bảo mật.
- **RESTful API hoàn chỉnh**: Quản lý Create, Read, Update, Delete (CRUD).
- **Phân tách Dữ liệu (Isolation)**: Dữ liệu (Todo) hoàn toàn riêng biệt giữa các User, bảo đảm an toàn trên 1 hệ thống Database.
- **Single Page Application (SPA)**: Các thao tác được Javascript xử lý trên Frontend qua `fetch()`, không hề load lại trang (Seamless User Experience).
- **Giao diện Premium**: Dark mode sành điệu, hiệu ứng thả bóng mờ, cùng Gradient Mesh đổi màu thú vị.

## 🛠️ Nhóm Công Nghệ Sử Dụng

### Backend
- **Java 17+** (Dự án có thể chạy trên Java phiên bản mới)
- **Spring Boot 3.x+**
- **Spring Data JPA & Hibernate**
- **Spring Security** (Session-based Auth & BCrypt)
- **MySQL Database**
- **Maven** dành cho quản lý dependency.

### Frontend
- **HTML5 & CSS3** (Vanilla CSS)
- **Vanilla Javascript** (ES6+)
- **Google Fonts** (Inter Typography)
- **FontAwesome** (Icons)

## 🎯 Hướng Dẫn Cài Đặt và Khởi Chạy

### 1. Chuẩn bị Cơ Sở Dữ Liệu
Hãy cài đặt **MySQL Server**.
Phần thiết lập Database đã được cấu hình trong `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?useSSL=false
spring.datasource.username=(your-username)
spring.datasource.password=(your-password)
```
**Hãy chắc chắn tạo một Schema mới tên là `todo_db` trong MySQL của bạn.** Hibernate sẽ làm nhiệm vụ còn lại (tự động tạo bảng `users` và `todos`).

### 2. Thực thi lệnh Build và Chạy
Sử dụng công cụ `mvnw` (Maven Wrapper) tích hợp sẵn trong thư mục dự án:
Mở Terminal/PowerShell tại thư mục gốc:

```powershell
# Cho Windows
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run

# Cho Linux / macOS
./mvnw clean compile
./mvnw spring-boot:run
```

### 3. Trải nghiệm!
Sau khi khởi động không báo lỗi, mở trình duyệt truy cập:
👉 **[http://localhost:8080/](http://localhost:8080/)**

- Hãy điền tài khoản để sign-up (Mật khẩu và Username phải dài trên 6 ký tự).
- Chọn **Login** bằng thông tin mới đăng ký.
- Quản lý Todo vô hạn thời gian của bạn!

## 📜 Giấy phép
Mã nguồn mở miễn phí học tập.
Đã sử dụng Antigravity (Vibe Coding)
