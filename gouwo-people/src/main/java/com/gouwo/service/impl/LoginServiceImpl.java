package com.gouwo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gouwo.api.CommonResult;
import com.gouwo.bo.AdminUserDetails;
import com.gouwo.mapper.PeoRoleMapper;
import com.gouwo.mapper.PeoUserMapper;
import com.gouwo.model.PeoRoleModel;
import com.gouwo.model.PeoUserModel;
import com.gouwo.service.LoginService;
import com.gouwo.service.RedisService;
import com.gouwo.service.UserService;
import com.gouwo.util.JwtTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author asky
 * @since 2020-07-01
 */
@Service
public class LoginServiceImpl extends ServiceImpl<PeoUserMapper, PeoUserModel> implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeoUserServiceImpl.class);

    //@Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE="authCode";
    //@Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS= Long.valueOf(60);

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PeoUserMapper peoUserMapper;

    @Autowired
    private PeoRoleMapper peoRoleMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public PeoUserModel register(PeoUserModel model) {
        PeoUserModel userModel = new PeoUserModel();
        BeanUtils.copyProperties(model, userModel);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodePassword);
        peoUserMapper.insert(userModel);
        return userModel;
    }

    @Override
    public String login(String account, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByAccount(account);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserDetails loadUserByAccount(String account){
        //获取用户信息
        PeoUserModel user = peoUserMapper.selectById(2);
        if (user != null) {
            List<PeoRoleModel> roleList=new ArrayList<>();
            PeoRoleModel role= peoRoleMapper.selectById(1);
            roleList.add(role);
            return new AdminUserDetails(user,roleList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

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
