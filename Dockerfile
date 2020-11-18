FROM openjdk:8-jdk-alpine
RUN addgroup -S item-tracker && adduser -S admin -G item-tracker 
USER admin:item-tracker
WORKDIR home/admin
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar 
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]
