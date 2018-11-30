package com.yi.weal.repository;

import com.yi.weal.model.Weal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 操作模板
 * @author YI
 * @date 2018-11-30 10:08:49
 */
public interface WealSearchRepository extends ElasticsearchRepository<Weal, Long> {

    /**
     * 按照标题精确查找
     * @param title 文章标题
     * @return
     */
    List<Weal> findByTitle(String title);

    /**
     * 查询文章内容分页
     *
     * 等同于下面代码
     * @Query("{\"bool\" : {\"must\" : {\"term\" : {\"content\" : \"?0\"}}}}")
     * Page<Weal> findByContent(String content, Pageable pageable);
     *
     * @param content 文章内容
     * @param page  分页偶爱徐
     * @return
     */
    Page<Weal> findByTitle(String content, Pageable page);

    /**
     * NOT 语句查询
     *
     * @param content
     * @param page
     * @return
     */
    Page<Weal> findByTitleNot(String content, Pageable page);

    /**
     * LIKE 语句查询
     *
     * @param content
     * @param page
     * @return
     */
    Page<Weal> findByTitleLike(String content, Pageable page);

}
