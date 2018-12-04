package com.yi.weal.controller;

import com.yi.weal.model.Weal;
import com.yi.weal.service.WealService;
import com.yi.weal.utils.MessageResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章操作
 * @author YI
 * @date 2018-8-13 11:06:05
 */
@RestController
@RequestMapping("/elasticsearch")
public class WealController {
    @Autowired
    WealService wealService;

    /**
     * 保存数据
     */
    @RequestMapping("/articleSaveList")
    public void articleSaveList(){
        List<Weal> weals = new ArrayList<>();

        wealService.wealSaveList(weals);
    }

    /**
     * 查找所有数据
     * @return
     */
    @RequestMapping("/findAll")
    public MessageResult findAll(){
        Iterable<Weal> weals = wealService.searchAll();

        return MessageResult.ok(weals);
    }

    /**
     * 按照标题精确查找
     * @param title 文章标题
     * @return
     */
    @RequestMapping("/findByTitle")
    public MessageResult findByTitle(String title){
        Iterable<Weal> weals = wealService.findByTitle(title);
        return MessageResult.ok(weals);
    }

    /**
     * 查找文章内容 分页排序
     * @param title 标题内容
     * @return
     */
    @RequestMapping("/findByTitlePage")
    public MessageResult findByTitlePage(String title){
        // 分页参数:分页从0开始，clickCount(点击量)倒序
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC,"creationTime");

        List<Weal> weals = wealService.findByTitle(title, pageable);

        return MessageResult.ok(weals);
    }

    /**
     * elasticsearchTemplate自定义查询：创建时间倒叙
     * @param title 标题
     * @return
     */
    @RequestMapping("/templateQuery")
    public MessageResult templateQuery(String title){
        // 分页参数:分页从0开始，title倒序
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC,"creationTime");

        // 构建条件为文章内容包含“美女”
        MatchQueryBuilder q1 = QueryBuilders.matchQuery("title", title);

        BoolQueryBuilder builder1 = QueryBuilders.boolQuery().must(q1);

        // weightFactorFunction是评分函数，详情请看文档
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                builder1, ScoreFunctionBuilders.weightFactorFunction(10)
        );

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder)
                .build();


        System.out.println("\n search DSL  = \n " + searchQuery.getQuery().toString());

        List<Weal> weals = wealService.templateSearchQuery(searchQuery);

        return MessageResult.ok(weals);
    }
}
