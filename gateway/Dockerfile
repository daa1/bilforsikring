FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
RUN mkdir /gateway
COPY build/libs/gateway-0.0.1-SNAPSHOT.jar /gateway/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/gateway/app.jar"]
