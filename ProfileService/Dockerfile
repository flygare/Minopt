FROM openjdk:8

RUN ["/bin/sh", "-c", "mkdir -p /service"]

WORKDIR /service
COPY target/scala_2.11/profileService.jar /service
