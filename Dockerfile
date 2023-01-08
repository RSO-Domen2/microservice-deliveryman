FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

ADD ./api/target/deliveryman-api-1.2.2-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "deliveryman-api-1.2.2-SNAPSHOT.jar"]