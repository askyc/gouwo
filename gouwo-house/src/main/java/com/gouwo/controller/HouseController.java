package com.gouwo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {

    @ApiOperation("aspect切面测试")
    @RequestMapping("/aspect")
    public String AspectTest(){
        System.out.println("================================================");
        return "success";
    }
}
