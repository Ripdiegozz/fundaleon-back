# Build Stage
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

# Production Stage
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY ./target/*.war fundaleon-api-rest-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/fundaleon-api-rest-0.0.1-SNAPSHOT.war"]