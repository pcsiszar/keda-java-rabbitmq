package org.pcsiszar.kjr.send.rabbitmq;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

    private final RabbitTemplate template;

    private final Queue queue;

    private final AtomicBoolean sendingEnabled = new AtomicBoolean(true);

    public RabbitSender(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        builder.append('.');
        String message = builder.toString();
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

    public void send(String message, int count) {
        for (int i = 0; i < count; i++) {
            this.template.convertAndSend(queue.getName(), message);
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    public void disableSending() {
        sendingEnabled.set(false);
    }
}
