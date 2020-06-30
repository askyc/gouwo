package com.gouwo.controller;

import com.gouwo.api.CommonResult;
import com.gouwo.model.PeoUserModel;
import com.gouwo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PeoUserModel> register(@RequestBody PeoUserModel model, BindingResult result) {
        PeoUserModel  userModel= userService.register(model);
        if (userModel == null) {
            CommonResult.failed();
        }
        return CommonResult.success(userModel);
    }

    @ApiOperation(value = "登录(返回token)")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody PeoUserModel model, BindingResult result) {
        String token = userService.login(model.getUserName(), model.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

//    @ApiOperation("登录")
//    @RequestMapping("/login")
//    public String login(@RequestBody PeoUserModel model){
//        System.out.println("成功"+model.toString());
//        return "success";
//    }

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
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
