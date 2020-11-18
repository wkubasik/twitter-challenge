# twitter-challenge
Twitter Challenge

Implemented with Java 11.  
Tests in Groovy/Spock.

## Run with maven
You can run this application with maven command:
`mvn spring-boot:run`

## Visit Rest API documentation
After starting the application, documentation is available here:  
http://localhost:8080/swagger-ui.html

The documentation is interactive and you can use it to test the API.

## Run with docker (optional):
- `mvn install`
- `docker build -t twitter:0.0.1 .`
- `docker run -p 8080:8080 -t twitter:0.0.1 .`

To stop docker container:
- `docker ps`
- find `CONTAINER_ID` for image `twitter:0.0.1`
- `docker stop CONTAINER_ID`
