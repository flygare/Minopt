FROM tomcat:8.0-jre8-alpine

RUN ["/bin/sh", "-c", "mkdir -p /service"]
ENV JAVA_OPTS="-XX:PermSize=2g -XX:MaxPermSize=1g"
WORKDIR /service
COPY target/scala-2.11/profileService.jar /service/profileService.jar

EXPOSE 3003

CMD ["java", "-cp", "profileService.jar", "me.flygare.ProfileService"]
