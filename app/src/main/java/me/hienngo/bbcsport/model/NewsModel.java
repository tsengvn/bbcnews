package me.hienngo.bbcsport.model;

import me.hienngo.bbcsport.entity.NewsEntity;
import me.hienngo.bbcsport.util.DateTimeUtil;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class NewsModel {
    private String title;
    private String description;
    private String timestamp;
    private String imageUrl;
    private String webUrl;

    public NewsModel(NewsEntity newsEntity) {
        title = newsEntity.getTitle();
        description = newsEntity.getDescription();
        timestamp = DateTimeUtil.parseTimestamp(newsEntity.getPublishedAt());
        imageUrl = newsEntity.getUrlToImage();
        webUrl = newsEntity.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
