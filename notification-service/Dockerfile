FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/notification-service
RUN cd notification-service && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8082
COPY --from=build /app/notification-service/target/*.jar notification-service.jar
ENTRYPOINT ["java", "-jar", "notification-service.jar"]