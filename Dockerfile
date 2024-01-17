FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the WAR file into the container
COPY target/fundaleon-api-rest-0.0.1-SNAPSHOT.war app.war

# Expose the port if your application listens on a specific port
EXPOSE 8080

# Define the entry point to run your application
ENTRYPOINT ["java", "-jar", "app.war"]
