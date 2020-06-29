package com.gouwo.controller;

import com.gouwo.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {


    @Autowired
    private FeignService feignService;

    @GetMapping("/feignHello/{name}")
    public String feignHello(@PathVariable String name) {
        return feignService.hello(name);
    }
}
