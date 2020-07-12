package com.gouwo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author asky
 * @Date 2020/7/5 9:25
 */
@Document(indexName = "ess", type = "article",shards = 1,replicas = 0)
public class SearchArticleDto implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Integer articleId;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String label;

    private String content;

    private String titlePage;

    private String publisher;

    private LocalDate releaseTime;

    private String releaseLocation;

    private BigDecimal currentRent;

    private Integer readAmount;

    private Integer sort;

    @Field(type =FieldType.Nested)
    private List<HouseAttributeDto> attrValueList;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitlePage() {
        return titlePage;
    }

    public void setTitlePage(String titlePage) {
        this.titlePage = titlePage;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReleaseLocation() {
        return releaseLocation;
    }

    public void setReleaseLocation(String releaseLocation) {
        this.releaseLocation = releaseLocation;
    }

    public BigDecimal getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(BigDecimal currentRent) {
        this.currentRent = currentRent;
    }

    public Integer getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(Integer readAmount) {
        this.readAmount = readAmount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<HouseAttributeDto> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<HouseAttributeDto> attrValueList) {
        this.attrValueList = attrValueList;
    }
}
