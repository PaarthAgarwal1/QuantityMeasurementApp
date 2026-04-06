# Use OpenJDK 17 as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target directory
COPY target/QuantityMeasurementApp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on (default Spring Boot port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]