package com.paymentGateway.PaymentGatewayService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaConsumer {

    @KafkaListener(topics = "payment_topic", groupId = "my-consumer-group")
    public void listen(String message) {
        System.out.println("Received message in group my-consumer-group: " + message);
    }
}

