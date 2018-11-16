FROM maven:3.6.0-jdk-8-alpine as builder
WORKDIR /usr/src/app
COPY . .
RUN mvn install -DskipTests

FROM java:8u111-jre-alpine
WORKDIR /usr/share/jewel/
COPY .Dockerfiles .
COPY --from=builder /usr/src/app/target/*.jar .

CMD ["./entrypoint.sh"]
