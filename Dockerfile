FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn test
RUN mvn package


FROM eclipse-temurin:21-jre

COPY --from=build /app/target/LibrarySystemMVC-0.0.1-SNAPSHOT.jar /app/LibrarySystemMVC.jar

ENTRYPOINT ["java", "-jar", "/app/LibrarySystemMVC.jar"]