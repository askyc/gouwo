package com.gouwo.service.impl;

import com.gouwo.model.PeoApiModel;
import com.gouwo.mapper.ApiMapper;
import com.gouwo.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author asky
 * @since 2020-07-01
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, PeoApiModel> implements ApiService {

}
