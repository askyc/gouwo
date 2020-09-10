package com.gouwo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gouwo.mapper.ArticleMapper;
import com.gouwo.model.ArticleModel;
import com.gouwo.service.ArticleService;
import com.gouwo.vo.ArticleInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author asky
 * @Date 2020/6/27 20:25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleModel> implements ArticleService {

    @Resource
    private com.gouwo.mapper.ArticleMapper articleMapper;

    @Override
    public IPage<ArticleInfoVo> getArticleInfoList(Page<?> page, String label) {
        return articleMapper.getArticleInfoList(page,label);
    }
}
