FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} sarbacane-0.0.1-SNAPSHOT.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sarbacane-0.0.1-SNAPSHOT.jar"]