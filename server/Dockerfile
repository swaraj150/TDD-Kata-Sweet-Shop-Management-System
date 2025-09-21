FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/server-0.0.1-SNAPSHOT.jar app.jar

# Expose port Spring Boot runs on
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
