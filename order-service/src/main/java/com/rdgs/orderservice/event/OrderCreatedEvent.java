package com.rdgs.orderservice.event;

public record OrderCreatedEvent(String orderId, String email){
}
