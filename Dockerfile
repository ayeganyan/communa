FROM openjdk:8-jdk-alpine
MAINTAINER ayeganyan92@gmail.com
VOLUME /tmp
EXPOSE 8000
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/server-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]