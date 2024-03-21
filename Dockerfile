# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Stage 2: Run the application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/StanPortfolio-0.0.1-SNAPSHOT.jar ./StanPortfolio.jar

CMD ["java","-jar","StanPortfolio.jar"]