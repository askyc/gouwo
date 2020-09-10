package com.gouwo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gouwo.model.ArticleModel;
import com.gouwo.vo.ArticleInfoVo;

/**
 * @Author asky
 * @Date 2020/6/27 20:25
 */
public interface ArticleService extends IService<ArticleModel> {
    IPage<ArticleInfoVo> getArticleInfoList(Page<?> page, String label);
}
