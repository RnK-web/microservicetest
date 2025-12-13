package com.rdgs.productservice.dto;

import com.rdgs.productservice.model.Product;

public record ProductCreationResponse(String id, String label, double price) {

    public static ProductCreationResponse from(Product product) {
        return new ProductCreationResponse(product.getId(), product.getLabel(), product.getPrice());
    }
}
