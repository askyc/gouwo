package com.gouwo.service;

import com.gouwo.dto.ArticleRelatedHouseDto;
import com.gouwo.dto.SearchArticleDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/7/5 9:48
 */
public interface SearchArticleService {
    /**
     * 导入数据库中所有图文到ES
     */
    int importAll();

    /**
     * 根据id删除图文
     */
    void delete(Integer articleId);

    /**
     * 批量删除图文
     */
    void deleteAll(List<Integer> ids);

    /**
     * 根据id创建图文
     */
    SearchArticleDto create(Integer articleId);

    /**
     * 根据关键字搜索图文或者标题
     */
    Page<SearchArticleDto> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字搜索图文或者标题复合查询
     */
    Page<SearchArticleDto> search(String keyword, String brandId, String releaseLocation, Integer pageNum, Integer pageSize,Integer sort);

    /**
     * 根据图文id推荐相关图文
     */
    Page<SearchArticleDto> recommend(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 获取搜索词相关图文、房源信息、房子时间线记录
     */
    ArticleRelatedHouseDto searchRelatedInfo(String keyword);
}
