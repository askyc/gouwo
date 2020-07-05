package com.gouwo.dao;

import com.gouwo.entity.SearchArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/7/5 14:21
 * 图文搜索自定义Dao
 */
public interface SearchArticleDao {
    List<SearchArticle> getAllArticleList(@Param("id") Integer id);
}
