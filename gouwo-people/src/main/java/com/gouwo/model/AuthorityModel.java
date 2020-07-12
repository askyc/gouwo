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
 * 权限表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("peo_authority")
@ApiModel(value="AuthorityModel对象", description="权限表")
public class AuthorityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "authority_id", type = IdType.AUTO)
    private Integer authorityId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "权限码")
    private String authorityCode;

    @ApiModelProperty(value = "权限名称")
    private String authorityName;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;

    @ApiModelProperty(value = "状态")
    private String status;


}
