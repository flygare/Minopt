FROM openjdk:8

RUN ["/bin/sh", "-c", "mkdir -p /service"]

WORKDIR /service
COPY target/scala-2.11/profileService.jar /service/profileService.jar

EXPOSE 3003

CMD ["java", "-cp", "profileService.jar", "me.flygare.ProfileService"]
