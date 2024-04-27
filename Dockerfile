FROM openjdk:21-slim
VOLUME /app
EXPOSE 8080
COPY /data/jenkins/workspace/APP-DEV/buil_app$ /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]