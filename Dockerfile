# Use official OpenJDK image with JDK 17
FROM openjdk:17-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy JAR from target folder into the container
COPY target/bookapi-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

