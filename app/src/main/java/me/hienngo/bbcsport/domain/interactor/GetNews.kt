package me.hienngo.bbcsport.domain.interactor


import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import me.hienngo.bbcsport.BuildConfig
import me.hienngo.bbcsport.db.AppDatabase
import me.hienngo.bbcsport.db.NewsDao
import me.hienngo.bbcsport.domain.repo.NewsApiRepo
import me.hienngo.bbcsport.entity.NewsEntity
import me.hienngo.bbcsport.model.NewsModel

/**
 * @author hienngo
 */

class GetNews(private val newsApiRepo: NewsApiRepo, appDatabase: AppDatabase) {
    private val newsDao: NewsDao = appDatabase.newsDao()
    private val behaviorSubject = BehaviorSubject.create<List<NewsModel>>()

    init {
        getDataFromDB()
    }

    fun getData(refresh: Boolean): Flowable<List<NewsModel>> {
        if (refresh || !behaviorSubject.hasValue()) {
            getDataFromApi()
        }

        return behaviorSubject.toFlowable(BackpressureStrategy.BUFFER)
    }

    private fun getDataFromDB() {
        newsDao.getAll()
                .subscribeOn(Schedulers.io())
                .map { daos -> daos.map { NewsModel(it) }.toList() }
                .filter { newsModels -> newsModels.size > 0 }
                .doOnSuccess { newsModels -> behaviorSubject.onNext(newsModels) }
                .subscribe()
    }

    private fun getDataFromApi() {
        newsApiRepo.getTopHeadline(BuildConfig.API_KEY, "bbc-sport")
                .subscribeOn(Schedulers.io())
                .map<List<NewsEntity>> { newsResponse ->
                    newsResponse.articles
                }
                .doOnEvent { newsEntities, throwable -> newsDao.insertOrUpdate(newsEntities) }
                .map { entities -> entities.map { NewsModel(it) }.toList() }
                .doOnSuccess { newsModel -> behaviorSubject.onNext(newsModel) }
                .subscribe()
    }


}
