# CodinViec - IT Job Platform API

Backend API cho sàn tìm kiếm việc làm IT và tuyển dụng ứng viên.

## 🛠 Tech Stack

- **Java 21** + **Spring Boot 3.5.6**
- **MySQL 8.0** - Database
- **Redis** - Caching & Session
- **Apache Kafka** - Message Queue
- **JWT** + **Google OAuth2** - Authentication
- **Swagger/OpenAPI** - API Documentation

## 📋 Yêu cầu

- Java 21+
- Maven 3.8+
- Docker & Docker Compose (khuyên dùng)

## ⚡ Cài đặt

### 1. Clone project

```bash
git clone <repository-url>
cd codinviec
```

### 2. Cấu hình môi trường

Tạo file `.env` tại thư mục gốc:

```env
# Database
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/codinviec_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=Codinviec123@
SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# Server
SERVER_PORT=8080

# JWT
JWT_SECRET=your-secret-key-min-256-bits
JWT_ACCESS_EXPIRED=3600000
JWT_REFRESH_EXPIRED=604800000

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=Codinviec123@

# Kafka
KAFKA_BOOSTRAP_SERVER=localhost:9094
KAFKA_CLIENT_ID=your-kafka-id
KAFKA_CLIENT_SECRET=your-kafka-secret

# Google OAuth2
SPRING_SECURITY_GOOGLE_CLIENT_ID=your-google-client-id
SPRING_SECURITY_GOOGLE_CLIENT_SECRET=your-google-client-secret

# Upload
UPLOAD_IMAGE=/uploads/images

# Client URL
CLIENT_URL=http://localhost:3000
```

### 3. Chạy với Docker (Khuyên dùng)

```bash
# Build và chạy tất cả services
docker-compose up -d

# Xem logs
docker-compose logs -f springboot-app
```

**Services khởi chạy:**
| Service | Port |
|---------|------|
| Spring Boot App | 8080 |
| MySQL | 3306 |
| Redis | 6379 |
| Redis UI | 8001 |
| Kafka | 9094 |
| Kafka UI | 8085 |
| Portainer | 9000 |

### 4. Chạy thủ công (Local)

```bash
# Khởi động MySQL, Redis, Kafka trước

# Build project
./mvnw clean package -DskipTests

# Chạy
java -jar target/codinviec-0.0.1-SNAPSHOT.jar
```

## 📚 API Documentation

Sau khi khởi động, truy cập Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

## 🔑 Chức năng chính

| Module | Mô tả |
|--------|-------|
| **Authentication** | Đăng ký, đăng nhập, Google OAuth2, JWT |
| **User/Company** | Quản lý tài khoản người dùng và công ty |
| **Job** | Đăng tin, tìm kiếm, quản lý việc làm |
| **CV** | Tạo và quản lý hồ sơ ứng viên |
| **Skills** | Kỹ năng, ngôn ngữ, chứng chỉ |
| **Search** | Tìm kiếm việc làm và ứng viên |
| **Wishlist** | Lưu việc làm và ứng viên yêu thích |
| **Review** | Đánh giá công ty |
| **Blog** | Bài viết, tin tức |
| **Payment** | Thanh toán dịch vụ |

## 📁 Cấu trúc project

```
src/main/java/com/project/codinviec/
├── config/          # Cấu hình (Security, Redis, Kafka, Swagger)
├── controller/      # REST API endpoints
├── dto/             # Data Transfer Objects
├── entity/          # JPA Entities
├── exception/       # Exception handlers
├── filter/          # Security filters
├── mapper/          # Entity-DTO mappers
├── repository/      # JPA Repositories
├── request/         # Request DTOs
├── service/         # Business logic
├── specification/   # JPA Specifications (search)
└── util/            # Helper classes
```

## 📄 Database

Import schema và sample data:

```bash
# Schema
mysql -u root -p codinviec_db < src/main/resources/itjob_db.sql

# Sample data
mysql -u root -p codinviec_db < src/main/resources/insert_sample_data.sql
```

## 📝 License

MIT License
