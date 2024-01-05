FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-alpine -y
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine
EXPOSE 8080
COPY --from=build /target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]