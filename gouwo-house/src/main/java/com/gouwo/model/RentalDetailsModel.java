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
 * 租费明细表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_rental_details")
@ApiModel(value="RentalDetailsModel对象", description="租费明细表")
public class RentalDetailsModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租费明细id")
    @TableId(value = "rental_details_id", type = IdType.AUTO)
    private Integer rentalDetailsId;

    @ApiModelProperty(value = "房子id")
    private Integer roomId;

    @ApiModelProperty(value = "月租金")
    private String monthlyRent;

    @ApiModelProperty(value = "电费")
    private String electricityFee;

    @ApiModelProperty(value = "水费")
    private String waterFee;

    @ApiModelProperty(value = "其他费用")
    private String otherFee;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createTime;


}
