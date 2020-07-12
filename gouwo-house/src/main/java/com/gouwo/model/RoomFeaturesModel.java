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
 * 房子特征表
 * </p>
 *
 * @author asky
 * @since 2020-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hou_room_features")
@ApiModel(value="RoomFeaturesModel对象", description="房子特征表")
public class RoomFeaturesModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "特征ID")
    @TableId(value = "features_id", type = IdType.AUTO)
    private Integer featuresId;

    @ApiModelProperty(value = "地铁")
    private String subway;

    @ApiModelProperty(value = "公交")
    private String bus;

    @ApiModelProperty(value = "电梯:Y-有，N-无")
    private String elevator;

    @ApiModelProperty(value = "首次出租:Y-是，N-否")
    private String firstRent;

    @ApiModelProperty(value = "装修风格:精装修等")
    private String style;

    @ApiModelProperty(value = "视频看房:Y-有，N-无")
    private String videoHouse;

    @ApiModelProperty(value = "VR看房:Y-有，N-无")
    private String vrSee;

    @ApiModelProperty(value = "其他：1.智能生活 2.深呼吸 3.智能空净 4.阁楼 5.独立阳台 6.离地铁近 7.绿化率高")
    private String other;


}
