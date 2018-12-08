package com.yi.weal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务配置类
 * @author YI
 * @date 2018-11-30 10:38:11
 */
@Configuration
public class Config {
    public static final String URL_NRC = "http://www.nrcb99.com";

    public static final String meizitu = "https://www.mzitu.com";

    public static AtomicInteger nextInc = new AtomicInteger(0);
}
