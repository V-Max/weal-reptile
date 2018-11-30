package com.yi.weal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体
 * @author YI
 * @date 2018-8-13 10:49:34
 */
@Data
@Document(indexName="weal_index",type="weal",shards=5,replicas=1,indexStoreType="fs",refreshInterval="-1")
public class Weal implements Serializable {
    private static final long serialVersionUID = 551589397625941750L;

    @Id
    private Integer id;
    /**标题*/
    private String title;
    /**摘要*/
    private String video;
    /**内容*/
    private String cover;
    /**创建时间*/
    private Date creationTime = new Date();

    public Weal() {
        super();
    }

    public Weal(Integer id, String title, String video, String cover) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.cover = cover;
    }

    public Weal(Integer id, String title, String video, String cover, Date creationTime) {
        this.id = id;
        this.title = title;
        this.video = video;
        this.cover = cover;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Weal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", video='" + video + '\'' +
                ", cover='" + cover + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
