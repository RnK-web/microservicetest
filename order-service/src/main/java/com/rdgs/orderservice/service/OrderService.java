package com.rdgs.orderservice.service;

import com.rdgs.orderservice.dto.OrderCreationRequest;
import com.rdgs.orderservice.dto.OrderCreationResponse;
import com.rdgs.orderservice.exception.StockNotAvailableException;
import com.rdgs.orderservice.model.Order;
import com.rdgs.orderservice.repository.OrderRepository;
import com.rdgs.orderservice.service.feignclient.FeignInventoryClient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FeignInventoryClient feignInventoryClient;

    public OrderService(OrderRepository orderRepository, FeignInventoryClient feignInventoryClient) {
        this.orderRepository = orderRepository;
        this.feignInventoryClient = feignInventoryClient;
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

        return OrderCreationResponse.from(newOrder);
    }
}
