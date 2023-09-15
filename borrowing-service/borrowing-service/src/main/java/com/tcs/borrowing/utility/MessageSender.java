package com.tcs.borrowing.utility;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    public void send(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
