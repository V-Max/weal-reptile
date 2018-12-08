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
@Document(indexName="image_index",type="image",shards=5,replicas=1,indexStoreType="fs",refreshInterval="-1")
public class ImageWeal implements Serializable {
    private static final long serialVersionUID = 551589397625941751L;

    @Id
    private Integer id;
    /**标题*/
    private String title;
    /**图片集合*/
    private String images;
    /**创建时间*/
    private Date creationTime = new Date();

    public ImageWeal() {
        super();
    }

    public ImageWeal(Integer id, String title, String images, Date creationTime) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.creationTime = creationTime;
    }
}
