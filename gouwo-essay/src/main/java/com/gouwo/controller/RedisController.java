package com.gouwo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.gouwo.api.CommonResult;
import com.gouwo.model.ArticleModel;
import com.gouwo.service.ArticleService;
import com.gouwo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author asky
 * @Date 2020/6/27 21:04
 */
@Api(tags = "RedisController", description = "Redis测试")
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleService articleService;

    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/simpleTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ArticleModel> simpleTest() {
        List<ArticleModel> articleList = articleService.list();
        ArticleModel model = articleList.get(0);
        String key = "redis:simple:" + model.getArticleId();
        redisService.set(key, model);
        ArticleModel cacheArticle = (ArticleModel) redisService.get(key);
        return CommonResult.success(cacheArticle);
    }

    @ApiOperation("测试Hash结构的缓存")
    @RequestMapping(value = "/hashTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ArticleModel> hashTest() {
        List<ArticleModel> articleList = articleService.list();
        ArticleModel model = articleList.get(0);
        String key = "redis:hash:" + model.getArticleId();
        Map<String, Object> value = BeanUtil.beanToMap(model);
        redisService.hSetAll(key, value);
        Map<Object, Object> cacheValue = redisService.hGetAll(key);
        ArticleModel cacheArticle = BeanUtil.mapToBean(cacheValue, ArticleModel.class, true);
        return CommonResult.success(cacheArticle);
    }

    @ApiOperation("测试Set结构的缓存")
    @RequestMapping(value = "/setTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Set<Object>> setTest() {
        List<ArticleModel> articleList = articleService.list();
        String key = "redis:set:all";
        redisService.sAdd(key, (Object[]) ArrayUtil.toArray(articleList, ArticleModel.class));
        redisService.sRemove(key, articleList.get(0));
        Set<Object> cacheArticleList = redisService.sMembers(key);
        return CommonResult.success(cacheArticleList);
    }

    @ApiOperation("测试List结构的缓存")
    @RequestMapping(value = "/listTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Object>> listTest() {
        List<ArticleModel> articleList = articleService.list();
        String key = "redis:list:all";
        redisService.lPushAll(key, (Object[]) ArrayUtil.toArray(articleList, ArticleModel.class));
        redisService.lRemove(key, 1, articleList.get(0));
        List<Object> cacheArticleList = redisService.lRange(key, 0, 3);
        return CommonResult.success(cacheArticleList);
    }
}

