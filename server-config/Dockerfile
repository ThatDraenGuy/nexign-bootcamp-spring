FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY $JAR_FILE app.jar
RUN apk --no-cache add curl
ADD config/application.properties /home/config/application.properties
ENTRYPOINT ["java","-jar","/app.jar"]