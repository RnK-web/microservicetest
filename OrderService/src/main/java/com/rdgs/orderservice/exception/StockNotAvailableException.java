package com.rdgs.orderservice.exception;

public class StockNotAvailableException extends Throwable {
    public StockNotAvailableException(String message) {
        super(message);
    }
}
