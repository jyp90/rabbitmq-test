package com.example.rabbitmq;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageSendTester implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitReceiver receiver;

    public RabbitMessageSendTester(RabbitTemplate rabbitTemplate, RabbitReceiver receiver) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    public void run(String... args) throws Exception {
        System.out.println("Sending message.....");
        rabbitTemplate.convertAndSend(MessagingRabbitApplication.topicExchangeName,
            MessagingRabbitApplication.serviceName + ".baz",
            "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}