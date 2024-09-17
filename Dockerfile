FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app

COPY . /app/

RUN mvn clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar finance-tracker.jar

EXPOSE 8080

CMD ["java", "-jar", "finance-tracker.jar"]
