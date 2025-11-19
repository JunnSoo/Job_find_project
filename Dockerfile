FROM eclipse-temurin:21-jre-alpine
WORKDIR /findjob
COPY ./target/it_job-0.0.1-SNAPSHOT.jar ./findjob.jar
ENTRYPOINT ["java","-jar","findjob.jar"]
EXPOSE 8080

