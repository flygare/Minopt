FROM openjdk:8-jre-alpine

RUN ["/bin/sh", "-c", "mkdir -p /mono"]

WORKDIR /mono
COPY target/scala-2.11/monolithic.jar /service/monolithic.jar

CMD ["java", "-cp", "monolithic.jar", "me.flygare.Monolithic"]
