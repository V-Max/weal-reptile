package com.yi.weal.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.yi.weal.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 启动爬虫
 * @author YI
 * @date 2018-11-30 14:42:11
 */
@Controller
public class ReptileController {
    @Autowired
    ReptileService reptileService;

    /**
     * NRC网站爬虫
     */
    @RequestMapping(value = "/startNrc", method = RequestMethod.GET)
    public void startNrc(){
        for (int i = 1; i < 50; i++) {
//            int finalI = i;
//            ThreadUtil.newExecutor().execute(() -> reptileService.cronTask(finalI));
            reptileService.cronTask(i);
        }

    }
}
