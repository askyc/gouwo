package com.gouwo.service.impl;

import com.gouwo.api.CommonResult;
import com.gouwo.model.PeoUser;
import com.gouwo.mapper.PeoUserMapper;
import com.gouwo.service.PeoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gouwo.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
@Service
public class PeoUserServiceImpl extends ServiceImpl<PeoUserMapper, PeoUser> implements PeoUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeoUserServiceImpl.class);

    //@Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE="authCode";
    //@Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS= Long.valueOf(60);

    @Autowired
    private RedisService redisService;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }

}
