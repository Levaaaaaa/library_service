FROM openjdk:21
arg appwar=target/*.war
COPY ${appwar} app.war
ENTRYPOINT ["java", "-jar", "/app.war"]
expose 8080
expose 5432