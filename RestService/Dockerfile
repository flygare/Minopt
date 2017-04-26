FROM openjdk:8

RUN ["/bin/sh", "-c", "mkdir -p /service"]

WORKDIR /service
COPY target/scala-2.11/restService.jar /service/restService.jar

EXPOSE 3000

CMD ["java", "-cp", "restService.jar", "me.flygare.RestService"]
