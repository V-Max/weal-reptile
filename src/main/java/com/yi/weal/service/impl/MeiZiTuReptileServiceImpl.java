package com.yi.weal.service.impl;

import com.yi.weal.config.Config;
import com.yi.weal.pipeline.ElasticSearchImagePipeline;
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
@Service("meizitu")
public class MeiZiTuReptileServiceImpl implements PageProcessor, ReptileService {
    @Autowired
    ElasticSearchImagePipeline elasticSearchPipeline;

    private static final Logger log = LoggerFactory.getLogger(MeiZiTuReptileServiceImpl.class);

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(500)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addHeader("Accept-Language", "zh-HK,zh-CN;q=0.9,zh;q=0.8")
            .addHeader("Connection", "keep-alive")
            .addHeader("X-Requested-With", "XMLHttpRequest")
            .addHeader("Referer", "https://www.mzitu.com")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

    @Override
    public void cronTask(int page) {
        StringBuffer url = new StringBuffer();

        url.append(Config.meizitu).append("/page/").append(page);

        Spider.create(new MeiZiTuReptileServiceImpl()).addUrl(url.toString()).thread(5).addPipeline(elasticSearchPipeline).run();

        log.info("当前页为 {}", page);
    }

    @Override
    public void process(Page page) {
//        List<String> images = new ArrayList<>();
//
        List<String> urls = page.getHtml().xpath("//*[@id='pins']/li/a/@href").all();
        page.addTargetRequests(urls);
//
//        List<String> allPages = page.getHtml().xpath("//*[@class='pagenavi']/a/span/text()").all();
//
//        int totalPages = Integer.parseInt(allPages.get(allPages.size() - 2));
//        for (int i = 1; i <= totalPages; i++) {
//            page.putField("title", page.getHtml().xpath("//div[@class='main-image']/p/a/@alt").toString());
//            images.add(page.getHtml().xpath("//div[@class='main-image']/p/a/@href").toString());
//        }

        //这个是提取的主站www.meizitu.com的每个突变的title，
        String webTitle = page.getHtml().xpath("//*[@id=\"maincontent\"]/div[1]/div[1]/h2/a/text()").toString();
        //先用xpath过滤到具体大的块（id为picture的容器中的p标签的img标签的src属性值），接着正则表达式提取出来（规律）
        List<String> all = page.getHtml().xpath("//*[@id=\"picture\"]/p/img[@src]").regex("(http:\\/\\/mm\\.howkuai\\.com\\/wp-content\\/uploads\\/(.*)\\.jpg)").all();

        System.out.println();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
