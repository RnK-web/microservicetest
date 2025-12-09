package com.rdgs.orderservice.controller;

import com.rdgs.orderservice.dto.OrderCreationRequest;
import com.rdgs.orderservice.dto.OrderCreationResponse;
import com.rdgs.orderservice.exception.StockNotAvailableException;
import com.rdgs.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderCreationResponse createOrder(@RequestBody OrderCreationRequest order) throws StockNotAvailableException {
        return orderService.createOrder(order);
    }

    @ExceptionHandler(StockNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleStockNotAvailableException(StockNotAvailableException e) {
    }
}
