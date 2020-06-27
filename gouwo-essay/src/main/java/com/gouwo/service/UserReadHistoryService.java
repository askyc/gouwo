package com.gouwo.service;

import com.gouwo.entity.UserReadHistory;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:51
 */
public interface UserReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(UserReadHistory userReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<UserReadHistory> list(Integer userId);
}
