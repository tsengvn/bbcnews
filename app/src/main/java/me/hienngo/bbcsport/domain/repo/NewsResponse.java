package me.hienngo.bbcsport.domain.repo;

import java.util.List;

import me.hienngo.bbcsport.entity.NewsEntity;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class NewsResponse {
    private String status;
    private int totalResult;
    private List<NewsEntity> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<NewsEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsEntity> articles) {
        this.articles = articles;
    }
}
