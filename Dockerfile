FROM eclipse-temurin:21-jre-alpine

WORKDIR /codinviec

# Copy bất kỳ file jar nào trong target và đổi tên thành codinviec.jar
COPY target/*.jar codinviec.jar

# Expose port Spring Boot
EXPOSE 8080

# Chạy Spring Boot
ENTRYPOINT ["java", "-jar", "codinviec.jar"]
