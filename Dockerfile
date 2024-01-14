FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ToDoListApplicationSpecialTopics-0.0.1-SNAPSHOT.jar /app/ToDoListApplicationSpecialTopics-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "ToDoListApplicationSpecialTopics-0.0.1-SNAPSHOT.jar"]
