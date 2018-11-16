#!/usr/bin/env sh


cat << EOF > ./application.properties
spring.data.mongodb.host: ${MONGODB_HOST:-localhost}
spring.data.mongodb.port: ${MONGODB_PORT:-27017}
spring.data.mongodb.database: ${MONGODB_DATABASE:-admin}
EOF

java -jar *.jar
