package com.rdgs.notificationservice;

import com.rdgs.notificationservice.event.OrderCreatedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class NotificationServiceApplication {

    private final JavaMailSender mailSender;

    public NotificationServiceApplication(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = {"product-orders"}, groupId = "notifications-consumer-group")
    public void listen(OrderCreatedEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("notification-service@mail.com");
        message.setTo(event.email());
        message.setSubject("Your order has been created");
        message.setText("""
                Hello,
                
                Your order has been created!
                Order id : """ + event.orderId());
        mailSender.send(message);
    }

}
