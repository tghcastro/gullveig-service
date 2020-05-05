# Gullveig Companies Service

## Build commands

```shell script
mvn clean install

// Unpack files so we can create Dockerimage caching Spring dependencies
mkdir -p target/dependency && (cd target/dependency; jar -xf ../gullveig-companies-service*.jar)

docker build -t tghcastro/gullveig-companies-service .

docker run -p 8080:8080 --name gullveig-companies-service tghcastro/gullveig-companies-service
```