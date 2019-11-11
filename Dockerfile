FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/sarbacane-0.0.1-SNAPSHOT.jar $PROJECT_HOME/sarbacane-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

EXPOSE 8000

CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb:27017/sarbacane","-Djava.security.egd=file:/dev/./urandom","-jar", "-Xdebug", "-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n", "./sarbacane-0.0.1-SNAPSHOT.jar"]
