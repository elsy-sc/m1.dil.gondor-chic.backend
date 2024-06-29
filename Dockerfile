FROM maven:3.6.3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/magic-vente-stock-0.0.1-SNAPSHOT.jar magic-vente-stock.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "magic-vente-stock.jar"]
