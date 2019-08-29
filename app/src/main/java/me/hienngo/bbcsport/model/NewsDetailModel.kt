package me.hienngo.bbcsport.model

import android.text.Html
import android.text.Spanned
import me.hienngo.bbcsport.entity.NewsEntity

/**
 * @author hienngo
 */
data class NewsDetailModel(private val newsEntity: NewsEntity) {
    val title : String = newsEntity.title
    val content : Spanned = Html.fromHtml(newsEntity?.content ?: "")
    val imageUrl : String? = newsEntity.urlToImage
}