FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/order-service
RUN cd order-service && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8082
COPY --from=build /app/order-service/target/*.jar order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]