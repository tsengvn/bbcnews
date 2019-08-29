package me.hienngo.bbcsport.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author hienngo
 * @since 1/31/18
 */
@Entity(tableName = "news", primaryKeys = ["title", "author", "publishedAt"])
class NewsEntity {
    var id: Int = 0
    var news: String? = null
    var title: String = ""
    var author: String = ""
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String = ""
    var content: String? = null
}
