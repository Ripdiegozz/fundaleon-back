FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/fundaleon-api-rest-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]