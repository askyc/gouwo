package com.gouwo.service.impl;

import com.gouwo.service.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceImpl implements FeignService {

    @Override
    public String hello(String name) {
        return "hello" + name + ", this is qyts-auth, but request error";
    }
}
