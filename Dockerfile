FROM maven:3.6.3-openjdk-8-slim AS build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN  mvn "verify" "-Pproduction"

FROM openjdk:8-jdk-alpine AS production
RUN addgroup -S item-tracker && adduser -S admin -G item-tracker 
USER admin:item-tracker
WORKDIR home/admin
COPY --from=build target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
