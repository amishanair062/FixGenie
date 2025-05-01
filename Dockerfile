# Use an official JDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target\FixGenie-ai-0.0.1-SNAPSHOT.jar app.jar

# Expose port (must match what your Spring Boot runs on)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
