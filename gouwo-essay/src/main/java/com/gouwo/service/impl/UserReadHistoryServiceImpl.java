package com.gouwo.service.impl;

import com.gouwo.entity.UserReadHistory;
import com.gouwo.repository.UserReadHistoryRepository;
import com.gouwo.service.UserReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author asky
 * @Date 2020/6/27 9:54
 */
@Service
public class UserReadHistoryServiceImpl implements UserReadHistoryService {

    @Autowired
    private UserReadHistoryRepository userReadHistoryRepository;

    @Override
    public int create(UserReadHistory userReadHistory) {
        userReadHistory.setId(null);
        userReadHistory.setReleaseTime(new Date());
        userReadHistoryRepository.save(userReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<UserReadHistory> deleteList = new ArrayList<>();
        for(String id:ids){
            UserReadHistory userReadHistory = new UserReadHistory();
            userReadHistory.setId(id);
            deleteList.add(userReadHistory);
        }
        userReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<UserReadHistory> list(Integer userId) {
        return userReadHistoryRepository.findReadHistoryByUserId(userId);
    }
}
