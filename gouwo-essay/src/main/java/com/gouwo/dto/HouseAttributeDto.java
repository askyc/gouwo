package com.gouwo.dto;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Author asky
 * @Date 2020/7/5 14:34
 * 搜索中图文关联的房子属性信息
 */
public class HouseAttributeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long houseAttributeId;

    @Field(type = FieldType.Keyword)
    private String value;

    private Integer type;

    @Field(type=FieldType.Keyword)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseAttributeId() {
        return houseAttributeId;
    }

    public void setHouseAttributeId(Long houseAttributeId) {
        this.houseAttributeId = houseAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
