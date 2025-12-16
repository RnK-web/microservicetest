FROM eclipse-temurin:21-alpine AS build
WORKDIR /app
COPY . /app/eureka-server
RUN cd eureka-server && chmod +x mvnw && ./mvnw -Dmaven.test.skip=true clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
EXPOSE 8082
COPY --from=build /app/eureka-server/target/*.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]