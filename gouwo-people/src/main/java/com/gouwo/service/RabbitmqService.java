package com.gouwo.service;

import com.gouwo.api.CommonResult;
import com.gouwo.model.UserModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author asky
 * @Date 2020/6/26 16:09
 */
public interface RabbitmqService {
    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(UserModel userModel);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Integer userId);
}
