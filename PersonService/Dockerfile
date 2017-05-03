FROM openjdk:8-jre-alpine

WORKDIR /service

COPY target/scala-2.11/personService.jar personService.jar

CMD ["java", "-cp", "personService.jar", "me.flygare.PersonService"]
