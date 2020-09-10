package com.gouwo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gouwo.model.ArticleModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gouwo.vo.ArticleInfoVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 图文表 Mapper 接口
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
public interface ArticleMapper extends BaseMapper<ArticleModel> {

    @Select("SELECT * FROM ess_article WHERE label = #{label}")
    IPage<ArticleInfoVo> getArticleInfoList(Page<?> page, String label);
}
