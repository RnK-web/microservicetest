package com.rdgs.productservice.dto;

import com.rdgs.productservice.model.Product;

public record ProductDto(String id, String label, double price) {

    public static ProductDto from(Product product) {
        return new ProductDto(product.getId(),  product.getLabel(), product.getPrice());
    }
}
