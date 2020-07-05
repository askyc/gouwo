package com.gouwo.entity;

import java.util.List;

/**
 * @Author asky
 * @Date 2020/7/5 13:47
 * * 相关图文、出租房拥有者，及房子时间线信息
 */
public class ArticleRelatedHouseInfo {

    private List<String> title;
    private List<String> releaseLocation;
    private List<HouseAttr> houseAttr;

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getReleaseLocation() {
        return releaseLocation;
    }

    public void setReleaseLocation(List<String> releaseLocation) {
        this.releaseLocation = releaseLocation;
    }

    public List<HouseAttr> getHouseAttr() {
        return houseAttr;
    }

    public void setHouseAttr(List<HouseAttr> houseAttr) {
        this.houseAttr = houseAttr;
    }

    public static class HouseAttr{
        private Long attrId;
        private String attrName;
        private List<String> attrValues;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public List<String> getAttrValues() {
            return attrValues;
        }

        public void setAttrValues(List<String> attrValues) {
            this.attrValues = attrValues;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }
    }
}
