package com.gouwo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房子配套设施表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_room_support")
@ApiModel(value="RoomSupportModel对象", description="房子配套设施表")
public class RoomSupportModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "support_id", type = IdType.AUTO)
    private Integer supportId;

    @ApiModelProperty(value = "床")
    private String bed;

    @ApiModelProperty(value = "衣柜")
    private String wardrobe;

    @ApiModelProperty(value = "沙发")
    private String sofa;

    @ApiModelProperty(value = "桌子")
    private String table;

    @ApiModelProperty(value = "椅子")
    private String chair;

    @ApiModelProperty(value = "宽带")
    private String broadband;

    @ApiModelProperty(value = "空调")
    @TableField("airCondition")
    private String airCondition;

    @ApiModelProperty(value = "冰箱")
    private String icebox;

    @ApiModelProperty(value = "油烟机")
    @TableField("rangeHood")
    private String rangeHood;

    @ApiModelProperty(value = "微波炉")
    @TableField("microwaveOven")
    private String microwaveOven;

    @ApiModelProperty(value = "电视")
    private String television;

    @ApiModelProperty(value = "洗衣机")
    private String washer;

    @ApiModelProperty(value = "卫生间")
    private String bathroom;

    @ApiModelProperty(value = "阳台")
    private String balcony;

    @ApiModelProperty(value = "可做饭")
    @TableField("canCook")
    private String canCook;

    @ApiModelProperty(value = "热水器")
    @TableField("waterHeater")
    private String waterHeater;

    @ApiModelProperty(value = "暖气")
    private String heating;

    @ApiModelProperty(value = "其他")
    private String other;


}
