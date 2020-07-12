package com.gouwo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图文表 前端控制器
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name + ", this is feign-requst";
    }
}
