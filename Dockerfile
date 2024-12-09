FROM maven:3.9.9-eclipse-temurin-21-alpine as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/books_service-0.0.1-SNAPSHOT.war /app/books_service-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/books_service-0.0.1-SNAPSHOT.war"]