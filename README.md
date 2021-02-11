# Demo project for Spring Boot with Atomix framework for distributed service coordination

## Run Locally

$`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9001`

## Load Test Locally

Start multiple bash shells with this script

$`while true; do curl localhost:10000/api/v1/sequence/next; sleep 0.1; echo; done`
