FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/api-gateway-service
RUN cd api-gateway-service && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8082
COPY --from=build /app/api-gateway-service/target/*.jar api-gateway-service.jar
ENTRYPOINT ["java", "-jar", "/api-gateway-service.jar"]