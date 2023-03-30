package com.example.messageQueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
@RequiredArgsConstructor
@Slf4j
public class MqReceiver {
    private final Stack<String> messages;
    public void receiveMessage(String message){
        messages.push(message);
        log.info("Received Message: %s", message);
        System.out.println(message);
    }
}
