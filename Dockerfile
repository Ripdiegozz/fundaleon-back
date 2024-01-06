# Production Stage
FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.war
COPY ./target/fundaleon-api-rest-0.0.1-SNAPSHOT.war fundaleon-api-rest-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/fundaleon-api-rest-0.0.1-SNAPSHOT.war"]