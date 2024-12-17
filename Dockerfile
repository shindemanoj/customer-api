FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./target/customer-0.0.1-SNAPSHOT.jar customer-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "customer-api.jar"]