package com.rdgs.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    @Value("${product.service.uri}")
    private String productServiceUri;

    @Value("${order.service.uri}")
    private String orderServiceUri;

    @Value("${inventory.service.uri}")
    private String inventoryServiceUri;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order-service", r -> r.path("/api/v1/orders/**").uri(orderServiceUri))
                .route("product-service", r -> r.path("/api/v1/products/**").uri(productServiceUri))
                .route("inventory-service", r -> r.path("/api/v1/inventory/**").uri(inventoryServiceUri))
                .build();
    }

}
