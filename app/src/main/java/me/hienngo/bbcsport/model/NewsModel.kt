package me.hienngo.bbcsport.model

import me.hienngo.bbcsport.entity.NewsEntity
import me.hienngo.bbcsport.util.DateTimeUtil

/**
 * @author hienngo
 */

data class NewsModel(private val newsEntity: NewsEntity) {
    val title: String = newsEntity.title ?: "No Title"
    val description: String = newsEntity.description ?: ""
    val timestamp: String = DateTimeUtil.parseTimestamp(newsEntity.publishedAt)
    val imageUrl: String? = newsEntity.urlToImage
    val webUrl: String? = newsEntity.url
}
