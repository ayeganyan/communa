FROM openjdk:8-jdk-alpine
MAINTAINER ayeganyan92@gmail.com
VOLUME /tmp
EXPOSE 8000
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/server-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#
#FROM maven:3.6.0-jdk-11-slim as build
#MAINTAINER ayeganyan92@gmail.com
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean install
#
#FROM openjdk:8-jdk-alpine
#RUN mkdir -p /app/
#COPY --from=build target/server-0.0.1-SNAPSHOT.jar /app/app.jar
#
#EXPOSE 8000
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]