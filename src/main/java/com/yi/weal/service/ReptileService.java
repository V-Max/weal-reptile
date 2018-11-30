package com.yi.weal.service;

/**
 * 爬虫接口
 * @author YI
 * @date 2018-11-30 14:48:29
 */
public interface ReptileService {
    /**
     * 启动爬虫
     * @param page 当前页
     */
    void cronTask(int page);
}
