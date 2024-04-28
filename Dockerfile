FROM openjdk:21-jdk
VOLUME /app
EXPOSE 8080
ADD app.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
