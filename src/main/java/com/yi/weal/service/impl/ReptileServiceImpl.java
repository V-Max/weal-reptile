package com.yi.weal.service.impl;

import com.yi.weal.config.Config;
import com.yi.weal.pipeline.ElasticSearchVideoPipeline;
import com.yi.weal.service.ReptileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫
 * @author YI
 * @date 2018-11-30 10:23:40
 */
@Service("reptile")
public class ReptileServiceImpl implements PageProcessor, ReptileService {
    @Autowired
    ElasticSearchVideoPipeline elasticSearchPipeline;

    private static final Logger log = LoggerFactory.getLogger(ReptileServiceImpl.class);

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Connection", "keep-alive")
            .addHeader("Accept-Language", "zh-HK,zh-CN;q=0.9,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("DNT", "1")
            .addHeader("Host", "www.nrcb99.com")
            .addHeader("Cookie", "PHPSESSID=bln0qkuaqms8375jq9vdbh5em2; UM_distinctid=1675fa3831fb2c-03c8ee6fe39375-b79183d-1fa400-1675fa38321d13; CNZZDATA1262368633=912818702-1543492774-%7C1543497825")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

    @Override
    public void cronTask(int page) {
        StringBuffer url = new StringBuffer();

        url.append(Config.URL_NRC).append("/Videos/showVideosList?name=国产无码&page=").append(page);

        Spider.create(new ReptileServiceImpl()).addUrl(url.toString()).thread(5).addPipeline(elasticSearchPipeline).run();

        log.info("当前页为 {}", page);
    }

    @Override
    public void process(Page page) {
        List<String> urls = new ArrayList<>();
        List<String> ids = page.getHtml().xpath("//*[@class='pic_list']/dd/a/@href").all();
        ids.forEach(e -> urls.add(Config.URL_NRC + e));
        page.addTargetRequests(urls);

        page.putField("title", page.getHtml().xpath("//h1[@id='h_title']/text()").toString());
        page.putField("video", page.getHtml().xpath("//*[@id='videoPlay']/@src").toString());
        page.putField("cover", page.getHtml().xpath("//*[@id='videoPlay']/@poster").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
