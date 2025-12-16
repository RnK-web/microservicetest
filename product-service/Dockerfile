FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/product-service
RUN cd product-service && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8083
COPY --from=build /app/product-service/target/*.jar product-service.jar
ENTRYPOINT ["java", "-jar", "product-service.jar"]