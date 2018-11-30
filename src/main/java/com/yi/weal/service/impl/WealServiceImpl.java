package com.yi.weal.service.impl;

import com.yi.weal.model.Weal;
import com.yi.weal.repository.WealSearchRepository;
import com.yi.weal.service.WealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * elasticsearch操作文章
 * @author YI
 * @date 2018-11-30 10:08:34
 */
@Service
public class WealServiceImpl implements WealService {
    @Autowired
    private WealSearchRepository wealSearchRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void wealSave(Weal weal) {
        wealSearchRepository.save(weal);
    }

    @Override
    public void wealSaveList(List<Weal> wealList) {
        wealSearchRepository.saveAll(wealList);
    }

    @Override
    public Iterable<Weal> searchAll() {
        return wealSearchRepository.findAll();
    }

    @Override
    public Iterable<Weal> findByTitle(String title) {
        return wealSearchRepository.findByTitle(title);
    }

    @Override
    public List<Weal> findByTitle(String content, Pageable pageable) {
        Page<Weal> pageageRsutl=wealSearchRepository.findByTitle(content, pageable );

        if (pageageRsutl == null) return null;

        return pageageRsutl.getContent();
    }

    @Override
    public List<Weal> searchQuery(SearchQuery searchQuery) {
        Page<Weal> searchPageResults = wealSearchRepository.search(searchQuery);

        if (searchPageResults == null) return null;

        return searchPageResults.getContent();
    }

    @Override
    public List<Weal> templateSearchQuery(SearchQuery searchQuery) {
        List<Weal> weals = elasticsearchTemplate.queryForList(searchQuery, Weal.class);
        if (weals == null) return null;

        return weals;
    }
}
