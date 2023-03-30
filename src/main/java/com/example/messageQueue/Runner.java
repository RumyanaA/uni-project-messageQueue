package com.example.messageQueue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component //initializes the class
@RequiredArgsConstructor //creates a constructor with all final properties
public class Runner implements CommandLineRunner {

        private final RabbitTemplate rabbitTemplate;
        // this constructor is defined from @RequiredArgsConstructor
//    public Runner(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("writing to the exchange");
    rabbitTemplate.convertAndSend(MqConfiguration.exchangeName, "key-1", "Hello world");
//        rabbitTemplate.convertAndSend("STSAS-Exchange", "key-1", "Hello -world");
        System.out.println("writing complete");
    }
}
