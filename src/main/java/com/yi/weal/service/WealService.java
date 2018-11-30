package com.yi.weal.service;

import com.yi.weal.model.Weal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * elasticsearch操作文章
 * @author YI
 * @date 2018-11-30 10:09:01
 */
public interface WealService {

    /**
     * 保存单个实例
     * @param weal
     */
    void wealSave(Weal weal);

    /**
     * 批量保存
     * @param wealList
     */
    void wealSaveList(List<Weal> wealList);

    /**
     * 查找全部
     * @return
     */
    Iterable<Weal> searchAll();

    /**
     * 按照标题精确查找
     * @param title 文章标题
     * @return
     */
    Iterable<Weal> findByTitle(String title);

    /**
     * 查找标题内容 分页排序
     * @param title     标题内容
     * @param pageable  分页排序
     * @return
     */
    List<Weal> findByTitle(String title, Pageable pageable);

    /**
     * 多条件聚合搜索
     * @param searchQuery   搜索条件
     * @return
     */
    List<Weal> searchQuery(SearchQuery searchQuery);

    /**
     * 通过模板引擎查询
     * @param searchQuery 查询条件
     * @return
     */
    List<Weal> templateSearchQuery(SearchQuery searchQuery);
}
