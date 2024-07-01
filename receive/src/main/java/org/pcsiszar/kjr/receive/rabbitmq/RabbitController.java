package org.pcsiszar.kjr.receive.rabbitmq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RabbitController {

    private final RabbitReceiver rabbitReceiver;

    public RabbitController(RabbitReceiver rabbitReceiver) {
        this.rabbitReceiver = rabbitReceiver;
    }

    @GetMapping("/total")
    public int getTotalMessageCount() {
        return rabbitReceiver.getTotalMessageCount();
    }

    @GetMapping("/latest")
    public String getLatestMessage() {
        return rabbitReceiver.getLatestMessage();
    }
}
