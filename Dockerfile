FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY src/main/resources/db /home/app/src/main/resources/db
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/*.jar app.jar
COPY --from=build /home/app/src/main/resources/db /db
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
