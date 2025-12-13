package com.rdgs.productservice.controller;

import com.rdgs.productservice.dto.ProductCreationRequest;
import com.rdgs.productservice.dto.ProductCreationResponse;
import com.rdgs.productservice.dto.ProductResponse;
import com.rdgs.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService  productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ProductResponse getProducts() {
        return productService.getProducts();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductCreationResponse createProduct(@RequestBody ProductCreationRequest productCreationRequest) {
        return productService.createProduct(productCreationRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
