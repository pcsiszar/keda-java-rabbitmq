package org.pcsiszar.kjr.send.rabbitmq;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RabbitController {

    private final RabbitSender rabbitSender;

    public RabbitController(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam(name = "message") String message, @RequestParam(name = "count") int count) {
        rabbitSender.send(message, count);
    }

    @PostMapping("/disable")
    public void disable() {
        rabbitSender.disableSending();
    }
}
