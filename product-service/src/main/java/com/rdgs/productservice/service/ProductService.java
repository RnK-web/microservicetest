package com.rdgs.productservice.service;

import com.rdgs.productservice.dto.ProductCreationRequest;
import com.rdgs.productservice.dto.ProductCreationResponse;
import com.rdgs.productservice.dto.ProductResponse;
import com.rdgs.productservice.model.Product;
import com.rdgs.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse getProducts() {
        return ProductResponse.from(productRepository.findAll());
    }

    public ProductCreationResponse createProduct(ProductCreationRequest productCreationRequest) {
        var productEntity = new Product();
        productEntity.setLabel(productCreationRequest.label());
        productEntity.setPrice(productCreationRequest.price());
        productRepository.save(productEntity);
        return ProductCreationResponse.from(productEntity);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
