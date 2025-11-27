FROM eclipse-temurin:21-jre-alpine
WORKDIR /codinviec
COPY target/codinviec-0.0.1-SNAPSHOT.jar codinviec.jar
ENTRYPOINT ["java","-jar","codinviec.jar"]
EXPOSE 8080

