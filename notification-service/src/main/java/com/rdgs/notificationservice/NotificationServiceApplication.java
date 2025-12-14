package com.rdgs.notificationservice;

import com.rdgs.notificationservice.event.OrderCreatedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = {"product-orders"}, groupId = "notifications-consumer-group")
    public void listen(OrderCreatedEvent event) {
        System.out.println(event);
    }

}
