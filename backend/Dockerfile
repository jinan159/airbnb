FROM openjdk:11.0.11
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} airbnb.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "airbnb.jar"]
