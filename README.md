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


### Transactions Service

Running Port: 8070

#### Commands

- Running Application
```shell
java -jar ./transactions-service/build/libs/transactions-service-0.0.1.SNAPSHOT.jar 

docker build -t gullveig/transactions-service -f ./transactions-service/Dockerfile .
docker run -p 8070:8070 -e SPRING_PROFILES_ACTIVE=local --name transactions-service gullveig/transactions-service
```

## Environment Commands

```shell script
# Starting test environment (jenkins - portainer - wiremock - cassandra[soon] - kafka[soon])
docker-compose -f .environment/test/docker-compose.test.yml down
docker-compose -f .environment/test/docker-compose.test.yml up -d --build --force-recreate --remove-orphans
docker-compose -f .environment/test/docker-compose.test.yml build

# Get Jenkins admin password (inside Jenkins container)
cat /var/jenkins_home/secrets/initialAdminPassword

```


