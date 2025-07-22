# Use official OpenJDK 17 Alpine image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file (change this to match your build output)
COPY target/*.jar app.jar

# Create a non-root user and switch to it
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose the port your app runs on
EXPOSE 8080

# Entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
