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
 * 
 * </p>
 *
 * @author asky
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role_menu")
@ApiModel(value="RoleMenuModel对象", description="")
public class RoleMenuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_menu_id", type = IdType.AUTO)
    private Integer roleMenuId;

    private Integer roleId;

    private Integer menuId;


}
