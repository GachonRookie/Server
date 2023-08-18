FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} Server.jar
ENTRYPOINT ["java","-jar","Server.jar", "--spring.profiles.active=dev"]

