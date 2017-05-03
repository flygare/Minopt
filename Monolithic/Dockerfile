FROM cassandra:3.0

WORKDIR /mono

COPY target/scala-2.11/monolithic.jar monolithic.jar
COPY Application.conf Application.conf

EXPOSE 3000

CMD  cassandra ; sleep 15 ; java -cp monolithic.jar -Dconfig.file=Application.conf me.flygare.Monolithic
