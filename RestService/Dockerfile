FROM openjdk:8-jre-alpine

WORKDIR /service

COPY target/scala-2.11/restService.jar restService.jar
COPY Application.conf Application.conf

EXPOSE 3000

CMD ["java", "-cp", "restService.jar", "-Dconfig.file=Application.conf", "me.flygare.RestService"]
