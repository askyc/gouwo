package com.gouwo.component;

import com.gouwo.model.UserModel;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author asky
 * @Date 2020/6/26 17:41
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(UserModel user) {
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend("hello", user);
    }

    public void send(String fanoutExchange) {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(fanoutExchange,"", context);
    }
}
