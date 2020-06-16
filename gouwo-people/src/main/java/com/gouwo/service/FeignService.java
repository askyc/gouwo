package com.gouwo.service;

import com.gouwo.service.impl.FeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "gouwo-essay",fallback = FeignServiceImpl.class)
public interface FeignService {
    /**
     * feign 测试
     */
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(name = "name") String name);
}
