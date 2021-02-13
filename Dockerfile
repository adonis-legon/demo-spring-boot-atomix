FROM maven:3-openjdk-8 as build
WORKDIR /build/app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-slim
WORKDIR /app

RUN mkdir cluster-storage

COPY --from=build /build/app/target/demo-spring-boot-atomix-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]