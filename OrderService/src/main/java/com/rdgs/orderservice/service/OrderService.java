package com.rdgs.orderservice.service;

import com.rdgs.orderservice.dto.OrderCreationRequest;
import com.rdgs.orderservice.dto.OrderCreationResponse;
import com.rdgs.orderservice.model.Order;
import com.rdgs.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient restClient = RestClient.create();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public OrderCreationResponse createOrder(OrderCreationRequest order) {
        // TODO : Call inventory service to look at stock.
        //var isInStock = restClient.get().uri(inventoryUrl + "api/v1/inventory?id="+order.productId()+"&quantity="+order.quantity())
        //        .accept(MediaType.APPLICATION_JSON).retrieve().body(Boolean.class);
        //if (isInStock == null || !isInStock) {
        //    return null;
        //}
        var newOrder = new Order();
        newOrder.setProductId(order.productId());
        newOrder.setQuantity(order.quantity());
        newOrder.setPrice(order.price());
        orderRepository.save(newOrder);

        return OrderCreationResponse.from(newOrder);
    }
}
