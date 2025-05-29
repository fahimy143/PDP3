FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]