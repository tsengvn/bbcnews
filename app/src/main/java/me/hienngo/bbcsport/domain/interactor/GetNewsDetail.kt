package me.hienngo.bbcsport.domain.interactor

import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import me.hienngo.bbcsport.db.AppDatabase
import me.hienngo.bbcsport.db.NewsDao
import me.hienngo.bbcsport.domain.repo.NewsApiRepo
import me.hienngo.bbcsport.model.NewsDetailModel

/**
 * @author hienngo
 */
class GetNewsDetail(private val appDatabase: AppDatabase) {
    private val newsDao: NewsDao = appDatabase.newsDao()

    fun getNews(title: String, author: String, publishedAt: String) : Single<NewsDetailModel> {
        return Single.create(object : SingleOnSubscribe<NewsDetailModel> {
            override fun subscribe(emitter: SingleEmitter<NewsDetailModel>) {
                newsDao.get(title, author, publishedAt)?.run {
                    emitter.onSuccess(NewsDetailModel(this))
                    return
                }
                emitter.onError(IllegalArgumentException("Cannot find news with title=$title, author=$author"))
            }
        }).subscribeOn(Schedulers.io())
    }

}