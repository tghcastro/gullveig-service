FROM openjdk:8-jdk-alpine

WORKDIR /usr/app/

COPY ./operations-service/build/libs/operations-service-*.SNAPSHOT.jar /usr/app/operations-service.jar

ENTRYPOINT ["java","-jar", "/usr/app/operations-service.jar"]