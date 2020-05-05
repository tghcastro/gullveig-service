# Gullveig Companies Service

## About

### Docker
    
```shell script
docker pull tghcastro/gullveig-companies-service´
```

DockerHub Repo: https://hub.docker.com/r/tghcastro/gullveig-companies-service
   
## Build commands

```shell script
mvn clean install

// Unpack files so we can create Dockerimage caching Spring dependencies
mkdir -p target/dependency && (cd target/dependency; jar -xf ../gullveig-companies-service*.jar)

docker build -t tghcastro/gullveig-companies-service .
docker build -t tghcastro/gullveig-companies-service -f ./.docker/Dockerfile .

docker run -p 8080:8080 --name gullveig-companies-service tghcastro/gullveig-companies-service
```