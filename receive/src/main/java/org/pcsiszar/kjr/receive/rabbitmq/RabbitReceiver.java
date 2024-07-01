package org.pcsiszar.kjr.receive.rabbitmq;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Component
@RabbitListener(queues = "hello")
public class RabbitReceiver {

    AtomicInteger counter = new AtomicInteger();
    AtomicReference<String> latestMessage = new AtomicReference<>();

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        counter.incrementAndGet();
        latestMessage.set(in);
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(" [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(500);
            }
        }
    }

    public int getTotalMessageCount() {
        return counter.get();
    }

    public String getLatestMessage() {
        return latestMessage.get();
    }
}
