# Fault Tolerant Microservices

## Context
This application was made as a part of a [presentation](https://github.com/luwojtaszek/fault-tolerant-microservices/blob/master/presentation/Presentation_20180507.pdf) I gave on May 2018.

The main purpose is to show how to use Neftlix libraries (Feign + Hystrix) and  RxJava in order to create fault tolerant and asynchronous communication between microservices.

## Requirements

- Java 8

## Build application

```bash
./mvnw clean install
```

## Start client and services

```bash
java -jar service/target/service-1.0.0.jar --info.name=Service1 --server.port=8081 --management.server.port=8081 --api.endpoints.contents.get.not-found-codes=CODE_1

java -jar service/target/service-1.0.0.jar --info.name=Service2 --server.port=8082 --management.server.port=8082 --api.endpoints.contents.get.not-found-codes=CODE_2

java -jar service/target/service-1.0.0.jar --info.name=Service3 --server.port=8083 --management.server.port=8083 --api.endpoints.contents.get.not-found-codes=CODE_3

java -jar client/target/client-1.0.0.jar --info.name=Client
```

## Urls

 - [Endpoint](http://localhost:8080/swagger-ui.html#/client/getCollectedContentsUsingGET)
 
 - [Hystrix Dashboard](http://localhost:8080/hystrix)
 
 - [http://localhost:8080/hystrix.stream](http://localhost:8080/hystrix.stream) -- to be entered in Hystrix Dashboard Page