package com.rdgs.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    @Value("${product.service.name}")
    private String productServiceName;

    @Value("${order.service.name}")
    private String orderServiceName;

    @Value("${inventory.service.name}")
    private String inventoryServiceName;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(orderServiceName, r -> r.path("/api/v1/orders/**").uri("lb://"+orderServiceName))
                .route(productServiceName, r -> r.path("/api/v1/products/**").uri("lb://"+productServiceName))
                .route(inventoryServiceName, r -> r.path("/api/v1/inventory/**").uri("lb://"+inventoryServiceName))
                .build();
    }

}
