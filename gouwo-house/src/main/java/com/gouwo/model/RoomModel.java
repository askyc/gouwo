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
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_room")
@ApiModel(value="RoomModel对象", description="房子信息表")
public class RoomModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房子ID")
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    @ApiModelProperty(value = "编号")
    private Integer roomNum;

    @ApiModelProperty(value = "户型")
    private String household;

    @ApiModelProperty(value = "面积")
    private String acreage;

    @ApiModelProperty(value = "朝向")
    private String towards;

    @ApiModelProperty(value = "楼层")
    private String floor;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "地址ID")
    private String addressId;

    @ApiModelProperty(value = "特征ID")
    private String featuresId;

    @ApiModelProperty(value = "类型：普通住宅等")
    private String houseType;

    @ApiModelProperty(value = "年代")
    private String period;

    @ApiModelProperty(value = "房主")
    private String homeowner;

    @ApiModelProperty(value = "出租状态")
    private String rentalStatus;

    @ApiModelProperty(value = "当前租客")
    private String currentTenant;

    @ApiModelProperty(value = "当前租金")
    private String currentRent;


}
