package com.gouwo.component;

import com.gouwo.model.PeoUserModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author asky
 * @Date 2020/6/26 17:41
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

    @RabbitHandler
    public void process(PeoUserModel user) {
        System.out.println("Receiver object : " + user);
    }


}
