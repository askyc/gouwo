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
 * 菜单表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("peo_menu")
@ApiModel(value="MenuModel对象", description="菜单表")
public class MenuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "父级ID")
    private Integer parentId;

    @ApiModelProperty(value = "菜单级数")
    private String level;

    @ApiModelProperty(value = "菜单排序")
    private String sort;

    @ApiModelProperty(value = "是否隐藏")
    private String hidden;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;


}
