package com.gouwo.controller;

import com.gouwo.api.CommonResult;
import com.gouwo.model.PeoUserModel;
import com.gouwo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
@Api(tags = "UserController", description = "people管理")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("获取所有用户")
    @RequestMapping("/getUserList")
    public List<PeoUserModel> getUserList(){

        List<PeoUserModel> list=(List<PeoUserModel>) userService.list();
        return list;
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return userService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return userService.verifyAuthCode(telephone,authCode);
    }


}
