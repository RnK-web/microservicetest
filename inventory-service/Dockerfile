FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/inventory-service
RUN cd inventory-service && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8081
COPY --from=build /app/inventory-service/target/*.jar inventory-service.jar
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]