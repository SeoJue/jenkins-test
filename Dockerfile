FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./bin/libs/Jenkins_test-0.0.1-SNAPSHOT.jar /app/Jenkins_test-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "Jenkins_test-0.0.1-SNAPSHOT.jar"]