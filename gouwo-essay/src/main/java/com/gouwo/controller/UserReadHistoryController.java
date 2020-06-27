package com.gouwo.controller;

import com.gouwo.api.CommonResult;
import com.gouwo.entity.UserReadHistory;
import com.gouwo.service.UserReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:59
 */

@Api(tags = "UserReadHistoryController", description = "用户图文浏览记录管理")
@RestController
@RequestMapping("/readHistory")
public class UserReadHistoryController {

    @Autowired
    private UserReadHistoryService userReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UserReadHistory userReadHistory) {
        int count = userReadHistoryService.create(userReadHistory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        int count = userReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UserReadHistory>> list(Integer userId) {
        List<UserReadHistory> userReadHistoryList = userReadHistoryService.list(userId);
        return CommonResult.success(userReadHistoryList);
    }
}
