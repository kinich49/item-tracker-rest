FROM openjdk:8-jdk-alpine
COPY ./target/item-tracker-1.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch item-tracker-1.0.jar'
EXPOSE 9000
ENTRYPOINT ["java","-jar","item-tracker-1.0.jar"]