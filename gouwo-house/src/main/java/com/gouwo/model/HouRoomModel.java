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
 * 房子信息表
 * </p>
 *
 * @author asky
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_room")
@ApiModel(value="HouRoomModel对象", description="房子信息表")
public class HouRoomModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房子id")
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    @ApiModelProperty(value = "房主")
    private String homeowner;

    @ApiModelProperty(value = "面积")
    private String area;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "出租状态")
    private String rentalStatus;

    @ApiModelProperty(value = "当前租客")
    private String currentTenant;

    @ApiModelProperty(value = "当前租金")
    private String currentRent;


}
