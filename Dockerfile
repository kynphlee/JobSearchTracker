FROM openjdk:11.0.8-jdk
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]