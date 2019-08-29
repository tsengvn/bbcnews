package me.hienngo.bbcsport.db

import androidx.room.*

import io.reactivex.Maybe
import io.reactivex.Single
import me.hienngo.bbcsport.entity.NewsEntity

/**
 * @author hienngo
 * @since 2/1/18
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM news WHERE title=:title AND author=:author AND publishedAt=:publishedAt LIMIT 1")
    fun get(title: String, author: String, publishedAt: String) : NewsEntity?

    @Query("select * from news")
    fun getAll(): Single<List<NewsEntity>>

    @Query("delete from news")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: NewsEntity) : Long

    @Update
    fun update(entity: NewsEntity)

    @Transaction
    fun insertOrUpdate(newsEntities: List<NewsEntity>) {
        newsEntities.forEach {
            if (insert(it) == -1L) {
                update(it)
            }
        }
    }
}
