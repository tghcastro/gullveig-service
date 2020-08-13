FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

# TODO: review how to copy all folder with its structure
COPY gradle/wrapper/gradle-wrapper.jar ./gradle/wrapper/gradle-wrapper.jar
COPY gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/gradle-wrapper.properties
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY src src

RUN ./gradlew clean build
RUN (cd build/libs; jar -xf *.jar)

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=/workspace/app/build/libs
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# TODO: fix hardcoded ""-Dspring.profiles.active=local" parameter
ENTRYPOINT ["java","-Dspring.profiles.active=local", "-cp","app:app/lib/*","gullveig.companiesservice.GullveigCompaniesServiceApplication"]