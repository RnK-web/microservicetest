package com.rdgs.orderservice.dto;

public record OrderCreationRequest(String productId, long quantity, double price) {
}
