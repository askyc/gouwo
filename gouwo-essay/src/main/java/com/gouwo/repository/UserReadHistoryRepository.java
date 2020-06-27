package com.gouwo.repository;

import com.gouwo.entity.UserReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:42
 */
public interface UserReadHistoryRepository extends MongoRepository<UserReadHistory,String> {
    /**
     * 根据用户id按时间倒序获取浏览记录
     * @param userId 用户id
     */
    List<UserReadHistory> findReadHistoryByUserId(Integer userId);
}
