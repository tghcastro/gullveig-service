# Gullveig Service

## About

## Modules

### Companies Service

Running Port: 8080

#### Commands

- Running Application
```shell
java -jar ./companies-service/build/libs/companies-service-0.0.1.SNAPSHOT.jar 

docker build -t gullveig/companies-service -f ./companies-service/Dockerfile .
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=local --name companies-service gullveig/companies-service
```

### Stocks Service

Running Port: 8090

#### Commands

- Running Application
```shell
java -jar ./stocks-service/build/libs/stocks-service-0.0.1.SNAPSHOT.jar 

docker build -t gullveig/stocks-service -f ./stocks-service/Dockerfile .
docker run -p 8090:8090 -e SPRING_PROFILES_ACTIVE=local --name stocks-service gullveig/stocks-service
```

## All Environment Commands

```shell script
gradle clean build

docker-compose -f .environment/docker-compose.local.yml up --build --remove-orphans --force-recreate

```


