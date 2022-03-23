package com.example.rabbitmq;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitReceiver receiver;

    @Test
    void sendRabbitMessage() throws Exception {
        System.out.println("Sending message.....");
        rabbitTemplate.convertAndSend(MessagingRabbitApplication.topicExchangeName,
            MessagingRabbitApplication.serviceName + ".baz",
            "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
