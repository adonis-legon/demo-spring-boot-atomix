FROM openjdk:8-jdk-slim

WORKDIR /app

RUN mkdir cluster-storage

ADD target/demo-spring-boot-atomix-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]