# TodoWebsite Spring Boot API

Một hệ thống RESTful API mạnh mẽ được xây dựng bằng Spring Boot để quản lý danh sách công việc (Todo tasks). Dự án này cung cấp tính năng xác thực người dùng (kết hợp Session + JWT) và đảm bảo tính cách ly dữ liệu, nghĩa là mỗi người dùng chỉ có thể xem và quản lý danh sách công việc do chính họ tạo ra.

## 🚀 Tính năng nổi bật

* **Xác thực người dùng (User Authentication)**: Hệ thống Đăng ký và Đăng nhập an toàn sử dụng Spring Security và JSON Web Tokens (JWT).
* **Bảo mật kép (Hybrid Security)**: Hỗ trợ linh hoạt cả hai phương thức xác thực: Bearer Token (JWT) và Cookie (Session).
* **Quản lý công việc (Todo Management)**: Nhóm chức năng CRUD hoàn chỉnh (Tạo, Đọc, Cập nhật, Xóa) cho các tác vụ Todo.
* **Cách ly dữ liệu (Data Isolation)**: Mối quan hệ Một-Nhiều (One-to-Many) chặt chẽ giữa User và Todo đảm bảo người dùng chỉ được phép can thiệp vào dữ liệu của riêng mình.
* **Kiến trúc Service Layer**: Phân tách rõ ràng giữa Controller và Business Logic thông qua Interface và Implementation (Service/ServiceImpl), giúp mã nguồn dễ bảo trì và mở rộng.
* **Xử lý ngoại lệ tập trung (Centralized Exception Handling)**: Tích hợp sẵn Controller Advice giúp định dạng và trả về các thông báo lỗi (Lỗi Validation, 404 Không tìm thấy...) một cách chuẩn mực dưới định dạng JSON.

## 🛠️ Công nghệ sử dụng

* **Java** (v25)
* **Spring Boot** (Starter Web, Data JPA, Security, Validation)
* **Cơ sở dữ liệu MySQL**
* **JSON Web Token (jwt)**
* **Lombok**
* **Maven**

## ⚙️ Điều kiện tiên quyết

* Đã cài đặt và cấu hình JDK.
* Đã cài đặt Maven (hoặc bạn có thể dùng file thực thi `mvnw` đi kèm).
* Chạy một Server MySQL trên máy tính cá nhân.

## 🔧 Cài đặt & Thiết lập

1. **Chuẩn bị Cơ sở dữ liệu**
   Mở phần mềm quản lý MySQL của bạn và tạo một database mới với tên `todo_db`:
   ```sql
   CREATE DATABASE todo_db;
   ```

2. **Cấu hình Application Properties**
   Mở file `src/main/resources/application.properties` và điều chỉnh lại thông tin đăng nhập MySQL cho đúng với máy của bạn:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=mật_khẩu_của_bạn
   ```

3. **Khởi chạy Ứng dụng**
   Mở terminal tại thư mục gốc của dự án và chạy lệnh sau (sử dụng Maven wrapper):
   ```bash
   ./mvnw spring-boot:run
   ```
   *Server sẽ bắt đầu chạy ở thư mục mặc định `localhost:8080`. Hibernate sẽ tự động tạo các bảng `users` và `todos` trong MySQL giúp bạn, không cần chạy script SQL thủ công.*

## 📡 Danh sách API Endpoints

### Phân hệ Xác thực (`/api/auth`)
| Phương thức | Endpoint | Mô tả | Dữ liệu Body quy định |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Đăng ký tài khoản mới | `{ "username": "admin", "password": "123", "email": "admin@test.com" }` |
| `POST` | `/api/auth/login` | Đăng nhập và nhận chuỗi JWT Token | `{ "username": "admin", "password": "123" }` |
| `POST` | `/api/auth/logout` | Vô hiệu hóa Session đang login bằng Cookie | Không yêu cầu |

### Phân hệ Công việc (`/api/todos`)
> **Lưu ý:** Tất cả các endpoint dưới đây đều yêu cầu bạn phải đang đăng nhập trước đó (truyền lên `Bearer Token` hoặc gửi kèm Session Cookie hợp lệ).

| Phương thức | Endpoint | Mô tả |
| :--- | :--- | :--- |
| `GET` | `/api/todos` | Lấy danh sách nhiệm vụ của người đang đăng nhập |
| `POST` | `/api/todos` | Tạo mới một nhiệm vụ (Bắt buộc có `title`, tùy chọn `description`) |
| `GET` | `/api/todos/{id}` | Lấy chi tiết một nhiệm vụ cụ thể |
| `PUT` | `/api/todos/{id}` | Cập nhật lại một nhiệm vụ |
| `DELETE` | `/api/todos/{id}` | Xóa một nhiệm vụ |

## 🧪 Hướng dẫn thiết lập Test với Postman (Dùng JWT)

1. Gửi request đăng ký tài khoản mới tại endpoint `/api/auth/register`.
2. Đăng nhập tại `/api/auth/login` và copy dải ký tự `token` mà Server phản hồi về trong định dạng JSON.
3. Để gọi bất kỳ API nào nằm trong nhánh `/api/todos`, bạn hãy sang tab **Authorization** (Ủy quyền) trong Postman, đổi Type sang **Bearer Token**, dán mã token vừa nãy vào và bấm Send. Mọi request lúc này sẽ được xác thực thành công.

## 📖 Tài liệu API (Swagger)

Dự án có sẵn file cấu hình API theo chuẩn OpenAPI 3.0 tại file `swagger.yaml`. Bạn có thể:
* Sử dụng [Swagger Editor](https://editor.swagger.io/) và dán nội dung file `swagger.yaml` vào để xem tài liệu trực quan.
* Tích hợp các thư viện như `springdoc-openapi` để tự động tạo giao diện UI từ file này.
