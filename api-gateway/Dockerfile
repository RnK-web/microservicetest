FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/api-gateway
RUN cd api-gateway && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8082
COPY --from=build /app/api-gateway/target/*.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]