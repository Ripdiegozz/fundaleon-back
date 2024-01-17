FROM openjdk:17-jdk-alpine
ARG WAR_FILE=target/*.war
COPY ./target/fundaleon-api-rest-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]