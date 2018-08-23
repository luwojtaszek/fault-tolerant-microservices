#!/bin/bash

# start service1
java -jar service/target/service-1.0.0.jar --server.port=8081 --management.server.port=8081 --api.endpoints.contents.get.not-found-codes=CODE_1

# start service2
java -jar service/target/service-1.0.0.jar --server.port=8082 --management.server.port=8082 --api.endpoints.contents.get.not-found-codes=CODE_2

# start service3
java -jar service/target/service-1.0.0.jar --server.port=8083 --management.server.port=8083 --api.endpoints.contents.get.not-found-codes=CODE_3

# start client