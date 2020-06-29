package com.gouwo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gouwo.model.PeoUserModel;

import java.util.List;

/**
 * @author asky
 * @since 2020-06-14
 */
public interface UserMapper extends BaseMapper<PeoUserModel> {

    List<PeoUserModel> findUser();
}
