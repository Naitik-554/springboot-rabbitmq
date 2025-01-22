package com.naitik.springboot_rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.jsonqueue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.name}")
    private String routing;
    @Value("${rabbitmq.jsonrouting.name}")
    private String jsonRouting;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routing);
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRouting);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

//    Also SpringBoot Auto Configure will configure
//    ConnectionFactory, RabbitTemplate, RabbitAdmin

}
