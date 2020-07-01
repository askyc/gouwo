package com.gouwo.service.impl;

import com.gouwo.mapper.PeoUserMapper;
import com.gouwo.model.PeoUserModel;
import com.gouwo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author asky
 * @since 2020-06-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<PeoUserMapper, PeoUserModel> implements UserService {

}
