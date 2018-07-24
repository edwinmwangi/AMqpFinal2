package com.example.mqapp;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Consumer consumer;

    public Publisher(Consumer receiver, RabbitTemplate rabbitTemplate) {
        this.consumer = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MqappApplication.topicExchangeName, "foo.bar.baz", "The publisher sends his regards!");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}