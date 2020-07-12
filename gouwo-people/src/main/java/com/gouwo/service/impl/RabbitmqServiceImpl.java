package com.gouwo.service.impl;

import com.gouwo.api.CommonResult;
import com.gouwo.component.CancelOrderSender;
import com.gouwo.model.UserModel;
import com.gouwo.service.RabbitmqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author asky
 * @Date 2020/6/26 16:11
 */
@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    private static Logger LOGGER = LoggerFactory.getLogger(RabbitmqServiceImpl.class);

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(UserModel userModel) {
        //todo 执行一系类下单操作
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单
        sendDelayMessageCancelOrder(1);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Integer userId) {
        //todo 执行一系类取消订单操作
        LOGGER.info("process cancelOrder userId:{}",userId);
    }

    private void sendDelayMessageCancelOrder(Integer userId) {
        //获取订单超时时间，假设为30秒
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(userId, delayTimes);
    }

}
