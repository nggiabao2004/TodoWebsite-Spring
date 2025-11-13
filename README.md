# Todo Website Spring - Xây dựng ứng dụng Quản lý Công việc với Spring Security và JWT

## 1. Phần mềm và ngôn ngữ lập trình được sử dụng trong việc xây dựng Project

Project Spring Security được xây dựng và phát triển bằng:

- **Ngôn ngữ lập trình** - Java (version 21)
- **Framework** - Spring Framework v3.5.7
- **Công cụ quản lý dự án** - Maven Plugin
- **Cơ sở dữ liệu** - Phần mềm XAMPP (có thể thay thế cho MySQL)
- **IDE soạn code** - Phần mềm Intellij IDEA Ultimate (có thể sử dụng Visual Studio Code, hay là IntelliJ IDEA Community Edition)
- **Công cụ test API** - Postman và SwaggerUI
- **Quản lý và lưu trữ Project** - GitBash và GitHub
- **Website hỗ trợ tạo các dependencies trong Project** - "start.spring.io" và "mvnrepository.com"

## 2. Dependencies trong Project

Project Spring Security sử dụng các dependencies sau:

### **Core Spring Boot Dependencies**
- **spring-boot-starter-security** - Spring Security (version theo parent: 3.5.6)
- **spring-boot-starter-validation** - Validation framework
- **spring-boot-starter-web** - Web MVC
- **spring-boot-starter-data-jpa** - JPA/Hibernate ORM
- **spring-boot-starter-test** - Testing framework

### **Database**
- **mysql-connector-j** - MySQL JDBC Driver

### **Development Tools**
- **spring-boot-devtools** - Live reload & debugging
- **lombok** - Code generation (annotations)

### **JWT Token Management**
- **jjwt-api** v0.12.5 - JWT token creation/validation
- **jjwt-impl** v0.12.5 - JWT implementation (runtime)
- **jjwt-jackson** v0.12.5 - JWT JSON processing (runtime)

### **Testing**
- **spring-security-test** - Security testing utilities

### **Parent Version**
- **spring-boot-starter-parent** v3.5.6 (sử dụng Java 21)

## 3. Các Endpoint API

### **Authentication (Xác thực)**
| Method | Endpoint    | Mô tả                      | Auth |
|--------|-------------|----------------------------|------|
| POST   | `/register` | Đăng ký tài khoản mới      | ❌  |
| POST   | `/login`    | Đăng nhập & nhận JWT token | ❌  |

### **Todo Management (Quản lý công việc)**
| Method | Endpoint      | Mô tả                      | Auth |
|--------|---------------|----------------------------|------|
| GET    | `/todos`      | Lấy danh sách tất cả todos | ✅  |
| POST   | `/todos`      | Tạo todo mới               | ✅  |
| GET    | `/todos/{id}` | Lấy chi tiết todo theo ID  | ✅  |
| PUT    | `/todos/{id}` | Cập nhật todo theo ID      | ✅  |
| DELETE | `/todos/{id}` | Xóa todo theo ID           | ✅  |

## 4. Cài đặt & Chạy Project

### Bước 1. **Clone repository**
```bash
git clone https://github.com/nggiabao2004/TodoWebsite-Spring.git
cd TodoWebsite-Spring
```

### Bước 2. **Cấu hình Database** trong `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
```
**Lưu ý: Đường dẫn này chỉ đúng nếu tạo Schema là "todo_db". Nên nếu bạn định connect với Schema bạn tạo ra thì hãy điền đúng tên Schema đó vào đường dẫn.**

### Bước 3. **Build & Run**
```bash
mvn clean install
mvn spring-boot:run
```
