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
 * 个人租房时间线表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("peo_rent_timeline")
@ApiModel(value="RentTimelineModel对象", description="个人租房时间线表")
public class RentTimelineModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租房时间线id")
    @TableId(value = "rent_timeline_id", type = IdType.AUTO)
    private Integer rentTimelineId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "租金")
    private String rent;

    @ApiModelProperty(value = "租房开始时间")
    private LocalDate rentStartTime;

    @ApiModelProperty(value = "租房结束时间")
    private LocalDate rentEndTime;


}
