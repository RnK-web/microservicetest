package com.rdgs.orderservice.service;

import com.rdgs.orderservice.dto.OrderCreationRequest;
import com.rdgs.orderservice.dto.OrderCreationResponse;
import com.rdgs.orderservice.model.Order;
import com.rdgs.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderCreationResponse createOrder(OrderCreationRequest order) {
        // TODO : Call inventory service to look at stock.
        var newOrder = new Order();
        newOrder.setProductId(order.productId());
        newOrder.setQuantity(order.quantity());
        newOrder.setPrice(order.price());
        orderRepository.save(newOrder);

        return OrderCreationResponse.from(newOrder);
    }
}
