package com.gouwo.controller;

import com.gouwo.api.CommonPage;
import com.gouwo.api.CommonResult;
import com.gouwo.entity.ArticleRelatedHouseInfo;
import com.gouwo.entity.SearchArticle;
import com.gouwo.service.SearchArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author asky
 * @Date 2020/7/5 9:51
 */

@RestController
@Api(tags = "SearchArticleController", description = "图文搜索")
@RequestMapping("/searchArticle")
public class SearchArticleController {

    @Autowired
    private SearchArticleService searchArticleService;

    @ApiOperation(value = "导入数据库中所有图文到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = searchArticleService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除图文")
    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Integer articleId) {
        searchArticleService.delete(articleId);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除图文")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> deleteAll(@RequestParam("ids") List<Integer> ids) {
        searchArticleService.deleteAll(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建图文")
    @RequestMapping(value = "/create/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<SearchArticle> create(@PathVariable Integer articleId) {
        SearchArticle searchArticle = searchArticleService.create(articleId);
        if (searchArticle != null) {
            return CommonResult.success(searchArticle);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SearchArticle>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<SearchArticle> searchArticlePage = searchArticleService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(searchArticlePage));
    }

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按发布时间；2->按标签；3->按阅读量；4->按租金",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SearchArticle>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false) String label,
                                                      @RequestParam(required = false) String releaseLocation,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort) {
        Page<SearchArticle> searchArticlePage = searchArticleService.search(keyword, label, releaseLocation, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(searchArticlePage));
    }

    @ApiOperation(value = "根据商品id推荐商品")
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SearchArticle>> recommend(@PathVariable Integer id,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<SearchArticle> articlePage = searchArticleService.recommend(id, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(articlePage));
    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ArticleRelatedHouseInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        ArticleRelatedHouseInfo relatedInfo = searchArticleService.searchRelatedInfo(keyword);
        return CommonResult.success(relatedInfo);
    }
}
