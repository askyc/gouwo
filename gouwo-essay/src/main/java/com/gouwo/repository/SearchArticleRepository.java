package com.gouwo.repository;

import com.gouwo.dto.SearchArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author asky
 * @Date 2020/7/5 9:45
 */
public interface SearchArticleRepository extends ElasticsearchRepository<SearchArticleDto, Integer> {

    /**
     * 搜索查询
     * @param title 图文标题
     * @param label 图文标签
     * @param keywords 图文关键字
     * @param page     分页信息
     * @return
     */
    Page<SearchArticleDto> findByTitleOrLabelOrKeywords(String title, String label, String keywords, Pageable page);

}
