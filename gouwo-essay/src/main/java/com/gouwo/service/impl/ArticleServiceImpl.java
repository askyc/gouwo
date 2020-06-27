package com.gouwo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gouwo.mapper.EssArticleMapper;
import com.gouwo.model.EssArticleModel;
import com.gouwo.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @Author asky
 * @Date 2020/6/27 20:25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<EssArticleMapper, EssArticleModel> implements ArticleService {

}
