FROM maven:3.6.3-openjdk-11-slim AS build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
ARG stage
RUN  mvn verify -P${stage}

FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-alpine-slim AS production
RUN addgroup -S item-tracker && adduser -S admin -G item-tracker 
USER admin:item-tracker
WORKDIR home/admin
COPY --from=build target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
