FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/Nilin-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} nilin

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "nilin"]