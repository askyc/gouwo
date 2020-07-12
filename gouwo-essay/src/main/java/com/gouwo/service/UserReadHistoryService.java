package com.gouwo.service;



import com.gouwo.dto.UserReadHistoryDto;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:51
 */
public interface UserReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(UserReadHistoryDto userReadHistoryDto);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<UserReadHistoryDto> list(Integer userId);
}
