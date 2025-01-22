package com.naitik.springboot_rabbitmq.consumer;

import com.naitik.springboot_rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.jsonqueue.name}"})
    public void consumeJsonMessage(User user) {
        LOGGER.info(String.format("Received JSON Message --> %s",user.toString()));
    }
}
