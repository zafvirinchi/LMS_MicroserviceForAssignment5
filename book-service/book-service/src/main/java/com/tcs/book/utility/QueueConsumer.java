package com.tcs.book.utility;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.tcs.book.service.IBookService;

@Component
public class QueueConsumer {

    @Autowired
    IBookService bookService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String message) {
        System.out.println("Message " + message);
        bookService.updateBook(UtilityMapper.stringToBook(message));
    }

}