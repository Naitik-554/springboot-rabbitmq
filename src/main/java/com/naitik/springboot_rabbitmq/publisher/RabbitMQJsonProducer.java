package com.naitik.springboot_rabbitmq.publisher;

import com.naitik.springboot_rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.jsonrouting.name}")
    private String jsonRouting;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("JSON message sent --> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange,jsonRouting,user );
    }


}
