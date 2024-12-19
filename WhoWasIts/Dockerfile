FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/WhoWasIts-0.0.1-SNAPSHOT.jar java-app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "java-app.jar"]