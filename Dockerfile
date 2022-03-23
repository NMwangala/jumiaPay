#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

#Information around who maintains the image
MAINTAINER mwangala.com

# Add the application's jar to the container
COPY target/jumiaPay-1.0.jar jumiaPay-1.0.jar

#execute the application
ENTRYPOINT ["java","-jar","/jumiaPay-1.0.jar"]