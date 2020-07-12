package com.gouwo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("peo_role_menu")
@ApiModel(value="RoleMenuModel对象", description="角色菜单关联表")
public class RoleMenuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "role_menu_id", type = IdType.AUTO)
    private Integer roleMenuId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;


}
