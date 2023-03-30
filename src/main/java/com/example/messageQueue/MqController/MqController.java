package com.example.messageQueue.MqController;

import com.example.messageQueue.MqConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Stack;

@RestController
@RequestMapping("/mq")
@RequiredArgsConstructor
public class MqController {
    private final RabbitTemplate rabbitTemplate;
    private final Stack<String> messages;
    @GetMapping
    public String getLastMessage(){
        return messages.get(0);
    }

    @PostMapping
    public void createMessage(@RequestBody String message){
        System.out.println("writing to the exchange");
        rabbitTemplate.convertAndSend(MqConfiguration.exchangeName, "key-1", message);
//        rabbitTemplate.convertAndSend("STSAS-Exchange", "key-1", "Hello -world");
        System.out.println("writing complete");
    }
}
