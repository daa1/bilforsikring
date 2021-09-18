FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
RUN mkdir /brevtjeneste
COPY build/libs/brevtjeneste-0.0.1-SNAPSHOT.jar /brevtjeneste/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/brevtjeneste/app.jar"]
