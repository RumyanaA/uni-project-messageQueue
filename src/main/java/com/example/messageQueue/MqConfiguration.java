package com.example.messageQueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Stack;

@Configuration
public class MqConfiguration {
    private final String queueName = "myQueue-RA";
    public static final String exchangeName = "myTopicExchange-RA";


    @Bean
    public Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean("exchange")
    public TopicExchange getExchange(){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange ){
        return BindingBuilder.bind(queue).to(exchange).with("key-1");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(MqReceiver receiver){
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public Stack messages(){
        return new Stack<String>();
    }
}
