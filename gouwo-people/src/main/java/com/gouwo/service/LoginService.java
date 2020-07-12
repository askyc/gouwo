package com.gouwo.service;

import com.gouwo.api.CommonResult;
import com.gouwo.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author asky
 * @since 2020-07-01
 */
public interface LoginService {

    /**
     * 注册
     */
    UserModel register(UserModel model);

    /**
     * 登录
     * @param account 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String account, String password);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByAccount(String account);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
