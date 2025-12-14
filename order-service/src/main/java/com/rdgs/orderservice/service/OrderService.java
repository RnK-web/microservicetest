package com.rdgs.orderservice.service;

import com.rdgs.orderservice.dto.OrderCreationRequest;
import com.rdgs.orderservice.dto.OrderCreationResponse;
import com.rdgs.orderservice.event.OrderCreatedEvent;
import com.rdgs.orderservice.exception.StockNotAvailableException;
import com.rdgs.orderservice.model.Order;
import com.rdgs.orderservice.repository.OrderRepository;
import com.rdgs.orderservice.service.feignclient.FeignInventoryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FeignInventoryClient feignInventoryClient;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, FeignInventoryClient feignInventoryClient, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.feignInventoryClient = feignInventoryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderCreationResponse createOrder(OrderCreationRequest order) throws StockNotAvailableException {
        var isInStock = feignInventoryClient.isInStock(order.productId(), order.quantity());
        if (!isInStock) {
            throw new StockNotAvailableException("Order creation failed, not enough stock !");
        }
        var newOrder = new Order();
        newOrder.setProductId(order.productId());
        newOrder.setQuantity(order.quantity());
        newOrder.setPrice(order.price());
        orderRepository.save(newOrder);

        log.info("Start - Sending order created event");
        kafkaTemplate.send("product-orders", new OrderCreatedEvent(newOrder.getId().toString(), "fakemail@gmail.com"));
        log.info("End - Sending order created event");

        return OrderCreationResponse.from(newOrder);
    }
}
