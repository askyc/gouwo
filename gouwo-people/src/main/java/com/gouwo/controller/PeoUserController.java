package com.gouwo.controller;


import com.gouwo.api.CommonResult;
import com.gouwo.model.PeoUser;
import com.gouwo.service.PeoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
@Api(tags = "PeoUserController", description = "people管理")
@RestController
@RequestMapping("/peo-user")
public class PeoUserController {

    @Resource
    private PeoUserService peoUserService;

    @ApiOperation("获取所有用户")
    @RequestMapping("/getUserList")
    public List<PeoUser> getUserList(){

        List<PeoUser> list=(List<PeoUser>) peoUserService.list();
        return list;
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return peoUserService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return peoUserService.verifyAuthCode(telephone,authCode);
    }
}
