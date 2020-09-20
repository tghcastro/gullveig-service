FROM openjdk:8-jdk-alpine

WORKDIR /usr/app/

COPY ./stocks-service/build/libs/stocks-service-*.SNAPSHOT.jar /usr/app/stocks-service.jar

ENTRYPOINT ["java","-jar", "/usr/app/stocks-service.jar"]