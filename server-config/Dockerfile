FROM openjdk:17-alpine
COPY build/libs/server-config-0.0.1-SNAPSHOT.jar server-config-0.0.1-SNAPSHOT.jar
ADD config/application.properties /home/config/application.properties
ENTRYPOINT ["java","-jar","/server-config-0.0.1-SNAPSHOT.jar"]