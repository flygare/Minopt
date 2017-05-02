FROM openjdk:8-jre-alpine

RUN ["/bin/sh", "-c", "mkdir -p /service"]

WORKDIR /service
COPY target/scala-2.11/addressService.jar /service/addressService.jar

EXPOSE 3001

CMD ["java", "-cp", "addressService.jar", "me.flygare.AddressService"]
