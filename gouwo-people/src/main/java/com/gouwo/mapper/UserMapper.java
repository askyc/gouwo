package com.gouwo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gouwo.model.UserModel;

import java.util.List;

/**
 * @author asky
 * @since 2020-06-14
 */
public interface UserMapper extends BaseMapper<UserModel> {

    List<UserModel> findUser();
}
