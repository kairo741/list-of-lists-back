FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} lists-service.jar

ENV SPRING_PROFILES_ACTIVE docker

ENTRYPOINT ["java","-jar","/lists-service.jar"]
EXPOSE 8080
