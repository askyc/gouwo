package com.gouwo.service;

import com.gouwo.api.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gouwo.model.PeoUserModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
public interface UserService extends IService<PeoUserModel> {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
