# FROM eclipse-temurin:21-jre-alpine
# WORKDIR /codinviec
# COPY target/codinviec-0.0.1-SNAPSHOT.jar codinviec.jar
# ENTRYPOINT ["java","-jar","codinviec.jar"]
# EXPOSE 8080

# -----------------------------
# Stage 1: Build JAR
# -----------------------------
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy toàn bộ source code vào container
COPY . .

# Build project, bỏ qua test để nhanh hơn
RUN mvn clean package -DskipTests

# -----------------------------
# Stage 2: Run JAR
# -----------------------------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy JAR vừa build từ stage trước
COPY --from=build /app/target/*.jar app.jar

# Chạy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

