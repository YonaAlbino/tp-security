FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/tp-integrador-spring-security-0.0.1.jar
COPY ${JAR_FILE} app_integrador.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_integrador.jar"]