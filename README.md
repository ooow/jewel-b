# Jewel Back-end [![Build Status](https://travis-ci.com/ooow/jewel-b.svg?token=9FACddwEUzmJsZDJqVky&branch=master)](https://travis-ci.com/ooow/jewel-b)

# Install & Deploy
## Technical Requirements
- Java 1.8+
- Maven 3.3+
## Install
Installs artifact locally:
```
        mvn clean install
```
By default:
```
        groupId=jewel
        artifactId=jewel-app
```
## Mongo
Ensure that there are no applications on port 27017 (such as MongoDB). If they are, shut them down or use different port in next step.
Create a mongo docker container(if you decided to run a container on a port different from 27017, use -p x:27017 instead, where x is your localhost port):
```
        docker run -d --name jewel-db -p 27017:27017 mongo
```
## Database configuration
Create and configure new Data Source configuration.
Use following values as configuration:
```
        host: localhost
        port: 27017
        database: admin
```
## Deploy
- Deploys on **localhost:8080** by default:
```
        mvn package
        java -jar target/jewel-app-0.x.x-SNAPSHOT.jar
```
- Deploys on **localhost:8080** using spring-boot plugin:
```
        mvn spring-boot:run
```