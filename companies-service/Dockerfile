FROM openjdk:8-jdk-alpine

WORKDIR /usr/app/

## Running direct from repository root (docker build)
# COPY ./companies-service/build/libs/companies-service-*.SNAPSHOT.jar /usr/app/companies-service.jar
# Command: docker build -t gullveig/companies-service -f ./companies-service/Dockerfile .

## Running on Palantir Docker
COPY companies-service-*.SNAPSHOT.jar /usr/app/companies-service.jar

ENTRYPOINT ["java","-jar", "/usr/app/companies-service.jar"]