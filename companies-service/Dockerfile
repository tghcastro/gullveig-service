FROM openjdk:8-jdk-alpine

WORKDIR /usr/app/

COPY ./companies-service/build/libs/companies-service-*.SNAPSHOT.jar /usr/app/companies-service.jar

ENTRYPOINT ["java","-jar", "/usr/app/companies-service.jar"]