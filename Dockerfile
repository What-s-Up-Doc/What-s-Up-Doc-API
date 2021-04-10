FROM openjdk:11.0.10-jre-slim

EXPOSE 8080
COPY ./target/whatsupdocapi-*.jar /app/whatsupdocapi.jar

ENTRYPOINT ["java", "-jar", "/app/whatsupdocapi.jar"]