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
 * 图文评论表
 * </p>
 *
 * @author asky
 * @since 2020-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ess_comment")
@ApiModel(value="EssCommentModel对象", description="图文评论表")
public class EssCommentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @ApiModelProperty(value = "图文id")
    private Integer articleId;

    @ApiModelProperty(value = "评论者id")
    private Integer userId;

    @ApiModelProperty(value = "评论内容")
    private String reviewContent;

    @ApiModelProperty(value = "评论时间")
    private LocalDate reviewTime;


}
