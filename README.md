# Jewel Back-end [![Build Status](https://travis-ci.com/ooow/jewel-b.svg?token=9FACddwEUzmJsZDJqVky&branch=master)](https://travis-ci.com/ooow/jewel-b)


# Setup
Ensure that there are no applications on port 27017 (such as MongoDB). If they are, shut them down or use different port in next step.
Create a mongo docker container(if you decided to run a container on a port different from 27017, use -p x:27017 instead, where x is your localhost port):

```
	docker run -d --name jewel-db -p 27017:27017 mongo
```	
  
## Database configuration
Create and configure new Data Source configuration.
Use following values as configuraton:

```
  spring.data.mongodb.host: localhost
  spring.data.mongodb.port: 27017
  spring.data.mongodb.database: admin
```