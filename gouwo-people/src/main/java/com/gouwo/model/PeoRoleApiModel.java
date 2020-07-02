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
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("peo_role_api")
@ApiModel(value="PeoRoleApiModel对象", description="")
public class PeoRoleApiModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_api_id", type = IdType.AUTO)
    private Integer roleApiId;

    private Integer roleId;

    private Integer apiId;


}