package com.gouwo.service.impl;

import com.gouwo.dao.SearchArticleDao;
import com.gouwo.dto.ArticleRelatedHouseDto;
import com.gouwo.dto.SearchArticleDto;
import com.gouwo.model.ArticleModel;
import com.gouwo.repository.SearchArticleRepository;
import com.gouwo.service.ArticleService;
import com.gouwo.service.SearchArticleService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author asky
 * @Date 2020/7/5 9:50
 */
@Service
public class SearchArticleServiceImpl implements SearchArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchArticleServiceImpl.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SearchArticleRepository searchArticleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private SearchArticleDao searchArticleDao;

    @Override
    public int importAll() {
        List<SearchArticleDto> articleList=new ArrayList<>();
        List<ArticleModel> list = articleService.list();
        for (ArticleModel articleModel : list) {
            SearchArticleDto article = new SearchArticleDto();
            BeanUtils.copyProperties(articleModel, article);
            articleList.add(article);
        }

        Iterable<SearchArticleDto> articleIterable = searchArticleRepository.saveAll(articleList);
        Iterator<SearchArticleDto> iterator = articleIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Integer articleId) {
        searchArticleRepository.deleteById(articleId);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<SearchArticleDto> articleList = new ArrayList<>();
            for (Integer id : ids) {
                SearchArticleDto searchArticle = new SearchArticleDto();
                searchArticle.setArticleId(id);
                articleList.add(searchArticle);
            }
            searchArticleRepository.deleteAll(articleList);
        }
    }

    @Override
    public SearchArticleDto create(Integer articleId) {
        SearchArticleDto result = null;
        ArticleModel article = articleService.getById(articleId);
        if (article!=null) {
            SearchArticleDto searchArticle = new SearchArticleDto();
            BeanUtils.copyProperties(article, searchArticle);
            result = searchArticleRepository.save(searchArticle);
        }
        return result;
    }

    @Override
    public Page<SearchArticleDto> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return searchArticleRepository.findByTitleOrLabelOrKeywords(keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<SearchArticleDto> search(String keyword, String label, String releaseLocation, Integer pageNum, Integer pageSize, Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (label != null || releaseLocation != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (label != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("label", label));
            }
            if (releaseLocation != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("releaseLocation", releaseLocation));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("title", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("label", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按发布时间
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }else if(sort==2){
            //按标签
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("label").order(SortOrder.DESC));
        }else if(sort==3){
            //按阅读量
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("readAmount").order(SortOrder.ASC));
        }else if(sort==4){
            //按租金
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("currentRent").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return searchArticleRepository.search(searchQuery);
    }

    @Override
    public Page<SearchArticleDto> recommend(Integer id, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<SearchArticleDto> articleList = searchArticleDao.getAllArticleList(id);
        if (articleList.size() > 0) {
            SearchArticleDto article = articleList.get(0);
            String keyword = article.getTitle();
            String label = article.getLabel();
            String releaseLocation = article.getReleaseLocation();
            //根据标题、标签、区域进行搜索
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("title", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("label", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("keywords", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("label", label),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("releaseLocation", releaseLocation),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withQuery(functionScoreQueryBuilder);
            builder.withPageable(pageable);
            NativeSearchQuery searchQuery = builder.build();
            LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
            return searchArticleRepository.search(searchQuery);
        }
        return new PageImpl<>(null);
    }

    @Override
    public ArticleRelatedHouseDto searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if(StringUtils.isEmpty(keyword)){
            builder.withQuery(QueryBuilders.matchAllQuery());
        }else{
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword,"title","label","keywords"));
        }
        //聚合搜索标签
        builder.addAggregation(AggregationBuilders.terms("label").field("label"));
        //集合搜索区域
        builder.addAggregation(AggregationBuilders.terms("releaseLocation").field("releaseLocation"));
        //聚合搜索属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues","attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs",QueryBuilders.termQuery("attrValueList.type",1))
                        .subAggregation(AggregationBuilders.terms("attrIds")
                                .field("attrValueList.productAttributeId")
                                .subAggregation(AggregationBuilders.terms("attrValues")
                                        .field("attrValueList.value"))
                                .subAggregation(AggregationBuilders.terms("attrNames")
                                        .field("attrValueList.name"))));
        builder.addAggregation(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.query(searchQuery, response -> {
            LOGGER.info("DSL:{}",searchQuery.getQuery().toString());
            return convertProductRelatedInfo(response);
        });
    }

    /**
     * 将返回结果转换为对象
     */
    private ArticleRelatedHouseDto convertProductRelatedInfo(SearchResponse response) {
        ArticleRelatedHouseDto relatedInfo = new ArticleRelatedHouseDto();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        //设置品牌
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for(int i = 0; i<((Terms) brandNames).getBuckets().size(); i++){
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        relatedInfo.setTitle(brandNameList);
        //设置分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for(int i=0;i<((Terms) productCategoryNames).getBuckets().size();i++){
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        relatedInfo.setReleaseLocation(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<LongTerms.Bucket> attrIds = ((LongTerms) ((InternalFilter) ((InternalNested) productAttrs).getProperty("productAttrs")).getProperty("attrIds")).getBuckets();
        List<ArticleRelatedHouseDto.HouseAttr> attrList = new ArrayList<>();
        for (Terms.Bucket attrId : attrIds) {
            ArticleRelatedHouseDto.HouseAttr attr = new ArticleRelatedHouseDto.HouseAttr();
            attr.setAttrId((Long) attrId.getKey());
            List<String> attrValueList = new ArrayList<>();
            List<StringTerms.Bucket> attrValues = ((StringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<StringTerms.Bucket> attrNames = ((StringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            attr.setAttrValues(attrValueList);
            if(!CollectionUtils.isEmpty(attrNames)){
                String attrName = attrNames.get(0).getKeyAsString();
                attr.setAttrName(attrName);
            }
            attrList.add(attr);
        }
        relatedInfo.setHouseAttr(attrList);
        return relatedInfo;
    }
}
