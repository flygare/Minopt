FROM openjdk:8-jre-alpine

RUN ["/bin/sh", "-c", "mkdir -p /service"]

WORKDIR /service
COPY target/scala-2.11/personService.jar /service/personService.jar

CMD ["java", "-cp", "personService.jar", "me.flygare.PersonService"]
