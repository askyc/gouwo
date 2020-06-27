package com.gouwo.controller;

import com.gouwo.component.CancelOrderSender;
import com.gouwo.component.HelloSender;
import com.gouwo.model.PeoUserModel;
import com.gouwo.service.RabbitmqService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author asky
 * @Date 2020/6/26 16:23
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @Autowired
    private HelloSender helloSender;

    @ApiOperation("生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object generateOrder(@RequestBody PeoUserModel peoUserModel) {
        return rabbitmqService.generateOrder(peoUserModel);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public void hello() throws Exception {
        helloSender.send();
    }
}
