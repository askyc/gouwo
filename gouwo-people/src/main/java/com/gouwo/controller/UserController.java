package com.gouwo.controller;

import com.gouwo.model.UserModel;
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
@Api(tags = "UserController", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public List<UserModel> getUserList(){
        List<UserModel> list=userService.list();
        return list;
    }

}
