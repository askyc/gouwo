package com.gouwo.component;

import com.gouwo.service.RabbitmqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "order.cancel")
public class CancelOrderReceiver {

    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

    @Autowired
    private RabbitmqService rabbitmqService;

    @RabbitHandler
    public void handle(Integer userId){
        rabbitmqService.cancelOrder(userId);
        LOGGER.info("process userId:{}",userId);
    }
}
