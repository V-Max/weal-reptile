# weal-reptile
福利爬虫 不定时更新

### 环境
- JDK 1.8
- Spring Boot 2.0.4.RELEASE
- Elasticsearch 5.5.2
- webmagic 0.7.3
- lombok

### 说明
我们把爬到的数据全部存在Elasticsearch中，介绍一下接口：
```
各个爬虫启动接口：com.yi.weal.controller.ReptileController
Elasticsearch数据查询接口：com.yi.weal.controller.WealController
```
数据预览，使用ElasticSearch Head

![](https://i.imgur.com/KxRndk8.jpg)

接口查询：

![](https://i.imgur.com/4qlMERa.jpg)

由于没有去重 所以可能会有重复的数据

### 权限
所有爬取的数据都来自互联网，如有侵权请联系作者及时删除，此项目仅供学习爬虫之后用，不得用于非法途径。

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL
