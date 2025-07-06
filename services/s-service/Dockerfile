FROM maven:3.8.6-amazoncorretto-17 as maven
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package -DskipTests


FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=/build/target/*.jar
RUN mkdir -p /app/student-service
COPY --from=maven $JAR_FILE /app/student-service/student-service.jar
ENTRYPOINT["java", "-jar", "/app/student-service/student-service.jar"]