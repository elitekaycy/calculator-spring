FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/calculator-app.jar /app/calculator-app.jar

EXPOSE 8080

CMD ["java", "-jar", "calculator-app.jar"]