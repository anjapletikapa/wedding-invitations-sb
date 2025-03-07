FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/invitationsSB-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]