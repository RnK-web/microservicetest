package com.rdgs.productservice.dto;

import com.rdgs.productservice.model.Product;

import java.util.List;

public record ProductResponse(List<ProductDto> products) {

    public static ProductResponse from(List<Product> products) {
        return new ProductResponse(products.stream().map(ProductDto::from).toList());
    }
}
