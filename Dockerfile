# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Package the application without running tests
RUN mvn -B -DskipTests package

# Run stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/target/QuantityMeasurementApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]