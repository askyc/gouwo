package com.gouwo.config;

import com.gouwo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author asky
 * @since 2020-06-30
 * gouwo-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PeopleSecurityConfig extends SecurityConfig {

    @Autowired
    private LoginService loginService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> loginService.loadUserByAccount(username);
    }
}
