package com.gouwo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图文表
 * </p>
 *
 * @author asky
 * @since 2020-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ess_article")
@ApiModel(value="EssArticleModel对象", description="图文表")
public class EssArticleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图文id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "封面")
    private String titlePage;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "发布者")
    private String publisher;

    @ApiModelProperty(value = "发布时间")
    private LocalDate releaseTime;

    @ApiModelProperty(value = "发布地点")
    private String releaseLocation;

    @ApiModelProperty(value = "状态：draft-草稿,released-已发布, deleted-已删除")
    private String articleStatus;

    @ApiModelProperty(value = "阅读量")
    private String readAmount;

    @ApiModelProperty(value = "点赞量")
    private String likeAmount;

    @ApiModelProperty(value = "收藏量")
    private String collectAmount;


}
