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
 * 房子时间线表
 * </p>
 *
 * @author asky
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_room_timeline")
@ApiModel(value="HouRoomTimelineModel对象", description="房子时间线表")
public class HouRoomTimelineModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房子时间线id")
    @TableId(value = "room_timeline_id", type = IdType.AUTO)
    private Integer roomTimelineId;

    @ApiModelProperty(value = "房子id")
    private Integer roomId;

    @ApiModelProperty(value = "租金")
    private String rent;

    @ApiModelProperty(value = "租户")
    private String tenant;

    @ApiModelProperty(value = "出租开始时间")
    private LocalDate rentalStartTime;

    @ApiModelProperty(value = "出租结束时间")
    private LocalDate rentalEndTime;


}
