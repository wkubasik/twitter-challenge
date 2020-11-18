FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
COPY target/*.jar twitter.jar
ENTRYPOINT ["java","-jar","twitter.jar"]