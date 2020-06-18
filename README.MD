# Item Tracker Rest

Welcome to Item Tracker! 
The intention of this project is to satisfy my need to keep track of my 
week-to-week shopping, such as groceries and stuff. 
This way I can visualice price increments, and be able to make better purchase decisions.

If you want to use this project for yourself, feel free to fork it. 
You can also fork the front end (It's a vue app), which is located in another repo.
## Requirements

For building and running the application you need:

- [JDK 1.8](https://openjdk.java.net/install/)
- [Maven 3](https://maven.apache.org)

Or alternative, [Docker](https://docs.docker.com/desktop/) 

## Running the service

### Maven
To run locally, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) 
like so:

```shell
mvn spring-boot:run
```

This project is configured to run at Port 9000

### Docker
Alternative, you can run this service with Docker. I added a simple and 
(most probably) poorly optimized Dockerfile to run this service. This is helpful if you don't have
the appropriate JDK and Maven installed and configured into your PATH.

Just follow these steps:

#### Build the docker image
```shell
docker build -t item-tracker-rest .
```

The project root folder should be the context
#### Run the container
```shell
docker run --name item-tracker-rest -p9000:9000 --rm item-tracker-rest
```

#### Stop the container
Once you're finished, don't forget to stop the container
```shell
docker stop item-tracker-rest
```
## Bugs and Enhancements
If you find any bug, please raise an issue with the steps and conditions
to reproduce it. If you want to contribute to this project, 
feel free to fork this repo and submit a pull request. 
Don't forget to run 

```shell
mvn verify
```

before any pull request. 

Ideas for enhancements and features are welcome! Raise an issue with the tag 

``Enhacement/Feature`` 