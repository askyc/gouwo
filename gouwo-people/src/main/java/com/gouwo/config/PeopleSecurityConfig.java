package com.gouwo.config;

import com.gouwo.component.DynamicSecurityService;
import com.gouwo.model.PeoApiModel;
import com.gouwo.service.LoginService;
import com.gouwo.service.PeoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Autowired
    private PeoApiService peoApiService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> loginService.loadUserByAccount(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                //todo 获取个人能访问的api
                List<PeoApiModel> apiList = peoApiService.list();
                for (PeoApiModel api : apiList) {
                    map.put(api.getUrl(), new org.springframework.security.access.SecurityConfig(api.getApiId() + ":" + api.getName()));
                }
                return map;
            }
        };
    }
}
