FROM openjdk:8-jre-alpine

WORKDIR /service
COPY target/scala-2.11/addressService.jar addressService.jar

EXPOSE 3001

CMD ["java", "-cp", "addressService.jar", "me.flygare.AddressService"]
