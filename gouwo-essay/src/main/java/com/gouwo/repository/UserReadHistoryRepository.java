package com.gouwo.repository;

import com.gouwo.dto.UserReadHistoryDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:42
 */
public interface UserReadHistoryRepository extends MongoRepository<UserReadHistoryDto,String> {
    /**
     * 根据用户id按时间倒序获取浏览记录
     * @param userId 用户id
     */
    List<UserReadHistoryDto> findReadHistoryByUserId(Integer userId);
}
