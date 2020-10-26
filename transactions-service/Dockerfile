FROM openjdk:8-jdk-alpine

WORKDIR /usr/app/

COPY ./transactions-service/build/libs/transactions-service-*.SNAPSHOT.jar /usr/app/transactions-service.jar

ENTRYPOINT ["java","-jar", "/usr/app/transactions-service.jar"]