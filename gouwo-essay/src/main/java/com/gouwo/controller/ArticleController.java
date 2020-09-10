package com.gouwo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gouwo.service.ArticleService;
import com.gouwo.vo.ArticleInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取租房图文信息")
    @RequestMapping(value = "/getArticleInfoList", method = RequestMethod.GET)
    public IPage<ArticleInfoVo> getArticleInfoList(String label){
        IPage<ArticleInfoVo> articlelist=articleService.getArticleInfoList(new Page<>(1,10),label);
        return articlelist;
    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name + ", this is feign-requst";
    }
}
