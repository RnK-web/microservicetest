package com.rdgs.orderservice.dto;

import com.rdgs.orderservice.model.Order;

public record OrderCreationResponse(long orderId, String productId, long quantity, double price) {

    public static OrderCreationResponse from(Order order) {
        return new OrderCreationResponse(order.getId(), order.getProductId(), order.getQuantity(),  order.getPrice());
    }
}
