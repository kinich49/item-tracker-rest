
#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 9000
COPY --from=build /home/app/target/item-tracker-1.0.jar item-tracker-1.0.jar
ENTRYPOINT ["java","-jar","item-tracker-1.0.jar"]