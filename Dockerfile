FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]