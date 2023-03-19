FROM openjdk:17
COPY ./target/schoolService-0.0.1-SNAPSHOT.jar schoolService-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "schoolService-0.0.1-SNAPSHOT.jar"]