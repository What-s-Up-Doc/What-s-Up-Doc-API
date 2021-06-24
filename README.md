# What-s-Up-Doc-API

What's Up Doc is a medical practice management system. It is suitable for small and medium-sized practices.
This repository is the backend Rest API made in Java 11 with Spring.

## Prerequisites
* Java 11
* Maven
* MariaDB

## Installation
```shell
git clone https://github.com/What-s-Up-Doc/What-s-Up-Doc-API.git
```

## Build it
```shell
mvn clean package 
```
You can also build the docker image :
```shell
docker build .
```

## Run it
The app uses environments variables defined in the .env.example and start on port 8080.
```shell
java -jar target/whatsupdocapi-<YOUR_VERSION>.jar
```
Or you can use docker the docker image.
