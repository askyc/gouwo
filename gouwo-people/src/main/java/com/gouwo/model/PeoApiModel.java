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
 * 
 * </p>
 *
 * @author asky
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api")
@ApiModel(value="ApiModel对象", description="")
public class PeoApiModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "api_id", type = IdType.AUTO)
    private Integer apiId;

    @ApiModelProperty(value = "api分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "api代码")
    private String code;

    @ApiModelProperty(value = "api名称 ")
    private String name;

    @ApiModelProperty(value = "api URL")
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;


}
