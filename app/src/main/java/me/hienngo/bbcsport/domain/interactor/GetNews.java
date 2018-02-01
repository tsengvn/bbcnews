package me.hienngo.bbcsport.domain.interactor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import me.hienngo.bbcsport.BuildConfig;
import me.hienngo.bbcsport.db.AppDatabase;
import me.hienngo.bbcsport.db.NewsDao;
import me.hienngo.bbcsport.domain.repo.NewsApiRepo;
import me.hienngo.bbcsport.model.NewsModel;

/**
 * @author hienngo
 * @since 2/1/18
 */

public class GetNews {
    private final NewsApiRepo newsApiRepo;
    private final NewsDao newsDao;
    private BehaviorSubject<List<NewsModel>> behaviorSubject = BehaviorSubject.create();

    public GetNews(NewsApiRepo newsApiRepo, AppDatabase appDatabase) {
        this.newsApiRepo = newsApiRepo;
        this.newsDao = appDatabase.newsDao();
        getDataFromDB();
    }

    public Flowable<List<NewsModel>> getData(boolean refresh) {
        if (refresh || !behaviorSubject.hasValue()) {
            getDataFromApi();
        }

        return behaviorSubject.toFlowable(BackpressureStrategy.BUFFER);
    }

    private void getDataFromDB() {
        newsDao.getAll()
                .subscribeOn(Schedulers.io())
                .flattenAsFlowable(ArrayList::new)
                .map(NewsModel::new)
                .toList()
                .filter(newsModels -> newsModels.size() > 0)
                .subscribe(newsModels -> behaviorSubject.onNext(newsModels));
    }

    private void getDataFromApi() {
        newsApiRepo.getTopHeadline(BuildConfig.API_KEY, "bbc-sport")
                .subscribeOn(Schedulers.io())
                .map(newsResponse -> {
                    newsDao.deleteAll();
                    return newsResponse.getArticles();
                })
                .doOnEvent((newsEntities, throwable) -> newsDao.add(newsEntities))
                .flattenAsObservable(ArrayList::new)
                .map(NewsModel::new)
                .toList()
                .subscribe(newsModel -> behaviorSubject.onNext(newsModel));

    }


}
