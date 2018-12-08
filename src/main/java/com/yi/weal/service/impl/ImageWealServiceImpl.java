package com.yi.weal.service.impl;

import com.yi.weal.model.ImageWeal;
import com.yi.weal.repository.ImageWealSearchRepository;
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
@Service("imageWeal")
public class ImageWealServiceImpl implements WealService<ImageWeal> {
    @Autowired
    private ImageWealSearchRepository wealSearchRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void wealSave(ImageWeal weal) {
        wealSearchRepository.save(weal);
    }

    @Override
    public void wealSaveList(List<ImageWeal> wealList) {
        wealSearchRepository.saveAll(wealList);
    }

    @Override
    public Iterable<ImageWeal> searchAll() {
        return wealSearchRepository.findAll();
    }

    @Override
    public Iterable<ImageWeal> findByTitle(String title) {
        return wealSearchRepository.findByTitle(title);
    }

    @Override
    public List<ImageWeal> findByTitle(String content, Pageable pageable) {
        Page<ImageWeal> pageageRsutl=wealSearchRepository.findByTitle(content, pageable );

        if (pageageRsutl == null) return null;

        return pageageRsutl.getContent();
    }

    @Override
    public List<ImageWeal> searchQuery(SearchQuery searchQuery) {
        Page<ImageWeal> searchPageResults = wealSearchRepository.search(searchQuery);

        if (searchPageResults == null) return null;

        return searchPageResults.getContent();
    }

    @Override
    public List<ImageWeal> templateSearchQuery(SearchQuery searchQuery) {
        List<ImageWeal> weals = elasticsearchTemplate.queryForList(searchQuery, ImageWeal.class);
        if (weals == null) return null;

        return weals;
    }
}
