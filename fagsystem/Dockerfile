FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
RUN mkdir /fagsystem
COPY build/libs/fagsystem-0.0.1-SNAPSHOT.jar /fagsystem/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/fagsystem/app.jar"]
